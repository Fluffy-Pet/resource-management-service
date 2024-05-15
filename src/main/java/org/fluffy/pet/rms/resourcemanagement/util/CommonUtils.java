package org.fluffy.pet.rms.resourcemanagement.util;

import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonUtils {
    public static final Integer PAGE = 0;

    public static final Integer PAGE_SIZE = 50;

    public static final Integer MONTH_DURATION_MAXIMUM_VALUE = 12;

    public static final Integer MONTH_DURATION_MINIMUM_VALUE = 12;

    public static final String MONTH_DURATION_MORE_THAN_PERMITTED_VALUE = "Month Duration must be less than or equal to %s";

    public static final String MONTH_DURATION_LESS_THAN_PERMITTED_VALUE = "Month Duration must be more than %s";

    public static final List<Float> CGST_RATE = List.of(0F, 2.5F, 6F, 9F, 14F);

    public static final List<Float> SGST_RATE = List.of(0F, 2.5F, 6F, 9F, 14F);

    public static final List<Float> IGST_RATE = List.of(0F, 5F, 12F, 18F, 28F);

    public static final String UN_EQUAL_CGST_AND_SGST_RATE = "CGST and SGST rate must be equal";

    public static final String LATE_FEE_PERCENTAGE_LESS_THAN_ZERO = "Late Fee Percentage must be greater than or equal to 0";

    public static final String LATE_FEE_PERCENTAGE_GREATER_THAN_HUNDRED = "Late Fee Percentage must be less than or equal to 100";

    public static final String NON_NULL_CGST_OR_SGST_RATE = "CGST and SGST must be null for IGST rate to be not null";

    public static final String INCORRECT_OBJECT_TYPE_FOR_WEEKLY_SCHEDULE = "Weekly schedule must have instance of RecurringWeeklyClassSchedulePatternRequest";

    public static final String INCORRECT_OBJECT_TYPE_FOR_MONTHLY_SCHEDULE = "Monthly schedule must have instance of RecurringMonthlyClassSchedulePatternRequest";

    public static <T> void writeResponse(
            HttpServletResponse response,
            HttpStatus httpStatus,
            ResponseWrapper<T> responseWrapper,
            JsonMapper jsonMapper
    ) throws IOException {
        response.setHeader(HeaderConstants.CONTENT_TYPE, HeaderConstants.APPLICATION_JSON);
        response.setStatus(httpStatus.value());
        response.getWriter().println(jsonMapper.writeValueAsString(responseWrapper));
    }

    public static boolean isBearerToken(String token) {
        return token.startsWith(HeaderConstants.BEARER_SPACE);
    }
}
