package de.cosmocode.commons;

import com.google.common.base.Function;

/**
 * Implementation for {@link Strings#toLowerCase()}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
enum StringLowerCaseFunction implements Function<String, String> {
    
    INSTANCE;

    @Override
    public String apply(String from) {
        return from == null ? null : from.toLowerCase();
    }
    
    @Override
    public String toString() {
        return "Strings.toLowerCase()";
    }
    
}
