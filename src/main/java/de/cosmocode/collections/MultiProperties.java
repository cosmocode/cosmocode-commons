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
package de.cosmocode.collections;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.util.Arrays;
import java.util.Properties;

/**
 * Extension of the {@link Properties} class which can load
 * multiple properties files in sequence. 
 *
 * @since 1.4
 * @author Willi Schoenborn
 */
public final class MultiProperties {

    private static final long serialVersionUID = 3902798386118115027L;

    private MultiProperties() {
        
    }
    
    /**
     * Creates {@link Properties} by reading all of the given files
     * in the supplied sequence.
     * 
     * @since 1.4
     * @param files the properties files to read from
     * @return a properties instance containing all elements from the supplied files
     * @throws IOException if {@link Properties#load(Reader)} failed
     */
    public static Properties load(File... files) throws IOException {
        return load(Arrays.asList(files));
    }

    /**
     * Creates {@link Properties} by reading all of the given files
     * in the supplied sequence.
     * 
     * @since 1.4
     * @param files the properties files to read from
     * @return a properties instance containing all elements from the supplied files
     * @throws IOException if {@link Properties#load(Reader)} failed
     */
    public static Properties load(Iterable<? extends File> files) throws IOException {
        Preconditions.checkNotNull(files, "Files");
        final Properties properties = new Properties();
        
        for (File file : files) {
            final Reader reader = Files.newReader(file, Charsets.UTF_8);
            try {
                properties.load(reader);
            } finally {
                reader.close();
            }
        }
        
        return properties;
    }

}
