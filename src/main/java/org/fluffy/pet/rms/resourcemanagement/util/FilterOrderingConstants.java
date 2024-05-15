package org.fluffy.pet.rms.resourcemanagement.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.core.Ordered;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class FilterOrderingConstants {
    public static final int CORS_FILTER_ORDER = Ordered.HIGHEST_PRECEDENCE;

    public static final int REQUEST_METADATA_INITIALIZER_FILTER_ORDER = Ordered.HIGHEST_PRECEDENCE + 1;

    public static final int HTTP_LOGGING_FILTER_ORDER = Ordered.HIGHEST_PRECEDENCE + 2;

    public static final String REQUEST_ID = "requestId";
}
