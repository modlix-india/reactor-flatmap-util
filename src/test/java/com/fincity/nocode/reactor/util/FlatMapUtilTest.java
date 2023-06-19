package com.fincity.nocode.reactor.util;

import org.junit.jupiter.api.Test;

import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuples;

class FlatMapUtilTest {

	@Test
	void test() {

		Mono<Integer> number = FlatMapUtil.flatMapMono(

		        () -> first(),

		        f -> second(),

		        (f, s) -> third());

		StepVerifier.create(number)
		        .expectNext(3)
		        .verifyComplete();

		Mono<Integer> numbers = FlatMapUtil.flatMapMonoWithNull(

		        () -> first(),

		        f -> secondNull(),

		        (f, s) -> third(),

		        (f, s, t) -> fourth(),

		        (f, s, t, fo) -> fifth())
		        .switchIfEmpty(Mono.just(-1));

		StepVerifier.create(numbers)
		        .expectNext(5)
		        .verifyComplete();

		Mono<Tuple3<Integer, Integer, Integer>> numbers3 = FlatMapUtil.flatMapMonoConsolidate(

		        () -> first(),

		        f -> second(),

		        (f, s) -> third());

		StepVerifier.create(numbers3)
		        .expectNext(Tuples.of(1, 2, 3))
		        .verifyComplete();
	}

	Mono<Integer> first() {
		System.out.println("Here in 1");
		return Mono.just(1);
	}

	Mono<Integer> second() {
		System.out.println("Here in 2");
		return Mono.just(2);
	}

	Mono<Integer> secondNull() {
		System.out.println("Here in 2 null");
		return Mono.empty();
	}

	Mono<Integer> third() {
		System.out.println("Here in 3");
		return Mono.just(3);
	}

	Mono<Integer> fourth() {
		System.out.println("Here in 4");
		return Mono.just(4);
	}

	Mono<Integer> fifth() {
		System.out.println("Here in 5");
		return Mono.just(5);
	}
}
