package de.cosmocode.commons.reflect;

import java.lang.annotation.Annotation;

import com.google.common.base.Predicate;

public interface Packages extends Iterable<Class<?>> {

    <T> Iterable<Class<? extends T>> subclassesOf(Class<T> type);
    
    Iterable<Class<?>> annotatedWith(Class<? extends Annotation> annotation);
    
    Iterable<Class<?>> filter(Predicate<? super Class<?>> predicate);
    
    <T> Iterable<Class<? extends T>> filter(Class<T> type, Predicate<? super Class<? extends T>> predicate);
    
}
