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

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Multimaps;
import de.cosmocode.collections.utility.Convert;
import org.junit.Assert;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Tests {@link Convert#intoMap(Object)} and
 * {@link Convert#intoMap(Object, Map)}.
 *
 * @author Willi Schoenborn
 */
public class ConvertMapTest {

    /**
     * Tests {@link Convert#intoMap(Object)} with null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoMapNull() {
        Convert.intoMap(null);
    }

    /**
     * Tests {@link Convert#intoMap(Object)} with "".
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoMapEmpty() {
        Convert.intoMap("");
    }
    
    /**
     * Tests {@link Convert#intoMap(Object)} with new Object().
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoMapObject() {
        Convert.intoMap(new Object());
    }

    /**
     * Tests {@link Convert#intoMap(Object)} with a {@link List}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoMapList() {
        Convert.intoMap(Lists.newArrayList());
    }
    
    /**
     * Tests {@link Convert#intoMap(Object)} with valid attributes.
     */
    @Test
    public void intoMap() {
        final Map<String, Object> map = ImmutableMap.of(
            "integer", 123,
            "boolean", Boolean.TRUE,
            "object", new Object()
        );
        
        Assert.assertEquals(map, Convert.intoMap(Maps.newHashMap(map)));
        Assert.assertEquals(Multimaps.forMap(map).asMap(), Convert.intoMap(Multimaps.forMap(map)));
    }
    
    /**
     * Tests {@link Convert#intoMap(Object, Map)} with valid attributes.
     */
    @Test
    public void intoMapDefaultUnused() {
        final Map<String, Object> map = ImmutableMap.of(
            "integer", 123,
            "boolean", Boolean.TRUE,
            "object", new Object()
        );
        final Map<Object, Object> defaultValue = Maps.newHashMap();
        
        Assert.assertFalse(Convert.intoMap(Maps.newHashMap(map), defaultValue).isEmpty());
        Assert.assertFalse(Convert.intoMap(Multimaps.forMap(map), defaultValue).isEmpty());
    }

    /**
     * Tests {@link Convert#intoMap(Object, Map)} with invalid attributes.
     */
    @Test
    public void intoMapDefaultUsed() {
        final Map<Object, Object> defaultValue = Maps.newHashMap();
        Assert.assertTrue(Convert.intoMap(null, defaultValue).isEmpty());
        Assert.assertTrue(Convert.intoMap("", defaultValue).isEmpty());
        Assert.assertTrue(Convert.intoMap(new Object(), defaultValue).isEmpty());
        Assert.assertTrue(Convert.intoMap(Lists.newArrayList(), defaultValue).isEmpty());
    }

}
