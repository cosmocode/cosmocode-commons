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

import com.google.common.collect.ForwardingIterator;

/**
 * Implementation of the {@link UtilityIterator} interface
 * delegating all calls to an underlying {@link UtilityIterator}.
 *
 * @author Willi Schoenborn
 * @param <E> the generic element type
 */
public abstract class ForwardingUtilityIterator<E> extends ForwardingIterator<E> implements UtilityIterator<E> {

    @Override
    protected abstract UtilityIterator<E> delegate();

    @Override
    public boolean nextBoolean() throws IllegalArgumentException {
        return delegate().nextBoolean();
    }

    @Override
    public boolean nextBoolean(boolean defaultValue) {
        return delegate().nextBoolean(defaultValue);
    }

    @Override
    public Date nextDate() throws IllegalArgumentException {
        return delegate().nextDate();
    }

    @Override
    public Date nextDate(Date defaultValue) {
        return delegate().nextDate(defaultValue);
    }

    @Override
    public double nextDouble() throws IllegalArgumentException {
        return delegate().nextDouble();
    }

    @Override
    public double nextDouble(double defaultValue) {
        return delegate().nextDouble(defaultValue);
    }

    @Override
    public <T extends Enum<T>> T nextEnum(Class<T> enumType, T defaultValue) {
        return delegate().nextEnum(enumType, defaultValue);
    }

    @Override
    public <T extends Enum<T>> T nextEnum(Class<T> enumType) throws IllegalArgumentException {
        return delegate().nextEnum(enumType);
    }

    @Override
    public UtilityList<Object> nextList() throws IllegalArgumentException {
        return delegate().nextList();
    }

    @Override
    public UtilityList<Object> nextList(UtilityList<Object> defaultValue) {
        return delegate().nextList(defaultValue);
    }

    @Override
    public long nextLong() throws IllegalArgumentException {
        return delegate().nextLong();
    }

    @Override
    public long nextLong(long defaultValue) {
        return delegate().nextLong(defaultValue);
    }

    @Override
    public UtilityMap<Object, Object> nextMap() throws IllegalArgumentException {
        return delegate().nextMap();
    }

    @Override
    public UtilityMap<Object, Object> nextMap(UtilityMap<Object, Object> defaultValue) {
        return delegate().nextMap(defaultValue);
    }

    @Override
    public String nextString() throws IllegalArgumentException {
        return delegate().nextString();
    }

    @Override
    public String nextString(String defaultValue) {
        return delegate().nextString(defaultValue);
    }

}
