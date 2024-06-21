package org.fluffy.pet.rms.resourcemanagement.service;

import com.fasterxml.jackson.databind.JsonNode;
import org.fluffy.pet.rms.resourcemanagement.dto.request.client.ClientRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.request.filter.FilterRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.client.ClientResponse;
import org.fluffy.pet.rms.resourcemanagement.util.PaginationWrapper;

import java.util.List;

public interface ClientService {
    ClientResponse getCurrentClient();

    ClientResponse updateCurrentClient(ClientRequest clientRequest);

    PaginationWrapper<List<JsonNode>> filterClients(FilterRequest filterRequest);
}
