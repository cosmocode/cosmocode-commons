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

import com.google.common.base.Function;

/**
 * Utility class for {@link JoinWalker}s.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
public final class JoinWalkers {

    private JoinWalkers() {
        
    }

    /**
     * Creates a {@link Function} which delegates to the given {@link JoinWalker}.
     * 
     * @since 1.9
     * @param <T> generic parameter type
     * @param walker the backing walker
     * @return a {@link Function} backed by the given walker
     * @throws NullPointerException if walker is null
     */
    @SuppressWarnings("deprecation")
    public static <T> Function<T, String> asFunction(final JoinWalker<? super T> walker) {
        return new JoinWalkerFunction<T>(walker);
    }

}
