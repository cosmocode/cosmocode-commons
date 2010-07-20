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

package de.cosmocode.commons.reflect;

import com.google.common.base.Preconditions;

import de.cosmocode.commons.AbstractFunction;

/**
 * Implementation for {@link Reflection#asSubclass(Class)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <T> generic class type
 */
final class AsSubClass<T> extends AbstractFunction<Class<?>, Class<? extends T>> {

    private final Class<T> type;
    
    public AsSubClass(Class<T> type) {
        this.type = Preconditions.checkNotNull(type, "Type");
    }
    
    @Override
    public Class<? extends T> apply(Class<?> input) {
        return input.asSubclass(type);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof AsSubClass<?>) {
            final AsSubClass<?> other = AsSubClass.class.cast(that);
            return type.equals(other.type);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return type.hashCode() ^ -987456325;
    }

    @Override
    public String toString() {
        return "Reflection.asSubclass(" + type + ")";
    }

}
