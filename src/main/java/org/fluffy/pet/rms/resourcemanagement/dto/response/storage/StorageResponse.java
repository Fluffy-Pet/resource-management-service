package org.fluffy.pet.rms.resourcemanagement.dto.response.storage;

import lombok.*;

import java.net.URL;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class StorageResponse {
    private URL storageUrl;
}
