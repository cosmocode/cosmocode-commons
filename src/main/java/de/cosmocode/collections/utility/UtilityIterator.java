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
import java.util.Iterator;
import java.util.Locale;

/**
 * An {@link Iterator} which is able to convert
 * elements into another type.
 *
 * @author Willi Schoenborn
 * @param <E> the generic element type
 */
public interface UtilityIterator<E> extends Iterator<E> {

    /**
     * Converts the return value of {@link Iterator#next()}
     * into a boolean.
     * 
     * @return the converted boolean value
     * @throws IllegalArgumentException if conversion failed
     */
    boolean nextBoolean() throws IllegalArgumentException;
    
    /**
     * Converts the the return value of {@link Iterator#next()}
     * into a boolean.
     * 
     * @param defaultValue the default value
     * @return the converted boolean value or the default value
     *         if conversion failed
     */
    boolean nextBoolean(boolean defaultValue);

    /**
     * Converts the return value of {@link Iterator#next()}
     * into an int.
     * 
     * @return the converted int value
     * @throws IllegalArgumentException if conversion failed
     */
    int nextInt() throws IllegalArgumentException;

    /**
     * Converts the the return value of {@link Iterator#next()}
     * into an int.
     * 
     * @param defaultValue the default value
     * @return the converted int value or the default value
     *         if conversion failed
     */
    int nextInt(int defaultValue);

    /**
     * Converts the return value of {@link Iterator#next()}
     * into a long.
     * 
     * @return the converted long value
     * @throws IllegalArgumentException if conversion failed
     */
    long nextLong() throws IllegalArgumentException;

    /**
     * Converts the the return value of {@link Iterator#next()}
     * into a long.
     * 
     * @param defaultValue the default value
     * @return the converted long value or the default value
     *         if conversion failed
     */
    long nextLong(long defaultValue);

    /**
     * Converts the return value of {@link Iterator#next()}
     * into a double.
     * 
     * @return the converted double value
     * @throws IllegalArgumentException if conversion failed
     */
    double nextDouble() throws IllegalArgumentException;

    /**
     * Converts the the return value of {@link Iterator#next()}
     * into a double.
     * 
     * @param defaultValue the default value
     * @return the converted double value or the default value
     *         if conversion failed
     */
    double nextDouble(double defaultValue);

    /**
     * Converts the return value of {@link Iterator#next()}
     * into a {@link Date}.
     * 
     * @return the converted {@link Date} value
     * @throws IllegalArgumentException if conversion failed
     */
    Date nextDate() throws IllegalArgumentException;

    /**
     * Converts the the return value of {@link Iterator#next()}
     * into a {@link Date}.
     * 
     * @param defaultValue the default value
     * @return the converted {@link Date} value or the default value
     *         if conversion failed
     */
    Date nextDate(Date defaultValue);

    /**
     * Converts the return value of {@link Iterator#next()}
     * into an {@link Enum}.
     * 
     * @param <T> the generic enum type
     * @param enumType the target enum type
     * @return the converted {@link Enum} value
     * @throws IllegalArgumentException if conversion failed
     */
    <T extends Enum<T>> T nextEnum(Class<T> enumType) throws IllegalArgumentException;
    
    /**
     * Converts the the return value of {@link Iterator#next()}
     * into an {@link Enum}.
     * 
     * @param <T> the generic enum type
     * @param enumType the target enum type
     * @param defaultValue the default value
     * @return the converted {@link Enum} value or the default value
     *         if conversion failed
     */
    <T extends Enum<T>> T nextEnum(Class<T> enumType, T defaultValue);

    /**
     * Converts the return value of {@link Iterator#next()}
     * into a {@link String}.
     * 
     * @return the converted {@link String} value
     * @throws IllegalArgumentException if conversion failed
     */
    String nextString() throws IllegalArgumentException;

    /**
     * Converts the the return value of {@link Iterator#next()}
     * into a {@link String}.
     * 
     * @param defaultValue the default value
     * @return the converted {@link String} value or the default value
     *         if conversion failed
     */
    String nextString(String defaultValue);

    /**
     * Converts the return value of {@link Iterator#next()}
     * into a {@link Locale}.
     * 
     * @return the converted {@link Locale} value
     * @throws IllegalArgumentException if conversion failed
     */
    Locale nextLocale() throws IllegalArgumentException;

    /**
     * Converts the the return value of {@link Iterator#next()}
     * into a {@link Locale}.
     * 
     * @param defaultValue the default value
     * @return the converted {@link Locale} value or the default value
     *         if conversion failed
     */
    Locale nextLocale(Locale defaultValue);

    /**
     * Converts the return value of {@link Iterator#next()}
     * into a {@link UtilityList}.
     * 
     * @return the converted {@link UtilityList} value
     * @throws IllegalArgumentException if conversion failed
     */
    UtilityList<Object> nextList() throws IllegalArgumentException;

    /**
     * Converts the the return value of {@link Iterator#next()}
     * into a {@link UtilityList}.
     * 
     * @param defaultValue the default value
     * @return the converted {@link UtilityList} value or the default value
     *         if conversion failed
     */
    UtilityList<Object> nextList(UtilityList<Object> defaultValue);

    /**
     * Converts the return value of {@link Iterator#next()}
     * into a {@link UtilityMap}.
     * 
     * @return the converted {@link UtilityMap} value
     * @throws IllegalArgumentException if conversion failed
     */
    UtilityMap<Object, Object> nextMap() throws IllegalArgumentException;

    /**
     * Converts the the return value of {@link Iterator#next()}
     * into a {@link UtilityMap}.
     * 
     * @param defaultValue the default value
     * @return the converted {@link UtilityMap} value or the default value
     *         if conversion failed
     */
    UtilityMap<Object, Object> nextMap(UtilityMap<Object, Object> defaultValue);

}
