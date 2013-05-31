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

import de.cosmocode.collections.utility.Convert;
import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link Convert#intoLong(Object)} and
 * {@link Convert#intoLong(Object, long)}.
 *
 * @author Willi Schoenborn
 */
public class ConvertLongTest {

    /**
     * Tests {@link Convert#intoLong(Object)} with null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoLongNull() {
        Convert.intoLong(null);
    }

    /**
     * Tests {@link Convert#intoLong(Object)} with "".
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoLongEmpty() {
        Convert.intoLong("");
    }
    
    /**
     * Tests {@link Convert#intoLong(Object)} with new Object().
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoLongObject() {
        Convert.intoLong(new Object());
    }
    
    /**
     * Tests {@link Convert#intoLong(Object)} with valid attributes.
     */
    @Test
    public void intoLong() {
        Assert.assertEquals(1250000, Convert.intoLong("1250000"));
        Assert.assertEquals(1250000, Convert.intoLong(1250000));
        Assert.assertEquals(0, Convert.intoLong("0"));
        Assert.assertEquals(0, Convert.intoLong(0));
        Assert.assertEquals(-1250000, Convert.intoLong("-1250000"));
        Assert.assertEquals(-1250000, Convert.intoLong(-1250000));
        Assert.assertEquals(-9223372036854775808L, Convert.intoLong("-9223372036854775808"));
        Assert.assertEquals(-9223372036854775808L, Convert.intoLong(-9223372036854775808L));
        Assert.assertEquals(9223372036854775807L, Convert.intoLong("9223372036854775807"));
        Assert.assertEquals(9223372036854775807L, Convert.intoLong(9223372036854775807L));
    }
    
    /**
     * Tests {@link Convert#intoLong(Object, long)} with valid attributes.
     */
    @Test
    public void intoLongDefaultUnused() {
        Assert.assertEquals(1250000, Convert.intoLong("1250000", 0));
        Assert.assertEquals(1250000, Convert.intoLong(1250000, 0));
        Assert.assertEquals(0, Convert.intoLong("0", 1));
        Assert.assertEquals(0, Convert.intoLong(0, 1));
        Assert.assertEquals(-1250000, Convert.intoLong("-1250000", 0));
        Assert.assertEquals(-1250000, Convert.intoLong(-1250000, 0));
        Assert.assertEquals(-9223372036854775808L, Convert.intoLong("-9223372036854775808", 0));
        Assert.assertEquals(-9223372036854775808L, Convert.intoLong(-9223372036854775808L, 0));
        Assert.assertEquals(9223372036854775807L, Convert.intoLong("9223372036854775807", 0));
        Assert.assertEquals(9223372036854775807L, Convert.intoLong(9223372036854775807L, 0));
    }

    /**
     * Tests {@link Convert#intoLong(Object, long)} with invalid attributes.
     */
    @Test
    public void intoLongDefaultUsed() {
        Assert.assertEquals(123456, Convert.intoLong(null, 123456));
        Assert.assertEquals(123456, Convert.intoLong("", 123456));
        Assert.assertEquals(123456, Convert.intoLong(new Object(), 123456));
    }

}
