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

import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * Utility class for {@link Rule}s.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
public final class Rules {

    private Rules() {
        
    }

    /**
     * Adapts the specified predicate to the {@link Rule} interface.
     * 
     * @since 1.9
     * @param <T> generic parameter type
     * @param predicate the backing predicate
     * @return a rule backed by the given predicate
     * @throws NullPointerException if predicate is null
     */
    public static <T> Rule<T> of(Predicate<? super T> predicate) {
        if (predicate instanceof Rule<?>) {
            @SuppressWarnings("unchecked")
            final Rule<T> rule = (Rule<T>) predicate;
            return rule;
        } else {
            return new PredicateRule<T>(predicate);
        }
    }
    
    /**
     * Rule-based alternative to {@link Predicates#alwaysTrue()}.
     * 
     * @since 1.13
     * @param <T> generic parameter type
     * @return a rule which always evaluates to true
     */
    @SuppressWarnings("unchecked")
    public static <T> Rule<T> alwaysTrue() {
        return (Rule<T>) TrueRule.INSTANCE;
    }

    /**
     * Rule-based alternative to {@link Predicates#alwaysFalse()}.
     * 
     * @since 1.13
     * @param <T> generic parameter type
     * @return a rule which always evaluates to false
     */
    @SuppressWarnings("unchecked")
    public static <T> Rule<T> alwaysFalse() {
        return (Rule<T>) FalseRule.INSTANCE;
    }
    
    /**
     * Rule-based alternative to {@link Predicates#isNull()}.
     *
     * @since 1.20
     * @param <T> generic parameter type
     * @return a rule which evaluates to true if the given parameter is null
     */
    // cast is safe, because we never access the parameter
    @SuppressWarnings("unchecked")
    public static <T> Rule<T> isNull() {
        return (Rule<T>) NullRule.INSTANCE;
    }

    /**
     * Rule-based alternative to {@link Predicates#notNull()}.
     *
     * @since 1.20
     * @param <T> generic parameter type
     * @return a rule which evaluates to true if the given parameter is not null
     */
    // cast is safe, because we never access the parameter
    @SuppressWarnings("unchecked")
    public static <T> Rule<T> isNotNull() {
        return (Rule<T>) NotNullRule.INSTANCE;
    }
    
    /**
     * Returs a rule which evaluates to true if the given parameter is
     * identical ({@code ==}) to value.
     *
     * @since 1.20
     * @param <T> generic parameter type
     * @param value the value to check agains
     * @return an identity checking rule
     */
    public static <T> Rule<T> is(T value) {
        return value == null ? Rules.<T>isNull() : new IsRule<T>(value);
    }

    /**
     * Returs a rule which evaluates to true if the given parameter is not
     * identical ({@code !=}) to value.
     *
     * @since 1.20
     * @param <T> generic parameter type
     * @param value the value to check agains
     * @return an non-identity checking rule
     */
    public static <T> Rule<T> isNot(T value) {
        return value == null ? Rules.<T>isNotNull() : is(value).negate();
    }
    
    /**
     * A Rule-based alternative to {@link Predicates#equalTo(Object)}.
     *
     * @since 1.20
     * @param <T> generic parameter type
     * @param target the value to check agains
     * @return an equality checking rule
     */
    public static <T> Rule<T> equalTo(T target) {
        return of(Predicates.equalTo(target));
    }
    
    /**
     * A Rule-based alternative to the negated version of {@link Predicates#equalTo(Object)}.
     *
     * @since 1.20
     * @param <T> generic parameter type
     * @param target the value to check agains
     * @return an non-equality checking rule
     */
    public static <T> Rule<T> notEqualTo(T target) {
        return equalTo(target).negate();
    }
    
    /**
     * Returns a rule which evaluates to true if the supplied input
     * is less than the given comparable.
     * 
     * @since 1.9
     * @param <C> the generic parameter type
     * @param <E> generic parameter type
     * @param value the value
     * @return a lt rule comparing input and value
     * @throws NullPointerException if value is null
     */
    public static <C extends Comparable<E>, E> Rule<C> lt(E value) {
        return new LtRule<C, E>(value);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * is less than or equals to the given value.
     * 
     * @since 1.9
     * @param <C> the generic parameter type
     * @param <E> generic parameter type
     * @param value the value
     * @return a le rule comparing input and value
     * @throws NullPointerException if value is null
     */
    public static <C extends Comparable<E>, E> Rule<C> le(E value) {
        return new LeRule<C, E>(value);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * is equals to the given value.
     * 
     * @since 1.9
     * @param <C> the generic parameter type
     * @param <E> generic parameter type
     * @param value the value
     * @return a eq rule comparing input and value
     * @throws NullPointerException if value is null
     */
    public static <C extends Comparable<E>, E> Rule<C> eq(E value) {
        return new EqRule<C, E>(value);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * is greater than or equals to the given value.
     * 
     * @since 1.9
     * @param <C> the generic parameter type
     * @param <E> generic parameter type
     * @param value the value
     * @return a ge rule comparing input and value
     * @throws NullPointerException if value is null
     */
    public static <C extends Comparable<E>, E> Rule<C> ge(E value) {
        return new GeRule<C, E>(value);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * is greater than the given value.
     * 
     * @since 1.9
     * @param <C> the generic parameter type
     * @param <E> generic parameter type
     * @param value the value
     * @return a gt rule comparing input and value
     * @throws NullPointerException if value is null
     */
    public static <C extends Comparable<E>, E> Rule<C> gt(E value) {
        return new GtRule<C, E>(value);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * is greater than lower and less than upper.
     * 
     * @since 1.9
     * @param <C> the generic parameter type
     * @param <E> generic parameter type
     * @param lower the lower bound
     * @param upper the upper bound
     * @return a between rule comparing input with lower and upper
     * @throws NullPointerException if lower or upper is null
     */
    public static <C extends Comparable<E>, E> Rule<C> between(E lower, E upper) {
        return gt(lower).and(lt(upper));
    }
    
}
