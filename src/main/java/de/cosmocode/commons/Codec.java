/**
 * Copyright 2010 CosmoCode GmbH
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

import java.util.Iterator;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

/**
 * A codec is an extension to the {@link Bijection} interface which adds several
 * more methods.
 *
 * @since 1.0
 * @author Willi Schoenborn
 * @param <F> first type
 * @param <T> second type
 */
public abstract class Codec<F, T> implements Bijection<F, T> {

    /**
     * Encodes input of type F into type T. This method is usually applied lazily.
     * 
     * @since 1.0
     * @param input the input
     * @return an immutable instance of T
     * @throws NullPointerException if input is null
     * @throws IllegalArgumentException if input can not be encoded
     */
    public abstract T encode(F input);

    /**
     * Decodes input of type T into type F. This method is usually applied lazily.
     * 
     * @since 1.0
     * @param input the input
     * @return instante of F
     * @throws NullPointerException if input is null
     * @throws IllegalArgumentException if input can not be decoded
     */
    public abstract F decode(T input);

    @Override
    public T apply(F from) {
        return encode(from);
    }

    @Override
    public Codec<T, F> inverse() {
        return new InverseCodec<T, F>(this);
    }
    
    /**
     * Implementation of {@link Codec#inverse()}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     * @param <T> the source type
     * @param <F> the target type
     */
    private static final class InverseCodec<T, F> extends Codec<T, F> {
        
        private final Codec<F, T> codec;
        
        public InverseCodec(Codec<F, T> codec) {
            this.codec = Preconditions.checkNotNull(codec, "Codec");
        }

        @Override
        public F encode(T input) {
            return codec.decode(input);
        }

        @Override
        public T decode(F input) {
            return codec.encode(input);
        }
        
        @Override
        public Codec<F, T> inverse() {
            return codec;
        }
        
        @Override
        public int hashCode() {
            return -codec.hashCode();
        }

        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            } else if (that instanceof InverseCodec<?, ?>) {
                final InverseCodec<?, ?> other = InverseCodec.class.cast(that);
                return codec.equals(other.codec);
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return codec + ".flip()";
        }
        
    }
    
    /**
     * Composes this codec and the given one into one.
     * 
     * @since 1.8
     * @param <S> the new target type
     * @param codec the second codec
     * @return a codec which combines this codec and the given one
     * @throws NullPointerException if codec is null
     */
    public <S> Codec<F, S> compose(Codec<T, S> codec) {
        return new ComposedCodec<F, T, S>(this, codec);
    }
    
    /**
     * Implementation of {@link Codec#compose(Codec)}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     * @param <F> source type
     * @param <T> intermediate type
     * @param <S> target type
     */
    private static final class ComposedCodec<F, T, S> extends Codec<F, S> {
        
        private final Codec<F, T> left;
        private final Codec<T, S> right;
        
        public ComposedCodec(Codec<F, T> left, Codec<T, S> right) {
            this.left = Preconditions.checkNotNull(left, "Left");
            this.right = Preconditions.checkNotNull(right, "Right");
        }
        
        @Override
        public S encode(F input) {
            return right.encode(left.encode(input));
        };

        @Override
        public F decode(S input) {
            return left.decode(right.decode(input));
        };
        
        @Override
        public int hashCode() {
            return left.hashCode() ^ right.hashCode();
        }

        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            } else if (that instanceof ComposedCodec<?, ?, ?>) {
                final ComposedCodec<?, ?, ?> other = ComposedCodec.class.cast(that);
                return left.equals(other.left) && right.equals(other.right);
            } else {
                return false;
            }
        }

        @Override
        public String toString() {
            return left + ".compose(" + right + ")";
        }
        
    }
    
    /**
     * Filters this codec using the specific function. The function
     * will be applied to every parameter passed to {@link Codec#encode(Object)}
     * and every return value of {@link Codec#decode(Object)}.
     * 
     * @since 1.8
     * @param function the function to be applied upon all parameter/return value
     * @return a Codec backed by this codec which will be filtered by function
     * @throws NullPointerException if function is null
     */
    public Codec<F, T> filter(Function<? super F, F> function) {
        return new FilteringCodec<F, T>(this, function);
    }
    
    /**
     * Implementation of {@link Codec#filter(Function)}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     * @param <F> source type
     * @param <T> target type
     */
    private static final class FilteringCodec<F, T> extends Codec<F, T> {
        
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
            return codec.hashCode() ^ function.hashCode();
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
    
    /**
     * Returns an iterable that applies this codec to each element of iterable.
     * The returned iterable's iterator supports remove() if the provided iterator does.
     * After a successful remove() call, iterable no longer contains the corresponding element.
     * 
     * @since 1.8
     * @param iterable the backing iterable
     * @return an encoded version of the given iterable
     * @throws NullPointerException if iterable is null
     */
    public Iterable<T> encode(Iterable<? extends F> iterable) {
        return Iterables.transform(iterable, this);
    }
    
    /**
     * Returns an iterator that applies this codec to each element return by the given
     * iterator. The returned iterator supports remove() if the provided iterator does.
     * After a successful remove() call, iterator no longer contains the corresponding
     * element.
     * 
     * @since 1.8
     * @param iterator the backing iterator
     * @return an encoded version of the given iterator
     * @throws NullPointerException if iterator is null
     */
    public Iterator<T> encode(Iterator<? extends F> iterator) {
        return Iterators.transform(iterator, this);
    }

    /**
     * Returns an iterable that applies the inverse version of this codec to each element of iterable.
     * The returned iterable's iterator supports remove() if the provided iterator does.
     * After a successful remove() call, iterable no longer contains the corresponding element.
     * 
     * @since 1.8
     * @param iterable the backing iterable
     * @return an decoded version of the given iterable
     * @throws NullPointerException if iterable is null
     */
    public Iterable<F> decode(Iterable<? extends T> iterable) {
        return Iterables.transform(iterable, this.inverse());
    }

    /**
     * Returns an iterator that applies the inverse version of this codec to each element return by the given
     * iterator. The returned iterator supports remove() if the provided iterator does.
     * After a successful remove() call, iterator no longer contains the corresponding
     * element.
     * 
     * @since 1.8
     * @param iterator the backing iterator
     * @return an decoded version of the given iterator
     * @throws NullPointerException if iterator is null
     */
    public Iterator<F> decode(Iterator<? extends T> iterator) {
        return Iterators.transform(iterator, this.inverse());
    }

    /**
     * Returns a codec for a pre-existing {@link Bijection}.
     * 
     * @since 1.8
     * @param <F> the source type
     * @param <T> the target type
     * @param bijection the bijection that defines the conversion
     * @return a Codec backed by the specific bijection
     * @throws NullPointerException if bijection is null
     */
    public static <F, T> Codec<F, T> from(Bijection<F, T> bijection) {
        if (bijection instanceof Codec<?, ?>) {
            return (Codec<F, T>) bijection;
        } else {
            return new BijectionCodec<F, T>(bijection);
        }
    }
    
    /**
     * Implementation of {@link Codec#from(Bijection)}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     * @param <F> source type
     * @param <T> target type
     */
    private static final class BijectionCodec<F, T> extends Codec<F, T> {
        
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
    
}
