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

package de.cosmocode.commons.validation;

import java.util.Collection;
import java.util.Collections;
import java.util.NoSuchElementException;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;

import de.cosmocode.junit.Asserts;
import de.cosmocode.junit.UnitProvider;

/**
 * Abstract base class for {@link Rule} tests.
 *
 * @since 1.21
 * @author Willi Schoenborn
 * @param <T> generic rule type
 */
public abstract class AbstractRuleTest<T> implements UnitProvider<Rule<T>> {
    
    /**
     * Provides the null support state.
     *
     * @since 1.21
     * @return true if the rule under test is satisfied by null
     */
    protected abstract boolean satisfiedByNull();
    
    /**
     * Provides a default value for {@link Rule#find(Iterable, Object)} test.
     *
     * @since 1.21 
     * @return the default value for the find method
     */
    protected abstract T defaultValueForFind();
    
    /**
     * Provides valid inputs.
     *
     * @since 1.21
     * @return valid inputs
     */
    protected abstract Iterable<T> validInputs();
    
    /**
     * Provides at least on valid input.
     *
     * @since 1.21
     * @return at least one valid input
     */
    protected Iterable<T> mixedInputs() {
        final Collection<T> mixed = Lists.newArrayList();
        Iterables.addAll(mixed, validInputs());
        Iterables.addAll(mixed, invalidInputs());
        return mixed;
    }
    
    /**
     * Provides invalid inputs.
     *
     * @since 1.21
     * @return invalid inputs
     */
    protected abstract Iterable<T> invalidInputs();
    
    /**
     * Tests {@link Rule#apply(Object)} with a valid input.
     */
    @Test
    public void applyValid() {
        final Rule<T> unit = unit();
        for (T valid : validInputs()) {
            Assert.assertTrue(unit.apply(valid));
        }
    }

    /**
     * Tests {@link Rule#apply(Object)} with an invalid input.
     */
    @Test
    public void applyInvalid() {
        final Rule<T> unit = unit();
        for (T invalid : invalidInputs()) {
            Assert.assertFalse(unit.apply(invalid));
        }
    }
    
    /**
     * Tests {@link Rule#apply(Object)} with null.
     */
    @Test
    public void applyNull() {
        if (satisfiedByNull()) {
            Assert.assertTrue(unit().apply(null));
        } else {
            Assert.assertFalse(unit().apply(null));
        }
    }
    
    /**
     * Tests {@link Rule#checkElement(Object)} with a valid input.
     */
    @Test
    public void checkElementValid() {
        unit().checkElement(validInputs().iterator().next());
    }
    
    /**
     * Tests {@link Rule#checkElement(Object)} with an invalid input.
     */
    @Test(expected = RuntimeException.class)
    public void checkElementInvalid() {
        unit().checkElement(invalidInputs().iterator().next());
    }
    
    /**
     * Tests {@link Rule#checkElement(Object)} with null.
     */
    @Test
    public void checkElementNull() {
        if (satisfiedByNull()) {
            unit().checkElement(null);
        } else {
            try {
                final Rule<T> unit = unit();
                unit.checkElement(null);
                Assert.fail("Excepted " + unit + ".checkElement(null) to throw exception");
            /* CHECKSTYLE:OFF */
            } catch (RuntimeException expected) {
            /* CHECKSTYLE:ON */
                return;
            }
        }
    }
    
    /**
     * Tests {@link Rule#all(Iterable)} with {@link #validInputs()}.
     */
    @Test
    public void allValids() {
        Assert.assertTrue(unit().all(validInputs()));
    }
    
    /**
     * Tests {@link Rule#all(Iterable)} with {@link #mixedInputs()}.
     *
     * @since
     */
    @Test
    public void allMixed() {
        Assert.assertFalse(unit().all(mixedInputs()));
    }
    
    /**
     * Tests {@link Rule#all(Iterable)} with {@link #invalidInputs().
     */
    @Test
    public void allInvalids() {
        Assert.assertFalse(unit().all(invalidInputs()));
    }
    
    /**
     * Tests {@link Rule#all(Iterable)} with an empty iterable.
     */
    @Test
    public void allEmpty() {
        Assert.assertTrue(unit().all(Collections.<T>emptySet())); 
    }
    
