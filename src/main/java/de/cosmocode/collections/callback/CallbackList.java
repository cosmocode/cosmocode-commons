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

package de.cosmocode.collections.callback;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingList;

/**
 * A composable {@link List} which pokes the specified {@link Callback}
 * on structural changes.
 *
 * @author Willi Schoenborn
 * @param <E> the generic element type
 */
final class CallbackList<E> extends ForwardingList<E> {

    private final List<E> list;
    
    private final Callback callback;

    public CallbackList(List<E> list, Callback callback) {
        this.list = Preconditions.checkNotNull(list, "List");
        this.callback = Preconditions.checkNotNull(callback, "Callback");
    }
    
    @Override
    protected List<E> delegate() {
        return list;
    }
    
    @Override
    public boolean add(E element) {
        final boolean added = super.add(element);
        callback.poke();
        return added;
    }
    
    @Override
    public void add(int index, E element) {
        super.add(index, element);
        callback.poke();
    }
   
    @Override
    public boolean addAll(Collection<? extends E> c) {
        final boolean added = super.addAll(c);
        callback.poke();
        return added;
    }
    
    @Override
    public boolean addAll(int index, Collection<? extends E> elements) {
        final boolean added = super.addAll(index, elements);
        callback.poke();
        return added;
    }
    
    @Override
    public void clear() {
        super.clear();
        callback.poke();
    }
    
    @Override
    public Iterator<E> iterator() {
        return Callbacks.compose(super.iterator(), callback);
    }
    
    @Override
    public ListIterator<E> listIterator() {
        return Callbacks.compose(super.listIterator(), callback);
    }
    
    @Override
    public ListIterator<E> listIterator(int index) {
        return Callbacks.compose(super.listIterator(index), callback);
    }
    
    @Override
    public boolean remove(Object object) {
        final boolean removed = super.remove(object);
        callback.poke();
        return removed;
    }
    
    @Override
    public E remove(int index) {
        final E value = super.remove(index);
        callback.poke();
        return value;
    }
    
    @Override
    public boolean removeAll(Collection<?> c) {
        final boolean removed = super.removeAll(c);
        callback.poke();
        return removed;
    }
    
    @Override
    public boolean retainAll(Collection<?> c) {
        final boolean retained = super.retainAll(c);
        callback.poke();
        return retained;
    }

    @Override
    public E set(int index, E element) {
        final E value = super.set(index, element);
        callback.poke();
        return value;
    }
    
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        return Callbacks.compose(super.subList(fromIndex, toIndex), callback);
    }
    
}
