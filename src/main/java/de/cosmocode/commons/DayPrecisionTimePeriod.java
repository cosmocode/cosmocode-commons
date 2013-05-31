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

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * {@link TimePeriod} that measures the start and end in days from year 0, January 1st.
 *
 * @since 1.21
 * @author Oliver Lorenz
 */
@Beta
final class DayPrecisionTimePeriod implements TimePeriod {

    private final Date reference;
    private final long start;
    private final long end;

    DayPrecisionTimePeriod(long start, long end) {
        this.reference = new ImmutableDate(getYearZeroTimestamp());
        this.start = start;
        this.end = end;
    }

    DayPrecisionTimePeriod(Date start, Date end) {
        final long referenceTime = getYearZeroTimestamp();
        this.reference = new ImmutableDate(referenceTime);
        this.start = TimeUnit.DAYS.convert(start.getTime() - referenceTime, TimeUnit.MILLISECONDS);
        this.end = TimeUnit.DAYS.convert(end.getTime() - referenceTime, TimeUnit.MILLISECONDS);
    }

    private long getYearZeroTimestamp() {
        final Calendar calendar = Calendar.getInstance();
        Calendars.toBeginningOfTheDay(calendar);
        calendar.set(Calendar.YEAR, 0);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime().getTime();
    }

    @Override
    public Date getReference() {
        return reference;
    }

    @Override
    public long getStart() {
        return start;
    }

    @Override
    public long getEnd() {
        return end;
    }

    @Override
    public TimeUnit getPrecision() {
        return TimeUnit.DAYS;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        } else if (o instanceof DayPrecisionTimePeriod) {
            final DayPrecisionTimePeriod that = DayPrecisionTimePeriod.class.cast(o);
            return start == that.start && end == that.end;
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + (int) (end ^ (end >>> 32));
        result = prime * result + (int) (start ^ (start >>> 32));
        return result;
    }

    @Override
    public String toString() {
        return "DayPrecisionTimePeriod [start=" + start + ", end=" + end + "]";
    }

}
