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
 * Tests {@link LocaleLanguageIsoConverter}.
 *
 * @author Oliver Lorenz
 */
public class LocaleLanguageIsoConverterTest implements UnitProvider<LocaleLanguageIsoConverter> {
    
    private static final Map<String, String> A3_TO_A2;
    
    static {
        // just a few test languages 
        A3_TO_A2 = new HashMap<String, String>();
        A3_TO_A2.put("chu", "cu");
        A3_TO_A2.put("deu", "de");
        A3_TO_A2.put("jpn", "ja");
        A3_TO_A2.put("lav", "lv");
        A3_TO_A2.put("zho", "zh");
        A3_TO_A2.put("epo", "eo");
    }
    
    @Override
    public LocaleLanguageIsoConverter unit() {
        return new LocaleLanguageIsoConverter();
    }

    /**
     * Tests {@link LocaleLanguageIsoConverter#toThreeLetter(String)}.
     */
    @Test
    public void toThreeLetter() {
        for (final String alpha2 : Locale.getISOLanguages()) {
            final String expected = new Locale(alpha2).getISO3Language();
            final String actual = unit().toThreeLetter(alpha2);
            Assert.assertEquals(expected, actual);
        }
    }

    /**
     * Tests {@link LocaleLanguageIsoConverter#toThreeLetter(String)} with a three-letter code.
     */
    @Test
    public void toThreeLetterOfThreeLetter() {
        final String expected = "deu";
        final String actual = unit().toThreeLetter(expected);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link LocaleLanguageIsoConverter#toThreeLetter(String)} with a blank String.
     */
    @Test
    public void toThreeLetterOfBlank() {
        final String expected = "";
        final String actual = unit().toThreeLetter("   ");
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link LocaleLanguageIsoConverter#toTwoLetter(String)}.
     */
    @Test
    public void toTwoLetter() {
        for (final String alpha3 : A3_TO_A2.keySet()) {
            final String expected = A3_TO_A2.get(alpha3);
            final String actual = unit().toTwoLetter(alpha3);
            Assert.assertEquals(expected, actual);
        }
    }

    /**
     * Tests {@link LocaleLanguageIsoConverter#toTwoLetter(String)} with a two-letter code.
     */
    @Test
    public void toTwoLetterOfTwoLetter() {
        final String expected = Locale.GERMAN.getLanguage();
        final String actual = unit().toTwoLetter(expected);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link LocaleLanguageIsoConverter#toTwoLetter(String)} with a blank String.
     */
    @Test
    public void toTwoLetterOfBlank() {
        final String expected = "";
        final String actual = unit().toTwoLetter("  ");
        Assert.assertEquals(expected, actual);
    }

}
