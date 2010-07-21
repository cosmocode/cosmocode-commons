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

import java.util.Iterator;

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingIterator;

/**
 * A composable {@link Iterator} which pokes the specified {@link Callback}
 * on structural changes.
 *
 * @author Willi Schoenborn
 * @param <E> the generic element type
 */
final class CallbackIterator<E> extends ForwardingIterator<E> {

    private final Iterator<E> iterator;

    private final Callback callback;

    public CallbackIterator(Iterator<E> iterator, Callback callback) {
        this.iterator = Preconditions.checkNotNull(iterator, "Iterator");
        this.callback = Preconditions.checkNotNull(callback, "Callback");
    }
    
    @Override
    protected Iterator<E> delegate() {
        return iterator;
    }
    
    @Override
    public void remove() {
        super.remove();
        callback.poke(); 
    }
    
}
