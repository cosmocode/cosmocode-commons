package de.cosmocode.commons;

import java.util.Date;

import de.cosmocode.commons.validation.AbstractRule;

/**
 * After date predicate implementation.
 *
 * @since 1.6
 * @see Dates#after(Date)
 * @author Willi Schoenborn
 */
final class DateAfterRule extends AbstractRule<Date> {
    
    private final Date when;
    
    public DateAfterRule(Date when) {
        this.when = when;
    }
    
    @Override
    public boolean apply(Date input) {
        return input.after(when);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof DateAfterRule) {
            final DateAfterRule other = DateAfterRule.class.cast(that);
            return when.equals(other.when);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return when.hashCode() ^ 35487245;
    }
    
    @Override
    public String toString() {
        return String.format("Dates.after(%s)", when);
    }
    
}
