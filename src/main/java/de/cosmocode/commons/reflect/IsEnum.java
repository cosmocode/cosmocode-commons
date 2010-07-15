package de.cosmocode.commons.reflect;

import de.cosmocode.commons.validation.AbstractRule;
import de.cosmocode.commons.validation.Rule;

/**
 * Singleton enum predicate for {@link Classes#isEnum()}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
final class IsEnum extends AbstractRule<Class<?>> {
    
    public static final Rule<Class<?>> INSTANCE = new IsEnum();
    
    private IsEnum() {
        
    }
    
    @Override
    public boolean apply(Class<?> input) {
        return input.isEnum();
    }

    @Override
    public String toString() {
        return "Reflection.isEnum()";
    }
    
}
