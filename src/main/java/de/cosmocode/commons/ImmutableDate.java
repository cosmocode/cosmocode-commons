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

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;

import com.google.common.annotations.Beta;

/**
 * Immutable subclass of {@link Date}.
 *
 * @author Oliver Lorenz
 */
@Immutable
@ThreadSafe
@Beta
final class ImmutableDate extends Date {
    
    private static final long serialVersionUID = -5520589706778945997L;

    public ImmutableDate() {
        super();
    }

    public ImmutableDate(long date) {
        super(date);
    }
    
    @Override
    public void setDate(int date) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setHours(int hours) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMinutes(int minutes) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setMonth(int month) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setSeconds(int seconds) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setTime(long time) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setYear(int year) {
        throw new UnsupportedOperationException();
    }

}
