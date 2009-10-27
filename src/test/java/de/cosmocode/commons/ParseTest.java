package de.cosmocode.commons;

import org.junit.Test;
import static org.junit.Assert.*;

public class ParseTest {

    private static enum Continent {
        
        EUROPE,
        ASIA,
        AUSTRALIA,
        AFRICA,
        NORTH_AMERICA,
        SOUTH_AMERICA,
        ANTARCTICA;
        
    }
    
    @Test
    public void asBoolean() {
        assertTrue(Parse.asBoolean(true));
        assertTrue(Parse.asBoolean(Boolean.TRUE));
        assertTrue(Parse.asBoolean("true"));
        assertTrue(Parse.asBoolean("TrUe"));
        assertTrue(Parse.asBoolean(new Object() {
            
            @Override
            public String toString() {
                return "true";
            }
            
        }));
        
        assertFalse(Parse.asBoolean(false));
        assertFalse(Parse.asBoolean(Boolean.FALSE));
        assertFalse(Parse.asBoolean("false"));
        assertFalse(Parse.asBoolean("FalSe"));
        assertFalse(Parse.asBoolean(new Object() {
            
            @Override
            public String toString() {
                return "false";
            }
            
        }));
    }
    
    @Test(expected=ClassCastException.class)
    public void asBooleanNull() {
        Parse.asBoolean(null);
    }

    @Test
    public void asByte() {
        assertEquals(123, Parse.asByte("123"));
        assertEquals(123, Parse.asByte(123));
        assertEquals(0, Parse.asByte("0"));
        assertEquals(0, Parse.asByte(0));
        assertEquals(-77, Parse.asByte("-77"));
        assertEquals(-77, Parse.asByte(-77));
        assertEquals(-128, Parse.asByte("-128"));
        assertEquals(-128, Parse.asByte(-128));
        assertEquals(127, Parse.asByte("127"));
        assertEquals(127, Parse.asByte(127));
    }
    
    @Test(expected=NumberFormatException.class)
    public void asByteOutOfRange() {
        Parse.asByte("-129");
        Parse.asByte("-99999");
        Parse.asByte("128");
        Parse.asByte("99999");
    }
    
    @Test(expected=NumberFormatException.class)
    public void asByteOverflow() {
        Parse.asByte(128);
        Parse.asByte(99999);
        Parse.asByte(-129);
        Parse.asByte(-99999);
    }

    @Test
    public void asShort() {
        assertEquals(2500, Parse.asShort("2500"));
        assertEquals(2500, Parse.asShort(2500));
        assertEquals(0, Parse.asShort("0"));
        assertEquals(0, Parse.asShort(0));
        assertEquals(-4763, Parse.asShort("-4763"));
        assertEquals(-4763, Parse.asShort(-4763));
        assertEquals(-32768, Parse.asShort("-32768"));
        assertEquals(-32768, Parse.asShort(-32768));
        assertEquals(32767, Parse.asShort("32767"));
        assertEquals(32767, Parse.asShort(32767));
    }
    
    @Test(expected=NumberFormatException.class)
    public void asShortOutOfRange() {
        Parse.asShort("32768");
        Parse.asShort("99999");
        Parse.asShort("-32769");
        Parse.asShort("-99999");
    }
    
    @Test(expected=NumberFormatException.class)
    public void asShortOverflow() {
        Parse.asShort(32768);
        Parse.asShort(99999);
        Parse.asShort(-32769);
        Parse.asShort(-99999);
    }

    @Test
    public void asChar() {
        assertEquals(65, Parse.asChar('A'));
        assertEquals(65, Parse.asChar("A"));
        assertEquals(65, Parse.asChar(65));
        assertEquals(65, Parse.asChar("65"));
        assertEquals(10250, Parse.asChar("10250"));
        assertEquals(10250, Parse.asChar(10250));
        assertEquals(0, Parse.asChar("0"));
        assertEquals(0, Parse.asChar(0));
        assertEquals(65535, Parse.asChar("65535"));
        assertEquals(65535, Parse.asChar(65535));
    }

    @Test(expected=NumberFormatException.class)
    public void asCharOutOfRange() {
        Parse.asChar("65536");
        Parse.asChar("99999");
        Parse.asChar("-1");
        Parse.asChar("-99999");
    }

    @Test(expected=NumberFormatException.class)
    public void asCharOverflow() {
        Parse.asChar(65536);
        Parse.asChar(99999);
        Parse.asChar(-1);
        Parse.asChar(-99999);
    }
    
    @Test
    public void asInt() {
        assertEquals(1250000, Parse.asInt("1250000"));
        assertEquals(1250000, Parse.asInt(1250000));
        assertEquals(0, Parse.asInt("0"));
        assertEquals(0, Parse.asInt(0));
        assertEquals(-1250000, Parse.asInt("-1250000"));
        assertEquals(-1250000, Parse.asInt(-1250000));
        assertEquals(-2147483648, Parse.asInt("-2147483648"));
        assertEquals(-2147483648, Parse.asInt(-2147483648));
        assertEquals(2147483647, Parse.asInt("2147483647"));
        assertEquals(2147483647, Parse.asInt(2147483647));
    }

