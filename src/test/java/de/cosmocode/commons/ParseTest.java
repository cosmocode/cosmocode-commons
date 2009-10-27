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
        fail("Not yet implemented");
    }

    @Test
    public void asByte() {
        fail("Not yet implemented");
    }

    @Test
    public void asShort() {
        fail("Not yet implemented");
    }

    @Test
    public void asInt() {
        fail("Not yet implemented");
    }

    @Test
    public void asLong() {
        fail("Not yet implemented");
    }

    @Test
    public void asFloat() {
        fail("Not yet implemented");
    }

    @Test
    public void asDouble() {
        fail("Not yet implemented");
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