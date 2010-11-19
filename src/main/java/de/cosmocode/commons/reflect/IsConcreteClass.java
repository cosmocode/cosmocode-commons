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

import de.cosmocode.commons.validation.AbstractRule;
import de.cosmocode.commons.validation.Rule;

/**
 * Implementation for {@link Reflection#isConcreteClass()}.
 *
 * @since 2.0
 * @author Willi Schoenborn
 */
final class IsConcreteClass extends AbstractRule<Class<?>> {

    public static final Rule<Class<?>> INSTANCE = new IsConcreteClass();
    
    private final Rule<Class<?>> delegate = 
            Reflection.isInterface().not().and(
            Reflection.isAbstract().not()).and(
            Reflection.isEnum().not()).and(
            Reflection.isArray().not());
    
    private IsConcreteClass() {
        
    }
    
    @Override
    public boolean apply(Class<?> input) {
        return delegate.apply(input);
    }
    
    @Override
    public String toString() {
        return "Reflection.isConcreteClass()";
    }
    
}
