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
import com.google.common.base.Predicate;
import com.google.common.collect.Constraint;

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
        if (apply(element)) {
            return transform(element);
        } else {
            throw new IllegalArgumentException(String.format("%s does not satisfy %s", element, this));
        }
    }
    
    /**
     * A hook called by {@link Constraint#checkElement(Object)} in case the supplied
     * element satisifies {@link Predicate#apply(Object)} of this instance.
     * 
     * <p>
     *   The default implementation just returns the parameter.
     * </p>
     * 
     * @since 1.9
     * @param element the supplied element
     * @return element
     */
    protected T transform(T element) {
        return element;
    }

    @Override
    public <S extends T> Rule<S> and(Rule<? super T> that) {
        return new AndRule<S>(this, that);
    }

    @Override
    public <S extends T> Rule<S> or(Rule<? super T> that) {
        return new OrRule<S>(this, that);
    }

    @Override
    public <S extends T> Rule<S> xor(Rule<? super T> that) {
        return new XorRule<S>(this, that);
    }

    @Override
    public Rule<T> not() {
        return new NotRule<T>(this);
    }

    @Override
    public <S> Rule<S> compose(Function<? super S, ? extends T> function) {
        return new ComposedRule<T, S>(this, function);
    }

    @Override
    public Rule<T> transform(Function<? super T, ? extends T> function) {
        return new TransformingRule<T>(this, function);
    }
    
    @Override
    public abstract String toString();

}
