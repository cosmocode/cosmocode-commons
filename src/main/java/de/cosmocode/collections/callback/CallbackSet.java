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
package de.cosmocode.collections.callback;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingSet;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

/**
 * A composable {@link Set} which pokes the specified {@link Callback}
 * on structural changes.
 *
 * @author Willi Schoenborn
 * @param <E> the generic element type
 */
final class CallbackSet<E> extends ForwardingSet<E> {

    private final Set<E> set;
    
    private final Callback callback;

    public CallbackSet(Set<E> set, Callback callback) {
        this.set = Preconditions.checkNotNull(set, "Set");
        this.callback = Preconditions.checkNotNull(callback, "Callback");
    }
    
    @Override
    protected Set<E> delegate() {
        return set;
    }
    
    @Override
    public boolean add(E element) {
        final boolean added = super.add(element);
        callback.poke();
        return added;
    }
    
    @Override
    public boolean addAll(Collection<? extends E> c) {
        final boolean added = super.addAll(c);
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
    public boolean remove(Object object) {
        final boolean removed = super.remove(object);
        callback.poke();
        return removed;
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
    
}
