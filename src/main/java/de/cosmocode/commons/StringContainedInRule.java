package de.cosmocode.commons;

import com.google.common.base.Preconditions;

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
        this.s = Preconditions.checkNotNull(s, "String");
    }
    
    @Override
    public boolean apply(CharSequence input) {
        return s.contains(input);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof StringContainedInRule) {
            final StringContainedInRule other = StringContainedInRule.class.cast(that);
            return s.equals(other.s);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return s.hashCode() ^ 358941548;
    }
    
    @Override
    public String toString() {
        return "Strings.containedIn(" + s + ")";
    }
    
}
