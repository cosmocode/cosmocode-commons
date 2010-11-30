package de.cosmocode.collections;

import java.util.List;

import javax.annotation.Nonnull;

public final class MoreLists {
    
    private MoreLists() {
        
    }
    
    /**
     * Returns a view on the given list which supports out-of-range index access.
     * This makes sure, no index passed to the list ever raises
     * a {@link IndexOutOfBoundsException}.
     * 
     * @param <E> the generic element type
     * @param list the backing list
     * @return a live view on the backing list, which supports out-of-range access
     */
    public static <E> List<E> cycle(@Nonnull List<E> list) {
        return new CyclingList<E>(list);
    }

}
