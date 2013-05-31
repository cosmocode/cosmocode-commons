/**
 * Copyright 2010 - 2013 CosmoCode GmbH
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
import java.util.List;
import java.util.Locale;

/**
 * A {@link List} which is able to convert
 * elements into another type.
 *
 * @author Willi Schoenborn
 * @param <E> the generic element type
 */
public interface UtilityList<E> extends UtilityCollection<E>, List<E> {

    /**
     * Converts the return value of {@link List#get(int)}
     * into a boolean.
     * 
     * @param index index of the element to return
     * @return the converted boolean value
     * @throws IllegalArgumentException if conversion failed
     */
    boolean getBoolean(int index) throws IllegalArgumentException;
    
    /**
     * Converts the return value of {@link List#get(int)}
     * into a boolean.
     * 
     * @param index index of the element to return
     * @param defaultValue the default value
     * @return the converted boolean value or the default value
     *         if conversion failed
     */
    boolean getBoolean(int index, boolean defaultValue);

    /**
     * Converts the return value of {@link List#get(int)}
     * into an int.
     * 
     * @param index index of the element to return
     * @return the converted int value
     * @throws IllegalArgumentException if conversion failed
     */
    int getInt(int index) throws IllegalArgumentException;

    /**
     * Converts the return value of {@link List#get(int)}
     * into an int.
     * 
     * @param index index of the element to return
     * @param defaultValue the default value
     * @return the converted int value or the default value
     *         if conversion failed
     */
    int getInt(int index, int defaultValue);

    /**
     * Converts the return value of {@link List#get(int)}
     * into a long.
     * 
     * @param index index of the element to return
     * @return the converted long value
     * @throws IllegalArgumentException if conversion failed
     */
    long getLong(int index) throws IllegalArgumentException;

    /**
     * Converts the return value of {@link List#get(int)}
     * into a long.
     * 
     * @param index index of the element to return
     * @param defaultValue the default value
     * @return the converted long value or the default value
     *         if conversion failed
     */
    long getLong(int index, long defaultValue);

    /**
     * Converts the return value of {@link List#get(int)}
     * into a double.
     * 
     * @param index index of the element to return
     * @return the converted double value
     * @throws IllegalArgumentException if conversion failed
     */
    double getDouble(int index) throws IllegalArgumentException;

    /**
     * Converts the return value of {@link List#get(int)}
     * into a double.
     * 
     * @param index index of the element to return
     * @param defaultValue the default value
     * @return the converted double value or the default value
     *         if conversion failed
     */
    double getDouble(int index, double defaultValue);

    /**
     * Converts the return value of {@link List#get(int)}
     * into a {@link Date}.
     * 
     * @param index index of the element to return
     * @return the converted {@link Date} value
     * @throws IllegalArgumentException if conversion failed
     */
    Date getDate(int index) throws IllegalArgumentException;

    /**
     * Converts the return value of {@link List#get(int)}
     * into a {@link Date}.
     * 
     * @param index index of the element to return
     * @param defaultValue the default value
     * @return the converted {@link Date} value or the default value
     *         if conversion failed
     */
    Date getDate(int index, Date defaultValue);
    
    /**
     * Converts the return value of {@link List#get(int)}
     * into an {@link Enum}.
     * 
     * @param <T> the generic enum type
     * @param index index of the element to return
     * @param enumType the target enum type
     * @return the converted {@link Enum} value
     * @throws IllegalArgumentException if conversion failed
     */
    <T extends Enum<T>> T getEnum(int index, Class<T> enumType) throws IllegalArgumentException;
    
    /**
     * Converts the return value of {@link List#get(int)}
     * into an {@link Enum}.
     * 
     * @param <T> the generic enum type
     * @param index index of the element to return
     * @param enumType the target enum type
     * @param defaultValue the default value
     * @return the converted {@link Enum} value
     */
    <T extends Enum<T>> T getEnum(int index, Class<T> enumType, T defaultValue);

    /**
     * Converts the return value of {@link List#get(int)}
     * into a {@link String}.
     * 
     * @param index index of the element to return
     * @return the converted {@link String} value
     * @throws IllegalArgumentException if conversion failed
     */
    String getString(int index) throws IllegalArgumentException;

    /**
     * Converts the return value of {@link List#get(int)}
     * into a {@link String}.
     * 
     * @param index index of the element to return
     * @param defaultValue the default value
     * @return the converted {@link String} value or the default value
     *         if conversion failed
     */
    String getString(int index, String defaultValue);

    /**
     * Converts the return value of {@link List#get(int)}
     * into a {@link Locale}.
     * 
     * @param index index of the element to return
     * @return the converted {@link Locale} value
     * @throws IllegalArgumentException if conversion failed
     */
    Locale getLocale(int index) throws IllegalArgumentException;

    /**
     * Converts the return value of {@link List#get(int)}
     * into a {@link Locale}.
     * 
     * @param index index of the element to return
     * @param defaultValue the default value
     * @return the converted {@link Locale} value or the default value
     *         if conversion failed
     */
    Locale getLocale(int index, Locale defaultValue);

    /**
     * Converts the return value of {@link List#get(int)}
     * into a {@link UtilityList}.
     * 
     * @param index index of the element to return
     * @return the converted {@link UtilityList} value
     * @throws IllegalArgumentException if conversion failed
     */
    UtilityList<Object> getList(int index) throws IllegalArgumentException;

    /**
     * Converts the return value of {@link List#get(int)}
     * into a {@link UtilityList}.
     * 
     * @param index index of the element to return
     * @param defaultValue the default value
     * @return the converted {@link UtilityList} value or the default value
     *         if conversion failed
     */
    UtilityList<Object> getList(int index, UtilityList<Object> defaultValue);

    /**
     * Converts the return value of {@link List#get(int)}
     * into a {@link UtilityMap}.
     * 
     * @param index index of the element to return
     * @return the converted {@link UtilityMap} value
     * @throws IllegalArgumentException if conversion failed
     */
    UtilityMap<Object, Object> getMap(int index) throws IllegalArgumentException;

    /**
     * Converts the return value of {@link List#get(int)}
     * into a {@link UtilityMap}.
     * 
     * @param index index of the element to return
     * @param defaultValue the default value
     * @return the converted {@link UtilityMap} value or the default value
     *         if conversion failed
     */
    UtilityMap<Object, Object> getMap(int index, UtilityMap<Object, Object> defaultValue);
    
    @Override
    UtilityIterator<E> iterator();
    
    @Override
    UtilityListIterator<E> listIterator();
    
    @Override
    UtilityListIterator<E> listIterator(int index);
    
}
