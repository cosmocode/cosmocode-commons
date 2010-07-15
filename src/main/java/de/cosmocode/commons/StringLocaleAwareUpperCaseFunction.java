package de.cosmocode.commons;

import java.util.Locale;

import com.google.common.base.Preconditions;

/**
 * Implementation for {@link Strings#toUpperCase(Locale)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
final class StringLocaleAwareUpperCaseFunction extends AbstractFunction<String, String> {
    
    private final Locale locale;
    
    public StringLocaleAwareUpperCaseFunction(Locale locale) {
        this.locale = Preconditions.checkNotNull(locale, "Locale");
    }
    
    @Override
    public String apply(String from) {
        return from == null ? null : from.toUpperCase(locale);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof StringLocaleAwareUpperCaseFunction) {
            final StringLocaleAwareUpperCaseFunction other = StringLocaleAwareUpperCaseFunction.class.cast(that);
            return locale.equals(other.locale);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return locale.hashCode();
    }
    
    @Override
    public String toString() {
        return "Strings.toUpperCase(" + locale + ")";
    }
    
}
