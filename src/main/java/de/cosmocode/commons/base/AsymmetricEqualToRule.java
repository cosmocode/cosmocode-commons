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

package de.cosmocode.commons.base;

import javax.annotation.Nullable;

import com.google.common.base.Preconditions;

import de.cosmocode.commons.validation.AbstractRule;
import de.cosmocode.commons.validation.Rules;

/**
 * An asymmetric version of {@link Rules#equalTo(Object)}.
 *
 * @since 1.21
 * @author Willi Schoenborn
 * @param <T> generic parameter type
 */
final class AsymmetricEqualToRule<T> extends AbstractRule<T> {

    private final T target;
    
    AsymmetricEqualToRule(T target) {
        this.target = Preconditions.checkNotNull(target, "Target");
    }
    
    @Override
    public boolean apply(@Nullable T input) {
        if (input == null) {
            return false;
        } else {
            return target.equals(input) || input.equals(target);
        }
    }

    @Override
    public String toString() {
        return "MoreObjects.asymetricEqualTo(" + target + ")";
    }

}
