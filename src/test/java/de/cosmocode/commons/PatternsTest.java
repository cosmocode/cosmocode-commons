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

package de.cosmocode.commons;

import java.util.Locale;
import java.util.regex.Matcher;

import org.junit.Assert;
import org.junit.Test;

import de.cosmocode.junit.Asserts;

/**
 * Tests the {@link Patterns} class.
 * 
 * @author Willi Schoenborn
 */
public class PatternsTest {

    /**
     * Tests invalid values for {@link Patterns#ISO_3166_1_ALPHA_2}.
     */
    @Test
    public void iso3166Invalid() {
        Asserts.assertDoesNotMatch(Patterns.ISO_3166_1_ALPHA_2, "");
        Asserts.assertDoesNotMatch(Patterns.ISO_3166_1_ALPHA_2, "de");
        Asserts.assertDoesNotMatch(Patterns.ISO_3166_1_ALPHA_2, "D");
        Asserts.assertDoesNotMatch(Patterns.ISO_3166_1_ALPHA_2, "DEU");
        Asserts.assertDoesNotMatch(Patterns.ISO_3166_1_ALPHA_2, "deutschland");
        Asserts.assertDoesNotMatch(Patterns.ISO_3166_1_ALPHA_2, "12");
    }

    /**
     * Tests valid values for {@link Patterns#ISO_3166_1_ALPHA_2}.
     */
    @Test
    public void iso3166Valid() {
        Asserts.assertMatches(Patterns.ISO_3166_1_ALPHA_2, "DE");
        Asserts.assertMatches(Patterns.ISO_3166_1_ALPHA_2, "AT");
        Asserts.assertMatches(Patterns.ISO_3166_1_ALPHA_2, "CH");
        Asserts.assertMatches(Patterns.ISO_3166_1_ALPHA_2, "UK");
        Asserts.assertMatches(Patterns.ISO_3166_1_ALPHA_2, "US");
    }

    /**
     * Tests invalid values for {@link Patterns#ISO_639_1}.
     */
    @Test
    public void iso639Invalid() {
        Asserts.assertDoesNotMatch(Patterns.ISO_639_1, "");
        Asserts.assertDoesNotMatch(Patterns.ISO_639_1, "DE");
        Asserts.assertDoesNotMatch(Patterns.ISO_639_1, "d");
        Asserts.assertDoesNotMatch(Patterns.ISO_639_1, "deu");
        Asserts.assertDoesNotMatch(Patterns.ISO_639_1, "deutsch");
        Asserts.assertDoesNotMatch(Patterns.ISO_639_1, "12");
    }

    /**
     * Tests valid values for {@link Patterns#ISO_639_1}.
     */
    @Test
    public void iso639Valid() {
        Asserts.assertMatches(Patterns.ISO_639_1, "de");
        Asserts.assertMatches(Patterns.ISO_639_1, "en");
        Asserts.assertMatches(Patterns.ISO_639_1, "es");
        Asserts.assertMatches(Patterns.ISO_639_1, "fr");
        Asserts.assertMatches(Patterns.ISO_639_1, "it");
    }

    /**
     * Tests invalid values for {@link Patterns#IATA_AIRPORT_CODE}.
     */
    @Test
    public void iataInvalid() {
        Asserts.assertDoesNotMatch(Patterns.IATA_AIRPORT_CODE, "");
        Asserts.assertDoesNotMatch(Patterns.IATA_AIRPORT_CODE, "txl");
        Asserts.assertDoesNotMatch(Patterns.IATA_AIRPORT_CODE, "tx");
        Asserts.assertDoesNotMatch(Patterns.IATA_AIRPORT_CODE, "txlx");
        Asserts.assertDoesNotMatch(Patterns.IATA_AIRPORT_CODE, "123");
    }

    /**
     * Tests valid values for {@link Patterns#IATA_AIRPORT_CODE}.
     */
    @Test
    public void iataValid() {
        Asserts.assertMatches(Patterns.IATA_AIRPORT_CODE, "TXL");
        Asserts.assertMatches(Patterns.IATA_AIRPORT_CODE, "SXF");
        Asserts.assertMatches(Patterns.IATA_AIRPORT_CODE, "MUC");
        Asserts.assertMatches(Patterns.IATA_AIRPORT_CODE, "NYC");
        Asserts.assertMatches(Patterns.IATA_AIRPORT_CODE, "PAR");
    }
    

