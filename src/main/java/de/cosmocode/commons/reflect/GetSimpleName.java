package de.cosmocode.commons.reflect;

import com.google.common.base.Function;

/**
 * Implementation for {@link Classes#getSimpleName()}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
enum GetSimpleName implements Function<Class<?>, String> {
    
    INSTANCE;
    
    @Override
    public String apply(Class<?> type) {
        return type.getSimpleName();
    }
    
    @Override
    public String toString() {
        return "Reflection.getSimpleName()";
    }
    
}
