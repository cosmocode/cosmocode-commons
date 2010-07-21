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

package de.cosmocode.collections.tree;

import junit.framework.Assert;

import org.junit.Test;

/**
 * Tests {@link ImmutableTree}.
 * 
 * @author Oliver Lorenz
 */
public class ImmutableTest {
    
    /**
     * Tries to create a new {@link ImmutableTree} from an existing {@link Tree}.
     */
    @Test
    public void copyTree() {
        final Tree<String> newTree = new DefaultTree<String>();
        newTree.addChildren("child-1", "child-2", "child-3", "child-4");
        
        final ImmutableTree<String> immutableTree = new ImmutableTree<String>(newTree);
        Assert.assertEquals("Not enough children", 4, immutableTree.getNumberOfChildren());
        for (TreeNode<String> child : immutableTree.getChildren()) {
            Assert.assertTrue("ChildNode is not an ImmutableNode", child instanceof ImmutableNode<?>);
        }
    }

}
