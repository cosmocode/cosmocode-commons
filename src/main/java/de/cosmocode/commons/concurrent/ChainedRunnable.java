package de.cosmocode.commons.concurrent;

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;

/**
 * Implementation for {@link Runnables#chain(Runnable, Runnable, Runnable...)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
final class ChainedRunnable implements Runnable {

    private static final Joiner JOINER = Joiner.on(", ");

    private final Iterable<Runnable> runnables;
        
    public ChainedRunnable(Iterable<Runnable> runnables) {
        this.runnables = Preconditions.checkNotNull(runnables, "Runnables");
        Preconditions.checkArgument(Iterables.all(runnables, Predicates.notNull()), "Found null in %s", runnables);
    }

    @Override
    public void run() {
        for (Runnable runnable : runnables) {
            runnable.run();
        }
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (this instanceof ChainedRunnable) {
            final ChainedRunnable other = ChainedRunnable.class.cast(that);
            return runnables.equals(other.runnables);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return runnables.hashCode();
    }
    
    @Override
    public String toString() {
        return "Runnables.chain(" + JOINER.join(runnables) + ")";
    }

}
