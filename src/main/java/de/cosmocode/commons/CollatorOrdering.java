package de.cosmocode.commons;

import java.text.Collator;
import java.util.Locale;

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.Ordering;

/**
 * An {@link Ordering} which is based on a given {@link Collator}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
final class CollatorOrdering extends Ordering<String> {

    private final Locale locale;
    private final Collator collator;

    public CollatorOrdering(Locale locale) {
        this.locale = Preconditions.checkNotNull(locale, "Locale");
        this.collator = Collator.getInstance(locale);
    }
    
    @Override
    public int compare(String left, String right) {
        return collator.compare(left, right);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof CollatorOrdering) {
            final CollatorOrdering other = CollatorOrdering.class.cast(that);
            return locale.equals(other.locale) && collator.equals(other.collator);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(locale, collator);
    }
    
    @Override
    public String toString() {
        return "Strings.orderBy(" + locale + ")";
    }
    
}
