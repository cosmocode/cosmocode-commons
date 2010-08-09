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

package de.cosmocode.collections.event;

import java.util.AbstractSet;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.ForwardingMap;
import com.google.common.collect.Iterators;
import com.google.common.collect.Maps;

/**
 * A {@link Map} composed with an {@link EventListener}.
 *
 * @since 1.5
 * @author Willi Schoenborn
 * @param <K> generic key type
 * @param <V> generic value type
 */
final class EventMap<K, V> extends ForwardingMap<K, V> {
    
    private final Function<Entry<K, V>, K> getKey = new Function<Entry<K, V>, K>() {

        @Override
        public K apply(Entry<K, V> entry) {
            return entry.getKey();
        }
        
    };
    
    private final Function<Entry<K, V>, V> getValue = new Function<Entry<K, V>, V>() {
        
        @Override
        public V apply(Entry<K, V> from) {
            return from.getValue();
        }
        
    };

    private final Map<K, V> map;
    
    private final EventListener<? super Entry<? extends K, ? extends V>> listener;

    public EventMap(Map<K, V> map, EventListener<? super Map.Entry<? extends K, ? extends V>> listener) {
        this.map = Preconditions.checkNotNull(map, "Map");
        this.listener = Preconditions.checkNotNull(listener, "Listener");
    }
    
    @Override
    protected Map<K, V> delegate() {
        return map;
    }

    @Override
    public void clear() {
        final Map<K, V> copy;
        
        if (map.isEmpty()) {
            copy = Collections.emptyMap();
        } else {
            copy = Maps.newHashMap(map);
        }
        
        super.clear();
        for (Entry<K, V> entry : copy.entrySet()) {
            listener.removed(entry);
        }
    }

    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return Events.compose(super.entrySet(), listener);
    }

    @Override
    public Set<K> keySet() {
        final Set<Entry<K, V>> entrySet = entrySet();
        // is ok, because the set returned by keySet() does not support add methods
        return new AbstractSet<K>() {

            @Override
            public Iterator<K> iterator() {
                return Iterators.transform(entrySet.iterator(), getKey);
            }

            @Override
            public int size() {
                return entrySet.size();
            }
            
        };
    }

    @Override
    public V put(K key, V value) {
        final boolean contained = containsKey(key);
        final V removed = super.put(key, value);
        // was the key present before?
        if (contained) {
            listener.removed(Maps.immutableEntry(key, removed));
        }
        // does the new value differ from the old one?
        if (removed != value) {
            listener.added(Maps.immutableEntry(key, value));
        }
        return removed;
    }

    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        for (Entry<? extends K, ? extends V> entry : m.entrySet()) {
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public V remove(Object object) {
        final boolean contained = containsKey(object);
        final V value = super.remove(object);
        if (contained) {
            @SuppressWarnings("unchecked")
            final K key = (K) object;
            listener.removed(Maps.immutableEntry(key, value));
        }
        return value;
    }

    @Override
    public Collection<V> values() {
        // is ok, because the collection returned by values() does not support add methods
        return Collections2.transform(entrySet(), getValue);
    }

}
