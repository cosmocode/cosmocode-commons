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

import java.io.InputStream;

import com.google.common.base.Preconditions;

/**
 * Static utility class for {@link InputStream}s.
 *
 * @author Willi Schoenborn
 */
public final class InputStreams {
    
    private InputStreams() {
        
    }

    /**
     * Decorates the given stream to provide auto-close behaviour. The
     * returned stream will close itself when either the end of
     * the stream is reached (any of the read methods returned -1) or
     * an exception occured. In an exception case the original exception
     * will be thrown, any exception which occured during close will
     * be logged and suppressed. This does not apply when exception
     * occur when attempting to close the stream after the end has
     * been reached.
     * 
     * @since 1.6
     * @param stream the backing stream
     * @return a stream which auto closes itself
     * @throws NullPointerException if stream is null
     */
    public static InputStream asAutoClosing(InputStream stream) {
        Preconditions.checkNotNull(stream);
        return new AutoClosingInputStream(stream);
    }
    
    /**
     * Decorates the given stream to provide "uncloseable" behaviour.
     * The returned stream will return immediately when the {@link InputStream#close() close}
     * method gets called.
     * 
     * @deprecated use {@link #asUnclosable(InputStream)}
     * @since 1.6
     * @param stream the backing stream
     * @return a stream which is not closeable
     * @throws NullPointerException if stream is null
     */
    @Deprecated
    public static InputStream asUncloseable(InputStream stream) {
        return new UnclosableInputStream(stream);
    }
    
    /**
     * Decorates the given stream to provide "uncloseable" behaviour.
     * The returned stream will return immediately when the {@link InputStream#close() close}
     * method gets called.
     * 
     * @since 1.6
     * @param stream the backing stream
     * @return a stream which is not closeable
     * @throws NullPointerException if stream is null
     */
    public static InputStream asUnclosable(InputStream stream) {
        return new UnclosableInputStream(stream);
    }

    /**
     * Returns an empty input stream. which Just returns {@code -1} on every
     * call to {@link InputStream#read()}, {@link InputStream#read(byte[])} and
     * {@link InputStream#read(byte[], int, int)}.
     * 
     * @since 1.13
     * @return an empty input stream
     */
    public static InputStream empty() {
        return EmptyInputStream.INSTANCE;
    }
    
}
