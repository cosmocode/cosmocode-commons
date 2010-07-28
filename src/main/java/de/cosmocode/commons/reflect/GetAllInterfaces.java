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

import java.util.Iterator;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.AbstractIterator;
import com.google.common.collect.Iterables;

/**
 * Implementation of {@link Reflection#getAllInterfaces()}.
 *
 * @since 1.11
 * @author Willi Schoenborn
 */
public enum GetAllInterfaces implements Function<Class<?>, Iterable<Class<?>>> {

    INSTANCE;
    
    @Override
    public Iterable<Class<?>> apply(final Class<?> type) {
        return new AllInterfaces(type);
    }
    
    /**
     * {@link Iterable} implementation which traverses the inheritance
     * tree and provides each interface.
     *
     * @since 1.11
     * @author Willi Schoenborn
     */
    private static final class AllInterfaces implements Iterable<Class<?>> {
        
        private final Class<?> type;
        
        public AllInterfaces(Class<?> type) {
            this.type = Preconditions.checkNotNull(type, "Type");
        }
        
        @Override
        public Iterator<Class<?>> iterator() {
            return new AbstractIterator<Class<?>>() {

                private Class<?> current = type;
                private int index;
                
                @Override
                protected Class<?> computeNext() {
                    if (current == null) {
                        return endOfData();
                    } else {
                        final Class<?>[] interfaces = current.getInterfaces();
                        if (index < interfaces.length) {
                            return interfaces[index++];
                        } else {
                            current = current.getSuperclass();
                            index = 0;
                            return computeNext();
                        }
                    }
                }
                
            };
        }
        
        @Override
        public String toString() {
            return Iterables.toString(this);
        }
        
    }
    
}
