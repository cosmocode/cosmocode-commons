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
import java.util.Comparator;
import java.util.concurrent.ConcurrentMap;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ComputationException;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Ordering;

import de.cosmocode.commons.Strings;
import de.cosmocode.commons.Throwables;
import de.cosmocode.commons.validation.Rule;

/**
 * Static utility class for {@link Classpath}s and {@link Packages}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
public final class Reflection {

    private static final ConcurrentMap<String, Class<?>> CACHE = new MapMaker().
        softValues().makeComputingMap(Reflection.forName());
    
    private static final Ordering<Class<?>> ORDER_BY_NAME = Ordering.natural().onResultOf(Reflection.getName());
    
    private static final Ordering<Class<?>> ORDER_BY_HIERARCHY = Reflection.orderByHierarchy(Reflection.orderByName());

    private Reflection() {
        
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
        return ForName.INSTANCE;
    }
    
    /**
     * Returns a function which transforms {@link Class}es into Strings by using
     * {@link Class#getName()}.
     * 
     * @since 1.9
     * @return a function using getName() to produce Strings
     */
    public static Function<Class<?>, String> getName() {
        return GetName.INSTANCE;
    }
    
    /**
     * Returns a function which transforms {@link Class}es into Strings by using
     * {@link Class#getSimpleName()}.
     * 
     * @since 1.9
     * @return a function using getSimpleName() to produce Strings
     */
    public static Function<Class<?>, String> getSimpleName() {
        return GetSimpleName.INSTANCE;
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
    public static <T> Function<Class<?>, Class<? extends T>> asSubclass(Class<T> type) {
        return new AsSubClass<T>(type);
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
     * Returns a predicate which delegates to {@link Class#isAnnotation()}.
     * 
     * @since 1.8
     * @return a predicate which matches annotation types
     */
    public static Predicate<Class<?>> isAnnotation() {
        return IsAnnotation.INSTANCE;
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
     * Returns a predicate which delegates to {@link Modifier#isAbstract(int)}.
     * 
     * @since 1.8
     * @return a predicate which matches abstract classes
     */
    public static Rule<Class<?>> isAbstract() {
        return IsAbstract.INSTANCE;
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
     * Returns a predicate which matches super types of the specified
     * type.
     * 
     * @since 1.8
     * @param type the sub type
     * @return a predicate which matches super types of the given type
     * @throws NullPointerException if type is null
     */
    public static Predicate<Class<?>> isSupertypeOf(Class<?> type) {
        return new IsSuperTypeOf(type);
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
    public static Predicate<Class<?>> isSubtypeOf(Class<?> type) {
        return new IsSubTypeOf(type);
    }
    
    /**
     * Returns a predicate which delegates to {@link Class#isAnnotationPresent(Class)}.
     * 
     * @since 1.8
     * @param annotation the annotation
     * @return a predicate matching classes annotated with the specified annotation
     * @throws NullPointerException if annotation is null
     */
    public static Rule<Class<?>> isAnnotationPresent(Class<? extends Annotation> annotation) {
        return new IsAnnotationPresent(annotation);
    }
    
    /**
     * Returns an {@link Ordering} which uses string comparision of {@link Class#getName()}.
     * 
     * @since 1.9
     * @return an ordering for sorting by name
     */
    public static Ordering<Class<?>> orderByName() {
        return Reflection.ORDER_BY_NAME;
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
        return Reflection.ORDER_BY_HIERARCHY;
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

}
