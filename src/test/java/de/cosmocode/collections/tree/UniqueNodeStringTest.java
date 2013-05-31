/**
 * Copyright 2010 - 2013 CosmoCode GmbH
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

import com.google.common.collect.ImmutableSet;
import junit.framework.Assert;
import org.junit.Test;

import java.beans.PropertyVetoException;
import java.util.Set;
import java.util.UUID;

/**
 * Tests {@link UniqueNode}.
 * 
 * @author Oliver Lorenz
 * 
 * @see UniqueNode
 * @see TreeNodeTest
 */
public class UniqueNodeStringTest extends TreeNodeTest<String> {
    
    @Override
    protected String newData() {
        return UUID.randomUUID().toString();
    }
    
    @Override
    protected Set<TreeNode<String>> createRealWorldChildren() {
        final ImmutableSet.Builder<TreeNode<String>> builder = ImmutableSet.builder();

        final TreeNode<String> node1 = new UniqueNode<String>("root-1");
        final TreeNode<String> node1x1 = node1.addChild("1-1");
        node1x1.addChild("1-1-1");
        node1x1.addChild("1-1-2");
        final TreeNode<String> node2 = new UniqueNode<String>("root-2");
        final TreeNode<String> node2x1 = node2.addChild("2-1");
        node2x1.addChild("2-1-1");
        node2x1.addChild("2-1-2");
        final TreeNode<String> node3 = new MergeNode<String>("root-3");
        node3.addChild("3-1");
        node3.addChild("3-2");
        final TreeNode<String> node4 = new UniqueNode<String>("root-4");
        
        builder.add(node1);
        builder.add(node2);
        builder.add(node3);
        builder.add(node4);
        
        return builder.build();
    }

    @Override
    protected TreeNode<String> emptyNode() {
        return new UniqueNode<String>();
    }

    @Override
    protected TreeNode<String> newNode(String data) {
        return new UniqueNode<String>(data);
    }
    
    
    /**
     * Tests {@link TreeNode#addChild(Object)} with two different values.
     */
    @Test
    public void testAddChildTwoDifferentChildren() {
        getRoot().addChild(newData());
        getRoot().addChild(newData());
        
        Assert.assertEquals(2, getRoot().getNumberOfChildren());
    }
    
    /**
     * Tests {@link TreeNode#addChild(Object)} with the same value twice.
     * Expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddChildSameTwice() {
        final String data = newData();
        getRoot().addChild(data);
        getRoot().addChild(data);
    }
    
    /**
     * Tests {@link TreeNode#addChildNode(TreeNode)} with two different TreeNodes.
     */
    @Test
    public void testAddChildNodeDifferent() {
        final TreeNode<String> newNode1 = newNode(newData());
        final TreeNode<String> newNode2 = newNode(newData());
        getRoot().addChildNode(newNode1);
        getRoot().addChildNode(newNode2);

        Assert.assertEquals(2, getRoot().getNumberOfChildren());
        Assert.assertEquals(getRoot(), newNode1.getParent());
        Assert.assertEquals(getRoot(), newNode2.getParent());
    }
    
    /**
     * Tests {@link TreeNode#addChildNode(TreeNode)} with two TreeNodes
     * that have the same data.
     * Expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddChildNodeSame() {
        final String data = newData();
        final TreeNode<String> newNode1 = newNode(data);
        final TreeNode<String> newNode2 = newNode(data);

        getRoot().addChildNode(newNode1);
        getRoot().addChildNode(newNode2);
    }
    
    /**
     * Tests {@link TreeNode#insertChildAt(int, TreeNode)} with two different TreeNodes.
     */
    @Test
    public void testInsertChildNodeDifferent() {
        final TreeNode<String> newNode1 = newNode(newData());
        final TreeNode<String> newNode2 = newNode(newData());
        getRoot().insertChildAt(0, newNode1);
        getRoot().insertChildAt(0, newNode2);

        Assert.assertEquals(2, getRoot().getNumberOfChildren());
        Assert.assertEquals(getRoot(), newNode1.getParent());
        Assert.assertEquals(getRoot(), newNode2.getParent());
    }
    
    /**
     * Tests {@link TreeNode#insertChildAt(int, TreeNode)} with two TreeNodes that have the same data.
     * Expects an IllegalArgumentException.
     */
    @Test(expected = IllegalArgumentException.class)
    public void testInsertChildNodeSame() {
        final String data = newData();
        final TreeNode<String> newNode1 = newNode(data);
        final TreeNode<String> newNode2 = newNode(data);
        
        getRoot().insertChildAt(0, newNode1);
        getRoot().insertChildAt(0, newNode2);
    }
    
    /**
     * Tests {@link TreeNode#setData(Object)} with a different data.
     */
    @Test
    public void testSetDataDifferent() {
        final TreeNode<String> node = getRoot().addChild(newData());
        final String newData = newData();
        try {
            node.setData(newData);
        } catch (PropertyVetoException e) {
            throw new IllegalStateException("Should not happen", e);
        }
        Assert.assertEquals(newData, node.getData());
    }
    
    /**
     * Tests {@link TreeNode#setData(Object)} with the same data as already present.
     * Expects a {@link PropertyVetoException}.
     * @throws PropertyVetoException expected, must be thrown
     */
    @Test(expected = PropertyVetoException.class)
    public void testSetDataSame() throws PropertyVetoException {
        final String data = newData();
        getRoot().addChild(data);
        final TreeNode<String> node = getRoot().addChild(newData());
        node.setData(data);
    }

}
