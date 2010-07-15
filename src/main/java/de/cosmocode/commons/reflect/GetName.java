package de.cosmocode.commons.reflect;

import de.cosmocode.commons.Bijection;

/**
 * Implementation for {@link ForName#inverse()}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
enum GetName implements Bijection<Class<?>, String> {
    
    INSTANCE;
    
    @Override
    public String apply(Class<?> type) {
        return type.getName();
    };
    
    @Override
    public Bijection<String, Class<?>> inverse() {
        return ForName.INSTANCE;
    }
    
    @Override
    public String toString() {
        return "Reflection.getName()";
    }
    
}
