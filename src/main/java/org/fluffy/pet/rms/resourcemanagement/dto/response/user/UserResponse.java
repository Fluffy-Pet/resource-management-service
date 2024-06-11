package org.fluffy.pet.rms.resourcemanagement.dto.response.user;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.EmailResponse;
import org.fluffy.pet.rms.resourcemanagement.dto.response.common.MobileResponse;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class UserResponse<T> {
    private T userData;

    private EmailResponse email;

    private MobileResponse mobile;

    private Boolean emailValid;

    private Boolean mobileValid;
}
