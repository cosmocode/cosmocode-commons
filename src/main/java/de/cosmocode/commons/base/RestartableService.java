package de.cosmocode.commons.base;

import java.util.concurrent.Future;

import com.google.common.annotations.Beta;
import com.google.common.base.Service;

/**
 * A restartable {@link Service}.
 *
 * @since 1.21
 * @author Willi Schoenborn
 */
@Beta
public interface RestartableService extends Service {

    /**
     * Restarts, literally stops and starts, this service and returns
     * a future which blocks until restart has finished. This method 
     * returns immediately.
     *
     * @since 1.21
     * @return a future which blocks until this service is restarted
     */
    Future<State> restart();
    
    /**
     * Restarts, literally stops and starts, this service and returns
     * the state after the restart.
     *
     * @since 1.21
     * @return the state after the restart
     */
    State restartAndWait();
    
}
