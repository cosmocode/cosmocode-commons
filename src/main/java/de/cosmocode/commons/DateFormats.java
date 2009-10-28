package de.cosmocode.commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

public final class DateFormats {

    public static final DateFormat ISO_8061 = DateUtility.concurrent(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz"));
    
    public static final DateFormat RFC_822 = DateUtility.concurrent(
        new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US)
    );
    
    public static final DateFormat RSS_20 = DateUtility.concurrent(new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z"));
    
    /**
     * Prevent instantiation.
     */
    private DateFormats() {
        
    }
    
}
