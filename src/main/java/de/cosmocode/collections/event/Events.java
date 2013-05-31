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
package de.cosmocode.collections.event;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

/**
 * Static factory class for {@link EventListener}s and {@link Collection}s.
 *
 * @since 1.5
 * @author Willi Schoenborn
 */
public final class Events {

    private Events() {
        
    }
    
    /**
     * Returns the composition of the given iterable and listener.
     * 
     * @since 1.5
     * @param <E> generic element type
     * @param iterable the backing iterable
     * @param listener the custom event listener
     * @return an iterable which propagates adds/removes to the specified listener
     * @throws NullPointerException if iterable or listener is null
     */
    public static <E> Iterable<E> compose(Iterable<? extends E> iterable, EventListener<? super E> listener) {
        return new EventIterable<E>(iterable, listener);
    }

    /**
     * Returns the composition of the given iterator and listener.
     * 
     * @since 1.5
     * @param <E> generic element type
     * @param iterator the backing iterator
     * @param listener the custom event listener
     * @return an iterator which propagates adds/removes to the specified listener
     * @throws NullPointerException if iterator or listener is null
     */
    public static <E> Iterator<E> compose(Iterator<? extends E> iterator, EventListener<? super E> listener) {
        return new EventIterator<E>(iterator, listener);
    }

    /**
     * Returns the composition of the given collection and listener.
     * 
     * @since 1.5
     * @param <E> generic element type
     * @param collection the backing collection
     * @param listener the custom event listener
     * @return a collection which propagates adds/removes to the specified listener
     * @throws NullPointerException if collection or listener is null
     */
    public static <E> Collection<E> compose(Collection<E> collection, EventListener<? super E> listener) {
        return new EventCollection<E>(collection, listener);
    }

    /**
     * Returns the composition of the given list and listener.
     * 
     * @since 1.5
     * @param <E> generic element type
     * @param list the backing list
     * @param listener the custom event listener
     * @return a list which propagates adds/removes to the specified listener
     * @throws NullPointerException if list or listener is null
     */
    public static <E> List<E> compose(List<E> list, EventListener<? super E> listener) {
        return new EventList<E>(list, listener);
    }

    /**
     * Returns the composition of the given list iterator and listener.
     * 
     * @since 1.5
     * @param <E> generic element type
     * @param iterator the backing iterator
     * @param listener the custom event listener
     * @return a iterator which propagates adds/removes to the specified listener
     * @throws NullPointerException if iterator or listener is null
     */
    public static <E> ListIterator<E> compose(ListIterator<E> iterator, EventListener<? super E> listener) {
        return new EventListIterator<E>(iterator, listener);
    }

    /**
     * Returns the composition of the given set and listener.
     * 
     * @since 1.5
     * @param <E> generic element type
     * @param set the backing set
     * @param listener the custom event listener
     * @return a set which propagates adds/removes to the specified listener
     * @throws NullPointerException if set or listener is null
     */
    public static <E> Set<E> compose(Set<E> set, EventListener<? super E> listener) {
        return new EventSet<E>(set, listener);
    }

    /**
     * Returns the composition of the given map and listener.
     * 
     * @since 1.5
     * @param <K> generic key type
     * @param <V> generic value type
     * @param map the backing map
     * @param listener the custom event listener
     * @return a map which propagates adds/removes to the specified listener
     * @throws NullPointerException if map or listener is null
     */
    public static <K, V> Map<K, V> compose(Map<K, V> map, 
        EventListener<? super Entry<? extends K, ? extends V>> listener) {
        return new EventMap<K, V>(map, listener);
    }
    
}
