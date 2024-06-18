package manager.file.impl;

import manager.file.FileManager;
import manager.file.configurations.S3Configuration;
import manager.file.enums.ContentType;
import manager.file.utils.S3Utils;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.S3Presigner;
import software.amazon.awssdk.services.s3.presigner.model.GetObjectPresignRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedGetObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PresignedPutObjectRequest;
import software.amazon.awssdk.services.s3.presigner.model.PutObjectPresignRequest;

import java.net.URL;

public class S3FileManager implements FileManager {
    private final S3Presigner s3Presigner;

    private final S3Configuration s3Configuration;

    public S3FileManager(S3Presigner s3Presigner, S3Configuration s3Configuration) {
        this.s3Presigner = s3Presigner;
        this.s3Configuration = s3Configuration;
    }

    @Override
    public URL uploadFile(ContentType contentTpe, String filename) {
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
                .bucket(s3Configuration.getBucketName())
                .contentType(S3Utils.getContentType(contentTpe))
                .key(filename)
                .build();

        PutObjectPresignRequest putObjectPresignRequest = PutObjectPresignRequest.builder()
                .signatureDuration(s3Configuration.getAccessDuration())
                .putObjectRequest(putObjectRequest)
                .build();

        PresignedPutObjectRequest presignedPutObjectRequest = s3Presigner.presignPutObject(putObjectPresignRequest);

        return presignedPutObjectRequest.url();
    }

    @Override
    public URL getFile(String fileName) {
        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
                .bucket(s3Configuration.getBucketName())
                .key(fileName)
                .build();

        GetObjectPresignRequest getObjectPresignRequest = GetObjectPresignRequest.builder()
                .getObjectRequest(getObjectRequest)
                .signatureDuration(s3Configuration.getAccessDuration())
                .build();

        PresignedGetObjectRequest presignedGetObjectRequest = s3Presigner.presignGetObject(getObjectPresignRequest);

        return presignedGetObjectRequest.url();
    }
}
