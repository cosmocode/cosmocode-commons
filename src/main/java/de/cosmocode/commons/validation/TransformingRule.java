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

import com.google.common.base.Function;
import com.google.common.base.Objects;
import com.google.common.base.Preconditions;

/**
 * Implementation of {@link Rule#transform(Function)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <T> generic parameter type
 */
final class TransformingRule<T> extends AbstractRule<T> {

    private final Rule<T> rule;
    private final Function<? super T, ? extends T> function;
    
    public TransformingRule(Rule<T> rule, Function<? super T, ? extends T> function) {
        this.rule = Preconditions.checkNotNull(rule, "Rule");
        this.function = Preconditions.checkNotNull(function, "Function");
    }

    @Override
    protected T transform(T element) {
        return function.apply(element);
    };
    
    @Override
    public boolean apply(T input) {
        return rule.apply(input);
    };
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof TransformingRule<?>) {
            final TransformingRule<?> other = TransformingRule.class.cast(that);
            return rule.equals(other.rule) && function.equals(other.function);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return Objects.hashCode(rule, function);
    }
    
    @Override
    public String toString() {
        return rule + ".transform(" + function + ")";
    }
    
}
