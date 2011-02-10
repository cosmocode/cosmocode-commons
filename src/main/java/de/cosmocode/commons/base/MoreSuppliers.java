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

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

/**
 * A static utility class to work with {@link Supplier}s.
 * Similiar to {@link Suppliers}.
 *
 * @since 1.18
 * @author Willi Schoenborn
 */
public final class MoreSuppliers {

    private MoreSuppliers() {
        
    }

    /**
     * Returns a supplier which caches the instance retrieved during the first
     * call to {@code get()} in a thread local and returns that value on subsequent calls to
     * {@code get()} in the same thread.
     *
     * @since 1.18
     * @see Suppliers#memoize(Supplier)
     * @param <T> the generic value type
     * @param supplier the backing supplier
     * @return a memoizing supplier which delegates to the given supplier
     * @throws NullPointerException if supplier is null
     */
    public static <T> Supplier<T> memoizePerThread(Supplier<T> supplier) {
        return new ThreadLocalMemoizingSupplier<T>(supplier);
    }

    /**
     * Returns a supplier that caches the instance supplied by the delegate and removes 
     * the cached value after the specified time has passed. Subsequent calls to get() 
     * return the cached value if the expiration time has not passed. 
     * After the expiration time, a new value is retrieved, cached, and returned. 
     * The expiration time works on a per-thread basis.
     *
     * @since 1.18
     * @see Suppliers#memoizeWithExpiration(Supplier, long, TimeUnit)
     * @param <T> the generic value type
     * @param supplier the backing suppier
     * @param duration the length of time after a value is created 
     *         that it should stop being returned by subsequent get() calls
     * @param unit the unit of duration
     * @return an expiring thread-local memoizing supplier
     */
    public static <T> Supplier<T> memoizePerThreadWithExpiration(Supplier<T> supplier, 
            long duration, TimeUnit unit) {
        return new ExpiringThreadLocalMemoizingSupplier<T>(supplier, duration, unit);
    }
    
    /**
     * Adapts the given supplier to the {@link Callable} interface.
     *
     * @since 1.21
     * @param <T> the generic value type
     * @param supplier the backing supplier
     * @return a {@link Callable} which delegats to the given supplier
     * @throws NullPointerException if supplier is null
     */
    public static <T> Callable<T> callable(Supplier<T> supplier) {
        return new SupplierCallable<T>(supplier);
    }
    
}
