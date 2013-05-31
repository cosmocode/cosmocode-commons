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

import com.google.common.collect.Ordering;

/**
 * Utility class for {@link Ordering}s.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
public final class Orderings {

    private Orderings() {
        
    }

    /**
     * Returns a comparator which produces random but repeatable 
     * comparision results when invoked with the same arguments.
     * The returned ordering is reflexive and anti-symetric
     * but not transitive. This means:
     * {@code random.compare(x, x)} will always return true and
     * {@code random.compare(x, y)} will always be {@code -random.compare(y, x)}.
     * 
     * <p>
     *   The common use case for this ordering is:<br />
     *   {@code grouping.compound(Orderings.random())}<br />
     *   where grouping is a predefined ordering which sorts (or groups)
     *   first. The random ordering will then be applied to the resulting
     *   list. This results in shuffled groups, without the groups being 
     *   changed.
     * </p>
     * 
     * @param <T> generic parameter type
     * @return a comparator which returns randomly generated results
     */
    public static <T> Ordering<T> random() {
        return new RandomOrdering<T>();
    }
    
}
