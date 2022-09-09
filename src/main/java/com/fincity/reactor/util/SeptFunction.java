package com.fincity.reactor.util;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface SeptFunction<T, U, V, W, X, Y, Z, R> {

	public R apply(T a, U b, V c, W d, X e, Y f, Z g);

	default <A> SeptFunction<T, U, V, W, X, Y, Z, A> andThen(Function<? super R, ? extends A> after) {
		Objects.requireNonNull(after);
		return (T t, U u, V v, W w, X x, Y y, Z z) -> after.apply(apply(t, u, v, w, x, y, z));
	}
}