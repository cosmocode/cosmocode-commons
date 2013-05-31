/**
 * Copyright 2010 - 2013 CosmoCode GmbH
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

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;
import com.google.common.collect.MapMaker;
import com.google.common.collect.Maps;
import com.google.common.collect.Ordering;

import javax.annotation.Nullable;
import java.io.Serializable;
import java.util.Map.Entry;
import java.util.Random;
import java.util.concurrent.ConcurrentMap;

/**
 * Implementation for {@link Orderings#random()}.
 * 
 * @since 1.9
 * @author Willi Schoenborn
 * @param <T> generic type parameter
 */
final class RandomOrdering<T> extends Ordering<T> implements Function<Entry<T, T>, Integer>, Serializable {
    
    private static final long serialVersionUID = 1463223861679378549L;

    /**
     * Reusable constant for "left-greater-right" to prevent autoboxing.
     */
    private static final Integer LEFT_IS_GREATER = 1;
    
    /**
     * Reusable constant for "right-greater-left" to prevent autoboxing.
     */
    private static final Integer RIGHT_IS_GREATER = -1;
    
    private final Random random = new Random();
    
    private final ConcurrentMap<Entry<T, T>, Integer> values = new MapMaker().makeComputingMap(this);

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
    public Integer apply(@Nullable Entry<T, T> entry) {
        Preconditions.checkNotNull(entry, "Entry");
        // whenever this function is being called neither (x,y) nor (y,x) has been compared yet
        
        final Integer value;
        final Integer inverted;
        
        if (random.nextInt(2) == 0) {
            value = LEFT_IS_GREATER;
            inverted = RIGHT_IS_GREATER;
        } else {
            value = RIGHT_IS_GREATER;
            inverted = LEFT_IS_GREATER;
        }
        
        // sgn(compare(x, y)) == -sgn(compare(y, x))
        values.putIfAbsent(Maps.immutableEntry(entry.getValue(), entry.getKey()), inverted);
        
        return value;
    }
    
    @Override
    public String toString() {
        return "Orderings.random()";
    }
    
}
