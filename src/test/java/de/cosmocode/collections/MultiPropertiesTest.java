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

package de.cosmocode.collections;

import java.io.File;
import java.io.IOException;
import java.util.Properties;

import org.junit.Assert;
import org.junit.Test;

/**
 * Tests {@link MultiProperties} with two files.
 *
 * @author Willi Schoenborn
 */
public final class MultiPropertiesTest {

    /**
     * Tests {@link MultiProperties#load(File...)} with two files.
     * 
     * @throws IOException should not happen
     */
    @Test
    public void load() throws IOException {
        final File a = new File("src/test/resources/a.properties");
        final File b = new File("src/test/resources/b.properties");
        final Properties properties = MultiProperties.load(a, b);
        
        Assert.assertEquals("v1", properties.getProperty("k1"));
        Assert.assertEquals("somethingelse", properties.getProperty("k2"));
        Assert.assertEquals("v3", properties.getProperty("k3"));
    }
    
}
