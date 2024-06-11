package manager.authentication.models;

import lombok.*;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class JwtPayload {
    private String sub;

    private UserType userType;
}
