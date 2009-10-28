package de.cosmocode.commons;

import java.util.Date;

public enum DateMode {

    JAVA {
        
        @Override
        public Date create(long value) {
            return new Date(value);
        }
        
    },
    
    UNIXTIME {
        
        @Override
        public Date create(long value) {
            return new Date(value * 1000L);
        }
        
    };
    
    public abstract Date create(long value);
    
}
