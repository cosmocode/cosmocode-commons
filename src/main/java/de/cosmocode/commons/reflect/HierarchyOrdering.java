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

package de.cosmocode.commons.reflect;

import java.util.Comparator;

import com.google.common.base.Preconditions;
import com.google.common.collect.Ordering;

/**
 * Implementation for {@link Reflection#orderByHierarchy(Comparator)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
final class HierarchyOrdering extends Ordering<Class<?>> {
    
    private final Comparator<Class<?>> comparator;
    
    public HierarchyOrdering(Comparator<Class<?>> comparator) {
        this.comparator = Preconditions.checkNotNull(comparator, "Comparator");
    }
    
    @Override
    public int compare(Class<?> left, Class<?> right) {
        if (left.equals(right)) {
            return 0;
        } else if (left.isAssignableFrom(right)) {
            return 1;
        } else if (right.isAssignableFrom(left)) {
            return -1;
        } else {
            return comparator.compare(left, right);
        }
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof HierarchyOrdering) {
            final HierarchyOrdering other = HierarchyOrdering.class.cast(that);
            return comparator.equals(other.comparator);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return comparator.hashCode();
    }
    
    @Override
    public String toString() {
        return "Reflection.orderByHierarchy(" + comparator + ")";
    }
    
}
