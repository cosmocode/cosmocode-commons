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

import de.cosmocode.commons.concurrent.TimeUnits;
import de.cosmocode.junit.UnitProvider;
import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Calendar;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Tests {@link de.cosmocode.commons.OverlapMode}.
 *
 * @author Oliver Lorenz
 */
public abstract class OverlapModeTestCase implements UnitProvider<OverlapMode> {

    private static final Logger LOG = LoggerFactory.getLogger(OverlapModeTestCase.class);

    private Date lastWeek() {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.WEEK_OF_MONTH, -1);
        Calendars.toBeginningOfTheDay(calendar);
        return calendar.getTime();
    }

    private Date yesterday() {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        Calendars.toBeginningOfTheDay(calendar);
        return calendar.getTime();
    }

    private Date today() {
        final Calendar calendar = Calendar.getInstance();
        Calendars.toBeginningOfTheDay(calendar);
        return calendar.getTime();
    }

    private Date tomorrow() {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, +1);
        Calendars.toBeginningOfTheDay(calendar);
        return calendar.getTime();
    }

    protected abstract boolean isOverlappingOnBorders();

    protected abstract boolean isOverlappingOnContaining();

    protected abstract boolean isOverlappingOnIntersection();

    protected abstract boolean isOverlappingOnNoIntersection();

    protected abstract boolean isOverlappingOnSameLength();

    protected abstract boolean canHandleFlippedPeriods();

    private void assertIsOverlappingAsExpected(boolean expected, long s1, long e1, long s2, long e2) {
        Assert.assertEquals(expected, unit().isOverlapping(s1, e1, s2, e2));

        if (this.canHandleFlippedPeriods()) {
            Assert.assertEquals("Flipped second period", expected, unit().isOverlapping(s1, e1, e2, s2));
            Assert.assertEquals("Flipped first period", expected, unit().isOverlapping(e1, s1, s2, e2));
            Assert.assertEquals("Flipped both periods", expected, unit().isOverlapping(e1, s1, e2, s2));
        }
    }

    private void assertIsOverlappingAsExpected(boolean expected, Date s1, Date e1, Date s2, Date e2) {
        assertIsOverlappingAsExpected(expected, s1.getTime(), e1.getTime(), s2.getTime(), e2.getTime());
    }

    /**
     * Tests overlapping when period1 lies left of period2.
     * As a diagram:
     * Period1:   |-------|
     * Period2:              |--------|
     */
    @Test
    public void period1BeforePeriod2() {
        final boolean expected = isOverlappingOnNoIntersection();
        assertIsOverlappingAsExpected(expected, lastWeek(), yesterday(), today(), tomorrow());
    }

    /**
     * Tests overlapping when period1 is shortly before period2.
     * As a diagram:
     * Period1:   |-------|
     * Period2:            |--------|
     */
    @Test
    public void period1ShortlyBeforePeriod2() {
        final boolean expected = isOverlappingOnNoIntersection();
        final long s1 = lastWeek().getTime();
        final long e1 = yesterday().getTime() - 1;
        final long s2 = yesterday().getTime();
        final long e2 = tomorrow().getTime();
        assertIsOverlappingAsExpected(expected, s1, e1, s2, e2);
    }

    /**
     * Tests overlapping when period1 lies left of period2,
     * but the end of period1 is the same as start of period2.
     * As a diagram:
     * Period1:   |-------|
     * Period2:           |--------|
     */
    @Test
    public void period1BordersAtPeriod2() {
        final boolean expected = isOverlappingOnBorders();
        assertIsOverlappingAsExpected(expected, lastWeek(), yesterday(), yesterday(), tomorrow());
    }

    /**
     * Tests overlapping when period1 intersects period2, with start of period1 being before period2.
     * As a diagram:
     * Period1:    |--------|
     * Period2:        |--------|
     */
    @Test
    public void period1IntersectsPeriod2() {
        final boolean expected = isOverlappingOnIntersection();
        assertIsOverlappingAsExpected(expected, lastWeek(), today(), yesterday(), tomorrow());
    }

    /**
     * Tests overlapping when both periods have the same length
     * As a diagram:
     * Period1:    |--------|
     * Period2:    |--------|
     */
    @Test
    public void periodsAreSameLength() {
        final boolean expected = isOverlappingOnSameLength();
        assertIsOverlappingAsExpected(expected, lastWeek(), today(), lastWeek(), today());
    }

    /**
     * Tests overlapping when period1 intersects period2, with start of period2 being before period1.
     * As a diagram:
     * Period1:        |--------|
     * Period2:    |--------|
     */
    @Test
    public void period2IntersectsPeriod1() {
        final boolean expected = isOverlappingOnIntersection();
        assertIsOverlappingAsExpected(expected, yesterday(), tomorrow(), lastWeek(), today());
    }

    /**
     * Tests overlapping when period1 lies right of period2,
     * but the end of period2 is the same as start of period1.
     * As a diagram:
     * Period1:           |-------|
     * Period2:  |--------|
     */
    @Test
    public void period2BordersAtPeriod1() {
        final boolean expected = isOverlappingOnBorders();
        assertIsOverlappingAsExpected(expected, yesterday(), tomorrow(), lastWeek(), yesterday());
    }

    /**
     * Tests overlapping when period1 is shortly after period2.
     * As a diagram:
     * Period1:            |--------|
     * Period2:   |-------|
     */
    @Test
    public void period1ShortlyAfterPeriod2() {
        final boolean expected = isOverlappingOnNoIntersection();
        final Date s2 = lastWeek();
        final Date e2 = new Date(yesterday().getTime() - 1);
        final Date s1 = yesterday();
        final Date e1 = tomorrow();
        assertIsOverlappingAsExpected(expected, s1, e1, s2, e2);
    }

    /**
     * Tests overlapping when period1 lies right of period2.
     * As a diagram:
     * Period1:              |--------|
     * Period2:   |-------|
     */
    @Test
    public void period1AfterPeriod2() {
        final boolean expected = isOverlappingOnNoIntersection();
        assertIsOverlappingAsExpected(expected, today(), tomorrow(), lastWeek(), yesterday());
    }

    /**
     * Tests overlapping when period2 starts with period1, but period1 ends before period2.
     * As a diagram:
     * Period1:    |--|
     * Period2:    |--------|
     */
    @Test
    public void periodsStartSamePeriod1EndsBeforePeriod2() {
        final boolean expected = isOverlappingOnContaining();
        assertIsOverlappingAsExpected(expected, lastWeek(), today(), lastWeek(), tomorrow());
    }

    /**
     * Tests overlapping when period1 completely lies in period2.
     * As a diagram:
     * Period1:       |--|
     * Period2:    |--------|
     */
    @Test
    public void period1InPeriod2() {
        final boolean expected = isOverlappingOnContaining();
        assertIsOverlappingAsExpected(expected, yesterday(), today(), lastWeek(), tomorrow());
    }

    /**
     * Tests overlapping when period2 starts after period1, but both periods end at the same point.
     * As a diagram:
     * Period1:          |--|
     * Period2:    |--------|
     */
    @Test
    public void periodsPeriod1StartsAfterPeriod2EndSame() {
        final boolean expected = isOverlappingOnContaining();
        assertIsOverlappingAsExpected(expected, yesterday(), today(), lastWeek(), today());
    }

    /**
     * Tests overlapping when period2 starts with period1, but period1 ends after period2.
     * As a diagram:
     * Period1:    |--------|
     * Period2:    |--|
     */
    @Test
    public void periodsStartSamePeriod1EndsAfterPeriod2() {
        final boolean expected = isOverlappingOnContaining();
        assertIsOverlappingAsExpected(expected, lastWeek(), tomorrow(), lastWeek(), today());
    }

    /**
     * Tests overlapping when period2 completely lies in period1.
     * As a diagram:
     * Period1:    |--------|
     * Period2:       |--|
     */
    @Test
    public void period2InPeriod1() {
        final boolean expected = isOverlappingOnContaining();
        assertIsOverlappingAsExpected(expected, lastWeek(), tomorrow(), yesterday(), today());
    }

    /**
     * Tests overlapping when period2 starts before period1, but both periods end at the same point.
     * As a diagram:
     * Period1:    |--------|
     * Period2:          |--|
     */
    @Test
    public void periodsPeriod1StartsBeforePeriod2EndSame() {
        final boolean expected = isOverlappingOnContaining();
        assertIsOverlappingAsExpected(expected, lastWeek(), today(), yesterday(), today());
    }

    /**
     * Tests overlapping when first period is empty and period2 is after period1.
     * As a diagram:
     * Period1:    |
     * Period2:        |-----|
     */
    @Test
    public void period1EmptyAndPeriod2AfterPeriod1() {
        final boolean expected = isOverlappingOnNoIntersection();
        assertIsOverlappingAsExpected(expected, yesterday(), yesterday(), today(), tomorrow());
    }

    /**
     * Tests overlapping when the start of period1 lies left of period2,
     * but the start and end of period1 is the same as the start period2.
     * This means that Period1 is empty (== is a point in time instead of a period).
     * As a diagram:
     * Period1:  |
     * Period2:  |--------|
     */
    @Test
    public void period1EmptyBordersAtPeriod2() {
        final boolean expected = isOverlappingOnBorders();
        assertIsOverlappingAsExpected(expected, lastWeek(), lastWeek(), lastWeek(), yesterday());
    }

    /**
     * Tests overlapping when period1 completely lies in period2 and period1 is empty.
     * As a diagram:
     * Period1:        |
     * Period2:    |--------|
     */
    @Test
    public void period1EmptyInPeriod2() {
        final boolean expected = isOverlappingOnContaining();
        assertIsOverlappingAsExpected(expected, today(), today(), yesterday(), tomorrow());
    }

    /**
     * Tests overlapping when period1 lies right of period2,
     * but the start and end of period1 is the same as end of period2.
     * This means that Period1 is empty (== is a point in time instead of a period).
     * As a diagram:
     * Period1:           |
     * Period2:   |-------|
     */
    @Test
    public void period2BordersAtPeriod1Empty() {
        final boolean expected = isOverlappingOnBorders();
        assertIsOverlappingAsExpected(expected, tomorrow(), tomorrow(), yesterday(), tomorrow());
    }

    /**
     * Tests overlapping when first period is empty and period1 is after period2.
     * As a diagram:
     * Period1:              |
     * Period2:    |-----|
     */
    @Test
    public void period1EmptyAndPeriod1AfterPeriod2() {
        final boolean expected = isOverlappingOnNoIntersection();
        assertIsOverlappingAsExpected(expected, today(), today(), lastWeek(), yesterday());
    }

    /**
     * Tests overlapping when period1 lies right of period2,
     * but period2 is an empty period, thereby denoting a point in time.
     * As a diagram:
     * Period1:        |-------|
     * Period2:   |
     */
    @Test
    public void period2EmptyBeforePeriod1() {
        final boolean expected = isOverlappingOnNoIntersection();
        assertIsOverlappingAsExpected(expected, today(), tomorrow(), yesterday(), yesterday());
    }

    /**
     * Tests overlapping when period1 lies right of period2,
     * but the start and end of period2 is the same as start of period1.
     * This means that Period2 is empty (== is a point in time instead of a period).
     * As a diagram:
     * Period1:   |-------|
     * Period2:   |
     */
    @Test
    public void period2EmptyBordersAtPeriod1() {
        final boolean expected = isOverlappingOnBorders();
        assertIsOverlappingAsExpected(expected, yesterday(), tomorrow(), yesterday(), yesterday());
    }

    /**
     * Tests overlapping when period2 completely lies in period1 and period2 is empty.
     * As a diagram:
     * Period1:    |--------|
     * Period2:        |
     */
    @Test
    public void period2EmptyInPeriod1() {
        final boolean expected = isOverlappingOnContaining();
        assertIsOverlappingAsExpected(expected, yesterday(), tomorrow(), today(), today());
    }

    /**
     * Tests overlapping when the start of period1 lies left of period2,
     * but the end of period1 is the same as the start and end of period2.
     * This means that Period2 is empty (== is a point in time instead of a period).
     * As a diagram:
     * Period1:  |--------|
     * Period2:           |
     */
    @Test
    public void period1BordersAtPeriod2Empty() {
        final boolean expected = isOverlappingOnBorders();
        assertIsOverlappingAsExpected(expected, lastWeek(), yesterday(), yesterday(), yesterday());
    }

    /**
     * Tests overlapping when period1 lies left of empty period2 (== point in time).
     * As a diagram:
     * Period1:  |--------|
     * Period2:                |
     */
    @Test
    public void period2EmptyAfterPeriod1() {
        final boolean expected = isOverlappingOnNoIntersection();
        assertIsOverlappingAsExpected(expected, lastWeek(), yesterday(), today(), today());
    }

    /**
     * Tests overlapping when both periods are empty (s1 == e1 and s2 == e2), but period1 is before period2.
     * As a diagram:
     * Period1:    |
     * Period2:          |
     */
    @Test
    public void periodsAreEmptyPeriod1BeforePeriod2() {
        final boolean expected = isOverlappingOnNoIntersection();
        assertIsOverlappingAsExpected(expected, today(), today(), tomorrow(), tomorrow());
    }

    /**
     * Tests overlapping when both periods are empty (s1 == e1 and s2 == e2), and on the same point.
     * So that s1 == e1 == s2 == e2.
     * As a diagram:
     * Period1:    |
     * Period2:    |
     */
    @Test
    public void periodsAreEmptySamePosition() {
        final boolean expected = isOverlappingOnSameLength();
        assertIsOverlappingAsExpected(expected, today(), today(), today(), today());
    }

    /**
     * Tests overlapping when both periods are empty (s1 == e1 and s2 == e2), but period1 is before period2.
     * As a diagram:
     * Period1:          |
     * Period2:    |
     */
    @Test
    public void periodsAreEmptyPeriod1AfterPeriod2() {
        final boolean expected = isOverlappingOnNoIntersection();
        assertIsOverlappingAsExpected(expected, tomorrow(), tomorrow(), today(), today());
    }

    /**
     * This is a non-scientific performance test.
     * It just loops several times and invokes the isOverlapping method with some data.
     */
    @Test
    public void performanceTestLongs() {
        final int loopCount = 1000000;
        final long start = System.nanoTime();
        for (int i = 0; i < loopCount; i++) {
            // the values used here are just randomly chosen, any other values would do too
            unit().isOverlapping(i / 100, 100000 / (i + 1), i % 100, i + (i % 20));
        }
        final long elapsed = System.nanoTime() - start;
        final TimeUnit mortalUnit = TimeUnits.forMortals(elapsed, TimeUnit.NANOSECONDS);
        LOG.debug("Needed {} {} for {} iterations (isOverlapping(long, long, long, long))",
            new Object[] {mortalUnit.convert(elapsed, TimeUnit.NANOSECONDS), mortalUnit, loopCount});
    }

    /**
     * This is a non-scientific performance test.
     * It just loops several times and invokes the isOverlapping method with some data.
     */
    @Test
    public void performanceTestDates() {
        final int loopCount = 1000000;
        final long start = System.nanoTime();
        for (int i = 0; i < loopCount; i++) {
            // the values used here are just randomly chosen, any other values would do too
            unit().isOverlapping(new Date(i / 10), new Date(100000 / (i + 1)), new Date(i % 100), new Date(i + (i % 20)));
        }
        final long elapsed = System.nanoTime() - start;
        final TimeUnit mortalUnit = TimeUnits.forMortals(elapsed, TimeUnit.NANOSECONDS);
        LOG.debug("Needed {} {} for {} iterations (isOverlapping(Date, Date, Date, Date))",
            new Object[] {mortalUnit.convert(elapsed, TimeUnit.NANOSECONDS), mortalUnit, loopCount});
    }

}
