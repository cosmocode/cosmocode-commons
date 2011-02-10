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

import junit.framework.Assert;

import org.junit.Test;

import com.google.common.base.Service.State;

import de.cosmocode.junit.UnitProvider;

/**
 * Test {@link AbstractRestartableService}.
 *
 * @since 1.21
 * @author Willi Schoenborn
 */
public final class RestartableServiceTest implements UnitProvider<RestartableService> {

    @Override
    public RestartableService unit() {
        return new AbstractRestartableService() {
            
            @Override
            protected void doStop() {
                // nothing to do
            }
            
            @Override
            protected void doStart() {
                // nothing to do
            }
            
        };
    }
    
    /**
     * Tests manually restarting.
     */
    @Test
    public void manualRestart() {
        final RestartableService unit = unit();
        Assert.assertFalse(unit.isRunning());
        Assert.assertSame(State.RUNNING, unit.startAndWait());
        Assert.assertTrue(unit.isRunning());
        Assert.assertSame(State.TERMINATED, unit.stopAndWait());
        Assert.assertFalse(unit.isRunning());
        Assert.assertSame(State.RUNNING, unit.startAndWait());
        Assert.assertTrue(unit.isRunning());
    }
    
    /**
     * Tests {@link RestartableService#restart()}.
     */
    @Test
    public void restartAndWait() {
        final RestartableService unit = unit();
        Assert.assertFalse(unit.isRunning());
        Assert.assertSame(State.RUNNING, unit.startAndWait());
        Assert.assertTrue(unit.isRunning());
        Assert.assertSame(State.RUNNING, unit.restartAndWait());
        Assert.assertTrue(unit.isRunning());
    }
    
    
}
