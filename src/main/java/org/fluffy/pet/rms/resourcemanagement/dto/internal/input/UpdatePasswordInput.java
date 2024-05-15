package org.fluffy.pet.rms.resourcemanagement.dto.internal.input;

public record UpdatePasswordInput(
        String userId,
        String newPassword
) {
}
