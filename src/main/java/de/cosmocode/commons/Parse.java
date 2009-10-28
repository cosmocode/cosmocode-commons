package de.cosmocode.commons;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

public final class Parse {

    /**
     * Prevent instantiation.
     */
    private Parse() {
        
    }
    
    /**
     * Parses a value of a generic type
     * into a boolean. If value is a {@link String}
     * or an {@link Object} {@link Boolean#parseBoolean(String)}
     * will be used.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @throws ClassCastException if value can't be parsed into a boolean
     * @return the parsed boolean
     */
    public static <E> boolean asBoolean(E value) {
        if (value instanceof Boolean) {
            return Boolean.class.cast(value).booleanValue();
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            return Boolean.parseBoolean(s);
        } else {
            throw new ClassCastException(value + " can't be parsed as boolean");
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a boolean. If value is a {@link String}
     * or an {@link Object} {@link Boolean#parseBoolean(String)}
     * will be used.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a boolean
     * @return the parsed boolean or the defaultValue if value can't be parsed into a boolean
     */
    public static <E> boolean asBoolean(E value, boolean defaultValue) {
        if (value instanceof Boolean) {
            return Boolean.class.cast(value).booleanValue();
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            return Boolean.parseBoolean(s);
        } else {
            return defaultValue;
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a byte. if value is a {@link String}
     * {@link Byte#parseByte(String)} will be used.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @throws NumberFormatException if value can't be parsed into a byte
     * @return the parsed byte
     */
    public static <E> byte asByte(E value) {
        if (value instanceof Byte) {
            return Byte.class.cast(value).byteValue();
        } else if (value instanceof Number) {
            final Number number = Number.class.cast(value);
            final long longValue = number.longValue();
            if (NumberUtility.isByte(longValue)) {
                return number.byteValue();
            } else {
                throw new NumberFormatException("Value out of range. Value:\"" + value + "\"");
            }
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            return Byte.parseByte(s);
        } else {
            throw new NumberFormatException(value + " can't be parsed as byte");
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a byte.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a byte
     * @return the parsed byte or the defaultValue if value can't be parsed into a byte
     */
    public static <E> byte asByte(E value, byte defaultValue) {
        if (value instanceof Byte) {
            return Byte.class.cast(value).byteValue();
        } else if (value instanceof Number) {
            final Number number = Number.class.cast(value);
            final long longValue = number.longValue();
            if (NumberUtility.isByte(longValue)) {
                return number.byteValue();
            } else {
                return defaultValue;
            }
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            try {
                final Number number = new Long(s);
                final long longValue = number.longValue();
                if (NumberUtility.isByte(longValue)) {
                    return number.byteValue();
                } else {
                    return defaultValue;
                }
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    
    /**
     * Parses a value of a generic type
     * into a short.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @throws NumberFormatException if value can't be parsed into a short
     * @return the parsed short
     */
    public static <E> short asShort(E value) {
        if (value instanceof Short) {
            return Short.class.cast(value).shortValue();
        } else if (value instanceof Number) {
            final Number number = Number.class.cast(value);
            final long longValue = number.longValue();
            if (NumberUtility.isShort(longValue)) {
                return number.shortValue();
            } else {
                throw new NumberFormatException("Value out of range. Value:\"" + value + "\"");
            }
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            return Short.parseShort(s);
        } else {
            throw new NumberFormatException(value + " can't be parsed as short");
        }
    }

    
    /**
     * Parses a value of a generic type
     * into a short.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a short
     * @return the parsed short or the defaultValue if value can't be parsed into a short
     */
    public static <E> short asShort(E value, short defaultValue) {
        if (value instanceof Short) {
            return Short.class.cast(value).shortValue();
        } else if (value instanceof Number) {
            final Number number = Number.class.cast(value);
            final long longValue = number.longValue();
            if (NumberUtility.isShort(longValue)) {
                return number.shortValue();
            } else {
                return defaultValue;
            }
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            return Short.parseShort(s);
        } else {
            return defaultValue;
        }
    }

    
    /**
     * Parses a value of a generic type
     * into a short.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @throws NumberFormatException if value can't be parsed into a short
     * @return the parsed short
     */
    public static <E> char asChar(E value) {
        if (value instanceof Character) {
            return Character.class.cast(value).charValue();
        } else if (value instanceof Number) {
            final Number number = Number.class.cast(value);
            final long longValue = number.longValue();
            if (NumberUtility.isChar(longValue)) {
                return (char) longValue;
            } else {
                throw new NumberFormatException("Value out of range. Value:\"" + value + "\"");
            }
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            if (StringUtility.isNumeric(s)) {
                final long longValue = Long.parseLong(s);
                if (NumberUtility.isChar(longValue)) {
                    return (char) longValue;
                } else {
                    throw new NumberFormatException("Value out of range. Value:\"" + value + "\"");
                }
            } else if (s.length() == 1) {
                return s.charAt(0);
            }
        }
        throw new NumberFormatException(value + " can't be parsed as char");
    }

    /**
     * Parses a value of a generic type
     * into a char.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a char
     * @return the parsed char or the defaultValue if value can't be parsed into a char
     */
    public static <E> char asChar(E value, char defaultValue) {
        if (value instanceof Character) {
            return Character.class.cast(value).charValue();
        } else if (value instanceof Number) {
            final Number number = Number.class.cast(value);
            final long longValue = number.longValue();
            if (NumberUtility.isChar(longValue)) {
                return (char) longValue;
            } else {
                return defaultValue;
            }
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            if (StringUtility.isNumeric(s)) {
                final long longValue = Long.parseLong(s);
                if (NumberUtility.isChar(longValue)) {
                    return (char) longValue;
                } else {
                    return defaultValue;
                }
            } else if (s.length() == 1) {
                return s.charAt(0);
            }
        }
        return defaultValue;
    }
    
    /**
     * Parses a value of a generic type
     * into an int.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @throws NumberFormatException if value can't be parsed into an int
     * @return the parsed int
     */
    public static <E> int asInt(E value) {
        if (value instanceof Integer) {
            return Integer.class.cast(value).intValue();
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            return Integer.parseInt(s);
        } else {
            throw new NumberFormatException(value + " can't be parsed as int");
        }
    }
    
    /**
     * Parses a value of a generic type
     * into an int.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into an int
     * @return the parsed int or the defaultValue if value can't be parsed into an int
     */
    public static <E> int asInt(E value, int defaultValue) {
        if (value instanceof Integer) {
            return Integer.class.cast(value).intValue();
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            return Integer.parseInt(s);
        } else {
            return defaultValue;
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a long.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @throws NumberFormatException if value can't be parsed into a long
     * @return the parsed long
     */
    public static <E> long asLong(E value) {
        if (value instanceof Long) {
            return Long.class.cast(value).longValue();
        } else if (value instanceof Number) {
            return Number.class.cast(value).longValue();
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            return Long.parseLong(s);
        } else {
            throw new NumberFormatException(value + " can't be parsed as long");
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a long.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a long
     * @return the parsed long or the defaultValue if value can't be parsed into a long
     */
    public static <E> long asLong(E value, long defaultValue) {
        if (value instanceof Long) {
            return Long.class.cast(value).longValue();
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            return Long.parseLong(s);
        } else {
            return defaultValue;
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a float.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @throws NumberFormatException if value can't be parsed into a float
     * @return the parsed float
     */
    public static <E> float asFloat(E value) {
        if (value instanceof Float) {
            return Float.class.cast(value).floatValue();
        } else if (value instanceof Number) {
            final Number number = Number.class.cast(value);
            final double doubleValue = number.doubleValue();
            if (NumberUtility.isFloat(doubleValue)) {
                return number.floatValue();
            } else {
                throw new NumberFormatException("Value out of range. Value:\"" + value + "\"");
            }
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            final double doubleValue = Double.parseDouble(s);
            if (NumberUtility.isFloat(doubleValue)) {
                return Float.parseFloat(s);
            } else {
                throw new NumberFormatException("Value out of range. Value:\"" + value + "\"");
            }
        } else {
            throw new NumberFormatException(value + " can't be parsed as float");
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a float.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a float
     * @return the parsed float or the defaultValue if value can't be parsed into a float
     */
    public static <E> float asFloat(E value, float defaultValue) {
        if (value instanceof Float) {
            return Float.class.cast(value).floatValue();
        } else if (value instanceof Number) {
            final Number number = Number.class.cast(value);
            final double doubleValue = number.doubleValue();
            if (NumberUtility.isFloat(doubleValue)) {
                return number.floatValue();
            } else {
                return defaultValue;
            }
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            final double doubleValue = Double.parseDouble(s);
            if (NumberUtility.isFloat(doubleValue)) {
                return Float.parseFloat(s);
            } else {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a double.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @throws NumberFormatException if value can't be parsed into a double
     * @return the parsed double
     */
    public static <E> double asDouble(E value) {
        if (value instanceof Double) {
            return Double.class.cast(value).doubleValue();
        } else if (value instanceof Number) {
            final Number number = Number.class.cast(value);
            return number.doubleValue();
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            return Double.parseDouble(s);
        } else {
            throw new NumberFormatException(value + " can't be parsed as float");
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a double.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a double
     * @return the parsed double or the defaultValue if value can't be parsed into a double
     */
    public static <E> double asDouble(E value, double defaultValue) {
        if (value instanceof Double) {
            return Double.class.cast(value).doubleValue();
        } else if (value instanceof Number) {
            final Number number = Number.class.cast(value);
            return number.doubleValue();
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            try {
                return Double.parseDouble(s);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        } else {
            return defaultValue;
        }
    }

    /**
     * Parses a value of a generic type
     * into a {@link BigInteger}.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @throws NumberFormatException if value can't be parsed into a {@link BigInteger}
     * @return the parsed {@link BigInteger}
     */
    public static <E> BigInteger asBigInteger(E value) {
        if (value == null) {
            return null;
        } else if (value instanceof BigInteger) {
            return BigInteger.class.cast(value);
        } else if (value instanceof Number) {
            final long l = Number.class.cast(value).longValue();
            return BigInteger.valueOf(l);
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            return new BigInteger(s);
        } else {
            throw new NumberFormatException(value + " can't be parsed as BigInteger");
        }
    }

    /**
     * Parses a value of a generic type
     * into a {@link BigInteger}.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a {@link BigInteger}
     * @return the parsed {@link BigInteger} or the defaultValue if value can't be parsed into a {@link BigInteger}
     */
    public static <E> BigInteger asBigInteger(E value, BigInteger defaultValue) {
        if (value == null) {
            return null;
        } else if (value instanceof BigInteger) {
            return BigInteger.class.cast(value);
        } else if (value instanceof Number) {
            final long l = Number.class.cast(value).longValue();
            return BigInteger.valueOf(l);
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            return new BigInteger(s);
        } else {
            return defaultValue;
        }
    }

    /**
     * Parses a value of a generic type
     * into a {@link BigDecimal}.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @throws NumberFormatException if value can't be parsed into a {@link BigDecimal}
     * @return the parsed {@link BigDecimal}
     */
    public static <E> BigDecimal asBigDecimal(E value) {
        if (value == null) {
            return null;
        } else if (value instanceof BigDecimal) {
            return BigDecimal.class.cast(value);
        } else if (value instanceof Number) {
            final double d = Number.class.cast(value).doubleValue();
            return BigDecimal.valueOf(d);
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            return new BigDecimal(s);
        } else {
            throw new NumberFormatException(value + " can't be parsed as BigDecimal");
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a {@link BigDecimal}.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a {@link BigDecimal}
     * @return the parsed {@link BigDecimal} or the defaultValue if value can't be parsed into a {@link BigDecimal}
     */
    public static <E> BigDecimal asBigDecimal(E value, BigDecimal defaultValue) {
        if (value instanceof BigDecimal) {
            return BigDecimal.class.cast(value);
        } else if (value instanceof Number) {
            final double d = Number.class.cast(value).doubleValue();
            return BigDecimal.valueOf(d);
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            return new BigDecimal(s);
        } else {
            return defaultValue;
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a {@link Date}.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @throws ClassCastException if value can't be parsed into a {@link Date}
     * @return the parsed {@link Date}
     */
    public static <E> Date asDate(E value) {
        return asDate(value, DateMode.JAVA);
    }

    /**
     * Parses a value of a generic type
     * into a {@link Date}.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @param mode a {@link DateMode} instance handling the long to time conversion
     * @throws ClassCastException if value can't be parsed into a {@link Date}
     * @return the parsed {@link Date}
     */
    public static <E> Date asDate(E value, DateMode mode) {
        if (value == null) {
            return null;
        } else if (value instanceof Date) {
            return Date.class.cast(value);
        } else if (value instanceof Calendar) {
            return Calendar.class.cast(value).getTime();
        } else if (value instanceof Number) {
            final long l = Number.class.cast(value).longValue();
            return mode.create(l);
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            if (StringUtility.isNumeric(s)) {
                final long l = Long.parseLong(s);
                return mode.create(l);
            }
        }
        throw new ClassCastException(value + " can't be parsed as Date");
    }


    /**
     * Parses a value of a generic type
     * into a {@link Date}.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a {@link Date}
     * @return the parsed {@link Date}
     */
    public static <E> Date asDate(E value, Date defaultValue) {
        return asDate(value, DateMode.JAVA, defaultValue);
    }
    
    /**
     * Parses a value of a generic type
     * into a {@link Date}.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a {@link Date}
     * @return the parsed {@link Date} or the defaultValue if value can't be parsed into a {@link Date}
     */
    public static <E> Date asDate(E value, DateMode mode, Date defaultValue) {
        if (value == null) {
            return null;
        } else if (value instanceof Date) {
            return Date.class.cast(value);
        } else if (value instanceof Calendar) {
            return Calendar.class.cast(value).getTime();
        } else if (value instanceof Number) {
            final long l = Number.class.cast(value).longValue();
            return mode.create(l);
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            if (StringUtility.isNumeric(s)) {
                final long l = Long.parseLong(s);
                return mode.create(l);
            }
        }
        return defaultValue;
    }

    /**
     * Parses a value of a generic type
     * into an {@link Enum}.
     * The value must be either a valid enum constant
     * name or ordinal.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @throws IllegalArgumentException if this value can parsed into a {@link String} or a {@link Number}
     *         but does not represent a valid {@link Enum} constant of enumType
     * @throws ClassCastException if value can't be parsed into an {@link Enum}
     * @return the parsed {@link Enum}
     */
    public static <T, E extends Enum<E>> E asEnum(T value, Class<E> enumType) {
        if (value == null) {
            return null;
        } else if (enumType.isInstance(value)) {
            return enumType.cast(value);
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            return Enum.valueOf(enumType, s.toUpperCase());
        } else if (value instanceof Number) {
            final int i = Number.class.cast(value).intValue();
            return EnumUtility.valueOf(enumType, i);
        } else {
            throw new ClassCastException(value + " can't be parsed as " + enumType.getName());
        }
    }
    
    /**
     * Parses a value of a generic type
     * into an {@link Enum}.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into an {@link Enum}
     * @return the parsed {@link Enum} or the defaultValue if value can't be parsed into an {@link Enum}
     */
    public static <T, E extends Enum<E>> E asEnum(T value, Class<E> enumType, E defaultValue) {
        if (enumType.isInstance(value)) {
            return enumType.cast(value);
        } else if (value instanceof String) {
            final String s = String.class.cast(value);
            return Enum.valueOf(enumType, s);
        } else {
            return defaultValue;
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a {@link String}.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @throws ClassCastException if value can't be parsed into a {@link String}
     * @return the parsed {@link String}
     */
    public static <E> String asString(E value) {
        if (value == null) {
            return null;
        } else if (value instanceof String) {
            return String.class.cast(value);
        } else {
            return value.toString();
        }
    }
    
    /**
     * Parses a value of a generic type
     * into a {@link String}.
     * 
     * @param <E> the generic parameter type
     * @param value the value being parsed
     * @param defaultValue the default value if value can't be parsed into a {@link String}
     * @return the parsed {@link String} or the defaultValue if value can't be parsed into a {@link String}
     */
    public static <E> String asString(E value, String defaultValue) {
        if (value instanceof String) {
            return String.class.cast(value);
        } else if (value == null) {
            return defaultValue;
        } else {
            return value.toString();
        }
    }
    
}
