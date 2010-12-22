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

import java.util.concurrent.Callable;

import com.google.common.base.Preconditions;

/**
 * Implementation for {@link Factories#asCallable(Factory)}.
 *
 * @since 1.10 
 * @author Willi Schoenborn
 * @param <T> generic return value
 */
final class FactoryCallable<T> implements Callable<T> {

    private final Factory<? extends T> factory;
    
    public FactoryCallable(Factory<? extends T> factory) {
        this.factory = Preconditions.checkNotNull(factory, "Factory");
    }
    
    @Override
    public T call() throws Exception {
        return factory.create();
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof FactoryCallable<?>) {
            final FactoryCallable<?> other = FactoryCallable.class.cast(that);
            return factory.equals(other.factory);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return factory.hashCode() ^ 634548782;
    }
    
    @Override
    public String toString() {
        return "Factories.asCallable(" + factory + ")";
    }
    
}
