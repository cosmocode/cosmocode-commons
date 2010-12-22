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

import java.sql.Timestamp;
import java.util.Date;

import javax.annotation.Nullable;

import com.google.common.base.Objects;

import de.cosmocode.commons.validation.Rule;
import de.cosmocode.commons.validation.Rules;

/**
 * Static utility class for {@link Object}s, just like {@link Objects}.
 *
 * @since 1.21
 * @author Willi Schoenborn
 */
public final class MoreObjects {

    private MoreObjects() {
        
    }
    
    /**
     * Creates a rule similiar to {@link Rules#equalTo(Object)} but checks for equality
     * by comparing target to the given input and vice versa to support
     * broken equals implementations like {@link Date} and {@link Timestamp}. 
     *
     * @since 1.21
     * @param <T> generic parameter type
     * @param target the target object
     * @return an asymmetric equal to rule
     */
    public static <T> Rule<T> asymmetricEqualTo(@Nullable T target) {
        return target == null ? Rules.<T>isNull() : new AsymmetricEqualToRule<T>(target);
    }
    
}
