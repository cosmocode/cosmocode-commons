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

import com.google.common.base.Preconditions;

/**
 * Static utility class for {@link InputStream}s.
 *
 * @author Willi Schoenborn
 */
public final class InputStreams {

    private InputStreams() {
        
    }
    
    // TODO check for end of stream and exceptions
    public static InputStream asAutoClosing(InputStream stream) {
        Preconditions.checkNotNull(stream);
        return new AutoClosingInputStream(stream);
    }
    
    private static final class AutoClosingInputStream extends FilterInputStream {

        private AutoClosingInputStream(InputStream in) {
            super(in);
        }

        @Override
        public int read() throws IOException {
            final int read = super.read();
            if (read == -1) close();
            return read;
        }

        @Override
        public int read(byte[] b, int off, int len) throws IOException {
            final int read = super.read(b, off, len);
            if (read == -1) close();
            return read;
        }

        @Override
        public int read(byte[] b) throws IOException {
            final int read = super.read(b);
            if (read == -1) close();
            return read;
        }
        
    }
    
    public static InputStream asUncloseable(InputStream stream) {
        Preconditions.checkNotNull(stream);
        return new UncloseableInputStream(stream);
    }
    
    private static final class UncloseableInputStream extends FilterInputStream {

        private UncloseableInputStream(InputStream in) {
            super(in);
        }

        @Override
        public void close() throws IOException {
            
        }
        
    }

}
