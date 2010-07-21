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

package de.cosmocode.patterns;

/**
 * Abstract skeleton implementation of the
 * {@link Decorator} interface.
 * 
 * @author schoenborn@cosmocode.de
 *
 * @deprecated use ForwardingObject instead
 * @param <E> the generic type of the delegate
 */
@Deprecated
public class AbstractDecorator<E> {

    private final E delegate;
    
    /**
     * Constructs a new {@link AbstractDecorator}
     * using the parameter of type E as delegate.
     * 
     * @param delegate the delegate
     */
    public AbstractDecorator(E delegate) {
        this.delegate = delegate;
    }

    /**
     * Returns the instance this {@link Decorator} wraps.
     * 
     * @return the wrapped delegate
     */
    protected final E delegate() {
        return delegate;
    }
    
}
