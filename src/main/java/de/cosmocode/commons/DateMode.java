package de.cosmocode.commons;

import java.util.Comparator;
import java.util.Date;

/**
 * A {@link DateMode} determines the way how to
 * transform a timestamp of type long into a {@link Date} instance.
 *
 * @author Willi Schoenborn
 */
public enum DateMode implements Comparator<Date> {

    /**
     * Assumes the timestamp is in milliseconds,
     * starting at 1970-01-01T00:00:00GMT
     * as described in {@link Date}.
     */
    JAVA {
        
        @Override
        public Date parse(long value) {
            return new Date(value);
        }
        
        @Override
        public long format(Date date) {
            return date == null ? -1L : date.getTime();
        }
        
        @Override
        public int compare(Date d1, Date d2) {
            return d1.compareTo(d2);
        }
        
    },
    
    /**
     * Assumes the timestamp is in seconds,
     * starting at 1970-01-01T00:00:00GMT.
     */
    UNIXTIME {
        
        @Override
        public Date parse(long value) {
            return new Date(value * 1000L);
        }
        
        @Override
        public long format(Date date) {
            return date == null ? -1L : date.getTime() / 1000L;
        }
        
        @Override
        public int compare(Date d1, Date d2) {
            final long diff = d1.getTime() - d2.getTime();
            return Math.abs(diff) < 1000L ? 0 : (diff < 0L ? -1 : 1); 
        }
        
    };
    
    /**
     * Transforms a timestamp into a {@link Date} instance.
     * 
     * @param value the timestamp
     * @return a new {@link Date} instance
     */
    public abstract Date parse(long value);
    
    /**
     * Transforms a {@link Date} instance into a timestamp.
     * 
     * @param date the date instance
     * @return a timestamp created from the date, or -1 if date is null
     */
    public abstract long format(Date date);

    /**
     * <p>
     *   This implementations returns 0 if the two given
     *   {@link Date}s are equals as defined by the semantics of
     *   this {@link DateMode}. 
     * </p>
     * 
     * {@inheritDoc}
     */
    @Override
    public abstract int compare(Date d1, Date d2);
    
}
