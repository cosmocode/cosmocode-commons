package de.cosmocode.commons;

import com.google.common.base.Function;

/**
 * Implementation of {@link Throwables#getMessage()}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
enum ThrowableGetMessageFunction implements Function<Throwable, String> {
    
    INSTANCE;
    
    @Override
    public String apply(Throwable from) {
        return from.getMessage();
    }
    
    @Override
    public String toString() {
        return "Throwables.getMessage()";
    }
    
}
