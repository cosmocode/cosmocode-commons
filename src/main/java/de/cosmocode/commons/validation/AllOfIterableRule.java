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

package de.cosmocode.commons.validation;

import com.google.common.annotations.Beta;
import com.google.common.base.Preconditions;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/**
 * Rule implementation that applies a rule to all elements of an Iterable.
 * Applies if the given rule applies to all of the elements.
 *
 * @since 1.21
 * @author Oliver Lorenz
 * @param <E> generic type of the Iterable
 */
@Beta
final class AllOfIterableRule<E> extends AbstractRule<Iterable<E>> {

    private final Rule<E> inversedSingleApplicableRule;
    private final Rule<E> singleApplicableRule;

    AllOfIterableRule(@Nonnull final Rule<E> singleApplicableRule) {
        this.singleApplicableRule = Preconditions.checkNotNull(singleApplicableRule, "singleApplicableRule");
        this.inversedSingleApplicableRule = singleApplicableRule.negate();
    }

    @Override
    public boolean apply(@Nullable Iterable<E> input) {
        if (input == null) {
            return false;
        }

        for (final E element : input) {
            final boolean elementDoesNotApply = inversedSingleApplicableRule.apply(element);
            if (elementDoesNotApply) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        return "Rules.allOf(" + singleApplicableRule.toString() + ")";
    }

}
