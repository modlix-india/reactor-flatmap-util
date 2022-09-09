package com.fincity.nocode.reactor.util;

import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple10;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuple3;
import reactor.util.function.Tuple4;
import reactor.util.function.Tuple5;
import reactor.util.function.Tuple6;
import reactor.util.function.Tuple7;
import reactor.util.function.Tuple8;
import reactor.util.function.Tuple9;
import reactor.util.function.Tuples;

public class FlatMapUtil {

	public static <F, S> Mono<S> flatMapMono(Supplier<Mono<F>> fMono, Function<F, Mono<S>> sMono) {

		return fMono.get()
		        .flatMap(sMono::apply);
	}

	public static <F, S> Mono<Tuple2<F, S>> flatMapConsolidate(Supplier<Mono<F>> fMono, Function<F, Mono<S>> sMono) {

		return fMono.get()
		        .flatMap(f ->
				{
			        Mono<S> ms = sMono.apply(f);
			        return ms.map(s -> Tuples.of(f, s));
		        });
	}

	public static <F, S, T> Mono<T> flatMapMono(Supplier<Mono<F>> fMono, Function<F, Mono<S>> sMono,
	        BiFunction<F, S, Mono<T>> tMono) {

		return fMono.get()
		        .flatMap(f ->
				{
			        Mono<S> ms = sMono.apply(f);
			        return ms.flatMap(s -> tMono.apply(f, s));
		        });

	}

	public static <F, S, T> Mono<Tuple3<F, S, T>> flatMapConsolidate(Supplier<Mono<F>> fMono,
	        Function<F, Mono<S>> sMono, BiFunction<F, S, Mono<T>> tMono) {

		return fMono.get()
		        .flatMap(f ->
				{
			        Mono<S> ms = sMono.apply(f);
			        return ms.flatMap(s -> {

				        Mono<T> mt = tMono.apply(f, s);
				        return mt.map(t -> Tuples.of(f, s, t));
			        });
		        });

	}

	public static <F, S, T, Q> Mono<Q> flatMapMono(Supplier<Mono<F>> fMono, Function<F, Mono<S>> sMono,
	        BiFunction<F, S, Mono<T>> tMono, TriFunction<F, S, T, Mono<Q>> qMono) {

		return fMono.get()
		        .flatMap(f ->
				{
			        Mono<S> ms = sMono.apply(f);
			        return ms.flatMap(s -> {

				        Mono<T> mt = tMono.apply(f, s);
				        return mt.flatMap(t -> qMono.apply(f, s, t));
			        });
		        });

	}

	public static <F, S, T, Q> Mono<Tuple4<F, S, T, Q>> flatMapConsolidate(Supplier<Mono<F>> fMono,
	        Function<F, Mono<S>> sMono, BiFunction<F, S, Mono<T>> tMono, TriFunction<F, S, T, Mono<Q>> qMono) {

		return fMono.get()
		        .flatMap(f ->
				{
			        Mono<S> ms = sMono.apply(f);
			        return ms.flatMap(s -> {

				        Mono<T> mt = tMono.apply(f, s);
				        return mt.flatMap(t -> {

					        Mono<Q> mq = qMono.apply(f, s, t);
					        return mq.map(q -> Tuples.of(f, s, t, q));
				        });
			        });
		        });

	}

	public static <F, S, T, Q, P> Mono<P> flatMapMono(Supplier<Mono<F>> fMono, Function<F, Mono<S>> sMono,
	        BiFunction<F, S, Mono<T>> tMono, TriFunction<F, S, T, Mono<Q>> qMono,
	        QuadFunction<F, S, T, Q, Mono<P>> pMono) {

		return fMono.get()
		        .flatMap(f ->
				{
			        Mono<S> ms = sMono.apply(f);
			        return ms.flatMap(s -> {

				        Mono<T> mt = tMono.apply(f, s);
				        return mt.flatMap(t -> {

					        Mono<Q> mq = qMono.apply(f, s, t);
					        return mq.flatMap(q -> pMono.apply(f, s, t, q));
				        });
			        });
		        });

	}

