package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor
@Builder
public class AssociatedClinic {
    List<String> clinicIds;
}
