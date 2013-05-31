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

import com.google.common.base.Preconditions;
import com.google.common.collect.ForwardingList;

import java.util.Collection;
import java.util.List;
import java.util.ListIterator;

/**
 * A {@link List} which supports out-of-range index access.
 * 
 * @since 
 * @author Willi Schoenborn
 *
 * @param <E> generic element type
 */
final class CyclingList<E> extends ForwardingList<E> {

    private final List<E> list;

    CyclingList(List<E> list) {
        this.list = Preconditions.checkNotNull(list, "List");
    }
    
    @Override
    protected List<E> delegate() {
        return list;
    }
    
    private int indexIfNotEmpty(int index) {
        return index < 0 ? indexIfNotEmpty(size() + index) : index % size();
    }
    
    private int readIndex(int index) {
        final int size = size();
        if (size == 0) {
            throw new IndexOutOfBoundsException(String.format(
                "index (%s) must not be greater than size (%s)", index, size
            ));
        } else {
            return index < 0 ? readIndex(size + index) : index % size;
        }
    }
    
    private int writeIndex(int index) {
        return isEmpty() ? 0 : readIndex(index);
    }
    
    private int addIndex(int index) {
        if (index == size()) {
            return index;
        } else {
            return readIndex(index);
        }
    }
    
    @Override
    public void add(int index, E element) {
        super.add(index == size() ? index : writeIndex(index), element);
    }

    @Override
    public boolean addAll(int index, Collection<? extends E> elements) {
        if (elements.isEmpty()) {
            return false;
        } else {
            return super.addAll(addIndex(index), elements);
        }
    }

    @Override
    public E get(int index) {
        return super.get(readIndex(index));
    }

    @Override
    public ListIterator<E> listIterator(int index) {
        return index == 0 ? listIterator() : super.listIterator(readIndex(index));
    }

    @Override
    public E remove(int index) {
        return super.remove(readIndex(index));
    }

    @Override
    public E set(int index, E element) {
        return super.set(writeIndex(index), element);
    }

    private int subListIndex(int index) {
        return isEmpty() || index <= size() ? index < 0 ? size() + index : index : indexIfNotEmpty(index);
    }
    
    @Override
    public List<E> subList(int fromIndex, int toIndex) {
        final int realFromIndex = subListIndex(fromIndex);
        final int realToIndex = subListIndex(toIndex);
        Preconditions.checkPositionIndexes(realFromIndex, realToIndex, size());
        return super.subList(realFromIndex, realToIndex);
    }
    
}
