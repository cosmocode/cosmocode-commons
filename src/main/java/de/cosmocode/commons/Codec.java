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

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import com.google.common.collect.Iterators;

import java.util.Iterator;

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
     * Composes this codec and the given bijection into a codec using
     * the bijection as an adapter from {@code T->S} and vice versa.
     * 
     * @since 1.8
     * @param <S> the new target type
     * @param bijection the bijection
     * @return a codec which combines this codec and the given bijection
     * @throws NullPointerException if bijection is null
     */
    public <S> Codec<F, S> compose(Bijection<T, S>  bijection) {
        return new ComposedCodec<F, T, S>(this, bijection);
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
        return inverse().encode(iterable);
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
        return inverse().encode(iterator);
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
    
}
