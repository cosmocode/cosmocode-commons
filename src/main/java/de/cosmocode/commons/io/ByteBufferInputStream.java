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

import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/**
 * {@link ByteBuffer} backed {@link InputStream}.
 *
 * @since 1.13
 * @author Willi Schoenborn
 */
final class ByteBufferInputStream extends InputStream {
    
    private final ByteBuffer buffer;

    public ByteBufferInputStream(ByteBuffer buffer) {
        this.buffer = Preconditions.checkNotNull(buffer, "Buffer");
    }

    @Override
    public int read() throws IOException {
        if (buffer.hasRemaining()) {
            return buffer.get();
        } else {
            return -1;
        }
    }
    
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        if (buffer.hasRemaining()) {
            final int length = Math.min(len, buffer.remaining());
            buffer.get(b, off, length);
            return length;
        } else {
            return -1;
        }
    }
    
    @Override
    public boolean markSupported() {
        return true;
    }
    
    @Override
    public void mark(int readlimit) {
        buffer.mark();
    }
    
    @Override
    public int available() throws IOException {
        return buffer.remaining();
    }
    
    @Override
    public void reset() throws IOException {
        buffer.reset();
    }
    
    @Override
    public long skip(long n) throws IOException {
        final int skipped = (int) (Math.min(n, buffer.remaining()));
        buffer.position(skipped);
        return skipped;
    }
    
    @Override
    public String toString() {
        return "ByteBuffers.asInputStream(" + buffer + ")";
    }
    
}
