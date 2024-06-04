package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
public class UserMobile {
    private String countryCode;

    private String mobileNumber;

}
