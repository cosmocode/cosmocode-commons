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

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Function;

/**
 * The {@link TrimMode} determines
 * how a {@link String} is being trimmed.
 * 
 * @author Willi Schoenborn
 */
public enum TrimMode implements Function<String, String> {

    /**
     * Removes control characters (char &lt;= 32) from both
     * ends of this String, handling {@code null} by returning
     * {@code null}.
     * 
     * See {@link StringUtils#trim(String)} for more details.
     */
    NORMAL {
        
        @Override
        public String trim(String s) {
            return StringUtils.trim(s);
        }
        
    }, 
    
    /**
     * Removes control characters (char &lt;= 32) from both
     * ends of this String returning an empty String ("") if the String
     * is empty ("") after the trim or if it is {@code null}.
     * 
     * See {@link StringUtils#trimToEmpty(String)} for more details.
     */
    EMPTY {
        
        @Override
        public String trim(String s) {
            return StringUtils.trimToEmpty(s);
        }
        
    },
    
    /**
     * Removes control characters (char &lt;= 32) from both
     * ends of this String returning {@code null} if the String is
     * empty ("") after the trim or if it is {@code null}.
     * 
     * See {@link StringUtils#trimToNull(String)} for more details.
     */
    NULL {
        
        @Override
        public String trim(String s) {
            return StringUtils.trimToNull(s);
        }
        
    };
    
    /**
     * Trims the given {@link String}.
     * 
     * @param s the {@link String} being trimmed
     * @return the trimmed version of s
     */
    public abstract String trim(String s);

    /**
     * Trims the given {@link CharSequence}.
     * 
     * @param sequence the {@link CharSequence} being trimmed
     * @return the trimmed version of sequence
     */
    public CharSequence trim(CharSequence sequence) {
        return trim(sequence == null ? null : sequence.toString());
    }
    
    /**
     * This is a kind of an adapter allowing
     * it to use a {@link TrimMode} as a {@link Function}.
     * 
     * <p>
     *   This method is delegating its work to {@link TrimMode#trim(CharSequence)}.
     * </p>
     * 
     * {@inheritDoc}
     */
    @Override
    public String apply(String from) {
        return trim(from);
    }
    
}
