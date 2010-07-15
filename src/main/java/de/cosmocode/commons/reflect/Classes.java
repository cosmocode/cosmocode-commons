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

package de.cosmocode.commons.reflect;

import java.util.Comparator;
import java.util.concurrent.ConcurrentMap;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ComputationException;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Ordering;

import de.cosmocode.commons.Throwables;

/**
 * Static utility class for {@link Class}es.
 *
 * @since 1.6
 * @author Willi Schoenborn
 */
public final class Classes {

    private static final ConcurrentMap<String, Class<?>> CACHE;
    
    static {
        
        CACHE = new MapMaker().softValues().makeComputingMap(ForNameFunction.INSTANCE);
        
    }
    
    private static final Ordering<Class<?>> ORDER_BY_NAME = Ordering.natural().onResultOf(Classes.getName());
    
    private static final Ordering<Class<?>> ORDER_BY_HIERARCHY = Classes.orderByHierarchy(Classes.orderByName());
    
    private Classes() {
        
    }
    
    /**
     * Returns a function which loads {@link Class}es by their name using {@link Class#forName(String)}.
     * 
     * <p>
     *   The returned function will warp {@link ClassNotFoundException}s inside {@link IllegalArgumentException}s.
     * </p>
     * 
     * @since 1.9
     * @return a function loading classes
     */
    public static Function<String, Class<?>> forName() {
        return ForNameFunction.INSTANCE;
    }
    
    /**
     * Implementation for {@link Classes#forName()}.
     *
     * @since 1.9
     * @author Willi Schoenborn
     */
    private enum ForNameFunction implements Function<String, Class<?>> {
        
        INSTANCE;
        
        @Override
        public Class<?> apply(String from) {
            try {
                return Class.forName(from);
            } catch (ClassNotFoundException e) {
                throw new IllegalArgumentException(e);
            }
        }
        
        @Override
        public String toString() {
            return "Classes.forName()";
        }
        
    }
    
    /**
     * Returns the Class object associated with the class or interface with the given string name.
     * This method does, in contrast to {@link Class#forName(String)}, cache the results.
     * 
     * @since 1.6
     * @param name the class name
     * @return the loaded class
     * @throws ClassNotFoundException if the class does not exist
     */
    public static Class<?> forName(String name) throws ClassNotFoundException {
        try {
            return CACHE.get(name);
        } catch (ComputationException e) {
            Throwables.propagateCauseIfInstanceOf(e, ClassNotFoundException.class);
            throw new ClassNotFoundException(e.getMessage(), e);
        }
    }
    
    /**
     * Returns a function which transforms {@link Class}es into Strings by using
     * {@link Class#getName()}.
     * 
     * @since 1.9
     * @return a function using getName() to produce Strings
     */
    public static Function<Class<?>, String> getName() {
        return GetNameFunction.INSTANCE;
    }
    
    /**
     * Implementation for {@link Classes#getName()}.
     *
     * @since 1.9
     * @author Willi Schoenborn
     */
    private enum GetNameFunction implements Function<Class<?>, String> {
        
        INSTANCE;
        
        @Override
        public String apply(Class<?> from) {
            return from.getName();
        };
        
        @Override
        public String toString() {
            return "Classes.getName()";
        }
        
    }
    
    /**
     * Returns a function which transforms {@link Class}es into Strings by using
     * {@link Class#getSimpleName()}.
     * 
     * @since 1.9
     * @return a function using getSimpleName() to produce Strings
     */
    public static Function<Class<?>, String> getSimpleName() {
        return GetSimpleNameFunction.INSTANCE;
    }
    
    /**
     * Implementation for {@link Classes#getSimpleName()}.
     *
     * @since 1.9
     * @author Willi Schoenborn
     */
    private enum GetSimpleNameFunction implements Function<Class<?>, String> {
        
        INSTANCE;
        
        @Override
        public String apply(Class<?> from) {
            return from.getSimpleName();
        }
        
        @Override
        public String toString() {
            return "Classes.getSimpleName()";
        }
        
    }
    
    /**
     * Returns an {@link Ordering} which uses string comparision of {@link Class#getName()}.
     * 
     * @since 1.9
     * @return an ordering for sorting by name
     */
    public static Ordering<Class<?>> orderByName() {
        return Classes.ORDER_BY_NAME;
    }
    
    /**
     * Returns an {@link Ordering} which uses the relation between classes to compare them.
     * Two classes that are equals according to {@link Object#equals(Object)} are considered
     * equals by the returned comparator. Sub types are considered less than super classes.
     * {@link Double}, {@link Long} and {@link Integer} e.g. are considered less than
     * {@link Number}. Two classes that are not related regarding inheritence are compared using
     * {@link Classes#orderByName()}.
     * 
     * @since 1.9
     * @return an ordering which sorts classes by hierarchy
     */
    public static Ordering<Class<?>> orderByHierarchy() {
        return Classes.ORDER_BY_HIERARCHY;
    }
    
    /**
     * Returns an {@link Ordering} which uses the relation between classes to compare them.
     * Two classes that are equals according to {@link Object#equals(Object)} are considered
     * equals by the returned comparator. Sub types are considered less than super classes.
     * {@link Double}, {@link Long} and {@link Integer} e.g. are considered less than
     * {@link Number}. Two classes that are not related regarding inheritence are compared using
     * the given comparator
     * 
     * @since 1.9
     * @param comparator the comparator which is used in case of a tie
     * @return an ordering which sorts classes by hierarchy
     * @throws NullPointerException if comparator is null
     */
    public static Ordering<Class<?>> orderByHierarchy(Comparator<Class<?>> comparator) {
        return new HierarchyOrdering(comparator);
    }
    
    /**
     * Implementation for {@link Classes#orderByHierarchy(Comparator)}.
     *
     * @since 1.9
     * @author Willi Schoenborn
     */
    private static final class HierarchyOrdering extends Ordering<Class<?>> {
        
        private final Comparator<Class<?>> comparator;
        
        public HierarchyOrdering(Comparator<Class<?>> comparator) {
            this.comparator = Preconditions.checkNotNull(comparator, "Comparator");
        }
        
        @Override
        public int compare(Class<?> left, Class<?> right) {
            if (left.equals(right)) {
                return 0;
            } else if (left.isAssignableFrom(right)) {
                return 1;
            } else if (right.isAssignableFrom(left)) {
                return -1;
            } else {
                return comparator.compare(left, right);
            }
        }
        
        @Override
        public boolean equals(Object that) {
            if (this == that) {
                return true;
            } else if (that instanceof HierarchyOrdering) {
                final HierarchyOrdering other = HierarchyOrdering.class.cast(that);
                return comparator.equals(other.comparator);
            } else {
                return false;
            }
        }
        
        @Override
        public int hashCode() {
            return comparator.hashCode();
        }
        
        @Override
        public String toString() {
            return "Classes.orderByHierarchy(" + comparator + ")";
        }
        
    }
    
}
