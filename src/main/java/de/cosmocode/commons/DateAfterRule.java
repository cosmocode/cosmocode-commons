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
    public String toString() {
        return String.format("Dates.after(%s)", when);
    }
    
}
