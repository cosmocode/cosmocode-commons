package de.cosmocode.commons;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

public class StringUtilityTest {
    
    private static final JoinWalker<Object> DEFAULT_WALKER = new JoinWalker<Object>() {
        
        @Override
        public String walk(Object t) {
            return t == null ? null : t.toString();
        }
    };
    
    private List<Object> list;
    
    @Before
    public void prepare() {
        list = new ArrayList<Object>();
    }

    @Test
    public void isNumericNull() {
        assertFalse(StringUtility.isNumeric(null));
    }

    @Test
    public void isNumericEmpty() {
        assertFalse(StringUtility.isNumeric(""));
    }

    @Test
    public void isNumericWhitespaces() {
        assertFalse(StringUtility.isNumeric("   \t"));
    }

    @Test
    public void isNumericAlpha() {
        assertFalse(StringUtility.isNumeric("abc"));
        assertFalse(StringUtility.isNumeric("125543b232"));
    }
    
    @Test
    public void isNumeric() {
        assertTrue(StringUtility.isNumeric("123"));
    }
    
    @Test
    public void joinEmpty() {
        final String joined = StringUtility.join(list, DEFAULT_WALKER);
        assertEquals(joined, "");
    }
    
    @Test
    public void join() {
        list.add("hello");
        list.add(null);
        list.add(0.0);
        final String joined = StringUtility.join(list, " ", DEFAULT_WALKER);
        assertEquals(joined, "hello null 0.0");
    }
    
}