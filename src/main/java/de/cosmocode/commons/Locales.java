package de.cosmocode.commons;

import java.util.Locale;

/**
 * Utility class providing constant and
 * frequently used {@link Locale}s.
 *
 * @author Willi Schoenborn
 */
public final class Locales {

    public static final Locale SPANISH = new Locale("es");
    public static final Locale AUSTRIA = new Locale("de", "AT");
    
    /**
     * Prevent instantiation.
     */
    private Locales() {
        
    }
    
}
