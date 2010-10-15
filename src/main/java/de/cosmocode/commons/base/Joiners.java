package de.cosmocode.commons.base;

import com.google.common.base.Joiner;

/**
 * Static utility class for {@link Joiner}s.
 *
 * @since 1.19
 * @author Willi Schoenborn
 */
public final class Joiners {

    public static final Joiner NEW_LINE = Joiner.on('\n');
    
    public static final Joiner BLANK = Joiner.on(" ");
    
    public static final Joiner COMMA = Joiner.on(',');
    
    private Joiners() {
        
    }
    
}
