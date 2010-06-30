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

package de.cosmocode.commons.validation;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Constraint;

/**
 * An extension to the {@link Constraint} and {@link Predicate}
 * interface which adds several more methods to support
 * easy method chaining.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <T> the generic parameter type
 */
public interface Rule<T> extends Constraint<T>, Predicate<T> {

    /**
     * Returns a {@link Rule} that is a conjunction
     * of this rule and the given rule.
     * 
     * @since 1.9
     * @param that the other rule
     * @return a conjuction rule
     * @throws NullPointerException if that is null
     */
    Rule<T> and(Rule<? super T> that);

    /**
     * Returns a {@link Rule} that is a disjunction
     * of this rule and the given rule.
     * 
     * @since 1.9
     * @param that the other rule
     * @return a disjunction rule
     * @throws NullPointerException if that is null
     */
    Rule<T> or(Rule<? super T> that);
    
    /**
     * Returns a {@link Rule} that is a negation of this rule.
     * 
     * @since 1.9
     * @return the negated version of this rule
     */
    Rule<T> not();
    
    /**
     * Returns a xor {@link Rule} of this rule and the given
     * one.
     * 
     * @since 1.9 
     * @param that the other rule
     * @return a xor rule
     * @throws NullPointerException if that is null
     */
    Rule<T> xor(Rule<? super T> that);
    
    /**
     * Composes this rule into another rule using the specified
     * function to convert from S to T.
     * 
     * @since 1.9
     * @param <S> the generic parameter type
     * @param function the function being used convert from S to T
     * @return a composed rule of this rule the given function
     * @throws NullPointerException if function is null
     */
    <S> Rule<S> compose(Function<? super S, ? extends T> function);
    
}
