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
import java.io.OutputStream;
import java.nio.ByteBuffer;

/**
 * {@link ByteBuffer} backed {@link OutputStream}.
 *
 * @since 1.13
 * @author Willi Schoenborn
 */
final class ByteBufferOutputStream extends OutputStream {
    
    private final ByteBuffer buffer;

    public ByteBufferOutputStream(ByteBuffer buffer) {
        this.buffer = Preconditions.checkNotNull(buffer, "Buffer");
    }

    @Override
    public void write(int b) throws IOException {
        buffer.put((byte) b);
    }

    @Override
    public void write(byte[] b, int off, int len) throws IOException {
        buffer.put(b, off, len);
    }

    @Override
    public String toString() {
        return "ByteBuffers.asOutputStream(" + buffer + ")";
    }
    
}
