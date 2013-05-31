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
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.Constraint;
import com.google.common.collect.Iterables;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Abstract {@link Rule} implementation.
 *
 * @since 1.9
 * @author Willi Schoenborn
 * @param <T> generic parameter type.
 */
public abstract class AbstractRule<T> implements Rule<T> {
    
    private static final Logger LOG = LoggerFactory.getLogger(AbstractRule.class);

    @Override
    public final T checkElement(T element) {
        Preconditions.checkArgument(apply(element), "%s does not satisfy %s", element, this);
        return transform(element);
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
    public boolean all(Iterable<? extends T> inputs) {
        Preconditions.checkNotNull(inputs, "Inputs");
        return Iterables.all(inputs, this);
    }
    
    @Override
    public boolean any(Iterable<? extends T> inputs) {
        Preconditions.checkNotNull(inputs, "Inputs");
        return Iterables.any(inputs, this);
    }
    
    @Override
    public boolean none(Iterable<? extends T> inputs) {
        return !any(inputs);
    }
    
    @Override
    public Iterable<T> filter(Iterable<T> unfiltered) {
        Preconditions.checkNotNull(unfiltered, "Unfiltered");
        return Iterables.filter(unfiltered, this);
    }
    
    @Override
    public T find(Iterable<? extends T> iterable) {
        Preconditions.checkNotNull(iterable, "Iterable");
        return Iterables.find(iterable, this);
    }
    
    @Override
    public T find(Iterable<T> iterable, T defaultValue) {
        Preconditions.checkNotNull(iterable, "Iterable");
        return Iterables.find(iterable, this, defaultValue);
    }
    
    @Override
    public boolean removeIf(Iterable<? extends T> removeFrom) {
        Preconditions.checkNotNull(removeFrom, "RemoveFrom");
        return Iterables.removeIf(removeFrom, this);
    }

    @Override
    public <S extends T> Rule<S> and(Rule<? super T> that) {
        if (equals(that)) {
            LOG.debug("{}.and({}) has been optimized", this, that);
            return Rules.<S>of(this);
        } else {
            return new AndRule<S>(this, that);
        }
    }
    
    @Override
    public <S extends T> Rule<S> and(Predicate<? super T> that) {
        return and(Rules.of(that));
    }

    @Override
    public <S extends T> Rule<S> or(Rule<? super T> that) {
        if (equals(that)) {
            LOG.debug("{}.or({}) has been optimized", this, that);
            return Rules.<S>of(this);
        } else {
            return new OrRule<S>(this, that);
        }
    }
    
    @Override
    public <S extends T> Rule<S> or(Predicate<? super T> that) {
        return or(Rules.of(that));
    }

    @Override
    public <S extends T> Rule<S> xor(Rule<? super T> that) {
        if (equals(that)) {
            LOG.debug("{}.xor({}) has been optimized", this, that);
            return Rules.<S>alwaysFalse();
        } else {
            return new XorRule<S>(this, that);
        }
    }
    
    @Override
    public <S extends T> Rule<S> xor(Predicate<? super T> that) {
        return xor(Rules.of(that));
    }

    @Override
    public Rule<T> negate() {
        return new NegatedRule<T>(this);
    }
    
    /**
     * {@inheritDoc}
     * @deprecated use {@link #negate()}
     */
    @Deprecated
    @Override
    public Rule<T> not() {
        return negate();
    }

    @Override
    public <S> Rule<S> compose(Function<? super S, ? extends T> function) {
        return new ComposedRule<T, S>(this, function);
    }

    @Override
    public abstract String toString();

}
