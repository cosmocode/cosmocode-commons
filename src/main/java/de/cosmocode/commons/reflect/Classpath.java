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
package de.cosmocode.commons.reflect;

import java.net.URL;

/**
 * An abstraction for a set of class locations which
 * can be used to perform operations on different packages.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
public interface Classpath extends Iterable<URL> {
    
    /**
     * Restricts this classpath to the specified packages.
     * 
     * @since 1.8
     * @param packages the package names
     * @return a {@link Packages} instance which allows further operations
     *         on the selected packages
     * @throws NullPointerException if packages is null
     */
    Packages restrictTo(Iterable<String> packages);

    /**
     * Restricts this classpath to the specified packages.
     * 
     * @since 1.8
     * @param packages the package names
     * @return a {@link Packages} instance which allows further operations
     *         on the selected packages
     * @throws NullPointerException if packages is null
     */
    Packages restrictTo(String... packages);
    
}
