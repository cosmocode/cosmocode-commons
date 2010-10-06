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

/**
 * Tests {@link de.cosmocode.commons.OverlapMode#IGNORE_BORDERS}.
 *
 * @since 1.16
 * @author Oliver Lorenz
 */
public final class OverlapModeIgnoreBordersTest extends OverlapModeTestCase {

    @Override
    protected boolean isOverlappingOnBorders() {
        return false;
    }

    @Override
    protected boolean isOverlappingOnContaining() {
        return true;
    }

    @Override
    protected boolean isOverlappingOnIntersection() {
        return true;
    }

    @Override
    protected boolean isOverlappingOnNoIntersection() {
        return false;
    }

    @Override
    protected boolean isOverlappingOnSameLength() {
        return true;
    }

    @Override
    protected boolean canHandleFlippedPeriods() {
        return true;
    }

    @Override
    public OverlapMode unit() {
        return OverlapMode.IGNORE_BORDERS;
    }
}
