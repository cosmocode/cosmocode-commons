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

package de.cosmocode.patterns;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

/**
 * Implementation for {@link Factories#asFunction(ParameterizedFactory)}.
 *
 * @since 1.10
 * @author Willi Schoenborn
 * @param <P> parameter type
 * @param <T> target type
 */
final class ParameterizedFactoryFunction<P, T> implements Function<P, T> {

    private final ParameterizedFactory<? extends T, ? super P> factory;
    
    public ParameterizedFactoryFunction(ParameterizedFactory<? extends T, ? super P> factory) {
        this.factory = Preconditions.checkNotNull(factory, "Factory");
    }

    @Override
    public T apply(P from) {
        return factory.create(from);
    };
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof ParameterizedFactoryFunction<?, ?>) {
            final ParameterizedFactoryFunction<?, ?> other = ParameterizedFactoryFunction.class.cast(that);
            return factory.equals(other.factory);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return factory.hashCode() ^ 983345487;
    }
    
    @Override
    public String toString() {
        return "Factories.asFunction(" + factory + ")";
    }

}
