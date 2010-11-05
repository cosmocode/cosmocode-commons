package de.cosmocode.commons.io;

import java.io.Closeable;
import java.util.Iterator;

/**
 * A {@link Closeable} {@link Iterator}.
 *
 * @since 1.19
 * @author Willi Schoenborn
 * @param <T> generic value type
 */
public interface CloseableIterator<T> extends Iterator<T>, Closeable {

}