	public static <F, S, T, Q, P> Mono<Tuple5<F, S, T, Q, P>> flatMapConsolidate(Supplier<Mono<F>> fMono,
	        Function<F, Mono<S>> sMono, BiFunction<F, S, Mono<T>> tMono, TriFunction<F, S, T, Mono<Q>> qMono,
	        QuadFunction<F, S, T, Q, Mono<P>> pMono) {

		return fMono.get()
		        .flatMap(f ->
				{
			        Mono<S> ms = sMono.apply(f);
			        return ms.flatMap(s -> {

				        Mono<T> mt = tMono.apply(f, s);
				        return mt.flatMap(t -> {

					        Mono<Q> mq = qMono.apply(f, s, t);
					        return mq.flatMap(q -> {

						        Mono<P> mp = pMono.apply(f, s, t, q);
						        return mp.map(p -> Tuples.of(f, s, t, q, p));
					        });
				        });
			        });
		        });

	}

	public static <F, S, T, Q, P, H> Mono<H> flatMapMono(Supplier<Mono<F>> fMono, Function<F, Mono<S>> sMono,
	        BiFunction<F, S, Mono<T>> tMono, TriFunction<F, S, T, Mono<Q>> qMono,
	        QuadFunction<F, S, T, Q, Mono<P>> pMono, PentaFunction<F, S, T, Q, P, Mono<H>> hMono) {

		return fMono.get()
		        .flatMap(f ->
				{
			        Mono<S> ms = sMono.apply(f);
			        return ms.flatMap(s -> {

				        Mono<T> mt = tMono.apply(f, s);
				        return mt.flatMap(t -> {

					        Mono<Q> mq = qMono.apply(f, s, t);
					        return mq.flatMap(q -> {

						        Mono<P> mp = pMono.apply(f, s, t, q);
						        return mp.flatMap(p -> hMono.apply(f, s, t, q, p));
					        });
				        });
			        });
		        });

	}

	public static <F, S, T, Q, P, H> Mono<Tuple6<F, S, T, Q, P, H>> flatMapConsolidate(Supplier<Mono<F>> fMono,
	        Function<F, Mono<S>> sMono, BiFunction<F, S, Mono<T>> tMono, TriFunction<F, S, T, Mono<Q>> qMono,
	        QuadFunction<F, S, T, Q, Mono<P>> pMono, PentaFunction<F, S, T, Q, P, Mono<H>> hMono) {

		return fMono.get()
		        .flatMap(f ->
				{
			        Mono<S> ms = sMono.apply(f);
			        return ms.flatMap(s -> {

				        Mono<T> mt = tMono.apply(f, s);
				        return mt.flatMap(t -> {

					        Mono<Q> mq = qMono.apply(f, s, t);
					        return mq.flatMap(q -> {

						        Mono<P> mp = pMono.apply(f, s, t, q);
						        return mp.flatMap(p -> {

							        Mono<H> mh = hMono.apply(f, s, t, q, p);
							        return mh.map(h -> Tuples.of(f, s, t, q, p, h));
						        });
					        });
				        });
			        });
		        });

	}

	public static <F, S, T, Q, P, H, E> Mono<E> flatMapMono(Supplier<Mono<F>> fMono, Function<F, Mono<S>> sMono,
	        BiFunction<F, S, Mono<T>> tMono, TriFunction<F, S, T, Mono<Q>> qMono,
	        QuadFunction<F, S, T, Q, Mono<P>> pMono, PentaFunction<F, S, T, Q, P, Mono<H>> hMono,
	        HexaFunction<F, S, T, Q, P, H, Mono<E>> seMono) {

		return fMono.get()
		        .flatMap(f ->
				{
			        Mono<S> ms = sMono.apply(f);
			        return ms.flatMap(s -> {

				        Mono<T> mt = tMono.apply(f, s);
				        return mt.flatMap(t -> {

					        Mono<Q> mq = qMono.apply(f, s, t);
					        return mq.flatMap(q -> {

						        Mono<P> mp = pMono.apply(f, s, t, q);
						        return mp.flatMap(p -> {

							        Mono<H> mh = hMono.apply(f, s, t, q, p);
							        return mh.flatMap(h -> seMono.apply(f, s, t, q, p, h));
						        });
					        });
				        });
			        });
		        });

	}

