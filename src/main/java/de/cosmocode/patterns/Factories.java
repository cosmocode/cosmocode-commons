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

import com.google.common.base.Function;

/**
 * Static utility class for {@link Factory}s and {@link ParameterizedFactory}s.
 *
 * @since 1.10
 * @author Willi Schoenborn
 */
public final class Factories {

    private Factories() {
        
    }
    
    /**
     * Adapts the given {@link Factory} to the {@link Callable} interface.
     * 
     * @since 1.10
     * @param <T> the target type
     * @param factory the backing factory
     * @return a callable backed by the given factory
     * @throws NullPointerException if factory is null
     */
    public static <T> Callable<T> asCallable(Factory<? extends T> factory) {
        return new FactoryCallable<T>(factory);
    }
    
    /**
     * Adapts the given {@link ParameterizedFactory} to the {@link Function} interface.
     * 
     * @since 1.10
     * @param <P> the parameter type
     * @param <T> the target type
     * @param factory the backing factory
     * @return a function backed by the given factory
     * @throws NullPointerException if factory is null
     */
    public static <T, P> Function<P, T> asFunction(ParameterizedFactory<? extends T, ? super P> factory) {
        return new ParameterizedFactoryFunction<P, T>(factory);
    }

}
