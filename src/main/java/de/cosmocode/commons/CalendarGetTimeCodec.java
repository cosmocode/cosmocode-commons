package de.cosmocode.commons;

import java.util.Calendar;
import java.util.Date;

/**
 * Implementation of {@link Calendars#getTime()}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
enum CalendarGetTimeCodec implements Bijection<Calendar, Date> {
    
    INSTANCE;
    
    @Override
    public Date apply(Calendar from) {
        return from.getTime();
    }
    
    @Override
    public Bijection<Date, Calendar> inverse() {
        return InverseCalendarGetTimeCodec.INSTANCE;
    }
    
    @Override
    public String toString() {
        return "Calendars.getTime()";
    }
    
    /**
     * Implementation for {@link CalendarGetTimeCodec#inverse()}.
     *
     * @since 1.9
     * @author Willi Schoenborn
     */
    private enum InverseCalendarGetTimeCodec implements Bijection<Date, Calendar> {
        
        INSTANCE;
        
        @Override
        public Calendar apply(Date from) {
            final Calendar calendar = Calendar.getInstance();
            calendar.setTime(from);
            return calendar;
        }
        
        @Override
        public Bijection<Calendar, Date> inverse() {
            return CalendarGetTimeCodec.INSTANCE;
        }
        
        @Override
        public String toString() {
            return CalendarGetTimeCodec.INSTANCE.toString() + ".inverse()";
        }
        
    }
    
}
