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
package de.cosmocode.patterns;

/**
 * A Factory is a class
 * able to create instances of a specific
 * type.
 *
 * See also {@link Factory}.
 * 
 * @author Willi Schoenborn
 *
 * @param <T> the generic type this factory is able to create instances of
 * @param <P> the generic parameter type
 */
public interface ParameterizedFactory<T, P> {

    /**
     * Creates a new instance of the generic type T.
     * 
     * @param parameter the generic parameter passed the factory
     * @return a new instance of <T>
     */
    T create(P parameter);
    
}
