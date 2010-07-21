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

import java.util.AbstractList;
import java.util.Date;
import java.util.Locale;

/**
 * Abstract skeleton implementation of the {@link UtilityList} interface
 * delegating all conversion calls to {@link Convert}.
 * 
 * @author Willi Schoenborn
 *
 * @param <E> the generic element type
 */
public abstract class AbstractUtilityList<E> extends AbstractList<E> implements UtilityList<E> {

    @Override
    public boolean getBoolean(int index) throws IllegalArgumentException {
        return Convert.intoBoolean(get(index));
    }

    @Override
    public boolean getBoolean(int index, boolean defaultValue) {
        return Convert.intoBoolean(get(index), defaultValue);
    }
    
    @Override
    public int getInt(int index) throws IndexOutOfBoundsException {
        return (int) getLong(index);
    }
    
    @Override
    public int getInt(int index, int defaultValue) {
        return (int) getLong(index, defaultValue);
    }

    @Override
    public long getLong(int index) throws IllegalArgumentException {
        return Convert.intoLong(get(index));
    }

    @Override
    public long getLong(int index, long defaultValue) {
        return Convert.intoLong(get(index), defaultValue);
    }

    @Override
    public double getDouble(int index) throws IllegalArgumentException {
        return Convert.intoDouble(get(index));
    }

    @Override
    public double getDouble(int index, double defaultValue) {
        return Convert.intoDouble(get(index), defaultValue);
    }

    @Override
    public Date getDate(int index) throws IllegalArgumentException {
        return Convert.intoDate(get(index));
    }

    @Override
    public Date getDate(int index, Date defaultValue) {
        return Convert.intoDate(get(index), defaultValue);
    }

    @Override
    public <T extends Enum<T>> T getEnum(int index, Class<T> enumType) throws IllegalArgumentException {
        return Convert.intoEnum(get(index), enumType);
    }

    @Override
    public <T extends Enum<T>> T getEnum(int index, Class<T> enumType, T defaultValue) {
        return Convert.intoEnum(get(index), enumType, defaultValue);
    }

    @Override
    public String getString(int index) throws IllegalArgumentException {
        return Convert.intoString(get(index));
    }

    @Override
    public String getString(int index, String defaultValue) {
        return Convert.intoString(get(index), defaultValue);
    }
    
    @Override
    public Locale getLocale(int index) throws IllegalArgumentException {
        return Convert.intoLocale(get(index));
    }
    
    @Override
    public Locale getLocale(int index, Locale defaultValue) {
        return Convert.intoLocale(get(index), defaultValue);
    }

    @Override
    public UtilityList<Object> getList(int index) throws IllegalArgumentException {
        return Convert.intoUtilityList(get(index));
    }

    @Override
    public UtilityList<Object> getList(int index, UtilityList<Object> defaultValue) {
        return Convert.intoUtilityList(get(index), defaultValue);
    }

    @Override
    public UtilityMap<Object, Object> getMap(int index) throws IllegalArgumentException {
        return Convert.intoUtilityMap(get(index));
    }

    @Override
    public UtilityMap<Object, Object> getMap(int index, UtilityMap<Object, Object> defaultValue) {
        return Convert.intoUtilityMap(get(index), defaultValue);
    }

    @Override
    public abstract void add(int index, E element);
    
    @Override
    public abstract E set(int index, E element);
    
    @Override
    public abstract E remove(int index);
    
    @Override
    public UtilityIterator<E> iterator() {
        return Utility.asUtilityIterator(super.iterator()); 
    }
    
    @Override
    public UtilityListIterator<E> listIterator() {
        return Utility.asUtilityListIterator(super.listIterator());
    }
    
    @Override
    public UtilityListIterator<E> listIterator(int index) {
        return Utility.asUtilityListIterator(super.listIterator(index));
    }
    
}
