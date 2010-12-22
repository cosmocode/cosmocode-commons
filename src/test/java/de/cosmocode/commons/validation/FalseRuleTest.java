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

import com.google.common.collect.Lists;

/**
 * Tests {@link FalseRule}.
 *
 * @since 1.21
 * @author Willi Schoenborn
 */
public final class FalseRuleTest extends AbstractRuleTest<String> {

    @Override
    public Rule<String> unit() {
        return Rules.alwaysFalse();
    }
    
    @Override
    protected Iterable<String> validInputs() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected Iterable<String> mixedInputs() {
        throw new UnsupportedOperationException();
    }
    
    @Override
    protected Iterable<String> invalidInputs() {
        return Lists.newArrayList("any", "string", "could", "be", "here", "even", null);
    }
    
    @Override
    protected boolean satisfiedByNull() {
        return false;
    }
    
    @Override
    protected String defaultValueForFind() {
        return "default";
    }
    
    @Override
    public void applyValid() {
        
    }

    @Override
    public void checkElementValid() {
        
    }

    @Override
    public void allValids() {
        
    }
    
    @Override
    public void allMixed() {
        
    }
    
    @Override
    public void anyValids() {
        
    }
    
    @Override
    public void anyMixed() {
        
    }
    
    @Override
    public void noneValids() {
        
    }
    
    @Override
    public void noneMixed() {
        
    }
    
    @Override
    public void filterValids() {
        
    }
    
    @Override
    public void filterMixed() {
        
    }
    
    @Override
    public void findValids() {
        
    }
    
    @Override
    public void findMixed() {
        
    }
    
    @Override
    public void findDefaultValids() {
        
    }
    
    @Override
    public void findDefaultMixed() {
        
    }
    
    @Override
    public void removeIfValid() {
        
    }
    
    @Override
    public void removeIfMixed() {
        
    }
    
}
