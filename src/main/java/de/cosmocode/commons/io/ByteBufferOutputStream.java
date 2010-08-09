package de.cosmocode.commons.io;

import java.io.IOException;
import java.io.OutputStream;
import java.nio.ByteBuffer;

import com.google.common.base.Preconditions;

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
        return String.format("ByteBuffers.asOutputStream(%s)", buffer);
    }
    
}
