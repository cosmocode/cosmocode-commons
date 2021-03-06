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
    
    private final Bijection<T, F> inverse;
    
    public ForBiMapWithDefaultBijection(BiMap<F, T> map, F defaultKey, T defaultValue) {
        this.map = Preconditions.checkNotNull(map, "Map");
        this.defaultKey = defaultKey;
        this.defaultValue = defaultValue;
        this.inverse = new ForBiMapWithDefaultBijection<T, F>(map.inverse(), defaultValue, defaultKey, this);
    }
    
    private ForBiMapWithDefaultBijection(BiMap<F, T> map, F defaultKey, T defaultValue, Bijection<T, F> inverse) {
        this.map = Preconditions.checkNotNull(map, "Map");
        this.defaultKey = defaultKey;
        this.defaultValue = defaultValue;
        this.inverse = Preconditions.checkNotNull(inverse, "Inverse");
    }
    
    @Override
    public T apply(F from) {
        final T result = map.get(from);
        if (result == null) {
            return map.containsKey(from) ? null : defaultValue;
        } else {
            return result;
        }
    }

    @Override
    public Bijection<T, F> inverse() {
        return inverse;
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

}
