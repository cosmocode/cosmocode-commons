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
package de.cosmocode.collections.utility;

import java.util.Map;

/**
 * Default implementation of the {@link UtilityMap}
 * interface delegating the entrySet creation
 * to {@link Utility#asUtilitySet(java.util.Set)}.
 * 
 * @author Willi Schoenborn
 * @param <K> the generic key type
 * @param <V> the generic value type
 */
class DefaultUtilityMap<K, V> extends AbstractUtilityMap<K, V> {

    private final Map<K, V> map;

    public DefaultUtilityMap(Map<K, V> map) {
        this.map = map;
    }

    @Override
    public V put(K key, V value) {
        return map.put(key, value);
    }
    
    @Override
    public UtilitySet<Map.Entry<K, V>> entrySet() {
        return Utility.asUtilitySet(map.entrySet());
    }

}
