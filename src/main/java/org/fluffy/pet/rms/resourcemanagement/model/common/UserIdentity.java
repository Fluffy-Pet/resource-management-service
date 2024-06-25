package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UserIdentity {
    private String userId;

    private String profilePhotoFileName;

    private String firstName;

    private String lastName;
}
