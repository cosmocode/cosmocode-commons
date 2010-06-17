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

import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;

import de.cosmocode.commons.Codec;
import de.cosmocode.commons.Patterns;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Locale;
import java.util.Map;
import java.util.MissingResourceException;

/**
 * A {@link CountryIsoConverter} that is backed by {@link Locale}.
 *
 * @since 1.8
 * @author Oliver Lorenz
 */
public final class LocaleCountryIsoConverter extends Codec<Locale, String> implements CountryIsoConverter {

    private static final Logger LOG = LoggerFactory.getLogger(LocaleCountryIsoConverter.class);
    
    private final Map<String, String> cache = Maps.newHashMap();

    @Override
    public String toAlpha3(String iso3166Alpha2) {
        if (Patterns.ISO_3166_1_ALPHA_2.matcher(iso3166Alpha2).matches()) {
            return encode(new Locale("", iso3166Alpha2));
        } else {
            throw new IllegalArgumentException("given country code " + iso3166Alpha2  + " not in ISO 3166 alpha-2");
        }
    }

    @Override
    public String toAlpha2(String iso3166Alpha3) {
        Preconditions.checkNotNull(iso3166Alpha3, "iso3166Alpha3 must not be null");
        if (Patterns.ISO_3166_1_ALPHA_2.matcher(iso3166Alpha3).matches()) {
            // already ISO 3166-1 alpha-2 (two letter)
            return iso3166Alpha3;
        }

        // try to get two letter code from cache
        final String fromCache = cache.get(iso3166Alpha3);
        if (fromCache != null) {
            return fromCache;
        }

        // search for three letter code in the known country codes of Locale
        for (final String alpha2 : Locale.getISOCountries()) {
            final String alpha3 = new Locale("", alpha2).getISO3Country();
            if (iso3166Alpha3.equals(alpha3)) {
                LOG.trace("Found alpha-2: {} for alpha-3: {}", alpha2, alpha3);
                synchronized (cache) {
                    cache.put(iso3166Alpha3, alpha2);
                }
                return alpha2;
            }
        }

        // if we arrive here then the Locale class could not find the iso3 code
        throw new IsoConversionException("No known alpha-2 code for " + iso3166Alpha3);
    }

    @Override
    public String encode(Locale input) {
        Preconditions.checkNotNull(input);
        try {
            return input.getISO3Country();
        } catch (MissingResourceException e) {
            throw new IsoConversionException("No known alpha-3 code for " + input, e);
        }
    }

    @Override
    public Locale decode(String input) {
        return new Locale("", toAlpha2(input));
    }
}
