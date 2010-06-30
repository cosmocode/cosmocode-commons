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

import com.google.common.base.Predicate;

/**
 * Utility class for {@link Rule}s.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
public final class Rules {

    private Rules() {
        
    }

    /**
     * Adapts the specified predicate to the {@link Rule} interface.
     * 
     * @since 1.9
     * @param <T> generic parameter type
     * @param predicate the backing predicate
     * @return a rule backed by the given predicate
     * @throws NullPointerException if predicate is null
     */
    public static <T> Rule<T> asRule(Predicate<? super T> predicate) {
        return new PredicateRule<T>(predicate);
    }
    
    /**
     * Returns a rule which evaluates to true if the supplied input
     * is less than the given comparable.
     * 
     * @since 1.9
     * @param <C> the generic parameter type
     * @param comparable the comparable
     * @return a lt rule comparing input and comparable
     * @throws NullPointerException if comparable is null
     */
    public static <C extends Comparable<C>> Rule<C> lt(C comparable) {
        return new LtRule<C>(comparable);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * is less than or equals to the given comparable.
     * 
     * @since 1.9
     * @param <C> the generic parameter type
     * @param comparable the comparable
     * @return a le rule comparing input and comparable
     * @throws NullPointerException if comparable is null
     */
    public static <C extends Comparable<C>> Rule<C> le(C comparable) {
        return new LeRule<C>(comparable);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * is equals to the given comparable.
     * 
     * @since 1.9
     * @param <C> the generic parameter type
     * @param comparable the comparable
     * @return a eq rule comparing input and comparable
     * @throws NullPointerException if comparable is null
     */
    public static <C extends Comparable<C>> Rule<C> eq(C comparable) {
        return new EqRule<C>(comparable);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * is greater than or equals to the given comparable.
     * 
     * @since 1.9
     * @param <C> the generic parameter type
     * @param comparable the comparable
     * @return a ge rule comparing input and comparable
     * @throws NullPointerException if comparable is null
     */
    public static <C extends Comparable<C>> Rule<C> ge(C comparable) {
        return new GeRule<C>(comparable);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * is greater than the given comparable.
     * 
     * @since 1.9
     * @param <C> the generic parameter type
     * @param comparable the comparable
     * @return a gt rule comparing input and comparable
     * @throws NullPointerException if comparable is null
     */
    public static <C extends Comparable<C>> Rule<C> gt(C comparable) {
        return new GtRule<C>(comparable);
    }

    /**
     * Returns a rule which evaluates to true if the supplied input
     * is greater than lower and less than upper.
     * 
     * @since 1.9
     * @param <C> the generic parameter type
     * @param lower the lower bound
     * @param upper the upper bound
     * @return a between rule comparing input with lower and upper
     * @throws NullPointerException if lower or upper is null
     */
    public static <C extends Comparable<C>> Rule<C> between(C lower, C upper) {
        return gt(lower).and(lt(upper));
    }
    
}
