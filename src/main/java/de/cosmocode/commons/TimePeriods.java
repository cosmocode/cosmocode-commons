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

import com.google.common.annotations.Beta;
import com.google.common.base.Function;

import de.cosmocode.commons.base.MoreObjects;
import de.cosmocode.commons.validation.Rule;

import javax.annotation.Nullable;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Helper class for {@link TimePeriod}s.
 *
 * @since 1.21
 * @author Oliver Lorenz
 */
@Beta
public final class TimePeriods {

    public static final Function<TimePeriod, Date> START_DATE = new AbstractFunction<TimePeriod, Date>() {

        @Override
        public Date apply(@Nullable TimePeriod input) {
            return input == null ? null : new Date(input.getStart(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "TimePeriods.START_DATE";
        }

    };

    public static final Function<TimePeriod, Date> END_DATE = new AbstractFunction<TimePeriod, Date>() {

        @Override
        public Date apply(@Nullable TimePeriod input) {
            return input == null ? null : new Date(input.getEnd(TimeUnit.MILLISECONDS));
        }

        @Override
        public String toString() {
            return "TimePeriods.END_DATE";
        }

    };

    /**
     * Private to prevent instantiation.
     */
    private TimePeriods() {

    }

    /**
     * <p>
     * Creates a {@link Rule} which evaluates to true
     * when passed in a TimePeriod which contains the given date.
     * This means that the start date must be before the given date
     * and the end date after the given date.
     * </p>
     * <p>
     * If the start of a TimePeriod is equal to the given Date,
     * then this TimePeriod is not considered as containing the date.
     * The same applies to the end.
     * </p>
     *
     * @since 1.21
     * @see Dates#before(java.util.Date)
     * @see Dates#after(java.util.Date)
     * @param when the date that should be inside any checked time period
     * @return a rule that returns true for all TimePeriods that contain the given Date
     */
    public static Rule<TimePeriod> contains(final Date when) {
        return Dates.before(when).compose(START_DATE).and(Dates.after(when).compose(END_DATE));
    }

    /**
     * <p>
     * Creates a {@link Rule} which evaluates to true
     * when passed in a TimePeriod which contains the given date, including start and end.
     * This means that the start date must be before or equal to the given date
     * and the end date must be after or equal to the given date.
     * </p>
     *
     * @since 1.21
     * @see TimePeriods#contains(java.util.Date)
     * @see Dates#before(java.util.Date)
     * @see Dates#after(java.util.Date)
     * @see Dates#equalTo(java.util.Date)
     * @param when the date that should be inside any checked time period
     * @return a rule that returns true for all TimePeriods that contain the given Date (including start and end)
     */
    public static Rule<TimePeriod> containsInclusive(final Date when) {
        return MoreObjects.asymmetricEqualTo(when).compose(START_DATE).
            or(MoreObjects.asymmetricEqualTo(when).compose(END_DATE)).
            or(contains(when));
    }

    /**
     * <p>
     * Creates a {@link Rule} which evaluates to true
     * if the passed in TimePeriod overlaps with the given other TimePeriod.
     * </p>
     *
     * @since 1.21
     * @see OverlapMode#isOverlapping(TimePeriod, TimePeriod)
     * @param otherTimePeriod the other TimePeriod to check against for overlap
     * @param overlapMode the OverlapMode to use for overlap checking
     * @return a rule that returns true for all TimePeriods that overlap with the given TimePeriod
     *         in the given OverlapMode
     */
    public static Rule<TimePeriod> overlaps(final TimePeriod otherTimePeriod, final OverlapMode overlapMode) {
        return new TimePeriodsOverlapsRule(otherTimePeriod, overlapMode);
    }

}
