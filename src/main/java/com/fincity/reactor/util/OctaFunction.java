package com.fincity.reactor.util;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface OctaFunction<T, U, V, W, X, Y, Z, A, R> {

	public R apply(T a, U b, V c, W d, X e, Y f, Z g, A h); // NOSONAR
	// Required more than 8 arguments

	default <B> OctaFunction<T, U, V, W, X, Y, Z, A, B> andThen(Function<? super R, ? extends B> after) {
		Objects.requireNonNull(after);
		return (T t, U u, V v, W w, X x, Y y, Z z, A a) -> after.apply(apply(t, u, v, w, x, y, z, a));
	}
}