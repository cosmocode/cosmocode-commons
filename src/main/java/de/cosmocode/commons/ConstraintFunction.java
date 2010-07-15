package de.cosmocode.commons;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Constraint;

/**
 * Implementation of {@link Conditions#asFunction(Constraint)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <T> generic parameter type
 */
final class ConstraintFunction<T> implements Function<T, T> {
    
    private final Constraint<T> constraint;
    
    public ConstraintFunction(Constraint<T> constraint) {
        this.constraint = Preconditions.checkNotNull(constraint, "Constraint");
    }
    
    @Override
    public T apply(T from) {
        return constraint.checkElement(from);
    };
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof ConstraintFunction<?>) {
            final ConstraintFunction<?> other = ConstraintFunction.class.cast(that);
            return constraint.equals(other.constraint);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return constraint.hashCode();
    }
    
    @Override
    public String toString() {
        return "Conditions.asFunction(" + constraint + ")";
    }
    
}
