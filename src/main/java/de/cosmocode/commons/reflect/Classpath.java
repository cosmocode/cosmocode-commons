package de.cosmocode.commons.reflect;

import java.net.URL;

/**
 * An abstraction for a set of class locations which
 * can be used to perform operations on different packages.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
public interface Classpath extends Iterable<URL> {
    
    /**
     * Restricts this classpath to the specified packages.
     * 
     * @since 1.8
     * @param packages the package names
     * @return a {@link Packages} instance which allows further operations
     *         on the selected packages
     * @throws NullPointerException if packages is null
     */
    Packages restrictTo(Iterable<String> packages);

    /**
     * Restricts this classpath to the specified packages.
     * 
     * @since 1.8
     * @param packages the package names
     * @return a {@link Packages} instance which allows further operations
     *         on the selected packages
     * @throws NullPointerException if packages is null
     */
    Packages restrictTo(String... packages);
    
}
