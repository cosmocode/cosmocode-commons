package de.cosmocode.commons;

import com.google.common.base.Function;

/**
 * A {@link Bijection} is a invertible {@link Function}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 * @param <F> the source type
 * @param <T> the target type
 */
public interface Bijection<F, T> extends Function<F, T> {

    /**
     * Provides the inverse function of this bijection.
     * 
     * @since 1.8
     * @return a bijection which defines the inversion of this bijection
     */
    Bijection<T, F> inverse();
    
}
