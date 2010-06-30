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

import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * Implementation of {@link Rule#xor(Rule)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <T> generic parameter type
 */
final class XorRule<T> extends AbstractRule<T> {

    private final Rule<? super T> left;
    private final Rule<? super T> right;
    
    public XorRule(Rule<? super T> left, Rule<? super T> right) {
        this.left = Preconditions.checkNotNull(left, "Left");
        this.right = Preconditions.checkNotNull(right, "Right");
    }

    @Override
    public boolean apply(T input) {
        return left.apply(input) ^ right.apply(input);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof XorRule<?>) {
            final XorRule<?> other = XorRule.class.cast(that);
            return left.equals(other.left) && right.equals(other.right);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(left, right);
    }

    @Override
    public String toString() {
        return left + ".xor(" + right + ")";
    }

}
