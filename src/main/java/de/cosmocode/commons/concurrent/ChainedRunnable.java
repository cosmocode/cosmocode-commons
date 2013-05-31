/**
 * Copyright 2010 - 2013 CosmoCode GmbH
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

import com.google.common.base.Preconditions;
import com.google.common.base.Predicates;
import com.google.common.collect.Iterables;
import de.cosmocode.commons.base.Joiners;

/**
 * Implementation for {@link Runnables#chain(Runnable, Runnable, Runnable...)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
final class ChainedRunnable implements Runnable {

    private final Iterable<Runnable> runnables;
        
    public ChainedRunnable(Iterable<Runnable> runnables) {
        this.runnables = Preconditions.checkNotNull(runnables, "Runnables");
        Preconditions.checkArgument(Iterables.all(runnables, Predicates.notNull()), "Found null in %s", runnables);
    }

    @Override
    public void run() {
        for (Runnable runnable : runnables) {
            runnable.run();
        }
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof ChainedRunnable) {
            final ChainedRunnable other = ChainedRunnable.class.cast(that);
            return runnables.equals(other.runnables);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return runnables.hashCode() ^ 12865424;
    }
    
    @Override
    public String toString() {
        return "Runnables.chain(" + Joiners.COLLECTION_ELEMENTS.join(runnables) + ")";
    }

}
