package de.cosmocode.commons;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * Implementation of {@link Bijections#compose(Function, Function)}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 * @param <F> source type
 * @param <T> target type
 */
final class FunctionBijection<F, T> implements Bijection<F, T> {
    
    private final Function<? super F, ? extends T> left;
    private final Function<? super T, ? extends F> right;
    
    public FunctionBijection(Function<? super F, ? extends T> left, Function<? super T, ? extends F> right) {
        this.left = Preconditions.checkNotNull(left, "Left");
        this.right = Preconditions.checkNotNull(right, "Right");
    }

    @Override
    public T apply(F from) {
        return left.apply(from);
    }

    @Override
    public Bijection<T, F> inverse() {
        return new InverseFunctionBijection<T, F>(this, right);
    }

    @Override
    public int hashCode() {
        return left.hashCode() ^ -921210296;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof FunctionBijection<?, ?>) {
            final FunctionBijection<?, ?> other = FunctionBijection.class.cast(that);
            return left.equals(other.left);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Bijections.compose(" + left + ", " + right + ")";
    }
    

    /**
     * Inverse implementation of {@link FunctionBijection}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     * @param <T> source type
     * @param <F> target type
     */
    private static final class InverseFunctionBijection<T, F> implements Bijection<T, F> {
        
        private final Bijection<F, T> original;
        private final Function<? super T, ? extends F> function;
        
        public InverseFunctionBijection(Bijection<F, T> original, Function<? super T, ? extends F> function) {
            this.original = Preconditions.checkNotNull(original, "Original");
            this.function = Preconditions.checkNotNull(function, "Function");
        }

        @Override
        public F apply(T from) {
            return function.apply(from);
        }

        @Override
        public Bijection<F, T> inverse() {
            return original;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(original, function);
        }

        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            } else if (that instanceof InverseFunctionBijection<?, ?>) {
                final InverseFunctionBijection<?, ?> other = InverseFunctionBijection.class.cast(that);
                return original.equals(other.original) && function.equals(other.function);
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return original + ".inverse()";
        }
        
    }

    
}
