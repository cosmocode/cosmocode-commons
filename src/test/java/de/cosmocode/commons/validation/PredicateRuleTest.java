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

import java.math.BigInteger;

import com.google.common.base.Predicates;
import com.google.common.collect.Lists;
import com.ibm.icu.math.BigDecimal;

/**
 * Tests {@link PredicateRule}.
 *
 * @since 1.21
 * @author Willi Schoenborn
 */
public final class PredicateRuleTest extends AbstractRuleTest<Object> {
    
    private final Object object = new Object();

    @Override
    public Rule<Object> unit() {
        return Rules.of(Predicates.instanceOf(Number.class));
    }
    
    @Override
    protected Iterable<Object> validInputs() {
        return Lists.<Object>newArrayList(
            Byte.MIN_VALUE, Byte.MAX_VALUE,
            Short.MIN_VALUE, Short.MAX_VALUE,
            Integer.MIN_VALUE, Integer.MAX_VALUE,
            Long.MIN_VALUE, Long.MAX_VALUE,
            Float.MIN_VALUE, Float.MAX_VALUE,
            Double.MIN_VALUE, Double.MAX_VALUE,
            BigInteger.ZERO, BigInteger.ONE,
            BigDecimal.ZERO, BigDecimal.ONE
        );
    }
    
    @Override
    protected Iterable<Object> invalidInputs() {
        return Lists.<Object>newArrayList(
            "String",
            Class.class,
            object,
            Boolean.FALSE,
            null
        );
    }
    
    @Override
    protected boolean satisfiedByNull() {
        return false;
    }
    
    @Override
    protected Object defaultValueForFind() {
        return "default";
    }
    
}
