package de.cosmocode.commons;

import java.text.AttributedCharacterIterator;
import java.text.DateFormat;
import java.text.FieldPosition;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.util.Calendar;
import java.util.Date;
import java.util.TimeZone;

class ConcurrentDateFormat extends DateFormat {

    private static final long serialVersionUID = -2375861144495307593L;
    
    private final DateFormat format;

    public ConcurrentDateFormat(DateFormat format) {
        this.format = format;
    }

    @Override
    public synchronized Object clone() {
        return format.clone();
    }

    @Override
    public synchronized boolean equals(Object obj) {
        return format.equals(obj);
    }

    @Override
    public synchronized StringBuffer format(Date date, StringBuffer toAppendTo, FieldPosition fieldPosition) {
        return format.format(date, toAppendTo, fieldPosition);
    }

    @Override
    public synchronized AttributedCharacterIterator formatToCharacterIterator(Object obj) {
        return format.formatToCharacterIterator(obj);
    }

    @Override
    public synchronized Calendar getCalendar() {
        return format.getCalendar();
    }

    @Override
    public synchronized NumberFormat getNumberFormat() {
        return format.getNumberFormat();
    }

    @Override
    public synchronized TimeZone getTimeZone() {
        return format.getTimeZone();
    }

    @Override
    public synchronized int hashCode() {
        return format.hashCode();
    }

    @Override
    public synchronized boolean isLenient() {
        return format.isLenient();
    }

    @Override
    public synchronized Date parse(String source, ParsePosition pos) {
        return format.parse(source, pos);
    }

    @Override
    public synchronized Date parse(String source) throws ParseException {
        return format.parse(source);
    }

    @Override
    public synchronized Object parseObject(String source, ParsePosition pos) {
        return format.parseObject(source, pos);
    }

    @Override
    public synchronized Object parseObject(String source) throws ParseException {
        return format.parseObject(source);
    }

    @Override
    public synchronized void setCalendar(Calendar newCalendar) {
        format.setCalendar(newCalendar);
    }

    @Override
    public synchronized void setLenient(boolean lenient) {
        format.setLenient(lenient);
    }

    @Override
    public synchronized void setNumberFormat(NumberFormat newNumberFormat) {
        format.setNumberFormat(newNumberFormat);
    }

    @Override
    public synchronized void setTimeZone(TimeZone zone) {
        format.setTimeZone(zone);
    }

    @Override
    public synchronized String toString() {
        return format.toString();
    }

}