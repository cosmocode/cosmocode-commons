package de.cosmocode.commons.validation;

import org.junit.Assert;
import org.junit.Test;

import de.cosmocode.commons.Strings;

/**
 * Tests {@link Rule}.
 *
 * @since 1.9
 * @author Willi Schoenborn
 */
public final class RuleTest {

    /**
     * Tests {@link Rule#and(Rule)}.
     */
    @Test
    public void and() {
        final Rule<String> aAndB = Strings.contains("a").and(Strings.contains("b"));
        Assert.assertTrue(aAndB.apply("a and b are great"));
        Assert.assertFalse(aAndB.apply("a"));
        Assert.assertFalse(aAndB.apply("b without the other is not complete"));
    }

}
