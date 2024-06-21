package org.fluffy.pet.rms.resourcemanagement.service.impl;

import com.fasterxml.jackson.databind.JsonNode;
import org.fluffy.pet.rms.resourcemanagement.configuration.contexts.UserContext;
import org.fluffy.pet.rms.resourcemanagement.dto.request.client.ClientRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.client.ClientResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ErrorResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.exception.AppException;
import org.fluffy.pet.rms.resourcemanagement.exception.RestException;
import org.fluffy.pet.rms.resourcemanagement.helper.FilterHelper;
import org.fluffy.pet.rms.resourcemanagement.model.client.Client;
import org.fluffy.pet.rms.resourcemanagement.repository.ClientRepository;
import org.fluffy.pet.rms.resourcemanagement.service.ClientService;
import org.fluffy.pet.rms.resourcemanagement.transformer.ClientTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {
    private final ClientRepository clientRepository;

    private final ClientTransformer clientTransformer;

    private final FilterHelper<ClientResponse> filterHelper;

    private final UserContext userContext;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, ClientTransformer clientTransformer, FilterHelper<ClientResponse> filterHelper, UserContext userContext) {
        this.clientRepository = clientRepository;
        this.clientTransformer = clientTransformer;
        this.filterHelper = filterHelper;
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

    @Override
    public PaginationWrapper<List<JsonNode>> filterClients(FilterRequest filterRequest) {
        try {
            return filterHelper.filterEntities(
                    filterRequest,
                    this::convertModelToResponseFromFilterRequest
            );
        } catch (AppException appException) {
            ErrorResponse errorResponse = ErrorResponse.from(ErrorCode.INVALID_FILTER_REQUEST);
            errorResponse.setDetail(appException.getMessage());
            throw new RestException(HttpStatus.BAD_REQUEST, errorResponse);
        }
    }

    private Page<ClientResponse> convertModelToResponseFromFilterRequest(FilterRequest filterRequest) {
        Page<Client> clients = clientRepository.filterDocuments(
                filterRequest.getFilters(),
                filterRequest.getSort(),
                filterRequest.getPage(),
                filterRequest.getPageSize()
        );
        return clients.map(clientTransformer::convertModelToResponse);
    }
}
