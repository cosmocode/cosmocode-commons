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
package de.cosmocode.collections;

import com.google.common.base.Function;

/**
 * A {@link Procedure} is comparable to the {@link Function}
 * interface but returns void instead of a value.
 *
 * @author Willi Schoenborn
 * @param <T> the generic parameter type
 */
public interface Procedure<T> {

    /**
     * Applies this procedure on an object of type T. 
     * 
     * @param input the single parameter for this procedure
     */
    void apply(T input);
    
}
