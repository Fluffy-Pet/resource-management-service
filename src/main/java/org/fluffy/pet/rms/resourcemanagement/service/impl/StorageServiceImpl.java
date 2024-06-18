package org.fluffy.pet.rms.resourcemanagement.service.impl;

import manager.file.FileManager;
import manager.file.enums.ContentType;
import org.fluffy.pet.rms.resourcemanagement.dto.response.storage.StorageResponse;
import org.fluffy.pet.rms.resourcemanagement.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;
import java.util.UUID;

@Service
public class StorageServiceImpl implements StorageService {
    private final FileManager fileManager;

    @Autowired
    public StorageServiceImpl(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public StorageResponse getUploadStorage(ContentType contentType) {
        String fileName = UUID.randomUUID().toString();
        URL url = fileManager.uploadFile(contentType, fileName);
        return StorageResponse.builder().storageUrl(url).fileName(fileName).build();
    }
}
