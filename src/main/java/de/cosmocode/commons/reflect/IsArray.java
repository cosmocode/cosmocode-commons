package de.cosmocode.commons.reflect;

import de.cosmocode.commons.validation.AbstractRule;
import de.cosmocode.commons.validation.Rule;

/**
 * Singleton enum predicate for {@link Classes#isArray()}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
final class IsArray extends AbstractRule<Class<?>> {
    
    public static final Rule<Class<?>> INSTANCE = new IsArray();
    
    private IsArray() {
        
    }
    
    @Override
    public boolean apply(Class<?> input) {
        return input.isArray();
    }
    
    @Override
    public String toString() {
        return "Reflection.isArray()";
    }
    
}
