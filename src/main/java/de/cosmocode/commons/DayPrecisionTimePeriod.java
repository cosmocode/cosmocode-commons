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
import java.util.concurrent.TimeUnit;

import com.google.common.annotations.Beta;

/**
 * {@link TimePeriod} that measures the start and end in days from year 0, January 1st.
 *
 * @since 1.21
 * @author Oliver Lorenz
 */
@Beta
final class DayPrecisionTimePeriod implements TimePeriod {

    private final long start;
    private final long end;

    DayPrecisionTimePeriod(long start, long end) {
        this.start = start;
        this.end = end;
    }

    DayPrecisionTimePeriod(Date start, Date end) {
        this.start = TimeUnit.DAYS.convert(start.getTime() - getReference().getTime(), TimeUnit.MILLISECONDS);
        this.end = TimeUnit.DAYS.convert(end.getTime() - getReference().getTime(), TimeUnit.MILLISECONDS);
    }

    @Override
    public Date getReference() {
        final Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 0);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        return calendar.getTime();
    }

    @Override
    public long getStart(TimeUnit unit) {
        return unit.convert(start, TimeUnit.DAYS);
    }

    @Override
    public long getEnd(TimeUnit unit) {
        return unit.convert(end, TimeUnit.DAYS);
    }

    @Override
    public TimeUnit getPrecision() {
        return TimeUnit.DAYS;
    }

}
