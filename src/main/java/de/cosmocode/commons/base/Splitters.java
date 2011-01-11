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

import com.google.common.base.Splitter;

/**
 * Static utility class for {@link Splitter}s.
 *
 * @since 1.21
 * @author Willi Schoenborn
 */
public final class Splitters {

    public static final Splitter NEW_LINE = Splitter.on('\n');
    
    public static final Splitter SPACE = Splitter.on(' ');
    
    public static final Splitter COMMA = Splitter.on(',');
    
    public static final Splitter COLON = Splitter.on(':');
    
    public static final Splitter SEMICOLON = Splitter.on(';');
    
    private Splitters() {
        
    }
    
}
