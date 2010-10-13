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

package de.cosmocode.commons.concurrent;

import javax.annotation.Nonnull;

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
public final class ThreadLocalMemoizingSupplier<T> implements Supplier<T> {

    private final ThreadLocal<T> local;
    
    private ThreadLocalMemoizingSupplier(final Supplier<T> supplier) {
        Preconditions.checkNotNull(supplier, "Supplier");
        this.local = new ThreadLocal<T>() {
            
            @Override
            protected T initialValue() {
                return supplier.get();
            };
            
        };
    }
    
    @Override
    public T get() {
        return local.get();
    }

    /**
     * Returns a supplier which caches the instance retrieved during the first
     * call to {@code get()} in a thread local and returns that value on subsequent calls to
     * {@code get()} in the same thread.
     *
     * @since 1.18
     * @param <T> the generic value type
     * @param supplier the backing supplier
     * @return a memoizing supplier which delegates to the given supplier
     * @throws NullPointerException if supplier is null
     */
    public static <T> Supplier<T> of(@Nonnull Supplier<T> supplier) {
        return new ThreadLocalMemoizingSupplier<T>(supplier);
    }
    
}
