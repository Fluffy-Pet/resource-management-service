package org.fluffy.pet.rms.resourcemanagement.enums;

import lombok.Getter;

@Getter
public enum ServiceType {
    CHECK_UP("Vets"),
    HOSTEL("Hostel"),
    GROOMING("Groomer"),
    ADOPTION("Adoption"),
    VOLUNTEER("Volunteer"),
    HANGOUT("Hangouts")
    ;

    private final String serviceType;

    ServiceType(String serviceType) {
        this.serviceType = serviceType;
    }
}
