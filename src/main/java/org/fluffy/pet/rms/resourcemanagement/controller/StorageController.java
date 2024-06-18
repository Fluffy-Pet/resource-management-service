package org.fluffy.pet.rms.resourcemanagement.controller;

import manager.file.enums.ContentType;
import org.fluffy.pet.rms.resourcemanagement.dto.response.storage.StorageResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.fluffy.pet.rms.resourcemanagement.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/storages")
@RestController
public class StorageController {
    private final StorageService storageService;

    @Autowired
    public StorageController(StorageService storageService) {
        this.storageService = storageService;
    }

    @GetMapping("/{contentType}")
    public ResponseEntity<ResponseWrapper<StorageResponse>> getStorage(@PathVariable ContentType contentType) {
        StorageResponse uploadStorage = storageService.getUploadStorage(contentType);
        return ResponseEntity.status(HttpStatus.OK).body(ResponseWrapper.success(uploadStorage));
    }
}
