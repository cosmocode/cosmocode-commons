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

import java.util.Calendar;
import java.util.Date;

/**
 * Utility class providing handy methods
 * regarding {@link Date} and {@link Calendar}.
 *
 * @author Willi Schoenborn
 */
public final class Dates {

    /**
     * Prevent instantiation.
     */
    private Dates() {
        
    }
  
    /**
     * Creates a {@link Date} using a {@link Calendar}
     * and automatically adds the given amount to the
     * given field.
     * 
     * <p>
     *   This allows one liner like this:
     *   <pre>
     *     final Date in2Days = DateUtility.add(Calendar.DATE, 2);
     *   </pre>
     * </p>
     * 
     * @param field the field (e.g. {@link Calendar#DATE})) which should be changed
     * @param amount the amount passed to {@link Calendar#add(int, int)}
     * @return the newly created {@link Date} instance
     */
    public static Date add(int field, int amount) {
        final Calendar calendar = Calendar.getInstance();
        calendar.add(field, amount);
        return calendar.getTime();
    }
    
}
