package manager.file.configurations;

import lombok.*;

import java.time.Duration;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@Builder
public class S3Configuration {
    private String bucketName;

    private Duration accessDuration;
}
