package de.cosmocode.commons;

import java.util.regex.Pattern;

public final class Patterns {
    
    /**
     * ISO 3166-1 alpha-2 codes are two-letter country codes defined in ISO 3166-1, part of the
     * ISO 3166 standard published by the International Organization for Standardization (ISO), 
     * to represent countries, dependent territories, and special areas of geographical interest.
     * 
     * @see <a href="http://en.wikipedia.org/wiki/ISO_3166-1_alpha-2">Wikpedia</a>
     */
    public static final Pattern ISO_3166_1_ALPHA_2 = Pattern.compile("^[A-Z]{2}$");
    
    /**
     * ISO 639-1 is the first part of the ISO 639 international-standard language-code family.
     * It consists of 136 two-letter codes used to identify the world's major languages. 
     * 
     * @see <a href="http://en.wikipedia.org/wiki/ISO_639-1">Wikipedia</a>
     */
    public static final Pattern ISO_639_1 = Pattern.compile("^[a-z]{2}$");
    
    /**
     * An IATA airport code, also known an IATA location identifier, IATA station code or 
     * simply a location identifier, is a three-letter code designating many airports around 
     * the world, defined by the International Air Transport Association (IATA).
     * 
     * @see <a href="http://en.wikipedia.org/wiki/International_Air_Transport_Association_airport_code">Wikipedia</a>
     */
    public static final Pattern IATA_AIRPORT_CODE = Pattern.compile("^[A-Z]{3}$");

    /**
     * Prevent instantiation
     */
    private Patterns() {
        
    }
    
}