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

/**
 * Implementation of the {@link UtilityListIterator} interface
 * delegating all calls to an underlying {@link UtilityListIterator}.
 *
 * @author Willi Schoenborn
 * @param <E> the generic element type
 */
public abstract class ForwardingUtilityListIterator<E> extends ForwardingUtilityIterator<E> 
    implements UtilityListIterator<E> {

    @Override
    protected abstract UtilityListIterator<E> delegate();

    @Override
    public boolean previousBoolean() throws IllegalArgumentException {
        return delegate().previousBoolean();
    }

    @Override
    public boolean previousBoolean(boolean defaultValue) {
        return delegate().previousBoolean(defaultValue);
    }

    @Override
    public Date previousDate() throws IllegalArgumentException {
        return delegate().previousDate();
    }

    @Override
    public Date previousDate(Date defaultValue) {
        return delegate().previousDate(defaultValue);
    }

    @Override
    public double previousDouble() throws IllegalArgumentException {
        return delegate().previousDouble();
    }

    @Override
    public double previousDouble(double defaultValue) {
        return delegate().previousDouble(defaultValue);
    }

    @Override
    public <T extends Enum<T>> T previousEnum(Class<T> enumType, T defaultValue) {
        return delegate().previousEnum(enumType, defaultValue);
    }

    @Override
    public <T extends Enum<T>> T previousEnum(Class<T> enumType) throws IllegalArgumentException {
        return delegate().previousEnum(enumType);
    }

    @Override
    public int previousIndex() {
        return delegate().previousIndex();
    }

    @Override
    public UtilityList<Object> previousList() throws IllegalArgumentException {
        return delegate().previousList();
    }

    @Override
    public UtilityList<Object> previousList(UtilityList<Object> defaultValue) {
        return delegate().previousList(defaultValue);
    }

    @Override
    public long previousLong() throws IllegalArgumentException {
        return delegate().previousLong();
    }

    @Override
    public long previousLong(long defaultValue) {
        return delegate().previousLong(defaultValue);
    }

    @Override
    public UtilityMap<Object, Object> previousMap() throws IllegalArgumentException {
        return delegate().previousMap();
    }

    @Override
    public UtilityMap<Object, Object> previousMap(UtilityMap<Object, Object> defaultValue) {
        return delegate().previousMap(defaultValue);
    }

    @Override
    public String previousString() throws IllegalArgumentException {
        return delegate().previousString();
    }

    @Override
    public String previousString(String defaultValue) {
        return delegate().previousString(defaultValue);
    }

}
