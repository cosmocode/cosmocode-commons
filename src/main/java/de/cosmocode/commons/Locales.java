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
package de.cosmocode.commons;

import com.google.common.base.Preconditions;

import java.util.Locale;
import java.util.regex.Matcher;

/**
 * Utility class providing constant and
 * frequently used {@link Locale}s.
 *
 * @author Willi Schoenborn
 */
public final class Locales {

    public static final Locale SPANISH = new Locale("es");
    
    public static final Locale AUSTRIA = new Locale("de", "AT");
    
    public static final Locale AUSTRALIA = new Locale("en", "AU");
    
    public static final Locale NEW_ZEALAND = new Locale("en", "NZ");
    
    /**
     * Prevent instantiation.
     */
    private Locales() {
        
    }
    
    /**
     * Parses a string into a {@link Locale}.
     * 
     * @param value the locale string
     * @return a new {@link Locale} parsed from value
     * @throws NullPointerException if value is null
     * @throws IllegalArgumentException if value is no valid locale
     */
    public static Locale parse(String value) {
        Preconditions.checkNotNull(value, "Value");
        final Matcher matcher = Patterns.LOCALE.matcher(value);
        Preconditions.checkArgument(matcher.matches(), "%s does not match %s", value, Patterns.LOCALE);
        final String language = Strings.defaultIfBlank(matcher.group(1), "");
        final String country = Strings.defaultIfBlank(matcher.group(2), "");
        final String variant = Strings.defaultIfBlank(matcher.group(3), "");
        return new Locale(language, country, variant);
    }
    
}
