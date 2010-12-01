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

import java.util.List;

import junit.framework.Test;

import org.junit.Ignore;

import com.google.common.collect.Lists;
import com.google.common.collect.testing.ListTestSuiteBuilder;
import com.google.common.collect.testing.SampleElements;
import com.google.common.collect.testing.TestListGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.ListFeature;

/**
 * Tests {@link List} compatability of {@link CyclingList}.
 *
 * @since 1.20
 * @author Willi Schoenborn
 */
@Ignore("Because we break some index rules, enable this test when necessary")
public final class CyclingListSpecTest implements TestListGenerator<String> {
    
    private CyclingListSpecTest() {
        
    }

    /**
     * Creates and returns the test for this test. 
     *
     * @since 1.20
     * @return the test
     */
    public static Test suite() {
        return ListTestSuiteBuilder.using(new CyclingListSpecTest()).
            named(CyclingListSpecTest.class.getSimpleName()).
            withFeatures(
                CollectionSize.ANY,
                CollectionFeature.ALLOWS_NULL_VALUES,
                CollectionFeature.KNOWN_ORDER,
                ListFeature.GENERAL_PURPOSE,
                ListFeature.REMOVE_OPERATIONS
            ).createTestSuite();
    }
    
    @Override
    public List<String> create(Object... elements) {
        final List<String> list = Lists.newArrayList();
        for (Object element : elements) {
            final String string = String.class.cast(element);
            list.add(string);
        }
        return MoreLists.cycle(list);
    }
    
    @Override
    public String[] createArray(int length) {
        return new String[length];
    }
    
    @Override
    public Iterable<String> order(List<String> insertionOrder) {
        return insertionOrder;
    }
    
    @Override
    public SampleElements<String> samples() {
        return new SampleElements<String>("a", "b", "c", "d", "e");
    }
    
}
