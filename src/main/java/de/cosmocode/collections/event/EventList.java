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

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingList;
import com.google.common.collect.Lists;

/**
 * A {@link List} composed with an {@link EventListener}.
 *
 * @since 1.5
 * @author Willi Schoenborn
 * @param <E> generic element type
 */
final class EventList<E> extends ForwardingList<E> {

    private final List<E> list;
    
    private final EventListener<? super E> listener;

    public EventList(List<E> list, EventListener<? super E> listener) {
        this.list = Preconditions.checkNotNull(list, "List");
        this.listener = Preconditions.checkNotNull(listener, "Listener");
    }
    
    @Override
    protected List<E> delegate() {
        return list;
    }

    @Override
    public boolean add(E element) {
        final boolean added = super.add(element);
        if (added) {
            listener.added(element);
        }
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> elements) {
        boolean added = false;
        for (E element : elements) {
            added |= add(element);
        }
        return added;
    }

    @Override
    public void add(int index, E element) {
        super.add(index, element);
        listener.added(element);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> elements) {
        final boolean added = super.addAll(index, elements);
        for (E element : elements) {
            listener.added(element);
        }
        return added;
    }

    @Override
    public void clear() {
        final Collection<E> copy = Lists.newArrayList(delegate()); 
        super.clear();
        for (E element : copy) {
            listener.removed(element);
        }
    }

    @Override
    public ListIterator<E> listIterator() {
        return Events.compose(super.listIterator(), listener);
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return Events.compose(super.listIterator(index), listener);
    }

    @Override
    public Iterator<E> iterator() {
        return Events.compose(super.iterator(), listener);
    }

    @Override
    public boolean remove(Object object) {
        final boolean removed = super.remove(object);
        if (removed) {
            @SuppressWarnings("unchecked")
            final E element = (E) object;
            listener.removed(element);
        }
        return removed;
    }

    @Override
    public E remove(int index) {
        final E element = super.remove(index);
        listener.removed(element);
        return element;
    }

    @Override
    public boolean removeAll(Collection<?> elements) {
        boolean removed = false;
        for (Object element : elements) {
            removed |= remove(element);
        }
        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> elements) {
        final Collection<E> copy = Lists.newArrayList(delegate());
        // this leaves all elements in copy which will be deleted in our collection
        copy.removeAll(elements);
        final boolean retained = super.retainAll(elements);
        for (E element : copy) {
            listener.removed(element);
        }
        return retained;
    }

    @Override
    public E set(int index, E element) {
        final E removed = super.set(index, element);
        listener.removed(removed);
        listener.added(element);
        return removed;
    }

    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return Events.compose(super.subList(fromIndex, toIndex), listener);
    }
    
}
