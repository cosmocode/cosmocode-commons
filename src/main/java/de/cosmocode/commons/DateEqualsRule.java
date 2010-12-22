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

import de.cosmocode.commons.base.MoreObjects;
import de.cosmocode.commons.validation.AbstractRule;

import javax.annotation.Nullable;
import java.util.Date;

/**
 * Implementation of {@link Dates#equalTo(java.util.Date)}.
 *
 * @deprecated use {@link MoreObjects#asymmetricEqualTo(Object)}
 * @since 1.20
 * @author Oliver Lorenz
 */
@Deprecated
final class DateEqualsRule extends AbstractRule<Date> {

    private final Date value;

    DateEqualsRule(Date value) {
        this.value = Preconditions.checkNotNull(value, "Value");
    }

    @Override
    public boolean apply(@Nullable Date input) {
        return input != null && (input.equals(value) || value.equals(input));
    }

    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        } else if (that instanceof DateEqualsRule) {
            final DateEqualsRule other = DateEqualsRule.class.cast(that);
            return other.value.equals(this.value);
        } else {
            return false;
        }
    }

    @Override
    public int hashCode() {
        return value.hashCode() ^ 763523534;
    }

    @Override
    public String toString() {
        return "Dates.equalTo(" + value + ")";
    }

}
