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

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;

/**
 * An {@link Adapter} wraps an instance and
 * implements the same interface as his adaptee.
 * 
 * An {@link Adapter} allows to provide
 * a different view on instances of a specific type.
 * 
 * The recommended naming pattern is {@code <Source><Target>}.
 * Example:
 * <p>
 *   Source would be {@code JSONArray} and the target interface a {@link List},
 *   then the resulting {@link Adapter} would be JSONArrayList.
 * </p>
 * 
 * See also <a href="http://en.wikipedia.org/wiki/Adapter_pattern">Wikipedia</a>.
 * 
 * @author schoenborn@cosmocode.de
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Inherited
public @interface Adapter {

    /**
     * The target class, this {@link Adapter} must implement.
     */
    Class<?> value();
    
}
