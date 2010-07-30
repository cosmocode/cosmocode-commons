package de.cosmocode.commons.reflect;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;

/**
 * Implementation of {@link Reflection#getAllInterfaces()}.
 *
 * @since 1.12
 * @author Willi Schoenborn
 */
enum GetAllInterfaces implements Function<Class<?>, Iterable<Class<?>>> {

    INSTANCE;
    
    @Override
    public Iterable<Class<?>> apply(Class<?> from) {
        return Iterables.filter(Reflection.getAllSuperTypes(from), Reflection.isInterface());
    }
    
}
