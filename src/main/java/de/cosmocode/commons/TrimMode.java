package de.cosmocode.commons;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Function;
import com.google.common.base.Predicate;

/**
 * The {@link TrimMode} determines
 * how a {@link String} is being trimmed.
 * 
 * @author Willi Schoenborn
 */
public enum TrimMode implements Predicate<String>, Function<CharSequence, CharSequence> {

    /**
     * Removes control characters (char &lt;= 32) from both
     * ends of this String, handling {@code null} by returning
     * {@code null}.
     * 
     * See {@link StringUtils#trim(String)} for more details.
     */
    NORMAL {
        
        @Override
        public String trim(String s) {
            return StringUtils.trim(s);
        }
        
    }, 
    
    /**
     * Removes control characters (char &lt;= 32) from both
     * ends of this String returning an empty String ("") if the String
     * is empty ("") after the trim or if it is {@code null}.
     * 
     * See {@link StringUtils#trimToEmpty(String)} for more details.
     */
    EMPTY {
        
        @Override
        public String trim(String s) {
            return StringUtils.trimToEmpty(s);
        }
        
    },
    
    /**
     * Removes control characters (char &lt;= 32) from both
     * ends of this String returning {@code null} if the String is
     * empty ("") after the trim or if it is {@code null}.
     * 
     * See {@link StringUtils#trimToNull(String)} for more details.
     */
    NULL {
        
        @Override
        public String trim(String s) {
            return StringUtils.trimToNull(s);
        }
        
    };
    
    /**
     * Trims the given {@link String}.
     * 
     * @param s the {@link String} being trimmed
     * @return the trimmed version of s
     */
    public abstract String trim(String s);

    /**
     * Trims the given {@link CharSequence}.
     * 
     * @param sequence the {@link CharSequence} being trimmed
     * @return the trimmed version of sequence
     */
    public CharSequence trim(CharSequence sequence) {
        return trim(sequence == null ? null : sequence.toString());
    }
    
    /**
     * This is a kind of an adapter allowing
     * it to use a {@link TrimMode} as a {@link Function}.
     * 
     * <p>
     *   This method is delegating its work to {@link TrimMode#trim(CharSequence)}.
     * </p>
     * 
     * {@inheritDoc}
     */
    @Override
    public CharSequence apply(CharSequence from) {
        return trim(from);
    }
    
    /**
     * This implementation returns true if the given input is
     * trimmed using the semantics of this {@link TrimMode}.
     * 
     * {@inheritDoc}
     */
    @Override
    public boolean apply(String input) {
        return trim(input) == input;
    };
    
}
