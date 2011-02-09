package de.cosmocode.commons.base;

import java.util.concurrent.Future;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.annotations.Beta;
import com.google.common.base.Function;
import com.google.common.base.Service;
import com.google.common.util.concurrent.AbstractService;
import com.google.common.util.concurrent.ForwardingService;
import com.google.common.util.concurrent.Futures;
import com.google.common.util.concurrent.ListenableFuture;
import com.google.common.util.concurrent.MoreExecutors;

/**
 * Abstract implementation of {@link RestartableService}.
 *
 * @since 1.21
 * @author Willi Schoenborn
 */
@Beta
public abstract class AbstractRestartableService extends ForwardingService implements RestartableService {
    
    private final Logger log = LoggerFactory.getLogger(getClass());

    private final Function<State, ListenableFuture<State>> startAfterStop = 
        new Function<State, ListenableFuture<State>>() {
        
            @Override
            public ListenableFuture<State> apply(State input) {
                return Futures.makeListenable(start());
            }
            
        };
        
    private Service service = newService();
    
    @Override
    protected Service delegate() {
        return service;
    }
    
    /**
     * Creates and returns a new {@link Service} instance used
     * to provide the lifecycle functionality.
     *
     * @since 1.21
     * @return an instance which is used to manage the lifecycle of this service
     */
    protected Service newService() {
        return new DefaultService();
    }

    /**
     * The default implementation returned by {@link AbstractRestartableService#newService()}.
     *
     * @since 1.21
     * @author Willi Schoenborn
     */
    protected final class DefaultService extends AbstractService {
        
        @Override
        protected void doStart() {
            try {
                AbstractRestartableService.this.doStart();
                notifyStarted();
            /* CHECKSTYLE:OFF */
            } catch (Exception e) {
            /* CHECKSTYLE:ON */
                log.warn("Failed to start " + AbstractRestartableService.this, e);
                notifyFailed(e);
            }
        }

        @Override
        protected void doStop() {
            try {
                AbstractRestartableService.this.doStop();
                notifyStopped();
            /* CHECKSTYLE:OFF */
            } catch (Exception e) {
            /* CHECKSTYLE:ON */
                log.warn("Failed to stop " + AbstractRestartableService.this, e);
                notifyFailed(e);
            }
        }
        
    }
    
    private void replaceIfNecessary() {
        if (state() == State.TERMINATED) {
            service = newService();
        }
    }
    
    /**
     * Provides the startup logic.
     *
     * @since 1.21
     */
    protected abstract void doStart();
    
    @Override
    public final Future<State> start() {
        replaceIfNecessary();
        return super.start();
    }
    
    @Override
    public final State startAndWait() {
        replaceIfNecessary();
        return super.startAndWait();
    }
    
    /**
     * Provides the stop logic.
     *
     * @since 1.21
     */
    protected abstract void doStop();
    
    @Override
    public final Future<State> restart() {
        return Futures.chain(Futures.makeListenable(stop()), startAfterStop, MoreExecutors.sameThreadExecutor());
    }

    @Override
    public final State restartAndWait() {
        stopAndWait();
        return startAndWait();
    }

}
