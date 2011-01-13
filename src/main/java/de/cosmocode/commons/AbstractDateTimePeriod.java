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

import java.util.Date;
import java.util.concurrent.TimeUnit;

import com.google.common.annotations.Beta;

/**
 * Abstract date based {@link TimePeriod}.
 * It defines two additional methods (getStartsAt() and getEndsAt()) that return the start and end date.
 *
 * @since 1.21
 * @author Oliver Lorenz
 */
@Beta
public abstract class AbstractDateTimePeriod implements TimePeriod {

    /**
     * Returns the start date of the period.
     * @return the start date
     */
    public abstract Date getStartsAt();

    /**
     * Returns the end date of the period.
     * @return the end date
     */
    public abstract Date getEndsAt();

    @Override
    public Date getReference() {
        return new Date(0);
    }

    @Override
    public long getStart(TimeUnit unit) {
        return unit.convert(getStartsAt().getTime(), TimeUnit.MILLISECONDS);
    }

    @Override
    public long getEnd(TimeUnit unit) {
        return unit.convert(getEndsAt().getTime(), TimeUnit.MILLISECONDS);
    }

    @Override
    public TimeUnit getPrecision() {
        return TimeUnit.MILLISECONDS;
    }
}
