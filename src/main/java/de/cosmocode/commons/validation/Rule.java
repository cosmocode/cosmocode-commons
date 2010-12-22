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
import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.Constraint;
import com.google.common.collect.Iterables;

/**
 * An extension to the {@link Constraint} and {@link Predicate}
 * interface which adds several more methods to support
 * easy method chaining.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <T> the generic parameter type
 */
@Beta
public interface Rule<T> extends Predicate<T>, Constraint<T> {
    
    @Override
    boolean apply(@Nullable T input);
    
    @Override
    T checkElement(@Nullable T element);
    
    /**
     * Returns true if every element in inputs satisfies this rule. If iterable is empty, true is returned.
     *
     * @since 1.21
     * @param inputs all inputs
     * @return true if every element satisfies this rule, false otherwise
     */
    @Beta
    boolean all(@Nonnull Iterable<? extends T> inputs);

    /**
     * Returns true if one or more elements in inputs satisfies this rule.
     *
     * @since 1.21
     * @param inputs all inputs
     * @return true if any element satisfies this rule, false otherwise
     */
    @Beta
    boolean any(@Nonnull Iterable<? extends T> inputs);

    /**
     * Returns true if no element in inputs satisfies this rule. If iterable is empty, true is returned.
     *
     * @since 1.21
     * @param inputs all inputs
     * @return true if none element satisfies this input
     */
    @Beta
    boolean none(@Nonnull Iterable<? extends T> inputs);

    /**
     * Returns the elements of unfiltered that satisfy this rule.
     * The resulting iterable's iterator does not support remove().
     *
     * @since 1.21
     * @param unfiltered the backing unfiltered iterable
     * @return a filtered live view of unfiltered
     */
    @Beta
    Iterable<T> filter(@Nonnull Iterable<T> unfiltered);

    /**
     * Returns the first element in iterable that satisfies this rule.
     * {@link Iterables#find(Iterable, Predicate)}
     *
     * @since 1.21
     * @param iterable the iterable to search in
     * @return the found element
     * @throws java.util.NoSuchElementException if no element in iterable matches the given predicate
     */
    @Beta
    T find(@Nonnull Iterable<? extends T> iterable);

    /**
     * Returns the first element in iterable that satisfies the given predicate, or defaultValue if none found.
     * {@link Iterables#find(Iterable, Predicate, Object)}
     *
     * @since 1.21
     * @param iterable the iterable to search in
     * @param defaultValue the return value if no element is found 
     * @return the found element or defaultValue if no element was found 
     */
    @Beta
    T find(@Nonnull Iterable<T> iterable, @Nullable T defaultValue);
    
    /**
     * Removes, from an iterable, every element that satisfies the provided predicate.
     * {@link Iterables#removeIf(Iterable, Predicate)}
     *
     * @since 1.21
     * @param removeFrom the iterable to (potentially) remove elements from
     * @return true if any elements were removed from the iterable
     * @throws UnsupportedOperationException if the iterable does not support remove()
     */
    @Beta
    boolean removeIf(@Nonnull Iterable<? extends T> removeFrom);
    
    /**
     * Returns a {@link Rule} that is a conjunction
     * of this rule and the given rule.
     * 
     * @since 1.9
     * @param <S> generic type parameter to prevent verbose generics
     * @param that the other rule
     * @return a conjuction rule
     * @throws NullPointerException if that is null
     */
    <S extends T> Rule<S> and(@Nonnull Rule<? super T> that);

    /**
     * Returns a {@link Rule} that is a conjunction
     * of this rule and the given predicate.
     * 
     * @since 1.9
     * @param <S> generic type parameter to prevent verbose generics
     * @param that the other predicate
     * @return a conjuction rule
     * @throws NullPointerException if that is null
     */
    <S extends T> Rule<S> and(@Nonnull Predicate<? super T> that);

    /**
     * Returns a {@link Rule} that is a disjunction
     * of this rule and the given rule.
     * 
     * @since 1.9
     * @param <S> generic type parameter to prevent verbose generics
     * @param that the other rule
     * @return a disjunction rule
     * @throws NullPointerException if that is null
     */
    <S extends T> Rule<S> or(@Nonnull Rule<? super T> that);


    /**
     * Returns a {@link Rule} that is a disjunction
     * of this rule and the given predicate.
     * 
     * @since 1.9
     * @param <S> generic type parameter to prevent verbose generics
     * @param that the other predicate
     * @return a disjunction rule
     * @throws NullPointerException if that is null
     */
    <S extends T> Rule<S> or(@Nonnull Predicate<? super T> that);
    
    /**
     * Returns a {@link Rule} that is a negation of this rule.
     * 
     * @since 1.20
     * @param <S> generic type parameter to prevent verbose generics
     * @return the negated version of this rule
     */
    <S extends T> Rule<S> negate();
    
    /**
     * Returns a {@link Rule} that is a negation of this rule.
     * 
     * @deprecated use {@link #negate()}
     * @since 1.9
     * @param <S> generic type parameter to prevent verbose generics
     * @return the negated version of this rule
     */
    @Deprecated
    <S extends T> Rule<S> not();
    
    /**
     * Returns a xor {@link Rule} of this rule and the given
     * one.
     * 
     * @since 1.9 
     * @param <S> generic type parameter to prevent verbose generics
     * @param that the other rule
     * @return a xor rule
     * @throws NullPointerException if that is null
     */
    <S extends T> Rule<S> xor(@Nonnull Rule<? super T> that);

    /**
     * Returns a xor {@link Rule} of this rule and the given predicate.
     * 
     * @since 1.9 
     * @param <S> generic type parameter to prevent verbose generics
     * @param that the other predicate
     * @return a xor rule
     * @throws NullPointerException if that is null
     */
    <S extends T> Rule<S> xor(@Nonnull Predicate<? super T> that);
    
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
    <S> Rule<S> compose(@Nonnull Function<? super S, ? extends T> function);
    
}
