package de.cosmocode.commons.validation;

import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Test;

import com.google.common.base.Predicate;

/**
 * Tests {@link Rules}.
 *
 * @author Willi Schoenborn
 */
public final class RulesTest {

    /**
     * Tests {@link Rules#asRule(Predicate)}.
     */
    @Test
    public void asRule() {
        final Predicate<String> predicate = new Predicate<String>() {
            
            @Override
            public boolean apply(String input) {
                return input.isEmpty();
            }
            
        };
        
        final Rule<String> rule = Rules.asRule(predicate);
        
        Assert.assertTrue(rule.apply(""));
        Assert.assertFalse(rule.not().apply(""));
        Assert.assertTrue(rule.not().not().apply(""));
    }
    
    /**
     * Tests {@link Rules#eq(Object)}.
     */
    @Test
    public void eq() {
        final Rule<TimeUnit> isSeconds = Rules.eq(TimeUnit.SECONDS);
        Assert.assertFalse(isSeconds.apply(TimeUnit.MILLISECONDS));
        Assert.assertTrue(isSeconds.apply(TimeUnit.SECONDS));
        Assert.assertFalse(isSeconds.apply(TimeUnit.DAYS));
    }

}
