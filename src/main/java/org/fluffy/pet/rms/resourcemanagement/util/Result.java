package org.fluffy.pet.rms.resourcemanagement.util;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.util.function.Function;
import java.util.function.Supplier;

@Getter
@Builder(access = AccessLevel.PRIVATE)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
public class Result<S, T> {
    private S data;

    private T error;

    public static <S, T> Result<S, T> success(S success) {
        return Result.<S, T>builder().data(success).build();
    }

    public static <S, T> Result<S, T> error(T error) {
        return Result.<S, T>builder().error(error).build();
    }

    public boolean isSuccess() {
        return !isFailure();
    }

    public boolean isFailure() {
        return this.getError() != null;
    }

    public S orElse(S other) {
        return this.isFailure() ? other : this.getData();
    }

    public S orElseGet(Supplier<? extends S> supplier) {
        return this.isFailure() ? supplier.get() : this.getData();
    }

    public S orElseRecover(Function<T, ? extends S> recoverFunction) {
        return this.isFailure() ? recoverFunction.apply(this.getError()) : this.getData();
    }

    public <X extends Throwable> S orElseThrow(Function<T, ? extends X> exceptionFunction) throws X {
        if (this.isFailure()) {
            throw exceptionFunction.apply(this.getError());
        }
        return this.getData();
    }
}
