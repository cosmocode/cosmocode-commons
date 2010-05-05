/**
 * Copyright 2010 CosmoCode GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
            return String.format("before(%s)", when);
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
            return String.format("after(%s)", when);
        }
        
    }
    
    public static Predicate<Date> between(Date start, Date end) {
        return Predicates.and(after(start), before(end));
    }
    
}
