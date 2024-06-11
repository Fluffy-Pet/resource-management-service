package org.fluffy.pet.rms.resourcemanagement.controller;

import jakarta.validation.Valid;
import org.fluffy.pet.rms.resourcemanagement.annotations.CheckAccess;
import org.fluffy.pet.rms.resourcemanagement.dto.request.client.ClientRequest;
import org.fluffy.pet.rms.resourcemanagement.dto.response.client.ClientResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;
import org.fluffy.pet.rms.resourcemanagement.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/clients")
@RestController
public class ClientController {
    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<ResponseWrapper<ClientResponse>> getCurrentAdminUser() {
        ClientResponse currentClient = clientService.getCurrentClient();
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(currentClient));
    }

    @PutMapping
    @CheckAccess(values = {UserType.CLIENT})
    public ResponseEntity<ResponseWrapper<ClientResponse>> updateCurrentAdmin(
            @RequestBody @Valid ClientRequest clientRequest
    ) {
        ClientResponse currentClient = clientService.updateCurrentClient(clientRequest);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(currentClient));
    }
}
