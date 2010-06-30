package de.cosmocode.commons.validation;

import com.google.common.base.Preconditions;

/**
 * Implementation of {@link Rules#le(Comparable)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <C> comparable generic parameter type
 * @param <E> generic parameter type
 */
final class LeRule<C extends Comparable<E>, E> extends AbstractRule<C> {

    private final E value;
    
    public LeRule(E value) {
        this.value = Preconditions.checkNotNull(value, "Value");
    }

    @Override
    public boolean apply(C input) {
        return input.compareTo(value) <= 0;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof LeRule<?, ?>) {
            final LeRule<?, ?> other = LeRule.class.cast(that);
            return value.equals(other.value);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    @Override
    public String toString() {
        return "Rules.le(" + value + ")";
    }

}
