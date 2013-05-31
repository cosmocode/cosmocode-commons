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
package de.cosmocode.commons;

/**
 * Implementation of {@link Bijections#identity()}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
enum IdentityBijection implements Bijection<Object, Object> {
    
    INSTANCE;

    @Override
    public Object apply(Object from) {
        return from;
    }

    @Override
    public Bijection<Object, Object> inverse() {
        return this;
    }
    
    @Override
    public String toString() {
        return "Bijections.identity()";
    }
    
}
