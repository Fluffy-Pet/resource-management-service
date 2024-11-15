package org.fluffy.pet.rms.resourcemanagement.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class Constants {
    public static final String ERROR_CODE_PREFIX = "RM-E";

    public static final String RESPONSE_TIMESTAMP_FORMAT = "yyyy-MM-dd'T'HH:mm:ss.SSS'Z'";

    public static final String DATE_FORMAT = "yyyy-MM-dd";

    public static final String TIME_FORMAT = "HH:mm:ss";

    public static final String BEARER_AUTH = "bearerAuth";

    public static final String OPERATOR = "operator";

    public static final String FILTER_SEPARATOR = "\\.";

    public static final String INVALID_FIELD = "Invalid path `%s`";
}
