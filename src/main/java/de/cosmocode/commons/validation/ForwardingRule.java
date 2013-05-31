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
package de.cosmocode.commons.validation;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
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
    public boolean all(Iterable<? extends T> inputs) {
        return delegate().all(inputs);
    }

    @Override
    public boolean any(Iterable<? extends T> inputs) {
        return delegate().any(inputs);
    }

    @Override
    public boolean none(Iterable<? extends T> inputs) {
        return delegate().none(inputs);
    }

    @Override
    public Iterable<T> filter(Iterable<T> unfiltered) {
        return delegate().filter(unfiltered);
    }

    @Override
    public T find(Iterable<? extends T> iterable) {
        return delegate().find(iterable);
    }

    @Override
    public T find(Iterable<T> iterable, T defaultValue) {
        return delegate().find(iterable, defaultValue);
    }

    @Override
    public boolean removeIf(Iterable<? extends T> removeFrom) {
        return delegate().removeIf(removeFrom);
    }

    @Override
    public <S extends T> Rule<S> and(Rule<? super T> that) {
        return delegate().and(that);
    }

    @Override
    public <S extends T> Rule<S> and(Predicate<? super T> that) {
        return delegate().and(that);
    }
    
    @Override
    public <S extends T> Rule<S> or(Rule<? super T> that) {
        return delegate().or(that);
    }

    @Override
    public <S extends T> Rule<S> or(Predicate<? super T> that) {
        return delegate().or(that);
    }
    
    @Override
    public <S extends T> Rule<S> xor(Rule<? super T> that) {
        return delegate().xor(that);
    }

    @Override
    public <S extends T> Rule<S> xor(Predicate<? super T> that) {
        return delegate().xor(that);
    }
    
    @Override
    public <S extends T> Rule<S> negate() {
        return delegate().negate();
    }

    /**
     * {@inheritDoc}
     * @deprecated use {@link #negate()}
     */
    @Deprecated
    @Override
    public <S extends T> Rule<S> not() {
        return delegate().not();
    }

    @Override
    public <S> Rule<S> compose(Function<? super S, ? extends T> function) {
        return delegate().compose(function);
    }
    
    @Override
    public boolean equals(Object that) {
        return delegate().equals(that);
    }
    
    @Override
    public int hashCode() {
        return delegate().hashCode();
    }
    
}
