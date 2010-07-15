package de.cosmocode.commons;

import com.google.common.base.Function;

/**
 * Abstract function which requires a "meaningful" {@link #toString()} method.
 *
 * @author Willi Schoenborn
 * @param <F> generic source parameter
 * @param <T> generic target parameter
 */
public abstract class AbstractFunction<F, T> implements Function<F, T> {

    @Override
    public abstract String toString();

}
