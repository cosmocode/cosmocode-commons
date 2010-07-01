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

import org.junit.Assert;
import org.junit.Test;

import de.cosmocode.commons.Strings;

/**
 * Tests {@link Rule}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
public final class RuleTest {

    /**
     * Tests {@link Rule#and(Rule)}.
     */
    @Test
    public void and() {
        final Rule<String> aAndB = Strings.contains("a").and(Strings.contains("b"));
        Assert.assertTrue(aAndB.apply("a and b are great"));
        Assert.assertFalse(aAndB.apply("a"));
        Assert.assertFalse(aAndB.apply("b without the other is not complete"));
    }

}
