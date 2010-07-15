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

import java.text.Collator;
import java.util.Collection;
import java.util.Locale;

import javax.annotation.Nonnull;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Iterables;
import com.google.common.collect.Ordering;

import de.cosmocode.commons.validation.Rule;

/**
 * Utility class inspired by {@link StringUtils},
 * providing {@link String} related helper methods.
 *
 * @author Willi Schoenborn
 */
public final class Strings {

    /**
     * Natural ordering on {@link String}s which trims comparee
     * first using {@link TrimMode#NULL} and puts nulls to the end.
     */
    public static final Ordering<String> CASE_SENSITIVE = Ordering.natural().nullsLast().onResultOf(TrimMode.NULL);
    
    /**
     * A case-insensitive version of {@link Strings#CASE_SENSITIVE}.
     */
    public static final Ordering<String> CASE_INSENSITIVE = Ordering.from(String.CASE_INSENSITIVE_ORDER).
        nullsLast().onResultOf(TrimMode.NULL);
    
    public static final String DEFAULT_DELIMITER = " ";
    
    private static final Object[] EMPTY_ARRAY = {};

    /**
     * Prevent instantiation.
     */
    private Strings() {
        
    }

    /**
     * Creates an {@link Ordering} which uses a {@link Collator} based
     * on the given {@link Locale} to sort Strings.
     * 
     * @param locale the desired locale
     * @return a new {@link Ordering} backed by a {@link Collator}
     * @throws NullPointerException if locale is null
     */
    public static Ordering<String> orderBy(@Nonnull Locale locale) {
        return new CollatorOrdering(locale);
    }

    /**
     * Returns a Function able to convert strings into their lowercase counterparts
     * using {@link String#toLowerCase()}.
     * 
     * <p>
     *   The returned function handles null by returning them.
     * </p>
     * 
     * @since 1.9
     * @return a function covnerting strings to lowercase
     */
    public static Function<String, String> toLowerCase() {
        return StringLowerCaseFunction.INSTANCE;
    }
    
    
    /**
     * Returns a Function able to convert strings into their lowercase counterparts
     * using {@link String#toLowerCase(Locale))}.
     * 
     * <p>
     *   The returned function handles null by returning them.
     * </p>
     * 
     * @since 1.9
     * @param locale the locale being used to convert to lowercase
     * @return a function converting strings to lowercase
     * @throws NullPointerException if locale is null
     */
    public static Function<String, String> toLowerCase(@Nonnull Locale locale) {
        return new StringLocaleAwareLowerCaseFunction(locale);
    }

    /**
     * Returns a Function able to convert strings into their uppercase counterparts
     * using {@link String#toUpperCase()}.
     * 
     * <p>
     *   The returned function handles null by returning them.
     * </p>
     * 
     * @since 1.9
     * @return a function covnerting strings to uppercase
     */
    public static Function<String, String> toUpperCase() {
        return StringUpperCaseFunction.INSTANCE;
    }
    
    /**
     * Returns a Function able to convert strings into their uppercase counterparts
     * using {@link String#toUpperCase(Locale)}.
     * 
     * <p>
     *   The returned function handles null by returning them.
     * </p>
     * 
     * @since 1.9
     * @param locale the locale being used to convert to uppercase
     * @return a function converting strings to uppercase
     * @throws NullPointerException if locale is null
     */
    public static Function<String, String> toUpperCase(@Nonnull Locale locale) {
        return new StringLocaleAwareUpperCaseFunction(locale);
    }
    
    /**
     * Joines instances of a collection
     * into a single string instance,
     * using a parametrizable JoinWalker
     * which transforms instances of T into Strings.
     * 
     * Calling this method is equivalent to
     * {@code Strings.join(collection, " ", walker}
     * 
     * @deprecated use {@link Joiner}, {@link Function} and {@link Iterables#transform(Iterable, Function)} instead
     * 
     * @param <T> the generic type
     * @param collection the element provider
     * @param walker the function object which transform instances of T into strings
     * @throws NullPointerException if collection is null or collection is empty and walker is null
     * @return the joined collection as a string
     */
    @Deprecated
    public static <T> String join(Collection<? extends T> collection, JoinWalker<? super T> walker) {
        return Strings.join(collection, DEFAULT_DELIMITER, walker);
    }

    /**
     * Joines instances of a collection
     * into a single string instance,
     * using a parametrizable delimeter and
     * a JoinWalker which transforms instances
     * of T into Strings.
     * 
     * @deprecated use {@link Joiner}, {@link Function} and {@link Iterables#transform(Iterable, Function)} instead
     * 
     * @param <T> the generic type
     * @param collection the element provider
     * @param delimiter the string betweens joined elements of collection
     * @param walker the function object which transform instances of T into strings
     * @throws NullPointerException if collection is null or collection is empty and walker is null
     * @return the joined collection as a string
     */
    @Deprecated
    public static <T> String join(Collection<? extends T> collection, String delimiter, JoinWalker<? super T> walker) {
        final Iterable<String> transformed = Iterables.transform(collection, JoinWalkers.asFunction(walker));
        return Joiner.on(delimiter).useForNull("null").join(transformed);
    }
    
