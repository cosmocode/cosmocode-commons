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

package de.cosmocode.commons;

import java.util.Calendar;
import java.util.Date;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

import de.cosmocode.commons.validation.AbstractRule;
import de.cosmocode.commons.validation.Rule;

/**
 * Utility class providing handy methods
 * regarding {@link Date} and {@link Calendar}.
 *
 * @author Willi Schoenborn
 */
public final class Dates {

    /**
     * Prevent instantiation.
     */
    private Dates() {
        
    }
  
    /**
     * Creates a {@link Date} using a {@link Calendar}
     * and automatically adds the given amount to the
     * given field.
     * 
     * <p>
     *   This allows one liner like this:
     *   <pre>
     *     final Date in2Days = Dates.add(Calendar.DATE, 2);
     *   </pre>
     * </p>
     * 
     * @deprecated use {@link Dates#nowPlus(int, int)} instead
     * @param field the field (e.g. {@link Calendar#DATE})) which should be changed
     * @param amount the amount passed to {@link Calendar#add(int, int)}
     * @return the newly created {@link Date} instance
     */
    @Deprecated
    public static Date add(int field, int amount) {
        return nowPlus(field, amount);
    }
    
    /**
     * Creates a {@link Date} using a new {@link Calendar}
     * and automatically adds the given amount to the
     * given field.
     * 
     * <p>
     *   This allows one liner like this:
     *   <pre>
     *     final Date in2Days = Dates.nowPlus(Calendar.DATE, 2);
     *   </pre>
     * </p>
     * 
     * @param field the field (e.g. {@link Calendar#DATE})) which should be changed
     * @param amount the amount passed to {@link Calendar#add(int, int)}
     * @return the newly created {@link Date} instance
     */
    public static Date nowPlus(int field, int amount) {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(field, amount);
        return calendar.getTime();
    }

    /**
     * Creates a {@link Rule} which evaluates to true
     * when passed in a date which is before the specified date.
     * 
     * @since 1.6
     * @see Date#before(Date)
     * @param when the maximum date
     * @return a rule which returns true for all date before the specified date
     * @throws NullPointerException if when is null
     */
    public static Rule<Date> before(Date when) {
        Preconditions.checkNotNull(when, "When");
        return new BeforeRule(when);
    }
    
    /**
     * Before date predicate implementation.
     *
     * @since 1.6
     * @see Dates#before(Date)
     * @author Willi Schoenborn
     */
    private static final class BeforeRule extends AbstractRule<Date> {
        
        private final Date when;
        
        public BeforeRule(Date when) {
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

    /**
     * Creates a {@link Rule} which evaluates to true
     * when passed in a date which is after the specified date.
     * 
     * @since 1.6
     * @see Date#after(Date)
     * @param when the minimum date
     * @return a rule which returns true for all date after the specified date
     * @throws NullPointerException if when is null
     */
    public static Rule<Date> after(Date when) {
        Preconditions.checkNotNull(when, "When");
        return new AfterRule(when);
    }

    /**
     * After date predicate implementation.
     *
     * @since 1.6
     * @see Dates#after(Date)
     * @author Willi Schoenborn
     */
    private static final class AfterRule extends AbstractRule<Date> {
        
        private final Date when;
        
        public AfterRule(Date when) {
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

    /**
     * Creates a {@link Predicate} which evaluates to true
     * when passed in a date which is after the specified start date
     * and before the specified end date.
     * 
     * @since 1.6
     * @see Date#after(Date)
     * @see Date#after(Date)
     * @param start the minimum date
     * @param end the maximum date
     * @return a predicate which returns true for all date after the specified start and before the end
     * @throws NullPointerException if start or end is null
     */
    public static Predicate<Date> between(Date start, Date end) {
        return after(start).and(before(end));
    }
    
}
