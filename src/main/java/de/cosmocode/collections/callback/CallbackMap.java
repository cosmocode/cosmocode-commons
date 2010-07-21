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

package de.cosmocode.collections.callback;

import java.util.Collection;
import java.util.Map;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingMap;

/**
 * A composable {@link Map} which pokes the specified {@link Callback}
 * on structural changes.
 *
 * @author Willi Schoenborn
 * @param <K> the generic key type
 * @param <V> the generic value type
 */
final class CallbackMap<K, V> extends ForwardingMap<K, V> {

    private final Map<K, V> map;
    
    private final Callback callback;

    public CallbackMap(Map<K, V> map, Callback callback) {
        this.map = Preconditions.checkNotNull(map, "Map");
        this.callback = Preconditions.checkNotNull(callback, "Callback");
    }
    
    @Override
    protected Map<K, V> delegate() {
        return map;
    }
    
    @Override
    public void clear() {
        super.clear();
        callback.poke();
    }
    
    @Override
    public Set<Map.Entry<K, V>> entrySet() {
        return Callbacks.compose(super.entrySet(), callback);
    }
    
    @Override
    public Set<K> keySet() {
        return Callbacks.compose(super.keySet(), callback);
    }
    
    @Override
    public V put(K key, V value) {
        final V old = super.put(key, value);
        callback.poke();
        return old;
    };
    
    @Override
    public void putAll(Map<? extends K, ? extends V> m) {
        super.putAll(m);
        callback.poke();
    }
    
    @Override
    public V remove(Object object) {
        final V value = super.remove(object);
        callback.poke();
        return value;
    }
    
    @Override
    public Collection<V> values() {
        return Callbacks.compose(super.values(), callback);
    }
    
}
