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

package de.cosmocode.collections;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

/**
 * Static utility class for {@link Procedure}s.
 *
 * @author Willi Schoenborn
 */
public final class Procedures {

    private Procedures() {
        
    }
    
    /**
     * Chains multiple {@link Procedure}s together into a single one.
     * 
     * @param <T> the generic procedure type
     * @param first the first procedure
     * @param second the second procedure
     * @param rest the rest
     * @return a {@link Procedure} containing all given {@link Procedure}
     * @throws NullPointerException if any parameter is null
     */
    public static <T> Procedure<T> chain(final Procedure<? super T> first, final Procedure<? super T> second, 
        final Iterable<? extends Procedure<? super T>> rest) {
        Preconditions.checkNotNull(first, "First");
        Preconditions.checkNotNull(second, "Second");
        Preconditions.checkNotNull(rest, "Rest");
        return new Procedure<T>() {
            
            @Override
            public void apply(T input) {
                first.apply(input);
                second.apply(input);
                for (Procedure<? super T> procedure : rest) {
                    procedure.apply(input);
                }
            };
            
        };
    }
    
    /**
     * Chains multiple procedures into one.
     * 
     * @since 1.15
     * @param <T> the generic parameter type
     * @param procedures the backing procedures
     * @return a {@link Procedure} executing all given procedures in sequence
     * @throws NullPointerException if procedures is null
     */
    public static <T> Procedure<T> chain(final Iterable<? extends Procedure<? super T>> procedures) {
        Preconditions.checkNotNull(procedures, "Procedures");
        return new Procedure<T>() {

            @Override
            public void apply(T input) {
                for (Procedure<? super T> procedure : procedures) {
                    procedure.apply(input);
                }
            }

            
        };
    }
    
    /**
     * Adapts the specified {@link Procedure} to the {@link Function} interface.
     * 
     * @since 1.6
     * @param <T> generic procedure parameter type
     * @param procedure the backing procedure
     * @return a function backed by the given procedure which always returns null
     * @throws NullPointerException if procedure is null
     */
    public static <T> Function<T, Void> asFunction(final Procedure<? super T> procedure) {
        Preconditions.checkNotNull(procedure, "Procedure");
        return new Function<T, Void>() {
            
            @Override
            public Void apply(T from) {
                procedure.apply(from);
                return null;
            };
            
        };
    }
    
}
