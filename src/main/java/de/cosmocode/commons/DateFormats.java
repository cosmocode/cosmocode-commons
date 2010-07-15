/**
 * Copyright 2010 CosmoCode GmbH
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package de.cosmocode.commons;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Locale;

/**
 * Utility class providing useful {@link DateFormat}s.
 * 
 * @author schoenborn@cosmocode.de
 */
public final class DateFormats {

    /**
     * DateFormat for ISO 8061.
     * 
     * @deprecated use {@link DateFormats#iso8061()} instead
     */
    @Deprecated
    public static final DateFormat ISO_8061 = DateFormats.concurrent(
        new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz")
    );

    /**
     * DateFormat for RFC 822.
     * 
     * @deprecated use {@link DateFormats#rfc822()()} instead
     */
    @Deprecated
    public static final DateFormat RFC_822 = DateFormats.concurrent(
        new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US)
    );

    /**
     * DateFormat for RSS 2.0.
     * 
     * @deprecated use {@link DateFormats#rss20()} instead
     */
    @Deprecated
    public static final DateFormat RSS_20 = DateFormats.concurrent(
        new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z")
    );
    
    /**
     * Prevent instantiation.
     */
    private DateFormats() {
        
    }
    
    /**
     * Decorates a {@link DateFormat} to make it threadsafe.
     * 
     * <p>
     *   <strong>Note:</strong> 
     *   This implementation uses synchronization to achieve
     *   thread safety. Consider implementing an immutable version.
     * <p>
     * 
     * @deprecated use a separate {@link DateFormat} for each thread, this implementation uses synchronization
     * @param format the {@link DateFormat} to decorate
     * @return a threadsafe version of the {@link DateFormat}
     */
    @Deprecated
    public static DateFormat concurrent(DateFormat format) {
        if (format instanceof SynchronizedDateFormat) {
            return SynchronizedDateFormat.class.cast(format);
        } else {
            return new SynchronizedDateFormat(format);
        }
    }

    /**
     * DateFormat for ISO 8061.
     *
     * @return ISO 8061 {@link DateFormat}
     */
    public static DateFormat iso8061() {
        return new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz");
    }

    /**
     * DateFormat for RFC 822.
     *
     * @return RFC 822 {@link DateFormat}
     */
    public static DateFormat rfc822() {
        return new SimpleDateFormat("EEE', 'dd' 'MMM' 'yyyy' 'HH:mm:ss' 'Z", Locale.US);
    }

    /**
     * DateFormat for RSS 2.0.
     *
     * @return RSS 2.0 {@link DateFormat}
     */
    public static DateFormat rss20() {
        return new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss z");
    }
    
}
