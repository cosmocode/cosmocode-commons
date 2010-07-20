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

import de.cosmocode.commons.validation.AbstractRule;

/**
 * Implementation of {@link Reflection#isSubtypeOf(Class)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
final class IsSubTypeOf extends AbstractRule<Class<?>> {

    private final Class<?> type;
    
    public IsSubTypeOf(Class<?> type) {
        this.type = Preconditions.checkNotNull(type, "Type");
    }

    @Override
    public boolean apply(Class<?> input) {
        return type.isAssignableFrom(input);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof IsSubTypeOf) {
            final IsSubTypeOf other = IsSubTypeOf.class.cast(that);
            return type.equals(other.type);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return type.hashCode() ^ -35678874;
    }
    
    @Override
    public String toString() {
        return "Reflection.isSubTypeOf(" + type + ")";
    }
    
}
