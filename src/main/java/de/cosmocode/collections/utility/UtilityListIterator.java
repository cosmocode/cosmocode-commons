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

package de.cosmocode.collections.utility;

import java.util.Date;
import java.util.ListIterator;
import java.util.Locale;

/**
 * A {@link ListIterator} which is able to convert
 * elements into another type.
 *
 * @author Willi Schoenborn
 * @param <E> the generic element type
 */
public interface UtilityListIterator<E> extends UtilityIterator<E>, ListIterator<E> {

    /**
     * Converts the return value of {@link ListIterator#previous()}
     * into a boolean.
     * 
     * @return the converted boolean value
     * @throws IllegalArgumentException if conversion failed
     */
    boolean previousBoolean() throws IllegalArgumentException;
    
    /**
     * Converts the the return value of {@link ListIterator#previous()}
     * into a boolean.
     * 
     * @param defaultValue the default value
     * @return the converted boolean value or the default value
     *         if conversion failed
     */
    boolean previousBoolean(boolean defaultValue);

    /**
     * Converts the return value of {@link ListIterator#previous()}
     * into an int.
     * 
     * @return the converted int value
     * @throws IllegalArgumentException if conversion failed
     */
    int previousInt() throws IllegalArgumentException;

    /**
     * Converts the the return value of {@link ListIterator#previous()}
     * into an int.
     * 
     * @param defaultValue the default value
     * @return the converted int value or the default value
     *         if conversion failed
     */
    int previousInt(int defaultValue);

    /**
     * Converts the return value of {@link ListIterator#previous()}
     * into a long.
     * 
     * @return the converted long value
     * @throws IllegalArgumentException if conversion failed
     */
    long previousLong() throws IllegalArgumentException;

    /**
     * Converts the the return value of {@link ListIterator#previous()}
     * into a long.
     * 
     * @param defaultValue the default value
     * @return the converted long value or the default value
     *         if conversion failed
     */
    long previousLong(long defaultValue);

    /**
     * Converts the return value of {@link ListIterator#previous()}
     * into a double.
     * 
     * @return the converted double value
     * @throws IllegalArgumentException if conversion failed
     */
    double previousDouble() throws IllegalArgumentException;

    /**
     * Converts the the return value of {@link ListIterator#previous()}
     * into a double.
     * 
     * @param defaultValue the default value
     * @return the converted double value or the default value
     *         if conversion failed
     */
    double previousDouble(double defaultValue);

    /**
     * Converts the return value of {@link ListIterator#previous()}
     * into a {@link Date}.
     * 
     * @return the converted {@link Date} value
     * @throws IllegalArgumentException if conversion failed
     */
    Date previousDate() throws IllegalArgumentException;

    /**
     * Converts the the return value of {@link ListIterator#previous()}
     * into a {@link Date}.
     * 
     * @param defaultValue the default value
     * @return the converted {@link Date} value or the default value
     *         if conversion failed
     */
    Date previousDate(Date defaultValue);

    /**
     * Converts the return value of {@link ListIterator#previous()}
     * into an {@link Enum}.
     * 
     * @param <T> the generic enum type
     * @param enumType the target enum type
     * @return the converted {@link Enum} value
     * @throws IllegalArgumentException if conversion failed
     */
    <T extends Enum<T>> T previousEnum(Class<T> enumType) throws IllegalArgumentException;
    
    /**
     * Converts the the return value of {@link ListIterator#previous()}
     * into an {@link Enum}.
     * 
     * @param <T> the generic enum type
     * @param enumType the target enum type
     * @param defaultValue the default value
     * @return the converted {@link Enum} value or the default value
     *         if conversion failed
     */
    <T extends Enum<T>> T previousEnum(Class<T> enumType, T defaultValue);

    /**
     * Converts the return value of {@link ListIterator#previous()}
     * into a {@link String}.
     * 
     * @return the converted {@link String} value
     * @throws IllegalArgumentException if conversion failed
     */
    String previousString() throws IllegalArgumentException;

    /**
     * Converts the the return value of {@link ListIterator#previous()}
     * into a {@link String}.
     * 
     * @param defaultValue the default value
     * @return the converted {@link String} value or the default value
     *         if conversion failed
     */
    String previousString(String defaultValue);

    /**
     * Converts the return value of {@link ListIterator#previous()}
     * into a {@link Locale}.
     * 
     * @return the converted {@link Locale} value
     * @throws IllegalArgumentException if conversion failed
     */
    Locale previousLocale() throws IllegalArgumentException;

    /**
     * Converts the the return value of {@link ListIterator#previous()}
     * into a {@link Locale}.
     * 
     * @param defaultValue the default value
     * @return the converted {@link Locale} value or the default value
     *         if conversion failed
     */
    Locale previousLocale(Locale defaultValue);

    /**
     * Converts the return value of {@link ListIterator#previous()}
     * into a {@link UtilityList}.
     * 
     * @return the converted {@link UtilityList} value
     * @throws IllegalArgumentException if conversion failed
     */
    UtilityList<Object> previousList() throws IllegalArgumentException;

    /**
     * Converts the the return value of {@link ListIterator#previous()}
     * into a {@link UtilityList}.
     * 
     * @param defaultValue the default value
     * @return the converted {@link UtilityList} value or the default value
     *         if conversion failed
     */
    UtilityList<Object> previousList(UtilityList<Object> defaultValue);

    /**
     * Converts the return value of {@link ListIterator#previous()}
     * into a {@link UtilityMap}.
     * 
     * @return the converted {@link UtilityMap} value
     * @throws IllegalArgumentException if conversion failed
     */
    UtilityMap<Object, Object> previousMap() throws IllegalArgumentException;

    /**
     * Converts the the return value of {@link ListIterator#previous()}
     * into a {@link UtilityMap}.
     * 
     * @param defaultValue the default value
     * @return the converted {@link UtilityMap} value or the default value
     *         if conversion failed
     */
    UtilityMap<Object, Object> previousMap(UtilityMap<Object, Object> defaultValue);
    
}
