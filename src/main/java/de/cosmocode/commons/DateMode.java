package de.cosmocode.commons;

import java.util.Date;

/**
 * A {@link DateMode} determines the way how to
 * transform a timestamp of type long into a {@link Date} instance.
 *
 * @author Willi Schoenborn
 */
public enum DateMode {

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
    
}
