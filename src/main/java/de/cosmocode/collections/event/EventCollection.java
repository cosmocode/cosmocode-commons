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
package de.cosmocode.collections.event;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingCollection;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Iterator;

/**
 * A {@link Collection} composed with an {@link EventListener}.
 *
 * @since 1.5
 * @author Willi Schoenborn
 * @param <E> generic element type
 */
final class EventCollection<E> extends ForwardingCollection<E> {

    private final Collection<E> collection;
    
    private final EventListener<? super E> listener;

    public EventCollection(Collection<E> collection, EventListener<? super E> listener) {
        this.collection = Preconditions.checkNotNull(collection, "Collection");
        this.listener = Preconditions.checkNotNull(listener, "Listener");
    }
    
    @Override
    protected Collection<E> delegate() {
        return collection;
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
    public void clear() {
        final Collection<E> copy = Lists.newArrayList(delegate()); 
        super.clear();
        for (E element : copy) {
            listener.removed(element);
        }
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
    
}
