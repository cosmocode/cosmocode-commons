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
import com.google.common.collect.Ordering;

import java.text.Collator;
import java.util.Locale;

/**
 * An {@link Ordering} which is based on a given {@link Collator}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
final class CollatorOrdering extends Ordering<String> {

    private final Locale locale;
    private final Collator collator;

    public CollatorOrdering(Locale locale) {
        this.locale = Preconditions.checkNotNull(locale, "Locale");
        this.collator = Collator.getInstance(locale);
    }
    
    @Override
    public int compare(String left, String right) {
        return collator.compare(left, right);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof CollatorOrdering) {
            final CollatorOrdering other = CollatorOrdering.class.cast(that);
            return locale.equals(other.locale) && collator.equals(other.collator);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return collator.hashCode() ^ 754623731;
    }
    
    @Override
    public String toString() {
        return "Strings.orderBy(" + locale + ")";
    }
    
}
