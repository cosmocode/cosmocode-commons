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

import java.util.Comparator;
import java.util.Date;

import javax.annotation.concurrent.Immutable;

import com.google.common.primitives.Longs;

/**
 * A {@link DateMode} determines the way how to
 * transform a timestamp of type long into a {@link Date} instance.
 *
 * @author Willi Schoenborn
 */
public enum DateMode implements Comparator<Date>, Bijection<Long, Date> {

    /**
     * Assumes the timestamp is in milliseconds,
     * starting at 1970-01-01T00:00:00GMT
     * as described in {@link Date}.
     */
    JAVA {
        
        @Override
        public Date parse(long value) {
            return new Date(value);
        }
        
        @Override
        public long format(Date date) {
            return date == null ? -1L : date.getTime();
        }
        
        @Override
        public int compare(Date left, Date right) {
            return left.compareTo(right);
        }
        
    },
    
    /**
     * Assumes the timestamp is in seconds,
     * starting at 1970-01-01T00:00:00GMT.
     */
    UNIXTIME {
        
        @Override
        public Date parse(long value) {
            return new Date(value * 1000L);
        }
        
        @Override
        public long format(Date date) {
            return date == null ? -1L : date.getTime() / 1000L;
        }
        
        @Override
        public int compare(Date left, Date right) {
            final long leftRounded = left.getTime() / 1000L;
            final long rightRounded = right.getTime() / 1000L;
            return Longs.compare(leftRounded, rightRounded);
        }
        
    };
    
    private final Bijection<Date, Long> inverse = new InverseDateMode();
    
    /**
     * Implementation of {@link DateMode#inverse()}.
     *
     * @since 1.9
     * @author Willi Schoenborn
     */
    @Immutable
    private final class InverseDateMode implements Bijection<Date, Long> {
        
        @Override
        public Long apply(Date from) {
            return format(from);
        }
        
        @Override
        public Bijection<Long, Date> inverse() {
            return DateMode.this;
        }
        
        @Override
        public String toString() {
            return DateMode.this + ".inverse()";
        }
        
    }
    
    /**
     * Transforms a timestamp into a {@link Date} instance.
     * 
     * @param value the timestamp
     * @return a new {@link Date} instance
     */
    public abstract Date parse(long value);
    
    /**
     * Transforms a {@link Date} instance into a timestamp.
     * 
     * @param date the date instance
     * @return a timestamp created from the date, or -1 if date is null
     */
    public abstract long format(Date date);

    @Override
    public Date apply(Long from) {
        return from == null ? null : parse(from.longValue());
    }
    
    @Override
    public Bijection<Date, Long> inverse() {
        return inverse;
    }
    
}
