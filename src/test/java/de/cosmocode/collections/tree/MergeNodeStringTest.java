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

import java.util.Set;
import java.util.UUID;

/**
 * Tests MergeDuplicatesNode&lt;String&gt;.
 * 
 * @author Oliver Lorenz
 */
public class MergeNodeStringTest extends TreeNodeTest<String> {
    
    @Override
    protected String newData() {
        return UUID.randomUUID().toString();
    }
    
    @Override
    protected Set<TreeNode<String>> createRealWorldChildren() {
        final ImmutableSet.Builder<TreeNode<String>> builder = ImmutableSet.builder();

        final TreeNode<String> node1 = new DuplicatesNode<String>("root-1");
        final TreeNode<String> node1x1 = node1.addChild("1-1");
        node1x1.addChild("1-1-1");
        node1x1.addChild("1-1-2");
        final TreeNode<String> node2 = new MergeNode<String>("root-2");
        final TreeNode<String> node2x1 = node2.addChild("2-1");
        node2x1.addChild("2-1-1");
        node2x1.addChild("2-1-2");
        final TreeNode<String> node3 = new MergeNode<String>("root-3");
        node3.addChild("3-1");
        node3.addChild("3-2");
        final TreeNode<String> node4 = new DuplicatesNode<String>("root-4");
        
        builder.add(node1);
        builder.add(node2);
        builder.add(node3);
        builder.add(node4);
        
        return builder.build();
    }
    
    @Override
    protected TreeNode<String> emptyNode() {
        return new MergeNode<String>();
    }
    
    @Override
    protected TreeNode<String> newNode(final String data) {
        return new MergeNode<String>(data);
    }
    
    /**
     * Returns a second set of children, that differ from the result of
     * {@link #createRealWorldChildren()}.
     * @return a set of TreeNodes
     */
    protected Set<TreeNode<String>> createRealWorldChildren2() {
        final ImmutableSet.Builder<TreeNode<String>> builder = ImmutableSet.builder();

        final TreeNode<String> node1 = new MergeNode<String>("root-1");
        final TreeNode<String> node1x2 = node1.addChild("1-2");
        node1x2.addChild("1-2-1");
        node1x2.addChild("1-2-2");
        final TreeNode<String> node3 = new MergeNode<String>("root-3");
        node3.addChild("3-1");
        final TreeNode<String> node3x2 = node3.addChild("3-2");
        node3x2.addChild("3-2-1");
        node3x2.addChild("3-2-2");
        node3.addChild("3-3");
        final TreeNode<String> node4 = new UniqueNode<String>("root-4");
        final TreeNode<String> node5 = new UniqueNode<String>("root-5");
        node5.addChild("5-1");
        node5.addChild("5-2");
        
        builder.add(node1);
        builder.add(node3);
        builder.add(node4);
        builder.add(node5);
        
        return builder.build();
    }

    /**
     * Returns the result from adding all items from {@link #createRealWorldChildren()}
     * and {@link #createRealWorldChildren2()}, in that order.
     * @return a TreeNode that has the merged children from both createRealWorldChildren methods
     */
    protected TreeNode<String> mergedRealWorld() {
        final TreeNode<String> rootNode = new MergeNode<String>();

        final TreeNode<String> node1 = new MergeNode<String>("root-1");
        final TreeNode<String> node1x1 = node1.addChild("1-1");
        node1x1.addChild("1-1-1");
        node1x1.addChild("1-1-2");
        final TreeNode<String> node1x2 = node1.addChild("1-2");
        node1x2.addChild("1-2-1");
        node1x2.addChild("1-2-2");
        final TreeNode<String> node2 = new MergeNode<String>("root-2");
        final TreeNode<String> node2x1 = node2.addChild("2-1");
        node2x1.addChild("2-1-1");
        node2x1.addChild("2-1-2");
        final TreeNode<String> node3 = new MergeNode<String>("root-3");
        node3.addChild("3-1");
        final TreeNode<String> node3x2 = node3.addChild("3-2");
        node3x2.addChild("3-2-1");
        node3x2.addChild("3-2-2");
        node3.addChild("3-3");
        final TreeNode<String> node4 = new MergeNode<String>("root-4");
        final TreeNode<String> node5 = new UniqueNode<String>("root-5");
        node5.addChild("5-1");
        node5.addChild("5-2");

        rootNode.addChildNode(node1);
        rootNode.addChildNode(node2);
        rootNode.addChildNode(node3);
        rootNode.addChildNode(node4);
        rootNode.addChildNode(node5);
        
        return rootNode;
    }
    
    
    /**
     * Tests {@link TreeNode#addChild(Object)} with two different values.
     */
    @Test
    public void testAddChildNoMerge() {
        getRoot().addChild(newData());
        getRoot().addChild(newData());
        
        Assert.assertEquals(2, getRoot().getNumberOfChildren());
    }
    
    /**
     * Tests {@link TreeNode#addChild(Object)} with twice the same value.
     */
    @Test
    public void testAddChildMerge() {
        final String data = newData();
        
        final TreeNode<String> a = getRoot().addChild(data);
        final TreeNode<String> b = getRoot().addChild(data);
        
        Assert.assertSame(a, b);
        Assert.assertEquals("Illegal number of children", 1, getRoot().getNumberOfChildren());
    }
    
    /**
     * Tests {@link TreeNode#insertChildAt(int, TreeNode)} with the same node previously added.
     */
    @Test
    public void testInsertChildMerge() {
        final TreeNode<String> node = getRoot().addChild(newData());
        
        getRoot().insertChildAt(1, node);
        Assert.assertEquals(1, getRoot().getNumberOfChildren());
    }
    
    /**
     * Tests {@link TreeNode#addChildNode(TreeNode)} with a node that has the same data as already added.
     */
    @Test
    public void testAddChildNodeMerge() {
        final String data = newData();
        
        getRoot().addChild(data);
        getRoot().addChildNode(new UniqueNode<String>(data));

        Assert.assertEquals(1, getRoot().getNumberOfChildren());
    }
    
    /**
     * Tests {@link TreeNode#addChildNode(TreeNode)} with 2 three level-deep trees.
     */
    @Test
    public void testAddChildNodeMergeDeep() {
        final TreeNode<String> actual = emptyNode();
        for (final TreeNode<String> child : createRealWorldChildren()) {
            actual.addChildNode(child);
        }
        for (final TreeNode<String> child : createRealWorldChildren2()) {
            actual.addChildNode(child);
        }
        
        final TreeNode<String> expected = mergedRealWorld();
        System.out.println(expected.toString());
        System.out.println(actual.toString());
        Assert.assertEquals(expected, actual);
    }

}
