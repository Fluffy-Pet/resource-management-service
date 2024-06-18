package manager.file.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import manager.file.enums.ContentType;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class S3Utils {
    public static String getContentType(ContentType contentType) {
        return switch (contentType) {
            case JPEG -> "image/jpeg";
            case PNG -> "image/png";
            case PDF -> "application/pdf";
        };
    }
}
