package de.cosmocode.commons;

import java.util.Comparator;

import com.google.common.collect.Ordering;

/**
 * Utility class for {@link Ordering}s.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
public final class Orderings {

    private Orderings() {
        
    }

    /**
     * Returns a shuffling comparator which returns a
     * random int between -1 and 1 (both inclusive)
     * on every call of the {@link Comparator#compare(Object, Object)}
     * method call.
     * 
     * @return a comparator which returns randomly generated results
     */
    public static Ordering<Object> random() {
        return RandomOrdering.INSTANCE;
    }
    
}
