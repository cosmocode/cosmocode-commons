package de.cosmocode.commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Utility class providing useful {@link DateFormat}s.
 * 
 * @author schoenborn@cosmocode.de
 */
public final class DateFormats {

    public static final DateFormat ISO_8061 = DateFormats.concurrent(
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
    );
    
    public static final DateFormat RFC_822 = DateFormats.concurrent(
        new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US)
    );
    
    public static final DateFormat RSS_20 = DateFormats.concurrent(
        new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z")
    );
    
    /**
     * Prevent instantiation.
     */
    private DateFormats() {
        
    }
    
    /**
     * Decorates a {@link DateFormat} to make it threadsafe.
     * 
     * <p>
     *   <strong>Note:</strong> 
     *   This implementation uses synchronization to achieve
     *   thread safety. Consider implementing an immutable version.
     * <p>
     * 
     * @param format the {@link DateFormat} to decorate
     * @return a threadsafe version of the {@link DateFormat}
     */
    public static DateFormat concurrent(DateFormat format) {
        if (format instanceof ConcurrentDateFormat) {
            return ConcurrentDateFormat.class.cast(format);
        } else {
            return new ConcurrentDateFormat(format);
        }
    }
    
}
