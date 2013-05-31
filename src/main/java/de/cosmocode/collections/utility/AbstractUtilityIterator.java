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

/**
 * Abstract skeleton implementation of the {@link UtilityIterator} interface
 * delegating all conversion calls to {@link Convert}.
 * 
 * @author Willi Schoenborn
 *
 * @param <E> the generic element type
 */
public abstract class AbstractUtilityIterator<E> implements UtilityIterator<E> {

    @Override
    public boolean nextBoolean() throws IllegalArgumentException {
        return Convert.intoBoolean(next());
    }

    @Override
    public boolean nextBoolean(boolean defaultValue) {
        return Convert.intoBoolean(next(), defaultValue);
    }

    @Override
    public int nextInt() throws IllegalArgumentException {
        return (int) nextLong();
    }
    
    @Override
    public int nextInt(int defaultValue) {
        return (int) nextLong(defaultValue);
    }
    
    @Override
    public long nextLong() throws IllegalArgumentException {
        return Convert.intoLong(next());
    }

    @Override
    public long nextLong(long defaultValue) {
        return Convert.intoLong(next(), defaultValue);
    }

    @Override
    public double nextDouble() throws IllegalArgumentException {
        return Convert.intoDouble(next());
    }

    @Override
    public double nextDouble(double defaultValue) {
        return Convert.intoDouble(next(), defaultValue);
    }

    @Override
    public Date nextDate() throws IllegalArgumentException {
        return Convert.intoDate(next());
    }

    @Override
    public Date nextDate(Date defaultValue) {
        return Convert.intoDate(next(), defaultValue);
    }

    @Override
    public <T extends Enum<T>> T nextEnum(Class<T> enumType) throws IllegalArgumentException {
        return Convert.intoEnum(next(), enumType);
    }

    @Override
    public <T extends Enum<T>> T nextEnum(Class<T> enumType, T defaultValue) {
        return Convert.intoEnum(next(), enumType, defaultValue);
    }

    @Override
    public String nextString() throws IllegalArgumentException {
        return Convert.intoString(next());
    }

    @Override
    public String nextString(String defaultValue) {
        return Convert.intoString(next(), defaultValue);
    }
    
    @Override
    public Locale nextLocale() throws IllegalArgumentException {
        return Convert.intoLocale(next());
    }
    
    @Override
    public Locale nextLocale(Locale defaultValue) {
        return Convert.intoLocale(next(), defaultValue);
    }
    
    @Override
    public UtilityList<Object> nextList() throws IllegalArgumentException {
        return Convert.intoUtilityList(next());
    }
    
    @Override
    public UtilityList<Object> nextList(UtilityList<Object> defaultValue) {
        return Convert.intoUtilityList(next(), defaultValue);
    }
    
    @Override
    public UtilityMap<Object, Object> nextMap() throws IllegalArgumentException {
        return Convert.intoUtilityMap(next());
    }
    
    @Override
    public UtilityMap<Object, Object> nextMap(UtilityMap<Object, Object> defaultValue) {
        return Convert.intoUtilityMap(next(), defaultValue);
    }

}
