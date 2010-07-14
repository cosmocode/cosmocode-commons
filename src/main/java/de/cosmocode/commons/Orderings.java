package de.cosmocode.commons;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
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
    
    /**
     * Implementation for {@link Orderings#random()}.
     *
     * @since 1.9
     * @author Willi Schoenborn
     */
    private static final class RandomOrdering extends Ordering<Object> {
        
        private static final Ordering<Object> INSTANCE = new RandomOrdering();

        private final Random random = new Random();
        
        @Override
        public int compare(Object left, Object right) {
            // generate a random number between -1 and 1 (both inclusive)
            return random.nextInt(3) - 1;
        }
        
        @Override
        public <E> List<E> sortedCopy(Iterable<E> iterable) {
            final List<E> list = Lists.newArrayList(iterable);
            // let shuffle do the hard work
            Collections.shuffle(list, random);
            return list;
        }
        
        @Override
        public boolean isOrdered(Iterable<? extends Object> iterable) {
            // everything can be considered "ordered" randomly
            return true;
        }
        
        @Override
        public boolean isStrictlyOrdered(Iterable<? extends Object> iterable) {
            // everything can be considered "ordered" randomly
            return true;
        }
        
        @Override
        public Ordering<Object> reverse() {
            // no need to do something
            return this;
        }
        
        @Override
        public int binarySearch(List<? extends Object> sortedList, Object key) {
            // can't be optimized
            return sortedList.indexOf(key);
        }

        @Override
        public String toString() {
            return "Orderings.random()";
        }
        
    }
    
}
