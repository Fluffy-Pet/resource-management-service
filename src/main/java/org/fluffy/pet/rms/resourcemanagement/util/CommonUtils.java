package org.fluffy.pet.rms.resourcemanagement.util;

import com.fasterxml.jackson.databind.json.JsonMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.fluffy.pet.rms.resourcemanagement.dto.response.wrapper.ResponseWrapper;
import org.springframework.http.HttpStatus;

import java.io.IOException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class CommonUtils {
    public static final Integer PAGE = 0;

    public static final Integer PAGE_SIZE = 10;

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
