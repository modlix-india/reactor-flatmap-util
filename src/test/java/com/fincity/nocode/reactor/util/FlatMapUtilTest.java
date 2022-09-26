package com.fincity.nocode.reactor.util;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

class FlatMapUtilTest {

	@Test
	void test() {

		Mono<Integer> number = FlatMapUtil.flatMapMonoLog(

		        () -> first(),

		        f -> second(),

		        (f, s) -> third());

		StepVerifier.create(number)
		        .expectNext(3)
		        .verifyComplete();

		Mono<Integer> numbers = FlatMapUtil.flatMapMonoWithNullLog(

		        () -> first(),

		        f -> secondNull(),

		        (f, s) -> third());

		StepVerifier.create(numbers)
		        .expectNext(3)
		        .verifyComplete();

		Mono<Tuple3<Integer, Integer, Integer>> numbers3 = FlatMapUtil.flatMapConsolidateLog(

		        () -> first(),

		        f -> second(),

		        (f, s) -> third());
		
		StepVerifier.create(numbers3)
			.expectNext(Tuples.of(1, 2, 3))
			.verifyComplete();
	}

	Mono<Integer> first() {
		return Mono.just(1);
	}

	Mono<Integer> second() {
		return Mono.just(2);
	}

	Mono<Integer> secondNull() {
		return Mono.empty();
	}

	Mono<Integer> third() {
		return Mono.just(3);
	}
}
