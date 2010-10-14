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

import java.util.concurrent.TimeUnit;

import com.google.common.base.Preconditions;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;

/**
 * A memoizing and expiring {@link Supplier} backed by a {@link ThreadLocal}.
 *
 * @since 1.18
 * @author Willi Schoenborn
 * @param <T> the generic value type
 */
final class ExpiringThreadLocalMemoizingSupplier<T> implements Supplier<T> {
    
    private final Supplier<T> supplier;
    private final long duration;
    private final TimeUnit unit;
    
    private final ThreadLocal<Supplier<T>> local;
    
    public ExpiringThreadLocalMemoizingSupplier(final Supplier<T> supplier, 
            final long duration, final TimeUnit unit) {
        
        this.supplier = Preconditions.checkNotNull(supplier, "Supplier");
        this.duration = duration;
        this.unit = Preconditions.checkNotNull(unit, "Unit");
        
        this.local = new ThreadLocal<Supplier<T>>() {

            @Override
            protected Supplier<T> initialValue() {
                return Suppliers.memoizeWithExpiration(supplier, duration, unit);
            }

        };
    }
    
    @Override
    public T get() {
        // the memoizing supplier of this thread
        final Supplier<T> memoizingSupplier = local.get();
        // the memoized value of the supplier
        final T value = memoizingSupplier.get();
        return value;
    }

    @Override
    public String toString() {
        return "MoreSuppliers.memoizePerThreadWithExpiration(" + supplier + ", " + duration + ", " + unit + ")";
    }
    
}
