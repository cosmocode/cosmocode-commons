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

import com.google.common.collect.ForwardingList;

/**
 * Implementation of the {@link UtilityList} interface
 * delegating all calls to an underlying {@link UtilityList}.
 *
 * @author Willi Schoenborn
 * @param <E> the generic element type
 */
public abstract class ForwardingUtilityList<E> extends ForwardingList<E> implements UtilityList<E> {

    @Override
    protected abstract UtilityList<E> delegate();

    @Override
    public boolean getBoolean(int index, boolean defaultValue) {
        return delegate().getBoolean(index, defaultValue);
    }

    @Override
    public boolean getBoolean(int index) throws IndexOutOfBoundsException {
        return delegate().getBoolean(index);
    }

    @Override
    public Date getDate(int index, Date defaultValue) {
        return delegate().getDate(index, defaultValue);
    }

    @Override
    public Date getDate(int index) throws IndexOutOfBoundsException {
        return delegate().getDate(index);
    }

    @Override
    public double getDouble(int index, double defaultValue) {
        return delegate().getDouble(index, defaultValue);
    }

    @Override
    public double getDouble(int index) throws IndexOutOfBoundsException {
        return delegate().getDouble(index);
    }

    @Override
    public <T extends Enum<T>> T getEnum(int index, Class<T> enumType, T defaultValue) {
        return delegate().getEnum(index, enumType, defaultValue);
    }

    @Override
    public <T extends Enum<T>> T getEnum(int index, Class<T> enumType) throws IndexOutOfBoundsException {
        return delegate().getEnum(index, enumType);
    }

    @Override
    public UtilityList<Object> getList(int index, UtilityList<Object> defaultValue) {
        return delegate().getList(index, defaultValue);
    }

    @Override
    public UtilityList<Object> getList(int index) throws IllegalArgumentException {
        return delegate().getList(index);
    }

    @Override
    public long getLong(int index, long defaultValue) {
        return delegate().getLong(index, defaultValue);
    }

    @Override
    public long getLong(int index) throws IndexOutOfBoundsException {
        return delegate().getLong(index);
    }

    @Override
    public UtilityMap<Object, Object> getMap(int index, UtilityMap<Object, Object> defaultValue) {
        return delegate().getMap(index, defaultValue);
    }

    @Override
    public UtilityMap<Object, Object> getMap(int index) throws IllegalArgumentException {
        return delegate().getMap(index);
    }

    @Override
    public String getString(int index, String defaultValue) {
        return delegate().getString(index, defaultValue);
    }

    @Override
    public String getString(int index) throws IndexOutOfBoundsException {
        return delegate().getString(index);
    }

    @Override
    public abstract UtilityIterator<E> iterator();
    
    @Override
    public abstract UtilityListIterator<E> listIterator();
    
    @Override
    public abstract UtilityListIterator<E> listIterator(int index);
    
}
