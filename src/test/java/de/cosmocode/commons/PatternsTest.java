package de.cosmocode.commons;

import org.junit.Test;

import de.cosmocode.junit.Asserts;

public class PatternsTest {

    @Test
    public void iso3166Lower() {
        Asserts.assertDoesNotMatch(Patterns.ISO_3166_1_ALPHA_2, "de");
    }
    
    @Test
    public void iso3166One() {
        Asserts.assertDoesNotMatch(Patterns.ISO_3166_1_ALPHA_2, "D");
    }
    
    @Test
    public void iso3166Three() {
        Asserts.assertDoesNotMatch(Patterns.ISO_3166_1_ALPHA_2, "DEU");
    }
    
    @Test
    public void iso3166Many() {
        Asserts.assertDoesNotMatch(Patterns.ISO_3166_1_ALPHA_2, "deutschland");
    }
    
    @Test
    public void iso3166Numeric() {
        Asserts.assertDoesNotMatch(Patterns.ISO_3166_1_ALPHA_2, "12");
    }
    
    @Test
    public void iso3166() {
        Asserts.assertMatches(Patterns.ISO_3166_1_ALPHA_2, "DE");
    }
    
    @Test
    public void iso639Upper() {
        Asserts.assertDoesNotMatch(Patterns.ISO_639_1, "DE");
    }
    
    @Test
    public void iso639One() {
        Asserts.assertDoesNotMatch(Patterns.ISO_639_1, "d");
    }
    
    @Test
    public void iso639Three() {
        Asserts.assertDoesNotMatch(Patterns.ISO_639_1, "deu");
    }
    
    @Test
    public void iso639Many() {
        Asserts.assertDoesNotMatch(Patterns.ISO_639_1, "deutsch");
    }
    
    @Test
    public void iso639Numeric() {
        Asserts.assertDoesNotMatch(Patterns.ISO_639_1, "12");
    }
    
    @Test
    public void iso639() {
        Asserts.assertMatches(Patterns.ISO_639_1, "de");
    }
    
    @Test
    public void iataLower() {
        Asserts.assertDoesNotMatch(Patterns.IATA_AIRPORT_CODE, "txl");
    }
    
    @Test
    public void iataTwo() {
        Asserts.assertDoesNotMatch(Patterns.IATA_AIRPORT_CODE, "tx");
    }
    
    @Test
    public void iataFour() {
        Asserts.assertDoesNotMatch(Patterns.IATA_AIRPORT_CODE, "txlx");
    }
    
    @Test
    public void iataNumeric() {
        Asserts.assertDoesNotMatch(Patterns.IATA_AIRPORT_CODE, "123");
    }
    
    @Test
    public void iata() {
        Asserts.assertMatches(Patterns.IATA_AIRPORT_CODE, "TXL");
    }
    
}
