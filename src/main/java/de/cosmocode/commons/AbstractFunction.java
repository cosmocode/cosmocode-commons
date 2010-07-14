package de.cosmocode.commons;

import com.google.common.base.Function;

public abstract class AbstractFunction<F, T> implements Function<F, T> {

    @Override
    public abstract String toString();

}
