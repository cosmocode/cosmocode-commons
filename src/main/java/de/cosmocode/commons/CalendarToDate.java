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
 * Implementation of {@link Calendars#getTime()}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
enum CalendarToDate implements Bijection<Calendar, Date> {
    
    INSTANCE;
    
    @Override
    public Date apply(Calendar from) {
        return from.getTime();
    }
    
    @Override
    public Bijection<Date, Calendar> inverse() {
        return DateToCalendar.INSTANCE;
    }
    
    @Override
    public String toString() {
        return "Calendars.getTime()";
    }
    
}
