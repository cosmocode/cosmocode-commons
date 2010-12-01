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

import com.google.common.base.Preconditions;

/**
 * Implementation of {@link Rule#negate()}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <T> generic parameter type
 */
final class NegatedRule<T> extends AbstractRule<T> {

    private final Rule<T> rule;
    
    public NegatedRule(Rule<T> rule) {
        this.rule = Preconditions.checkNotNull(rule, "Rule");
    }
    
    @Override
    public boolean apply(T input) {
        return !rule.apply(input);
    };
    
    @Override
    public Rule<T> negate() {
        return rule;
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof NegatedRule<?>) {
            final NegatedRule<?> other = NegatedRule.class.cast(that);
            return rule.equals(other.rule);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return -rule.hashCode();
    }
    
    @Override
    public String toString() {
        return rule + ".negate()";
    }

}
