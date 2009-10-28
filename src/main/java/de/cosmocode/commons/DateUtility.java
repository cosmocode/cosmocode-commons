package de.cosmocode.commons;

import java.text.DateFormat;
import java.util.Calendar;
import java.util.Date;

public final class DateUtility {

    /**
     * Prevent instantiation
     */
    private DateUtility() {
        
    }
    
	public static Date add(int field, int amount) {
		final Calendar calendar = Calendar.getInstance();
		calendar.add(field, amount);
		return calendar.getTime();
	}
	
	public static DateFormat concurrent(DateFormat format) {
	    return new ConcurrentDateFormat(format);
	}
	
}
