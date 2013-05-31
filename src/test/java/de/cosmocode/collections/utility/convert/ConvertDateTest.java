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
package de.cosmocode.collections.utility.convert;

import de.cosmocode.collections.utility.Convert;
import de.cosmocode.commons.DateMode;
import de.cosmocode.junit.Asserts;
import org.junit.Assert;
import org.junit.Test;

import java.util.Calendar;
import java.util.Date;

/**
 * Tests {@link Convert#intoDate(Object)} and
 * {@link Convert#intoDate(Object, Date)}.
 *
 * @author Willi Schoenborn
 */
public class ConvertDateTest {
    
    /**
     * Tests {@link Convert#intoDate(Object)} with null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoDateNull() {
        Convert.intoDate(null);
    }

    /**
     * Tests {@link Convert#intoDate(Object)} with "".
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoDateEmpty() {
        Convert.intoDate("");
    }
    
    /**
     * Tests {@link Convert#intoDate(Object)} with new Object().
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoDateObject() {
        Convert.intoDate(new Object());
    }
    
    /**
     * Tests {@link Convert#intoDate(Object)} with valid attributes.
     */
    @Test
    public void intoDate() {
        final Date date = new Date();
        Assert.assertEquals(date, Convert.intoDate(date));
        Assert.assertEquals(date, Convert.intoDate(date.getTime()));
        Assert.assertEquals(date, Convert.intoDate(Long.toString(date.getTime())));
        
        final Calendar calendar = Calendar.getInstance();
        Assert.assertEquals(calendar.getTime(), Convert.intoDate(calendar));
        Assert.assertEquals(calendar.getTime(), Convert.intoDate(calendar.getTime()));
        Assert.assertEquals(calendar.getTime(), Convert.intoDate(calendar.getTime().getTime()));
        Assert.assertEquals(calendar.getTime(), Convert.intoDate(Long.toString(calendar.getTime().getTime())));
    }
    
    /**
     * Tests {@link Convert#intoDate(Object, Date)} with valid attributes.
     */
    @Test
    public void intoDateDefaultUnused() {
        final Date date = new Date();
        final Date defaultValue = null;
        Assert.assertEquals(date, Convert.intoDate(date, defaultValue));
        Assert.assertEquals(date, Convert.intoDate(date.getTime(), defaultValue));
        Assert.assertEquals(date, Convert.intoDate(Long.toString(date.getTime()), defaultValue));
        
        final Calendar calendar = Calendar.getInstance();
        Assert.assertEquals(calendar.getTime(), Convert.intoDate(calendar, defaultValue));
        Assert.assertEquals(calendar.getTime(), Convert.intoDate(calendar.getTime(), defaultValue));
        Assert.assertEquals(calendar.getTime(), Convert.intoDate(calendar.getTime().getTime(), defaultValue));
        final String timeString = Long.toString(calendar.getTime().getTime());
        Assert.assertEquals(calendar.getTime(), Convert.intoDate(timeString, defaultValue));
    }

    /**
     * Tests {@link Convert#intoDate(Object, Date)} with invalid attributes.
     */
    @Test
    public void intoDateDefaultUsed() {
        final Date defaultValue = new Date();
        Assert.assertSame(defaultValue, Convert.intoDate(null, defaultValue));
        Assert.assertSame(defaultValue, Convert.intoDate("", defaultValue));
        Assert.assertSame(defaultValue, Convert.intoDate(new Object(), defaultValue));
    }
    
    /**
     * Tests {@link Convert#intoDate(Object, DateMode)} with null dateMode.
     */
    @Test(expected = NullPointerException.class)
    public void intoDateModeNullMode() {
        final DateMode dateMode = null;
        Convert.intoDate(null, dateMode);
    }
    
    /**
     * Tests {@link Convert#intoDate(Object, DateMode)} with null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoDateModeNull() {
        Convert.intoDate(null, DateMode.JAVA);
    }

    /**
     * Tests {@link Convert#intoDate(Object, DateMode)} with "".
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoDateModeEmpty() {
        Convert.intoDate("", DateMode.JAVA);
    }
    
    /**
     * Tests {@link Convert#intoDate(Object, DateMode)} with new Object().
     */
    @Test(expected = IllegalArgumentException.class)
    public void intoDateModeObject() {
        Convert.intoDate(new Object(), DateMode.JAVA);
    }
    
    /**
     * Tests {@link Convert#intoDate(Object, DateMode)} with valid {@link Date} attributes.
     */
    @Test
    public void intoDateMode() {
        final Date date = new Date();
        final long unix = date.getTime() / 1000;
        Assert.assertSame(0, DateMode.UNIXTIME.compare(date, Convert.intoDate(date, DateMode.UNIXTIME)));
        Assert.assertSame(0, DateMode.UNIXTIME.compare(date, Convert.intoDate(unix, DateMode.UNIXTIME)));
        Assert.assertSame(0, DateMode.UNIXTIME.compare(date, Convert.intoDate(Long.toString(unix), DateMode.UNIXTIME)));
    }

    /**
     * Tests {@link Convert#intoDate(Object, DateMode)} with valid {@link Calendar} attributes.
     */
    @Test
    public void intoDateModeCalendar() {
        final Calendar calendar = Calendar.getInstance();
        final Date date = calendar.getTime();
        final long unix = calendar.getTimeInMillis() / 1000;
        Assert.assertSame(0, DateMode.UNIXTIME.compare(date, Convert.intoDate(date, DateMode.UNIXTIME)));
        Assert.assertSame(0, DateMode.UNIXTIME.compare(date, Convert.intoDate(unix, DateMode.UNIXTIME)));
        Assert.assertSame(0, DateMode.UNIXTIME.compare(date, Convert.intoDate(Long.toString(unix), DateMode.UNIXTIME)));
    }
    
    /**
     * Tests {@link Convert#intoDate(Object, DateMode, Date)} with valid attributes.
     */
    @Test
    public void intoDateModeDefaultUnused() {
        final Date date = new Date();
        final long unix = date.getTime() / 1000;
        final Date somewhen = new Date(date.getTime() / 2);
        Asserts.assertNotEquals(somewhen, Convert.intoDate(date, DateMode.UNIXTIME, somewhen));
        Asserts.assertNotEquals(somewhen, Convert.intoDate(unix, DateMode.UNIXTIME, somewhen));
        Asserts.assertNotEquals(somewhen, Convert.intoDate(Long.toString(unix), DateMode.UNIXTIME, somewhen));
    }

    /**
     * Tests {@link Convert#intoDate(Object, DateMode, Date)} with invalid attributes.
     */
    @Test
    public void intoDateModeDefaultUsed() {
        final Date date = new Date();
        final Date somewhen = new Date(date.getTime() / 2);
        Asserts.assertEquals(somewhen, Convert.intoDate(null, DateMode.UNIXTIME, somewhen));
        Asserts.assertEquals(somewhen, Convert.intoDate("", DateMode.UNIXTIME, somewhen));
        Asserts.assertEquals(somewhen, Convert.intoDate(new Object(), DateMode.UNIXTIME, somewhen));
        Asserts.assertEquals(somewhen, Convert.intoDate(-1, DateMode.UNIXTIME, somewhen));
    }

}
