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

import java.lang.annotation.Annotation;
import java.lang.reflect.Modifier;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

import de.cosmocode.commons.Strings;

/**
 * Static utility class for {@link Classpath}s and {@link Packages}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
public final class Reflection {

    private Reflection() {
        
    }
    
    /**
     * Creates a new {@link Classpath} using the classpath property
     * of this virtual machine.
     * 
     * @since 1.8
     * @return a {@link Classpath} backed by the classpath of this virtual machine
     */
    public static Classpath defaultClasspath() {
        final String classpath = System.getProperty("java.class.path");
        return Reflection.classpathOf(Strings.defaultIfBlank(classpath, ""));
    }
    
    /**
     * Creates a {@link Classpath} using the specified classpath value.
     * 
     * @since 1.8
     * @param classpath the backing classpath value (; separated)
     * @return a {@link Classpath} backed by the specified classpath
     * @throws NullPointerException if classpath is null
     */
    public static Classpath classpathOf(String classpath) {
        Preconditions.checkNotNull(classpath, "Classpath");
        return new DefaultClasspath(classpath);
    }

    /**
     * Returns a predicate which delegates to {@link Class#isEnum()}.
     * 
     * @since 1.8
     * @return a predicate which matches enum types
     */
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

    /**
     * Returns a predicate which delegates to {@link Class#isAnnotation()}.
     * 
     * @since 1.8
     * @return a predicate which matches annotation types
     */
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

    /**
     * Returns a predicate which delegates to {@link Class#isArray()}.
     * 
     * @since 1.8
     * @return a predicate which matches array types
     */
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
    
    /**
     * Returns a predicate which delegates to {@link Modifier#isAbstract(int)}.
     * 
     * @since 1.8
     * @return a predicate which matches abstract classes
     */
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
    
    /**
     * Returns a predicate which delegates to {@link Class#isInterface()}.
     * 
     * @since 1.8
     * @return a predicate which matches interfaces
     */
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
    
    /**
     * Returns a predicate which matches super types of the specified
     * type.
     * 
     * @since 1.8
     * @param type the sub type
     * @return a predicate which matches super types of the given type
     * @throws NullPointerException if type is null
     */
    public static Predicate<Class<?>> isSupertypeOf(final Class<?> type) {
        Preconditions.checkNotNull(type, "Type");
        return new Predicate<Class<?>>() {
            
            @Override
            public boolean apply(Class<?> input) {
                return input.isAssignableFrom(type);
            }
            
        };
    }
    
    /**
     * Returns a predicate which matches sub types of the specified
     * type.
     * 
     * @since 1.8 
     * @param type the super type
     * @return a predicate which matches sub types of the given type
     * @throws NullPointerException if type is null
     */
    public static Predicate<Class<?>> isSubtypeOf(final Class<?> type) {
        Preconditions.checkNotNull(type, "Type");
        return new Predicate<Class<?>>() {
            
            @Override
            public boolean apply(Class<?> input) {
                return type.isAssignableFrom(input);
            }
            
        };
    }
    
    /**
     * Returns a function which casts class literals into subclass literals
     * using {@link Class#asSubclass(Class)}.
     * 
     * @since 1.8
     * @param <T> the generic class type
     * @param type the super class type
     * @return a function casting class literals to sub class literals
     * @throws NullPointerException if type is null
     */
    public static <T> Function<Class<?>, Class<? extends T>> asSubclass(final Class<T> type) {
        Preconditions.checkNotNull(type, "Type");
        return new Function<Class<?>, Class<? extends T>>() {
            
            @Override
            public Class<? extends T> apply(Class<?> from) {
                return from.asSubclass(type);
            }
            
        };
    }
    
    /**
     * Returns a predicate which delegates to {@link Class#isAnnotationPresent(Class)}.
     * 
     * @since 1.8
     * @param annotation the annotation
     * @return a predicate matching classes annotated with the specified annotation
     * @throws NullPointerException if annotation is null
     */
    public static Predicate<Class<?>> isAnnotationPresent(final Class<? extends Annotation> annotation) {
        Preconditions.checkNotNull(annotation, "Annotation");
        return new Predicate<Class<?>>() {
            
            @Override
            public boolean apply(Class<?> input) {
                return input.isAnnotationPresent(annotation);
            }
            
        };
    }

}
