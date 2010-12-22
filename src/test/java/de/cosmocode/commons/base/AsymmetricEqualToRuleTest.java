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

package de.cosmocode.commons.base;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

/**
 * Tests {@link AsymmetricEqualToRule}. 
 *
 * @since 1.21
 * @author Willi Schoenborn
 */
@RunWith(Suite.class)
@SuiteClasses({
    ForwardAsymmetricEqualToRuleTest.class,
    ReverseAsymmetricEqualToRuleTest.class
})
public final class AsymmetricEqualToRuleTest {

}
