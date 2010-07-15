package de.cosmocode.commons;

import de.cosmocode.commons.validation.AbstractRule;

/**
 * Contained in string predicate.
 *
 * @since 1.6
 * @see Strings#containedIn(String)
 * @author Willi Schoenborn
 */
final class StringContainedInRule extends AbstractRule<CharSequence> {
    
    private final String s;
    
    public StringContainedInRule(String s) {
        this.s = s;
    }
    
    @Override
    public boolean apply(CharSequence input) {
        return s.contains(input);
    }
    
    @Override
    public String toString() {
        return "Strings.containedIn(" + s + ")";
    }
    
}
