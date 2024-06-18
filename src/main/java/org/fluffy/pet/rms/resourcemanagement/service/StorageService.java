package org.fluffy.pet.rms.resourcemanagement.service;

import manager.file.enums.ContentType;
import org.fluffy.pet.rms.resourcemanagement.dto.response.storage.StorageResponse;

public interface StorageService {
    StorageResponse getUploadStorage(ContentType contentType);
}
