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

package de.cosmocode.commons.reflect;

import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.MapMaker;

/**
 * Static utility class for {@link Class}es.
 *
 * @since 1.6
 * @author Willi Schoenborn
 */
public final class Classes {

    private static final ConcurrentMap<String, Class<?>> CACHE = new MapMaker().softValues().makeMap();
    
    private Classes() {
        
    }
    
    /**
     * Returns the Class object associated with the class or interface with the given string name.
     * This method does in contrast to {@link Class#forName(String)} caches the results.
     * 
     * @since 1.6
     * @param name the class name
     * @return the loaded class
     * @throws ClassNotFoundException if the class does not exist
     */
    public static Class<?> forName(String name) throws ClassNotFoundException {
        final Class<?> cached = CACHE.get(name);
        return cached == null ? store(name) : cached;
    }
    
    private static Class<?> store(String name) throws ClassNotFoundException {
        final Class<?> type = Class.forName(name);
        CACHE.put(name, type);
        return type;
    }
    
}
