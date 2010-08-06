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
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * Rule implementation which always evaluates to {@code true}.
 *
 * @since 1.13
 * @author Willi Schoenborn
 */
enum TrueRule implements Rule<Object> {

    INSTANCE;
    
    @Override
    public boolean apply(Object input) {
        return true;
    }
    
    @Override
    public Object checkElement(Object element) {
        return element;
    }
    
    @Override
    public <S> Rule<S> and(Rule<? super Object> that) {
        return Rules.of(checkNotNull(that));
    }
    
    @Override
    public <S> Rule<S> and(Predicate<? super Object> that) {
        return Rules.of(checkNotNull(that));
    }
    
    @Override
    public <S> Rule<S> or(Rule<? super Object> that) {
        return Rules.of(this);
    }
    
    @Override
    public <S> Rule<S> or(Predicate<? super Object> that) {
        return Rules.of(this);
    }
    
    @Override
    public <S> Rule<S> not() {
        return Rules.alwaysFalse();
    }
    
    @Override
    public <S> Rule<S> xor(Rule<? super Object> that) {
        return checkNotNull(that).not();
    }
    
    @Override
    public <S> Rule<S> xor(Predicate<? super Object> that) {
        return xor(Rules.of(checkNotNull(that)));
    }
    
    @Override
    public <S> Rule<S> compose(Function<? super S, ? extends Object> function) {
        return new ComposedRule<Object, S>(this, function);
    }
    
    private <O> O checkNotNull(O that) {
        return Preconditions.checkNotNull(that, "That");
    }
    
    @Override
    public String toString() {
        return "Rules.alwaysTrue()";
    }
    
}
