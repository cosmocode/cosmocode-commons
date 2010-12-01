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

import javax.annotation.Nonnull;

import com.google.common.base.CharMatcher;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;

import de.cosmocode.commons.validation.Rule;

/**
 * Static utility class for {@link CharMatcher}s.
 *
 * @since 1.20
 * @author Willi Schoenborn
 */
public final class CharMatchers {

    private CharMatchers() {
        
    }
    
    /**
     * Creates a {@link Rule} which evaluates to true if the given char matcher matches
     * all of the supplied char sequence.
     *
     * @since 1.20
     * @param matcher the underlying matcher
     * @return a rule backed by the given matcher
     * @throws NullPointerException if matcher is null
     */
    public static Rule<CharSequence> matchAll(@Nonnull CharMatcher matcher) {
        return new CharMatcherPredicate(matcher);
    }
    
}
