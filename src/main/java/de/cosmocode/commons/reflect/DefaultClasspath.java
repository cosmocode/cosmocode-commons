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

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.base.Splitter;
import com.google.common.collect.ImmutableSet;
import com.google.common.collect.Iterables;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Arrays;
import java.util.Iterator;

/**
 * Default {@link Classpath} implementation.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
final class DefaultClasspath implements Classpath {
    
    private static final Splitter SPLITTER = Splitter.on(File.pathSeparator).trimResults().omitEmptyStrings();

    private final Iterable<URL> entries;

    public DefaultClasspath(String classpath) {
        Preconditions.checkNotNull(classpath, "Classpath");
        
        this.entries = ImmutableSet.copyOf(Iterables.transform(SPLITTER.split(classpath), new Function<String, URL>() {
            
            @Override
            public URL apply(String from) {
                try {
                    return new File(from).toURI().toURL();
                } catch (MalformedURLException e) {
                    throw new ExceptionInInitializerError(e);
                }
            }
            
        }));
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
