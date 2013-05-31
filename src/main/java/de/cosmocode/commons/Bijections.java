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
import com.google.common.collect.BiMap;

/**
 * Utility class for {@link Bijection}s.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
public final class Bijections {

    private Bijections() {
        
    }
    
    /**
     * Returns the composition of two bijections. For {@code f: F->T} and
     * {@code g: T->S}, composition is defined as the function h such that
     * {@code h(a) == g(f(a))} for each {@code a}.
     * 
     * @since 1.8
     * @param <F> the source type
     * @param <T> the intermediate type
     * @param <S> the target type
     * @param f the first bijection
     * @param g the second bijection
     * @return a composed bijection of f and g 
     * @throws NullPointerException if f or g is null
     */
    public static <F, T, S> Bijection<F, S> compose(Bijection<F, T> f, Bijection<T, S> g) {
        return new ComposedBijection<F, T, S>(f, g);
    }
    
    /**
     * Composes a function {@code F->T} and  function {@code T->F} to a bijection {@code F->T}.
     * 
     * @since 1.8
     * @param <F> the source type
     * @param <T> the target type
     * @param left the first function
     * @param right the second function
     * @return a bijection backed by left and right
     * @throws NullPointerException if left or right is null
     */
    public static <F, T> Bijection<F, T> compose(Function<? super F, ? extends T> left, 
        Function<? super T, ? extends F> right) {
        return new FunctionBijection<F, T>(left, right);
    }
    
    /**
     * Provides the identity biinjection.
     * 
     * @since 1.8
     * @param <E> the source and target type
     * @return a bijection which always returns the parameter
     */
    @SuppressWarnings("unchecked")
    public static <E> Bijection<E, E> identity() {
        return (Bijection<E, E>) IdentityBijection.INSTANCE;
    }
    
    /**
     * Returns a bijection which performs a bimap lookup. The returned function
     * throws an {@link IllegalArgumentException} if given a key/value that does not
     * exist in the map.
     * 
     * @since 1.8
     * @param <F> the source type
     * @param <T> the target type
     * @param map the backing bimap
     * @return a bijection backed by the given bimap
     * @throws NullPointerException if map is null
     */
    public static <F, T> Bijection<F, T> forBiMap(BiMap<F, T> map) {
        return new ForBiMapBijection<F, T>(map);
    }
    
    /**
     * Returns a bijection which performs a map lookup with a default value. The
     * function created by this method returns {@code defaultValue} for all
     * inputs that do not belong to the map's key set.
     *
     * @since 1.8
     * @param <F> the source type
     * @param <T> the target type
     * @param map the backing bimap
     * @param defaultKey the default key
     * @param defaultValue the default value
     * @return a bijection backed by the given map
     * @throws NullPointerException if map is null
     */
    public static <F, T> Bijection<F, T> forBiMap(BiMap<F, T> map, F defaultKey, T defaultValue) {
        return new ForBiMapWithDefaultBijection<F, T>(map, defaultKey, defaultValue);
    }
    
}
