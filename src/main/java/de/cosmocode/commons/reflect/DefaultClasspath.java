package de.cosmocode.commons.reflect;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;
import java.util.Set;

import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;

/**
 * Default {@link Classpath} implementation.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
final class DefaultClasspath implements Classpath {
    
    private static final Splitter SPLITTER = Splitter.on(";").trimResults().omitEmptyStrings();

    private final Set<URL> entries;

    public DefaultClasspath(String classpath) {
        Preconditions.checkNotNull(classpath, "Classpath");
        
        final Builder<URL> builder = ImmutableSet.builder();
        
        for (String entry : SPLITTER.split(classpath)) {
            try {
                builder.add(new File(entry).toURI().toURL());
            } catch (MalformedURLException e) {
                throw new ExceptionInInitializerError(e);
            }
        }
        
        entries = builder.build();
    }
    
    @Override
    public Iterator<URL> iterator() {
        return entries.iterator();
    }

    @Override
    public Packages restrictTo(String... packages) {
        Preconditions.checkNotNull(packages, "Packages");
        return restrictTo(Arrays.asList(packages));
    }
    
    @Override
    public Packages restrictTo(Iterable<String> packages) {
        Preconditions.checkNotNull(packages, "Packages");
        try {
            return new DefaultPackages(this, packages);
        } catch (IOException e) {
            throw new IllegalStateException(e);
        }
    }
    
}