    /**
     * Tests {@link Rule#all(Iterable)} with null.
     */
    @Test(expected = NullPointerException.class)
    public void allNull() {
        unit().all(null);
    }
    
    /**
     * Tests {@link Rule#any(Iterable)} with {@link #validInputs()}.
     */
    @Test
    public void anyValids() {
        Assert.assertTrue(unit().any(validInputs()));
    }
    
    /**
     * Tests {@link Rule#any(Iterable)} with {@link #mixedInputs()}.
     */
    @Test
    public void anyMixed() {
        Assert.assertTrue(unit().any(mixedInputs()));
    }
    
    /**
     * Tests {@link Rule#any(Iterable)} with {@link #invalidInputs()}.
     *
     * @since
     */
    @Test
    public void anyInvalids() {
        Assert.assertFalse(unit().any(invalidInputs()));
    }
    
    /**
     * Tests {@link Rule#any(Iterable)} with an empty iterable.
     */
    @Test
    public void anyEmpty() {
        Assert.assertFalse(unit().any(Collections.<T>emptySet()));
    }
    
    /**
     * Tests {@link Rule#any(Iterable)} with null.
     *
     * @since
     */
    @Test(expected = NullPointerException.class)
    public void anyNull() {
        unit().any(null);
    }
    
    /**
     * Tests {@link Rule#none(Iterable)} with {@link #validInputs()}.
     */
    @Test
    public void noneValids() {
        Assert.assertFalse(unit().none(validInputs()));
    }
    
    /**
     * Tests {@link Rule#none(Iterable)} with {@link #mixedInputs()}.
     */
    @Test
    public void noneMixed() {
        Assert.assertFalse(unit().none(mixedInputs()));
    }
    
    /**
     * Tests {@link Rule#none(Iterable)} with {@link #invalidInputs()}.
     */
    @Test
    public void noneInvalids() {
        Assert.assertTrue(unit().none(invalidInputs()));
    }
    
    /**
     * Tests {@link Rule#none(Iterable)} with an empty iterable.
     */
    @Test
    public void noneEmpty() {
        Assert.assertTrue(unit().none(Collections.<T>emptySet()));
    }
    
    /**
     * Tests {@link Rule#none(Iterable)} with null.
     */
    @Test(expected = NullPointerException.class)
    public void noneNull() {
        unit().none(null);
    }
    
    private void assertEquals(Iterable<T> expected, Iterable<T> actual) {
        Assert.assertTrue(
            "Expected " + expected + " to be equals to " + actual, 
            Iterables.elementsEqual(expected, actual)
        );
    }
    
    /**
     * Tests {@link Rule#filter(Iterable)} with {@link #validInputs()}.
     */
    @Test
    public void filterValids() {
        assertEquals(validInputs(), unit().filter(validInputs()));
    }
    
    /**
     * Tests {@link Rule#filter(Iterable)} with {@link #mixedInputs()}.
     */
    @Test
    public void filterMixed() {
        assertEquals(Iterables.filter(mixedInputs(), unit()), unit().filter(mixedInputs()));
    }
    
    /**
     * Tests {@link Rule#filter(Iterable)} with {@link #invalidInputs()}.
     */
    @Test
    public void filterInvalids() {
        Assert.assertTrue(Iterables.isEmpty(unit().filter(invalidInputs())));
    }
    
    /**
     * Tests {@link Rule#filter(Iterable)} with an empty iterable.
     */
    @Test
    public void filterEmpty() {
        Assert.assertTrue(Iterables.isEmpty(unit().filter(Collections.<T>emptySet())));
    }
    
    /**
     * Tests {@link Rule#filter(Iterable)} with null.
     */
    @Test(expected = NullPointerException.class)
    public void filterNull() {
        unit().filter(null);
    }
    
    /**
     * Tests {@link Rule#find(Iterable)} with {@link #validInputs()}.
     */
    @Test
    public void findValids() {
        Assert.assertEquals(validInputs().iterator().next(), unit().find(validInputs()));
    }

