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
import java.util.Locale;

/**
 * Abstract skeleton implementation of the {@link UtilityListIterator} interface
 * delegating all conversion calls to {@link Convert}.
 * 
 * @author Willi Schoenborn
 *
 * @param <E> the generic element type
 */
public abstract class AbstractUtilityListIterator<E> 
    extends AbstractUtilityIterator<E> implements UtilityListIterator<E> {

    @Override
    public boolean previousBoolean() throws IllegalArgumentException {
        return Convert.intoBoolean(previous());
    }

    @Override
    public boolean previousBoolean(boolean defaultValue) {
        return Convert.intoBoolean(previous(), defaultValue);
    }
    
    @Override
    public int previousInt() throws IllegalArgumentException {
        return (int) previousLong();
    }
    
    @Override
    public int previousInt(int defaultValue) {
        return (int) previousLong(defaultValue);
    }

    @Override
    public long previousLong() throws IllegalArgumentException {
        return Convert.intoLong(previous());
    }

    @Override
    public long previousLong(long defaultValue) {
        return Convert.intoLong(previous(), defaultValue);
    }

    @Override
    public double previousDouble() throws IllegalArgumentException {
        return Convert.intoDouble(previous());
    }

    @Override
    public double previousDouble(double defaultValue) {
        return Convert.intoDouble(previous(), defaultValue);
    }

    @Override
    public Date previousDate() throws IllegalArgumentException {
        return Convert.intoDate(previous());
    }

    @Override
    public Date previousDate(Date defaultValue) {
        return Convert.intoDate(previous(), defaultValue);
    }

    @Override
    public <T extends Enum<T>> T previousEnum(Class<T> enumType) throws IllegalArgumentException {
        return Convert.intoEnum(previous(), enumType);
    }

    @Override
    public <T extends Enum<T>> T previousEnum(Class<T> enumType, T defaultValue) {
        return Convert.intoEnum(previous(), enumType, defaultValue);
    }

    @Override
    public String previousString() throws IllegalArgumentException {
        return Convert.intoString(previous());
    }

    @Override
    public String previousString(String defaultValue) {
        return Convert.intoString(previous(), defaultValue);
    }
    
    @Override
    public Locale previousLocale() throws IllegalArgumentException {
        return Convert.intoLocale(previous());
    }
    
    @Override
    public Locale previousLocale(Locale defaultValue) {
        return Convert.intoLocale(previous(), defaultValue);
    }

    @Override
    public UtilityList<Object> previousList() throws IllegalArgumentException {
        return Convert.intoUtilityList(previous());
    }
    
    @Override
    public UtilityList<Object> previousList(UtilityList<Object> defaultValue) {
        return Convert.intoUtilityList(previous(), defaultValue);
    }
    
    @Override
    public UtilityMap<Object, Object> previousMap() throws IllegalArgumentException {
        return Convert.intoUtilityMap(previous());
    }
    
    @Override
    public UtilityMap<Object, Object> previousMap(UtilityMap<Object, Object> defaultValue) {
        return Convert.intoUtilityMap(previous(), defaultValue);
    }

}
