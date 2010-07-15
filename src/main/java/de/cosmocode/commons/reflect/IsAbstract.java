package de.cosmocode.commons.reflect;

import java.lang.reflect.Modifier;

import de.cosmocode.commons.validation.AbstractRule;
import de.cosmocode.commons.validation.Rule;

/**
 * Singleton enum predicate for {@link Classes#isAbstract()}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
final class IsAbstract extends AbstractRule<Class<?>> {
    
    public static final Rule<Class<?>> INSTANCE = new IsAbstract();
    
    private IsAbstract() {
        
    }
    
    @Override
    public boolean apply(Class<?> input) {
        return Modifier.isAbstract(input.getModifiers());
    }
    
    @Override
    public String toString() {
        return "Reflection.isAbstract()";
    }
    
}
