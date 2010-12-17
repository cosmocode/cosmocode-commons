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
public class OverlapModeGeneralTest {

    private final OverlapMode mode = OverlapMode.NORMAL;

    private Date yesterday;
    private Date today;
    private Date tomorrow;
    private Date nextMonth;

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
    }

    @Test
    public void timePeriodsOverlap() {
        final TimePeriod a = Dates.timePeriod(today, tomorrow);
        final TimePeriod b = Dates.timePeriod(today, nextMonth);

        final boolean expected = true;
        final boolean actual = mode.isOverlapping(a, b);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void timePeriodsOverlapImmutableDateAndDayPrecision() {
        final TimePeriod a = Dates.timePeriod(yesterday, tomorrow);
        final TimePeriod b = new DayPrecisionTimePeriod(today, nextMonth);

        final boolean expected = true;
        final boolean actual = mode.isOverlapping(a, b);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void timePeriodsNotOverlappingImmutableDateAndDayPrecision() {
        final TimePeriod a = Dates.timePeriod(yesterday, today);
        final TimePeriod b = new DayPrecisionTimePeriod(tomorrow, nextMonth);

        final boolean expected = false;
        final boolean actual = mode.isOverlapping(a, b);
        Assert.assertEquals(expected, actual);
    }

    @Test
    public void timePeriodsOverlapDayPrecisionAndImmutableDate() {
        final TimePeriod a = new DayPrecisionTimePeriod(today, nextMonth);
        final TimePeriod b = Dates.timePeriod(yesterday, tomorrow);

        final boolean expected = true;
        final boolean actual = mode.isOverlapping(a, b);
        Assert.assertEquals(expected, actual);
    }

}
