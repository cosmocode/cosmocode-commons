/**
 * Copyright 2010 CosmoCode GmbH
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
    
    private static final Splitter SPLITTER = Splitter.on(File.pathSeparator).trimResults().omitEmptyStrings();

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
