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
package de.cosmocode.commons.io;

import com.google.common.base.Preconditions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * Autoclosing inputstream implementation.
 *
 * @since 1.6
 * @see InputStreams#asAutoClosing(InputStream)
 * @author Willi Schoenborn
 */
final class AutoClosingInputStream extends InputStream {
    
    private static final Logger LOG = LoggerFactory.getLogger(AutoClosingInputStream.class);

    private final InputStream stream;
    
    public AutoClosingInputStream(InputStream stream) {
        this.stream = Preconditions.checkNotNull(stream, "Stream");
    }

    @Override
    public int read() throws IOException {
        try {
            final int read = stream.read();
            if (read == -1) close();
            return read;
        } catch (IOException e) {
            closeFinally();
            throw e;
        }
    }

    @Override
    public int read(byte[] b) throws IOException {
        try {
            final int read = stream.read(b);
            if (read == -1) close();
            return read;
        } catch (IOException e) {
            closeFinally();
            throw e;
        }
    }
    
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        try {
            final int read = stream.read(b, off, len);
            if (read == -1) close();
            return read;
        } catch (IOException e) {
            closeFinally();
            throw e;
        }
    }

    
    @Override
    public long skip(long n) throws IOException {
        try {
            return stream.skip(n);
        } catch (IOException e) {
            closeFinally();
            throw e;
        }
    }
    
    @Override
    public int available() throws IOException {
        try {
            return stream.available();
        } catch (IOException e) {
            closeFinally();
            throw e;
        }
    }
    
    
    @Override
    public void close() throws IOException {
        stream.close();
    }

    @Override
    public void mark(int readlimit) {
        stream.mark(readlimit);
    }

    @Override
    public boolean markSupported() {
        return stream.markSupported();
    }

    @Override
    public void reset() throws IOException {
        try {
            stream.reset();
        } catch (IOException e) {
            closeFinally();
            throw e;
        }
    }
    
    private void closeFinally() {
        try {
            close();
        } catch (IOException e) {
            LOG.warn("Unable to close " + stream, e);
        }
    }
    
    @Override
    public String toString() {
        return "InputStreams.asAutoClosing(" + stream + ")";
    }
    
}
