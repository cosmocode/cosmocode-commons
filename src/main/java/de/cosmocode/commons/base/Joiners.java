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

import java.util.Collection;

import com.google.common.base.Joiner;

/**
 * Static utility class for {@link Joiner}s.
 *
 * @since 1.19
 * @author Willi Schoenborn
 */
public final class Joiners {

    public static final Joiner NEW_LINE = Joiner.on('\n');
    
    public static final Joiner SPACE = Joiner.on(' ');

    /**
     * Old name for {@link #SPACE}.
     * 
     * @deprecated replaced by {@link #SPACE}
     */
    @Deprecated
    public static final Joiner BLANK = SPACE;
    
    public static final Joiner COMMA = Joiner.on(',');
    
    /**
     * This joiner is especially useful when building string representations
     * of {@link Collection}s.
     */
    public static final Joiner COLLECTION_ELEMENTS = Joiner.on(", ");
    
    private Joiners() {
        
    }
    
}