	public static <F, S, T, Q, P, H, E> Mono<Tuple7<F, S, T, Q, P, H, E>> flatMapConsolidate(Supplier<Mono<F>> fMono,
	        Function<F, Mono<S>> sMono, BiFunction<F, S, Mono<T>> tMono, TriFunction<F, S, T, Mono<Q>> qMono,
	        QuadFunction<F, S, T, Q, Mono<P>> pMono, PentaFunction<F, S, T, Q, P, Mono<H>> hMono,
	        HexaFunction<F, S, T, Q, P, H, Mono<E>> seMono) {

		return fMono.get()
		        .flatMap(f ->
				{
			        Mono<S> ms = sMono.apply(f);
			        return ms.flatMap(s -> {

				        Mono<T> mt = tMono.apply(f, s);
				        return mt.flatMap(t -> {

					        Mono<Q> mq = qMono.apply(f, s, t);
					        return mq.flatMap(q -> {

						        Mono<P> mp = pMono.apply(f, s, t, q);
						        return mp.flatMap(p -> {

							        Mono<H> mh = hMono.apply(f, s, t, q, p);
							        return mh.flatMap(h -> {

								        Mono<E> mSe = seMono.apply(f, s, t, q, p, h);
								        return mSe.map(se -> Tuples.of(f, s, t, q, p, h, se));
							        });
						        });
					        });
				        });
			        });
		        });

	}

	public static <F, S, T, Q, P, H, E, O> Mono<O> flatMapMono(Supplier<Mono<F>> fMono, Function<F, Mono<S>> sMono, // NOSONAR
	        BiFunction<F, S, Mono<T>> tMono, TriFunction<F, S, T, Mono<Q>> qMono,
	        QuadFunction<F, S, T, Q, Mono<P>> pMono, PentaFunction<F, S, T, Q, P, Mono<H>> hMono,
	        HexaFunction<F, S, T, Q, P, H, Mono<E>> seMono, SeptFunction<F, S, T, Q, P, H, E, Mono<O>> oMono) {
		// Required more than 8 arguments

		return fMono.get()
		        .flatMap(f ->
				{
			        Mono<S> ms = sMono.apply(f);
			        return ms.flatMap(s -> {

				        Mono<T> mt = tMono.apply(f, s);
				        return mt.flatMap(t -> {

					        Mono<Q> mq = qMono.apply(f, s, t);
					        return mq.flatMap(q -> {

						        Mono<P> mp = pMono.apply(f, s, t, q);
						        return mp.flatMap(p -> {

							        Mono<H> mh = hMono.apply(f, s, t, q, p);
							        return mh.flatMap(h -> {

								        Mono<E> mSe = seMono.apply(f, s, t, q, p, h);
								        return mSe.flatMap(se -> oMono.apply(f, s, t, q, p, h, se));
							        });
						        });
					        });
				        });
			        });
		        });

	}

	public static <F, S, T, Q, P, H, E, O> Mono<Tuple8<F, S, T, Q, P, H, E, O>> flatMapConsolidate( // NOSONAR
	        Supplier<Mono<F>> fMono, Function<F, Mono<S>> sMono, BiFunction<F, S, Mono<T>> tMono,
	        TriFunction<F, S, T, Mono<Q>> qMono, QuadFunction<F, S, T, Q, Mono<P>> pMono,
	        PentaFunction<F, S, T, Q, P, Mono<H>> hMono, HexaFunction<F, S, T, Q, P, H, Mono<E>> seMono,
	        SeptFunction<F, S, T, Q, P, H, E, Mono<O>> oMono) {
		// Required more than 8 arguments

		return fMono.get()
		        .flatMap(f ->
				{
			        Mono<S> ms = sMono.apply(f);
			        return ms.flatMap(s -> {

				        Mono<T> mt = tMono.apply(f, s);
				        return mt.flatMap(t -> {

					        Mono<Q> mq = qMono.apply(f, s, t);
					        return mq.flatMap(q -> {

						        Mono<P> mp = pMono.apply(f, s, t, q);
						        return mp.flatMap(p -> {

							        Mono<H> mh = hMono.apply(f, s, t, q, p);
							        return mh.flatMap(h -> {

								        Mono<E> mSe = seMono.apply(f, s, t, q, p, h);
								        return mSe.flatMap(se -> {

									        Mono<O> mo = oMono.apply(f, s, t, q, p, h, se);
									        return mo.map(o -> Tuples.of(f, s, t, q, p, h, se, o));
								        });
							        });
						        });
					        });
				        });
			        });
		        });

	}

