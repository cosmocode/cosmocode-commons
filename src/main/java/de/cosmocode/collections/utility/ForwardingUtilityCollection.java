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
package de.cosmocode.collections.utility;

import com.google.common.collect.ForwardingCollection;

/**
 * Implementation of the {@link UtilityCollection} interface
 * delegating all calls to an underlying {@link UtilityCollection}.
 *
 * @author Willi Schoenborn
 * @param <E> the generic element type
 */
public abstract class ForwardingUtilityCollection<E> extends ForwardingCollection<E> implements UtilityCollection<E> {

    @Override
    protected abstract UtilityCollection<E> delegate();
    
    @Override
    public abstract UtilityIterator<E> iterator();
    
}
