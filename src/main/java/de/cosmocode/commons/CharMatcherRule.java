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

import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;

import de.cosmocode.commons.validation.AbstractRule;

/**
 * Default implementation of {@link CharMatchers#matchAll(CharMatcher)}.
 *
 * @since 1.20
 * @author Willi Schoenborn
 */
final class CharMatcherRule extends AbstractRule<CharSequence> {

    private final CharMatcher matcher;
    
    CharMatcherRule(CharMatcher matcher) {
        this.matcher = Preconditions.checkNotNull(matcher, "Matcher");
    }
    
    @Override
    public boolean apply(CharSequence sequence) {
        return matcher.matchesAllOf(sequence);
    }
    
    @Override
    public String toString() {
        return "CharMatchers.matchAll(" + matcher + ")";
    }
    
}