	public static <F, S, T, Q, P, H, E, O, N> Mono<N> flatMapMono(Supplier<Mono<F>> fMono, Function<F, Mono<S>> sMono, // NOSONAR
	        BiFunction<F, S, Mono<T>> tMono, TriFunction<F, S, T, Mono<Q>> qMono,
	        QuadFunction<F, S, T, Q, Mono<P>> pMono, PentaFunction<F, S, T, Q, P, Mono<H>> hMono,
	        HexaFunction<F, S, T, Q, P, H, Mono<E>> seMono, SeptFunction<F, S, T, Q, P, H, E, Mono<O>> oMono,
	        OctaFunction<F, S, T, Q, P, H, E, O, Mono<N>> nMono) {
		// Required more than 8 arguments

		return fMono.get()
		        .flatMap(f ->
				{
			        Mono<S> ms = sMono.apply(f);
			        return ms.flatMap(s -> {

				        Mono<T> mt = tMono.apply(f, s);
				        return mt.flatMap(t -> {

					        Mono<Q> mq = qMono.apply(f, s, t);
					        return mq.flatMap(q -> {

						        Mono<P> mp = pMono.apply(f, s, t, q);
						        return mp.flatMap(p -> {

							        Mono<H> mh = hMono.apply(f, s, t, q, p);
							        return mh.flatMap(h -> {

								        Mono<E> mSe = seMono.apply(f, s, t, q, p, h);
								        return mSe.flatMap(se -> {

									        Mono<O> mo = oMono.apply(f, s, t, q, p, h, se);
									        return mo.flatMap(o -> nMono.apply(f, s, t, q, p, h, se, o));
								        });
							        });
						        });
					        });
				        });
			        });
		        });

	}

	public static <F, S, T, Q, P, H, E, O, N> Mono<Tuple9<F, S, T, Q, P, H, E, O, N>> flatMapConsolidate( // NOSONAR
	        Supplier<Mono<F>> fMono, Function<F, Mono<S>> sMono,
	        BiFunction<F, S, Mono<T>> tMono, TriFunction<F, S, T, Mono<Q>> qMono,
	        QuadFunction<F, S, T, Q, Mono<P>> pMono, PentaFunction<F, S, T, Q, P, Mono<H>> hMono,
	        HexaFunction<F, S, T, Q, P, H, Mono<E>> seMono, SeptFunction<F, S, T, Q, P, H, E, Mono<O>> oMono,
	        OctaFunction<F, S, T, Q, P, H, E, O, Mono<N>> nMono) {
		// Required more than 8 arguments

		return fMono.get()
		        .flatMap(f ->
				{
			        Mono<S> ms = sMono.apply(f);
			        return ms.flatMap(s -> {

				        Mono<T> mt = tMono.apply(f, s);
				        return mt.flatMap(t -> {

					        Mono<Q> mq = qMono.apply(f, s, t);
					        return mq.flatMap(q -> {

						        Mono<P> mp = pMono.apply(f, s, t, q);
						        return mp.flatMap(p -> {

							        Mono<H> mh = hMono.apply(f, s, t, q, p);
							        return mh.flatMap(h -> {

								        Mono<E> mSe = seMono.apply(f, s, t, q, p, h);
								        return mSe.flatMap(se -> {

									        Mono<O> mo = oMono.apply(f, s, t, q, p, h, se);
									        return mo.flatMap(o -> {

										        Mono<N> mn = nMono.apply(f, s, t, q, p, h, se, o);
										        return mn.map(n -> new Tuple9<>(f, s, t, q, p, h, se, o, n));
									        });
								        });
							        });
						        });
					        });
				        });
			        });
		        });

	}

