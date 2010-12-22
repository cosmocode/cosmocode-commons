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

import javax.annotation.Nullable;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;

/**
 * The {@link TrimMode} determines
 * how a {@link String} is being trimmed.
 * 
 * @author Willi Schoenborn
 */
public enum TrimMode implements Function<String, String> {

    /**
     * Removes whitespaces, as defined by {@link CharMatcher#WHITESPACE}, from both
     * ends of this String, handling {@code null} by returning
     * {@code null}.
     */
    NORMAL {
        
        @Override
        public String apply(@Nullable CharSequence sequence) {
            return sequence == null ? null : CharMatcher.WHITESPACE.trimFrom(sequence);
        }
        
    }, 
    
    /**
     * Removes whitespaces, as defined by {@link CharMatcher#WHITESPACE}, from both
     * ends of this String, returning an empty String ("") if the String
     * is empty ("") after the trim or if it is {@code null}.
     */
    EMPTY {
        
        @Override
        public String apply(@Nullable CharSequence sequence) {
            return sequence == null ? "" : CharMatcher.WHITESPACE.trimFrom(sequence);
        }
        
    },
    
    /**
     * Removess whitespaces, as defined by {@link CharMatcher#WHITESPACE}, from both
     * ends of this String, returning {@code null} if the String is
     * empty ("") after the trim or if it is {@code null}.
     */
    NULL {
        
        @Override
        public String apply(@Nullable CharSequence sequence) {
            if (sequence == null) {
                return null;
            } else {
                final String trimmed = CharMatcher.WHITESPACE.trimFrom(sequence);
                return trimmed.isEmpty() ? null : trimmed;
            }
        }
        
    };
    
    /**
     * Trims the given {@link String}.
     * 
     * @deprecated use {@link #apply(String)}
     * @param string the {@link String} being trimmed
     * @return the trimmed version of string
     */
    @Deprecated
    public final String trim(@Nullable String string) {
        return apply(string);
    }
    

    /**
     * Trims the given {@link CharSequence}.
     * 
     * @deprecated use {@link #apply(CharSequence)}
     * @param sequence the {@link CharSequence} being trimmed
     * @return the trimmed version of sequence
     */
    @Deprecated
    public final CharSequence trim(@Nullable CharSequence sequence) {
        return apply(sequence);
    }

    /**
     * Trims the given {@link CharSequence}.
     * 
     * @param sequence the {@link CharSequence} being trimmed
     * @return the trimmed version of sequence
     */
    public abstract String apply(@Nullable CharSequence sequence);

    /**
     * Trims the given {@link String}.
     * 
     * @param string the {@link String} being trimmed
     * @return the trimmed version of sequence
     */
    @Override
    public String apply(@Nullable String string) {
        // intermediate variable is required to make sure we delegate to the correct method
        final CharSequence sequence = string;
        return apply(sequence);
    }
    
}
