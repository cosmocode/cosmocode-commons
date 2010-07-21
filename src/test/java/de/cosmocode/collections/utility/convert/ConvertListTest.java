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

package de.cosmocode.collections.utility.convert;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Iterators;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import de.cosmocode.collections.utility.Convert;

/**
 * Tests {@link Convert#intoList(Object)} and
 * {@link Convert#intoList(Object, List)}.
 *
 * @author Willi Schoenborn
 */
public class ConvertListTest {

    /**
     * Tests {@link Convert#intoList(Object)} with null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoListNull() {
        Convert.intoList(null);
    }

    /**
     * Tests {@link Convert#intoList(Object)} with "".
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoListEmpty() {
        Convert.intoList("");
    }
    
    /**
     * Tests {@link Convert#intoList(Object)} with new Object().
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoListObject() {
        Convert.intoList(new Object());
    }
    
    /**
     * Tests {@link Convert#intoList(Object)} with a {@link Map}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoListMap() {
        Convert.intoList(Maps.newHashMap());
    }
    
    /**
     * Tests {@link Convert#intoList(Object)} with valid attributes.
     */
    @Test
    public void intoList() {
        final Object[] array = new Object[] {Boolean.TRUE, null, "string"};
        final List<Object> expected = Arrays.asList(array);
        
        Assert.assertEquals(expected, Convert.intoList(Lists.newArrayList(expected)));
        Assert.assertEquals(expected, Convert.intoList(array));
        Assert.assertEquals(expected, Convert.intoList(Sets.newLinkedHashSet(expected)));
        Assert.assertEquals(expected, Convert.intoList(Iterators.unmodifiableIterator(expected.iterator())));
    }
    
    /**
     * Tests {@link Convert#intoList(Object, List)} with valid attributes.
     */
    @Test
    public void intoListDefaultUnused() {
        final Object[] array = new Object[] {Boolean.TRUE, null, "string"};
        final List<Object> expected = Arrays.asList(array);
        final List<Object> defaultValue = Lists.newArrayList();

        Assert.assertFalse(Convert.intoList(Lists.newArrayList(expected), defaultValue).isEmpty());
        Assert.assertFalse(Convert.intoList(array, defaultValue).isEmpty());
        Assert.assertFalse(Convert.intoList(Sets.newLinkedHashSet(expected), defaultValue).isEmpty());
        final Iterator<Object> iterator = Iterators.unmodifiableIterator(expected.iterator());
        Assert.assertFalse(Convert.intoList(iterator, defaultValue).isEmpty());
    }

    /**
     * Tests {@link Convert#intoList(Object, List)} with invalid attributes.
     */
    @Test
    public void intoListDefaultUsed() {
        final List<Object> defaultValue = Lists.newArrayList();

        Assert.assertSame(defaultValue, Convert.intoList(null, defaultValue));
        Assert.assertSame(defaultValue, Convert.intoList("", defaultValue));
        Assert.assertSame(defaultValue, Convert.intoList(new Object(), defaultValue));
        Assert.assertSame(defaultValue, Convert.intoList(Maps.newHashMap(), defaultValue));
    }
    
}
