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

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Preconditions;

/**
 * A {@link Callable} which delays execution by forcing the current
 * thread to sleep for a specific amount of time.
 *
 * @since 1.21
 * @author Willi Schoenborn
 * @param <V> generic return value
 */
final class DelayingCallable<V> implements Callable<V> {

    private final Callable<? extends V> delegate;
    private final long timeout;
    private final TimeUnit timeoutUnit;
    
    DelayingCallable(Callable<? extends V> delegate, long timeout, TimeUnit timeoutUnit) {
        this.delegate = Preconditions.checkNotNull(delegate, "Delegate");
        this.timeout = timeout;
        this.timeoutUnit = Preconditions.checkNotNull(timeoutUnit, "TimeoutUnit");
    }
    
    @Override
    public V call() throws Exception {
        Thread.sleep(timeoutUnit.toMillis(timeout));
        return delegate.call();
    }
    
}
