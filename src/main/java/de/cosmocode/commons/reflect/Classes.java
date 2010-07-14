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
import com.google.common.collect.MapMaker;
import com.google.common.collect.Ordering;

/**
 * Static utility class for {@link Class}es.
 *
 * @since 1.6
 * @author Willi Schoenborn
 */
public final class Classes {

    private static final ConcurrentMap<String, Class<?>> CACHE = new MapMaker().softValues().makeMap();
    
    private static final Ordering<Class<?>> ORDER_BY_NAME = Ordering.natural().onResultOf(Classes.getName());
    private static final Ordering<Class<?>> ORDER_BY_HIERARCHY = Classes.orderByHierarchy(Classes.orderByName());
    
    private Classes() {
        
    }
    
    /**
     * Returns the Class object associated with the class or interface with the given string name.
     * This method does in contrast to {@link Class#forName(String)} caches the results.
     * 
     * @since 1.6
     * @param name the class name
     * @return the loaded class
     * @throws ClassNotFoundException if the class does not exist
     */
    public static Class<?> forName(String name) throws ClassNotFoundException {
        final Class<?> cached = CACHE.get(name);
        return cached == null ? store(name) : cached;
    }
    
    private static Class<?> store(String name) throws ClassNotFoundException {
        final Class<?> type = Class.forName(name);
        CACHE.put(name, type);
        return type;
    }
    
    public static Function<Class<?>, String> getName() {
        return GetNameFunction.INSTANCE;
    }
    
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
    
    public static Ordering<Class<?>> orderByName() {
        return Classes.ORDER_BY_NAME;
    }
    
    public static Ordering<Class<?>> orderByHierarchy() {
        return Classes.ORDER_BY_HIERARCHY;
    }
    
    public static Ordering<Class<?>> orderByHierarchy(Comparator<Class<?>> comparator) {
        return new HierarchyOrdering(comparator);
    }
    
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
