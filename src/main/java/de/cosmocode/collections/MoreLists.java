/**
 * Copyright 2010 - 2013 CosmoCode GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.cosmocode.collections;

import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import java.util.Arrays;
import java.util.List;

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
     * This makes sure, no index passed to the list ever raises an {@link IndexOutOfBoundsException}.
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
