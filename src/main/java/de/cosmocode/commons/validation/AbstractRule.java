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

import de.cosmocode.commons.Conditions;

/**
 * Abstract {@link Rule} implementation.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <T> generic parameter type.
 */
public abstract class AbstractRule<T> implements Rule<T> {

    @Override
    public T checkElement(T element) {
        return Conditions.checkArgument(this, element);
    }

    @Override
    public Rule<T> and(Rule<? super T> that) {
        return new AndRule<T>(this, that);
    }

    @Override
    public Rule<T> not() {
        return new NotRule<T>(this);
    }

    @Override
    public Rule<T> or(Rule<? super T> that) {
        return new OrRule<T>(this, that);
    }

    @Override
    public Rule<T> xor(Rule<? super T> that) {
        return new XorRule<T>(this, that);
    }

    @Override
    public <S> Rule<S> compose(Function<? super S, ? extends T> function) {
        return new ComposedRule<T, S>(this, function);
    }

    @Override
    public abstract String toString();

}
