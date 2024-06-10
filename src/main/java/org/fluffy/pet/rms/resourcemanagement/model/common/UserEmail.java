package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class UserEmail {
    private String emailId;
}
