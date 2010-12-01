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

import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.regex.Matcher;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;
import com.google.common.collect.Multimap;

import de.cosmocode.commons.DateMode;
import de.cosmocode.commons.Enums;
import de.cosmocode.commons.Patterns;

/**
 * Utility class providing method to convert
 * genericly typed parameters into strongly
 * typed primitives or well known
 * and widely used objects.
 * 
 * @author Willi Schoenborn
 */
public final class Convert {
    
    private static final Logger LOG = LoggerFactory.getLogger(Convert.class);

    /**
     * Prevent instantiation.
     */
    private Convert() {
        
    }
    
    private static RuntimeException fail(Object value, Class<?> type) {
        final String message = "'" + value + "' couldn't be converted into '" + type.getName() + "'";
        return new IllegalArgumentException(message);
    }
    
    @edu.umd.cs.findbugs.annotations.SuppressWarnings("NP_BOOLEAN_RETURN_NULL")
    private static Boolean doIntoBoolean(Object value) {
        if (value == null) return null;
        if (value instanceof Boolean) return Boolean.class.cast(value);
        final String converted = doIntoString(value);
        if ("true".equalsIgnoreCase(converted)) {
            return Boolean.TRUE;
        } else if ("false".equalsIgnoreCase(converted)) {
            return Boolean.FALSE;
        } else {
            return null;
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a boolean.
     * 
     * @param value the value being parsed
     * @return the parsed boolean
     * @throws IllegalArgumentException if conversion failed
     */
    public static boolean intoBoolean(Object value) {
        final Boolean b = doIntoBoolean(value);
        if (b == null) {
            throw fail(value, boolean.class);
        } else {
            return b.booleanValue();
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a boolean.
     * 
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a boolean
     * @return the parsed boolean or the defaultValue if value can't be parsed into a boolean
     */
    public static boolean intoBoolean(Object value, boolean defaultValue) {
        final Boolean b = doIntoBoolean(value);
        return b == null ? defaultValue : b.booleanValue();
    }
    
    private static Long doIntoLong(Object value) {
        if (value == null) return null;
        if (value instanceof Long) return Long.class.cast(value);
        if (value instanceof Number) return Number.class.cast(value).longValue();
        final String s = doIntoString(value);
        try {
            return Long.valueOf(s);
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a long.
     * 
     * @param value the value being parsed
     * @return the parsed long
     * @throws IllegalArgumentException if conversion failed
     */
    public static long intoLong(Object value) {
        final Long l = doIntoLong(value);
        if (l == null) {
            throw fail(value, long.class);
        } else {
            return l.longValue();
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a long.
     * 
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a long
     * @return the parsed long or the defaultValue if value can't be parsed into a long
     */
    public static long intoLong(Object value, long defaultValue) {
        final Long l = doIntoLong(value);
        return l == null ? defaultValue : l.longValue();
    }
    
    private static Double doIntoDouble(Object value) {
        if (value == null) return null;
        if (value instanceof Double) return Double.class.cast(value);
        if (value instanceof Number) return Number.class.cast(value).doubleValue();
        final String s = doIntoString(value);
        try {
            final Double d = Double.valueOf(s);
            LOG.debug("Converted {} into {}", value, d);
            if (d.isInfinite() || d.isNaN()) return null;
            return d;
        } catch (NumberFormatException e) {
            return null;
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a double.
     * 
     * @param value the value being parsed
     * @return the parsed double
     * @throws IllegalArgumentException if conversion failed
     */
    public static double intoDouble(Object value) {
        final Double d = doIntoDouble(value);
        if (d == null) {
            throw fail(value, double.class);
        } else {
            return d.doubleValue();
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a double.
     * 
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a double
     * @return the parsed double or the defaultValue if value can't be parsed into a double
     */
    public static double intoDouble(Object value, double defaultValue) {
        final Double d = doIntoDouble(value);
        return d == null ? defaultValue : d.doubleValue();
    }
    
    private static Date doIntoDate(Object value, DateMode dateMode) {
        Preconditions.checkNotNull(dateMode, "DateMode");
        if (value == null) return null;
        if (value instanceof Date) return Date.class.cast(value);
        if (value instanceof Calendar) return Calendar.class.cast(value).getTime();
        final Long time = doIntoLong(value);
        return time == null || time.longValue() < 0 ? null : dateMode.parse(time.longValue());
    }
    
    /**
     * Parses a value of a generic type
     * into a {@link Date}.
     * 
     * @param value the value being parsed
     * @return the parsed {@link Date}
     * @throws IllegalArgumentException if conversion failed
     */
    public static Date intoDate(Object value) {
        return intoDate(value, DateMode.JAVA);
    }

    /**
     * Parses a value of a generic type
     * into a {@link Date}.
     * 
     * @param value the value being parsed
     * @param dateMode a {@link DateMode} instance handling the long to time conversion
     * @return the parsed {@link Date}
     * @throws NullPointerException if dateMode is null
     * @throws IllegalArgumentException if conversion failed
     */
    public static Date intoDate(Object value, DateMode dateMode) {
        final Date date = doIntoDate(value, dateMode);
        if (date == null) {
            throw fail(value, Date.class);
        } else {
            return date;
        }
    }


    /**
     * Parses a value of a generic type
     * into a {@link Date}.
     * 
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a {@link Date}
     * @return the parsed {@link Date}
     */
    public static Date intoDate(Object value, Date defaultValue) {
        return intoDate(value, DateMode.JAVA, defaultValue);
    }
    
    /**
     * Parses a value of a generic type
     * into a {@link Date}.
     * 
     * @param value the value being parsed
     * @param dateMode the {@link DateMode} being used to convert long to {@link Date}
     * @param defaultValue the default value if value can't be parsed into a {@link Date}
     * @return the parsed {@link Date} or the defaultValue if value can't be parsed into a {@link Date}
     * @throws NullPointerException if dateMode is null
     */
    public static Date intoDate(Object value, DateMode dateMode, Date defaultValue) {
        final Date date = doIntoDate(value, dateMode);
        return date == null ? defaultValue : date;
    }

    private static <E extends Enum<E>> E doIntoEnum(Object value, Class<E> enumType) {
        Preconditions.checkNotNull(enumType, "EnumType");
        if (enumType.isInstance(value)) return enumType.cast(value);
        final Long ordinal = doIntoLong(value);
        if (ordinal == null) {
            final String name = doIntoString(value);
            if (name == null) return null;
            try {
                return Enum.valueOf(enumType, name.toUpperCase());
            } catch (IllegalArgumentException e) {
                return null;
            }
        } else {
            try {
                return Enums.valueOf(enumType, ordinal.intValue());
            } catch (IndexOutOfBoundsException e) {
                return null;
            }
        }
    }
    
    /**
     * Parses a value of a generic type
     * into an {@link Enum}.
     * The value must be either a valid enum constant
     * name or ordinal.
     * 
     * @param <E> the generic enum type
     * @param value the value being parsed
     * @param enumType the enum type's class
     * @return the parsed {@link Enum}
     * @throws NullPointerException if enumType is null
     * @throws IllegalArgumentException if conversion failed
     */
    public static <E extends Enum<E>> E intoEnum(Object value, Class<E> enumType) {
        final E e = doIntoEnum(value, enumType);
        if (e == null) {
            throw fail(value, enumType);
        } else {
            return e;
        }
    }
    
    /**
     * Parses a value of a generic type
     * into an {@link Enum}.
     * 
     * @param <E> the generic enum type
     * @param value the value being parsed
     * @param enumType the enum type's class
     * @param defaultValue the default value if value can't be parsed into an {@link Enum}
     * @return the parsed {@link Enum} or the defaultValue if value can't be parsed into an {@link Enum}
     */
    public static <E extends Enum<E>> E intoEnum(Object value, Class<E> enumType, E defaultValue) {
        final E e = doIntoEnum(value, enumType);
        return e == null ? defaultValue : e;
    }
    
    private static String doIntoString(Object value) {
        return value == null ? null : value.toString();
    }
    
    /**
     * Parses a value of a generic type
     * into a {@link String}.
     * 
     * @param value the value being parsed
     * @return the parsed {@link String}
     * @throws IllegalArgumentException if conversion failed
     */
    public static String intoString(Object value) {
        final String converted = doIntoString(value);
        if (value == null) {
            throw fail(value, String.class);
        } else {
            return converted;
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a {@link String}.
     * 
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a {@link String}
     * @return the parsed {@link String} or the defaultValue if value can't be parsed into a {@link String}
     */
    public static String intoString(Object value, String defaultValue) {
        final String converted = doIntoString(value);
        return value == null ? defaultValue : converted;
    }
    
    private static Locale doIntoLocale(Object value) {
        if (value == null) return null;
        if (value instanceof Locale) return Locale.class.cast(value);
        final String string = doIntoString(value);
        final Matcher matcher = Patterns.LOCALE.matcher(string);
        if (matcher.matches()) {
            final String language = StringUtils.defaultString(matcher.group(1));
            final String country = StringUtils.defaultString(matcher.group(2));
            final String variant = StringUtils.defaultString(matcher.group(3));
            return new Locale(language, country, variant);
        } else {
            return null;
        }
    }
    
    /**
     * Parses a value of a generic type into a {@link Locale}.
     * 
     * @param value the value being parsed
     * @return the parsed {@link Locale}
     * @throws IllegalArgumentException if conversion failed
     */
    public static Locale intoLocale(Object value) {
        final Locale locale = doIntoLocale(value);
        if (locale == null) {
            throw fail(value, Locale.class);
        } else {
            return locale;
        }
    }
    
    /**
     * Parses a value of a generic type into a {@link Locale}.
     * 
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a {@link Locale}
     * @return the parsed {@link Locale} of the defaultValue if value can't be parsed into a {@link Locale}
     */
    public static Locale intoLocale(Object value, Locale defaultValue) {
        final Locale locale = doIntoLocale(value);
        return locale == null ? defaultValue : locale;
    }
    
    private static List<Object> doIntoList(Object value) {
        if (value == null) return null;
        if (value instanceof List<?>) {
            // cast is safe, because everything is an object
            @SuppressWarnings("unchecked")
            final List<Object> list = List.class.cast(value);
            return list;
        } else if (value.getClass().isArray()) {
            final Object[] array = Object[].class.cast(value);
            return Lists.newArrayList(array);
        } else if (value instanceof Iterable<?>) {
            final Iterable<?> iterable = Iterable.class.cast(value);
            return Lists.newArrayList(iterable);
        } else if (value instanceof Iterator<?>) {
            final Iterator<?> iterator = Iterator.class.cast(value);
            return Lists.newArrayList(iterator);
        } else {
            return null;
        }
    }
    
    /**
     * Parses a value of a generic type into a {@link List}.
     * 
     * This method transforms any kind of the following into a {@link List}.
     * <ul>
     *   <li>{@link List}</li>
     *   <li>Array</li>
     *   <li>{@link Iterable}</li>
     *   <li>{@link Iterator}</li>
     * </ul>
     * 
     * @param value the value being parsed
     * @return the parsed {@link List}
     * @throws IllegalArgumentException if conversion failed
     */
    public static List<Object> intoList(Object value) {
        final List<Object> list = doIntoList(value);
        if (list == null) {
            throw fail(value, List.class);
        } else {
            return list;
        }
    }
    
    /**
     * Parses a value of a generic type into a {@link List}.
     * 
     * This method transforms any kind of the following into a {@link List}.
     * <ul>
     *   <li>{@link List}</li>
     *   <li>Array</li>
     *   <li>{@link Iterable}</li>
     *   <li>{@link Iterator}</li>
     * </ul>
     * 
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a {@link List}
     * @return the parsed {@link List} or the defaultValue if value can't be parsed into a {@link List}
     */
    public static List<Object> intoList(Object value, List<Object> defaultValue) {
        final List<Object> list = doIntoList(value);
        return list == null ? defaultValue : list;
    }
    
    /**
     * Parses a value of a generic type into a {@link UtilityList}.
     * 
     * This method uses the same features as {@link Convert#intoList(Object)}.
     * 
     * @param value the value being parsed
     * @return the parses {@link UtilityList}
     * @throws IllegalArgumentException if conversion failed
     */
    public static UtilityList<Object> intoUtilityList(Object value) {
        final List<Object> list = doIntoList(value);
        if (list == null) {
            throw fail(value, UtilityList.class);
        } else {
            return Utility.asUtilityList(list);
        }
    }
    
    /**
     * Parses a value of a generic type into a {@link UtilityList}.
     * 
     * This method uses the same features as {@link Convert#intoList(Object)}.
     * 
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a {@link UtilityList}
     * @return the parsed {@link UtilityList} or the defaultValue if value can't be parsed into a {@link UtilityList}
     */
    public static UtilityList<Object> intoUtilityList(Object value, UtilityList<Object> defaultValue) {
        final List<Object> list = doIntoList(value);
        return list == null ? defaultValue : Utility.asUtilityList(list);
    }
    
    private static Map<Object, Object> doIntoMap(Object value) {
        if (value == null) return null;
        if (value instanceof Map<?, ?>) {
            // cast is safe, because everything is an object
            @SuppressWarnings("unchecked")
            final Map<Object, Object> map = Map.class.cast(value);
            return map;
        } else if (value instanceof Multimap<?, ?>) {
            // cast is safe, because everything is an object
            @SuppressWarnings("unchecked")
            final Map<Object, Object> map = Multimap.class.cast(value).asMap();
            return map;
        } else {
            return null;
        }
    }
    
    /**
     * Parses a value of a generic type into a {@link Map}.
     * 
     * This method transforms any kind of the following into a {@link Map}.
     * <ul>
     *   <li>{@link Map}</li>
     *   <li>{@link Multimap}</li>
     * </ul>
     * 
     * @param value the value being parsed
     * @return the parsed {@link Map}
     * @throws IllegalArgumentException if conversion failed
     */
    public static Map<Object, Object> intoMap(Object value) {
        final Map<Object, Object> map = doIntoMap(value);
        if (map == null) {
            throw fail(value, Map.class);
        } else {
            return map;
        }
    }
    
    /**
     * Parses a value of a generic type into a {@link Map}.
     * 
     * This method transforms any kind of the following into a {@link Map}.
     * <ul>
     *   <li>{@link Map}</li>
     *   <li>{@link Multimap}</li>
     * </ul>
     * 
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a {@link Map}
     * @return the parsed {@link Map} or the defaultValue if value can't be parsed into a {@link Map}
     */
    public static Map<Object, Object> intoMap(Object value, Map<Object, Object> defaultValue) {
        final Map<Object, Object> map = doIntoMap(value);
        return map == null ? defaultValue : map;
    }
    
    /**
     * Parses a value of a generic type into a {@link UtilityMap}.
     * 
     * This method uses the same features as {@link Convert#intoMap(Object)}.
     * 
     * @param value the value being parsed
     * @return the parsed {@link UtilityMap}
     * @throws IllegalArgumentException if conversion failed
     */
    public static UtilityMap<Object, Object> intoUtilityMap(Object value) {
        final Map<Object, Object> map = doIntoMap(value);
        if (map == null) {
            throw fail(value, UtilityMap.class);
        } else {
            return Utility.asUtilityMap(map);
        }
    }
    
    /**
     * Parses a value of a generic type into a {@link UtilityMap}.
     * 
     * This method uses the same features as {@link Convert#intoMap(Object, Map)}.
     * 
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a {@link UtilityMap}
     * @return the parsed {@link UtilityMap} or the defaultValue if value can't be parsed into a {@link UtilityMap}
     */
    public static UtilityMap<Object, Object> intoUtilityMap(Object value, UtilityMap<Object, Object> defaultValue) {
        final Map<Object, Object> map = doIntoMap(value);
        return map == null ? defaultValue : Utility.asUtilityMap(map);
    }
    
}
