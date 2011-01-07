package de.cosmocode.commons.base;

import com.google.common.base.Splitter;

/**
 * Static utility class for {@link Splitter}s.
 *
 * @since 1.21
 * @author Willi Schoenborn
 */
public final class Splitters {

    public static final Splitter NEW_LINE = Splitter.on('\n');
    
    public static final Splitter SPACE = Splitter.on(' ');
    
    public static final Splitter COMMA = Splitter.on(',');
    
    public static final Splitter COLON = Splitter.on(':');
    
    public static final Splitter SEMICOLON = Splitter.on(';');
    
    private Splitters() {
        
    }
    
}
