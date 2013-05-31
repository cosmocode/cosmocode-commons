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

import com.google.common.collect.ForwardingMap;

import java.util.Date;
import java.util.Locale;
import java.util.Map;

/**
 * Implementation of the {@link UtilityMap} interface
 * delegating all calls to an underlying {@link UtilityMap}.
 *
 * @author Willi Schoenborn
 * @param <K> the generic key type
 * @param <V> the generic value type
 */
public abstract class ForwardingUtilityMap<K, V> extends ForwardingMap<K, V> implements UtilityMap<K, V> {

    @Override
    protected abstract UtilityMap<K, V> delegate();

    @Override
    public UtilitySet<Map.Entry<K, V>> entrySet() {
        return delegate().entrySet();
    }

    @Override
    public boolean getBoolean(K key, boolean defaultValue) {
        return delegate().getBoolean(key, defaultValue);
    }

    @Override
    public boolean getBoolean(K key) throws IllegalArgumentException {
        return delegate().getBoolean(key);
    }

    @Override
    public Date getDate(K key, Date defaultValue) {
        return delegate().getDate(key, defaultValue);
    }

    @Override
    public Date getDate(K key) throws IllegalArgumentException {
        return delegate().getDate(key);
    }

    @Override
    public double getDouble(K key, double defaultValue) {
        return delegate().getDouble(key, defaultValue);
    }

    @Override
    public double getDouble(K key) throws IllegalArgumentException {
        return delegate().getDouble(key);
    }

    @Override
    public <T extends Enum<T>> T getEnum(K key, Class<T> enumType, T defaultValue) {
        return delegate().getEnum(key, enumType, defaultValue);
    }

    @Override
    public <T extends Enum<T>> T getEnum(K key, Class<T> enumType) throws IllegalArgumentException {
        return delegate().getEnum(key, enumType);
    }

    @Override
    public int getInt(K key) throws IllegalArgumentException {
        return delegate().getInt(key);
    }
    
    @Override
    public int getInt(K key, int defaultValue) {
        return delegate().getInt(key, defaultValue);
    }

    @Override
    public UtilityList<Object> getList(K key, UtilityList<Object> defaultValue) {
        return delegate().getList(key, defaultValue);
    }

    @Override
    public UtilityList<Object> getList(K key) throws IllegalArgumentException {
        return delegate().getList(key);
    }

    @Override
    public Locale getLocale(K key, Locale defaultValue) {
        return delegate().getLocale(key, defaultValue);
    }

    @Override
    public Locale getLocale(K key) throws IllegalArgumentException {
        return delegate().getLocale(key);
    }

    @Override
    public long getLong(K key, long defaultValue) {
        return delegate().getLong(key, defaultValue);
    }

    @Override
    public long getLong(K key) throws IllegalArgumentException {
        return delegate().getLong(key);
    }

    @Override
    public UtilityMap<Object, Object> getMap(K key, UtilityMap<Object, Object> defaultValue) {
        return delegate().getMap(key, defaultValue);
    }

    @Override
    public UtilityMap<Object, Object> getMap(K key) throws IllegalArgumentException {
        return delegate().getMap(key);
    }

    @Override
    public String getString(K key, String defaultValue) {
        return delegate().getString(key, defaultValue);
    }

    @Override
    public String getString(K key) throws IllegalArgumentException {
        return delegate().getString(key);
    }

    @Override
    public UtilitySet<K> keySet() {
        return delegate().keySet();
    }

    @Override
    public UtilityCollection<V> values() {
        return delegate().values();
    }
    
}
