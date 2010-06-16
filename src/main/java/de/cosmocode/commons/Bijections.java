package de.cosmocode.commons;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

public final class Bijections {

    private Bijections() {
        
    }
    
    public static <F, T, S> Bijection<F, S> compose(Bijection<F, T> left, Bijection<T, S> right) {
        return new ComposedBijection<F, T, S>(left, right);
    }
    
    private static final class ComposedBijection<F, T, S> implements Bijection<F, S> {
        
        private final Bijection<F, T> left;
        private final Bijection<T, S> right;
        
        private Bijection<S, F> inverse;
        
        public ComposedBijection(Bijection<F, T> left, Bijection<T, S> right) {
            this.left = Preconditions.checkNotNull(left, "Left");
            this.right = Preconditions.checkNotNull(right, "Right");
        }

        @Override
        public S apply(F from) {
            return right.apply(left.apply(from));
        }
        
        @Override
        public Bijection<S, F> inverse() {
            if (inverse == null) {
                inverse = new InverseComposedBijection<F, T, S>(this, right.inverse(), left.inverse());
            }
            return inverse;
        }
        
        private static final class InverseComposedBijection<F, T, S> implements Bijection<S, F> {
            
            private final Bijection<F, S> original;
            private final Bijection<S, T> left;
            private final Bijection<T, F> right;
            
            public InverseComposedBijection(Bijection<F, S> original, Bijection<S, T> left, Bijection<T, F> right) {
                this.original = Preconditions.checkNotNull(original, "Original");
                this.left = Preconditions.checkNotNull(left, "Left");
                this.right = Preconditions.checkNotNull(right, "Right");
            }
            
            @Override
            public F apply(S from) {
                return right.apply(left.apply(from));
            }
            
            @Override
            public Bijection<F, S> inverse() {
                return original;
            }

            @Override
            public int hashCode() {
                return Objects.hashCode(original, left, right);
            }
            
            @Override
            public boolean equals(Object that) {
                if (this == that) {
                    return true;
                } else if (that instanceof InverseComposedBijection<?, ?, ?>) {
                    final InverseComposedBijection<?, ?, ?> other = InverseComposedBijection.class.cast(that);
                    return original.equals(other.original) && left.equals(other.left) && right.equals(other.right);
                } else {
                    return false;
                }
            }
            
            @Override
            public String toString() {
                return original + ".inverse()";
            }
            
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(left, right);
        }
        
        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            } else if (that instanceof ComposedBijection<?, ?, ?>) {
                final ComposedBijection<?, ?, ?> other = ComposedBijection.class.cast(that);
                return left.equals(other.left) && right.equals(other.right);
            } else {
                return false;
            }
        }
        
        @Override
        public String toString() {
            return "Bijections.compose(" + left + ", " + right + ")";
        }
        
    }

}
