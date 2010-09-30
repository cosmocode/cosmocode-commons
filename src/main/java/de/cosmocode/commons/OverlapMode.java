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

import com.google.gag.annotation.enforceable.CantTouchThis;
import com.google.gag.annotation.remark.Magic;
import com.google.gag.enumeration.MagicType;
import com.google.gag.enumeration.Stop;

import java.util.Arrays;
import java.util.Date;

/**
 * Overlap mode. This class will get better documentation later on (when it will become public).
 *
 * @author Oliver Lorenz
 * @author Adrian Lang
 * @author Willi Schönborn
*/
enum OverlapMode {

    /**
     * <p> The two periods overlap if they intersect.
     * This means that they do not overlap only if one whole period
     * is right or left of the other period, so that they do not intersect.
     * </p>
     */
    ALL {
        @Override
        public boolean isOverlapping(final long s1, final long e1, final long s2, final long e2) {
            /*
             * this is the negation of the only two non-overlapping cases.
             *
             * These are:
             *    period1 right of period2: s1 > s2 && s1 > e2 && e1 > s2 && e1 > e2
             *    period1 left of period2: s1 < s2 && s1 < e2 && e1 < s2 && e1 < e2.
             *
             * So the mathematic formula is:
             *    !((s1 > s2 && s1 > e2 && e1 > s2 && e1 > e2) || (s1 < s2 && s1 < e2 && e1 < s2 && e1 < e2))
             */
            return !((s1 > s2 && s1 > e2 && e1 > s2 && e1 > e2) ||
                     (s1 < s2 && s1 < e2 && e1 < s2 && e1 < e2));
        }
    },

    /**
     * The two periods overlap if they intersect, and borders are not considered to overlap.
     * This means that if the start date of one period and the end date of the other period are equal
     * (this means that the two periods "touch" each other, but do not intersect otherwise),
     * then they are not considered overlapping, and isOverlapping would return false.
     */
    NO_BORDERS {
        @Override
        @Magic(type = MagicType.WHITE)
        @CantTouchThis(Stop.HAMMERTIME)
        public boolean isOverlapping(final long s1, final long e1, final long s2, final long e2) {
            if (s1 == e1 && e1 == s2 && s2 == e2) {
                // special case: all are equal. means: is overlapping
                return true;
            }

            // r1 is the sum of the first period
            // r2 is the sum of the second period
            final long r1 = s1 + e1;
            final long r2 = s2 + e2;
            // all points of the first and second period are put into an array and sorted ascending
            final long[] a = {s1, e1, s2, e2};
            Arrays.sort(a);
            /* here the magic happens:
             *  after sorting the points, the sum of the first two points must either be:
             *   - equal to the sum of the first period
             *     (in which case the first period still is before the second period),
             *   - or equal to the sum of the second period
             *     (in this case the first period is after the second period)
             * If either period overlaps, then the points get in disorder, so the sum of the first two points
             * is a mixed sum of one point of the first and one point of the second period.
             *
             * This is like a VERY simple hash.
             */
            return !(a[0] + a[1] == r1 || a[0] + a[1] == r2);
        }
    };

    /**
     * <p> Checks whether period 1 and period 2 overlap. The result depends on the enum.
     * </p>
     * <p> Period 1 is represented by s1 (start time) and e1 (end time).
     * Period 2 is represented by s2 (start time) and e2 (end time).
     * </p>
     * @param s1 start time of period 1 (Java timestamp)
     * @param e1 end time of period 1 (Java timestamp)
     * @param s2 start time of period 2 (Java timestamp)
     * @param e2 end time of period 2 (Java timestamp)
     * @return true if the two periods overlap, false otherwise
     */
    public abstract boolean isOverlapping(long s1, long e1, long s2, long e2);

    /**
     * <p> Checks whether period 1 and period 2 overlap. The result depends on the enum.
     * </p>
     * <p> Period 1 is represented by s1 (start time) and e1 (end time).
     * Period 2 is represented by s2 (start time) and e2 (end time).
     * </p>
     * @param s1 start date of period 1
     * @param e1 end date of period 1
     * @param s2 start date of period 2
     * @param e2 end date of period 2
     * @return true if the two periods overlap, false otherwise
     * @throws NullPointerException if any of the given Dates is null
     */
    public boolean isOverlapping(final Date s1, final Date e1, final Date s2, final Date e2) {
        return isOverlapping(s1.getTime(), e1.getTime(), s2.getTime(), e2.getTime());
    }

}

