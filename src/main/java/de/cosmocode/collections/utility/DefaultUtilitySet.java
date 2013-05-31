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

import com.google.common.collect.ForwardingSet;

import java.util.Set;

/**
 * Default implementation of the {@link UtilitySet}
 * interface.
 *
 * @author Willi Schoenborn
 * @param <E> the generic element type
 */
class DefaultUtilitySet<E> extends ForwardingSet<E> implements UtilitySet<E> {

    private final Set<E> set;

    public DefaultUtilitySet(Set<E> set) {
        this.set = set;
    }

    @Override
    protected Set<E> delegate() {
        return set;
    }

    @Override
    public UtilityIterator<E> iterator() {
        return new DefaultUtilityIterator<E>(super.iterator());
    }

}
