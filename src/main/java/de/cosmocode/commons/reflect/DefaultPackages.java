package de.cosmocode.commons.reflect;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Function;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.io.Files;

/**
 * Default {@link Packages} implementation.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
final class DefaultPackages implements Packages {

    private static final Logger LOG = LoggerFactory.getLogger(DefaultPackages.class);

    private final ImmutableSet<Class<?>> classes;
    
    public DefaultPackages(Classpath classpath, Iterable<String> packages) throws IOException {
        final Builder<Class<?>> builder = ImmutableSet.builder();
        
        for (URL url : classpath) {
            final File file = new File(url.getFile());
            if (file.isFile()) {
                loadJar(builder, file, packages);
            } else if (file.isDirectory()) {
                loadDirectory(builder, file, packages);
            } else {
                LOG.warn("Unable to load from classpath entry {}", url);
            }
        }
        
        this.classes = builder.build();
    }

    private void loadDirectory(Builder<Class<?>> builder, File directory, Iterable<String> packages) {
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                loadDirectory(builder, file, packages);
            } else {
                loadClass(builder, file.getAbsolutePath().replace(directory.getAbsolutePath(), ""), packages);
            }
        }
    }

    private void loadJar(Builder<Class<?>> builder, File file, Iterable<String> packages) throws IOException {
        final InputStream stream = Files.newInputStreamSupplier(file).getInput();
        final JarInputStream jar = new JarInputStream(stream);
        
        while (true) {
            final JarEntry entry = jar.getNextJarEntry();
            if (entry == null) return;
            loadClass(builder, entry.getName(), packages);
        }
    }
    
    private void loadClass(Builder<Class<?>> builder, String name, Iterable<String> packages) {
        if (!name.endsWith(".class")) return;
        final String className = name.substring(0, name.length() - ".class".length()).replace('/', '.');
        if (containedIn(className, packages)) {
            try {
                builder.add(Classes.forName(className));
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }
    }
    
    private boolean containedIn(String className, Iterable<String> packages) {
        for (String p : packages) {
            if (className.startsWith(p + ".")) return true;
        }
        return false;
    }

    @Override
    public <T> Iterable<Class<? extends T>> subclassesOf(final Class<T> type) {
        return Iterables.transform(Iterables.filter(this, new Predicate<Class<?>>() {
            
            @Override
            public boolean apply(Class<?> input) {
                return type.isAssignableFrom(input);
            }
            
        }), new Function<Class<?>, Class<? extends T>>() {
            
            @Override
            public Class<? extends T> apply(Class<?> from) {
                return from.asSubclass(type);
            }
            
        });
    }

    @Override
    public Iterable<Class<?>> annotatedWith(final Class<? extends Annotation> annotation) {
        return Iterables.filter(this, new Predicate<Class<?>>() {
            
            @Override
            public boolean apply(Class<?> input) {
                return input.isAnnotationPresent(annotation);
            }
            
        });
    }
    
    @Override
    public Iterable<Class<?>> filter(Predicate<? super Class<?>> predicate) {
        return Iterables.filter(this, predicate);
    }
    
    @Override
    public <T> Iterable<Class<? extends T>> filter(Class<T> type, Predicate<? super Class<? extends T>> predicate) {
        return Iterables.filter(subclassesOf(type), predicate);
    }
    
    @Override
    public Iterator<Class<?>> iterator() {
        return classes.iterator();
    }

}
