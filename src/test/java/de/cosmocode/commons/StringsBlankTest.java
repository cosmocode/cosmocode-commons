/**
 * Copyright 2010 - 2013 CosmoCode GmbH
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

import com.google.common.collect.Lists;
import de.cosmocode.commons.validation.AbstractRuleTest;
import de.cosmocode.commons.validation.Rule;

/**
 * Tests {@link Strings#BLANK}.
 *
 * @since 1.21
 * @author Willi Schoenborn
 */
public final class StringsBlankTest extends AbstractRuleTest<CharSequence> {

    @Override
    public Rule<CharSequence> unit() {
        return Strings.BLANK;
    }
    
    @Override
    protected Iterable<CharSequence> validInputs() {
        return Lists.<CharSequence>newArrayList("", "     ", null, "\t  ", "\t\t");
    }

    @Override
    protected Iterable<CharSequence> invalidInputs() {
        return Lists.<CharSequence>newArrayList(
            "any", "string", "but", "empty", 
            "whitespace", "only", "tabs", "and", "null"
        );
    }

    @Override
    protected boolean satisfiedByNull() {
        return true;
    }
    
    @Override
    protected CharSequence defaultValueForFind() {
        return "default";
    }
    
}
