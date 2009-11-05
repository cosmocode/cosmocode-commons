package de.cosmocode.commons;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Utility class providing handy methods
 * regarding {@link Date} and {@link Calendar}.
 *
 * @author Willi Schoenborn
 */
public final class DateUtility {

    /**
     * Prevent instantiation.
     */
    private DateUtility() {
        
    }
  
    /**
     * Creates a {@link Date} using a {@link Calendar}
     * and automatically adds the given amount to the
     * given field.
     * 
     * <p>
     *   This allows one liner like this:
     *   <pre>
     *     final Date in2Days = DateUtility.add(Calendar.DATE, 2);
     *   </pre>
     * </p>
     * 
     * @param field the field (e.g. {@link Calendar#DATE})) which should be changed
     * @param amount the amount passed to {@link Calendar#add(int, int)}
     * @return the newly created {@link Date} instance
     */
    public static Date add(int field, int amount) {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(field, amount);
        return calendar.getTime();
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
