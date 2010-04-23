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

package de.cosmocode.commons.predicates;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

/**
 * 
 *
 * @author Willi Schoenborn
 */
public final class StringPredicates {

    private StringPredicates() {
        
    }
    
    public static Predicate<String> contains(String s) {
        return new ContainsPredicate(s);
    }
    
    private static final class ContainsPredicate extends AbstractPredicate<String> {
        
        private final String s;
        
        public ContainsPredicate(String s) {
            this.s = Preconditions.checkNotNull(s, "String");
        }
      
        @Override
        public boolean apply(String input) {
            return input.contains(s);
        };

        @Override
        public String toString() {
            return "contains(" + s + ")";
        }
    }

}
