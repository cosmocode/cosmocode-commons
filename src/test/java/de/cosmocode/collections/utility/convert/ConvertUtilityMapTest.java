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
import de.cosmocode.collections.utility.Utility;
import de.cosmocode.collections.utility.UtilityMap;
import org.junit.Assert;
import org.junit.Test;

import java.util.Collection;
import java.util.List;

/**
 * Tests {@link Convert#intoUtilityMap(Object)} and
 * {@link Convert#intoUtilityMap(Object, UtilityMap)}.
 *
 * @author Willi Schoenborn
 */
public class ConvertUtilityMapTest {

    /**
     * Tests {@link Convert#intoUtilityMap(Object)} with null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoUtilityMapNull() {
        Convert.intoUtilityMap(null);
    }

    /**
     * Tests {@link Convert#intoUtilityMap(Object)} with "".
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoUtilityMapEmpty() {
        Convert.intoUtilityMap("");
    }
    
    /**
     * Tests {@link Convert#intoUtilityMap(Object)} with new Object().
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoUtilityMapObject() {
        Convert.intoUtilityMap(new Object());
    }
    
    /**
     * Tests {@link Convert#intoUtilityMap(Object)} with a {@link List}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoUtilityMapList() {
        Convert.intoUtilityMap(Lists.newArrayList());
    }
    
    /**
     * Tests {@link Convert#intoUtilityMap(Object)} with valid attributes.
     */
    @Test
    public void intoUtilityMap() {
        final UtilityMap<String, Object> map = Utility.asUtilityMap(ImmutableMap.of(
            "integer", 123,
            "boolean", Boolean.TRUE,
            "object", new Object()
        ));
        final UtilityMap<String, Collection<Object>> multi = Utility.asUtilityMap(Multimaps.forMap(map).asMap());
            
        Assert.assertEquals(map, Convert.intoUtilityMap(Maps.newHashMap(map)));
        Assert.assertEquals(multi, Convert.intoUtilityMap(Multimaps.forMap(map)));
    }
    
    /**
     * Tests {@link Convert#intoUtilityMap(Object, UtilityMap)} with valid attributes.
     */
    @Test
    public void intoUtilityMapDefaultUnused() {
        final UtilityMap<String, Object> map = Utility.asUtilityMap(ImmutableMap.of(
            "integer", 123,
            "boolean", Boolean.TRUE,
            "object", new Object()
        ));
        final UtilityMap<String, Collection<Object>> multi = Utility.asUtilityMap(Multimaps.forMap(map).asMap());
        final UtilityMap<Object, Object> defaultValue = Utility.createUtilityMap();
        
        Assert.assertFalse(Convert.intoUtilityMap(map, defaultValue).isEmpty());
        Assert.assertFalse(Convert.intoUtilityMap(multi, defaultValue).isEmpty());
    }

    /**
     * Tests {@link Convert#intoUtilityMap(Object, UtilityMap)} with invalid attributes.
     */
    @Test
    public void intoUtilityMapDefaultUsed() {
        final UtilityMap<Object, Object> defaultValue = Utility.createUtilityMap();
        Assert.assertTrue(Convert.intoUtilityMap(null, defaultValue).isEmpty());
        Assert.assertTrue(Convert.intoUtilityMap("", defaultValue).isEmpty());
        Assert.assertTrue(Convert.intoUtilityMap(new Object(), defaultValue).isEmpty());
        Assert.assertTrue(Convert.intoUtilityMap(Lists.newArrayList(), defaultValue).isEmpty());
    }

}
