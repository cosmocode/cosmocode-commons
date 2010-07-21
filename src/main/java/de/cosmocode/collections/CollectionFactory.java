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

package de.cosmocode.collections;

import java.util.Map;

import com.google.common.base.Function;
import com.google.common.collect.Maps;

/**
 * A static factory to provide short names
 * for all useful kinds of collections.
 * 
 * @author Willi Schoenborn
 */
public final class CollectionFactory {

    private static final Object[] EMPTY_ARRAY = {};
    
    /**
     * Prevent instantiation.
     */
    private CollectionFactory() {
        
    }
    
    /**
     * Returns an empty and immutable generic array.
     * 
     * @param <E> the generic element type
     * @return always the same empty generic array
     */
    // Suppress is ok, because the array will never hold any elements
    @SuppressWarnings("unchecked")
    public static <E> E[] emptyArray() {
        return (E[]) EMPTY_ARRAY;
    }

    /**
     * Transforms the keys of a map.
     * 
     * @param <K> the generic key type of the given map
     * @param <V> the generic value type of the given map
     * @param <N> the generic key type of the map being returned
     * @param from the map being used for transformation
     * @param function a {@link Function} used to convert every key in from
     * @return the transformed map
     */
    public static <K, V, N> Map<N, V> transformKeys(Map<K, V> from, Function<? super K, ? extends N> function) {
        final Map<N, V> to = Maps.newHashMap();
        for (Map.Entry<K, V> entry : from.entrySet()) {
            final N key = function.apply(entry.getKey());
            final V value = entry.getValue();
            to.put(key, value);
        }
        return to;
    }
    
    /**
     * Transforms the entries of a map.
     * 
     * @param <K> the generic key type of the given map
     * @param <V> the generic value type of the given map
     * @param <N> the generic key type of the map being returned
     * @param <W> the generic value type of the map being returned
     * @param from the map being used for transformation
     * @param keys a {@link Function} used to convert every key in from
     * @param values a {@link Function} used to convert every value in from
     * @return the transformed map
     */
    public static <K, V, N, W> Map<N, W> transform(Map<K, V> from, 
        Function<? super K, ? extends N> keys, Function<? super V, ?extends W> values) {
        
        final Map<N, W> to = Maps.newHashMap();
        
        for (Map.Entry<K, V> entry : from.entrySet()) {
            final N key = keys.apply(entry.getKey());
            final W value = values.apply(entry.getValue());
            to.put(key, value);
        }
        
        return to;
    }
    
}
