package de.cosmocode.commons.reflect;

import de.cosmocode.commons.Bijection;

/**
 * Implementation for {@link Classes#forName()}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
enum ForName implements Bijection<String, Class<?>> {
    
    INSTANCE;
    
    @Override
    public Class<?> apply(String name) {
        try {
            return Class.forName(name);
        } catch (ClassNotFoundException e) {
            throw new IllegalArgumentException(e);
        }
    }
    
    @Override
    public Bijection<Class<?>, String> inverse() {
        return GetName.INSTANCE;
    }
    
    @Override
    public String toString() {
        return "Reflection.forName()";
    }
    
}
