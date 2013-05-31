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

import com.google.common.base.Preconditions;
import de.cosmocode.commons.validation.AbstractRule;

/**
 * Contained in string predicate.
 *
 * @since 1.6
 * @see Strings#containedIn(String)
 * @author Willi Schoenborn
 */
final class StringContainedInRule extends AbstractRule<CharSequence> {
    
    private final String s;
    
    public StringContainedInRule(String s) {
        this.s = Preconditions.checkNotNull(s, "String");
    }
    
    @Override
    public boolean apply(CharSequence input) {
        return input == null ? false : s.contains(input);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof StringContainedInRule) {
            final StringContainedInRule other = StringContainedInRule.class.cast(that);
            return s.equals(other.s);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return s.hashCode() ^ 358941548;
    }
    
    @Override
    public String toString() {
        return "Strings.containedIn(" + s + ")";
    }
    
}
