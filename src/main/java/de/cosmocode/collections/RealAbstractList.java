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

package de.cosmocode.collections;

import java.util.AbstractList;
import java.util.List;

/**
 * Abstract base implementation of the {@link List} interface which extends
 * {@link AbstractList} and makes all methods abstract which would throw an
 * {@link UnsupportedOperationException}.
 *
 * @author Willi Schoenborn
 * @param <E> the generic element type
 */
public abstract class RealAbstractList<E> extends AbstractList<E> {

    @Override
    public abstract E set(int index, E element);

    @Override
    public abstract void add(int index, E element);
    
    @Override
    public abstract E remove(int index);
    
}
