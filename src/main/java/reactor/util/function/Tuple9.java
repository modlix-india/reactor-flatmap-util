package reactor.util.function;

import java.util.Objects;
import java.util.function.Function;

import reactor.util.annotation.NonNull;
import reactor.util.annotation.Nullable;

public class Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, T9> extends Tuple8<T1, T2, T3, T4, T5, T6, T7, T8> { //NOSONAR
	//Tuple with 9 values required

	private static final long serialVersionUID = -7143309386405013711L;

	@NonNull
	final T9 t9; //NOSONAR
	//This value might not be a serialised object.

	public Tuple9(T1 t1, T2 t2, T3 t3, T4 t4, T5 t5, T6 t6, T7 t7, T8 t8, T9 t9) { //NOSONAR
		//Tuple with 9 requires more than 8 arguments
		super(t1, t2, t3, t4, t5, t6, t7, t8);
		this.t9 = Objects.requireNonNull(t9, "t9");
	}

	public T9 getT9() {
		return t9;
	}

	@Override
	public <R> Tuple9<R, T2, T3, T4, T5, T6, T7, T8, T9> mapT1(Function<T1, R> mapper) {
		return new Tuple9<>(mapper.apply(t1), t2, t3, t4, t5, t6, t7, t8, t9);
	}

	@Override
	public <R> Tuple9<T1, R, T3, T4, T5, T6, T7, T8, T9> mapT2(Function<T2, R> mapper) {
		return new Tuple9<>(t1, mapper.apply(t2), t3, t4, t5, t6, t7, t8, t9);
	}

	@Override
	public <R> Tuple9<T1, T2, R, T4, T5, T6, T7, T8, T9> mapT3(Function<T3, R> mapper) {
		return new Tuple9<>(t1, t2, mapper.apply(t3), t4, t5, t6, t7, t8, t9);
	}

	@Override
	public <R> Tuple9<T1, T2, T3, R, T5, T6, T7, T8, T9> mapT4(Function<T4, R> mapper) {
		return new Tuple9<>(t1, t2, t3, mapper.apply(t4), t5, t6, t7, t8, t9);
	}

	@Override
	public <R> Tuple9<T1, T2, T3, T4, R, T6, T7, T8, T9> mapT5(Function<T5, R> mapper) {
		return new Tuple9<>(t1, t2, t3, t4, mapper.apply(t5), t6, t7, t8, t9);
	}

	@Override
	public <R> Tuple9<T1, T2, T3, T4, T5, R, T7, T8, T9> mapT6(Function<T6, R> mapper) {
		return new Tuple9<>(t1, t2, t3, t4, t5, mapper.apply(t6), t7, t8, t9);
	}

	@Override
	public <R> Tuple9<T1, T2, T3, T4, T5, T6, R, T8, T9> mapT7(Function<T7, R> mapper) {
		return new Tuple9<>(t1, t2, t3, t4, t5, t6, mapper.apply(t7), t8, t9);
	}

	@Override
	public <R> Tuple9<T1, T2, T3, T4, T5, T6, T7, R, T9> mapT8(Function<T8, R> mapper) {
		return new Tuple9<>(t1, t2, t3, t4, t5, t6, t7, mapper.apply(t8), t9);
	}

	public <R> Tuple9<T1, T2, T3, T4, T5, T6, T7, T8, R> mapT9(Function<T9, R> mapper) {
		return new Tuple9<>(t1, t2, t3, t4, t5, t6, t7, t8, mapper.apply(t9));
	}

	@Nullable
	@Override
	public Object get(int index) {
		switch (index) {
		case 0:
			return t1;
		case 1:
			return t2;
		case 2:
			return t3;
		case 3:
			return t4;
		case 4:
			return t5;
		case 5:
			return t6;
		case 6:
			return t7;
		case 7:
			return t8;
		case 8:
			return t9;
		default:
			return null;
		}
	}

	@Override
	public Object[] toArray() {
		return new Object[] { t1, t2, t3, t4, t5, t6, t7, t8, t9 };
	}

	@Override
	public boolean equals(@Nullable Object o) {
		if (this == o)
			return true;
		if (!(o instanceof Tuple9))
			return false;
		if (!super.equals(o))
			return false;

		@SuppressWarnings("rawtypes")
		Tuple9 tuple9 = (Tuple9) o;

		return t9.equals(tuple9.t9);

	}

	@Override
	public int hashCode() {
		int result = super.hashCode();
		result = 31 * result + t9.hashCode();
		return result;
	}

	@Override
	public int size() {
		return 9;
	}
}
