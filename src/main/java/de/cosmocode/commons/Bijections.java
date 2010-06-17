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

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
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
     * Implementation for {@link Bijections#compose(Bijection, Bijection)}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     * @param <F> source type
     * @param <T> intermediate type
     * @param <S> target type
     */
    private static final class ComposedBijection<F, T, S> implements Bijection<F, S> {
        
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
     * Implementation of {@link Bijections#compose(Function, Function)}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     * @param <F> source type
     * @param <T> target type
     */
    private static final class FunctionBijection<F, T> implements Bijection<F, T> {
        
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
            return left.hashCode();
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
     * Implementation of {@link Bijections#identity()}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     */
    private static enum IdentityBijection implements Bijection<Object, Object> {
        
        INSTANCE;

        @Override
        public Object apply(Object from) {
            return from;
        }

        @Override
        public Bijection<Object, Object> inverse() {
            return this;
        }
        
        @Override
        public String toString() {
            return "Bijections.identity()";
        }
        
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
        return new BiMapBijection<F, T>(map);
    }
    
    /**
     * Implementation of {@link Bijections#forBiMap(BiMap)}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     * @param <F> source type
     * @param <T> target type
     */
    private static final class BiMapBijection<F, T> implements Bijection<F, T> {
        
        private final BiMap<F, T> map;
        
        public BiMapBijection(BiMap<F, T> map) {
            this.map = Preconditions.checkNotNull(map, "Map");
        }

        @Override
        public T apply(F from) {
            final T result = map.get(from);
            Preconditions.checkArgument(result != null || map.containsKey(from), "Key '%s' not present in map", from);
            return result;
        }

        @Override
        public Bijection<T, F> inverse() {
            return new InverseBiMapBijection<T, F>(this, map.inverse());
        }
        
        @Override
        public int hashCode() {
            return map.hashCode();
        }
        
        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            } else if (that instanceof BiMapBijection<?, ?>) {
                final BiMapBijection<?, ?> other = BiMapBijection.class.cast(that);
                return map.equals(other.map);
            } else {
                return false;
            }
        }
        
        @Override
        public String toString() {
            return "Bijections.forBiMap(" + map + ")";
        }
        
    }
    
    /**
     * Inverse implementation of {@link BiMapBijection}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     * @param <T> source type
     * @param <F> target type
     */
    private static final class InverseBiMapBijection<T, F> implements Bijection<T, F> {
        
        private final Bijection<F, T> original;
        private final BiMap<T, F> map;
        
        public InverseBiMapBijection(Bijection<F, T> original, BiMap<T, F> map) {
            this.original = Preconditions.checkNotNull(original, "Original");
            this.map = Preconditions.checkNotNull(map, "Map");
        }

        @Override
        public F apply(T from) {
            final F result = map.get(from);
            Preconditions.checkArgument(result != null || map.containsKey(from), "Value '%s' not present in map", from);
            return result;
        }
        
        @Override
        public Bijection<F, T> inverse() {
            return original;
        }
        
        @Override
        public int hashCode() {
            return Objects.hashCode(original, map);
        }
        
        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            } else if (that instanceof InverseBiMapBijection<?, ?>) {
                final InverseBiMapBijection<?, ?> other = InverseBiMapBijection.class.cast(that);
                return original.equals(other.original) && map.equals(other.map);
            } else {
                return false;
            }
        }
        
        @Override
        public String toString() {
            return original + ".inverse()";
        }
        
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
        return new BiMapDefaultBijection<F, T>(map, defaultKey, defaultValue);
    }
    
    /**
     * Implementation of {@link Bijections#forBiMap(BiMap, Object, Object)}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     * @param <F> source type
     * @param <T> target type
     */
    private static final class BiMapDefaultBijection<F, T> implements Bijection<F, T> {
        
        private final BiMap<F, T> map;
        private final F defaultKey;
        private final T defaultValue;
        
        public BiMapDefaultBijection(BiMap<F, T> map, F defaultKey, T defaultValue) {
            this.map = Preconditions.checkNotNull(map, "Map");
            this.defaultKey = defaultKey;
            this.defaultValue = defaultValue;
        }

        @Override
        public T apply(F from) {
            final T result = map.get(from);
            if (result != null || map.containsKey(result)) {
                return result;
            } else {
                return defaultValue;
            }
        }

        @Override
        public Bijection<T, F> inverse() {
            return new InverseBiMapDefaultBijection<T, F>(this, map.inverse(), defaultKey);
        }
        
        @Override
        public int hashCode() {
            return Objects.hashCode(map, defaultKey, defaultValue);
        }
        
        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            } else if (that instanceof BiMapDefaultBijection<?, ?>) {
                final BiMapDefaultBijection<?, ?> other = BiMapDefaultBijection.class.cast(that);
                return map.equals(other.map) && 
                    Objects.equal(defaultKey, other.defaultKey) && 
                    Objects.equal(defaultValue, other.defaultValue);
            } else {
                return false;
            }
        }
        
        @Override
        public String toString() {
            return "Bijections.forBiMap(" + map + ", " + defaultKey + ", " + defaultValue + ")";
        }
        
    }
    
    /**
     * Inverse implementation of {@link BiMapDefaultBijection}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     * @param <T> source type
     * @param <F> target type
     */
    public static final class InverseBiMapDefaultBijection<T, F> implements Bijection<T, F> {
        
        private final Bijection<F, T> original;
        
        private final BiMap<T, F> map;
        private final F defaultKey;
        
        public InverseBiMapDefaultBijection(Bijection<F, T> original, BiMap<T, F> map, F defaultKey) {
            this.original = Preconditions.checkNotNull(original, "Original");
            this.map = Preconditions.checkNotNull(map, "Map");
            this.defaultKey = defaultKey;
        }

        @Override
        public F apply(T from) {
            final F result = map.get(from);
            if (result != null || map.containsKey(from)) {
                return result;
            } else {
                return defaultKey;
            }
        }

        @Override
        public Bijection<F, T> inverse() {
            return original;
        }

        @Override
        public int hashCode() {
            return Objects.hashCode(original, map, defaultKey);
        }

        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            } else if (that instanceof InverseBiMapDefaultBijection<?, ?>) {
                final InverseBiMapDefaultBijection<?, ?> other = InverseBiMapDefaultBijection.class.cast(that);
                return original.equals(other.original) && map.equals(other.map) &&
                    Objects.equal(defaultKey, other.defaultKey);
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
