package de.cosmocode.commons.validation;

import com.google.common.base.Preconditions;

/**
 * Implementation of {@link Rules#le(Comparable)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <C> comparable generic parameter type
 */
final class LeRule<C extends Comparable<C>> extends AbstractRule<C> {

    private final C comparable;
    
    public LeRule(C comparable) {
        this.comparable = Preconditions.checkNotNull(comparable, "Comparable");
    }

    @Override
    public boolean apply(C input) {
        return input.compareTo(comparable) <= 0;
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof LeRule<?>) {
            final LeRule<?> other = LeRule.class.cast(that);
            return comparable.equals(other.comparable);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return comparable.hashCode();
    }

    @Override
    public String toString() {
        return "Rules.le(" + comparable + ")";
    }

}
