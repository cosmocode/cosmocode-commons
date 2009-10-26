package de.cosmocode.commons;

import static de.cosmocode.junit.Asserts.assertDoesNotMatch;
import static de.cosmocode.junit.Asserts.assertMatches;

import org.junit.Test;

public class PatternsTest {

    @Test
    public void iso3166Lower() {
        assertDoesNotMatch(Patterns.ISO_3166_1_ALPHA_2, "de");
    }
    
    @Test
    public void iso3166One() {
        assertDoesNotMatch(Patterns.ISO_3166_1_ALPHA_2, "D");
    }
    
    @Test
    public void iso3166Three() {
        assertDoesNotMatch(Patterns.ISO_3166_1_ALPHA_2, "DEU");
    }
    
    @Test
    public void iso3166Many() {
        assertDoesNotMatch(Patterns.ISO_3166_1_ALPHA_2, "deutschland");
    }
    
    @Test
    public void iso3166Numeric() {
        assertDoesNotMatch(Patterns.ISO_3166_1_ALPHA_2, "12");
    }
    
    @Test
    public void iso3166() {
        assertMatches(Patterns.ISO_3166_1_ALPHA_2, "DE");
    }
    
    @Test
    public void iso639Upper() {
        assertDoesNotMatch(Patterns.ISO_639_1, "DE");
    }
    
    @Test
    public void iso639One() {
        assertDoesNotMatch(Patterns.ISO_639_1, "d");
    }
    
    @Test
    public void iso639Three() {
        assertDoesNotMatch(Patterns.ISO_639_1, "deu");
    }
    
    @Test
    public void iso639Many() {
        assertDoesNotMatch(Patterns.ISO_639_1, "deutsch");
    }
    
    @Test
    public void iso639Numeric() {
        assertDoesNotMatch(Patterns.ISO_639_1, "12");
    }
    
    @Test
    public void iso639() {
        assertMatches(Patterns.ISO_639_1, "de");
    }
    
    @Test
    public void iataLower() {
        assertDoesNotMatch(Patterns.IATA_AIRPORT_CODE, "txl");
    }
    
    @Test
    public void iataTwo() {
        assertDoesNotMatch(Patterns.IATA_AIRPORT_CODE, "tx");
    }
    
    @Test
    public void iataFour() {
        assertDoesNotMatch(Patterns.IATA_AIRPORT_CODE, "txlx");
    }
    
    @Test
    public void iataNumeric() {
        assertDoesNotMatch(Patterns.IATA_AIRPORT_CODE, "123");
    }
    
    @Test
    public void iata() {
        assertMatches(Patterns.IATA_AIRPORT_CODE, "TXL");
    }
    
}