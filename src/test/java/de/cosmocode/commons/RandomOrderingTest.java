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

package de.cosmocode.commons;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Ordering;
import com.google.common.collect.Sets;

import de.cosmocode.junit.UnitProvider;

/**
 * Tests {@link RandomOrdering}.
 *
 * @since 1.10
 * @author Willi Schoenborn
 */
public final class RandomOrderingTest implements UnitProvider<Ordering<Object>> {

    @Override
    public Ordering<Object> unit() {
        return Orderings.random();
    }

    /**
     * Tests the shuffling functionality.
     */
    @Test
    public void shuffle() {
        final List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        final Set<List<String>> set = Sets.newHashSetWithExpectedSize(120);
        
        for (int i = 0; i < 12000; i++) {
            set.add(unit().sortedCopy(list));
        }
        
        Assert.assertEquals(120, set.size());
    }
    
    /**
     * Tests anti-symetry and caching.
     */
    @Test
    public void antisymmetric() {
        final List<String> list = Arrays.asList("a", "b", "c", "d", "e");
        final Ordering<Object> unit = unit();
        
        for (int i = 0; i < 5; i++) {
            Assert.assertEquals(unit.sortedCopy(list), unit.sortedCopy(list));
        }
    }
    
    /**
     * Tests equals contract.
     */
    @Test
    public void equals() {
        final List<String> list = Arrays.asList("a", "a", "b", "c", "d", "e");
        final Set<String> set = Sets.newTreeSet(unit());
        set.addAll(list);
        Assert.assertEquals(5, set.size());
    }
    
}
