package de.cosmocode.commons;

/**
 * A JoinWalker is a function object,
 * which allows a String-join-Algorithm to
 * transform any kind of object into a string.
 * A JoinWalker should be stateless and therefore
 * sould be declared as an anonymous private static
 * instance. This approach provides best performance.
 * 
 * @author schoenborn
 *
 * @param <T> the type this walker is able to transform into a string
 */
public interface JoinWalker<T> {

    /**
     * "Walk" an instance and return its value as a string.
     * 
     * @param t the object this walker should transform into a string, may be null
     * @return string representation (of any kind) of parameter t
     */
    public String walk(T t);
    
}