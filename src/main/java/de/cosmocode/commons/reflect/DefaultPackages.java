/**
 * Copyright 2010 - 2013 CosmoCode GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package de.cosmocode.commons.reflect;

import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.ImmutableSet.Builder;
import com.google.common.collect.Iterables;
import com.google.common.io.Files;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.lang.annotation.Annotation;
import java.net.URL;
import java.util.Iterator;
import java.util.jar.JarEntry;
import java.util.jar.JarInputStream;

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
        Preconditions.checkNotNull(classpath, "Classpath");
        Preconditions.checkNotNull(packages, "Packages");
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
        loadDirectoryRecursively(builder, directory, directory, packages);
    }
    
    private void loadDirectoryRecursively(Builder<Class<?>> builder, File root, File directory, 
        Iterable<String> packages) {
        LOG.trace("Loading from directory {}", directory);
        for (File file : directory.listFiles()) {
            if (file.isDirectory()) {
                loadDirectoryRecursively(builder, root, file, packages);
            } else {
                loadClass(builder, file.getAbsolutePath().replace(root.getAbsolutePath(), ""), packages);
            }
        }
    }

    private void loadJar(Builder<Class<?>> builder, File file, Iterable<String> packages) throws IOException {
        LOG.trace("Loading from jar {}", file);
        final InputStream stream = Files.newInputStreamSupplier(file).getInput();
        
        try {
            final JarInputStream jar = new JarInputStream(stream);
            
            try {
                while (true) {
                    final JarEntry entry = jar.getNextJarEntry();
                    if (entry == null) return;
                    loadClass(builder, entry.getName(), packages);
                }
            } finally {
                jar.close();
            }
        } finally {
            stream.close();
        }
    }
    
    private void loadClass(Builder<Class<?>> builder, String name, Iterable<String> packages) {
        if (!name.endsWith(".class")) return;
        final String className = pathToName(name);
        LOG.trace("Considering class {}", className);
        if (containedIn(className, packages)) {
            LOG.trace("Loading class {}", className);
            try {
                builder.add(Reflection.forName(className));
            } catch (ClassNotFoundException e) {
                throw new IllegalStateException(e);
            }
        }
    }
    
    private String pathToName(String path) {
        final String name = path.replace("/", ".").replace(".class", "");
        return name.startsWith(".") ? name.substring(1) : name;
    }
    
    private boolean containedIn(String className, Iterable<String> packages) {
        for (String p : packages) {
            if (className.startsWith(p + ".")) return true;
        }
        return false;
    }

    @Override
    public <T> Iterable<Class<? extends T>> subclassesOf(Class<T> type) {
        Preconditions.checkNotNull(type, "Type");
        final Iterable<Class<?>> filtered = Iterables.filter(this, Reflection.isSubtypeOf(type));
        return Iterables.transform(filtered, Reflection.asSubclass(type));
    }

    @Override
    public Iterable<Class<?>> annotatedWith(Class<? extends Annotation> annotation) {
        Preconditions.checkNotNull(annotation, "Annotation");
        return Iterables.filter(this, Reflection.isAnnotationPresent(annotation));
    }
    
    @Override
    public Iterable<Class<?>> filter(Predicate<? super Class<?>> predicate) {
        Preconditions.checkNotNull(predicate, "Predicate");
        return Iterables.filter(this, predicate);
    }
    
    @Override
    public <T> Iterable<Class<? extends T>> filter(Class<T> type, Predicate<? super Class<? extends T>> predicate) {
        Preconditions.checkNotNull(type, "Type");
        Preconditions.checkNotNull(predicate, "Predicate");
        return Iterables.filter(subclassesOf(type), predicate);
    }
    
    @Override
    public Iterator<Class<?>> iterator() {
        return classes.iterator();
    }

}
