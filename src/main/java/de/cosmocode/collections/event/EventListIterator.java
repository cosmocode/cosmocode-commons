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
import com.google.common.collect.ForwardingListIterator;

import java.util.ListIterator;

/**
 * A {@link ListIterator} composed with an {@link EventListener}.
 *
 * @since 1.5
 * @author Willi Schoenborn
 * @param <E> generic element type
 */
final class EventListIterator<E> extends ForwardingListIterator<E> {

    private final ListIterator<E> iterator;
    
    private final EventListener<? super E> listener;
    
    private E last;

    public EventListIterator(ListIterator<E> iterator, EventListener<? super E> listener) {
        this.iterator = Preconditions.checkNotNull(iterator, "Iterator");
        this.listener = Preconditions.checkNotNull(listener, "Listener");
    }
    
    @Override
    protected ListIterator<E> delegate() {
        return iterator;
    }

    @Override
    public void add(E element) {
        super.add(element);
        listener.added(element);
    }

    @Override
    public E previous() {
        final E previous = super.previous();
        last = previous;
        return previous;
    }

    @Override
    public void set(E element) {
        super.set(element);
        listener.removed(last);
        last = element;
        listener.added(element);
    }

    @Override
    public E next() {
        final E next = super.next();
        last = next;
        return next;
    }

    @Override
    public void remove() {
        super.remove();
        listener.removed(last);
        // prevent memory leak
        last = null;
    }

}
