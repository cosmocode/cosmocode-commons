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

package de.cosmocode.commons;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

import de.cosmocode.patterns.Adapter;

/**
 * An {@link Adapter} from {@link JoinWalker} to {@link Function}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <F> generic parameter type
 */
@SuppressWarnings("deprecation")
final class JoinWalkerFunction<F> extends AbstractFunction<F, String> {

    private final JoinWalker<? super F> walker;
    
    public JoinWalkerFunction(JoinWalker<? super F> walker) {
        this.walker = Preconditions.checkNotNull(walker, "Walker");
    }
    
    @Override 
    public String apply(F from) {
        return walker.walk(from);
    };

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof JoinWalkerFunction<?>) {
            final JoinWalkerFunction<?> other = JoinWalkerFunction.class.cast(that);
            return walker.equals(other.walker);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return walker.hashCode() ^ -921210296;
    }
    
    @Override
    public String toString() {
        return "JoinWalkers.asFunction(" + walker + ")";
    }
    
}
