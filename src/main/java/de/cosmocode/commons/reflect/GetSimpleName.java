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
package de.cosmocode.commons.reflect;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;

import javax.annotation.Nullable;

/**
 * Implementation for {@link Reflection#getSimpleName()}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
enum GetSimpleName implements Function<Class<?>, String> {
    
    INSTANCE;
    
    @Override
    public String apply(@Nullable Class<?> type) {
        Preconditions.checkNotNull(type, "Type");
        return type.getSimpleName();
    }
    
    @Override
    public String toString() {
        return "Reflection.getSimpleName()";
    }
    
}
