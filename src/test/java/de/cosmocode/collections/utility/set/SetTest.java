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

package de.cosmocode.collections.utility.set;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import junit.framework.Test;

import com.google.common.collect.testing.SampleElements;
import com.google.common.collect.testing.SetTestSuiteBuilder;
import com.google.common.collect.testing.TestSetGenerator;
import com.google.common.collect.testing.features.CollectionFeature;
import com.google.common.collect.testing.features.CollectionSize;
import com.google.common.collect.testing.features.SetFeature;

import de.cosmocode.collections.utility.Utility;
import de.cosmocode.collections.utility.UtilitySet;

/**
 * Tests the general compliance of the {@link UtilitySet}
 * to the {@link Set} interface.
 *
 * @author Willi Schoenborn
 */
public final class SetTest implements TestSetGenerator<Object> {

    private SetTest() {
        
    }
    
    /**
     * Creates {@link Test}.
     * 
     * @return {@link Test}
     */
    public static Test suite() {
        return SetTestSuiteBuilder.using(new SetTest()).
            named(SetTest.class.getName()).
            withFeatures(
                CollectionSize.ANY,
                CollectionFeature.ALLOWS_NULL_VALUES,
                SetFeature.GENERAL_PURPOSE
            ).createTestSuite();
    }
    
    @Override
    public Set<Object> create(Object... elements) {
        final UtilitySet<Object> set = Utility.createUtilitySet();
        Collections.addAll(set, elements);
        return set;
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
