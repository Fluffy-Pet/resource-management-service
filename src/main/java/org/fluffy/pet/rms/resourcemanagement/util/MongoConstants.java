package org.fluffy.pet.rms.resourcemanagement.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class MongoConstants {
    public static final String ID = "_id";

    public static final String STATUS = "_status";

    public static final String VERSION = "_version";

    public static final String CREATED_AT = "_createdAt";

    public static final String UPDATED_AT = "_updatedAt";

    public static final String CREATED_BY = "_createdBy";

    public static final String UPDATED_BY = "_updatedBy";

    public static final String INFRASTRUCTURE_TABLE = "infrastructure";
}
