package de.cosmocode.commons;

import java.util.Date;

import de.cosmocode.commons.validation.AbstractRule;

/**
 * Before date predicate implementation.
 *
 * @since 1.6
 * @see Dates#before(Date)
 * @author Willi Schoenborn
 */
final class DateBeforeRule extends AbstractRule<Date> {
    
    private final Date when;
    
    public DateBeforeRule(Date when) {
        this.when = when;
    }
    
    @Override
    public boolean apply(Date input) {
        return input.before(when);
    };
    
    @Override
    public String toString() {
        return String.format("Dates.before(%s)", when);
    }
    
}
