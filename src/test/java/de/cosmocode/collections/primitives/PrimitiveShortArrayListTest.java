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

import java.util.Collection;
import java.util.List;

import junit.framework.Test;

import com.google.common.collect.testing.CollectionTestSuiteBuilder;
import com.google.common.collect.testing.SampleElements;
import com.google.common.collect.testing.TestCollectionGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;

/**
 * Tests {@link PrimitiveBooelanArrayList}.
 *
 * @since 1.19
 * @author Willi Schoenborn
 */
public final class PrimitiveShortArrayListTest implements TestCollectionGenerator<Short> {

    private PrimitiveShortArrayListTest() {
        
    }

    /**
     * Creates {@link Test}.
     * 
     * @return {@link Test}
     */
    public static Test suite() {
        return CollectionTestSuiteBuilder.using(new PrimitiveShortArrayListTest()).
            named(PrimitiveShortArrayListTest.class.getName()).
            withFeatures(
                CollectionSize.ANY,
                CollectionFeature.KNOWN_ORDER
            ).createTestSuite();
    }
    
    @Override
    public Collection<Short> create(Object... elements) {
        final short[] array = new short[elements.length];
        for (int i = 0; i < elements.length; i++) {
            array[i] = (Short) elements[i];
        }
        return PrimitiveArrays.asList(array);
    }

    @Override
    public Short[] createArray(int length) {
        return new Short[length];
    }

    @Override
    public Iterable<Short> order(List<Short> insertionOrder) {
        return insertionOrder;
    }

    @Override
    public SampleElements<Short> samples() {
        return new SampleElements<Short>(Short.MIN_VALUE, (short) -1, (short) 0, (short) 1, Short.MAX_VALUE);
    }
    
}
