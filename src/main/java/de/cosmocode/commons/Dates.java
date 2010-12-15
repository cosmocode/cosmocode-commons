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

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

import de.cosmocode.commons.validation.Rule;
import de.cosmocode.commons.validation.Rules;

import javax.annotation.Nullable;

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
     * Special equalTo for Dates that takes the oddities of Timestamp into account.
     * This means that if this method is passed a Timestamp or the resulting Rule is applied to a Timestamp
     * then apply will return true if either one (input or given Date) returns true on equals.
     * It does not affect otherwise normal behaviour.
     *
     * @since 1.20
     * @see Rules#equalTo(Object)
     * @see java.sql.Timestamp
     * @param other the other date to compare to
     * @return a rule which returns true if both Dates denote the same time
     */
    public static Rule<Date> equalTo(@Nullable Date other) {
        return (other == null)
            ? Rules.<Date>isNull()
            : new DateEqualsRule(other);
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
        return new DateBeforeRule(when);
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
        return new DateAfterRule(when);
    }

    /**
     * Creates a {@link Predicate} which evaluates to true
     * when passed in a date which is after the specified start date
     * and before the specified end date.
     * 
     * @since 1.6
     * @see Date#before(Date)
     * @see Date#after(Date)
     * @param start the minimum date
     * @param end the maximum date
     * @return a predicate which returns true for all date after the specified start and before the end
     * @throws NullPointerException if start or end is null
     */
    public static Rule<Date> between(Date start, Date end) {
        return after(start).and(before(end));
    }
    
    /**
     * <p>
     * Creates a {@link Rule} which evaluates to true
     * when passed in a date which is after the specified start date (inclusive)
     * and before the specified end date (inclusive).
     * </p>
     * <p>
     * This means that a date which is equal to the start or the end date is considered
     * as between the start and end date by the created rule and apply(date) returns true then.
     * </p>
     * 
     * @since 1.20
     * @see Date#before(Date)
     * @see Date#after(Date)
     * @see Dates#between(Date, Date)
     * @see Dates#equalTo(Date)
     * @param start the minimum date (inclusive)
     * @param end the maximum date (inclusive)
     * @return a predicate which returns true for all date equal to or after the specified start 
     *         and equal to or before the end
     * @throws NullPointerException if start or end is null
     */
    public static Rule<Date> betweenInclusive(Date start, Date end) {
        return between(start, end).or(equalTo(start)).or(equalTo(end));
    }

    /**
     * Returns an immutable {@link TimePeriod}, created from the given start and end.
     *
     * @param start the start date of the period
     * @param end the end date of the period
     * @return a new TimePeriod that spans the time between start and end
     */
    @Beta
    public static TimePeriod immutablePeriodOf(final Date start, final Date end) {
        return new ImmutableDateTimePeriod(start, end);
    }

}
