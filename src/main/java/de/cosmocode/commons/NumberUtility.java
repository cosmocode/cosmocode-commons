package de.cosmocode.commons;

/**
 * Utility class providing many useful
 * methods regarding all kind of {@link Number}s.
 *
 * @author Willi Schoenborn <schoenborn@cosmocode.de>
 */
public final class NumberUtility {

    /**
     * Prevent instantiation.
     */
    private NumberUtility() {
        
    }
    
    /**
     * Checks whether value is a valid byte.
     * 
     * @param value the long value
     * @return true of value is a valid byte
     */
    public static boolean isByte(long value) {
        return isBetween(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
    }

    /**
     * Checks whether value is not a valid byte.
     * 
     * @param value the long value
     * @return true of value is a valid byte
     */
    public static boolean isNotByte(long value) {
        return !isByte(value);
    }

    /**
     * Checks whether value is a valid byte.
     * 
     * @param value the int value
     * @return true of value is a valid byte
     */
    public static boolean isByte(int value) {
        return isBetween(value, Byte.MIN_VALUE, Byte.MAX_VALUE);
    }

    /**
     * Checks whether value is not a valid byte.
     * 
     * @param value the int value
     * @return true of value is a valid byte
     */
    public static boolean isNotByte(int value) {
        return !isByte(value);
    }

    /**
     * Checks whether value is a valid short.
     * 
     * @param value the long value
     * @return true of value is a valid short
     */
    public static boolean isShort(long value) {
        return isBetween(value, Short.MIN_VALUE, Short.MAX_VALUE);
    }

    /**
     * Checks whether value is not a valid short.
     * 
     * @param value the long value
     * @return true of value is a valid short
     */
    public static boolean isNotShort(long value) {
        return !isShort(value);
    }

    /**
     * Checks whether value is a valid short.
     * 
     * @param value the int value
     * @return true of value is a valid short
     */
    public static boolean isShort(int value) {
        return isBetween(value, Short.MIN_VALUE, Short.MAX_VALUE);
    }

    /**
     * Checks whether value is not a valid short.
     * 
     * @param value the int value
     * @return true of value is a valid short
     */
    public static boolean isNotShort(int value) {
        return !isShort(value);
    }

    /**
     * Checks whether value is a valid char.
     * 
     * @param value the long value
     * @return true of value is a valid char
     */
    public static boolean isChar(long value) {
        return isBetween(value, Character.MIN_VALUE, Character.MAX_VALUE);
    }

    /**
     * Checks whether value is not a valid char.
     * 
     * @param value the long value
     * @return true of value is a valid char
     */
    public static boolean isNotChar(long value) {
        return !isChar(value);
    }

    /**
     * Checks whether value is a valid char.
     * 
     * @param value the int value
     * @return true of value is a valid char
     */
    public static boolean isChar(int value) {
        return isBetween(value, Character.MIN_VALUE, Character.MAX_VALUE);
    }

    /**
     * Checks whether value is not a valid char.
     * 
     * @param value the int value
     * @return true of value is a valid char
     */
    public static boolean isNotChar(int value) {
        return !isChar(value);
    }

    /**
     * Checks whether value is a valid int.
     * 
     * @param value the long value
     * @return true of value is a valid int
     */
    public static boolean isInt(long value) {
        return isBetween(value, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    /**
     * Checks whether value is not a valid int.
     * 
     * @param value the long value
     * @return true of value is a valid int
     */
    public static boolean isNotInt(long value) {
        return !isInt(value);
    }

    /**
     * Checks whether value is a valid float.
     * 
     * @param value the double value
     * @return true of value is a valid float
     */
    public static boolean isFloat(double value) {
        if (value == 0) return true;
        if (isBetween(value, Float.MIN_VALUE, Float.MAX_VALUE)) return true;
        if (isBetween(value, -Float.MAX_VALUE, -Float.MIN_VALUE)) return true;
        return false;
    }

    /**
     * Checks whether value is not a valid float.
     * 
     * @param value the double value
     * @return true of value is a valid float
     */
    public static boolean isNotFloat(double value) {
        return !isFloat(value);
    }
    
    private static boolean isBetween(int value, int start, int end) {
        return value >= start && value <= end;
    }
    
    private static boolean isBetween(long value, long start, long end) {
        return value >= start && value <= end;
    }
    
    private static boolean isBetween(double value, double start, double end) {
        return value >= start && value <= end;
    }
    
}
