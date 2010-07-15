package de.cosmocode.commons;

import com.google.common.base.Preconditions;

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
        this.s = Preconditions.checkNotNull(s, "Sequence");
    }

    @Override
    public boolean apply(String input) {
        return input.contains(s);
    };
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof StringContainsRule) {
            final StringContainsRule other = StringContainsRule.class.cast(that);
            return s.equals(other.s);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return s.hashCode() ^ -983245472;
    }
    
    @Override
    public String toString() {
        return "Strings.contains(" + s + ")";
    }
    
}
