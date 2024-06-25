package org.fluffy.pet.rms.resourcemanagement.model.booking;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UserInfo {
    private String userId;

    private String petId;
}
