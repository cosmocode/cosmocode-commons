package de.cosmocode.commons;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
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
        Assert.assertFalse(StringUtility.isNumeric(null));
    }

    @Test
    public void isNumericEmpty() {
        Assert.assertFalse(StringUtility.isNumeric(""));
    }

    @Test
    public void isNumericWhitespaces() {
        Assert.assertFalse(StringUtility.isNumeric("   \t"));
    }

    @Test
    public void isNumericAlpha() {
        Assert.assertFalse(StringUtility.isNumeric("abc"));
        Assert.assertFalse(StringUtility.isNumeric("125543b232"));
    }
    
    @Test
    public void isNumeric() {
        Assert.assertTrue(StringUtility.isNumeric("123"));
    }
    
    @Test
    public void joinEmpty() {
        final String joined = StringUtility.join(list, DEFAULT_WALKER);
        Assert.assertEquals(joined, "");
    }
    
    @Test
    public void join() {
        list.add("hello");
        list.add(null);
        list.add(0.0);
        final String joined = StringUtility.join(list, " ", DEFAULT_WALKER);
        Assert.assertEquals(joined, "hello null 0.0");
    }
    
}
