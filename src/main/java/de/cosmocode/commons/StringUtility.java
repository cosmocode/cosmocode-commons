package de.cosmocode.commons;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Utility class inspired by {@link StringUtils},
 * providing {@link String} related helper methods.
 *
 * @author Willi Schoenborn
 */
public final class StringUtility {
    
    public static final String DEFAULT_DELIMITER = " ";

    private static final Logger log = LoggerFactory.getLogger(StringUtility.class);
    
    /**
     * Prevent instantiation.
     */
    private StringUtility() {
        
    }
    
    /**
     * Joines instances of a collection
     * into a single string instance,
     * using a parametrizable JoinWalker
     * which transforms instances of T into Strings.
     * 
     * Calling this method is equivalent to
     * {@code StringUtility.join(collection, " ", walker}
     * 
     * @param <T> the generic type
     * @param collection the element provider
     * @param walker the function object which transform instances of T into strings
     * @throws NullPointerException if collection is null or collection is empty and walker is null
     * @return the joined collection as a string
     */
    public static <T> String join(Collection<? extends T> collection, JoinWalker<? super T> walker) {
        return join(collection, DEFAULT_DELIMITER, walker);
    }

    /**
     * Joines instances of a collection
     * into a single string instance,
     * using a parametrizable delimeter and
     * a JoinWalker which transforms instances
     * of T into Strings.
     * 
     * @param <T> the generic type
     * @param collection the element provider
     * @param delimiter the string betweens joined elements of collection
     * @param walker the function object which transform instances of T into strings
     * @throws NullPointerException if collection is null or collection is empty and walker is null
     * @return the joined collection as a string
     */
    public static <T> String join(Collection<? extends T> collection, String delimiter, JoinWalker<? super T> walker) {
        log.debug("Joining collection {} with delimiter '{}'", collection, delimiter);
        final StringBuilder builder = new StringBuilder();

        final Iterator<? extends T> iterator = collection.iterator();
        while (iterator.hasNext()) {
            final String value = walker.walk(iterator.next());
            log.debug("Walker returned {}", value);
            builder.append(value);
            if (iterator.hasNext()) builder.append(delimiter);
        }
        
        final String returnValue = builder.toString();
        log.debug("Joined collection: {}", returnValue);
        return returnValue;
    }
    
    /**
     * Checks whether a given {@link String} is a valid
     * number, containing only digits.
     * This implementation is performing a blank-check
     * before using {@link StringUtils#isNumeric(String)}.
     * 
     * <pre>
     * StringUtility.isNumeric(null)   = false
     * StringUtility.isNumeric("")     = false
     * StringUtility.isNumeric("  ")   = false
     * StringUtility.isNumeric("123")  = true
     * StringUtility.isNumeric("12 3") = false
     * StringUtility.isNumeric("ab2c") = false
     * StringUtility.isNumeric("12-3") = false
     * StringUtility.isNumeric("12.3") = false
     * </pre>
     * 
     * @param s the String to check, may be null
     * @return true if s is not blank and contains only digits
     */
    public static boolean isNumeric(String s) {
        return StringUtils.isNotBlank(s) && StringUtils.isNumeric(s);
    }
    
    /**
     * Checks whether s is blank according
     * to {@link StringUtils#isBlank(String)} and
     * returns null in this case.
     * 
     * <p>
     *   Using this method is equivalent to calling
     *   {@link StringUtility#defaultIfBlank(String, String)}
     *   with s and null.
     * </p>
     * 
     * @param s the string to check
     * @return null if s is blank, s otherwise
     */
    public static String defaultIfBlank(String s) {
        return defaultIfBlank(s, null);
    }
    
    /**
     * Checks whether s is blank according
     * to {@link StringUtils#isBlank(String)} and
     * returns the default value in this case.
     * 
     * @param s the string to check
     * @param defaultValue the default value to return in case s is blank
     * @return defaultValue if s is blank, s otherwise
     */
    public static String defaultIfBlank(String s, String defaultValue) {
        return StringUtils.isBlank(s) ? defaultValue : s;
    }
    
}
