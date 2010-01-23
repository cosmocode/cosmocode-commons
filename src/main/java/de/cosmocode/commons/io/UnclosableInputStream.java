package de.cosmocode.commons.io;

import java.io.FilterInputStream;
import java.io.InputStream;

import com.google.common.base.Preconditions;

/**
 * An {@link InputStream} which is not closable.
 *
 * @author Willi Schoenborn
 */
public final class UnclosableInputStream extends FilterInputStream {

    public UnclosableInputStream(InputStream input) {
        super(Preconditions.checkNotNull(input, "Input"));
    }

    /**
     * Returns <strong>without</strong> closing.
     */
    @Override
    public void close() {
        // we don't close
    }

}
