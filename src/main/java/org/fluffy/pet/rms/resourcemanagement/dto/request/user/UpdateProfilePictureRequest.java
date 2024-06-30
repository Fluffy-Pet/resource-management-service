package org.fluffy.pet.rms.resourcemanagement.dto.request.user;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class UpdateProfilePictureRequest {
    private String profileImageFileName;
}
