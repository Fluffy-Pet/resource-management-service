package org.fluffy.pet.rms.resourcemanagement.dto.internal.input;

public record SignupInput(
        EmailInput email,
        MobileInput mobileInput,
        String password
) {
}
