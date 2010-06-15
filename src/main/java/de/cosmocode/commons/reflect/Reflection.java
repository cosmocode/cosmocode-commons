package de.cosmocode.commons.reflect;

import com.google.common.base.Preconditions;

import de.cosmocode.commons.Strings;

/**
 * Static utility class for {@link Classpath}s and {@link Packages}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
public final class Reflection {

    private Reflection() {
        
    }
    
    /**
     * Creates a new {@link Classpath} using the classpath property
     * of this virtual machine.
     * 
     * @since 1.8
     * @return a {@link Classpath} backed by the classpath of this virtual machine
     */
    public static Classpath defaultClasspath() {
        final String classpath = System.getProperty("java.class.path");
        return Reflection.classpathOf(Strings.defaultIfBlank(classpath, ""));
    }
    
    /**
     * Creates a {@link Classpath} using the specified classpath value.
     * 
     * @since 1.8
     * @param classpath the backing classpath value (; separated)
     * @return a {@link Classpath} backed by the specified classpath
     * @throws NullPointerException if classpath is null
     */
    public static Classpath classpathOf(String classpath) {
        Preconditions.checkNotNull(classpath, "Classpath");
        return new DefaultClasspath(classpath);
    }

}
