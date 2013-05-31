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

import com.google.common.base.CharMatcher;
import com.google.common.base.Predicate;

import javax.annotation.Nullable;

/**
 * A {@link Predicate} for {@link CharSequence}s which evaluates
 * to true if a given sequence contains only whitespaces
 * as defined by {@link CharMatcher#WHITESPACE}.
 *
 * @since 1.20
 * @author Willi Schoenborn
 */
enum WhitespaceOnly implements Predicate<CharSequence> {

    INSTANCE;

    @Override
    public boolean apply(@Nullable CharSequence sequence) {
        return sequence != null && CharMatcher.WHITESPACE.matchesAllOf(sequence);
    }
    
}
