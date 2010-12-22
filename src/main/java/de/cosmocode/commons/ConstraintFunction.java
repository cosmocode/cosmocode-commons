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
import com.google.common.base.Preconditions;
import com.google.common.collect.Constraint;

/**
 * Implementation of {@link Conditions#asFunction(Constraint)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <T> generic parameter type
 */
final class ConstraintFunction<T> implements Function<T, T> {
    
    private final Constraint<T> constraint;
    
    public ConstraintFunction(Constraint<T> constraint) {
        this.constraint = Preconditions.checkNotNull(constraint, "Constraint");
    }
    
    @Override
    public T apply(T from) {
        return constraint.checkElement(from);
    }
   
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof ConstraintFunction<?>) {
            final ConstraintFunction<?> other = ConstraintFunction.class.cast(that);
            return constraint.equals(other.constraint);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return constraint.hashCode();
    }
    
    @Override
    public String toString() {
        return "Conditions.asFunction(" + constraint + ")";
    }
    
}
