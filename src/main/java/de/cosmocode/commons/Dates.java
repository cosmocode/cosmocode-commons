package de.cosmocode.commons;

import java.util.Calendar;
import java.util.Date;

/**
 * Utility class providing handy methods
 * regarding {@link Date} and {@link Calendar}.
 *
 * @author Willi Schoenborn
 */
public final class Dates {

    /**
     * Prevent instantiation.
     */
    private Dates() {
        
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
    
}
