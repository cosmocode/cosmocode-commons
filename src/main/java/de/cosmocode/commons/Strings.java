package de.cosmocode.commons;

import java.util.Collection;

import org.apache.commons.lang.StringUtils;

import com.google.common.base.Function;
import com.google.common.base.Joiner;
import com.google.common.collect.Iterables;

/**
 * Utility class inspired by {@link StringUtils},
 * providing {@link String} related helper methods.
 *
 * @author Willi Schoenborn
 */
public final class Strings {
    
    public static final String DEFAULT_DELIMITER = " ";

    /**
     * Prevent instantiation.
     */
    private Strings() {
        
    }
    
    private static <T> Function<T, String> asFunction(final JoinWalker<? super T> walker) {
        return new Function<T, String>() {
            
            @Override
            public String apply(T from) {
                return walker.walk(from);
            };
            
        };
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
     * @deprecated use {@link Joiner}, {@link Function} and {@link Iterables#transform(Iterable, Function)} instead
     * 
     * @param <T> the generic type
     * @param collection the element provider
     * @param walker the function object which transform instances of T into strings
     * @throws NullPointerException if collection is null or collection is empty and walker is null
     * @return the joined collection as a string
     */
    @Deprecated
    public static <T> String join(Collection<? extends T> collection, JoinWalker<? super T> walker) {
        return Strings.join(collection, DEFAULT_DELIMITER, walker);
    }

    /**
     * Joines instances of a collection
     * into a single string instance,
     * using a parametrizable delimeter and
     * a JoinWalker which transforms instances
     * of T into Strings.
     * 
     * @deprecated use {@link Joiner}, {@link Function} and {@link Iterables#transform(Iterable, Function)} instead
     * 
     * @param <T> the generic type
     * @param collection the element provider
     * @param delimiter the string betweens joined elements of collection
     * @param walker the function object which transform instances of T into strings
     * @throws NullPointerException if collection is null or collection is empty and walker is null
     * @return the joined collection as a string
     */
    @Deprecated
    public static <T> String join(Collection<? extends T> collection, String delimiter, JoinWalker<? super T> walker) {
        return Joiner.on(delimiter).useForNull("null").join(Iterables.transform(collection, asFunction(walker)));
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
     *   {@link Strings#defaultIfBlank(String, String)}
     *   with s and null.
     * </p>
     * 
     * @param s the string to check
     * @return null if s is blank, s otherwise
     */
    public static String defaultIfBlank(String s) {
        return Strings.defaultIfBlank(s, null);
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
