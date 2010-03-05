package de.cosmocode.commons.predicates;

import com.google.common.base.Predicate;

/**
 * 
 *
 * @author Willi Schoenborn
 * @param <T>
 */
public abstract class AbstractPredicate<T> implements Predicate<T> {

    @Override
    public abstract String toString();

}
