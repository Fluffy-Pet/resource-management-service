package org.fluffy.pet.rms.resourcemanagement.dto.request.user;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.dto.request.common.EmailRequest;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class UpdateEmailRequest {
    private EmailRequest email;
}
