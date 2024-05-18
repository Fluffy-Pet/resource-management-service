package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.model.infrastructure.Clinic;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.List;

@Getter
@Setter
@SuperBuilder
public class Information {
    @Field("documents")
    private List<Document> documents;

    @Field("associated_clinics")
    private List<Clinic> associatedClinics; // List of Clinic objects

    @Field("address")
    private Address address; // Address object
}
