/**
 * Copyright 2010 - 2013 CosmoCode GmbH
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

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import de.cosmocode.commons.base.MoreObjects;
import de.cosmocode.commons.validation.Rule;

import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

/**
 * Utility class providing handy methods
 * regarding {@link Date} and {@link Calendar}.
 *
 * @author Willi Schoenborn
 * @author Oliver Lorenz
 */
public final class Dates {

    private static final long MILLISECONDS_PER_DAY = 24L * 60 * 60 * 1000;

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
     * @param start the minimum date (inclusive)
     * @param end the maximum date (inclusive)
     * @return a predicate which returns true for all date equal to or after the specified start 
     *         and equal to or before the end
     * @throws NullPointerException if start or end is null
     */
    public static Rule<Date> betweenInclusive(Date start, Date end) {
        return between(start, end).or(MoreObjects.asymmetricEqualTo(start)).or(MoreObjects.asymmetricEqualTo(end));
    }

    /**
     * Sets a {@link Date} to midnight (00:00:00) at
     * the date currently selected.
     * This is the {@link Date}s equivalent of
     * {@link Calendars#toBeginningOfTheDay(Calendar)}.
     *
     * <p>
     * Calling this method is faster than to instantiate a new calendar
     * and use the Calendars.toBeginningOfTheDay method on that calendar.
     * But it is slower than just calling Calendars.toBeginningOfTheDay on a calendar
     * so this method is mostly a performance optimization for plain Date objects.
     * </p>
     *
     * @since 1.21
     * @param date the {@link Date} which will be set to midnight
     */
    public static void toBeginningOfTheDay(final Date date) {
        final long oldTime = date.getTime();
        final long timeZoneOffset = TimeZone.getDefault().getOffset(oldTime);

        /* we make an integer division by the milliseconds per day, to strip the old time
         * from the hours, minutes, seconds and milliseconds.
         * Then we multiply it with that value again to get a valid timestamp again
         */
        date.setTime(((oldTime + timeZoneOffset) / MILLISECONDS_PER_DAY) * MILLISECONDS_PER_DAY - timeZoneOffset);
    }

    /**
     * Returns an immutable {@link TimePeriod}, created from the given start and end date.
     *
     * @since 1.21
     * @param start the start date of the period
     * @param end the end date of the period
     * @return a new TimePeriod that spans the time between start and end
     */
    @Beta
    public static TimePeriod timePeriod(final Date start, final Date end) {
        return new ImmutableDateTimePeriod(start, end);
    }

}
