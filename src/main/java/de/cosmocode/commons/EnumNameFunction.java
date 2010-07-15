package de.cosmocode.commons;

import com.google.common.base.Function;

/**
 * Implementation of {@link Enums#name()}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
enum EnumNameFunction implements Function<Enum<?>, String> {
    
    INSTANCE;
    
    @Override
    public String apply(Enum<?> from) {
        return from.name();
    }
    
    @Override
    public String toString() {
        return "Enums.name()";
    }
    
}
