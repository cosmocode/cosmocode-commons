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

import com.google.common.collect.Lists;
import de.cosmocode.commons.validation.Rule;
import de.cosmocode.commons.validation.Rules;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Tests the static classes in the {@link TimePeriods} helper class.
 *
 * @since 1.21
 * @author Oliver Lorenz
 */
public final class TimePeriodsTest {

    private static final Logger LOG = LoggerFactory.getLogger(TimePeriodsTest.class);

    private Date yesterday;
    private Date today;
    private Date tomorrow;
    private List<TimePeriod> periods;

    @Before
    public void setUpTestDates() {
        final Calendar calendar = Calendar.getInstance();
        Calendars.toBeginningOfTheDay(calendar);
        calendar.add(Calendar.DAY_OF_MONTH, -1);
        yesterday = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        today = calendar.getTime();
        calendar.add(Calendar.DAY_OF_MONTH, 1);
        tomorrow = calendar.getTime();

        final int dayOfMonth = Calendar.DAY_OF_MONTH;
        this.periods = Lists.newArrayList();
        periods.add(Dates.timePeriod(Dates.nowPlus(dayOfMonth, -20), Dates.nowPlus(dayOfMonth, -7)));
        periods.add(Dates.timePeriod(Dates.nowPlus(dayOfMonth, -9),  new Date(yesterday.getTime())));
        periods.add(Dates.timePeriod(new Date(yesterday.getTime()),  Dates.nowPlus(dayOfMonth,  5)));
        periods.add(Dates.timePeriod(Dates.nowPlus(dayOfMonth,  2),  Dates.nowPlus(dayOfMonth,  9)));
        periods.add(Dates.timePeriod(Dates.nowPlus(dayOfMonth,  8),  Dates.nowPlus(dayOfMonth, 18)));
    }

