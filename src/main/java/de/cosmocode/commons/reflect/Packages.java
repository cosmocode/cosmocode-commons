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
package de.cosmocode.commons.reflect;

import com.google.common.base.Predicate;

import java.lang.annotation.Annotation;

/**
 * An abstraction of multiple packages which
 * can be used to query classes using predicates/types/annotations.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
public interface Packages extends Iterable<Class<?>> {

    /**
     * Finds all subclasses of the specified type in this packages.
     * 
     * @since 1.8
     * @param <T> the generic class type
     * @param type the super type
     * @return an unmodifiable iterable over all found classes
     * @throws NullPointerException if type is null
     */
    <T> Iterable<Class<? extends T>> subclassesOf(Class<T> type);
    
    /**
     * Finds all classes annotated with the specified annotation in
     * this packages.
     * 
     * @since 1.8
     * @param annotation the type annotation
     * @return an unmodifiable iterable over all found classes
     * @throws NullPointerException if annotation is null
     */
    Iterable<Class<?>> annotatedWith(Class<? extends Annotation> annotation);
    
    /**
     * Finds all classes satisfying the specified predicate in
     * this packages.
     * 
     * @since 1.8
     * @param predicate the predicate which is used to match classes 
     * @return an unmodifiable iterable over all found classes
     * @throws NullPointerException if predicate is null
     */
    Iterable<Class<?>> filter(Predicate<? super Class<?>> predicate);
    
    /**
     * Finds all subclasses of the specified type which satisfy the specified
     * predicate in this packages.
     * 
     * @since 1.8
     * @param <T> the generic class type
     * @param type the super type
     * @param predicate the predicate which is used to match classes 
     * @return an unmodifiable iterable over all found classes
     * @throws NullPointerException if type or predicate is null
     */
    <T> Iterable<Class<? extends T>> filter(Class<T> type, Predicate<? super Class<? extends T>> predicate);
    
}
