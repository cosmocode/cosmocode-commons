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

package de.cosmocode.commons.reflect;

import java.lang.annotation.Annotation;

import com.google.common.base.Preconditions;

import de.cosmocode.commons.validation.AbstractRule;

/**
 * Implementation for {@link Reflection#isAnnotationPresent(Class)}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
final class IsAnnotationPresent extends AbstractRule<Class<?>> {

    private final Class<? extends Annotation> annotation;
    
    public IsAnnotationPresent(Class<? extends Annotation> annotation) {
        this.annotation = Preconditions.checkNotNull(annotation, "Annotation");
    }
    
    @Override
    public boolean apply(Class<?> input) {
        return input.isAnnotationPresent(annotation);
    }
    
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof IsAnnotationPresent) {
            final IsAnnotationPresent other = IsAnnotationPresent.class.cast(that);
            return annotation.equals(other.annotation);
        } else {
            return false;
        }
    }
    
    @Override
    public int hashCode() {
        return annotation.hashCode() ^ -652874548;
    }
    
    @Override
    public String toString() {
        return "Reflection.isAnnotationPresent(" + annotation.getName() + ")";
    }

}
