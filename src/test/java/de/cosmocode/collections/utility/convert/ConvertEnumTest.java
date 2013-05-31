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
 * Tests {@link Convert#intoEnum(Object, Class)} and
 * {@link Convert#intoEnum(Object, Class, Enum)}.
 *
 * @author Willi Schoenborn
 */
public class ConvertEnumTest {

    /**
     * Dummy enum.
     *
     * @author Willi Schoenborn
     */
    private static enum Continent {
        
        EUROPE,
        ASIA,
        AUSTRALIA,
        AFRICA,
        NORTH_AMERICA,
        SOUTH_AMERICA,
        ANTARCTICA;
        
    }

    /**
     * Tests {@link Convert#intoEnum(Object, Class)} with null enumType.
     */
    @Test(expected = NullPointerException.class)
    public void intoEnumNullType() {
        final Class<Continent> enumType = null;
        Convert.intoEnum("EUROPE", enumType);
    }

    /**
     * Tests {@link Convert#intoEnum(Object, Class, Enum)} with null enumType.
     */
    @Test(expected = NullPointerException.class)
    public void intoEnumDefaultNullType() {
        final Class<Continent> enumType = null;
        Convert.intoEnum("EUROPE", enumType, null);
    }
    
    /**
     * Tests {@link Convert#intoEnum(Object, Class)} with null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoEnumNull() {
        Convert.intoEnum(null, Continent.class);
    }

    /**
     * Tests {@link Convert#intoEnum(Object, Class)} with "".
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoEnumEmpty() {
        Convert.intoEnum("", Continent.class);
    }
    
    /**
     * Tests {@link Convert#intoEnum(Object, Class)} with new Object().
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoEnumObject() {
        Convert.intoEnum(new Object(), Continent.class);
    }
    
    /**
     * Tests {@link Convert#intoEnum(Object, Class)} with {@link Integer#MAX_VALUE}.
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoEnunHighOrdinal() {
        Convert.intoEnum(Integer.MAX_VALUE, Continent.class);
    }
    
    /**
     * Tests {@link Convert#intoEnum(Object, Class)} with valid attributes.
     */
    @Test
    public void intoEnum() {
        for (Continent continent : Continent.values()) {
            Assert.assertSame(continent, Convert.intoEnum(continent, Continent.class));
            Assert.assertSame(continent, Convert.intoEnum(continent.name(), Continent.class));
            Assert.assertSame(continent, Convert.intoEnum(continent.name().toLowerCase(), Continent.class));
            Assert.assertSame(continent, Convert.intoEnum(continent.ordinal(), Continent.class));
        }
    }
    
    /**
     * Tests {@link Convert#intoEnum(Object, Class, Enum)} with valid attributes.
     */
    @Test
    public void intoEnumDefaultUnused() {
        for (Continent continent : Continent.values()) {
            Assert.assertSame(continent, Convert.intoEnum(continent, Continent.class, null));
            Assert.assertSame(continent, Convert.intoEnum(continent.name(), Continent.class, null));
            Assert.assertSame(continent, Convert.intoEnum(continent.name().toLowerCase(), Continent.class, null));
            Assert.assertSame(continent, Convert.intoEnum(continent.ordinal(), Continent.class, null));
        }
    }

    /**
     * Tests {@link Convert#intoEnum(Object, Class, Enum)} with invalid attributes.
     */
    @Test
    public void intoEnumDefaultUsed() {
        Assert.assertSame(Continent.EUROPE, Convert.intoEnum(null, Continent.class, Continent.EUROPE));
        Assert.assertSame(Continent.EUROPE, Convert.intoEnum("", Continent.class, Continent.EUROPE));
        Assert.assertSame(Continent.EUROPE, Convert.intoEnum(new Object(), Continent.class, Continent.EUROPE));
        Assert.assertSame(Continent.EUROPE, Convert.intoEnum(-1, Continent.class, Continent.EUROPE));
    }

}
