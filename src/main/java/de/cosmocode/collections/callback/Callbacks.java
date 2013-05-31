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
package de.cosmocode.collections.callback;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Set;

/**
 * Static utility class which allows to build callback based collections
 * to register custom callbacks which will be triggered on structural changes.
 *
 * @author Willi Schoenborn
 */
public final class Callbacks {

    private Callbacks() {

    }
    
    /**
     * Returns the composition of the given collection and callback.
     * <p>
     *   Note: Any changes to the original collection will cause the returned
     *   collection to change and vice versa. But direct changes on the source
     *   will not be propagated with the callback.
     * </p>
     * 
     * @param <E> the generic element type
     * @param collection the underlying collection
     * @param callback the custom callback
     * @return a collection which propagates strucutural changes using the specified callback
     */
    public static <E> Collection<E> compose(Collection<E> collection, Callback callback) {
        return new CallbackCollection<E>(collection, callback);
    }

    /**
     * Returns the composition of the given iterator and callback.
     * <p>
     *   Note: Any changes to the original iterator will cause the returned
     *   iterator to change and vice versa. But direct changes on the source
     *   will not be propagated with the callback.
     * </p>
     * 
     * @param <E> the generic element type
     * @param iterator the underlying iterator
     * @param callback the custom callback
     * @return a iterator which propagates strucutural changes using the specified callback
     */
    public static <E> Iterator<E> compose(Iterator<E> iterator, Callback callback) {
        return new CallbackIterator<E>(iterator, callback);
    }

    /**
     * Returns the composition of the given list and callback.
     * <p>
     *   Note: Any changes to the original list will cause the returned
     *   list to change and vice versa. But direct changes on the source
     *   will not be propagated with the callback.
     * </p>
     * 
     * @param <E> the generic element type
     * @param list the underlying list
     * @param callback the custom callback
     * @return a list which propagates strucutural changes using the specified callback
     */
    public static <E> List<E> compose(List<E> list, Callback callback) {
        return new CallbackList<E>(list, callback);
    }

    /**
     * Returns the composition of the given iterator and callback.
     * <p>
     *   Note: Any changes to the original iterator will cause the returned
     *   iterator to change and vice versa. But direct changes on the source
     *   will not be propagated with the callback.
     * </p>
     * 
     * @param <E> the generic element type
     * @param listIterator the underlying iterator
     * @param callback the custom callback
     * @return a iterator which propagates strucutural changes using the specified callback
     */
    public static <E> ListIterator<E> compose(ListIterator<E> listIterator, Callback callback) {
        return new CallbackListIterator<E>(listIterator, callback);
    }

    /**
     * Returns the composition of the given set and callback.
     * <p>
     *   Note: Any changes to the original set will cause the returned
     *   set to change and vice versa. But direct changes on the source
     *   will not be propagated with the callback.
     * </p>
     * 
     * @param <E> the generic element type
     * @param set the underlying set
     * @param callback the custom callback
     * @return a set which propagates strucutural changes using the specified callback
     */
    public static <E> Set<E> compose(Set<E> set, Callback callback) {
        return new CallbackSet<E>(set, callback);
    }

    /**
     * Returns the composition of the given map and callback.
     * <p>
     *   Note: Any changes to the original map will cause the returned
     *   map to change and vice versa. But direct changes on the source
     *   will not be propagated with the callback.
     * </p>
     * 
     * @param <K> the generic key type
     * @param <V> the generic value type
     * @param map the underlying map
     * @param callback the custom callback
     * @return a map which propagates strucutural changes using the specified callback
     */
    public static <K, V> Map<K, V> compose(Map<K, V> map, Callback callback) {
        return new CallbackMap<K, V>(map, callback);
    }
    
}
