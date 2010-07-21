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

import org.junit.Assert;
import org.junit.Test;

import de.cosmocode.collections.utility.Convert;

/**
 * Tests {@link Convert#intoBoolean(Object)}
 * and {@link Convert#intoBoolean(Object, boolean)}.
 *
 * @author Willi Schoenborn
 */
public class ConvertBooleanTest {
    
    /**
     * Tests {@link Convert#intoBoolean(Object)} with null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoBooleanNull() {
        Convert.intoBoolean(null);
    }
        
    /**
     * Tests {@link Convert#intoBoolean(Object)} with "".
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoBooleanEmpty() {
        Convert.intoBoolean("");
    }

    /**
     * Tests {@link Convert#intoBoolean(Object)} with new Object().
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoBooleanObject() {
        Convert.intoBoolean(new Object());
    }

    /**
     * Tests {@link Convert#intoBoolean(Object)} with valid attributes.
     */
    @Test
    public void intoBoolean() {
        Assert.assertTrue(Convert.intoBoolean(true));
        Assert.assertTrue(Convert.intoBoolean(Boolean.TRUE));
        Assert.assertTrue(Convert.intoBoolean("true"));
        Assert.assertTrue(Convert.intoBoolean("TrUe"));
        
        Assert.assertFalse(Convert.intoBoolean(false));
        Assert.assertFalse(Convert.intoBoolean(Boolean.FALSE));
        Assert.assertFalse(Convert.intoBoolean("false"));
        Assert.assertFalse(Convert.intoBoolean("FalSe"));
    }

    /**
     * Tests {@link Convert#intoBoolean(Object, boolean)} with valid attributes.
     */
    @Test
    public void intoBooleanDefaultUnused() {
        Assert.assertTrue(Convert.intoBoolean(true, false));
        Assert.assertTrue(Convert.intoBoolean(Boolean.TRUE, false));
        Assert.assertTrue(Convert.intoBoolean("true", false));
        Assert.assertTrue(Convert.intoBoolean("TrUe", false));
        
        Assert.assertFalse(Convert.intoBoolean(false, true));
        Assert.assertFalse(Convert.intoBoolean(Boolean.FALSE, true));
        Assert.assertFalse(Convert.intoBoolean("false", true));
        Assert.assertFalse(Convert.intoBoolean("FalSe", true));
    }

    /**
     * Tests {@link Convert#intoBoolean(Object, boolean)} with invalid attributes.
     */
    @Test
    public void intoBooleanDefaultUsed() {
        Assert.assertTrue(Convert.intoBoolean(null, true));
        Assert.assertTrue(Convert.intoBoolean("", true));
        Assert.assertTrue(Convert.intoBoolean(new Object(), true));
        
        Assert.assertFalse(Convert.intoBoolean(null, false));
        Assert.assertFalse(Convert.intoBoolean("", false));
        Assert.assertFalse(Convert.intoBoolean(new Object(), false));
    }

}
