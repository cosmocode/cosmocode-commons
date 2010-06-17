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

import com.google.common.base.Joiner;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * Utility class for {@link Runnable}s.
 *
 * @author Willi Schoenborn
 */
public final class Runnables {

    private static final Joiner JOINER = Joiner.on(", ");
    
    private Runnables() {
        
    }
    
    /**
     * Chains multiple {@link Runnable}s together.
     * 
     * @param first the first Runnable
     * @param second the second Runnable
     * @param rest zero or more additional Runnables
     * @return a Runnable which runs all given Runnables in a sequence
     * @throws NullPointerException if first, second or rest is null
     */
    public static Runnable chain(final Runnable first, final Runnable second, final Runnable... rest) {
        Preconditions.checkNotNull(first, "First");
        Preconditions.checkNotNull(second, "Second");
        Preconditions.checkNotNull(rest, "Rest");
        final Iterable<Runnable> runnables = Lists.asList(first, second, rest);
        return new Runnable() {
            
            @Override
            public void run() {
                for (Runnable runnable : runnables) {
                    runnable.run();
                }
            }
            
            @Override
            public String toString() {
                return "Runnables.chain(" + JOINER.join(runnables) + ")";
            }
            
        };
    }

}
