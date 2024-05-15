package org.fluffy.pet.rms.resourcemanagement.dto.internal.input;

public record SignInEmailPassword(
        EmailInput email,
        String password
) {
}
