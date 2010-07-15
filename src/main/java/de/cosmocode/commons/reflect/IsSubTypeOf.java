package de.cosmocode.commons.reflect;

import com.google.common.base.Preconditions;

import de.cosmocode.commons.validation.AbstractRule;

/**
 * Implementation of {@link Reflection#isSubtypeOf(Class)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
final class IsSubTypeOf extends AbstractRule<Class<?>> {

    private final Class<?> type;
    
    public IsSubTypeOf(Class<?> type) {
        this.type = Preconditions.checkNotNull(type, "Type");
    }

    @Override
    public boolean apply(Class<?> input) {
        return type.isAssignableFrom(input);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof IsSubTypeOf) {
            final IsSubTypeOf other = IsSubTypeOf.class.cast(that);
            return type.equals(other.type);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return type.hashCode() ^ -35678874;
    }
    
    @Override
    public String toString() {
        return "Reflection.isSubTypeOf(" + type + ")";
    }
    
}
