package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.*;


@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class AssociatedClinic {
    String clinicIds;
}
