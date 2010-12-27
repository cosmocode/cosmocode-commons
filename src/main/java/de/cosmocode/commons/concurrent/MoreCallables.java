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

import com.google.common.util.concurrent.Callables;

/**
 * A static utility class for {@link Callable}s, just like {@link Callables}.
 *
 * @since 1.21
 * @author Willi Schoenborn
 */
public final class MoreCallables {
    
    private MoreCallables() {
        
    }
    
    /**
     * Creates a callable which delays the execution of the specified callable by the given amount of time.
     *
     * @since 1.21
     * @param <V> the generic return value
     * @param callable the backing callable
     * @param timeout the amount of time to wait before execution
     * @param timeoutUnit the unit of timeout
     * @return a callable delaying the execution of the backing callable
     * @throws NullPointerException if callable or timeoutUnit is null
     */
    public static <V> Callable<V> delay(Callable<? extends V> callable, long timeout, TimeUnit timeoutUnit) {
        return new DelayingCallable<V>(callable, timeout, timeoutUnit);
    }

}
