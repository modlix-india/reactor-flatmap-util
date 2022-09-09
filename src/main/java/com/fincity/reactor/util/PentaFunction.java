package com.fincity.reactor.util;

import java.util.Objects;
import java.util.function.Function;

@FunctionalInterface
public interface PentaFunction<T, U, V, W, X, R> {

	public R apply(T a, U b, V c, W d, X e);

	default <Y> PentaFunction<T, U, V, W, X, Y> andThen(Function<? super R, ? extends Y> after) {
		Objects.requireNonNull(after);
		return (T t, U u, V v, W w, X x) -> after.apply(apply(t, u, v, w, x));
	}
}