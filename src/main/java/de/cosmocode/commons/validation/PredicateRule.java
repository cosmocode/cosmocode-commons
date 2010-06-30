package de.cosmocode.commons.validation;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * Implementation of {@link Rules#asRule(Predicate)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <T> generic parameter type
 */
final class PredicateRule<T> extends AbstractRule<T> {
    
    private final Predicate<? super T> predicate;
    
    public PredicateRule(Predicate<? super T> predicate) {
        this.predicate = Preconditions.checkNotNull(predicate, "Predicate");
    }

    @Override
    public boolean apply(T input) {
        return predicate.apply(input);
    }
    
    @Override
    public String toString() {
        return "Rules.asRule(" + predicate + ")";
    }
    
}
