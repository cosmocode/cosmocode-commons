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

import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Supplier;

import de.cosmocode.junit.UnitProvider;

/**
 * Tests {@link ExpiringThreadLocalMemoizingSupplier}.
 *
 * @since 1.18
 * @author Willi Schoenborn
 */
public final class ExpiringThreadLocalMemoizingSupplierTest implements UnitProvider<Supplier<UUID>> {

    private final long duration = 25;
    private final TimeUnit durationUnit = TimeUnit.MILLISECONDS;
    
    @Override
    public Supplier<UUID> unit() {
        return MoreSuppliers.memoizePerThreadWithExpiration(new Supplier<UUID>() {
            
            @Override
            public UUID get() {
                return UUID.randomUUID();
            }
            
        }, duration, durationUnit);
    }
    
    private void sleep(long sleepDuration, TimeUnit sleepDurationUnit) {
        try {
            Thread.sleep(sleepDurationUnit.toMillis(sleepDuration));
        } catch (InterruptedException e) {
            throw new AssertionError(e);
        }
    }
    
    /**
     * Tests {@link ExpiringThreadLocalMemoizingSupplier#get()} with a single thread.
     */
    @Test
    public void singleThread() {
        final Supplier<UUID> unit = unit();
        final UUID first = unit.get();
        Assert.assertSame(first, unit.get());
        Assert.assertSame(first, unit.get());
        sleep(duration, durationUnit);
        Assert.assertNotSame(first, unit.get());
    }
    
    private <T> T execute(ExecutorService executor, final Supplier<T> supplier) {
        try {
            return executor.submit(new Callable<T>() {
                
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
     * Tests {@link ExpiringThreadLocalMemoizingSupplier#get()} with multiple threads.
     */
    @Test
    public void multipleThreads() {
        final Supplier<UUID> unit = unit();
        final ExecutorService firstExecutor = Executors.newSingleThreadExecutor();
        final ExecutorService secondExecutor = Executors.newSingleThreadExecutor();
        final ExecutorService thirdExecutor = Executors.newSingleThreadExecutor();
        
        final UUID first = execute(firstExecutor, unit);
        final UUID second = execute(secondExecutor, unit);
        final UUID third = execute(thirdExecutor, unit);
        
        // subsequent calls from the same thread should yield the same results
        Assert.assertSame(first, execute(firstExecutor, unit));
        Assert.assertSame(second, execute(secondExecutor, unit));
        Assert.assertSame(third, execute(thirdExecutor, unit));
        
        // alle uuids should differ from each other
        Assert.assertNotSame(first, second);
        Assert.assertNotSame(first, third);
        Assert.assertNotSame(second, third);

        sleep(duration, durationUnit);

        // after expiration, each thread should have a new value
        Assert.assertNotSame(first, execute(firstExecutor, unit));
        Assert.assertNotSame(second, execute(secondExecutor, unit));
        Assert.assertNotSame(third, execute(thirdExecutor, unit));
    }
    
}
