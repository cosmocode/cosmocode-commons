package de.cosmocode.commons;

/**
 * A {@link Stateful} service's state can
 * be accessed through this specification.
 *
 * @author Willi Schoenborn
 */
public interface Stateful {

    /**
     * Provide the current state.
     * 
     * @return the current {@link State}
     */
    State currentState();
    
    /**
     * Returns true if currently running.
     * 
     * @return true if this service is currently running
     */
    boolean isRunning();
    
}
