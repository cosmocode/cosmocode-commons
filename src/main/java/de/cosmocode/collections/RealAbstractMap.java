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

import java.util.AbstractMap;
import java.util.Map;

/**
 * Abstract base implementation of the {@link Map} interface which extends
 * {@link AbstractMap} and makes all methods abstract which would throw an
 * {@link UnsupportedOperationException}.
 *
 * @author Willi Schoenborn
 * @param <K> the generic key type
 * @param <V> the generic value type
 */
public abstract class RealAbstractMap<K, V> extends AbstractMap<K, V> {

    @Override
    public abstract V put(K key, V value);

}
