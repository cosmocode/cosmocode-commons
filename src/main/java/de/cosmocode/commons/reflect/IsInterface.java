package de.cosmocode.commons.reflect;

import de.cosmocode.commons.validation.AbstractRule;
import de.cosmocode.commons.validation.Rule;

/**
 * Singleton enum predicate for {@link Classes#isInterface()}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
final class IsInterface extends AbstractRule<Class<?>> {
    
    public static final Rule<Class<?>> INSTANCE = new IsInterface();
    
    private IsInterface() {
        
    }
    
    @Override
    public boolean apply(Class<?> input) {
        return input.isInterface();
    }
    
    @Override
    public String toString() {
        return "Reflection.isInterface()";
    }
    
}
