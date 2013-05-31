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
package de.cosmocode.commons.validation;

import com.google.common.base.Preconditions;

/**
 * A {@link Rule} which checks for identity.
 *
 * @since 1.20
 * @author Willi Schoenborn
 * @param <T> the generic parameter type
 */
final class SameAsRule<T> extends AbstractRule<T> {

    private final T value;
    
    SameAsRule(T value) {
        this.value = Preconditions.checkNotNull(value, "Value");
    }

    @Override
    public boolean apply(T input) {
        return input == value;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof SameAsRule<?>) {
            final SameAsRule<?> other = SameAsRule.class.cast(that);
            // this rule is identity based
            return value == other.value;
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return value.hashCode() ^ -875423434;
    }
    
    @Override
    public String toString() {
        return "Rules.sameAs(" + value + ")";
    }
    
}
