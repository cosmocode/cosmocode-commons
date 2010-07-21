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

package de.cosmocode.collections.compare;

import java.text.Collator;
import java.util.Comparator;
import java.util.Locale;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.collect.Ordering;
import com.google.common.primitives.Booleans;
import com.google.common.primitives.Ints;

import de.cosmocode.commons.Orderings;
import de.cosmocode.commons.Strings;
import de.cosmocode.commons.TrimMode;

/**
 * Utility class providing factory methods
 * for decorating comparators.
 * 
 * @deprecated use {@link Ordering} and {@link Orderings} instead
 * @author Willi Schoenborn
 */
@Deprecated
public final class Comparators {

    /**
     * Natural ordering on {@link String}s which trims comparee
     * first using {@link TrimMode#NULL} and puts nulls to the end.
     * 
     * @deprecated use {@link Strings#CASE_SENSITIVE}
     */
    @Deprecated
    public static final Ordering<String> CASE_SENSITIVE = Strings.CASE_SENSITIVE;
    
    /**
     * A case-insensitive version of {@link Comparators#CASE_SENSITIVE}.
     * 
     * @deprecated use {@link Strings#CASE_INSENSITIVE}
     */
    @Deprecated
    public static final Ordering<String> CASE_INSENSITIVE = Strings.CASE_INSENSITIVE;
    
    private static final Logger LOG = LoggerFactory.getLogger(Comparators.class);
    
    /**
     * Prevent instantiation.
     */
    private Comparators() {
        
    }
    
    /**
     * Creates an {@link Ordering} which uses a {@link Collator} based
     * on the given {@link Locale} to sort Strings.
     * 
     * @deprecated {@link Strings#orderBy(Locale)} instead
     * @param locale the desired locale
     * @return a new {@link Ordering} backed by a {@link Collator}
     */
    @Deprecated
    public static Ordering<String> collator(Locale locale) {
        return Strings.orderBy(locale);
    }
    
    /**
     * Creates a shuffling comparator which returns a
     * random int between -1 and 1 (both inclusive)
     * on every call of the {@link Comparator#compare(Object, Object)}
     * method call.
     * 
     * @deprecated use {@link Orderings#shuffle()} instead
     * @param <E> the element type of the given comparator
     * @return a comparator which returns randomly generated results
     */
    @Deprecated
    @SuppressWarnings("unchecked")
    public static <E> Ordering<E> shuffle() {
        return (Ordering<E>) Orderings.random();
    }
    
    /**
     * Makes a comparator trimming his string comparees before
     * actually comparing them. A trimming
     * string comparator is using the given
     * comparator's compare method with a trimmed
     * version (or null value in case comparee is null) of
     * the given string values.
     * 
     * <p>
     *   It's equivalent to calling {@link Comparators#trimming(Comparator, TrimMode)}
     *   using {@link TrimMode#NORMAL}.
     * </p>
     * 
     * @deprecated use {@link Ordering#onResultOf(com.google.common.base.Function)} instead
     * @param comparator the comparator to rely on
     * @throws NullPointerException if comparator is null
     * @return a trimming version of the given comparator
     */
    @Deprecated
    public static Ordering<String> trimming(Comparator<String> comparator) {
        return Comparators.trimming(comparator, TrimMode.NORMAL);
    }

    /**
     * Makes a comparator nulling its string comparees before
     * actually comparing them. A nulling
     * string comparator is using the given
     * comparator's compare method with a trimmed
     * version (or null value in case comparee is null) of
     * the given string values.
     * The comparator converts empty strings (after trimming) to null.
     * 
     * It's usually used in combination with {@link Ordering#nullsFirst()} or
     * {@link Ordering#nullsLast()} respectively.
     * 
     * <p>
     *   It's equivalent to calling {@link Comparators#trimming(Comparator, TrimMode)} 
     *   using {@link TrimMode#NULL}.
     * </p>
     * 
     * @deprecated use {@link Ordering#onResultOf(com.google.common.base.Function)} instead
     * @param comparator the comparator to rely on
     * @return a trimming version of the given comparator
     * @throws NullPointerException if comparator is null
     */
    @Deprecated
    public static Ordering<String> nulling(Comparator<String> comparator) {
        return Comparators.trimming(comparator, TrimMode.NULL);
    }
    
    /**
     * Makes a comparator trimming his stirng comparees before
     * actually comparing them.
     * 
     * @deprecated use {@link Ordering#onResultOf(com.google.common.base.Function)} instead
     * @param comparator the comparator to rely on
     * @param trimMode the {@link TrimMode} to use for trim operations
     * @return a trimming version of the given comparator
     * @throws NullPointerException if comparator is null
     */
    @Deprecated
    public static Ordering<String> trimming(Comparator<String> comparator, TrimMode trimMode) {
        LOG.debug("Making {} trimming using {}", comparator, trimMode);
        return Ordering.from(comparator).onResultOf(trimMode);
    }

    /**
     * Compares two boolean values.
     * True comes before false.
     * See also {@link Boolean#compareTo(Boolean)}
     * 
     * @deprecated use {@link Booleans#compare(boolean, boolean)} instead
     * @param b1 first boolean value
     * @param b2 second boolean value
     * @return zero if b1 represents the same boolean value as b2;
     *         a positive value if b1 represents true
     *         and b2 represents false; and a negative value if
     *         b1 represents false and the b2 true
     */
    @Deprecated
    public static int compare(boolean b1, boolean b2) {
        return Boolean.valueOf(b1).compareTo(Boolean.valueOf(b2));
    }
    
    /**
     * Compares two int values.
     * Low value comes before high ones.
     * See also {@link Integer#compareTo(Integer)}
     * 
     * @deprecated use {@link Ints#compare(int, int)} instead
     * @param i1 first int value
     * @param i2 second int value
     * @return the difference of the two arguments
     */
    @Deprecated
    public static int compare(int i1, int i2) {
        return i1 - i2;
    }
    
}