    /**
     * Tests invalid values for {@link Patterns#LOCALE}.
     */
    @Test
    public void localeInvalid() {
        Asserts.assertDoesNotMatch(Patterns.LOCALE, "");
        Asserts.assertDoesNotMatch(Patterns.LOCALE, "de_");
        Asserts.assertDoesNotMatch(Patterns.LOCALE, "_de");
        Asserts.assertDoesNotMatch(Patterns.LOCALE, "de_POSIX");
        Asserts.assertDoesNotMatch(Patterns.LOCALE, "_DE_");
        Asserts.assertDoesNotMatch(Patterns.LOCALE, "__WIN");
    }

    /**
     * Tests valid values for {@link Patterns#LOCALE}.
     */
    @Test
    public void localeValid() {
        Asserts.assertMatches(Patterns.LOCALE, "de");
        Asserts.assertMatches(Patterns.LOCALE, "en");
        Asserts.assertMatches(Patterns.LOCALE, "es");
        Asserts.assertMatches(Patterns.LOCALE, "fr");
        Asserts.assertMatches(Patterns.LOCALE, "it");
        Asserts.assertMatches(Patterns.LOCALE, "de_DE");
        Asserts.assertMatches(Patterns.LOCALE, "de_AT");
        Asserts.assertMatches(Patterns.LOCALE, "en_GB");
        Asserts.assertMatches(Patterns.LOCALE, "en_US");
        Asserts.assertMatches(Patterns.LOCALE, "fr_CA");
        Asserts.assertMatches(Patterns.LOCALE, "_GB");
        Asserts.assertMatches(Patterns.LOCALE, "_DE");
        Asserts.assertMatches(Patterns.LOCALE, "_AT");
        Asserts.assertMatches(Patterns.LOCALE, "_US");
        Asserts.assertMatches(Patterns.LOCALE, "_ES");
        Asserts.assertMatches(Patterns.LOCALE, "en_US_WIN");
        Asserts.assertMatches(Patterns.LOCALE, "de_DE_POSIX");
        Asserts.assertMatches(Patterns.LOCALE, "fr_CA_MAC");
        Asserts.assertMatches(Patterns.LOCALE, "es_ES_Traditional");
        Asserts.assertMatches(Patterns.LOCALE, "en_UK_LINUX");
        Asserts.assertMatches(Patterns.LOCALE, "en__WIN");
        Asserts.assertMatches(Patterns.LOCALE, "de__POSIX");
        Asserts.assertMatches(Patterns.LOCALE, "fr__MAC");
        Asserts.assertMatches(Patterns.LOCALE, "es__Traditional");
        Asserts.assertMatches(Patterns.LOCALE, "en__LINUX");
        Asserts.assertMatches(Patterns.LOCALE, "_US_WIN");
        Asserts.assertMatches(Patterns.LOCALE, "_DE_POSIX");
        Asserts.assertMatches(Patterns.LOCALE, "_CA_MAC");
        Asserts.assertMatches(Patterns.LOCALE, "_ES_Traditional");
        Asserts.assertMatches(Patterns.LOCALE, "_UK_LINUX");
    }

    /**
     * Tests the correct groups for
     * "Language" Locales. 
     */
    @Test
    public void localeGroupsLanguage() {
        final Locale locale = new Locale("en");
        final String value = locale.toString();
        
        Assert.assertEquals("en", value);
        final Matcher matcher = Patterns.LOCALE.matcher(value);
        Assert.assertTrue(matcher.matches());
       
        Assert.assertEquals("en", matcher.group(1));
        Asserts.assertEmpty(matcher.group(2));
        Asserts.assertEmpty(matcher.group(3));
    }

