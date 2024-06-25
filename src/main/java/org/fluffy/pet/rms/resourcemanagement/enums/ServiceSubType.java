package org.fluffy.pet.rms.resourcemanagement.enums;

import java.util.List;

public enum ServiceSubType {
    GENERAL_CHECK_UP(ServiceType.CHECK_UP, List.of(ServiceProviderType.DOCTOR, ServiceProviderType.CLINIC)),
    SURGERY(ServiceType.CHECK_UP, List.of(ServiceProviderType.DOCTOR, ServiceProviderType.CLINIC)),
    STAY(ServiceType.HOSTEL, List.of(ServiceProviderType.SHELTER_HOME)),
    PAWS_GROOMING(ServiceType.GROOMING, List.of(ServiceProviderType.VOLUNTEER)),
    GENERAL_GROOMING(ServiceType.GROOMING, List.of(ServiceProviderType.VOLUNTEER)),
    ADOPTION(ServiceType.ADOPTION, List.of(ServiceProviderType.SHELTER_HOME)),
    WALKING(ServiceType.VOLUNTEER, List.of(ServiceProviderType.VOLUNTEER)),
    HOME_CARE(ServiceType.HANGOUT, List.of(ServiceProviderType.VOLUNTEER))
    ;
    private final ServiceType serviceType;

    private final List<ServiceProviderType> userType;

    ServiceSubType(ServiceType serviceType, List<ServiceProviderType> userType) {
        this.serviceType = serviceType;
        this.userType = userType;
    }
}
