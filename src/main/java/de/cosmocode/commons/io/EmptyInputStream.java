package de.cosmocode.commons.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * {@link InputStream} implementation which mimics the behaviour of an empty stream.
 *
 * @since 1.13
 * @author Willi Schoenborn
 */
final class EmptyInputStream extends InputStream {

    private static final InputStream INSTANCE = new EmptyInputStream();
    
    private EmptyInputStream() {
        
    }
    
    @Override
    public int read() throws IOException {
        return -1;
    }
    
    @Override
    public int read(byte[] b) throws IOException {
        return -1;
    }
    
    @Override
    public int read(byte[] b, int off, int len) throws IOException {
        return -1;
    }
    
    @Override
    public long skip(long n) throws IOException {
        return 0;
    }
    
    @Override
    public String toString() {
        return "InputStreams.empty()";
    }

    public static InputStream getInstance() {
        return INSTANCE;
    }

}
