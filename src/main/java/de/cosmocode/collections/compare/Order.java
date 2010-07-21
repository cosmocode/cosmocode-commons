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

package de.cosmocode.collections.compare;

import java.util.Collections;
import java.util.Comparator;

import com.google.common.collect.Ordering;

/**
 * A utility class used to simplify
 * ascending and descending ordering of comparators.
 * 
 * @author Willi Schoenborn
 */
public enum Order {

    ASC {

        @Override
        public <E> Comparator<E> apply(Comparator<E> comparator) {
            return comparator;
        }
        
    },
    
    DESC {
        
        @Override
        public <E> Comparator<E> apply(Comparator<E> comparator) {
            if (comparator instanceof Ordering<?>) {
                final Ordering<E> ordering = (Ordering<E>) comparator;
                // might be optimized
                return ordering.reverse();
            } else {
                return Collections.reverseOrder(comparator);
            }
        }
        
    };
    
    /**
     * Applies the ordering represented by the current instance to
     * the given comparator and returns either
     * the same instance, in case of {@code Order.ASC}, or
     * an inverted instance, {@code Order.DESC}.
     * 
     * @param <E> the element type of the given comparator
     * @param comparator the comparator this ordering will be apllied to
     * @return a comparator using the same sorting criterias as the given one, but with the applied order 
     */
    public abstract <E> Comparator<E> apply(Comparator<E> comparator);
    
}
