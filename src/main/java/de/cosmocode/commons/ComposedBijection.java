/**
 * Copyright 2010 - 2013 CosmoCode GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.cosmocode.commons;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * Implementation for {@link Bijections#compose(Bijection, Bijection)}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 * @param <F> source type
 * @param <T> intermediate type
 * @param <S> target type
 */
final class ComposedBijection<F, T, S> implements Bijection<F, S> {
    
    private final Bijection<F, T> left;
    private final Bijection<T, S> right;
    
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
        return new InverseComposedBijection<F, T, S>(this, right.inverse(), left.inverse());
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

    /**
     * Inverse implementation of {@link ComposedBijection}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     * @param <F> source type
     * @param <T> intermediate type
     * @param <S> target type
     */
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
    
    
}
