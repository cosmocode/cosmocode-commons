package de.cosmocode.commons;

import com.google.common.base.Preconditions;

/**
 * Implementation of {@link Enums#name(Class)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <E> generic enum type
 */
final class EnumNameCodec<E extends Enum<E>> extends Codec<E, String> {
    
    private final Class<E> type;
    
    public EnumNameCodec(Class<E> type) {
        this.type = Preconditions.checkNotNull(type, "Type");
    }

    @Override
    public String encode(E input) {
        return input.name();
    }

    @Override
    public E decode(String input) {
        return Enum.valueOf(type, input);
    }

    @Override
    public int hashCode() {
        return type.hashCode() ^ -921210296;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof EnumNameCodec<?>) {
            final EnumNameCodec<?> other = EnumNameCodec.class.cast(that);
            return type.equals(other.type);
        } else {
            return false;
        }
    }

    @Override
    public String toString() {
        return "Enums.name(" + type.getName() + ")";
    }
    
}
