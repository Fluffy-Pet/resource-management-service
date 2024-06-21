package org.fluffy.pet.rms.resourcemanagement.transformer;

import org.fluffy.pet.rms.resourcemanagement.annotations.Transformer;
import org.fluffy.pet.rms.resourcemanagement.dto.request.client.ClientRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.client.ClientResponse;
import org.fluffy.pet.rms.resourcemanagement.model.client.Client;
import org.fluffy.pet.rms.resourcemanagement.util.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URL;

@Transformer
public class ClientTransformer {
    private final CommonTransformer commonTransformer;

    @Autowired
    public ClientTransformer(CommonTransformer commonTransformer) {
        this.commonTransformer = commonTransformer;
    }

    public ClientResponse convertModelToResponse(Client client) {
        return ClientResponse
                .builder()
                .firstName(client.getFirstName())
                .lastName(client.getLastName())
                .profileUrl(
                        ObjectUtils.transformIfNotNull(
                                ObjectUtils.transformIfNotNull(
                                        client.getProfileImageFileName(),
                                        commonTransformer::convertFileNameToUrl
                                ),
                                URL::toString
                        )
                )
                .build();
    }

    public void updateClient(Client client, ClientRequest clientRequest) {
        client.setFirstName(clientRequest.getFirstName());
        client.setLastName(clientRequest.getLastName());
        client.setProfileImageFileName(clientRequest.getProfileImageFileName());
    }
}