    /**
     * Checks whether a given {@link String} is a valid
     * number, containing only digits.
     * This implementation is performing a blank-check
     * before using {@link StringUtils#isNumeric(String)}.
     * 
     * <pre>
     * StringUtility.isNumeric(null)   = false
     * StringUtility.isNumeric("")     = false
     * StringUtility.isNumeric("  ")   = false
     * StringUtility.isNumeric("123")  = true
     * StringUtility.isNumeric("12 3") = false
     * StringUtility.isNumeric("ab2c") = false
     * StringUtility.isNumeric("12-3") = false
     * StringUtility.isNumeric("12.3") = false
     * </pre>
     * 
     * @param s the String to check, may be null
     * @return true if s is not blank and contains only digits
     */
    public static boolean isNumeric(String s) {
        return StringUtils.isNotBlank(s) && StringUtils.isNumeric(s);
    }
    
    /**
     * Checks whether s is blank according
     * to {@link StringUtils#isBlank(String)} and
     * returns null in this case.
     * 
     * <p>
     *   Using this method is equivalent to calling
     *   {@link Strings#defaultIfBlank(String, String)}
     *   with s and null.
     * </p>
     * 
     * @param s the string to check
     * @return null if s is blank, s otherwise
     */
    public static String defaultIfBlank(String s) {
        return Strings.defaultIfBlank(s, null);
    }
    
    /**
     * Checks whether s is blank according
     * to {@link StringUtils#isBlank(String)} and
     * returns the default value in this case.
     * 
     * @since 1.6
     * @param s the string to check
     * @param defaultValue the default value to return in case s is blank
     * @return defaultValue if s is blank, s otherwise
     */
    public static String defaultIfBlank(String s, String defaultValue) {
        return StringUtils.isBlank(s) ? defaultValue : s;
    }
    
    /**
     * Creates a {@link Rule} which evaluates to true
     * when passed in a string which contains the specified char sequence.
     * 
     * @since 1.6
     * @param s the char sequence which should be contained in the input
     * @return a rule which returns true when the given input contains s
     * @throws NullPointerException if s is null
     */
    public static Rule<String> contains(CharSequence s) {
        return new StringContainsRule(s);
    }
    
    /**
     * Creates a {@link Rule} which evaluates to true
     * when passed in a char sequence which is contained in the specified string.
     * 
     * @since 1.6
     * @param s the string which should contain the input
     * @return a rule which returns true when s contains the given char sequence
     * @throws NullPointerException if s is null
     */
    public static Rule<CharSequence> containedIn(String s) {
        return new StringContainedInRule(s);
    }
    
    /**
     * Ensures that a string passed as a parameter to the calling method is not empty.
     * 
     * @since 1.6
     * @param s the string being checked
     * @return s
     */
    public static String checkNotEmpty(String s) {
        return checkNotEmpty(s, "s must not be empty but was '%s'", s);
    }

    /**
     * Ensures that a string passed as a parameter to the calling method is not empty.
     * 
     * @since 1.6
     * @param s the string being checked
     * @param message the error message
     * @return s
     */
    public static String checkNotEmpty(String s, String message) {
        return checkNotEmpty(s, message, EMPTY_ARRAY);
    }

    /**
     * Ensures that a string passed as a parameter to the calling method is not empty.
     * 
     * @since 1.6
     * @param s the string being checked
     * @param message the error message
     * @param args the error message arguments
     * @return s
     */
    public static String checkNotEmpty(String s, String message, Object... args) {
        Preconditions.checkArgument(StringUtils.isNotEmpty(s), message, args);
        return s;
    }

    /**
     * Ensures that a string passed as a parameter to the calling method is not blank.
     * 
     * @since 1.6
     * @param s the string being checked
     * @return version of s
     */
    public static String checkNotBlank(String s) {
        return checkNotBlank(s, "s must not be blank but was '%s'", s);
    }

    /**
     * Ensures that a string passed as a parameter to the calling method is not blank.
     * 
     * @since 1.6
     * @param s the string being checked
     * @param message the error message
     * @return version of s
     */
    public static String checkNotBlank(String s, String message) {
        return checkNotBlank(s, message, EMPTY_ARRAY);
    }

    /**
     * Ensures that a string passed as a parameter to the calling method is not blank.
     * 
     * @since 1.6
     * @param s the string being checked
     * @param args the error message arguments
     * @param message the error message
     * @return version of s
     */
    public static String checkNotBlank(String s, String message, Object... args) {
        Preconditions.checkArgument(StringUtils.isNotBlank(s), message, args);
        return s;
    }
    
}
