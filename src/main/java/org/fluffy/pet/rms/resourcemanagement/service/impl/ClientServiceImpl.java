package org.fluffy.pet.rms.resourcemanagement.service.impl;

import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.UserContext;
import org.fluffy.pet.rms.resourcemanagement.dto.request.client.ClientRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.client.ClientResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.model.client.Client;
import org.fluffy.pet.rms.resourcemanagement.repository.ClientRepository;
import org.fluffy.pet.rms.resourcemanagement.service.ClientService;
import org.fluffy.pet.rms.resourcemanagement.transformer.ClientTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    private final ClientTransformer clientTransformer;

    private final UserContext userContext;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientTransformer clientTransformer, UserContext userContext) {
        this.clientRepository = clientRepository;
        this.clientTransformer = clientTransformer;
        this.userContext = userContext;
    }

    @Override
    public ClientResponse getCurrentClient() {
        Client client = clientRepository.findById(userContext.getUserId()).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.CLIENT_NOT_FOUND))
        );
        return clientTransformer.convertModelToResponse(client);
    }

    @Override
    public ClientResponse updateCurrentClient(ClientRequest clientRequest) {
        Client client = clientRepository.findById(userContext.getUserId()).orElseThrow(
                () -> new RestException(HttpStatus.NOT_FOUND, ErrorResponse.from(ErrorCode.CLIENT_NOT_FOUND))
        );
        clientTransformer.updateClient(client, clientRequest);
        Client updatedClient = clientRepository.save(client);
        return clientTransformer.convertModelToResponse(updatedClient);
    }
}
