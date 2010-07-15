package de.cosmocode.commons;

import com.google.common.base.Function;

/**
 * Implementation for {@link Strings#toUpperCase()}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
enum StringUpperCaseFunction implements Function<String, String> {

    INSTANCE;
    
    @Override
    public String apply(String from) {
        return from == null ? null : from.toUpperCase();
    }
    
    @Override
    public String toString() {
        return "Strings.toUpperCase()";
    }
    
}
