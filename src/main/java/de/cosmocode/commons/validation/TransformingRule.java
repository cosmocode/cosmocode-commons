package de.cosmocode.commons.validation;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * Implementation of {@link Rule#transform(Function)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <T> generic parameter type
 */
final class TransformingRule<T> extends AbstractRule<T> {

    private final Rule<T> rule;
    private final Function<? super T, ? extends T> function;
    
    public TransformingRule(Rule<T> rule, Function<? super T, ? extends T> function) {
        this.rule = Preconditions.checkNotNull(rule, "Rule");
        this.function = Preconditions.checkNotNull(function, "Function");
    }

    @Override
    protected T transform(T element) {
        return function.apply(element);
    };
    
    @Override
    public boolean apply(T input) {
        return rule.apply(input);
    };
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof TransformingRule<?>) {
            final TransformingRule<?> other = TransformingRule.class.cast(that);
            return rule.equals(other.rule) && function.equals(other.function);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(rule, function);
    }
    
    @Override
    public String toString() {
        return rule + ".transform(" + function + ")";
    }
    
}
