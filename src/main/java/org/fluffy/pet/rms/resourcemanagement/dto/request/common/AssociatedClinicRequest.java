package org.fluffy.pet.rms.resourcemanagement.dto.request.common;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class AssociatedClinicRequest {
    private List<String> clinicIds;
}
