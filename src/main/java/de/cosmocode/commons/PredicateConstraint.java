package de.cosmocode.commons;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Constraint;

/**
 * Implementation of {@link Conditions#asContraint(Predicate)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <T> generic parameter type
 */
final class PredicateConstraint<T> implements Constraint<T> {
    
    private final Predicate<? super T> predicate;
    
    public PredicateConstraint(Predicate<? super T> predicate) {
        this.predicate = Preconditions.checkNotNull(predicate, "Predicate");
    }
    
    @Override
    public T checkElement(T element) {
        return Conditions.checkArgument(predicate, element);
    };
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof PredicateConstraint<?>) {
            final PredicateConstraint<?> other = PredicateConstraint.class.cast(that);
            return predicate.equals(other.predicate);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return predicate.hashCode();
    }

    @Override
    public String toString() {
        return "Conditions.asConstraint(" + predicate + ")";
    }
    
}
