package de.cosmocode.collections;

import java.util.Arrays;
import java.util.List;

import javax.annotation.Nonnull;

import com.google.common.base.Preconditions;

/**
 * Static utility class for {@link List}s.
 *
 * @since 1.20
 * @author Willi Schoenborn
 */
public final class MoreLists {
    
    private MoreLists() {
        
    }
    
    /**
     * Returns a list view on the given array which supports out-of-range index access.
     * 
     * <p>
     *   Consider the following examples:
     * </p>
     * <p>
     *   {@code MoreLists.cycle("a", "b", "c", "d", "e").get(5)} will return "a".<br />
     *   {@code MoreLists.cycle("a", "b", "c", "d", "e").get(7)} will return "c".<br />
     *   {@code MoreLists.cycle("a", "b", "c", "d", "e").get(18)} will return "d".<br />
     *   {@code MoreLists.cycle("a", "b", "c", "d", "e").get(-1)} will return "e".<br />
     *   {@code MoreLists.cycle("a", "b", "c", "d", "e").get(-9)} will return "b".<br />
     *   {@code MoreLists.cycle("a", "b", "c", "d", "e").subList(1, -1) will return ["b", "c", "d"]}<br />
     *   {@code MoreLists.cycle("a", "b", "c", "d", "e").subList(-1, 2) will return ["e", "a", "b"]}
     * </p>
     * 
     * This makes sure, no index passed to the list ever raises an {@link IndexOutOfBoundsException}.
     *
     * <p>
     *   Implementation note: The returned list is backed by {@link Arrays#asList(Object...)}.
     * </p>
     * 
     * @since 1.20
     * @param <E> the generic element type
     * @param array the backing array
     * @return a fixed size live view on the backing array, which supports out-of-range access
     */
    public static <E> List<E> cycle(@Nonnull E... array) {
        Preconditions.checkNotNull(array, "Array");
        return cycle(Arrays.asList(array));
    }

    /**
     * Returns a view on the given list which supports out-of-range index access.
     *
     * <p>
     *   Consider the following examples:
     * </p>
     * <p>
     *   {@code MoreLists.cycle(Arrays.asList("a", "b", "c", "d", "e")).get(5)} will return "a".<br />
     *   {@code MoreLists.cycle(Arrays.asList("a", "b", "c", "d", "e")).get(7)} will return "c".<br />
     *   {@code MoreLists.cycle(Arrays.asList("a", "b", "c", "d", "e")).get(18)} will return "d".<br />
     *   {@code MoreLists.cycle(Arrays.asList("a", "b", "c", "d", "e")).get(-1)} will return "e".<br />
     *   {@code MoreLists.cycle(Arrays.asList("a", "b", "c", "d", "e")).get(-9)} will return "b".<br />
     *   {@code MoreLists.cycle(Arrays.asList("a", "b", "c", "d", "e")).subList(1, -1) will return ["b", "c", "d"]}
     * </p>
     *
     * @since 1.20
     * @param <E> the generic element type
     * @param list the backig list
     * @return a live view on the backing list array, which supports out-of-range access
     */
    public static <E> List<E> cycle(@Nonnull List<E> list) {
        return new CyclingList<E>(list);
    }

}
