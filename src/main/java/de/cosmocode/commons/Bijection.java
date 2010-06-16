package de.cosmocode.commons;

import com.google.common.base.Function;

public interface Bijection<F, T> extends Function<F, T> {

    Bijection<T, F> inverse();
    
}
