package de.cosmocode.commons;

import java.util.Calendar;
import java.util.Date;

public class DateUtility {

	public static Date add(int field, int amount) {
		final Calendar calendar = Calendar.getInstance();
		calendar.add(field, amount);
		return calendar.getTime();
	}
	
}