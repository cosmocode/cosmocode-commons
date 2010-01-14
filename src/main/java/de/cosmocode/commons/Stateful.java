package de.cosmocode.commons;

public interface Stateful {

    State currentState();
    
    boolean isRunning();
    
}
