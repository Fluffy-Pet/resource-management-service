package org.fluffy.pet.rms.resourcemanagement.helper.impl;

import lombok.extern.slf4j.Slf4j;
import org.fluffy.pet.rms.resourcemanagement.annotations.Helper;
import org.fluffy.pet.rms.resourcemanagement.dto.response.client.ClientResponse;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.helper.ClientHelper;
import org.fluffy.pet.rms.resourcemanagement.model.client.Client;
import org.fluffy.pet.rms.resourcemanagement.repository.ClientRepository;
import org.fluffy.pet.rms.resourcemanagement.transformer.ClientTransformer;
import org.fluffy.pet.rms.resourcemanagement.util.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;

import java.util.Optional;

@Helper
@Slf4j
public class ClientHelperImpl implements ClientHelper {
    private final ClientRepository clientRepository;

    private final ClientTransformer clientTransformer;

    @Autowired
    public ClientHelperImpl(ClientRepository clientRepository, ClientTransformer clientTransformer) {
        this.clientRepository = clientRepository;
        this.clientTransformer = clientTransformer;
    }

    @Override
    public Result<Void, ErrorCode> createUserEntityForIdOnly(String userId) {
        Client client = Client.builder().id(userId).status(Status.ACTIVE).build();
        try {
            Client createdClient = clientRepository.save(client);
            log.info("Successfully created empty client with id {}", createdClient.getId());
            return Result.success(null);
        } catch (DuplicateKeyException duplicateKeyException) {
            return Result.error(ErrorCode.DUPLICATE_USER);
        }
    }

    @Override
    public boolean checkUserEntityExists(String userEntityId) {
        return clientRepository.exists(userEntityId);
    }

    @Override
    public Result<ClientResponse, ErrorCode> getUserEntityById(String userId) {
        Optional<Client> optionalClient = clientRepository.findById(userId);
        if (optionalClient.isEmpty()) {
            return Result.error(ErrorCode.USER_NOT_FOUND);
        }
        return Result.success(clientTransformer.convertModelToResponse(optionalClient.get()));
    }
}
