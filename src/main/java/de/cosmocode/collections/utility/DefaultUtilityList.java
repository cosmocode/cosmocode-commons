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

package de.cosmocode.collections.utility;

import java.util.List;

/**
 * Default implementation of the {@link UtilityList}
 * interface.
 *
 * @author Willi Schoenborn
 * @param <E> the generic element type
 */
class DefaultUtilityList<E> extends AbstractUtilityList<E> implements UtilityList<E> {

    private final List<E> list;

    public DefaultUtilityList(List<E> list) {
        this.list = list;
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
    };
    
    @Override
    public E set(int index, E element) {
        return list.set(index, element);
    };
    
    @Override
    public E remove(int index) {
        return list.remove(index);
    }
    
    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public int size() {
        return list.size();
    }
    
}
