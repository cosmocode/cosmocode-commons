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

/**
 * Implementation of {@link Rules#isNull()}.
 *
 * @since 1.20
 * @author Willi Schoenborn
 */
final class NullRule extends AbstractRule<Object> {
    
    static final Rule<Object> INSTANCE = new NullRule();
    
    private NullRule() {
        
    }

    @Override
    public boolean apply(Object input) {
        return input == null;
    }
    
    @Override
    public Rule<Object> negate() {
        return Rules.isNotNull();
    }
    
    @Override
    public String toString() {
        return "Rules.isNull()";
    }
    
}
