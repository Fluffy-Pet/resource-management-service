package org.fluffy.pet.rms.resourcemanagement.configuration.contexts;

import lombok.Getter;
import lombok.Setter;
import org.fluffy.pet.rms.resourcemanagement.enums.UserType;
import org.springframework.web.context.annotation.RequestScope;

@RequestScope
@Getter
@Setter
public class UserContext {
    private String userId;

    private UserType userType;
}
