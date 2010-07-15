package de.cosmocode.commons;

import java.util.Locale;

import com.google.common.base.Preconditions;

/**
 * Implementation for {@link Strings#toLowerCase(Locale)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
final class StringLocaleAwareLowerCaseFunction extends AbstractFunction<String, String> {

    private final Locale locale;
    
    public StringLocaleAwareLowerCaseFunction(Locale locale) {
        this.locale = Preconditions.checkNotNull(locale, "Locale");
    }
    
    @Override
    public String apply(String from) {
        return from == null ? null : from.toLowerCase(locale);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof StringLocaleAwareLowerCaseFunction) {
            final StringLocaleAwareLowerCaseFunction other = StringLocaleAwareLowerCaseFunction.class.cast(that);
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
        return "Strings.toLowerCase(" + locale + ")";
    }
    
}
