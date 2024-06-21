package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.client.ClientRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.client.ClientResponse;
import org.fluffy.pet.rms.resourcemanagement.model.client.Client;

@Transformer
public class ClientTransformer {
    public ClientResponse convertModelToResponse(Client client) {
        return ClientResponse
                .builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .profileUrl(client.getProfileImageFileName())
                .build();
    }

    public void updateClient(Client client, ClientRequest clientRequest) {
        client.setFirstName(clientRequest.getFirstName());
        client.setLastName(clientRequest.getLastName());
        client.setProfileImageFileName(clientRequest.getProfileUrl());
    }
}
