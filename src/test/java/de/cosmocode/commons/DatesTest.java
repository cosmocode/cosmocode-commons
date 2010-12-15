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

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

/**
 * Tests the static methods from {@link Dates}.
 *
 * @author Oliver Lorenz
 */
public class DatesTest {

    private Date yesterday;
    private Date today;
    private Date tomorrow;

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
    }

    /**
     * Simple test for {@link Dates#nowPlus(int, int)}.
     */
    @Test
    public void nowPlus() {
        final Date now = new Date();
        final Date future = Dates.nowPlus(Calendar.DAY_OF_MONTH, 1);
        Assert.assertEquals(true, now.before(future));
    }

    /**
     * Tests {@link Dates#equalTo(Date)}.
     */
    @Test
    public void equalTo() {
        final Date now = new Date();
        final Date other = new Date(now.getTime());

        final boolean expected = true;
        final boolean actual = Dates.equalTo(now).apply(other);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Dates#equalTo(Date)} with a timestamp as input, applied to a Timestamp.
     */
    @Test
    public void equalToTimestamps() {
        final Timestamp now = new Timestamp(System.currentTimeMillis());
        final Timestamp other = new Timestamp(now.getTime());

        final boolean expected = true;
        final boolean actual = Dates.equalTo(now).apply(other);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Dates#equalTo(Date)} with a timestamp as input, applied to a Date.
     */
    @Test
    public void equalToTimestampAppliedToDate() {
        final Timestamp now = new Timestamp(System.currentTimeMillis());
        final Date other = new Date(now.getTime());

        final boolean expected = true;
        final boolean actual = Dates.equalTo(now).apply(other);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Dates#equalTo(Date)} with a Date as input, applied to a Timestamp.
     */
    @Test
    public void equalToDateAppliedToTimestamp() {
        final Date now = new Date();
        final Timestamp other = new Timestamp(now.getTime());

        final boolean expected = true;
        final boolean actual = Dates.equalTo(now).apply(other);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Dates#equalTo(Date)} with a Date, applied to another Date that is not equal.
     */
    @Test
    public void equalToAppliedToNotEqual() {
        final Date now = new Date();
        final Date other = new Date(now.getTime() + 1);

        final boolean expected = false;
        final boolean actual = Dates.equalTo(now).apply(other);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Dates#equalTo(Date)} with a normal input and applied to null.
     */
    @Test
    public void equalToAppliedToNull() {
        final boolean expected = false;
        final boolean actual = Dates.equalTo(new Date()).apply(null);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Dates#equalTo(Date)} with a null input and applied to a normal value.
     */
    @Test
    public void equalToNullAppliedToNow() {
        final boolean expected = false;
        final boolean actual = Dates.equalTo(null).apply(new Date());
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Dates#equalTo(Date)} with a null input and applied to null.
     */
    @Test
    public void equalToNullAppliedNull() {
        final boolean expected = true;
        final boolean actual = Dates.equalTo(null).apply(null);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Dates#before(Date)} with a date before today.
     */
    @Test
    public void before() {
        final boolean expected = true;
        final boolean actual = Dates.before(today).apply(yesterday);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Dates#before(Date)} with only a millisecond difference before now.
     */
    @Test
    public void beforeOneMilliSecondAgo() {
        final Date now = new Date();
        final Date oneMilliSecondAgo = new Date(now.getTime() - 1);
        
        final boolean expected = true;
        final boolean actual = Dates.before(now).apply(oneMilliSecondAgo);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Dates#before(Date)} with two times the same time.
     */
    @Test
    public void beforeSameTime() {
        final Date now = new Date();
        final Date nowToo = new Date(now.getTime());
        
        final boolean expected = false;
        final boolean actual = Dates.before(now).apply(nowToo);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Dates#before(Date)} with only a millisecond difference after now.
     */
    @Test
    public void beforeOneMilliSecondAfter() {
        final Date now = new Date();
        final Date oneMilliSecondAfter = new Date(now.getTime() + 1);
        
        final boolean expected = false;
        final boolean actual = Dates.before(now).apply(oneMilliSecondAfter);
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Dates#before(Date)} with a date after today.
     */
    @Test
    public void beforeWithDateAfter() {
        final boolean expected = false;
        final boolean actual = Dates.before(today).apply(tomorrow);
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Dates#before(Date)} with a null input.
     */
    @Test(expected = NullPointerException.class)
    public void beforeNull() {
        Dates.before(null);
    }
    


    /**
     * Tests {@link Dates#after(Date)} with a date before today.
     */
    @Test
    public void afterWithDateBefore() {
        final boolean expected = false;
        final boolean actual = Dates.after(today).apply(yesterday);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Dates#after(Date)} with only a millisecond difference.
     */
    @Test
    public void afterOneMilliSecondAgo() {
        final Date now = new Date();
        final Date oneMilliSecondAgo = new Date(now.getTime() - 1);
        
        final boolean expected = false;
        final boolean actual = Dates.after(now).apply(oneMilliSecondAgo);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Dates#after(Date)} with two times the same time.
     */
    @Test
    public void afterSameTime() {
        final Date now = new Date();
        final Date nowToo = new Date(now.getTime());
        
        final boolean expected = false;
        final boolean actual = Dates.after(now).apply(nowToo);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Dates#after(Date)} with only a millisecond difference after now.
     */
    @Test
    public void afterOneMilliSecondAfter() {
        final Date now = new Date();
        final Date oneMilliSecondAfter = new Date(now.getTime() + 1);
        
        final boolean expected = true;
        final boolean actual = Dates.after(now).apply(oneMilliSecondAfter);
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Dates#after(Date)} with a date after the other.
     */
    @Test
    public void afterWithDateAfter() {
        final boolean expected = true;
        final boolean actual = Dates.after(today).apply(tomorrow);
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Dates#after(Date)} with a null input.
     */
    @Test(expected = NullPointerException.class)
    public void afterNull() {
        Dates.after(null);
    }
    
    /**
     * Tests {@link Dates#between(Date, Date)}.
     */
    @Test
    public void between() {
        final boolean expected = true;
        final boolean actual = Dates.between(yesterday, tomorrow).apply(today);
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Dates#between(Date, Date)} with an input before the range.
     */
    @Test
    public void betweenBefore() {
        final boolean expected = false;
        final boolean actual = Dates.between(today, tomorrow).apply(yesterday);
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Dates#between(Date, Date)} with an input after the range.
     */
    @Test
    public void betweenAfter() {
        final boolean expected = false;
        final boolean actual = Dates.between(yesterday, today).apply(tomorrow);
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Dates#between(Date, Date)} with an input at exactly the start.
     */
    @Test
    public void betweenAtStart() {
        final Date yesterdayToo = new Date(yesterday.getTime());

        final boolean expected = false;
        final boolean actual = Dates.between(yesterday, tomorrow).apply(yesterdayToo);
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Dates#between(Date, Date)} with an input at exactly the end.
     */
    @Test
    public void betweenAtEnd() {
        final Date tomorrowToo = new Date(tomorrow.getTime());
        
        final boolean expected = false;
        final boolean actual = Dates.between(yesterday, tomorrow).apply(tomorrowToo);
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Dates#betweenInclusive(Date, Date)}.
     */
    @Test
    public void betweenInclusive() {
        final boolean expected = true;
        final boolean actual = Dates.betweenInclusive(yesterday, tomorrow).apply(today);
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Dates#betweenInclusive(Date, Date)} with an input before the range.
     */
    @Test
    public void betweenInclusiveBefore() {
        final boolean expected = false;
        final boolean actual = Dates.betweenInclusive(today, tomorrow).apply(yesterday);
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Dates#betweenInclusive(Date, Date)} with an input after the range.
     */
    @Test
    public void betweenInclusiveAfter() {
        final boolean expected = false;
        final boolean actual = Dates.betweenInclusive(yesterday, today).apply(tomorrow);
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Dates#betweenInclusive(Date, Date)} with an input at exactly the start.
     */
    @Test
    public void betweenInclusiveAtStart() {
        final Date yesterdayToo = new Date(yesterday.getTime());

        final boolean expected = true;
        final boolean actual = Dates.betweenInclusive(yesterday, tomorrow).apply(yesterdayToo);
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Dates#betweenInclusive(Date, Date)} with an input at exactly the end.
     */
    @Test
    public void betweenInclusiveAtEnd() {
        final Date tomorrowToo = new Date(tomorrow.getTime());
        
        final boolean expected = true;
        final boolean actual = Dates.betweenInclusive(yesterday, tomorrow).apply(tomorrowToo);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Dates#betweenInclusive(Date, Date)} with sql timestamps, applied with a normal date.
     * Although Timestamps should not be seen as instances of Dates they are often used this way.
     * The betweenInclusive method should not break then.
     */
    @Test
    public void betweenInclusiveTimestamps() {
        final Timestamp yesterdayTimestamp = new Timestamp(yesterday.getTime());
        final Date yesterdayToo = new Date(yesterday.getTime());
        final Timestamp tomorrowTimestamp = new Timestamp(tomorrow.getTime());

        final boolean expected = true;
        final boolean actual = Dates.betweenInclusive(yesterdayTimestamp, tomorrowTimestamp).apply(yesterdayToo);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Dates#betweenInclusive(Date, Date)} with normal dates, applied with a sql timestamp.
     * Although Timestamps should not be seen as instances of Dates they are often used this way.
     * The betweenInclusive method should not break then.
     */
    @Test
    public void betweenInclusiveAppliedWithTimestamp() {
        final Timestamp yesterdayTimestamp = new Timestamp(yesterday.getTime());

        final boolean expected = true;
        final boolean actual = Dates.betweenInclusive(yesterday, tomorrow).apply(yesterdayTimestamp);
        Assert.assertEquals(expected, actual);
    }

}
