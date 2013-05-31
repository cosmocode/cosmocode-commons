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
package de.cosmocode.commons.validation;

import com.google.common.collect.Lists;
import org.junit.Test;

/**
 * Tests {@link TrueRule}.
 *
 * @since 1.21
 * @author Willi Schoenborn
 */
public final class TrueRuleTest extends AbstractRuleTest<String> {

    @Override
    public Rule<String> unit() {
        return Rules.alwaysTrue();
    }
    
    @Override
    protected Iterable<String> validInputs() {
        return Lists.newArrayList("any", "string", "could", "be", "here", "even", null);
    }
    
    @Override
    protected Iterable<String> mixedInputs() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected Iterable<String> invalidInputs() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected boolean satisfiedByNull() {
        return true;
    }
    
    @Override
    protected String defaultValueForFind() {
        return "default";
    }
    
    @Override
    public void applyInvalid() {
        
    }

    @Override
    public void allMixed() {
        
    }
    
    @Override
    public void allInvalids() {
        
    }
    
    @Override
    public void anyMixed() {
        
    }
    
    @Override
    public void anyInvalids() {
        
    }
    
    @Override
    public void noneMixed() {
        
    }
    
    @Override
    public void noneInvalids() {
        
    }
    
    @Override
    public void filterMixed() {
        
    }

    @Override
    public void filterInvalids() {
        
    }
    
    @Override
    public void findMixed() {
        
    }
    
    // no expected exception
    @Test
    @Override
    public void findInvalids() {
        
    }
    
    @Override
    public void findDefaultMixed() {
        
    }
    
    @Override
    public void findDefaultInvalids() {
        
    }
    
    @Override
    public void removeIfInvalid() {
        
    }
    
}
