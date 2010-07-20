/**
 * Copyright 2010 CosmoCode GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.cosmocode.commons;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

/**
 * Tests the {@link Strings} class.
 *
 * @author Willi Schoenborn
 */
public class StringsTest {
    
    @SuppressWarnings("deprecation")
    private static final JoinWalker<Object> DEFAULT_WALKER = new JoinWalker<Object>() {
        
        @Override
        public String walk(Object t) {
            return t == null ? null : t.toString();
        }
    };
    
    /**
     * Tests {@link Strings#isNumeric(String)} with null.
     */
    @Test
    public void isNumericNull() {
        Assert.assertFalse(Strings.isNumeric(null));
    }
    
    /**
     * Tests {@link Strings#isNumeric(String)} with an empty String.
     */
    @Test
    public void isNumericEmpty() {
        Assert.assertFalse(Strings.isNumeric(""));
    }

    /**
     * Tests {@link Strings#isNumeric(String)} with whitespaces.
     */
    @Test
    public void isNumericWhitespaces() {
        Assert.assertFalse(Strings.isNumeric("   \t"));
    }

    /**
     * Tests {@link Strings#isNumeric(String)} with alphanumeric characters.
     */
    @Test
    public void isNumericAlpha() {
        Assert.assertFalse(Strings.isNumeric("abc"));
        Assert.assertFalse(Strings.isNumeric("125543b232"));
    }

    /**
     * Tests {@link Strings#isNumeric(String)} with digits.
     */
    @Test
    public void isNumeric() {
        Assert.assertTrue(Strings.isNumeric("123"));
    }

    /**
     * Tests {@link Strings#join(java.util.Collection, JoinWalker)} with
     * an empty list.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void joinEmpty() {
        final List<Object> list = Lists.newArrayList();
        final String joined = Strings.join(list, DEFAULT_WALKER);
        Assert.assertEquals(joined, "");
    }

    /**
     * Tests {@link Strings#join(java.util.Collection, JoinWalker)} with
     * an non-empty list.
     */
    @SuppressWarnings("deprecation")
    @Test
    public void join() {
        final List<Object> list = Lists.newArrayList();
        list.add("hello");
        list.add(null);
        list.add(0.0);
        final String joined = Strings.join(list, " ", DEFAULT_WALKER);
        Assert.assertEquals(joined, "hello null 0.0");
    }

    /**
     * Tests {@link Strings#checkNotEmpty(String)} with null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkNotEmptyOfNull() {
        Strings.checkNotEmpty(null);
    }

    /**
     * Tests {@link Strings#checkNotEmpty(String)} with "".
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkNotEmptyOfEmpty() {
        Strings.checkNotEmpty("");
    }

    /**
     * Tests {@link Strings#checkNotEmpty(String)} with "   ".
     */
    @Test
    public void checkNotEmptyOfBlank() {
        final String expected = "   ";
        final String actual = Strings.checkNotEmpty(expected);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Strings#checkNotEmpty(String)} with "blubb".
     */
    @Test
    public void checkNotEmptyOfValid() {
        final String expected = "blubb";
        final String actual = Strings.checkNotEmpty(expected);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Strings#checkNotBlank(String)} with null.
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkNotBlankOfNull() {
        Strings.checkNotBlank(null);
    }

    /**
     * Tests {@link Strings#checkNotBlank(String)} with "".
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkNotBlankOfEmpty() {
        Strings.checkNotBlank("");
    }

    /**
     * Tests {@link Strings#checkNotBlank(String)} with "   ".
     */
    @Test(expected = IllegalArgumentException.class)
    public void checkNotBlankOfBlank() {
        Strings.checkNotBlank("   ");
    }

    /**
     * Tests {@link Strings#checkNotBlank(String)} with "blubb".
     */
    @Test
    public void checkNotBlankOfValid() {
        final String actual = Strings.checkNotBlank("  blubb  ");
        Assert.assertEquals("  blubb  ", actual);
    }
    
}
