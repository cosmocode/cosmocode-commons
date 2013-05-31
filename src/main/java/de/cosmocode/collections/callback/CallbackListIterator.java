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
import com.google.common.collect.ForwardingListIterator;

import java.util.ListIterator;

/**
 * A composable {@link ListIterator} which pokes the specified {@link Callback}
 * on structural changes.
 *
 * @author Willi Schoenborn
 * @param <E> the generic element type
 */
final class CallbackListIterator<E> extends ForwardingListIterator<E> {

    private final ListIterator<E> listIterator;
    
    private final Callback callback;

    public CallbackListIterator(ListIterator<E> listIterator, Callback callback) {
        this.listIterator = Preconditions.checkNotNull(listIterator, "ListIterator");
        this.callback = Preconditions.checkNotNull(callback, "Callback");
    }
    
    @Override
    protected ListIterator<E> delegate() {
        return listIterator;
    }

    @Override
    public void add(E element) {
        super.add(element);
        callback.poke();
    }
    
    @Override
    public void remove() {
        super.remove();
        callback.poke();
    }
    
    @Override
    public void set(E element) {
        super.set(element);
        callback.poke();
    }
    
}
