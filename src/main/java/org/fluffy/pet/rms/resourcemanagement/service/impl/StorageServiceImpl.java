package org.fluffy.pet.rms.resourcemanagement.service.impl;

import manager.file.FileManager;
import manager.file.enums.ContentType;
import org.fluffy.pet.rms.resourcemanagement.dto.response.storage.StorageResponse;
import org.fluffy.pet.rms.resourcemanagement.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URL;

@Service
public class StorageServiceImpl implements StorageService {
    private final FileManager fileManager;

    @Autowired
    public StorageServiceImpl(FileManager fileManager) {
        this.fileManager = fileManager;
    }

    @Override
    public StorageResponse getUploadStorage(ContentType contentType) {
        URL url = fileManager.uploadFile(contentType);
        return StorageResponse.builder().storageUrl(url).build();
    }
}
