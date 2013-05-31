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
import com.google.common.collect.ForwardingSet;
import com.google.common.collect.Lists;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * A {@link Set} composed with an {@link EventListener}.
 *
 * @since 1.5
 * @author Willi Schoenborn
 * @param <E> generic element type
 */
final class EventSet<E> extends ForwardingSet<E> {

    private final Set<E> set;
    
    private final EventListener<? super E> listener;

    public EventSet(Set<E> set, EventListener<? super E> listener) {
        this.set = Preconditions.checkNotNull(set, "Set");
        this.listener = Preconditions.checkNotNull(listener, "Listener");
    }

    @Override
    protected Set<E> delegate() {
        return set;
    }

    @Override
    public boolean add(E element) {
        final boolean added = super.add(element);
        listener.added(element);
        return added;
    }

    @Override
    public boolean addAll(Collection<? extends E> elements) {
        final boolean added = super.addAll(elements);
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
    public Iterator<E> iterator() {
        return Events.compose(super.iterator(), listener);
    }

    @Override
    public boolean remove(Object object) {
        final boolean removed = super.remove(object);
        @SuppressWarnings("unchecked")
        final E element = (E) object;
        listener.removed(element);
        return removed;
    }

    @Override
    public boolean removeAll(Collection<?> elements) {
        final boolean removed = super.removeAll(elements);
        for (Object e : elements) {
            @SuppressWarnings("unchecked")
            final E element = (E) e;
            listener.removed(element);
        }
        return removed;
    }

    @Override
    public boolean retainAll(Collection<?> elements) {
        final Collection<E> copy = Lists.newArrayList(delegate());
        copy.removeAll(elements);
        final boolean retained = super.retainAll(elements);
        for (E element : copy) {
            listener.removed(element);
        }
        return retained;
    }

}
