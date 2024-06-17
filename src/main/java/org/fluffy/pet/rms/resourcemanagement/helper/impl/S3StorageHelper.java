package org.fluffy.pet.rms.resourcemanagement.helper.impl;

import org.fluffy.pet.rms.resourcemanagement.annotations.Helper;
import org.fluffy.pet.rms.resourcemanagement.helper.StorageHelper;
import org.fluffy.pet.rms.resourcemanagement.model.s3.GetFileUrlInput;
import org.springframework.beans.factory.annotation.Autowired;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;

import java.net.URL;

@Helper
public class S3StorageHelper implements StorageHelper {
    private final S3Presigner s3Presigner;

    @Autowired
    public S3StorageHelper(S3Presigner s3Presigner) {
        this.s3Presigner = s3Presigner;
    }

//    @Override
//    public URL generateSignedUrlForUpload(UploadFileInput request) {
//        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
//                .bucket(request.getBucketName())
//                .key(request.getFileName())
//                .build();
//
//        PutObjectPresignRequest putObjectPresignRequest = PutObjectPresignRequest.builder()
//                .putObjectRequest(putObjectRequest)
//                .signatureDuration(request.getDuration())
//                .build();
//
//        PresignedPutObjectRequest presignedPutObjectRequest = s3Presigner.presignPutObject(putObjectPresignRequest);
//
//        return presignedPutObjectRequest.url();
//    }

    @Override
    public URL generateSignedUrlForDownload(GetFileUrlInput request) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(request.getBucketName())
                .key(request.getFileName())
                .build();

        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                .getObjectRequest(getObjectRequest)
                .signatureDuration(request.getDuration())
                .build();

        PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner.presignGetObject(getObjectPresignRequest);

        return presignedGetObjectRequest.url();
    }
}
