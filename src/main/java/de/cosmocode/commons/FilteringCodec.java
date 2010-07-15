package de.cosmocode.commons;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * Implementation of {@link Codec#filter(Function)}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 * @param <F> source type
 * @param <T> target type
 */
final class FilteringCodec<F, T> extends Codec<F, T> {
    
    private final Codec<F, T> codec;
    private final Function<? super F, F> function;
    
    public FilteringCodec(Codec<F, T> codec, Function<? super F, F> function) {
        this.codec = Preconditions.checkNotNull(codec, "Codec");
        this.function = Preconditions.checkNotNull(function, "Function");
    }

    @Override
    public T encode(F input) {
        return codec.encode(function.apply(input));
    }

    @Override
    public F decode(T input) {
        return function.apply(codec.decode(input));
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(codec, function);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof FilteringCodec<?, ?>) {
            final FilteringCodec<?, ?> other = FilteringCodec.class.cast(that);
            return codec.equals(other.codec) && function.equals(other.function);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return codec + ".filter(" + function + ")";
    }
    
}
