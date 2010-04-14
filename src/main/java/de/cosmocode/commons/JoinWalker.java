package de.cosmocode.commons;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;

/**
 * A JoinWalker is a function object,
 * which allows a String-join-Algorithm to
 * transform any kind of object into a string.
 * A JoinWalker should be stateless and therefore
 * sould be declared as an anonymous private static
 * instance. This approach provides best performance.
 * 
 * @deprecated use {@link Joiner}, {@link Function} and {@link Iterables#transform(Iterable, Function)} instead
 *  
 * @author schoenborn@cosmocode.de
 * @param <T> the type this walker is able to transform into a string
 */
@Deprecated
public interface JoinWalker<T> {

    /**
     * "Walk" an instance and return its value as a string.
     * 
     * @param t the object this walker should transform into a string, may be null
     * @return string representation (of any kind) of parameter t
     */
    String walk(T t);
    
}