	public static <F, S, T, Q, P, H, E, O, N, D> Mono<D> flatMapMono(Supplier<Mono<F>> fMono, Function<F, Mono<S>> sMono, // NOSONAR
	        BiFunction<F, S, Mono<T>> tMono, TriFunction<F, S, T, Mono<Q>> qMono,
	        QuadFunction<F, S, T, Q, Mono<P>> pMono, PentaFunction<F, S, T, Q, P, Mono<H>> hMono,
	        HexaFunction<F, S, T, Q, P, H, Mono<E>> seMono, SeptFunction<F, S, T, Q, P, H, E, Mono<O>> oMono,
	        OctaFunction<F, S, T, Q, P, H, E, O, Mono<N>> nMono,
	        NanoFunction<F, S, T, Q, P, H, E, O, N, Mono<D>> dMono) {
		// Required more than 8 arguments

		return fMono.get()
		        .flatMap(f ->
				{
			        Mono<S> ms = sMono.apply(f);
			        return ms.flatMap(s -> {

				        Mono<T> mt = tMono.apply(f, s);
				        return mt.flatMap(t -> {

					        Mono<Q> mq = qMono.apply(f, s, t);
					        return mq.flatMap(q -> {

						        Mono<P> mp = pMono.apply(f, s, t, q);
						        return mp.flatMap(p -> {

							        Mono<H> mh = hMono.apply(f, s, t, q, p);
							        return mh.flatMap(h -> {

								        Mono<E> mSe = seMono.apply(f, s, t, q, p, h);
								        return mSe.flatMap(se -> {

									        Mono<O> mo = oMono.apply(f, s, t, q, p, h, se);
									        return mo.flatMap(o -> {

										        Mono<N> mn = nMono.apply(f, s, t, q, p, h, se, o);
										        return mn.flatMap(n -> dMono.apply(f, s, t, q, p, h, se, o, n));
									        });
								        });
							        });
						        });
					        });
				        });
			        });
		        });

	}

	public static <F, S, T, Q, P, H, E, O, N, D> Mono<Tuple10<F, S, T, Q, P, H, E, O, N, D>> flatMapConsolidate(// NOSONAR
	        Supplier<Mono<F>> fMono, Function<F, Mono<S>> sMono, BiFunction<F, S, Mono<T>> tMono,
	        TriFunction<F, S, T, Mono<Q>> qMono, QuadFunction<F, S, T, Q, Mono<P>> pMono,
	        PentaFunction<F, S, T, Q, P, Mono<H>> hMono, HexaFunction<F, S, T, Q, P, H, Mono<E>> seMono,
	        SeptFunction<F, S, T, Q, P, H, E, Mono<O>> oMono, OctaFunction<F, S, T, Q, P, H, E, O, Mono<N>> nMono,
	        NanoFunction<F, S, T, Q, P, H, E, O, N, Mono<D>> dMono) {
		// Required more than 8 arguments

		return fMono.get()
		        .flatMap(f ->
				{
			        Mono<S> ms = sMono.apply(f);
			        return ms.flatMap(s -> {

				        Mono<T> mt = tMono.apply(f, s);
				        return mt.flatMap(t -> {

					        Mono<Q> mq = qMono.apply(f, s, t);
					        return mq.flatMap(q -> {

						        Mono<P> mp = pMono.apply(f, s, t, q);
						        return mp.flatMap(p -> {

							        Mono<H> mh = hMono.apply(f, s, t, q, p);
							        return mh.flatMap(h -> {

								        Mono<E> mSe = seMono.apply(f, s, t, q, p, h);
								        return mSe.flatMap(se -> {

									        Mono<O> mo = oMono.apply(f, s, t, q, p, h, se);
									        return mo.flatMap(o -> {

										        Mono<N> mn = nMono.apply(f, s, t, q, p, h, se, o);
										        return mn.flatMap(n -> dMono.apply(f, s, t, q, p, h, se, o, n)
										                .map(d -> new Tuple10<>(f, s, t, q, p, h, se, o, n, d)));
									        });
								        });
							        });
						        });
					        });
				        });
			        });
		        });

	}

	public static <F, S> Flux<S> flatMapFlux(Supplier<Flux<F>> fFlux, Function<F, Flux<S>> sFlux) {

		return fFlux.get()
		        .flatMap(sFlux::apply);
	}

	public static <F, S, T> Flux<T> flatMapFlux(Supplier<Flux<F>> fFlux, Function<F, Flux<S>> sFlux,
	        BiFunction<F, S, Flux<T>> tFlux) {

		return fFlux.get()
		        .flatMap(f ->
				{
			        Flux<S> ms = sFlux.apply(f);
			        return ms.flatMap(s -> tFlux.apply(f, s));
		        });

	}

	public static <F, S, T, Q> Flux<Q> flatMapFlux(Supplier<Flux<F>> fFlux, Function<F, Flux<S>> sFlux,
	        BiFunction<F, S, Flux<T>> tFlux, TriFunction<F, S, T, Flux<Q>> qFlux) {

		return fFlux.get()
		        .flatMap(f ->
				{
			        Flux<S> ms = sFlux.apply(f);
			        return ms.flatMap(s -> {

				        Flux<T> mt = tFlux.apply(f, s);
				        return mt.flatMap(t -> qFlux.apply(f, s, t));
			        });
		        });

	}

