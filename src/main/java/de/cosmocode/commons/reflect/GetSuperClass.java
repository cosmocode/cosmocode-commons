package de.cosmocode.commons.reflect;

import com.google.common.base.Function;

/**
 * Implementation for {@link Reflection#getSuperClass()}.
 *
 * @since 2.0
 * @author Willi Schoenborn
 */
enum GetSuperClass implements Function<Class<?>, Class<?>> {

    INSTANCE;
    
    @Override
    public Class<?> apply(Class<?> from) {
        return from.getSuperclass();
    }
    
}
