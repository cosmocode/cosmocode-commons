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

import java.io.FilterInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Preconditions;

/**
 * Static utility class for {@link InputStream}s.
 *
 * @author Willi Schoenborn
 */
public final class InputStreams {
    
    private static final Logger LOG = LoggerFactory.getLogger(InputStreams.class);

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
     * Autoclosing inputstream implementation.
     *
     * @since 1.6
     * @see InputStreams#asAutoClosing(InputStream)
     * @author Willi Schoenborn
     */
    private static final class AutoClosingInputStream extends FilterInputStream {

        private final InputStream stream;
        
        private AutoClosingInputStream(InputStream stream) {
            super(stream);
            this.stream = stream;
        }

        @Override
        public int read() throws IOException {
            try {
                final int read = super.read();
                if (read == -1) endOfStream();
                return read;
            } catch (IOException e) {
                closeFinally();
                throw e;
            }
        }

        @Override
        public int read(byte[] b) throws IOException {
            try {
                final int read = super.read(b);
                if (read == -1) endOfStream();
                return read;
            } catch (IOException e) {
                closeFinally();
                throw e;
            }
        }
        
        @Override
        public long skip(long n) throws IOException {
            try {
                return super.skip(n);
            } catch (IOException e) {
                closeFinally();
                throw e;
            }
        }
        
        @Override
        public int available() throws IOException {
            try {
                return super.available();
            } catch (IOException e) {
                closeFinally();
                throw e;
            }
        }
        
        @Override
        public synchronized void reset() throws IOException {
            try {
                super.reset();
            } catch (IOException e) {
                closeFinally();
                throw e;
            }
        }
        
        private void endOfStream() throws IOException {
            close();
        }
        
        private void closeFinally() {
            try {
                close();
            } catch (IOException e) {
                LOG.warn("Unable to close " + this, e);
            }
        }
        
        @Override
        public String toString() {
            return String.format("InputStreams.asAutoClosing(%s)", stream);
        }
        
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
    public static InputStream asUncloseable(InputStream stream) {
        Preconditions.checkNotNull(stream);
        return new UncloseableInputStream(stream);
    }
    
    /**
     * Uncloseable inputstream implementation.
     *
     * @since 1.6
     * @see InputStreams#asUncloseable(InputStream)
     * @author Willi Schoenborn
     */
    private static final class UncloseableInputStream extends FilterInputStream {

        private final InputStream stream;

        private UncloseableInputStream(InputStream stream) {
            super(stream);
            this.stream = stream;
        }

        @Override
        public void close() throws IOException {
            
        }
        
        @Override
        public String toString() {
            return String.format("InputStreams.asUncloseable(%s)", stream);
        }
        
    }

}