	public static <F, S, T, Q, P> Flux<P> flatMapFlux(Supplier<Flux<F>> fFlux, Function<F, Flux<S>> sFlux,
	        BiFunction<F, S, Flux<T>> tFlux, TriFunction<F, S, T, Flux<Q>> qFlux,
	        QuadFunction<F, S, T, Q, Flux<P>> pFlux) {

		return fFlux.get()
		        .flatMap(f ->
				{
			        Flux<S> ms = sFlux.apply(f);
			        return ms.flatMap(s -> {

				        Flux<T> mt = tFlux.apply(f, s);
				        return mt.flatMap(t -> {

					        Flux<Q> mq = qFlux.apply(f, s, t);
					        return mq.flatMap(q -> pFlux.apply(f, s, t, q));
				        });
			        });
		        });

	}

	public static <F, S, T, Q, P, H> Flux<H> flatMapFlux(Supplier<Flux<F>> fFlux, Function<F, Flux<S>> sFlux,
	        BiFunction<F, S, Flux<T>> tFlux, TriFunction<F, S, T, Flux<Q>> qFlux,
	        QuadFunction<F, S, T, Q, Flux<P>> pFlux, PentaFunction<F, S, T, Q, P, Flux<H>> hFlux) {

		return fFlux.get()
		        .flatMap(f ->
				{
			        Flux<S> ms = sFlux.apply(f);
			        return ms.flatMap(s -> {

				        Flux<T> mt = tFlux.apply(f, s);
				        return mt.flatMap(t -> {

					        Flux<Q> mq = qFlux.apply(f, s, t);
					        return mq.flatMap(q -> {

						        Flux<P> mp = pFlux.apply(f, s, t, q);
						        return mp.flatMap(p -> hFlux.apply(f, s, t, q, p));
					        });
				        });
			        });
		        });

	}

	public static <F, S, T, Q, P, H, E> Flux<E> flatMapFlux(Supplier<Flux<F>> fFlux, Function<F, Flux<S>> sFlux,
	        BiFunction<F, S, Flux<T>> tFlux, TriFunction<F, S, T, Flux<Q>> qFlux,
	        QuadFunction<F, S, T, Q, Flux<P>> pFlux, PentaFunction<F, S, T, Q, P, Flux<H>> hFlux,
	        HexaFunction<F, S, T, Q, P, H, Flux<E>> seFlux) {

		return fFlux.get()
		        .flatMap(f ->
				{
			        Flux<S> ms = sFlux.apply(f);
			        return ms.flatMap(s -> {

				        Flux<T> mt = tFlux.apply(f, s);
				        return mt.flatMap(t -> {

					        Flux<Q> mq = qFlux.apply(f, s, t);
					        return mq.flatMap(q -> {

						        Flux<P> mp = pFlux.apply(f, s, t, q);
						        return mp.flatMap(p -> {

							        Flux<H> mh = hFlux.apply(f, s, t, q, p);
							        return mh.flatMap(h -> seFlux.apply(f, s, t, q, p, h));
						        });
					        });
				        });
			        });
		        });

	}

	public static <F, S, T, Q, P, H, E, O> Flux<O> flatMapFlux(Supplier<Flux<F>> fFlux, Function<F, Flux<S>> sFlux, // NOSONAR
	        BiFunction<F, S, Flux<T>> tFlux, TriFunction<F, S, T, Flux<Q>> qFlux,
	        QuadFunction<F, S, T, Q, Flux<P>> pFlux, PentaFunction<F, S, T, Q, P, Flux<H>> hFlux,
	        HexaFunction<F, S, T, Q, P, H, Flux<E>> seFlux, SeptFunction<F, S, T, Q, P, H, E, Flux<O>> oFlux) {
		// Required more than 8 arguments

		return fFlux.get()
		        .flatMap(f ->
				{
			        Flux<S> ms = sFlux.apply(f);
			        return ms.flatMap(s -> {

				        Flux<T> mt = tFlux.apply(f, s);
				        return mt.flatMap(t -> {

					        Flux<Q> mq = qFlux.apply(f, s, t);
					        return mq.flatMap(q -> {

						        Flux<P> mp = pFlux.apply(f, s, t, q);
						        return mp.flatMap(p -> {

							        Flux<H> mh = hFlux.apply(f, s, t, q, p);
							        return mh.flatMap(h -> {

								        Flux<E> mSe = seFlux.apply(f, s, t, q, p, h);
								        return mSe.flatMap(se -> oFlux.apply(f, s, t, q, p, h, se));
							        });
						        });
					        });
				        });
			        });
		        });

	}

