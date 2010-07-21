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

package de.cosmocode.collections.utility.collection;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

import junit.framework.Test;

import com.google.common.collect.testing.CollectionTestSuiteBuilder;
import com.google.common.collect.testing.SampleElements;
import com.google.common.collect.testing.TestCollectionGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;

import de.cosmocode.collections.utility.Utility;
import de.cosmocode.collections.utility.UtilityCollection;

/**
 * Tests the general compliance of the {@link UtilityCollection}
 * to the {@link Collection} interface.
 *
 * @author Willi Schoenborn
 */
public final class CollectionTest implements TestCollectionGenerator<Object> {

    private CollectionTest() {
        
    }

    /**
     * Creates {@link Test}.
     * 
     * @return {@link Test}
     */
    public static Test suite() {
        return CollectionTestSuiteBuilder.using(new CollectionTest()).
            named(CollectionTest.class.getName()).
            withFeatures(
                CollectionSize.ANY,
                CollectionFeature.GENERAL_PURPOSE,
                CollectionFeature.ALLOWS_NULL_VALUES
            ).createTestSuite();
    }
    
    @Override
    public Collection<Object> create(Object... elements) {
        final UtilityCollection<Object> collection = Utility.createUtilityCollection();
        Collections.addAll(collection, elements);
        return collection;
    }
    
    @Override
    public Object[] createArray(int length) {
        return new Object[length];
    }
    
    @Override
    public Iterable<Object> order(List<Object> insertionOrder) {
        return insertionOrder;
    }
    
    @Override
    public SampleElements<Object> samples() {
        return new SampleElements<Object>(
            Boolean.TRUE, 
            Integer.MAX_VALUE, 
            123, 
            new Object(), 
            "test"
        );
    }

}
