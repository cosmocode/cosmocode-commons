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
package de.cosmocode.commons.reflect;

import de.cosmocode.commons.validation.AbstractRule;
import de.cosmocode.commons.validation.Rule;

import javax.annotation.Nullable;

/**
 * Singleton enum predicate for {@link Reflection#isArray()}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
final class IsArray extends AbstractRule<Class<?>> {
    
    public static final Rule<Class<?>> INSTANCE = new IsArray();
    
    private IsArray() {
        
    }
    
    @Override
    public boolean apply(@Nullable Class<?> input) {
        return input != null && input.isArray();
    }
    
    @Override
    public String toString() {
        return "Reflection.isArray()";
    }
    
}
