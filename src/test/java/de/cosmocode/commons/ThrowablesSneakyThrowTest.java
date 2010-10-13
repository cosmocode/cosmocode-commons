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

import java.io.IOException;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link Throwables#sneakyThrow(Throwable)}.
 *
 * @since 1.18
 * @author Willi Schoenborn
 */
public final class ThrowablesSneakyThrowTest {

    /**
     * Tests {@link Throwables#sneakyThrow(Throwable)}.
     */
    @Test
    public void test() {
        final IOException e = new IOException();
        try {
            Throwables.sneakyThrow(e);
            Assert.fail("Should never get here.");
        /* CHECKSTYLE:OFF */
        } catch (Throwable t) {
        /* CHECKSTYLE:ON */
            Assert.assertSame(t, e);
            return;
        }
        Assert.fail("Should never get here.");
    }
    
}
