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

import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

/**
 * A {@link LanguageIsoConverter} that is backed by {@link Locale}.
 *
 * @author Oliver Lorenz
 */
public class LocaleLanguageIsoConverter implements LanguageIsoConverter {

    private static final Logger LOG = LoggerFactory.getLogger(LocaleLanguageIsoConverter.class);
    
    private final Map<String, String> cache = Maps.newHashMap();
    
    @Override
    public String toThreeLetter(String iso6391) {
        try {
            return new Locale(iso6391).getISO3Language();
        } catch (MissingResourceException e) {
            throw new IsoConversionException("No known three-letter code for " + iso6391, e);
        }
    }
    
    @Override
    public String toTwoLetter(String iso6392) {
        Preconditions.checkNotNull(iso6392, "iso6392 must not be null");

        // try to get two letter code from cache
        final String fromCache = cache.get(iso6392);
        if (fromCache != null) {
            return fromCache;
        }

        // search for three letter code in the known language codes of Locale
        for (final String iso6391 : Locale.getISOLanguages()) {
            final String threeLetter = new Locale(iso6391).getISO3Language();
            if (iso6392.equals(threeLetter)) {
                LOG.trace("Found ISO 639-1 language code: {} for ISO 639-2 language code: {}", iso6391, iso6392);
                cache.put(iso6392, iso6391);
                return iso6391;
            }
        }

        // if we arrive here then the Locale class could not find the iso3 code
        throw new IsoConversionException("No known ISO 639-1 language code for " + iso6392);
    }

}
