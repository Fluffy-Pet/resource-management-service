package org.fluffy.pet.rms.resourcemanagement.helper;

import org.fluffy.pet.rms.resourcemanagement.model.s3.GetFileUrlInput;

import java.net.URL;

public interface StorageHelper {
    URL generateSignedUrlForDownload(GetFileUrlInput request);
}