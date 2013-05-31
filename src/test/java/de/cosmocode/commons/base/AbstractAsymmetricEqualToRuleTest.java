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
package de.cosmocode.commons.base;

import com.google.common.collect.Lists;
import de.cosmocode.commons.validation.AbstractRuleTest;
import de.cosmocode.commons.validation.Rule;

import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;

/**
 * Abstract test class for {@link AsymmetricEqualToRule}.
 *
 * @since 1.21
 * @author Willi Schoenborn
 */
abstract class AbstractAsymmetricEqualToRuleTest extends AbstractRuleTest<Date> {

    private final Date now = initializeNow();
    
    /**
     * Initializes the now field.
     *
     * @since 1.21
     * @return the final value for now
     */
    protected abstract Date initializeNow();
    
    @Override
    public Rule<Date> unit() {
        return MoreObjects.asymmetricEqualTo(now);
    }
    
    @Override
    protected Iterable<Date> validInputs() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(now);
        return Lists.newArrayList(
            now, 
            new Date(now.getTime()), 
            calendar.getTime(),
            new java.sql.Date(now.getTime()),
            new Timestamp(now.getTime())
        );
    }
    
    @Override
    protected Iterable<Date> invalidInputs() {
        final Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date(now.getTime() - 100));
        return Lists.newArrayList(
            new Date(now.getTime() - 100), 
            new Date(now.getTime() + 100), 
            null,
            calendar.getTime(),
            new java.sql.Date(now.getTime() - 100),
            new Timestamp(now.getTime() + 100)
        );
    }
    
    @Override
    protected boolean satisfiedByNull() {
        return false;
    }
    
}
