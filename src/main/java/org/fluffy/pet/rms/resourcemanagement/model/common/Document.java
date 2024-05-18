package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.enums.DocumentType;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@SuperBuilder
public class Document {
    @Field("type")
    private DocumentType type;

    @Field("id_number")
    private String idNumber;
}
