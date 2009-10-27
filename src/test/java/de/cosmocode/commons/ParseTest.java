package de.cosmocode.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Calendar;
import java.util.Date;

import org.junit.Test;

public class ParseTest {

    @Test
    public void asBoolean() {
        assertTrue(Parse.asBoolean(true));
        assertTrue(Parse.asBoolean(Boolean.TRUE));
        assertTrue(Parse.asBoolean("true"));
        assertTrue(Parse.asBoolean("TrUe"));
        
        assertFalse(Parse.asBoolean(false));
        assertFalse(Parse.asBoolean(Boolean.FALSE));
        assertFalse(Parse.asBoolean("false"));
        assertFalse(Parse.asBoolean("FalSe"));
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
    public void asByteOutOfRange1() {
        Parse.asByte("-129");
    }
        
    @Test(expected=NumberFormatException.class)
    public void asByteOutOfRange2() {
        Parse.asByte("-99999");
    }

    @Test(expected=NumberFormatException.class)
    public void asByteOutOfRange3() {
        Parse.asByte("128");
    }
    
    @Test(expected=NumberFormatException.class)
    public void asByteOutOfRange4() {
        Parse.asByte("99999");
    }        

    @Test(expected=NumberFormatException.class)
    public void asByteOverflow1() {
        Parse.asByte(128);
    }

    @Test(expected=NumberFormatException.class)
    public void asByteOverflow2() {
        Parse.asByte(99999);
    }

    @Test(expected=NumberFormatException.class)
    public void asByteOverflow3() {
        Parse.asByte(-129);
    }

    @Test(expected=NumberFormatException.class)
    public void asByteOverflow4() {
        Parse.asByte(-99999);
    }
    
    @Test
    public void asByteDefault() {
        assertEquals(66, Parse.asByte(128, (byte) 66));
        assertEquals(-66, Parse.asByte(99999, (byte) -66));
        assertEquals(-66, Parse.asByte(-129, (byte) -66));
        assertEquals(66, Parse.asByte(-99999, (byte) 66));
        assertEquals(66, Parse.asByte("128", (byte) 66));
        assertEquals(-66, Parse.asByte("99999", (byte) -66));
        assertEquals(-66, Parse.asByte("-129", (byte) -66));
        assertEquals(66, Parse.asByte("-99999", (byte) 66));
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
    public void asShortOutOfRange1() {
        Parse.asShort("32768");
    }

    @Test(expected=NumberFormatException.class)
    public void asShortOutOfRange2() {
        Parse.asShort("99999");
    }

    @Test(expected=NumberFormatException.class)
    public void asShortOutOfRange3() {
        Parse.asShort("-32769");
    }

    @Test(expected=NumberFormatException.class)
    public void asShortOutOfRange4() {
        Parse.asShort("-99999");
    }
    
    @Test(expected=NumberFormatException.class)
    public void asShortOverflow1() {
        Parse.asShort(32768);
    }

    @Test(expected=NumberFormatException.class)
    public void asShortOverflow2() {
        Parse.asShort(99999);
    }

    @Test(expected=NumberFormatException.class)
    public void asShortOverflow3() {
        Parse.asShort(-32769);
    }

    @Test(expected=NumberFormatException.class)
    public void asShortOverflow4() {
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
    public void asCharOutOfRange1() {
        Parse.asChar("65536");
    }

    @Test(expected=NumberFormatException.class)
    public void asCharOutOfRange2() {
        Parse.asChar("99999");
    }

    @Test(expected=NumberFormatException.class)
    public void asCharOutOfRange3() {
        Parse.asChar("-1");
    }

    @Test(expected=NumberFormatException.class)
    public void asCharOutOfRange4() {
        Parse.asChar("-99999");
    }

    @Test(expected=NumberFormatException.class)
    public void asCharOverflow1() {
        Parse.asChar(65536);
    }
    
    @Test(expected=NumberFormatException.class)
    public void asCharOverflow2() {
        Parse.asChar(99999);
    }
        
    @Test(expected=NumberFormatException.class)
    public void asCharOverflow3() {
        Parse.asChar(-1);
    }
        
    @Test(expected=NumberFormatException.class)
    public void asCharOverflow4() {
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
    public void asIntOutOfRange1() {
        Parse.asInt("-2147483649");
    }
    
    @Test(expected=NumberFormatException.class)
    public void asIntOutOfRange2() {
        Parse.asInt("2147483648");
    }

    @Test(expected=NumberFormatException.class)
    public void asIntOverflo1w() {
        Parse.asInt(-2147483649l);
    }
    
    @Test(expected=NumberFormatException.class)
    public void asIntOverflow2() {
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
    public void asLongOutOfRange1() {
        Parse.asInt("-2147483650");
    }
    
    @Test(expected=NumberFormatException.class)
    public void asLongOutOfRange2() {
        Parse.asInt("-9999999999");
    }
    
    @Test(expected=NumberFormatException.class)
    public void asLongOutOfRange3() {
        Parse.asInt("2147483649");
    }
    
    @Test(expected=NumberFormatException.class)
    public void asLongOutOfRange4() {
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
    public void asFloatOutOfRange1() {
        Parse.asFloat("1.40129846432481707e-46");
    }
    
    @Test(expected=NumberFormatException.class)
    public void asFloatOutOfRange2() {
        Parse.asFloat("3.40282346638528860e+39");
    }

    @Test(expected=NumberFormatException.class)
    public void asFloatOverflow1() {
        Parse.asFloat(1.40129846432481707e-46);
    }
    
    @Test(expected=NumberFormatException.class)
    public void asFloatOverflow2() {
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
        assertEquals(4.94065645841246544e-324d, Parse.asDouble("4.94065645841246544e-324"), 0d);
        assertEquals(4.94065645841246544e-324d, Parse.asDouble(4.94065645841246544e-324d), 0d);
        assertEquals(1.79769313486231570e+308d, Parse.asDouble("1.79769313486231570e+308"), 0d);
        assertEquals(1.79769313486231570e+308d, Parse.asDouble(1.79769313486231570e+308d), 0d);
    }

    @Test
    public void asBigInteger() {
        assertEquals(new BigInteger("123"), Parse.asBigInteger(123));
        assertEquals(new BigInteger("123"), Parse.asBigInteger("123"));
        assertEquals(new BigInteger("0"), Parse.asBigInteger(0));
        assertEquals(new BigInteger("0"), Parse.asBigInteger("0"));
        assertEquals(new BigInteger("-123"), Parse.asBigInteger(-123));
        assertEquals(new BigInteger("-123"), Parse.asBigInteger("-123"));
    }

    @Test
    public void asBigDecimal() {
        assertEquals(new BigDecimal("123.45"), Parse.asBigDecimal(123.45));
        assertEquals(new BigDecimal("123.45"), Parse.asBigDecimal("123.45"));
        assertEquals(new BigDecimal("0.0"), Parse.asBigDecimal(0));
        assertEquals(new BigDecimal("0"), Parse.asBigDecimal("0"));
        assertEquals(new BigDecimal("-123.45"), Parse.asBigDecimal(-123.45));
        assertEquals(new BigDecimal("-123.45"), Parse.asBigDecimal("-123.45"));
    }

    @Test
    public void asDate() {
        assertSame(null, Parse.asDate(null));
        
        final Date date = new Date();
        assertEquals(date, Parse.asDate(date));
        assertEquals(date, Parse.asDate(date.getTime()));
        assertEquals(date, Parse.asDate(Long.toString(date.getTime())));
        
        final Calendar calendar = Calendar.getInstance();
        assertEquals(calendar.getTime(), Parse.asDate(calendar));
        assertEquals(calendar.getTime(), Parse.asDate(calendar.getTime()));
        assertEquals(calendar.getTime(), Parse.asDate(calendar.getTime().getTime()));
        assertEquals(calendar.getTime(), Parse.asDate(Long.toString(calendar.getTime().getTime())));
    }

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
    public void asEnum() {
        final Continent[] continents = Continent.values();
        for (int i=0; i<continents.length; i++) {
            final Continent continent = continents[i];
            assertSame(continent, Parse.asEnum(continent.name(), Continent.class));
            assertSame(continent, Parse.asEnum(continent.name().toLowerCase(), Continent.class));
            assertSame(continent, Parse.asEnum(i, Continent.class));
        }
    }

    @Test
    public void asString() {
        assertSame(null, Parse.asString(null));
        
        assertEquals("test", Parse.asString(new String("test")));
        assertEquals("123", Parse.asString(new String("123")));
        assertEquals("", Parse.asString(new String("")));
        
        assertEquals("true", Parse.asString(true));
        assertEquals("123", Parse.asString(123));
        assertEquals(Integer.toString(Integer.MAX_VALUE), Parse.asString(Integer.MAX_VALUE));
    }

}