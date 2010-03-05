package de.cosmocode.commons.predicates;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * 
 *
 * @author Willi Schoenborn
 */
public final class StringPredicates {

    private StringPredicates() {
        
    }
    
    public static Predicate<String> contains(String s) {
        return new ContainsPredicate(s);
    }
    
    private static final class ContainsPredicate extends AbstractPredicate<String> {
        
        private final String s;
        
        public ContainsPredicate(String s) {
            this.s = Preconditions.checkNotNull(s, "String");
        }
      
        @Override
        public boolean apply(String input) {
            return input.contains(s);
        };

        @Override
        public String toString() {
            return String.format("%s.contains(%s)", StringPredicates.class.getName(), s);
        }
    }

}
