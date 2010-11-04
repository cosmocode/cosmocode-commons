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
import com.google.common.base.Predicate;
import com.google.common.collect.Constraint;

/**
 * Utility class for {@link Predicate}s, {@link Constraint}s and general conditional checks.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
public final class Conditions {

    private Conditions() {
        
    }

    /**
     * Checks the given input using the specified attribute.
     * 
     * @since 1.9
     * @param <T> the generic parameter type
     * @param predicate the predicate being used to check inout
     * @param input the input to be checked
     * @return input
     * @throws NullPointerException if predicate is null
     * @throws IllegalArgumentException if input does not satisfy predicate
     */
    public static <T> T checkArgument(Predicate<? super T> predicate, T input) {
        return checkArgument(predicate, input, "%s does not satisfy %s");
    }
    
    /**
     * Checks the given input using the specified attribute.
     *
     * <p>
     *   Using this method is equivalent to:<br />
     *   {@code Conditions.checkArgument(predicate, input, message, input, predicate);}
     * </p>
     *
     * @since 1.19
     * @param <T> the generic parameter type
     * @param predicate the predicate being used to check inout
     * @param input the input to be checked
     * @param message the error message
     * @return input
     * @throws NullPointerException if predicate is null
     * @throws IllegalArgumentException if input does not satisfy predicate
     */
    public static <T> T checkArgument(Predicate<? super T> predicate, T input, String message) {
        return checkArgument(predicate, input, message, input, predicate);
    }
    
    /**
     * Checks the given input using the specified attribute.
     *
     * <p>
     *   Using this method is equivalent to:<br />
     *   {@code Conditions.checkArgument(predicate, input, message, input, predicate);}
     * </p>
     *
     * @since 1.19
     * @param <T> the generic parameter type
     * @param predicate the predicate being used to check inout
     * @param input the input to be checked
     * @param message the error message
     * @param args the error message arguments
     * @return input
     * @throws NullPointerException if predicate is null
     * @throws IllegalArgumentException if input does not satisfy predicate
     */
    public static <T> T checkArgument(Predicate<? super T> predicate, T input, String message, Object... args) {
        Preconditions.checkNotNull(predicate, "Predicate");
        Preconditions.checkArgument(predicate.apply(input), message, args);
        return input;
    }
    
    /**
     * Adapts the given predicate to the {@link Constraint} interface.
     * The returned constraint throws an {@link IllegalArgumentException} if
     * the supplied input does not satisfy the given predicate.
     * 
     * @since 1.8
     * @param <T> the generic parameter type
     * @param predicate the backing predicate
     * @return a constraint backed by the specified predicate
     */
    public static <T> Constraint<T> asContraint(Predicate<? super T> predicate) {
        return new PredicateConstraint<T>(predicate);
    }
    
    /**
     * Adapts the given {@link Constraint} to the {@link Function} interface.
     * 
     * @since 1.9
     * @param <T> generic parameter type
     * @param constraint the backing constraint
     * @return a function backed by the given constraint
     * @throws NullPointerException if constraint is null
     */
    public static <T> Function<T, T> asFunction(Constraint<T> constraint) {
        return new ConstraintFunction<T>(constraint);
    }
    
}
