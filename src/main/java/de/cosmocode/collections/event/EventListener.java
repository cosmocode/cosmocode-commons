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

package de.cosmocode.collections.event;

/**
 * A listener which can be composed with all of the major
 * collection types using the static factory methods in {@link Events}.
 * Listeners will be notified when elements in their corresponding collection
 * are added or removed.
 *
 * @since 1.5
 * @author Willi Schoenborn
 * @param <T> generic element type
 */
public interface EventListener<T> {

    /**
     * Event callback when the specified element
     * has been added.
     * 
     * @since 1.5
     * @param element the new element
     */
    void added(T element);
    
    /**
     * Event callback when the specified element
     * has been removed.
     * 
     * @since 1.5
     * @param element the old element
     */
    void removed(T element);
    
}