    /**
     * Tests the correct groups for
     * "Language Country" Locales. 
     */
    @Test
    public void localeGroupsLanguageCountry() {
        final Locale locale = new Locale("en", "GB");
        final String value = locale.toString();
        
        Assert.assertEquals("en_GB", value);
        final Matcher matcher = Patterns.LOCALE.matcher(value);
        Assert.assertTrue(matcher.matches());
       
        Assert.assertEquals("en", matcher.group(1));
        Assert.assertEquals("GB", matcher.group(2));
        Asserts.assertEmpty(matcher.group(3));
    }

    /**
     * Tests the correct groups for
     * "Language Variant" Locales. 
     */
    @Test
    public void localeGroupsLanguageVariant() {
        final Locale locale = new Locale("en", "", "MAC");
        final String value = locale.toString();
        
        Assert.assertEquals("en__MAC", value);
        final Matcher matcher = Patterns.LOCALE.matcher(value);
        Assert.assertTrue(matcher.matches());
       
        Assert.assertEquals("en", matcher.group(1));
        Asserts.assertEmpty(matcher.group(2));
        Assert.assertEquals("MAC", matcher.group(3));
    }
    
    /**
     * Tests the correct groups for
     * "Language Country Variant" Locales. 
     */
    @Test
    public void localeGroupsLanguageCountryVariant() {
        final Locale locale = new Locale("de", "DE", "WIN");
        final String value = locale.toString();
        
        Assert.assertEquals("de_DE_WIN", value);
        final Matcher matcher = Patterns.LOCALE.matcher(value);
        Assert.assertTrue(matcher.matches());
       
        Assert.assertEquals("de", matcher.group(1));
        Assert.assertEquals("DE", matcher.group(2));
        Assert.assertEquals("WIN", matcher.group(3));
    }

    /**
     * Tests the correct groups for
     * "Country" Locales. 
     */
    @Test
    public void localeGroupsCountry() {
        final Locale locale = new Locale("", "GB");
        final String value = locale.toString();
        
        Assert.assertEquals("_GB", value);
        final Matcher matcher = Patterns.LOCALE.matcher(value);
        Assert.assertTrue(matcher.matches());
       
        Asserts.assertEmpty(matcher.group(1));
        Assert.assertEquals("GB", matcher.group(2));
        Asserts.assertEmpty(matcher.group(3));
    }

    /**
     * Tests the correct groups for
     * "Country Variant" Locales. 
     */
    @Test
    public void localeGroupsCountryVariant() {
        final Locale locale = new Locale("", "GB", "POSIX");
        final String value = locale.toString();
        
        Assert.assertEquals("_GB_POSIX", value);
        final Matcher matcher = Patterns.LOCALE.matcher(value);
        Assert.assertTrue(matcher.matches());
       
        Asserts.assertEmpty(matcher.group(1));
        Assert.assertEquals("GB", matcher.group(2));
        Assert.assertEquals("POSIX", matcher.group(3));
    }
    
    /**
     * Tests invalid values for {@link Patterns#INTERNET_ADDRESS}.
     */
    @Test
    public void internetAddressInvalid() {
        Asserts.assertDoesNotMatch(Patterns.INTERNET_ADDRESS, "");
        Asserts.assertDoesNotMatch(Patterns.INTERNET_ADDRESS, "1.1.1");
        Asserts.assertDoesNotMatch(Patterns.INTERNET_ADDRESS, "1.1.1.");
        Asserts.assertDoesNotMatch(Patterns.INTERNET_ADDRESS, ".1.1.1");
        Asserts.assertDoesNotMatch(Patterns.INTERNET_ADDRESS, "192.168.0.256");
        Asserts.assertDoesNotMatch(Patterns.INTERNET_ADDRESS, "192.168.0.-1");
        
    }

    /**
     * Tests valid values for {@link Patterns#INTERNET_ADDRESS}.
     */
    public void internetAddressValid() {
        Asserts.assertMatches(Patterns.INTERNET_ADDRESS, "192.168.100.1");
        Asserts.assertMatches(Patterns.INTERNET_ADDRESS, "192.168.200.0");
        Asserts.assertMatches(Patterns.INTERNET_ADDRESS, "0.0.0.0");
        Asserts.assertMatches(Patterns.INTERNET_ADDRESS, "255.255.255.255");
    }
    
}
