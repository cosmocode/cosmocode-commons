package de.cosmocode.commons;

public final class NumberUtility {

    /**
     * Prevent instantiation
     */
    private NumberUtility() {
        
    }
    
    public static boolean isByte(long value) {
        return isBetween(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
    }
    
    public static boolean isNotByte(long value) {
        return !isByte(value);
    }
    
    public static boolean isByte(int value) {
        return isBetween(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
    }
    
    public static boolean isNotByte(int value) {
        return !isByte(value);
    }
    
    public static boolean isShort(long value) {
        return isBetween(value, Short.MIN_VALUE, Short.MAX_VALUE);
    }
    
    public static boolean isNotShort(long value) {
        return !isShort(value);
    }
    
    public static boolean isShort(int value) {
        return isBetween(value, Short.MIN_VALUE, Short.MAX_VALUE);
    }
    
    public static boolean isNotShort(int value) {
        return !isShort(value);
    }
    
    public static boolean isChar(long value) {
        return isBetween(value, Character.MIN_VALUE, Character.MAX_VALUE);
    }
    
    public static boolean isNotChar(long value) {
        return !isChar(value);
    }
    
    public static boolean isChar(int value) {
        return isBetween(value, Character.MIN_VALUE, Character.MAX_VALUE);
    }
    
    public static boolean isNotChar(int value) {
        return !isChar(value);
    }
    
    public static boolean isInt(long value) {
        return isBetween(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    public static boolean isNotInt(long value) {
        return !isInt(value);
    }
    
    public static boolean isFloat(double value) {
        if (value == 0) return true;
        if (isBetween(value, Float.MIN_VALUE, Float.MAX_VALUE)) return true;
        if (isBetween(value, -Float.MAX_VALUE, -Float.MIN_VALUE)) return true;
        return false;
    }
    
    public static boolean isNotFloat(double value) {
        return !isFloat(value);
    }
    
    public static boolean isBetween(int value, int start, int end) {
        return value >= start && value <= end;
    }
    
    public static boolean isBetween(long value, long start, long end) {
        return value >= start && value <= end;
    }
    
    public static boolean isBetween(double value, double start, double end) {
        return value >= start && value <= end;
    }
    
}