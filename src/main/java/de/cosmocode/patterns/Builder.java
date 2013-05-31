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
 * A {@link Builder} is able to build
 * instances of a specifc type.
 * 
 * A {@link Builder} is the best alternative when
 * facing constructors with many parameters and if
 * you want to take advantage of final instance fields.
 * 
 * {@link Builder}s provide meaningful method names,
 * comparable to getter/setters of the JavaBean specification.
 * 
 * See also <a href="http://en.wikipedia.org/wiki/Builder_pattern">Wikipedia</a>
 * 
 * @author schoenborn@cosmocode.de
 *
 * @param <T> the generic type this {@link Builder} is able to create instances of
 */
public interface Builder<T> {

    /**
     * Builds/creates an instance of T.
     * 
     * @return a newly created instance of T
     */
    T build();
    
}
