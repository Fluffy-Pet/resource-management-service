package org.fluffy.pet.rms.resourcemanagement.model;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.fluffy.pet.rms.resourcemanagement.enums.Status;
import org.fluffy.pet.rms.resourcemanagement.util.MongoConstants;
import org.springframework.data.annotation.*;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@SuperBuilder
@EqualsAndHashCode
public class BaseEntity {
    @Field(MongoConstants.ID)
    @Id
    private String id;

    @Field(MongoConstants.STATUS)
    private Status status;

    @Version
    @Field(MongoConstants.VERSION)
    private Long version;

    @CreatedDate
    @Field(MongoConstants.CREATED_AT)
    private Date createdAt;

    @LastModifiedDate
    @Field(MongoConstants.UPDATED_AT)
    private Date updatedAt;

    @Field(MongoConstants.CREATED_BY)
    @CreatedBy
    private String createdBy;

    @Field(MongoConstants.UPDATED_BY)
    @LastModifiedBy
    private String updatedBy;
}