    /**
     * Tests {@link TimePeriods#contains(java.util.Date)}.
     */
    @Test
    public void contains() {
        final boolean expected = true;
        final boolean actual = TimePeriods.contains(today).apply(Dates.timePeriod(yesterday, tomorrow));
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TimePeriods#contains(java.util.Date)} with a date before the time period.
     */
    @Test
    public void containsBefore() {
        final boolean expected = false;
        final boolean actual = TimePeriods.contains(yesterday).apply(Dates.timePeriod(today, tomorrow));
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TimePeriods#contains(java.util.Date)} with a date after the time period.
     */
    @Test
    public void containsAfter() {
        final boolean expected = false;
        final boolean actual = TimePeriods.contains(tomorrow).apply(Dates.timePeriod(yesterday, today));
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TimePeriods#contains(java.util.Date)} with a date at exactly the start of the time period.
     */
    @Test
    public void containsAtStart() {
        final Date yesterdayToo = new Date(yesterday.getTime());

        final boolean expected = false;
        final boolean actual = TimePeriods.contains(yesterdayToo).apply(Dates.timePeriod(yesterday, tomorrow));
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TimePeriods#contains(java.util.Date)} with a date at exactly the end of the time period.
     */
    @Test
    public void containsAtEnd() {
        final Date tomorrowToo = new Date(tomorrow.getTime());

        final boolean expected = false;
        final boolean actual = TimePeriods.contains(tomorrowToo).apply(Dates.timePeriod(yesterday, tomorrow));
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TimePeriods#contains(java.util.Date)} with a null input.
     */
    @Test
    public void containsNull() {
        final boolean expected = false;
        final boolean actual = TimePeriods.containsInclusive(today).apply(null);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TimePeriods#containsInclusive(java.util.Date)}.
     */
    @Test
    public void containsInclusive() {
        final boolean expected = true;
        final boolean actual = TimePeriods.containsInclusive(today).apply(Dates.timePeriod(yesterday, tomorrow));
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TimePeriods#containsInclusive(java.util.Date)} with a date before the time period.
     */
    @Test
    public void containsInclusiveBefore() {
        final boolean expected = false;
        final boolean actual = TimePeriods.containsInclusive(yesterday).apply(Dates.timePeriod(today, tomorrow));
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TimePeriods#containsInclusive(java.util.Date)} with a date after the time period.
     */
    @Test
    public void containsInclusiveAfter() {
        final boolean expected = false;
        final boolean actual = TimePeriods.containsInclusive(tomorrow).apply(Dates.timePeriod(yesterday, today));
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TimePeriods#containsInclusive(java.util.Date)} with a date at exactly the start of the time period.
     */
    @Test
    public void containsInclusiveAtStart() {
        final Date yesterdayToo = new Date(yesterday.getTime());

        final boolean expected = true;
        final boolean actual = TimePeriods.containsInclusive(yesterdayToo).apply(Dates.timePeriod(yesterday, tomorrow));
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TimePeriods#containsInclusive(java.util.Date)} with a date at exactly the end of the time period.
     */
    @Test
    public void containsInclusiveAtEnd() {
        final Date tomorrowToo = new Date(tomorrow.getTime());

        final boolean expected = true;
        final boolean actual = TimePeriods.containsInclusive(tomorrowToo).apply(Dates.timePeriod(yesterday, tomorrow));
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TimePeriods#containsInclusive(java.util.Date)} with a null input.
     */
    @Test
    public void containsInclusiveNull() {
        final boolean expected = false;
        final boolean actual = TimePeriods.containsInclusive(today).apply(null);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TimePeriods#containsInclusive(java.util.Date)} applied with a sql timestamp.
     * Although Timestamps should not be seen as instances of Dates they are often used this way.
     * The containsInclusive method should not break then.
     */
    @Test
    public void containsInclusiveAppliedWithTimestamp() {
        final Timestamp yesterdayTimestamp = new Timestamp(yesterday.getTime());
        final TimePeriod period = Dates.timePeriod(yesterday, today);

        final boolean expected = true;
        final boolean actual = TimePeriods.containsInclusive(yesterdayTimestamp).apply(period);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests the toString() of the long, combined list rule.
     */
    @Test
    public void containsInclusiveListToString() {
        final Rule<Iterable<TimePeriod>> containsYesterday = Rules.any(TimePeriods.containsInclusive(yesterday));
        LOG.debug("toString() of Rules.any(TimePeriods.containsInclusive(yesterday)): {}", containsYesterday);
    }

    /**
     * Tests a combined rule that is true if any TimePeriod in the list contains a given date.
     */
    @Test
    public void containsInclusiveList() {
        final boolean expected = true;
        final boolean actual = Rules.any(TimePeriods.containsInclusive(yesterday)).apply(periods);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests a combined rule that is true if any TimePeriod in the list contains a given date.
     */
    @Test
    public void containsInclusiveList2() {
        final Date threeDaysAgo = Dates.nowPlus(Calendar.DAY_OF_MONTH, -3);

        final boolean expected = true;
        final boolean actual = Rules.any(TimePeriods.containsInclusive(threeDaysAgo)).apply(periods);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests a combined rule that is true if any TimePeriod in the list contains a given date,
     * with a far future date.
     */
    @Test
    public void containsInclusiveListFarFuture() {
        final boolean expected = false;
        final boolean actual = Rules.any(TimePeriods.containsInclusive(Dates.nowPlus(Calendar.YEAR, 1))).apply(periods);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TimePeriods#overlaps(TimePeriod, OverlapMode)}.
     */
    @Test
    public void overlaps() {
        final TimePeriod first = Dates.timePeriod(yesterday, today);
        final TimePeriod second = Dates.timePeriod(today, tomorrow);

        final boolean expected = true;
        final boolean actual = TimePeriods.overlaps(first, OverlapMode.NORMAL).apply(second);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TimePeriods#overlaps(TimePeriod, OverlapMode)} with null.
     */
    @Test
    public void overlapsNull() {
        final TimePeriod first = Dates.timePeriod(yesterday, today);

        final boolean expected = false;
        final boolean actual = TimePeriods.overlaps(first, OverlapMode.NORMAL).apply(null);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TimePeriods#overlaps(TimePeriod, OverlapMode)} with a list (together with Rules.any).
     */
    @Test
    public void overlapsList() {
        final TimePeriod first = Dates.timePeriod(today, tomorrow);

        final boolean expected = true;
        final boolean actual = Rules.any(TimePeriods.overlaps(first, OverlapMode.NORMAL)).apply(periods);
        Assert.assertEquals(expected, actual);
        LOG.debug("toString() of overlap on list: {}", Rules.any(TimePeriods.overlaps(first, OverlapMode.NORMAL)));
    }

    /**
     * Tests {@link TimePeriods#overlaps(TimePeriod, OverlapMode)} with a list (together with Rules.any), null as input.
     */
    @Test
    public void overlapsListNull() {
        final TimePeriod first = Dates.timePeriod(today, tomorrow);

        final boolean expected = false;
        final boolean actual = Rules.any(TimePeriods.overlaps(first, OverlapMode.NORMAL)).apply(null);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TimePeriods#overlaps(TimePeriod, OverlapMode)} with a list (together with Rules.any).
     */
    @Test
    public void overlapsNotList() {
        final TimePeriod first = Dates.timePeriod(
            Dates.nowPlus(Calendar.DAY_OF_MONTH, -30), Dates.nowPlus(Calendar.DAY_OF_MONTH, -25));

        final boolean expected = false;
        final boolean actual = Rules.any(TimePeriods.overlaps(first, OverlapMode.NORMAL)).apply(periods);
        Assert.assertEquals(expected, actual);
    }

}
