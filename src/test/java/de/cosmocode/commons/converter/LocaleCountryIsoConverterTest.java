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

package de.cosmocode.commons.converter;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

import org.junit.Assert;
import org.junit.Test;

import de.cosmocode.junit.UnitProvider;

/**
 * Tests {@link LocaleCountryIsoConverter}.
 *
 * @author Oliver Lorenz
 */
public class LocaleCountryIsoConverterTest implements UnitProvider<LocaleCountryIsoConverter> {
    
    private static final Map<String, String> A3_TO_A2;
    
    static {
        // just a few test countries 
        A3_TO_A2 = new HashMap<String, String>();
        A3_TO_A2.put("DEU", "DE");
        A3_TO_A2.put("GBR", "GB");
        A3_TO_A2.put("FRA", "FR");
        A3_TO_A2.put("AFG", "AF");
        A3_TO_A2.put("EGY", "EG");
        A3_TO_A2.put("AUS", "AU");
    }
    
    @Override
    public LocaleCountryIsoConverter unit() {
        return new LocaleCountryIsoConverter();
    }

    /**
     * Tests {@link LocaleCountryIsoConverter#toAlpha3(String)}.
     */
    @Test
    public void toAlpha3() {
        for (final String alpha2 : Locale.getISOCountries()) {
            final String expected = new Locale("", alpha2).getISO3Country();
            final String actual = unit().toAlpha3(alpha2);
            Assert.assertEquals(expected, actual);
        }
    }

    /**
     * Tests {@link LocaleCountryIsoConverter#toAlpha3(String)} with an alpha-3 as input.
     * Should return the input.
     */
    @Test
    public void toAlpha3OfAlpha3() {
        final String expected = "DEU";
        final String actual = unit().toAlpha3("DEU");
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link LocaleCountryIsoConverter#toAlpha3(String)} with a blank input.
     */
    @Test
    public void toAlpha3OfBlank() {
        final String expected = "";
        final String actual = unit().toAlpha3("   ");
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link LocaleCountryIsoConverter#toAlpha2(String)}.
     */
    @Test
    public void toAlpha2() {
        for (final String alpha3 : A3_TO_A2.keySet()) {
            final String expected = A3_TO_A2.get(alpha3);
            final String actual = unit().toAlpha2(alpha3);
            Assert.assertEquals(expected, actual);
        }
    }

    /**
     * Tests {@link LocaleCountryIsoConverter#toAlpha2(String)} with an alpha-2 as input.
     * Should return the input.
     */
    @Test
    public void toAlpha2OfAlpha2() {
        final String expected = Locale.GERMAN.getCountry();
        final String actual = unit().toAlpha2(expected);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link LocaleCountryIsoConverter#toAlpha2(String)} with a blank input.
     */
    @Test
    public void toAlpha2OfBlank() {
        final String expected = "";
        final String actual = unit().toAlpha2("  ");
        Assert.assertEquals(expected, actual);
    }

}
