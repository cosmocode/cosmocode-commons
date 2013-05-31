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

import java.util.Iterator;

/**
 * Abstract skeleton implementation of the {@link UtilityIterator} interface
 * delegating all conversion calls to {@link Convert}.
 * 
 * @author Willi Schoenborn
 *
 * @param <E> the generic element type
 */
class DefaultUtilityIterator<E> extends AbstractUtilityIterator<E> {

    private final Iterator<E> iterator;

    /**
     * Creates a {@link DefaultUtilityIterator} backed
     * by a {@link Iterator}.
     * 
     * @param iterator the {@link Iterator} instance this {@link UtilityIterator}
     *        will be backed by
     */
    public DefaultUtilityIterator(Iterator<E> iterator) {
        this.iterator = iterator;
    }

    @Override
    public boolean hasNext() {
        return iterator.hasNext();
    }

    @Override
    public E next() {
        return iterator.next();
    }

    @Override
    public void remove() {
        iterator.remove();
    }

}
