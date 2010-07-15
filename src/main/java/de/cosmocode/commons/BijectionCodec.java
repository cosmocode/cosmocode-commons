package de.cosmocode.commons;

import com.google.common.base.Preconditions;

/**
 * Implementation of {@link Codec#from(Bijection)}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 * @param <F> source type
 * @param <T> target type
 */
final class BijectionCodec<F, T> extends Codec<F, T> {
    
    private final Bijection<F, T> bijection;
    private final Bijection<T, F> inverse;
    
    public BijectionCodec(Bijection<F, T> bijection) {
        this.bijection = Preconditions.checkNotNull(bijection, "Bijection");
        this.inverse = Preconditions.checkNotNull(bijection.inverse(), "%s.inverse()", bijection);
    }

    @Override
    public T encode(F input) {
        return bijection.apply(input);
    }

    @Override
    public F decode(T input) {
        return inverse.apply(input);
    }
    
    @Override
    public int hashCode() {
        return bijection.hashCode();
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof BijectionCodec<?, ?>) {
            final BijectionCodec<?, ?> other = BijectionCodec.class.cast(that);
            return bijection.equals(other.bijection);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Codec.from(" + bijection + ")";
    }
    
}
