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

import com.google.common.collect.ImmutableList;
import de.cosmocode.collections.utility.Convert;
import de.cosmocode.junit.Asserts;
import org.junit.Assert;
import org.junit.Test;

import java.util.Locale;

/**
 * Tests {@link Convert#intoLocale(Object)} and
 * {@link Convert#intoLocale(Object, Locale)}.
 *
 * @author Willi Schoenborn
 */
public class ConvertLocaleTest {

    private static final ImmutableList<Locale> LOCALES = new ImmutableList.Builder<Locale>().add(
        Locale.CANADA, 
        Locale.CANADA_FRENCH,
        Locale.CHINA,
        Locale.CHINESE,
        Locale.ENGLISH,
        Locale.FRANCE,
        Locale.FRENCH,
        Locale.GERMAN, 
        Locale.GERMANY,
        Locale.ITALIAN,
        Locale.ITALY,
        Locale.JAPAN,
        Locale.JAPANESE,
        Locale.KOREA,
        Locale.KOREAN,
        Locale.PRC,
        Locale.SIMPLIFIED_CHINESE,
        Locale.TAIWAN,
        Locale.TRADITIONAL_CHINESE,
        Locale.UK,
        Locale.US
    ).build();
    
    /**
     * Tests {@link Convert#intoLocale(Object)} with null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoLocaleNull() {
        Convert.intoLocale(null);
    }

    /**
     * Tests {@link Convert#intoLocale(Object)} with "".
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoLocaleEmpty() {
        Convert.intoLocale("");
    }
    
    /**
     * Tests {@link Convert#intoLocale(Object)} with new Object().
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoLocaleObject() {
        Convert.intoLocale(new Object());
    }
    
    /**
     * Tests {@link Convert#intoLocale(Object)} with valid attributes.
     */
    @Test
    public void intoLocale() {
        for (Locale locale : LOCALES) {
            Assert.assertEquals(locale, Convert.intoLocale(locale.toString()));
            Assert.assertEquals(locale, Convert.intoLocale(locale));
        }
    }
    
    /**
     * Tests {@link Convert#intoLocale(Object, long)} with valid attributes.
     */
    @Test
    public void intoLocaleDefaultUnused() {
        final Locale defaultValue = new Locale("de_CH_MAC");
        for (Locale locale : LOCALES) {
            Asserts.assertNotEquals(defaultValue, Convert.intoLocale(locale.toString(), defaultValue));
            Asserts.assertNotEquals(defaultValue, Convert.intoLocale(locale, defaultValue));
        }
    }

    /**
     * Tests {@link Convert#intoLocale(Object, long)} with invalid attributes.
     */
    @Test
    public void intoLocaleDefaultUsed() {
        final Locale defaultValue = new Locale("de_CH_MAC");
        Assert.assertSame(defaultValue, Convert.intoLocale("", defaultValue));
        Assert.assertSame(defaultValue, Convert.intoLocale(null, defaultValue));
        Assert.assertSame(defaultValue, Convert.intoLocale(new Object(), defaultValue));
    }

}
