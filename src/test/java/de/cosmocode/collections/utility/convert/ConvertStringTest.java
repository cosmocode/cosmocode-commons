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

import com.google.common.collect.Maps;
import de.cosmocode.collections.utility.Convert;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Tests {@link Convert#intoString(Object)} and
 * {@link Convert#intoString(Object, String)}.
 *
 * @author Willi Schoenborn
 */
public class ConvertStringTest {

    /**
     * Tests {@link Convert#intoString(Object)} with null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoStringNull() {
        Convert.intoString(null);
    }

    /**
     * Tests {@link Convert#intoString(Object)} with valid attributes.
     */
    @Test
    public void intoString() {
        Assert.assertEquals("a string", Convert.intoString("a string"));
        Assert.assertEquals("", Convert.intoString(""));
        Assert.assertEquals("", Convert.intoString(new StringBuilder()));
        Assert.assertEquals("[]", Convert.intoString(Arrays.asList()));
        Assert.assertEquals("{}", Convert.intoString(Maps.newHashMap()));
        Assert.assertEquals(getClass().toString(), Convert.intoString(getClass()));
    }
    
    /**
     * Tests {@link Convert#intoString(Object, String)} with valid attributes.
     */
    @Test
    public void intoStringDefaultUnused() {
        Assert.assertEquals("a string", Convert.intoString("a string", null));
        Assert.assertEquals("", Convert.intoString("", null));
        Assert.assertEquals("", Convert.intoString(new StringBuilder(), null));
        Assert.assertEquals("[]", Convert.intoString(Arrays.asList(), null));
        Assert.assertEquals("{}", Convert.intoString(Maps.newHashMap(), null));
        Assert.assertEquals(getClass().toString(), Convert.intoString(getClass(), null));
    }

    /**
     * Tests {@link Convert#intoString(Object, String)} with invalid attributes.
     */
    @Test
    public void intoStringDefaultUsed() {
        Assert.assertSame("default", Convert.intoString(null, "default"));
    }

}
