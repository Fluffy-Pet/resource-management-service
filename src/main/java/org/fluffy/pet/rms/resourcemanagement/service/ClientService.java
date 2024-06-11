package org.fluffy.pet.rms.resourcemanagement.service;

import org.fluffy.pet.rms.resourcemanagement.dto.request.client.ClientRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.client.ClientResponse;

public interface ClientService {
    ClientResponse getCurrentClient();

    ClientResponse updateCurrentClient(ClientRequest clientRequest);
}
