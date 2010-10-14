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

package de.cosmocode.commons.base;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

/**
 * A memoizing {@link Supplier} backed by a {@link ThreadLocal}.
 *
 * @since 1.18
 * @see Suppliers#memoize(Supplier)
 * @author Willi Schoenborn
 * @param <T> generic value type
 */
final class ThreadLocalMemoizingSupplier<T> implements Supplier<T> {

    private final Supplier<T> supplier;
    
    private final ThreadLocal<T> local;
    
    ThreadLocalMemoizingSupplier(final Supplier<T> supplier) {
        this.supplier = Preconditions.checkNotNull(supplier, "Supplier");
        
        this.local = new ThreadLocal<T>() {

            @Override
            protected T initialValue() {
                return supplier.get();
            }
            
        };
    }
    
    @Override
    public T get() {
        return local.get();
    }
    
    @Override
    public String toString() {
        return "MoreSuppliers.memoizePerThread(" + supplier + ")";
    }
    
}
