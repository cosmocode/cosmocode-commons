package de.cosmocode.commons.concurrent;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.TimeUnit;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.Ordering;

/**
 * Static utility class for {@link TimeUnit}s.
 *
 * @author Willi Schoenborn
 */
public final class TimeUnits {

    private static final ImmutableList<TimeUnit> REVERSED;
    
    static {
        final Ordering<TimeUnit> ordering = Ordering.natural().reverse();
        final List<TimeUnit> list = Arrays.asList(TimeUnit.values());
        final List<TimeUnit> sorted = ordering.sortedCopy(list);
        REVERSED = ImmutableList.copyOf(sorted);
    }
    
    private TimeUnits() {
        
    }
    
    /**
     * Attempts to find a {@link TimeUnit} in which the given
     * time can be presented in a human friendly way.

     * <p>
     *   Note: This function does not produce accurate results.
     * </p>
     * 
     * @param source the source time
     * @param sourceUnit the unit of source
     * @return a time unit for mere mortals
     * @throws NullPointerException if sourceUnit is null
     */
    public static TimeUnit forMortals(long source, TimeUnit sourceUnit) {
        Preconditions.checkNotNull(sourceUnit, "SourceUnit");
        for (TimeUnit unit : REVERSED) {
            if (unit.convert(source, sourceUnit) > 0) {
                return unit;
            }
        }
        return sourceUnit;
    }

}
