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

import java.lang.reflect.Modifier;
import java.util.concurrent.ConcurrentMap;

import com.google.common.base.Predicate;
import com.google.common.collect.MapMaker;

/**
 * Static utility class for {@link Class}es.
 *
 * @since 1.6
 * @author Willi Schoenborn
 */
public final class Classes {

    private static final ConcurrentMap<String, Class<?>> CACHE = new MapMaker().softValues().makeMap();
    
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
    
    public static Predicate<Class<?>> isEnum() {
        return IsEnum.INSTANCE;
    }
    
    /**
     * Singleton enum predicate for {@link Classes#isEnum()}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     */
    private static enum IsEnum implements Predicate<Class<?>> {
        
        INSTANCE;
        
        @Override
        public boolean apply(Class<?> input) {
            return input.isEnum();
        }
        
    }
    
    public static Predicate<Class<?>> isAnnotation() {
        return IsAnnotation.INSTANCE;
    }

    /**
     * Singleton enum predicate for {@link Classes#isAnnotation()}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     */
    private static enum IsAnnotation implements Predicate<Class<?>> {
        
        INSTANCE;
        
        @Override
        public boolean apply(Class<?> input) {
            return input.isAnnotation();
        }
        
    }
    
    public static Predicate<Class<?>> isArray() {
        return IsArray.INSTANCE;
    }

    /**
     * Singleton enum predicate for {@link Classes#isArray()}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     */
    private static enum IsArray implements Predicate<Class<?>> {
        
        INSTANCE;
        
        @Override
        public boolean apply(Class<?> input) {
            return input.isArray();
        }
        
    }
    
    public static Predicate<Class<?>> isAbstract() {
        return IsAbstract.INSTANCE;
    }

    /**
     * Singleton enum predicate for {@link Classes#isAbstract()}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     */
    private static enum IsAbstract implements Predicate<Class<?>> {
        
        INSTANCE;
        
        @Override
        public boolean apply(Class<?> input) {
            return Modifier.isAbstract(input.getModifiers());
        }
        
    }
    
    public static Predicate<Class<?>> isInterface() {
        return IsInterface.INSTANCE;
    }

    /**
     * Singleton enum predicate for {@link Classes#isInterface()}.
     *
     * @since 1.8
     * @author Willi Schoenborn
     */
    private static enum IsInterface implements Predicate<Class<?>> {
        
        INSTANCE;
        
        @Override
        public boolean apply(Class<?> input) {
            return input.isInterface();
        }
        
    }
    
}
