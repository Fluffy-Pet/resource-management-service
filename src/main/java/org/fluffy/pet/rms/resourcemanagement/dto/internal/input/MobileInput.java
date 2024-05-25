package org.fluffy.pet.rms.resourcemanagement.dto.internal.input;

public record MobileInput(
        String countryCode,
        String mobileNumber
) {
    @Override
    public String toString() {
        return String.format("%s-%s", countryCode, mobileNumber);
    }
}
