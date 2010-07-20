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

import java.util.Collections;
import java.util.List;
import java.util.Random;

import com.google.common.collect.Lists;
import com.google.common.collect.Ordering;

/**
 * Implementation for {@link Orderings#random()}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
final class RandomOrdering extends Ordering<Object> {
    
    public static final Ordering<Object> INSTANCE = new RandomOrdering();

    private final Random random = new Random();
    
    private RandomOrdering() {
        
    }
    
    @Override
    public int compare(Object left, Object right) {
        // generate a random number between -1 and 1 (both inclusive)
        return random.nextInt(3) - 1;
    }
    
    @Override
    public <E> List<E> sortedCopy(Iterable<E> iterable) {
        final List<E> list = Lists.newArrayList(iterable);
        // let shuffle do the hard work
        Collections.shuffle(list, random);
        return list;
    }
    
    @Override
    public boolean isOrdered(Iterable<? extends Object> iterable) {
        // everything can be considered "ordered" randomly
        return true;
    }
    
    @Override
    public boolean isStrictlyOrdered(Iterable<? extends Object> iterable) {
        // everything can be considered "ordered" randomly
        return true;
    }
    
    @Override
    public Ordering<Object> reverse() {
        // no need to do something
        return this;
    }
    
    @Override
    public int binarySearch(List<? extends Object> sortedList, Object key) {
        // can't be optimized
        return sortedList.indexOf(key);
    }
    
    @Override
    public String toString() {
        return "Orderings.random()";
    }
    
}
