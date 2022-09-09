package com.fincity.reactor.util;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface NanoFunction<T, U, V, W, X, Y, Z, A, B, R> {

	public R apply(T a, U b, V c, W d, X e, Y f, Z g, A h, B i); // NOSONAR
	// Required more than 8 arguments

	default <C> NanoFunction<T, U, V, W, X, Y, Z, A, B, C> andThen(Function<? super R, ? extends C> after) {
		Objects.requireNonNull(after);
		return (T t, U u, V v, W w, X x, Y y, Z z, A a, B b) -> after.apply(apply(t, u, v, w, x, y, z, a, b));
	}
}