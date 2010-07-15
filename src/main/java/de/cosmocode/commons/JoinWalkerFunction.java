package de.cosmocode.commons;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

import de.cosmocode.patterns.Adapter;

/**
 * An {@link Adapter} from {@link JoinWalker} to {@link Function}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <F> generic parameter type
 */
@SuppressWarnings("deprecation")
final class JoinWalkerFunction<F> extends AbstractFunction<F, String> {

    private final JoinWalker<? super F> walker;
    
    public JoinWalkerFunction(JoinWalker<? super F> walker) {
        this.walker = Preconditions.checkNotNull(walker, "Walker");
    }
    
    @Override 
    public String apply(F from) {
        return walker.walk(from);
    };

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof JoinWalkerFunction<?>) {
            final JoinWalkerFunction<?> other = JoinWalkerFunction.class.cast(that);
            return walker.equals(other.walker);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return walker.hashCode() ^ -921210296;
    }
    
    @Override
    public String toString() {
        return "JoinWalkers.asFunction(" + walker + ")";
    }
    
}
