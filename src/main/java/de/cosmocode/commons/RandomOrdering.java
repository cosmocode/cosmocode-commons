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

import java.util.Random;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentMap;

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

/**
 * Implementation for {@link Orderings#random()}.
 * 
 * @since 1.9
 * @author Willi Schoenborn
 * @param <T> generic type parameter
 */
final class RandomOrdering<T> extends Ordering<T> {
    
    /**
     * Reusable constant for "left-greater-right" to prevent autoboxing.
     */
    private static final Integer ONE = 1;
    
    /**
     * Reusable constant for "right-greater-left" to prevent autoboxing.
     */
    private static final Integer MINUS_ONE = -1;
    
    private final ConcurrentMap<Entry<T, T>, Integer> values;
    
    public RandomOrdering() {
        this.values = new MapMaker().makeComputingMap(new Function<Entry<T, T>, Integer>() {
            
            private final Random random = new Random();
            
            @Override
            public Integer apply(Entry<T, T> entry) {
                final Entry<T, T> reverseEntry = Maps.immutableEntry(
                    entry.getValue(), entry.getKey()
                );
                if (values.containsKey(reverseEntry)) {
                    // sgn(compare(x, y)) == -sgn(compare(y, x))
                    return values.get(reverseEntry) == ONE ? MINUS_ONE : ONE;
                } else {
                    // return -1 or 1 with a 50% possibility (each)
                    return random.nextInt(2) == 0 ? ONE : MINUS_ONE;
                }
            }
            
        });
    }
    
    @Override
    public int compare(T left, T right) {
        if (Objects.equal(left, right)) {
            // compare(x, y)==0) == (x.equals(y)
            return 0;
        } else {
            // will either return a cached value or compute a new one
            return values.get(Maps.immutableEntry(left, right));
        }
    }
    
    @Override
    public String toString() {
        return "Orderings.random()";
    }
    
}
