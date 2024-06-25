package org.fluffy.pet.rms.resourcemanagement.enums;

public enum ServiceProviderType {
    CLINIC(UserType.ADMIN),
    DOCTOR(UserType.DOCTOR),
    SHELTER_HOME(UserType.ADMIN),
    VOLUNTEER(UserType.VOLUNTEER)
    ;

    private final UserType userType;

    ServiceProviderType(UserType userType) {
        this.userType = userType;
    }
}
