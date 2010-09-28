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

import com.google.common.base.Preconditions;

import java.util.AbstractMap;
import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Abstract skeleton implementation of
 * the {@link UtilityMap} interface.
 * 
 * @author Willi Schoenborn
 *
 * @param <K> the type of keys maintained by this map
 * @param <V> the type of mapped values
 */
public abstract class AbstractUtilityMap<K, V> extends AbstractMap<K, V> implements Map<K, V>, UtilityMap<K, V> {

    private V checkAndGet(K key, String type) {
        Preconditions.checkArgument(containsKey(key), "No key named '%s' present for expected %s value", key, type);
        final V value = get(key);
        Preconditions.checkNotNull(value, "Required value for key '%s' is null, but should be of type %s", key, type);
        return value;
    }

    @Override
    public boolean getBoolean(K key) throws IllegalArgumentException {
        return Convert.intoBoolean(checkAndGet(key, "boolean"));
    }

    @Override
    public boolean getBoolean(K key, boolean defaultValue) {
        return Convert.intoBoolean(get(key), defaultValue);
    }
    
    @Override
    public int getInt(K key) throws IllegalArgumentException {
        return (int) getLong(key);
    }
    
    @Override
    public int getInt(K key, int defaultValue) {
        return (int) getLong(key, defaultValue);
    }

    @Override
    public long getLong(K key) throws IllegalArgumentException {
        return Convert.intoLong(checkAndGet(key, "long"));
    }

    @Override
    public long getLong(K key, long defaultValue) {
        return Convert.intoLong(get(key), defaultValue);
    }

    @Override
    public double getDouble(K key) throws IllegalArgumentException {
        return Convert.intoDouble(checkAndGet(key, "double"));
    }

    @Override
    public double getDouble(K key, double defaultValue) {
        return Convert.intoDouble(get(key), defaultValue);
    }

    @Override
    public Date getDate(K key) throws IllegalArgumentException {
        return Convert.intoDate(checkAndGet(key, "Date"));
    }

    @Override
    public Date getDate(K key, Date defaultValue) {
        return Convert.intoDate(get(key), defaultValue);
    }

    @Override
    public <T extends Enum<T>> T getEnum(K key, Class<T> enumType) throws IllegalArgumentException {
        return Convert.intoEnum(checkAndGet(key, "enum " + enumType.getName()), enumType);
    }

    @Override
    public <T extends Enum<T>> T getEnum(K key, Class<T> enumType, T defaultValue) {
        return Convert.intoEnum(get(key), enumType, defaultValue);
    }

    @Override
    public String getString(K key) throws IllegalArgumentException {
        return Convert.intoString(checkAndGet(key, "String"));
    }

    @Override
    public String getString(K key, String defaultValue) {
        return Convert.intoString(get(key), defaultValue);
    }

    @Override
    public Locale getLocale(K key) throws IllegalArgumentException {
        return Convert.intoLocale(checkAndGet(key, "Locale"));
    }

    @Override
    public Locale getLocale(K key, Locale defaultValue) {
        return Convert.intoLocale(get(key), defaultValue);
    }
    
    @Override
    public UtilityList<Object> getList(K key) throws IllegalArgumentException {
        return Convert.intoUtilityList(checkAndGet(key, "List"));
    }

    @Override
    public UtilityList<Object> getList(K key, UtilityList<Object> defaultValue) {
        return Convert.intoUtilityList(get(key), defaultValue);
    }

    @Override
    public UtilityMap<Object, Object> getMap(K key) throws IllegalArgumentException {
        return Convert.intoUtilityMap(checkAndGet(key, "Map"));
    }

    @Override
    public UtilityMap<Object, Object> getMap(K key, UtilityMap<Object, Object> defaultValue) {
        return Convert.intoUtilityMap(get(key), defaultValue);
    }

    @Override
    public abstract V put(K key, V value);
    
    @Override
    public abstract UtilitySet<Map.Entry<K, V>> entrySet();
    
    @Override
    public UtilitySet<K> keySet() {
        return Utility.asUtilitySet(super.keySet());
    }

    @Override
    public UtilityCollection<V> values() {
        return Utility.asUtilityCollection(super.values());
    }
    
}
