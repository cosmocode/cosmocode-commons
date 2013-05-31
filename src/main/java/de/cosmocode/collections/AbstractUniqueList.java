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

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;
import com.google.common.collect.ForwardingListIterator;
import com.google.common.collect.Iterables;

import java.util.Collection;
import java.util.ListIterator;

/**
 * Abstract skeleton implementation of the {@link UniqueList} interface.
 *
 * @since 1.5
 * @author Willi Schoenborn
 * @param <E> generic element type
 */
public abstract class AbstractUniqueList<E> extends RealAbstractList<E> implements UniqueList<E> {

    private final Predicate<E> contains = new Predicate<E>() {
        
        @Override
        public boolean apply(E input) {
            return contains(input);
        }
        
    };
    
    private final Predicate<E> notContains = Predicates.not(contains);
    
    @Override
    public boolean add(E e) {
        if (contains(e)) return false;
        add(size(), e);
        return true;
    }
    
    @Override
    public boolean addAll(int index, Collection<? extends E> c) {
        boolean changed = false;
        int i = index;
        for (E element : Iterables.filter(c, notContains)) {
            add(i++, element);
            changed = true;
        }
        return changed;
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return new SimpleListIterator(super.listIterator(index));
    }

    /**
     * Implementation of the {@link ListIterator} interface.
     *
     * @since 1.5
     * @author Willi Schoenborn
     */
    private final class SimpleListIterator extends ForwardingListIterator<E> {
        
        private final ListIterator<E> iterator;
        
        public SimpleListIterator(ListIterator<E> iterator) {
            this.iterator = iterator;
        }
        
        @Override
        protected ListIterator<E> delegate() {
            return iterator;
        }
        
        @Override
        public void add(E e) {
            if (contains(e)) return;
            super.add(e);
        }

        @Override
        public void set(E e) {
            Preconditions.checkArgument(!contains(e), "%s is already contained in %s", e, this);
            super.set(e);
        }

    }
    
}