    @Test(expected=NumberFormatException.class)
    public void asIntOutOfRange() {
        Parse.asInt("-2147483649");
        Parse.asInt("2147483648");
    }

    @Test(expected=NumberFormatException.class)
    public void asIntOverflow() {
        Parse.asInt(-2147483649l);
        Parse.asInt(2147483648l);
    }

    @Test
    public void asLong() {
        assertEquals(1250000, Parse.asLong("1250000"));
        assertEquals(1250000, Parse.asLong(1250000));
        assertEquals(0, Parse.asLong("0"));
        assertEquals(0, Parse.asLong(0));
        assertEquals(-1250000, Parse.asLong("-1250000"));
        assertEquals(-1250000, Parse.asLong(-1250000));
        assertEquals(-9223372036854775808l, Parse.asLong("-9223372036854775808"));
        assertEquals(-9223372036854775808l, Parse.asLong(-9223372036854775808l));
        assertEquals(9223372036854775807l, Parse.asLong("9223372036854775807"));
        assertEquals(9223372036854775807l, Parse.asLong(9223372036854775807l));
    }

    @Test(expected=NumberFormatException.class)
    public void asLongOutOfRange() {
        Parse.asInt("-2147483650");
        Parse.asInt("-9999999999");
        Parse.asInt("2147483649");
        Parse.asInt("9999999999");
    }

    @Test
    public void asFloat() {
        assertEquals(123.45f, Parse.asFloat("123.45"), 0f);
        assertEquals(123.45f, Parse.asFloat(123.45f), 0f);
        assertEquals(0f, Parse.asFloat("0"), 0f);
        assertEquals(0f, Parse.asFloat(0f), 0f);
        assertEquals(-123.45f, Parse.asFloat("-123.45"), 0f);
        assertEquals(-123.45f, Parse.asFloat(-123.45f), 0f);
        assertEquals(1.40129846432481707e-45f, Parse.asFloat("1.40129846432481707e-45"), 0f);
        assertEquals(1.40129846432481707e-45f, Parse.asFloat(1.40129846432481707e-45f), 0f);
        assertEquals(3.40282346638528860e+38f, Parse.asFloat("3.40282346638528860e+38"), 0f);
        assertEquals(3.40282346638528860e+38f, Parse.asFloat(3.40282346638528860e+38f), 0f);
    }

    @Test(expected=NumberFormatException.class)
    public void asFloatOutOfRange() {
        Parse.asFloat("1.40129846432481707e-46");
        Parse.asFloat("3.40282346638528860e+39");
    }

    @Test(expected=NumberFormatException.class)
    public void asFloatOverflow() {
        Parse.asFloat(1.40129846432481707e-46);
        Parse.asFloat(3.40282346638528860e+39);
    }

    @Test
    public void asDouble() {
        assertEquals(123.45d, Parse.asDouble("123.45"), 0d);
        assertEquals(123.45d, Parse.asDouble(123.45d), 0d);
        assertEquals(0d, Parse.asDouble("0"), 0d);
        assertEquals(0d, Parse.asDouble(0d), 0d);
        assertEquals(-123.45d, Parse.asDouble("-123.45"), 0d);
        assertEquals(-123.45d, Parse.asDouble(-123.45d), 0d);
        assertEquals(1.40129846432481707e-45d, Parse.asDouble("1.40129846432481707e-45"), 0d);
        assertEquals(1.40129846432481707e-45d, Parse.asDouble(1.40129846432481707e-45d), 0d);
        assertEquals(3.40282346638528860e+38d, Parse.asDouble("3.40282346638528860e+38"), 0d);
        assertEquals(3.40282346638528860e+38d, Parse.asDouble(3.40282346638528860e+38d), 0d);
    }

    @Test(expected=NumberFormatException.class)
    public void asDoubleOutOfRange() {
        Parse.asDouble("1.40129846432481707e-46");
        Parse.asDouble("3.40282346638528860e+39");
    }

    @Test(expected=NumberFormatException.class)
    public void asDoubleOverflow() {
        Parse.asDouble(1.40129846432481707e-46);
        Parse.asDouble(3.40282346638528860e+39);
    }

    @Test
    public void asBigInteger() {
        fail("Not yet implemented");
    }

    @Test
    public void asBigDecimal() {
        fail("Not yet implemented");
    }

    @Test
    public void asDate() {
        fail("Not yet implemented");
    }

    @Test
    public void asEnum() {
        assertSame(Continent.EUROPE, Parse.asEnum("EUROPE", Continent.class));
        assertSame(Continent.AFRICA, Parse.asEnum(3, Continent.class));
    }

    @Test
    public void asString() {
        fail("Not yet implemented");
    }

}