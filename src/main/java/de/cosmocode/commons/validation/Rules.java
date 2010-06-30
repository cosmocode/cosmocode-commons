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

import com.google.common.base.Preconditions;
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
     * Implementation of {@link Rules#asRule(Predicate)}.
     *
     * @since 1.9
     * @author Willi Schoenborn
     * @param <T> generic parameter type
     */
    private static final class PredicateRule<T> extends AbstractRule<T> {
        
        private final Predicate<? super T> predicate;
        
        public PredicateRule(Predicate<? super T> predicate) {
            this.predicate = Preconditions.checkNotNull(predicate, "Predicate");
        }

        @Override
        public boolean apply(T input) {
            return predicate.apply(input);
        }
        
        @Override
        public String toString() {
            return "Rules.asRule(" + predicate + ")";
        }
        
    }
    
}
