package de.cosmocode.commons;

import java.util.Locale;
import java.util.regex.Matcher;

import com.google.common.base.Preconditions;

/**
 * Utility class providing constant and
 * frequently used {@link Locale}s.
 *
 * @author Willi Schoenborn
 */
public final class Locales {

    public static final Locale SPANISH = new Locale("es");
    public static final Locale AUSTRIA = new Locale("de", "AT");
    
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
        if (matcher.matches()) {
            final String language = Strings.defaultIfBlank(matcher.group(1), "");
            final String country = Strings.defaultIfBlank(matcher.group(2), "");
            final String variant = Strings.defaultIfBlank(matcher.group(3), "");
            return new Locale(language, country, variant);
        } else {
            final String message = String.format("%s does not match %", value, Patterns.LOCALE.pattern());
            throw new IllegalArgumentException(message);
        }
    }
    
}
