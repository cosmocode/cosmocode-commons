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
