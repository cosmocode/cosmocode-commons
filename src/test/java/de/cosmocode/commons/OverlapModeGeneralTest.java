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

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Tests the public functions of overlap mode that are independent of the specific mode.
 *
 * @since 1.21
 * @author Oliver Lorenz
 */
public final class OverlapModeGeneralTest {

    private final OverlapMode mode = OverlapMode.NORMAL;

    private Date yearFifty;
    private Date yesterday;
    private Date today;
    private Date tomorrow;
    private Date nextMonth;

    /**
     * Runs before each test.
     */
    @Before
    public void setupPeriods() {
        final Calendar calendar = Calendar.getInstance();
        Calendars.toBeginningOfTheDay(calendar);

        calendar.add(Calendar.DAY_OF_MONTH, -1);
        yesterday = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        tomorrow = calendar.getTime();
        calendar.add(Calendar.MONTH, 1);
        nextMonth = calendar.getTime();

        // special
        calendar.set(Calendar.YEAR, 50);
        calendar.set(Calendar.MONTH, 0);
        calendar.set(Calendar.DAY_OF_MONTH, 1);
        yearFifty = calendar.getTime();
    }

    /**
     * Tests {@link OverlapMode#isOverlapping(TimePeriod, TimePeriod)}.
     */
    @Test
    public void timePeriodsOverlap() {
        final TimePeriod a = Dates.timePeriod(today, tomorrow);
        final TimePeriod b = Dates.timePeriod(today, nextMonth);

        final boolean expected = true;
        final boolean actual = mode.isOverlapping(a, b);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link OverlapMode#isOverlapping(TimePeriod, TimePeriod)}.
     */
    @Test
    public void timePeriodsOverlapImmutableDateAndDayPrecision() {
        final TimePeriod a = Dates.timePeriod(yesterday, tomorrow);
        final TimePeriod b = new DayPrecisionTimePeriod(today, nextMonth);

        final boolean expected = true;
        final boolean actual = mode.isOverlapping(a, b);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link OverlapMode#isOverlapping(TimePeriod, TimePeriod)}.
     */
    @Test
    public void timePeriodsNotOverlappingImmutableDateAndDayPrecision() {
        final TimePeriod a = Dates.timePeriod(yesterday, today);
        final TimePeriod b = new DayPrecisionTimePeriod(tomorrow, nextMonth);

        final boolean expected = false;
        final boolean actual = mode.isOverlapping(a, b);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link OverlapMode#isOverlapping(TimePeriod, TimePeriod)}.
     */
    @Test
    public void timePeriodsOverlapDayPrecisionAndImmutableDate() {
        final TimePeriod a = new DayPrecisionTimePeriod(today, nextMonth);
        final TimePeriod b = Dates.timePeriod(yesterday, tomorrow);

        final boolean expected = true;
        final boolean actual = mode.isOverlapping(a, b);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link OverlapMode#isOverlapping(TimePeriod, TimePeriod)}.
     */
    @Test
    public void timePeriodsOverlapYearFive() {
        // first period: year 2 to year 200
        final TimePeriod a = new DayPrecisionTimePeriod(2L * 365L, 200L * 365L);
        final TimePeriod b = Dates.timePeriod(yearFifty, today);

        final boolean expected = true;
        final boolean actual = mode.isOverlapping(a, b);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link OverlapMode#isOverlapping(TimePeriod, TimePeriod)}.
     */
    @Test
    public void timePeriodsOverlapYearFiveReversed() {
        // first period: year 2 to year 200
        final TimePeriod a = Dates.timePeriod(yearFifty, today);
        final TimePeriod b = new DayPrecisionTimePeriod(2L * 365L, 200L * 365L);

        final boolean expected = true;
        final boolean actual = mode.isOverlapping(a, b);
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link OverlapMode#isOverlapping(TimePeriod, TimePeriod)},
     * using two TimePeriods that have a huge distance from one another.
     */
    @Test
    public void timePeriodsDontOverlapHugeDistance() {
        // 1.000.000 BC to 200.000 BC
        final TimePeriod a = new DayPrecisionTimePeriod(-1000000L * 365L, -200000L * 365L);
        final TimePeriod b = Dates.timePeriod(yearFifty, nextMonth);
        
        final boolean expected = false;
        final boolean actual = mode.isOverlapping(a, b);
        Assert.assertEquals(expected, actual);
    }

}
