package org.fluffy.pet.rms.resourcemanagement.model.common;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.DocumentType;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class IdentityDocument {
    @Field(MongoConstants.TYPE)
    private DocumentType type;

    @Field(MongoConstants.ID_NUMBER)
    private String idNumber;

    @Field(MongoConstants.DOCUMENT_FILE_NAME)
    private String documentFileName;
}
