package de.cosmocode.commons.predicates;

import java.util.Date;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.base.Predicates;

/**
 * 
 *
 * @author Willi Schoenborn
 */
public final class DatePredicates {

    private DatePredicates() {
        
    }

    public static Predicate<Date> before(Date when) {
        return new BeforePredicate(when);
    }
    
    private static final class BeforePredicate extends AbstractPredicate<Date> {
        
        private final Date when;
        
        public BeforePredicate(Date when) {
            this.when = Preconditions.checkNotNull(when, "When");
        }
        
        @Override
        public boolean apply(Date input) {
            return input.before(when);
        };
        
        @Override
        public String toString() {
            return String.format("%s.before(%s)", DatePredicates.class.getName(), when);
        }
        
    }
    
    public static Predicate<Date> after(Date when) {
        return new AfterPredicate(when);
    }
    
    private static final class AfterPredicate extends AbstractPredicate<Date> {
        
        private final Date when;
        
        public AfterPredicate(Date when) {
            this.when = Preconditions.checkNotNull(when, "When");
        }
        
        @Override
        public boolean apply(Date input) {
            return input.after(when);
        }
        
        @Override
        public String toString() {
            return String.format("%s.after(%s)", DatePredicates.class.getName(), when);
        }
        
    }
    
    public static Predicate<Date> between(Date start, Date end) {
        return Predicates.and(after(start), before(end));
    }
    
}
