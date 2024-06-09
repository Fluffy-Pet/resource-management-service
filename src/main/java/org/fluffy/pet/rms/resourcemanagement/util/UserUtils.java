package org.fluffy.pet.rms.resourcemanagement.util;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignInEmailPassword;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignInMobilePassword;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.input.SignupInput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignInOutput;
import org.fluffy.pet.rms.resourcemanagement.dto.internal.output.SignupOutput;
import org.fluffy.pet.rms.resourcemanagement.enums.ErrorCode;

import java.util.Optional;
import java.util.function.Function;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class UserUtils {
    public static <T, R> Optional<String> fetchUserIdForSignup(
            T request,
            R signupRequest,
            Function<T, SignupInput> requestTransformer,
            Function<SignupInput, Result<SignupOutput, ErrorCode>> signupInputFunction,
            Function<R, Result<SignInOutput, ErrorCode>> signInFunction
    ) {
        SignupInput signupInput = requestTransformer.apply(request);
        Result<SignupOutput, ErrorCode> result = ObjectUtils.transformIfNotNull(signupInput, signupInputFunction);
        if (result == null) {
            return Optional.empty();
        }
        if (result.isSuccess()) {
            return Optional.of(result.getData().userId());
        }
        return switch (result.getError()) {
            case DUPLICATE_USER -> switch (signupRequest) {
                case SignInEmailPassword ignored -> {
                    Result<SignInOutput, ErrorCode> signInResult = signInFunction.apply(signupRequest);
                    yield Optional.of(signInResult.getData().userId());
                }
                case SignInMobilePassword ignored -> {
                    Result<SignInOutput, ErrorCode> signInResult = signInFunction.apply(signupRequest);
                    yield Optional.of(signInResult.getData().userId());
                }
                default -> Optional.empty();
            };
            default -> Optional.empty();
        };
    }
}
