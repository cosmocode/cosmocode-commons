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
import java.util.Locale;
import java.util.Map;

/**
 * A {@link Map} which is able to convert
 * elements into another type.
 * 
 * @author Willi Schoenborn
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public interface UtilityMap<K, V> extends Map<K, V> {

    /**
     * Converts the return value of {@link Map#get(Object)}
     * into a boolean.
     * 
     * @param key the key whose associated value is to be returned
     * @return the converted boolean value
     * @throws IllegalArgumentException if conversion failed
     */
    boolean getBoolean(K key) throws IllegalArgumentException;
    
    /**
     * Converts the return value of {@link Map#get(Object)}
     * into a boolean.
     * 
     * @param key the key whose associated value is to be returned
     * @param defaultValue the default value
     * @return the converted boolean value or the default value
     *         if conversion failed
     */
    boolean getBoolean(K key, boolean defaultValue);

    /**
     * Converts the return value of {@link Map#get(Object)}
     * into an int.
     * 
     * @param key the key whose associated value is to be returned
     * @return the converted int value
     * @throws IllegalArgumentException if conversion failed
     */
    int getInt(K key) throws IllegalArgumentException;

    /**
     * Converts the return value of {@link Map#get(Object)}
     * into a int.
     * 
     * @param key the key whose associated value is to be returned
     * @param defaultValue the default value
     * @return the converted int value or the default value
     *         if conversion failed
     */
    int getInt(K key, int defaultValue);

    /**
     * Converts the return value of {@link Map#get(Object)}
     * into a long.
     * 
     * @param key the key whose associated value is to be returned
     * @return the converted long value
     * @throws IllegalArgumentException if conversion failed
     */
    long getLong(K key) throws IllegalArgumentException;

    /**
     * Converts the return value of {@link Map#get(Object)}
     * into a long.
     * 
     * @param key the key whose associated value is to be returned
     * @param defaultValue the default value
     * @return the converted long value or the default value
     *         if conversion failed
     */
    long getLong(K key, long defaultValue);

    /**
     * Converts the return value of {@link Map#get(Object)}
     * into a double.
     * 
     * @param key the key whose associated value is to be returned
     * @return the converted double value
     * @throws IllegalArgumentException if conversion failed
     */
    double getDouble(K key) throws IllegalArgumentException;

    /**
     * Converts the return value of {@link Map#get(Object)}
     * into a double.
     * 
     * @param key the key whose associated value is to be returned
     * @param defaultValue the default value
     * @return the converted double value or the default value
     *         if conversion failed
     */
    double getDouble(K key, double defaultValue);

    /**
     * Converts the return value of {@link Map#get(Object)}
     * into a {@link Date}.
     * 
     * @param key the key whose associated value is to be returned
     * @return the converted {@link Date} value
     * @throws IllegalArgumentException if conversion failed
     */
    Date getDate(K key) throws IllegalArgumentException;

    /**
     * Converts the return value of {@link Map#get(Object)}
     * into a {@link Date}.
     * 
     * @param key the key whose associated value is to be returned
     * @param defaultValue the default value
     * @return the converted {@link Date} value or the default value
     *         if conversion failed
     */
    Date getDate(K key, Date defaultValue);
    
    /**
     * Converts the return value of {@link Map#get(Object)}
     * into an {@link Enum}.
     * 
     * @param <T> the generic enum type
     * @param key the key whose associated value is to be returned
     * @param enumType the target enum type
     * @return the converted {@link Enum} value
     * @throws IllegalArgumentException if conversion failed
     */
    <T extends Enum<T>> T getEnum(K key, Class<T> enumType) throws IllegalArgumentException;
    
    /**
     * Converts the return value of {@link Map#get(Object)}
     * into an {@link Enum}.
     * 
     * @param <T> the generic enum type
     * @param key the key whose associated value is to be returned
     * @param enumType the target enum type
     * @param defaultValue the default value
     * @return the converted {@link Enum} value or the default value
     *         if conversion failed
     */
    <T extends Enum<T>> T getEnum(K key, Class<T> enumType, T defaultValue);

    /**
     * Converts the return value of {@link Map#get(Object)}
     * into a {@link String}.
     * 
     * @param key the key whose associated value is to be returned
     * @return the converted {@link String} value
     * @throws IllegalArgumentException if conversion failed
     */
    String getString(K key) throws IllegalArgumentException;

    /**
     * Converts the return value of {@link Map#get(Object)}
     * into a {@link String}.
     * 
     * @param key the key whose associated value is to be returned
     * @param defaultValue the default value
     * @return the converted {@link String} value or the default value
     *         if conversion failed
     */
    String getString(K key, String defaultValue);

    /**
     * Converts the return value of {@link Map#get(Object)}
     * into a {@link Locale}.
     * 
     * @param key the key whose associated value is to be returned
     * @return the converted {@link Locale} value
     * @throws IllegalArgumentException if conversion failed
     */
    Locale getLocale(K key) throws IllegalArgumentException;

    /**
     * Converts the return value of {@link Map#get(Object)}
     * into a {@link Locale}.
     * 
     * @param key the key whose associated value is to be returned
     * @param defaultValue the default value
     * @return the converted {@link Locale} value or the default value
     *         if conversion failed
     */
    Locale getLocale(K key, Locale defaultValue);

    /**
     * Converts the return value of {@link Map#get(Object)}
     * into a {@link UtilityList}.
     * 
     * @param key the key whose associated value is to be returned
     * @return the converted {@link UtilityList} value
     * @throws IllegalArgumentException if conversion failed
     */
    UtilityList<Object> getList(K key) throws IllegalArgumentException;

    /**
     * Converts the return value of {@link Map#get(Object)}
     * into a {@link UtilityList}.
     * 
     * @param key the key whose associated value is to be returned
     * @param defaultValue the default value
     * @return the converted {@link UtilityList} value or the default value
     *         if conversion failed
     */
    UtilityList<Object> getList(K key, UtilityList<Object> defaultValue);

    /**
     * Converts the return value of {@link Map#get(Object)}
     * into a {@link UtilityMap}.
     * 
     * @param key the key whose associated value is to be returned
     * @return the converted {@link UtilityMap} value
     * @throws IllegalArgumentException if conversion failed
     */
    UtilityMap<Object, Object> getMap(K key) throws IllegalArgumentException;

    /**
     * Converts the return value of {@link Map#get(Object)}
     * into a {@link UtilityMap}.
     * 
     * @param key the key whose associated value is to be returned
     * @param defaultValue the default value
     * @return the converted {@link UtilityMap} value or the default value
     *         if conversion failed
     */
    UtilityMap<Object, Object> getMap(K key, UtilityMap<Object, Object> defaultValue);
    
    @Override
    UtilitySet<Map.Entry<K, V>> entrySet();
    
    @Override
    UtilitySet<K> keySet();
    
    @Override
    UtilityCollection<V> values();
    
}
