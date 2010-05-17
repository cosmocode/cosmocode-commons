package de.cosmocode.commons.reflect;

import java.util.concurrent.ConcurrentMap;

import com.google.common.collect.MapMaker;

/**
 * Static utility class for {@link Class}es.
 *
 * @since 1.6
 * @author Willi Schoenborn
 */
public final class Classes {

    private static final ConcurrentMap<String, Class<?>> CACHE = new MapMaker().softValues().makeMap();
    
    private Classes() {
        
    }
    
    /**
     * Returns the Class object associated with the class or interface with the given string name.
     * This method does in contrast to {@link Class#forName(String)} caches the results.
     * 
     * @since 1.6
     * @param name the class name
     * @return the loaded class
     * @throws ClassNotFoundException if the class does not exist
     */
    public static Class<?> forName(String name) throws ClassNotFoundException {
        final Class<?> cached = CACHE.get(name);
        return cached == null ? store(name) : cached;
    }
    
    private static Class<?> store(String name) throws ClassNotFoundException {
        final Class<?> type = Class.forName(name);
        CACHE.put(name, type);
        return type;
    }

}
