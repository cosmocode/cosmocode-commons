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

import java.util.concurrent.TimeUnit;

/**
 * Interface for a time period.
 * This is not in any respect final. Neither the methods nor this interface.
 * Maybe this interface will vanish altogether later.
 */
@Beta
public interface TimePeriod {

    /**
     * Returns the start of the time period.
     *
     * @param unit the unit in which the start time is returned
     * @return the start of the time period, measured in the given TimeUnit
     * @throws NullPointerException if unit is null
     */
    long getStart(TimeUnit unit);

    /**
     * Returns the end of the time period.
     *
     * @param unit the unit in which the end time is returned
     * @return the end of the time period, measured in the given TimeUnit
     * @throws NullPointerException if unit is null
     */
    long getEnd(TimeUnit unit);

    /**
     * Returns the precision of this TimePeriod.
     * The precision defines the minimum TimeUnit in which the difference between start and end can be 1.
     * For example if a TimePeriod stores its start and end in days (like a calendar),
     * then the minimum precision is TimeUnit.DAYS.
     * Another example: A TimePeriod using {@link java.util.Date}s has a precision of TimeUnit.MILLISECOND.
     *
     * @return a TimeUnit that indicates the minimum precision between start and end
     */
    TimeUnit getPrecision();

}
