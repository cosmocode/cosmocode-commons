package de.cosmocode.commons;

import java.util.Date;

/**
 * A {@link DateMode} determines the way how to
 * transform a timestamp of type long into a {@link Date} instance.
 *
 * @author Willi Schoenborn <schoenborn@cosmocode.de>
 */
public enum DateMode {

    /**
     * Assumes the timestamp is in milliseconds,
     * starting at 1970-01-01T00:00:00GMT
     * as described in {@link Date}.
     */
    JAVA {
        
        @Override
        public Date create(long value) {
            return new Date(value);
        }
        
    },
    
    /**
     * Assumes the timestamp is in seconds,
     * starting at 1970-01-01T00:00:00GMT.
     */
    UNIXTIME {
        
        @Override
        public Date create(long value) {
            return new Date(value * 1000L);
        }
        
    };
    
    /**
     * Transforms a timestamp into a {@link Date} instance.
     * 
     * @param value the timestamp
     * @return a new {@link Date} instance
     */
    public abstract Date create(long value);
    
}
