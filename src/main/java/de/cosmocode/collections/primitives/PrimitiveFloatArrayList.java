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

package de.cosmocode.collections.primitives;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import com.google.common.base.Preconditions;

import de.cosmocode.collections.RealAbstractList;

/**
 * {@link List} implementation backed by a primitive {@code float[]}.
 *
 * @since 1.19
 * @author Willi Schoenborn
 */
final class PrimitiveFloatArrayList extends RealAbstractList<Float> {

    private final float[] array;
    
    PrimitiveFloatArrayList(float[] array) {
        this.array = Preconditions.checkNotNull(array, "Array");
    }
    
    @Override
    public Float set(int index, Float element) {
        Preconditions.checkNotNull(element, "Element");
        final Float old = array[index];
        array[index] = element;
        return old;
    }

    @Override
    public void add(int index, Float element) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Float remove(int index) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Float get(int index) {
        return array[index];
    }
    
    @Override
    public int indexOf(Object o) {
        // we only contain non null floats
        if (o instanceof Float) {
            for (int i = 0; i < array.length; i++) {
                if (o.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }
    
    @Override
    public int lastIndexOf(Object o) {
        // we only contain non null floats
        if (o instanceof Float) {
            for (int i = array.length - 1; i >= 0; i -= 1) {
                if (o.equals(array[i])) {
                    return i;
                }
            }
        }
        return -1;
    }

    @Override
    public boolean contains(Object o) {
        return indexOf(o) != -1;
    }
    
    @Override
    public int size() {
        return array.length;
    }
    
    @Override
    public int hashCode() {
        return Arrays.hashCode(array);
    }

}
