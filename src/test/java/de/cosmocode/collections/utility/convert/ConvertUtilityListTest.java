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
package de.cosmocode.collections.utility.convert;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import de.cosmocode.collections.utility.Convert;
import de.cosmocode.collections.utility.Utility;
import de.cosmocode.collections.utility.UtilityList;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/**
 * Tests {@link Convert#intoUtilityList(Object)} and
 * {@link Convert#intoUtilityList(Object, UtilityList)}.
 *
 * @author Willi Schoenborn
 */
public class ConvertUtilityListTest {

    /**
     * Tests {@link Convert#intoUtilityList(Object)} with null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoUtilityListNull() {
        Convert.intoUtilityList(null);
    }

    /**
     * Tests {@link Convert#intoUtilityList(Object)} with "".
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoUtilityListEmpty() {
        Convert.intoUtilityList("");
    }
    
    /**
     * Tests {@link Convert#intoUtilityList(Object)} with new Object().
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoUtilityListObject() {
        Convert.intoUtilityList(new Object());
    }
    
    /**
     * Tests {@link Convert#intoUtilityList(Object)} with a {@link Map}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoUtilityListMap() {
        Convert.intoUtilityList(Maps.newHashMap());
    }
    
    /**
     * Tests {@link Convert#intoUtilityList(Object)} with valid attributes.
     */
    @Test
    public void intoUtilityList() {
        final Object[] array = new Object[] {Boolean.TRUE, null, "string"};
        final List<Object> expected = Utility.asUtilityList(Arrays.asList(array));
        
        Assert.assertEquals(expected, Convert.intoUtilityList(Lists.newArrayList(expected)));
        Assert.assertEquals(expected, Convert.intoUtilityList(array));
        Assert.assertEquals(expected, Convert.intoUtilityList(Sets.newLinkedHashSet(expected)));
        Assert.assertEquals(expected, Convert.intoUtilityList(Iterators.unmodifiableIterator(expected.iterator())));
    }
    
    /**
     * Tests {@link Convert#intoUtilityList(Object, UtilityList)} with valid attributes.
     */
    @Test
    public void intoUtilityListDefaultUnused() {
        final Object[] array = new Object[] {Boolean.TRUE, null, "string"};
        final List<Object> expected = Utility.asUtilityList(Arrays.asList(array));
        final UtilityList<Object> defaultValue = Utility.asUtilityList(Lists.newArrayList());

        Assert.assertFalse(Convert.intoUtilityList(Lists.newArrayList(expected), defaultValue).isEmpty());
        Assert.assertFalse(Convert.intoUtilityList(array, defaultValue).isEmpty());
        Assert.assertFalse(Convert.intoUtilityList(Sets.newLinkedHashSet(expected), defaultValue).isEmpty());
        final Iterator<Object> iterator = Iterators.unmodifiableIterator(expected.iterator());
        Assert.assertFalse(Convert.intoUtilityList(iterator, defaultValue).isEmpty());
    }

    /**
     * Tests {@link Convert#intoUtilityList(Object, UtilityList)} with invalid attributes.
     */
    @Test
    public void intoUtilityListDefaultUsed() {
        final UtilityList<Object> defaultValue = Utility.asUtilityList(Lists.newArrayList());

        Assert.assertSame(defaultValue, Convert.intoUtilityList(null, defaultValue));
        Assert.assertSame(defaultValue, Convert.intoUtilityList("", defaultValue));
        Assert.assertSame(defaultValue, Convert.intoUtilityList(new Object(), defaultValue));
        Assert.assertSame(defaultValue, Convert.intoUtilityList(Maps.newHashMap(), defaultValue));
    }

}
