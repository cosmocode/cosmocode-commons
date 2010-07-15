package de.cosmocode.commons;

import de.cosmocode.commons.validation.AbstractRule;

/**
 * Contains string predicate.
 *
 * @since 1.6
 * @see Strings#contains(CharSequence)
 * @author Willi Schoenborn
 */
final class StringContainsRule extends AbstractRule<String> {
    
    private final CharSequence s;
    
    public StringContainsRule(CharSequence s) {
        this.s = s;
    }

    @Override
    public boolean apply(String input) {
        return input.contains(s);
    };

    @Override
    public String toString() {
        return "Strings.contains(" + s + ")";
    }
    
}
