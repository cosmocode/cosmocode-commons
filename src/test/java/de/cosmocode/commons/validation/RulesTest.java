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

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Predicate;

/**
 * Tests {@link Rules}.
 *
 * @author Willi Schoenborn
 */
public final class RulesTest {

    /**
     * Tests {@link Rules#of(Predicate)}.
     */
    @Test
    public void asRule() {
        final Predicate<String> predicate = new Predicate<String>() {
            
            @Override
            public boolean apply(String input) {
                return input.isEmpty();
            }
            
        };
        
        final Rule<String> rule = Rules.of(predicate);
        
        Assert.assertTrue(rule.apply(""));
        Assert.assertFalse(rule.negate().apply(""));
        Assert.assertTrue(rule.negate().negate().apply(""));
    }
    
    /**
     * Tests {@link Rules#eq(Object)}.
     */
    @Test
    public void eq() {
        final Rule<TimeUnit> isSeconds = Rules.eq(TimeUnit.SECONDS);
        Assert.assertFalse(isSeconds.apply(TimeUnit.MILLISECONDS));
        Assert.assertTrue(isSeconds.apply(TimeUnit.SECONDS));
        Assert.assertFalse(isSeconds.apply(TimeUnit.DAYS));
    }
    
    /**
     * Tests {@link Rules#between(Object, Object)}.
     */
    @Test
    public void between() {
        final Rule<TimeUnit> between = Rules.between(TimeUnit.NANOSECONDS, TimeUnit.SECONDS).negate();
        Assert.assertFalse(between.apply(TimeUnit.MICROSECONDS));
        Assert.assertTrue(between.apply(TimeUnit.HOURS));
    }

}
