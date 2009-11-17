package de.cosmocode.commons;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class StringsTest {
    
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
        Assert.assertFalse(Strings.isNumeric(null));
    }

    @Test
    public void isNumericEmpty() {
        Assert.assertFalse(Strings.isNumeric(""));
    }

    @Test
    public void isNumericWhitespaces() {
        Assert.assertFalse(Strings.isNumeric("   \t"));
    }

    @Test
    public void isNumericAlpha() {
        Assert.assertFalse(Strings.isNumeric("abc"));
        Assert.assertFalse(Strings.isNumeric("125543b232"));
    }
    
    @Test
    public void isNumeric() {
        Assert.assertTrue(Strings.isNumeric("123"));
    }
    
    @Test
    public void joinEmpty() {
        final String joined = Strings.join(list, DEFAULT_WALKER);
        Assert.assertEquals(joined, "");
    }
    
    @Test
    public void join() {
        list.add("hello");
        list.add(null);
        list.add(0.0);
        final String joined = Strings.join(list, " ", DEFAULT_WALKER);
        Assert.assertEquals(joined, "hello null 0.0");
    }
    
}
