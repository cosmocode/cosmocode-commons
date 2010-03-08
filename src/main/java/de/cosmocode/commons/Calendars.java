package de.cosmocode.commons;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 * Utility class providng factory and
 * manipulation methods for {@link Calendar}s.
 *
 * @author Willi Schoenborn
 */
public final class Calendars {

    private Calendars() {
        
    }
    
    /**
     * Creates a new {@link Calendar} with its
     * internal time set to a given {@link Date}.
     * 
     * @param date the date to use as the base
     * @return a new {@link Calendar} which is set to date
     * @throws NullPointerException if date is null
     */
    public static GregorianCalendar of(Date date) {
        if (date == null) throw new NullPointerException("Date must not be null");
        final GregorianCalendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        return calendar;
    }
    
    /**
     * Sets a {@link Calendar} to midnight (00:00:00) at 
     * the date currently selected.
     * 
     * @param calendar the {@link Calendar} which will be set to midnight
     * @throws NullPointerException if calendar is null
     */
    public static void toBeginningOfTheDay(Calendar calendar) {
        if (calendar == null) throw new NullPointerException("Calendar must not be null");
        calendar.set(Calendar.HOUR_OF_DAY, 0);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
    }
    
    /**
     * Sets a {@link Calendar} to one second before midnight (23:59:59) at 
     * the date currently selected.
     * 
     * @param calendar the {@link Calendar} which will be set to one second before midnight
     * @throws NullPointerException if calendar is null
     */
    public static void toEndOfTheDay(Calendar calendar) {
        Calendars.toBeginningOfTheDay(calendar);
        calendar.add(Calendar.DATE, 1);
        calendar.add(Calendar.SECOND, -1);
    }
    
}
