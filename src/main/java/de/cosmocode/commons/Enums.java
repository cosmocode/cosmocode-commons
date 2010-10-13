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

package de.cosmocode.commons;

import java.util.Collection;
import java.util.EnumSet;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Sets;

/**
 * Utility class providing all kind of useful
 * methods regarding enums.
 *
 * @author Willi Schoenborn
 */
public final class Enums {

    /**
     * Prevent instantiation.
     */
    private Enums() {
        
    }
    
    /**
     * Provides a {@link Function} which transforms enum values into
     * Strings by calling {@link Enum#name()}.
     * 
     * <p>
     *   Note: {@link Enum#toString()} <strong>may</strong> return the same
     *   result, but while toString can be overridden, name is final and therefore
     *   safer.
     * </p>
     * 
     * @param <E> the generic enum type
     * @return a function which transforms an enum value to a string using its name 
     */
    @SuppressWarnings("unchecked")
    public static <E extends Enum<?>> Function<E, String> name() {
        return (Function<E, String>) EnumNameFunction.INSTANCE;
    }
    
    /**
     * Provides a codec which encodes Enums into Strings by using {@link Enum#name()} and
     * vice versa using {@link Enum#valueOf(Class, String)}.
     * 
     * @since 1.9
     * @param <E> generic enum type
     * @param type the enum types class literal
     * @return a codec for enum to string conversion
     * @throws NullPointerException if type is null
     */
    public static <E extends Enum<E>> Codec<E, String> name(Class<E> type) {
        return new EnumNameCodec<E>(type);
    }
    
    /**
     * Produces a String out of an enum value
     * by using it's simple class name and it's name.
     * 
     * <p>
     *   e.g. NORMAL of {@link TrimMode} would return:
     *   {@code TrimMode.NORMAL}
     * </p>
     * 
     * @param e the enum value
     * @return full name of the enum constant
     * @throws NullPointerException if e is null
     */
    public static String toString(Enum<?> e) {
        Preconditions.checkNotNull(e, "Enum");
        return e.getClass().getSimpleName() + "." + e.name();
    }
    
    /**
     * Returns the {@link Enum} instance for a given ordinal.
     * This method is the index based alternative
     * to {@link Enum#valueOf(Class, String)}, which
     * requires the name of an instance.
     * 
     * @param <E> the enum type
     * @param type the enum class object
     * @param ordinal the index of the enum instance
     * @throws IndexOutOfBoundsException if ordinal < 0 || ordinal >= enums.length
     * @return the enum instance with the given ordinal
     */
    public static <E extends Enum<E>> E valueOf(Class<E> type, int ordinal) {
        Preconditions.checkNotNull(type, "Type");
        if (ordinal < 0) throw new IndexOutOfBoundsException("Ordinal must not be negative");
        final E[] enums = type.getEnumConstants();
        if (ordinal < enums.length) {
            return enums[ordinal];
        } else {
            throw new IndexOutOfBoundsException("Index: " + ordinal + ", Size: " + enums.length);
        }
    }
    
    /**
     * Decodes a bitset (flag) into an {@link EnumSet}.
     * 
     * <p>
     *   This is the reverse operation of {@link Enums#encode(Set)}.
     * </p>
     * 
     * @param <E> the enum type
     * @param type the enum class object
     * @param flag the bit flag
     * @return a {@link Set} containing all enum instances with one-bits in flag
     * @throws NullPointerException if type is null
     */
    public static <E extends Enum<E>> Set<E> decode(Class<E> type, long flag) {
        Preconditions.checkNotNull(type, "Type");
        final Set<E> enums = EnumSet.noneOf(type);
        if (flag == 0) return enums;
        
        for (E e : type.getEnumConstants()) {
            if (Enums.has(flag, e)) enums.add(e);
        }
        
        return enums;
    }
    
    /**
     * Encodes a {@link Set} of enums into a bitset.
     * 
     * <p>
     *   This is the reverse operation of {@link Enums#decode(Class, long)}.
     * </p>
     * 
     * @param <E> the enum type
     * @param enums {@link Set} of enums to represent as a bitset
     * @return a bitset having all enums in the set with one bit
     * @throws NullPointerException if enums is null
     */
    public static <E extends Enum<E>> long encode(Set<E> enums) {
        Preconditions.checkNotNull(enums, "Enums");
        long flag = Enums.empty();
        
        for (E e : enums) {
            flag = Enums.add(flag, e);
        }
        return flag;
    }
    
    /**
     * Creates a union of enum {@link Collection}s.
     * 
     * @deprecated use {@link Sets#union(Set, Set)}
     * @param <E> the enum type
     * @param a {@link Collection} of enums
     * @param b {@link Collection} of enums
     * @return a {@link Collection} containing all elements of a and b
     */
    @Deprecated
    public static <E extends Enum<E>> Set<E> union(Collection<E> a, Collection<E> b) {
        final EnumSet<E> union = EnumSet.copyOf(a);
        union.addAll(b);
        return union;
    }
    
    /**
     * Calculates the bit of an {@link Enum} 
     * which is basically the following.
     * <pre>
     *   2 ^ e.ordinal()
     * </pre>
     * 
     * @param <E> the enum type
     * @param e the enum instance
     * @return the bit of the enum instance
     * @throws NullPointerException if e is null
     */
    private static <E extends Enum<E>> long asBit(E e) {
        return 1L << Preconditions.checkNotNull(e, "Enum").ordinal();
    }
    
    /**
     * Performs a bitwise OR using flag and the calculated
     * bit of e.
     * 
     * See also {@link Enums#asBit(Enum)}
     * 
     * @param <E> the enum type
     * @param flag the bit flag
     * @param e the enum instance
     * @return flag OR asBit(e)
     * @throws NullPointerException if e is null
     */
    private static <E extends Enum<E>> long add(long flag, E e) {
        return flag | asBit(e);
    }
    
    /**
     * Determines whether tje calculated bit of
     * e is contained in flag.
     * 
     * @param <E> the enum type
     * @param flag the bit flag
     * @param e the enum instance
     * @return (flag & asBit(e)) > 0
     * @throws NullPointerException if e is null
     */
    private static <E extends Enum<E>> boolean has(long flag, E e) {
        return (flag & asBit(e)) > 0;
    }
    
    /**
     * Creates an empty bitset of type long.
     * 
     * @return an empty bitset
     */
    private static long empty() {
        return 0x0L;
    }
    
}