    /**
     * Tests {@link Rule#find(Iterable)} with {@link #mixedInputs()}.
     */
    @Test
    public void findMixed() {
        Assert.assertEquals(Iterables.find(mixedInputs(), unit()), unit().find(mixedInputs()));
    }
    
    /**
     * Tests {@link Rule#find(Iterable)} with {@link #invalidInputs()}.
     */
    @Test(expected = NoSuchElementException.class)
    public void findInvalids() {
        unit().find(invalidInputs());
    }
    
    /**
     * Tests {@link Rule#find(Iterable)} with an empty iterable.
     */
    @Test(expected = NoSuchElementException.class)
    public void findEmpty() {
        unit().find(Collections.<T>emptySet());
    }
    
    /**
     * Tests {@link Rule#find(Iterable)} with null.
     *
     * @since
     */
    @Test(expected = NullPointerException.class)
    public void findNull() {
        unit().find(null);
    }
    
    /**
     * Tests {@link Rule#find(Iterable, Object)} with {@link #validInputs()}.
     */
    @Test
    public void findDefaultValids() {
        Assert.assertSame(validInputs().iterator().next(), unit().find(validInputs()));
        Asserts.assertNotEquals(defaultValueForFind(), unit().find(validInputs(), defaultValueForFind()));
    }
    
    /**
     * Tests {@link Rule#find(Iterable, Object)} with {@link #mixedInputs()}.
     */
    @Test
    public void findDefaultMixed() {
        Assert.assertSame(Iterables.find(mixedInputs(), unit()), unit().find(mixedInputs()));
        Asserts.assertNotEquals(defaultValueForFind(), unit().find(mixedInputs(), defaultValueForFind()));
    }
    
    /**
     * Tests {@link Rule#find(Iterable, Object)} with {@link #invalidInputs()}.
     */
    @Test
    public void findDefaultInvalids() {
        Assert.assertEquals(defaultValueForFind(), unit().find(invalidInputs(), defaultValueForFind()));
        Assert.assertNull(unit().find(Collections.<T>emptySet(), null));
    }
    
    /**
     * Tests {@link Rule#find(Iterable, Object)} with an empty iterable.
     */
    @Test
    public void findDefaultEmpty() {
        Assert.assertEquals(defaultValueForFind(), unit().find(Collections.<T>emptySet(), defaultValueForFind()));
    }
    
    /**
     * Tests {@link Rule#find(Iterable, Object)} with a null iterable.
     */
    @Test(expected = NullPointerException.class)
    public void findDefaultNull() {
        unit().find(null, defaultValueForFind());
    }
    
    /**
     * Tests {@link Rule#removeIf(Iterable)} with {@link #validInputs()}.
     */
    @Test
    public void removeIfValid() {
        final Iterable<T> validInputs = validInputs();
        Assert.assertTrue(unit().removeIf(validInputs));
        Assert.assertTrue(Iterables.isEmpty(validInputs));
    }
    
    /**
     * Tests {@link Rule#removeIf(Iterable)} with {@link #mixedInputs()}.
     */
    @Test
    public void removeIfMixed() {
        final Iterable<T> expected = validInputs();
        Iterables.removeIf(expected, unit());
        final Iterable<T> actual = validInputs();
        Assert.assertTrue(unit().removeIf(actual));
        assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Rule#removeIf(Iterable)} with {@link #invalidInputs()}.
     */
    @Test
    public void removeIfInvalid() {
        final Iterable<T> invalidInputs = invalidInputs();
        Assert.assertFalse(unit().removeIf(invalidInputs));
        assertEquals(invalidInputs(), invalidInputs);
    }
    
    /**
     * Tests {@link Rule#removeIf(Iterable)} with an empty iterable.
     */
    @Test
    public void removeIfEmpty() {
        final Iterable<T> empty = Collections.emptySet();
        Assert.assertFalse(unit().removeIf(empty));
        Assert.assertTrue(Iterables.isEmpty(empty));
    }
    
    /**
     * Tests {@link Rule#removeIf(Iterable)} with null.
     */
    @Test(expected = NullPointerException.class)
    public void removeIfNull() {
        unit().removeIf(null);
    }
    
}
