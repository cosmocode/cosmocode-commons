package de.cosmocode.commons.reflect;

import com.google.common.base.Preconditions;

import de.cosmocode.commons.validation.AbstractRule;

/**
 * Implementation for {@link Reflection#isSupertypeOf(Class)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
final class IsSuperTypeOf extends AbstractRule<Class<?>> {

    private final Class<?> type;
    
    public IsSuperTypeOf(Class<?> type) {
        this.type = Preconditions.checkNotNull(type, "Type");
    }

    @Override
    public boolean apply(Class<?> input) {
        return input.isAssignableFrom(type);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof IsSuperTypeOf) {
            final IsSuperTypeOf other = IsSuperTypeOf.class.cast(that);
            return type.equals(other.type);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return type.hashCode() ^ 45578782;
    }
    
    @Override
    public String toString() {
        return "Reflection.isSuperTypeOf(" + type + ")";
    }
    
}
