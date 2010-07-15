package de.cosmocode.commons.reflect;

import de.cosmocode.commons.validation.AbstractRule;
import de.cosmocode.commons.validation.Rule;

/**
 * Singleton enum predicate for {@link Classes#isAnnotation()}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
final class IsAnnotation extends AbstractRule<Class<?>> {
    
    public static final Rule<Class<?>> INSTANCE = new IsAnnotation();
    
    private IsAnnotation() {
        
    }
    
    @Override
    public boolean apply(Class<?> input) {
        return input.isAnnotation();
    }
    
    @Override
    public String toString() {
        return "Reflection.isAnnotation()";
    }
    
}
