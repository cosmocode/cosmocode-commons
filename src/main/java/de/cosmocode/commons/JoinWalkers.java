package de.cosmocode.commons;

import com.google.common.base.Function;

/**
 * Utility class for {@link JoinWalker}s.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
public final class JoinWalkers {

    private JoinWalkers() {
        
    }

    /**
     * Creates a {@link Function} which delegates to the given {@link JoinWalker}.
     * 
     * @since 1.9
     * @param <T> generic parameter type
     * @param walker the backing walker
     * @return a {@link Function} backed by the given walker
     * @throws NullPointerException if walker is null
     */
    @SuppressWarnings("deprecation")
    public static <T> Function<T, String> asFunction(final JoinWalker<? super T> walker) {
        return new JoinWalkerFunction<T>(walker);
    }

}
