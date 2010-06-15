package de.cosmocode.commons.reflect;

import org.junit.Test;

import com.google.common.base.Predicates;

/**
 * Tests {@link Reflection}.
 *
 * @since 1.8
 * @author Willi Schoenborn
 */
public final class ReflectionTest {

    /**
     * Tests {@link Reflection#defaultClasspath()}.
     */
    @Test
    public void defaultClasspath() {
        final Packages packages = Reflection.defaultClasspath().restrictTo("de.cosmocode.palava");
        packages.filter(Number.class, Predicates.notNull());
    }

}
