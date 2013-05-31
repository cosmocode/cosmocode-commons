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

import java.util.List;
import java.util.Set;

/**
 * An extension of the {@link List} and {@link Set} interfaces which
 * combines the semantics of both, which leads to a collection
 * type which supports index-based access and ensures no element
 * is added twice.
 *
 * @since 1.5
 * @author Willi Schoenborn
 * @param <E> generic element type
 */
public interface UniqueList<E> extends List<E>, Set<E> {

    /**
     * {@inheritDoc}
     * @throws IllegalStateException if element is already contained in this list
     */
    @Override
    E set(int index, E element);
    
}
