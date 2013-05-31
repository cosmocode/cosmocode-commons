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

import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Interface for a time period.
 * This is not in any respect final. Neither the methods nor this interface.
 * Maybe this interface will vanish altogether later.
 */
@Beta
public interface TimePeriod {

    /**
     * Returns the reference point in time, from which start and end are counted.
     * It is given as a Date to allow for high precision based on another defined scale.
     * Date based implementations would return "new Date(0)".
     *
     * @return the reference point in time from which start and end are counted from
     */
    Date getReference();

    /**
     * Returns the start of the time period.
     * It is measured in {@link #getPrecision()}.
     *
     * @return the start of the time period, measured in {@link #getPrecision()}
     */
    long getStart();

    /**
     * Returns the end of the time period.
     * It is measured in {@link #getPrecision()}.
     *
     * @return the end of the time period, measured in {@link #getPrecision()}
     */
    long getEnd();

    /**
     * <p>
     * Returns the precision of this TimePeriod.
     * The precision defines the TimeUnit of {@link #getStart()} and {@link #getEnd()}.
     * </p>
     * <p>
     * For example if a TimePeriod stores its start and end in days (like a calendar),
     * then the precision is TimeUnit.DAYS.
     * Another example: A TimePeriod using {@link java.util.Date}s has a precision of TimeUnit.MILLISECOND.
     * </p>
     * <p>
     * Because TimePeriod only goes up to DAYS, any implementation can only have this as max precision.
     * This should be no problem, because this can cover all measurable time periods
     * (even the age of the universe).
     * </p>
     *
     * @return a TimeUnit that indicates the precision of {@link #getStart()} and {@link #getEnd()}
     */
    TimeUnit getPrecision();

}
