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

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import com.google.common.base.Preconditions;

/**
 * Static utility class for {@link ByteBuffer}s.
 *
 * @author Willi Schoenborn
 */
public final class ByteBuffers {

    private ByteBuffers() {
        
    }
    
    /**
     * Adapts a {@link ByteBuffer} to an {@link InputStream}.
     * 
     * @param buffer the underlying byte buffer
     * @return an inputstream which reads from the given byte buffer
     * @throws NullPointerException if buffer is null
     */
    public static InputStream asInputStream(final ByteBuffer buffer) {
        Preconditions.checkNotNull(buffer, "Buffer");
        return new InputStream() {
            
            @Override
            public synchronized int read() throws IOException {
                if (buffer.hasRemaining()) {
                    return buffer.get();
                } else {
                    return -1;
                }
            }
            
            @Override
            public synchronized int read(byte[] b, int off, int len) throws IOException {
                if (buffer.hasRemaining()) {
                    final int length = Math.min(len, buffer.remaining());
                    buffer.get(b, off, length);
                    return length;
                } else {
                    return -1;
                }
            }
            
        };
    }

    /**
     * Adapts a {@link ByteBuffer} to an {@link OutputStream}.
     * 
     * @param buffer the underlying byte buffer
     * @return an outputstream which writes to the given byte buffer
     * @throws NullPointerException if buffer is null
     */
    public static OutputStream asOutputStream(final ByteBuffer buffer) {
        Preconditions.checkNotNull(buffer, "Buffer");
        return new OutputStream() {
            
            @Override
            public synchronized void write(int b) throws IOException {
                buffer.put((byte) b);
            }
            
            @Override
            public synchronized void write(byte[] b, int off, int len) throws IOException {
                buffer.put(b, off, len);
            }
            
        };
    }
    
}
