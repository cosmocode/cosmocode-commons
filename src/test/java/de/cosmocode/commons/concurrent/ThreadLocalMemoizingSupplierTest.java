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

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.mutable.MutableInt;
import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Supplier;

import de.cosmocode.junit.UnitProvider;

/**
 * Tests {@link ThreadLocalMemoizingSupplier}.
 *
 * @since 1.18
 * @author Willi Schoenborn
 */
public final class ThreadLocalMemoizingSupplierTest implements UnitProvider<Supplier<UUID>> {

    
    @Override
    public Supplier<UUID> unit() {
        return ThreadLocalMemoizingSupplier.of(new Supplier<UUID>() {
            
            @Override
            public UUID get() {
                return UUID.randomUUID();
            }
            
        });
    }
 
    /**
     * Tests {@link ThreadLocalMemoizingSupplier#get()} with a single thread.
     */
    @Test
    public void singleThread() {
        final Supplier<UUID> unit = unit();
        Assert.assertSame(unit.get(), unit.get());
        Assert.assertSame(unit.get(), unit.get());
    }
    
    private <T> T execute(final Supplier<T> supplier) {
        try {
            return Executors.newSingleThreadExecutor().submit(new Callable<T>() {
                
                @Override
                public T call() throws Exception {
                    return supplier.get();
                }
                
            }).get();
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        } catch (ExecutionException e) {
            throw new AssertionError(e);
        }
    }
    
    /**
     * Tests {@link ThreadLocalMemoizingSupplier#get()} with multiple threads.
     */
    @Test
    public void multipleThreads() {
        final Supplier<UUID> unit = unit();
        final UUID first = execute(unit);
        final UUID second = execute(unit);
        final UUID third = execute(unit);
        Assert.assertNotSame(first, second);
        Assert.assertNotSame(first, third);
        Assert.assertNotSame(second, third);
    }
    
}