	public static <F, S, T, Q, P, H, E, O, N> Flux<N> flatMapFlux(Supplier<Flux<F>> fFlux, Function<F, Flux<S>> sFlux, // NOSONAR
	        BiFunction<F, S, Flux<T>> tFlux, TriFunction<F, S, T, Flux<Q>> qFlux,
	        QuadFunction<F, S, T, Q, Flux<P>> pFlux, PentaFunction<F, S, T, Q, P, Flux<H>> hFlux,
	        HexaFunction<F, S, T, Q, P, H, Flux<E>> seFlux, SeptFunction<F, S, T, Q, P, H, E, Flux<O>> oFlux,
	        OctaFunction<F, S, T, Q, P, H, E, O, Flux<N>> nFlux) {
		// Required more than 8 arguments

		return fFlux.get()
		        .flatMap(f ->
				{
			        Flux<S> ms = sFlux.apply(f);
			        return ms.flatMap(s -> {

				        Flux<T> mt = tFlux.apply(f, s);
				        return mt.flatMap(t -> {

					        Flux<Q> mq = qFlux.apply(f, s, t);
					        return mq.flatMap(q -> {

						        Flux<P> mp = pFlux.apply(f, s, t, q);
						        return mp.flatMap(p -> {

							        Flux<H> mh = hFlux.apply(f, s, t, q, p);
							        return mh.flatMap(h -> {

								        Flux<E> mSe = seFlux.apply(f, s, t, q, p, h);
								        return mSe.flatMap(se -> {

									        Flux<O> mo = oFlux.apply(f, s, t, q, p, h, se);
									        return mo.flatMap(o -> nFlux.apply(f, s, t, q, p, h, se, o));
								        });
							        });
						        });
					        });
				        });
			        });
		        });

	}

	public static <F, S, T, Q, P, H, E, O, N, D> Flux<D> flatMapFlux(Supplier<Flux<F>> fFlux, // NOSONAR
	        Function<F, Flux<S>> sFlux, BiFunction<F, S, Flux<T>> tFlux, TriFunction<F, S, T, Flux<Q>> qFlux,
	        QuadFunction<F, S, T, Q, Flux<P>> pFlux, PentaFunction<F, S, T, Q, P, Flux<H>> hFlux,
	        HexaFunction<F, S, T, Q, P, H, Flux<E>> seFlux, SeptFunction<F, S, T, Q, P, H, E, Flux<O>> oFlux,
	        OctaFunction<F, S, T, Q, P, H, E, O, Flux<N>> nFlux,
	        NanoFunction<F, S, T, Q, P, H, E, O, N, Flux<D>> dFlux) {
		// Required more than 8 arguments

		return fFlux.get()
		        .flatMap(f ->
				{
			        Flux<S> ms = sFlux.apply(f);
			        return ms.flatMap(s -> {

				        Flux<T> mt = tFlux.apply(f, s);
				        return mt.flatMap(t -> {

					        Flux<Q> mq = qFlux.apply(f, s, t);
					        return mq.flatMap(q -> {

						        Flux<P> mp = pFlux.apply(f, s, t, q);
						        return mp.flatMap(p -> {

							        Flux<H> mh = hFlux.apply(f, s, t, q, p);
							        return mh.flatMap(h -> {

								        Flux<E> mSe = seFlux.apply(f, s, t, q, p, h);
								        return mSe.flatMap(se -> {

									        Flux<O> mo = oFlux.apply(f, s, t, q, p, h, se);
									        return mo.flatMap(o -> {

										        Flux<N> mn = nFlux.apply(f, s, t, q, p, h, se, o);
										        return mn.flatMap(n -> dFlux.apply(f, s, t, q, p, h, se, o, n));
									        });
								        });
							        });
						        });
					        });
				        });
			        });
		        });

	}

	private FlatMapUtil() {
	}
}
