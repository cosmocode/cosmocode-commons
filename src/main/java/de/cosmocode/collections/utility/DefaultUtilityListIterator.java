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

import java.util.ListIterator;

/**
 * Default implementation of the {@link UtilityListIterator}
 * interface.
 *
 * @author Willi Schoenborn
 * @param <E> the generic element type
 */
class DefaultUtilityListIterator<E> extends AbstractUtilityListIterator<E> {

    private final ListIterator<E> listIterator;
    
    public DefaultUtilityListIterator(ListIterator<E> listIterator) {
        this.listIterator = listIterator;
    }

    @Override
    public void add(E e) {
        listIterator.add(e);
    }

    @Override
    public boolean hasNext() {
        return listIterator.hasNext();
    }

    @Override
    public boolean hasPrevious() {
        return listIterator.hasPrevious();
    }

    @Override
    public E next() {
        return listIterator.next();
    }

    @Override
    public int nextIndex() {
        return listIterator.nextIndex();
    }

    @Override
    public E previous() {
        return listIterator.previous();
    }

    @Override
    public int previousIndex() {
        return listIterator.previousIndex();
    }

    @Override
    public void remove() {
        listIterator.remove();
    }

    @Override
    public void set(E e) {
        listIterator.set(e);
    }

}
