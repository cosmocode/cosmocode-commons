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
 * Tests {@link Convert#intoDouble(Object)} and
 * {@link Convert#intoDouble(Object, double)}.
 *
 * @author Willi Schoenborn
 */
public class ConvertDoubleTest {

    /**
     * Tests {@link Convert#intoDouble(Object)} with null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoDoubleNull() {
        Convert.intoDouble(null);
    }

    /**
     * Tests {@link Convert#intoDouble(Object)} with "".
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoDoubleEmpty() {
        Convert.intoDouble("");
    }
    
    /**
     * Tests {@link Convert#intoDouble(Object)} with new Object().
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoDoubleObject() {
        Convert.intoDouble(new Object());
    }
    
    /**
     * Tests {@link Convert#intoDouble(Object)} with valid attributes.
     */
    @Test
    public void intoDouble() {
        Assert.assertEquals(123.45d, Convert.intoDouble("123.45"), 0d);
        Assert.assertEquals(123.45d, Convert.intoDouble(123.45d), 0d);
        Assert.assertEquals(0d, Convert.intoDouble("0"), 0d);
        Assert.assertEquals(0d, Convert.intoDouble(0d), 0d);
        Assert.assertEquals(-123.45d, Convert.intoDouble("-123.45"), 0d);
        Assert.assertEquals(-123.45d, Convert.intoDouble(-123.45d), 0d);
        Assert.assertEquals(4.94065645841246544e-324d, Convert.intoDouble("4.94065645841246544e-324"), 0d);
        Assert.assertEquals(4.94065645841246544e-324d, Convert.intoDouble(4.94065645841246544e-324d), 0d);
        Assert.assertEquals(1.79769313486231570e+308d, Convert.intoDouble("1.79769313486231570e+308"), 0d);
        Assert.assertEquals(1.79769313486231570e+308d, Convert.intoDouble(1.79769313486231570e+308d), 0d);
    }
    
    /**
     * Tests {@link Convert#intoDouble(Object, double)} with valid attributes.
     */
    @Test
    public void intoDoubleDefaultUnused() {
        Assert.assertEquals(123.45d, Convert.intoDouble("123.45", 0), 0d);
        Assert.assertEquals(123.45d, Convert.intoDouble(123.45d, 0), 0d);
        Assert.assertEquals(0d, Convert.intoDouble("0", 1), 0d);
        Assert.assertEquals(0d, Convert.intoDouble(0d, 1), 0d);
        Assert.assertEquals(-123.45d, Convert.intoDouble("-123.45", 0), 0d);
        Assert.assertEquals(-123.45d, Convert.intoDouble(-123.45d, 0), 0d);
        Assert.assertEquals(4.94065645841246544e-324d, Convert.intoDouble("4.94065645841246544e-324", 0), 0d);
        Assert.assertEquals(4.94065645841246544e-324d, Convert.intoDouble(4.94065645841246544e-324d, 0), 0d);
        Assert.assertEquals(1.79769313486231570e+308d, Convert.intoDouble("1.79769313486231570e+308", 0), 0d);
        Assert.assertEquals(1.79769313486231570e+308d, Convert.intoDouble(1.79769313486231570e+308d, 0), 0d);
    }

    /**
     * Tests {@link Convert#intoDouble(Object, double)} with invalid attributes.
     */
    @Test
    public void intoDoubleDefaultUsed() {
        Assert.assertEquals(123.45d, Convert.intoDouble(null, 123.45d), 0d);
        Assert.assertEquals(123.45d, Convert.intoDouble("", 123.45d), 0d);
        Assert.assertEquals(123.45d, Convert.intoDouble(new Object(), 123.45d), 0d);
    }

}
