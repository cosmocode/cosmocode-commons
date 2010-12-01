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

package de.cosmocode.collections;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.collect.Lists;

import de.cosmocode.junit.UnitProvider;

/**
 * Tests {@link CyclingList}.
 *
 * @since 1.20
 * @author Willi Schoenborn
 */
public final class CyclingListTest implements UnitProvider<List<String>> {

    @Override
    public List<String> unit() {
        return MoreLists.cycle(Lists.newArrayList("a", "b", "c", "d", "e"));
    }
    
    private List<String> unitAndAdd(int index, String element) {
        final List<String> unit = unit();
        unit.add(index, element);
        return unit;
    }
    
    /**
     * Tests {@link CyclingList#add(int, Object)}.
     *
     * @since 1.20
     */
    @Test
    public void add() {
        Assert.assertEquals(Arrays.asList("a", "b", "c", "d", "e", "f"), unitAndAdd(5, "f"));
        Assert.assertEquals(Arrays.asList("a", "f", "b", "c", "d", "e"), unitAndAdd(6, "f"));
        Assert.assertEquals(Arrays.asList("a", "b", "f", "c", "d", "e"), unitAndAdd(17, "f"));
        Assert.assertEquals(Arrays.asList("a", "b", "f", "c", "d", "e"), unitAndAdd(-8, "f"));
    }
    
    private List<String> unitAndAddAll(int index, String... elements) {
        final List<String> unit = unit();
        unit.addAll(index, Arrays.asList(elements));
        return unit;
    }
    
    /**
     * Tests {@link CyclingList#addAll(int, Collection)}.
     *
     * @since 1.20
     */
    @Test
    public void addAll() {
        Assert.assertEquals(Arrays.asList("a", "b", "c", "d", "e", "f", "g", "h"), unitAndAddAll(5, "f", "g", "h"));
        Assert.assertEquals(Arrays.asList("a", "f", "g", "h", "b", "c", "d", "e"), unitAndAddAll(6, "f", "g", "h"));
        Assert.assertEquals(Arrays.asList("a", "b", "f", "g", "h", "c", "d", "e"), unitAndAddAll(17, "f", "g", "h"));
        Assert.assertEquals(Arrays.asList("a", "b", "f", "g", "h", "c", "d", "e"), unitAndAddAll(-8, "f", "g", "h"));
    }
    
    /**
     * Tests {@link CyclingList#get(int)}.
     * 
     * @since 1.20
     */
    @Test
    public void get() {
        Assert.assertEquals("a", unit().get(5));
        Assert.assertEquals("c", unit().get(7));
        Assert.assertEquals("d", unit().get(18));
        Assert.assertEquals("e", unit().get(-1));
        Assert.assertEquals("b", unit().get(-9));
    }
    
    /**
     * Tests {@link CyclingList#listIterator(int)}.
     *
     * @since
     */
    @Test
    public void listIterator() {
        Assert.assertEquals("a", unit().listIterator(5).next());
        Assert.assertEquals("c", unit().listIterator(7).next());
        Assert.assertEquals("d", unit().listIterator(18).next());
        Assert.assertEquals("e", unit().listIterator(-1).next());
        Assert.assertEquals("b", unit().listIterator(-9).next());
        
        Assert.assertEquals("a", unit().listIterator(6).previous());
        Assert.assertEquals("b", unit().listIterator(7).previous());
        Assert.assertEquals("c", unit().listIterator(18).previous());
        Assert.assertEquals("d", unit().listIterator(-1).previous());
        Assert.assertEquals("a", unit().listIterator(-9).previous());
    }
    
    /**
     * Tests {@link CyclingList#remove(int)}.
     *
     * @since 1.20
     */
    @Test
    public void remove() {
        Assert.assertEquals("a", unit().remove(5));
        Assert.assertEquals("c", unit().remove(7));
        Assert.assertEquals("d", unit().remove(18));
        Assert.assertEquals("e", unit().remove(-1));
        Assert.assertEquals("b", unit().remove(-9));
    }
    
    private List<String> unitAndSet(int index, String element) {
        final List<String> unit = unit();
        unit.set(index, element);
        return unit;
    }
    
    /**
     * Tests {@link CyclingList#set(int, Object)}.
     *
     * @since 1.20
     */
    @Test
    public void set() {
        Assert.assertEquals(Arrays.asList("x", "b", "c", "d", "e"), unitAndSet(5, "x"));
        Assert.assertEquals(Arrays.asList("a", "b", "x", "d", "e"), unitAndSet(7, "x"));
        Assert.assertEquals(Arrays.asList("a", "b", "c", "x", "e"), unitAndSet(18, "x"));
        Assert.assertEquals(Arrays.asList("a", "b", "c", "d", "x"), unitAndSet(-1, "x"));
        Assert.assertEquals(Arrays.asList("a", "x", "c", "d", "e"), unitAndSet(-9, "x"));
    }
    
    /**
     * Tests {@link CyclingList#subList(int, int)}.
     *
     * @since 1.20
     */
    @Test
    public void subList() {
        Assert.assertEquals(Arrays.asList("b", "c", "d"), unit().subList(1, -1));
    }
    
}
