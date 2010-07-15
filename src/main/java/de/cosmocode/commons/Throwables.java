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

package de.cosmocode.commons;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

/**
 * Static utility class for {@link Throwable}s.
 *
 * @since 1.7
 * @author Willi Schoenborn
 */
public final class Throwables {

    private Throwables() {
        
    }
    
    public static Function<Throwable, String> getMessage() {
        return GetMessage.INSTANCE;
    }
    
    /**
     * Implementation of {@link Throwables#getMessage()}.
     *
     * @since 1.9
     * @author Willi Schoenborn
     */
    private enum GetMessage implements Function<Throwable, String> {
        
        INSTANCE;
        
        @Override
        public String apply(Throwable from) {
            return from.getMessage();
        }
        
        @Override
        public String toString() {
            return "Throwables.getMessage()";
        }
        
    }
    
    /**
     * Propagates {@code throwable} or one of it's causes exactly as-is, if and only if it is an
     * instance of {@code declaredType}.
     * 
     * @since 1.9
     * @param <X> generic exception type
     * @param t the thrown throwable
     * @param declaredType the declared exception type
     * @throws NullPointerException if declaredType is null
     * @throws X if t or one of its causes is an instanceof declaredType
     */
    public static <X extends Throwable> void propagateCauseIfInstanceOf(Throwable t, Class<X> declaredType) throws X {
        Preconditions.checkNotNull(declaredType, "DeclaredType");
        if (t == null) return;
        com.google.common.base.Throwables.propagateIfInstanceOf(t, declaredType);
        propagateCauseIfInstanceOf(t.getCause(), declaredType);
    }
    
}
