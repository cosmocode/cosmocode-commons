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
package de.cosmocode.collections.utility.list;

import com.google.common.collect.testing.ListTestSuiteBuilder;
import com.google.common.collect.testing.SampleElements;
import com.google.common.collect.testing.TestListGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.ListFeature;
import de.cosmocode.collections.utility.Utility;
import de.cosmocode.collections.utility.UtilityList;
import junit.framework.Test;

import java.util.Collections;
import java.util.List;

/**
 * Tests the general compliance of the {@link UtilityList}
 * to the {@link List} interface.
 *
 * @author Willi Schoenborn
 */
public final class ListTest implements TestListGenerator<Object> {

    private ListTest() {
        
    }

    /**
     * Creates {@link Test}.
     * 
     * @return {@link Test}
     */
    public static Test suite() {
        return ListTestSuiteBuilder.using(new ListTest()).
            named(ListTest.class.getSimpleName()).
            withFeatures(
                CollectionSize.ANY,
                CollectionFeature.ALLOWS_NULL_VALUES,
                CollectionFeature.KNOWN_ORDER,
                ListFeature.GENERAL_PURPOSE,
                ListFeature.REMOVE_OPERATIONS
            ).createTestSuite();
    }

    @Override
    public List<Object> create(Object... elements) {
        final UtilityList<Object> list = Utility.createUtilityList();
        Collections.addAll(list, elements);
        return list;
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
