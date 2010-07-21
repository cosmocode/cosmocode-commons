/**
 * Copyright 2010 CosmoCode GmbH
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

package de.cosmocode.collections.utility;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

/**
 * Static factory providing methods
 * which return utility views on all kind of
 * collections. All of the following interfaces
 * are supported:
 * <ul>
 *   <li>{@link UtilityIterable}</li>
 *   <li>{@link UtilityIterator}</li>
 *   <li>{@link UtilityCollection}</li>
 *   <li>{@link UtilitySet}</li>
 *   <li>{@link UtilityList}</li>
 *   <li>{@link UtilityListIterator}</li>
 *   <li>{@link UtilityMap}</li>
 * </ul>
 * 
 * @author Willi Schoenborn
 */
public final class Utility {

    /**
     * Prevent instantiation.
     */
    private Utility() {
        
    }

    /**
     * Creates a {@link UtilityIterable} view on an {@link Iterable}.
     * 
     * @param <E> the generic element type
     * @param iterable the {@link Iterable} which will used as a {@link UtilityIterable}
     * @return a new {@link UtilityIterable} which will be backed by the given {@link Iterable} or
     *         iterable if it is already an instance of {@link UtilityIterable}
     * @throws NullPointerException if iterable is null
     */
    public static <E> UtilityIterable<E> asUtilityIterable(Iterable<E> iterable) {
        if (iterable == null) throw new NullPointerException("Iterable must not be null");
        if (iterable instanceof UtilityIterable<?>) {
            return (UtilityIterable<E>) iterable;
        } else {
            return new DefaultUtilityIterable<E>(iterable);
        }
    }
    
    /**
     * Creates a {@link UtilityIterator} view on an {@link Iterator}.
     * 
     * @param <E> the generic element type
     * @param iterator the {@link Iterator} which will be used as a {@link UtilityIterator}
     * @return a new {@link UtilityIterator} which will be backed by the given {@link Iterator} or
     *         iterator if it is already an instance of {@link UtilityIterator}
     * @throws NullPointerException if iterator is null
     */
    public static <E> UtilityIterator<E> asUtilityIterator(Iterator<E> iterator) {
        if (iterator == null) throw new NullPointerException("Iterator must not be null");
        if (iterator instanceof UtilityIterator<?>) {
            return (UtilityIterator<E>) iterator;
        } else {
            return new DefaultUtilityIterator<E>(iterator);
        }
    }
    
    /**
     * Creates a {@link UtilityCollection} view on a {@link Collection}.
     * 
     * @param <E> the generic element type
     * @param collection the {@link Collection} which will be used as a {@link UtilityCollection}
     * @return a new {@link UtilityCollection} which will be backed by the given {@link Collection} or
     *         collection if it is already an instance of {@link UtilityCollection}
     * @throws NullPointerException if collection is null
     */
    public static <E> UtilityCollection<E> asUtilityCollection(Collection<E> collection) {
        if (collection == null) throw new NullPointerException("Collection must not be null");
        if (collection instanceof UtilityCollection<?>) {
            return (UtilityCollection<E>) collection;
        } else {
            return new DefaultUtilityCollection<E>(collection);
        }
    }
    
    /**
     * Creates a {@link UtilitySet} view on a {@link Set}.
     * 
     * @param <E> the generic element type
     * @param set the {@link Set} which will be used as a {@link UtilitySet}
     * @return a new {@link UtilitySet} which will be backed by the given {@link Set} or
     *         set if it is already an instance of {@link UtilitySet}
     * @throws NullPointerException if set is null
     */
    public static <E> UtilitySet<E> asUtilitySet(Set<E> set) {
        if (set == null) throw new NullPointerException("Set must not be null");
        if (set instanceof UtilitySet<?>) {
            return (UtilitySet<E>) set;
        } else {
            return new DefaultUtilitySet<E>(set);
        }
    }
    
    /**
     * Creates a {@link UtilityList} view on a {@link List}.
     * 
     * @param <E> the generic element type
     * @param list the {@link List} which will be used as a {@link UtilityList}
     * @return a new {@link UtilityList} which will be backed by the given {@link List} or
     *         list if it is already an instance of {@link UtilityList}
     * @throws NullPointerException if list is null
     */
    public static <E> UtilityList<E> asUtilityList(List<E> list) {
        if (list == null) throw new NullPointerException("List must not be null");
        if (list instanceof UtilityList<?>) {
            return (UtilityList<E>) list;
        } else {
            return new DefaultUtilityList<E>(list);
        }
    }
    
    /**
     * Creates a {@link UtilityListIterator} view on a {@link ListIterator}.
     * 
     * @param <E> the generic element type
     * @param listIterator the {@link ListIterator} which will be used as a {@link UtilityListIterator}
     * @return a new {@link UtilityListIterator} which will be backed by the given {@link ListIterator} or
     *         listIerator if it is already an instance of {@link UtilityListIterator}
     * @throws NullPointerException if listIterator is null
     */
    public static <E> UtilityListIterator<E> asUtilityListIterator(ListIterator<E> listIterator) {
        if (listIterator == null) throw new NullPointerException("ListIterator must not be null");
        if (listIterator instanceof UtilityListIterator<?>) {
            return (UtilityListIterator<E>) listIterator;
        } else {
            return new DefaultUtilityListIterator<E>(listIterator);
        }
    }
    
    /**
     * Creates a {@link UtilityMap} view on a {@link Map}.
     * 
     * @param <K> the generic key type
     * @param <V> the generic value type
     * @param map the {@link Map} which will be used as a {@link UtilityMap}
     * @return a new {@link UtilityMap} which will be backed by the given {@link Map} or
     *         map if it is already an instance of {@link UtilityMap}
     * @throws NullPointerException if map is null
     */
    public static <K, V> UtilityMap<K, V> asUtilityMap(Map<K, V> map) {
        if (map == null) throw new NullPointerException("Map must not be null");
        if (map instanceof UtilityMap<?, ?>) {
            return (UtilityMap<K, V>) map;
        } else {
            return new DefaultUtilityMap<K, V>(map);
        }
    }
    
    /**
     * Creates a new {@link UtilitySet}.
     * 
     * @param <E> the generic element type
     * @return a new {@link UtilitySet}
     */
    public static <E> UtilitySet<E> createUtilitySet() {
        final Set<E> set = Sets.newHashSet();
        return Utility.asUtilitySet(set);
    }
    
    /**
     * Creates a new {@link UtilityList}.
     * 
     * @param <E> the generic element type
     * @return a new {@link UtilityList}
     */
    public static <E> UtilityList<E> createUtilityList() {
        final List<E> list = Lists.newArrayList();
        return Utility.asUtilityList(list);
    }
    
    /**
     * Creates a new {@link UtilityMap}.
     * 
     * @param <K> the generic key type
     * @param <V> the generic value type
     * @return a new {@link UtilityMap}
     */
    public static <K, V> UtilityMap<K, V> createUtilityMap() {
        final Map<K, V> map = Maps.newHashMap();
        return Utility.asUtilityMap(map);
    }
    
    /**
     * Creates a new {@link UtilityCollection}.
     * 
     * @param <E> the generic element type
     * @return a new {@link UtilityCollection}
     */
    public static <E> UtilityCollection<E> createUtilityCollection() {
        return Utility.<E>createUtilityList();
    }
    
}
