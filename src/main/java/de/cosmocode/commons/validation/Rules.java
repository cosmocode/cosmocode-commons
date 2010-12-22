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

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

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
    public static <T> Rule<T> of(@Nonnull Predicate<? super T> predicate) {
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
    public static <T> Rule<T> sameAs(@Nullable T value) {
        return value == null ? Rules.<T>isNull() : new SameAsRule<T>(value);
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
    public static <T> Rule<T> notSameAs(@Nullable T value) {
        return sameAs(value).negate();
    }
    
    /**
     * A Rule-based alternative to {@link Predicates#equalTo(Object)}.
     *
     * @since 1.20
     * @param <T> generic parameter type
     * @param target the value to check agains
     * @return an equality checking rule
     */
    public static <T> Rule<T> equalTo(@Nullable T target) {
        return target == null ? Rules.<T>isNull() : new EqualToRule<T>(target);
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
     * compared to value returns less than 0.
     * 
     * @since 1.9
     * @param <C> the generic parameter type
     * @param <E> generic parameter type
     * @param value the value
     * @return a lt rule comparing input and value
     * @throws NullPointerException if value is null
     */
    public static <C extends Comparable<E>, E> Rule<C> lessThan(@Nonnull E value) {
        return new LessThanRule<C, E>(value);
    }
    
    /**
     * Returns a rule which evaluates to true if the supplied input
     * is less than the given comparable.
     * 
     * @deprecated use {@link #lessThan(Object)}
     * @since 1.9
     * @param <C> the generic parameter type
     * @param <E> generic parameter type
     * @param value the value
     * @return a lt rule comparing input and value
     * @throws NullPointerException if value is null
     */
    @Deprecated
    public static <C extends Comparable<E>, E> Rule<C> lt(@Nonnull E value) {
        return lessThan(value);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * compared to value returns 0 or less.
     * 
     * @since 1.9
     * @param <C> the generic parameter type
     * @param <E> generic parameter type
     * @param value the value
     * @return a le rule comparing input and value
     * @throws NullPointerException if value is null
     */
    public static <C extends Comparable<E>, E> Rule<C> lessThanOrEqualTo(@Nonnull E value) {
        return new LessOrEqualToRule<C, E>(value);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * is less than or equals to the given value.
     * 
     * @deprecated use {@link #lessThanOrEqualTo(Object)}
     * @since 1.9
     * @param <C> the generic parameter type
     * @param <E> generic parameter type
     * @param value the value
     * @return a le rule comparing input and value
     * @throws NullPointerException if value is null
     */
    @Deprecated
    public static <C extends Comparable<E>, E> Rule<C> le(@Nonnull E value) {
        return lessThanOrEqualTo(value);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * compared to value returns 0.
     * 
     * @since 1.9
     * @param <C> the generic parameter type
     * @param <E> generic parameter type
     * @param value the value
     * @return a eq rule comparing input and value
     * @throws NullPointerException if value is null
     */
    public static <C extends Comparable<E>, E> Rule<C> comparesTo(@Nonnull E value) {
        return new ComparesToRule<C, E>(value);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * is equals to the given value.
     * 
     * @deprecated use {@link #comparesTo(Object)}
     * @since 1.9
     * @param <C> the generic parameter type
     * @param <E> generic parameter type
     * @param value the value
     * @return a eq rule comparing input and value
     * @throws NullPointerException if value is null
     */
    @Deprecated
    public static <C extends Comparable<E>, E> Rule<C> eq(@Nonnull E value) {
        return comparesTo(value);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * compared to value return 0 or more.
     * 
     * @since 1.9
     * @param <C> the generic parameter type
     * @param <E> generic parameter type
     * @param value the value
     * @return a ge rule comparing input and value
     * @throws NullPointerException if value is null
     */
    public static <C extends Comparable<E>, E> Rule<C> greaterThanOrEqualTo(@Nonnull E value) {
        return new GreaterOrEqualToRule<C, E>(value);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * is greater than or equals to the given value.
     * 
     * @deprecated use {@link #greaterThanOrEqualTo(Object)}
     * @since 1.9
     * @param <C> the generic parameter type
     * @param <E> generic parameter type
     * @param value the value
     * @return a ge rule comparing input and value
     * @throws NullPointerException if value is null
     */
    @Deprecated
    public static <C extends Comparable<E>, E> Rule<C> ge(@Nonnull E value) {
        return greaterThanOrEqualTo(value);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * compared to value returns more than 0.
     * 
     * @since 1.9
     * @param <C> the generic parameter type
     * @param <E> generic parameter type
     * @param value the value
     * @return a gt rule comparing input and value
     * @throws NullPointerException if value is null
     */
    public static <C extends Comparable<E>, E> Rule<C> greaterThan(@Nonnull E value) {
        return new GreaterThanRule<C, E>(value);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * is greater than the given value.
     * 
     * @deprecated use {@link #greaterThan(Object)}
     * @since 1.9
     * @param <C> the generic parameter type
     * @param <E> generic parameter type
     * @param value the value
     * @return a gt rule comparing input and value
     * @throws NullPointerException if value is null
     */
    @Deprecated
    public static <C extends Comparable<E>, E> Rule<C> gt(@Nonnull E value) {
        return greaterThan(value);
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
    public static <C extends Comparable<E>, E> Rule<C> between(@Nonnull E lower, @Nonnull E upper) {
        return greaterThan(lower).and(lessThan(upper));
    }
    
    /**
     * Returns a rule which evaluates to true if the supplied input
     * is greater than or equal to lower and less than or equal to upper.
     *
     * @since 1.21
     * @param <C> the generic parameter type
     * @param <E> generic parameter type
     * @param lower the lower bound
     * @param upper the upper bound
     * @return a between rule comparing input with lower and upper
     * @throws NullPointerException if lower or upper is null
     */
    public static <C extends Comparable<E>, E> Rule<C> betweenOrEqualTo(@Nonnull E lower, @Nonnull E upper) {
        return greaterThanOrEqualTo(lower).and(lessThanOrEqualTo(upper));
    }

}
