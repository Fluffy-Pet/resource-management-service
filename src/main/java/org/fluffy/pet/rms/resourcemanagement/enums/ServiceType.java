package org.fluffy.pet.rms.resourcemanagement.enums;

import lombok.Getter;
import org.fluffy.pet.rms.resourcemanagement.exception.AppException;

import java.util.Arrays;
import java.util.Optional;

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

    public static ServiceType getServiceType(String serviceType) {
        Optional<ServiceType> first = Arrays.stream(ServiceType.values()).filter(type ->
                type.getServiceType().equals(serviceType)
        ).findFirst();
        if (first.isEmpty()) {
            throw new AppException(String.format("Unknown Service type %s", serviceType));
        }
        return first.get();
    }
}
