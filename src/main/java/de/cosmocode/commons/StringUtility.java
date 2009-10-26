package de.cosmocode.commons;

import java.util.Collection;
import java.util.Iterator;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class StringUtility {
    
    private static final Logger log = LoggerFactory.getLogger(StringUtility.class);
    
    public static final String DEFAULT_DELIMITER = " ";

    /**
     * Joines instances of a collection
     * into a single string instance,
     * using a parametrizable JoinWalker
     * which transforms instances of T into Strings.
     * 
     * Calling this method is equivalent to
     * {@code StringUtility.join(collection, " ", walker}
     * 
     * @param <T>
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
     * @param <T>
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
    
    public static boolean isNumeric(String s) {
        return StringUtils.isNotBlank(s) && StringUtils.isNumeric(s);
    }
    
    public static String defaultIfBlank(String s) {
        return defaultIfBlank(s, null);
    }
    
    public static String defaultIfBlank(String s, String defaultValue) {
        return StringUtils.isBlank(s) ? defaultValue : s;
    }
    
    /**
     * Prevent instantiation
     */
    private StringUtility() {
        
    }
    
}