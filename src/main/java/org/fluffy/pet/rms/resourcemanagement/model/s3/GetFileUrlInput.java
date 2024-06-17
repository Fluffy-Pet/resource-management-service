package org.fluffy.pet.rms.resourcemanagement.model.s3;

import lombok.*;

import java.time.Duration;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public final class GetFileUrlInput {
    private Duration duration;

    private String bucketName;

    private String fileName;
}
