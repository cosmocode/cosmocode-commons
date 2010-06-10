package de.cosmocode.commons;

import com.google.common.base.Function;

/**
 * Static utility class for {@link Throwable}s.
 *
 * @since 1.7
 * @author Willi Schoenborn
 */
public final class Throwables {

    private static final Function<Throwable, String> GET_MESSAGE = new Function<Throwable, String>() {
        
        @Override
        public String apply(Throwable from) {
            return from.getMessage();
        }
        
    };

    private Throwables() {
        
    }
    
    public static Function<Throwable, String> getMessage() {
        return GET_MESSAGE;
    }
    
}
