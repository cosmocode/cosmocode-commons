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
import com.google.common.collect.ForwardingObject;

/**
 * Abstract decorator for {@link Rule}s.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <T> generic parameter type
 */
abstract class ForwardingRule<T> extends ForwardingObject implements Rule<T> {

    @Override
    protected abstract Rule<T> delegate();

    @Override
    public boolean apply(T input) {
        return delegate().apply(input);
    }

    @Override
    public T checkElement(T element) {
        return delegate().checkElement(element);
    }
    
    @Override
    public <S extends T> Rule<S> and(Rule<? super T> that) {
        return delegate().and(that);
    }

    @Override
    public <S extends T> Rule<S> or(Rule<? super T> that) {
        return delegate().or(that);
    }

    @Override
    public <S extends T> Rule<S> xor(Rule<? super T> that) {
        return delegate().xor(that);
    }

    @Override
    public <S extends T> Rule<S> not() {
        return delegate().not();
    }

    @Override
    public <S> Rule<S> compose(Function<? super S, ? extends T> function) {
        return delegate().compose(function);
    }
    
}
