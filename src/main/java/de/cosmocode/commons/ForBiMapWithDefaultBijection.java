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

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.BiMap;

/**
 * Implementation of {@link Bijections#forBiMap(BiMap, Object, Object)}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 * @param <F> source type
 * @param <T> target type
 */
final class ForBiMapWithDefaultBijection<F, T> implements Bijection<F, T> {
    
    private final BiMap<F, T> map;
    private final F defaultKey;
    private final T defaultValue;
    
    public ForBiMapWithDefaultBijection(BiMap<F, T> map, F defaultKey, T defaultValue) {
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
        } else if (that instanceof ForBiMapWithDefaultBijection<?, ?>) {
            final ForBiMapWithDefaultBijection<?, ?> other = ForBiMapWithDefaultBijection.class.cast(that);
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

    /**
     * Inverse implementation of {@link ForBiMapWithDefaultBijection}.
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
