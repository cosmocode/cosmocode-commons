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

import com.google.common.annotations.Beta;
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
@Beta
public interface Rule<T> extends Predicate<T>, Constraint<T> {

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
    // type parameter <S> lets us avoid the extra <String> in statements like:
    // Strings.contains("a").<String>and(Strings.contains("b"));
    <S extends T> Rule<S> and(Rule<? super T> that);

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
    // type parameter <S> lets us avoid the extra <String> in statements like:
    // Strings.contains("a").<String>and(Strings.contains("b"));
    <S extends T> Rule<S> and(Predicate<? super T> that);

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
    // type parameter <S> lets us avoid the extra <String> in statements like:
    // Strings.contains("a").<String>or(Strings.contains("b"));
    <S extends T> Rule<S> or(Rule<? super T> that);


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
    // type parameter <S> lets us avoid the extra <String> in statements like:
    // Strings.contains("a").<String>or(Strings.contains("b"));
    <S extends T> Rule<S> or(Predicate<? super T> that);
    
    /**
     * Returns a {@link Rule} that is a negation of this rule.
     * 
     * @since 1.20
     * @param <S> generic type parameter to prevent verbose generics
     * @return the negated version of this rule
     */
    // type parameter <S> lets us avoid the extra <String> in statements like:
    // Strings.contains("a").<String>or(Strings.contains("b")).negate();
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
    // type parameter <S> lets us avoid the extra <String> in statements like:
    // Strings.contains("a").<String>xor(Strings.contains("b"));
    <S extends T> Rule<S> xor(Rule<? super T> that);

    /**
     * Returns a xor {@link Rule} of this rule and the given predicate.
     * 
     * @since 1.9 
     * @param <S> generic type parameter to prevent verbose generics
     * @param that the other predicate
     * @return a xor rule
     * @throws NullPointerException if that is null
     */
    // type parameter <S> lets us avoid the extra <String> in statements like:
    // Strings.contains("a").<String>xor(Strings.contains("b"));
    <S extends T> Rule<S> xor(Predicate<? super T> that);
    
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
