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

import com.google.common.collect.ForwardingCollection;

import java.util.Collection;

/**
 * Default implementation of the {@link UtilityCollection}
 * interface delegating the iterator implementation
 * to {@link Utility#asUtilityIterator(java.util.Iterator)}.
 *
 * @author Willi Schoenborn
 * @param <E> the generic element type
 */
class DefaultUtilityCollection<E> extends ForwardingCollection<E> implements UtilityCollection<E> {

    private final Collection<E> collection;
    
    public DefaultUtilityCollection(Collection<E> collection) {
        this.collection = collection;
    }

    @Override
    protected Collection<E> delegate() {
        return collection;
    }

    @Override
    public UtilityIterator<E> iterator() {
        return Utility.asUtilityIterator(super.iterator());
    }
    
}
