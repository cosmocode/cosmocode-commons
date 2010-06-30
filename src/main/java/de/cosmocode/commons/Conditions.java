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
import com.google.common.base.Predicate;
import com.google.common.collect.Constraint;

/**
 * Utility class for {@link Predicate}s, {@link Constraint}s and general conditional checks.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
public final class Conditions {

    private Conditions() {
        
    }

    /**
     * Checks the given input using the specified attribute.
     * 
     * @since 1.9
     * @param <T> the generic parameter type
     * @param predicate the predicate being used to check inout
     * @param input the input to be checked
     * @return input
     * @throws NullPointerException if predicate is null
     * @throws IllegalArgumentException if input does not satisfy predicate
     */
    public static <T> T checkArgument(Predicate<? super T> predicate, T input) {
        Preconditions.checkNotNull(predicate, "Predicate");
        if (predicate.apply(input)) {
            return input;
        } else {
            throw new IllegalArgumentException(String.format("%s does not satisfy %s", input, predicate));
        }
    }
    
    /**
     * Adapts the given predicate to the {@link Constraint} interface.
     * The returned constraint throws an {@link IllegalArgumentException} if
     * the supplied input does not satisfy the given predicate.
     * 
     * @since 1.8
     * @param <T> the generic parameter type
     * @param predicate the backing predicate
     * @return a constraint backed by the specified predicate
     */
    public static <T> Constraint<T> asContraint(Predicate<? super T> predicate) {
        return new PredicateConstraint<T>(predicate);
    }
    
    /**
     * Implementation of {@link Conditions#asContraint(Predicate)}.
     *
     * @since 1.9
     * @author Willi Schoenborn
     * @param <T> generic parameter type
     */
    private static final class PredicateConstraint<T> implements Constraint<T> {
        
        private final Predicate<? super T> predicate;
        
        public PredicateConstraint(Predicate<? super T> predicate) {
            this.predicate = Preconditions.checkNotNull(predicate, "Predicate");
        }
        
        @Override
        public T checkElement(T element) {
            return checkArgument(predicate, element);
        };
        
        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            } else if (that instanceof PredicateConstraint<?>) {
                final PredicateConstraint<?> other = PredicateConstraint.class.cast(that);
                return predicate.equals(other.predicate);
            } else {
                return false;
            }
        }

        @Override
        public int hashCode() {
            return predicate.hashCode();
        }

        @Override
        public String toString() {
            return "Conditions.asConstraint(" + predicate + ")";
        }
        
    }
    
    public static <T> Function<T, T> asFunction(Constraint<T> constraint) {
        return new ConstraintFunction<T>(constraint);
    }
    
    /**
     * Implementation of {@link Conditions#asFunction(Constraint)}.
     *
     * @since 1.9
     * @author Willi Schoenborn
     * @param <T> generic parameter type
     */
    private static final class ConstraintFunction<T> implements Function<T, T> {
        
        private final Constraint<T> constraint;
        
        public ConstraintFunction(Constraint<T> constraint) {
            this.constraint = Preconditions.checkNotNull(constraint, "Constraint");
        }
        
        @Override
        public T apply(T from) {
            return constraint.checkElement(from);
        };
        
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

}
