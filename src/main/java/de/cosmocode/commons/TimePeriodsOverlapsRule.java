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

import com.google.common.base.Preconditions;
import de.cosmocode.commons.validation.AbstractRule;

import javax.annotation.Nullable;

/**
 * Implementation of {@link TimePeriods#overlaps(TimePeriod, OverlapMode)}.
 */
final class TimePeriodsOverlapsRule extends AbstractRule<TimePeriod> {

    private final TimePeriod otherTimePeriod;
    private final OverlapMode overlapMode;

    TimePeriodsOverlapsRule(TimePeriod otherTimePeriod, OverlapMode overlapMode) {
        this.otherTimePeriod = Preconditions.checkNotNull(otherTimePeriod, "Other TimePeriod");
        this.overlapMode = Preconditions.checkNotNull(overlapMode, "OverlapMode");
    }

    @Override
    public boolean apply(@Nullable TimePeriod input) {
        return input != null && overlapMode.isOverlapping(otherTimePeriod, input);
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof TimePeriodsOverlapsRule) {
            final TimePeriodsOverlapsRule other = TimePeriodsOverlapsRule.class.cast(that);
            return otherTimePeriod.equals(other.otherTimePeriod) && overlapMode.equals(other.overlapMode);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return otherTimePeriod.hashCode() << 16 ^ overlapMode.hashCode();
    }

    @Override
    public String toString() {
        return "TimePeriods.overlaps(" + otherTimePeriod + ", " + overlapMode + ")";
    }

}
