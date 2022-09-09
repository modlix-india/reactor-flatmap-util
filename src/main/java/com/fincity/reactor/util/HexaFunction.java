package com.fincity.reactor.util;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface HexaFunction<T, U, V, W, X, Y, R> {

	public R apply(T a, U b, V c, W d, X e, Y f);

	default <Z> HexaFunction<T, U, V, W, X, Y, Z> andThen(Function<? super R, ? extends Z> after) {
		Objects.requireNonNull(after);
		return (T t, U u, V v, W w, X x, Y y) -> after.apply(apply(t, u, v, w, x, y));
	}
}