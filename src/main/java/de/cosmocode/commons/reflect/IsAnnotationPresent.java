package de.cosmocode.commons.reflect;

import java.lang.annotation.Annotation;

import com.google.common.base.Preconditions;

import de.cosmocode.commons.validation.AbstractRule;

/**
 * Implementation for {@link Reflection#isAnnotationPresent(Class)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
final class IsAnnotationPresent extends AbstractRule<Class<?>> {

    private final Class<? extends Annotation> annotation;
    
    public IsAnnotationPresent(Class<? extends Annotation> annotation) {
        this.annotation = Preconditions.checkNotNull(annotation, "Annotation");
    }
    
    @Override
    public boolean apply(Class<?> input) {
        return input.isAnnotationPresent(annotation);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof IsAnnotationPresent) {
            final IsAnnotationPresent other = IsAnnotationPresent.class.cast(that);
            return annotation.equals(other.annotation);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return annotation.hashCode() ^ -652874548;
    }
    
    @Override
    public String toString() {
        return "Reflection.isAnnotationPresent(" + annotation.getName() + ")";
    }

}
