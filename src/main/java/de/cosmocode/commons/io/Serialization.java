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

package de.cosmocode.commons.io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * Static utility class for {@link Serializable}s.
 *
 * @since 1.13
 * @author Willi Schoenborn
 */
public final class Serialization {
    
    private static final Logger LOG = LoggerFactory.getLogger(Serialization.class);
    
    private Serialization() {
        
    }

    /**
     * Saves the given serializable to the specified file.
     * 
     * @since 1.13
     * @param serializable the object to be saved
     * @param file the target file
     * @throws NullPointerException if serializable or file is null
     * @throws IOException if saving failed
     */
    public static void save(Serializable serializable, File file) throws IOException {
        Preconditions.checkNotNull(file, "File");
        final OutputStream stream = new FileOutputStream(file);
        try {
            save(serializable, stream);
        } finally {
            stream.close();
        }
    }
    
    /**
     * Saves the given serializable to the specified output stream.
     * 
     * <p>
     *   The given output stream won't be closed.
     * </p>
     * 
     * @since 1.13
     * @param serializable the object to be saved
     * @param out the target stream
     * @throws NullPointerException if serializable or out is null
     * @throws IOException if saving failed
     */
    public static void save(Serializable serializable, OutputStream out) throws IOException {
        Preconditions.checkNotNull(serializable, "Serializable");
        Preconditions.checkNotNull(out, "Out");
        final ObjectOutputStream stream = new ObjectOutputStream(out);
        stream.writeObject(serializable);
        // do not close stream, as it would close out
        LOG.trace("Saved {} to {}", serializable, out);
    }
    
    /**
     * Restores an object from the specified file.
     * 
     * @since 1.13
     * @param <T> the generic object type
     * @param type the class literal of T used for typesafe casting
     * @param file the source file
     * @return the read instance of T
     * @throws NullPointerException if type or file is null
     * @throws IOException if restoring failed
     * @throws ClassNotFoundException if the read object is an instance of an unknown class
     */
    public static <T extends Serializable> T restore(Class<T> type, File file) 
        throws IOException, ClassNotFoundException {
        
        Preconditions.checkNotNull(type, "Type");
        Preconditions.checkNotNull(file, "File");
        final InputStream stream = new FileInputStream(file);
        try {
            return restore(type, stream);
        } finally {
            stream.close();
        }
    }
    
    /**
     * Restores an object from the specified input stream.
     * 
     * <p>
     *   The given input stream won't be closed.
     * </p>
     * 
     * @since 1.13
     * @param <T> the generic object type
     * @param type the class literal of T used for typesafe casting
     * @param in the source stream
     * @return the read instance of T
     * @throws NullPointerException if type or in is null
     * @throws IOException if restoring failed
     * @throws ClassNotFoundException if the read object is an instance of an unknown class
     */
    public static <T extends Serializable> T restore(Class<T> type, InputStream in) 
        throws IOException, ClassNotFoundException {
        
        Preconditions.checkNotNull(type, "Type");
        Preconditions.checkNotNull(in, "In");
        final ObjectInputStream stream = new ObjectInputStream(in);
        final Object object = stream.readObject();
        final T t = type.cast(object);
        LOG.trace("Restored {} from {}", object, stream);
        return t;
    }

}
