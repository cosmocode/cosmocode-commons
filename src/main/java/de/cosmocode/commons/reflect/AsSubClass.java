package de.cosmocode.commons.reflect;

import com.google.common.base.Preconditions;

import de.cosmocode.commons.AbstractFunction;

/**
 * Implementation for {@link Reflection#asSubclass(Class)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <T> generic class type
 */
final class AsSubClass<T> extends AbstractFunction<Class<?>, Class<? extends T>> {

    private final Class<T> type;
    
    public AsSubClass(Class<T> type) {
        this.type = Preconditions.checkNotNull(type, "Type");
    }
    
    @Override
    public Class<? extends T> apply(Class<?> input) {
        return input.asSubclass(type);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof AsSubClass<?>) {
            final AsSubClass<?> other = AsSubClass.class.cast(that);
            return type.equals(other.type);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return type.hashCode() ^ -987456325;
    }

    @Override
    public String toString() {
        return "Reflection.asSubclass(" + type + ")";
    }

}
