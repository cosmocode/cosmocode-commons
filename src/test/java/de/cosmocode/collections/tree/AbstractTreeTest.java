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

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;
import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;
import java.util.Collections;
import java.util.List;


/**
 * Tests {@link Tree}. This test is independent of the implementation.
 * Special behaviour should be tested by the Tests for the actual implementation.
 * 
 * @param <T>
 * 
 * @author Oliver Lorenz
 */
public abstract class AbstractTreeTest<T> {
    
    private Tree<T> instance;
    
    /**
     * Returns a new instance of {@link Tree}
     * with the given TreeNode as root node.
     * @param rootNode the root node for the tree
     * @return a new tree
     */
    protected abstract Tree<T> newTree(final TreeNode<T> rootNode);
    
    /**
     * Returns an empty {@link TreeNode}.
     * It must have no parent and no children.
     * @return an empty {@link TreeNode}.
     */
    protected abstract TreeNode<T> emptyNode();
    
    /**
     * Returns a new TreeNode<String> with the specified data.
     * It must have no parent and no children.
     * @param data the data for the new TreeNode<String>
     * @return an instance of TreeNode<String>, with the specified data set
     */
    protected abstract TreeNode<T> newNode(final T data);
    
    /**
     * Creates a new instance of T.
     * This method must return a new value on each successive call.
     * It is allowed to alternate between 2 different values 
     * (so the same element is returned on the first, third, fifth, ... call). 
     * @return a new T
     */
    protected abstract T newData();
    
    /**
     * Returns a list of test childnodes, which should reflect a real world example.
     * Used by {@link #fillTree(Tree)}
     * @return a list of test childnodes
     */
    protected abstract Collection<TreeNode<T>> createRealWorldChildren();
    
    /**
     * Creates a complex tree, that should have at least a depth of 4.
     * @return a complex tree
     */
    protected abstract Tree<T> createComplexTree();
    
    /**
     * <p>
     * Fills a given tree with test nodes.
     * It uses {@link #createChildNodes()} and {@link Tree#addChildNode(TreeNode)}.
     * </p>
     * <ul>
     * <li>Preconditions: The given tree is empty</li>
     * <li>Effects: The tree is filled with nodes and is not empty anymore.</li>
     * </ul>
     * @param tree an empty {@link Tree}
     */
    protected void fillTree(Tree<T> tree) {
        for (final TreeNode<T> node : createRealWorldChildren()) {
            tree.addChildNode(node);
        }
    }
    
    /**
     * Returns the root node, that is initialized as an empty node.
     * @return the root node
     */
    protected TreeNode<T> getRoot() {
        return instance.getRoot();
    }

    
    /* (non Javadoc)
     * Methods to test on Tree<T>:
     *   addChild(T)                                     - tested in 2 methods
     *   addChildNode(TreeNode<T>)                       - tested in 2 methods
     *   addChildren(T...)                               - tested in 1 method
     *   contains(TreeNode<T>)                           - tested in 5 methods
     *   equals(Tree<T>)                                 - tested in 4 methods
     *   getChildAt(int)                                 - tested in 3 methods
     *   getChildren()                                   - tested in 2 methods
     *   getNumberOfChildren()                           - tested in 3 methods
     *   getRoot()                                       
     *   setRootElement()                                
     *   insertChildAt(int, TreeNode<T>)                 - tested in 5 methods
     *   removeChild(TreeNode<T>)                        - tested in 2 methods
     *   removeChildAt(int)                              - tested in 3 methods
     *   setChildren(Collection<TreeNode<T>>)            - tested in 5 methods
     *   traverse(TraverseMode)
     */
    
    
    /**
     * Sets the attribute {@link #root} to a new instance of
     * a {@link TreeNode} using {@link #emptyNode()}.
     * This method is called before each test execution. 
     */
    @Before
    public void initializeTree() {
        this.instance = newTree(emptyNode());
    }

    /**
     * Tests {@link #emptyNode()}.
     * Tests that parent is null, data is null and the new node has no children.
     */
    @Test
    public void testEmptyNode() {
        final TreeNode<T> empty = emptyNode();
        
        Assert.assertEquals("parent not null", null, empty.getParent());
        Assert.assertEquals("data not null", null, empty.getData());
        Assert.assertTrue("error: empty node has children", empty.getNumberOfChildren() == 0);
    }
    
    /**
     * Tests {@link #newNode(Object)}.
     * Tests that parent is null, data is set and the new node has no children.
     */
    @Test
    public void testNewNode() {
        final T data = newData();
        final TreeNode<T> node = newNode(data);
        
        Assert.assertEquals("parent not null", null, node.getParent());
        Assert.assertEquals("data not as expected", data, node.getData());
        Assert.assertTrue("error: new node has children", node.getNumberOfChildren() == 0);
    }
    
    /**
     * Tests {@link Tree#equals(Object)} on 2 Trees with the same data.
     */
    @Test
    public void testEqualsWithData() {
        final T data = newData();
        final Tree<T> tree1 = newTree(newNode(data));
        final Tree<T> tree2 = newTree(newNode(data));
        
        Assert.assertEquals(tree1, tree2);
    }
    
    /**
     * Tests {@link Tree#equals(Object)} on 2 empty Trees.
     */
    @Test
    public void testEqualsEmpty() {
        final Tree<T> tree1 = newTree(emptyNode());
        final Tree<T> tree2 = newTree(emptyNode());
        
        Assert.assertEquals(tree1, tree2);
    }
    
    /**
     * Tests {@link Tree#equals(Object)} on 2 TreeNodes which each have 2 children.
     */
    @Test
    public void testEqualsWithTwoChildren() {
        final Tree<T> tree1 = newTree(emptyNode());
        final Tree<T> tree2 = newTree(emptyNode());
        
        final T data1 = newData();
        final T data2 = newData();
        tree1.addChild(data1);
        tree1.addChild(data2);
        tree2.addChild(data1);
        tree2.addChild(data2);
        
        Assert.assertEquals(tree1, tree2);
    }
    
    /**
     * Tests {@link Tree#equals(Object)} with real world examples (complex trees).
     */
    @Test
    public void testEqualsRealWorld() {
        final Tree<T> tree1 = newTree(emptyNode());
        final Tree<T> tree2 = newTree(emptyNode());
        fillTree(tree1);
        fillTree(tree2);
        
        Assert.assertEquals(tree1, tree2);
    }
    
    /**
     * Test {@link Tree#getChildren()} on an empty Tree.
     */
    @Test
    public void testGetChildrenEmpty() {
        final Collection<TreeNode<T>> expected = Collections.emptySet();
        final Collection<TreeNode<T>> actual = instance.getChildren();
        TreeNodeTest.assertEqualsCollections(expected, actual);
    }

    /**
     * Tests {@link Tree#getChildren()} on a real world example.
     */
    @Test
    public void testGetChildrenRealWorld() {
        fillTree(instance);
        
        final Collection<TreeNode<T>> expected = createRealWorldChildren();
        final Collection<TreeNode<T>> actual = instance.getChildren();
        TreeNodeTest.assertEqualsCollections(expected, actual);
    }

    /**
     * Tests {@link Tree#removeChildNode(TreeNode)}.
     */
    @Test
    public void testRemoveChild() {
        final T data = newData();
        
        final TreeNode<T> newChild = instance.addChild(data);
        Assert.assertEquals(1, instance.getNumberOfChildren());
        Assert.assertEquals("root is not parent of new child - addChild(TreeNode) failed", 
                getRoot(), newChild.getParent());
        
        instance.removeChildNode(newChild);
        Assert.assertEquals(0, instance.getNumberOfChildren());
        Assert.assertEquals("root is still parent of child after remove", null, newChild.getParent());
    }
    
    /**
     * Tests {@link Tree#removeChildNode(TreeNode)} with a null value.
     * Expects a {@link NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveChildNull() {
        instance.removeChildNode(null);
    }

    /**
     * Tests {@link Tree#setChildren(List)}.
     */
    @Test
    public void testSetChildren() {
        instance.setChildren(createRealWorldChildren());
        final Collection<TreeNode<T>> expected = createRealWorldChildren();
        final Collection<TreeNode<T>> actual = instance.getChildren();
        TreeNodeTest.assertEqualsCollections(expected, actual);
        
        for (TreeNode<T> child : instance.getChildren()) {
            Assert.assertEquals("root is not parent of one of the children", getRoot(), child.getParent());
        }
    }
    
    /**
     * Tests {@link Tree#setChildren(Collection)} on the root node that previously had children
     * with real-world children.
     * The old children should be removed from the root node and the new nodes
     * should be children of the root node.
     */
    @Test
    public void testSetChildrenOverridePreviousChildren() {
        final TreeNode<T> child1 = instance.addChild(newData());
        final TreeNode<T> child2 = instance.addChild(newData());
        final TreeNode<T> child3 = instance.addChild(newData());
        final TreeNode<T> child4 = instance.addChild(newData());
        
        instance.setChildren(createRealWorldChildren());
        final Collection<TreeNode<T>> expected = createRealWorldChildren();
        final Collection<TreeNode<T>> actual = instance.getChildren();
        TreeNodeTest.assertEqualsCollections(expected, actual);
        
        for (TreeNode<T> child : instance.getChildren()) {
            Assert.assertEquals("root is not parent of one of the new children", getRoot(), child.getParent());
        }
        
        Assert.assertEquals("old child1 not removed from root", null, child1.getParent());
        Assert.assertEquals("old child2 not removed from root", null, child2.getParent());
        Assert.assertEquals("old child3 not removed from root", null, child3.getParent());
        Assert.assertEquals("old child4 not removed from root", null, child4.getParent());
    }
    
    /**
     * Tests {@link Tree#setChildren(List)} with a null value.
     * Expects a {@link NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testSetChildrenNull() {
        instance.setChildren(null);
    }
    
    /**
     * Test {@link Tree#setChildren(Collection)} with a collection that has exactly one null value.
     * Expects a {@link NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testSetChildrenOneElementNull() {
        final Collection<TreeNode<T>> testList = ImmutableList.of(
                emptyNode(), newNode(newData()), null, emptyNode());
        
        instance.setChildren(testList);
    }
    
    /**
     * Tests {@link Tree#setChildren(Collection)} with getChildren() as its argument.
     */
    @Test
    public void testSetChildrenOfGetChildren() {
        instance.setChildren(createRealWorldChildren());
        instance.setChildren(instance.getChildren());

        final Collection<TreeNode<T>> expected = createRealWorldChildren();
        final Collection<TreeNode<T>> actual = instance.getChildren();
        TreeNodeTest.assertEqualsCollections(expected, actual);
        
        for (TreeNode<T> child : instance.getChildren()) {
            Assert.assertEquals("root is not parent of one of the children", getRoot(), child.getParent());
        }
    }

    /**
     * Tests {@link Tree#addChildNode(TreeNode)}.
     */
    @Test
    public void testAddChildNode() {
        final TreeNode<T> newNode = emptyNode();
        instance.addChildNode(newNode);

        Assert.assertEquals("wrong number of children after add", 1, instance.getNumberOfChildren());
        Assert.assertEquals("instance not parent of new node", getRoot(), newNode.getParent());
    }

    /**
     * Tests {@link Tree#addChildNode(TreeNode)} with a null value.
     * Expects a {@link NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testAddChildNodeNull() {
        instance.addChildNode(null);
    }

    /**
     * Tests {@link Tree#addChild(Object)} with a non-empty value.
     */
    @Test
    public void testAddChildT() {
        final T data = newData();
        final TreeNode<T> newNode = instance.addChild(data);
        Assert.assertNotNull("error: new node is null", newNode);
        Assert.assertEquals("wrong number of children after add", 1, instance.getNumberOfChildren());
        Assert.assertEquals("instance not parent of new node", getRoot(), newNode.getParent());
        
        final T actual = newNode.getData();
        Assert.assertEquals(data, actual);
    }

    /**
     * Tests {@link Tree#addChild(Object)} with null.
     */
    @Test
    public void testAddChildTNull() {
        final TreeNode<T> newNode = instance.addChild(null);
        Assert.assertNotNull("error: new node is null", newNode);
        Assert.assertEquals("wrong number of children after add", 1, instance.getNumberOfChildren());
        Assert.assertEquals("instance not parent of new node", getRoot(), newNode.getParent());
        
        final T expected = null;
        final T actual = newNode.getData();
        Assert.assertEquals("child has wrong data", expected, actual);
    }
    
    /**
     * Tests {@link Tree#addChildren(Object...)} with 3 values.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddChildren() {
        final T data1 = newData();
        final T data2 = null;
        final T data3 = newData();
        
        final List<TreeNode<T>> expected = ImmutableList.of(newNode(data1), newNode(data2), newNode(data3));
        final List<TreeNode<T>> actual = instance.addChildren(data1, data2, data3);
        Assert.assertEquals("return value of addChildren not as expected", expected, actual);
        
        Assert.assertTrue(data1 + " was not added correctly", instance.getRoot().hasChild(data1));
        Assert.assertTrue(data2 + " was not added correctly", instance.getRoot().hasChild(data2));
        Assert.assertTrue(data3 + " was not added correctly", instance.getRoot().hasChild(data3));
    }

    /**
     * Tests {@link Tree#getNumberOfChildren()} before and after addChild calls.
     */
    @Test
    public void testGetNumberOfChildrenAfterAddChildT() {
        instance.addChild(newData());
        Assert.assertEquals("1. after first addChild(T)", 1, instance.getNumberOfChildren());
        instance.addChild(newData());
        Assert.assertEquals("2. after second addChild(T)", 2, instance.getNumberOfChildren());
    }
    
    /**
     * Tests {@link Tree#getChildren()} after removeChild calls. 
     */
    public void testGetNumberOfChildrenAfterRemoveChild() {
        final TreeNode<T> child1 = instance.addChild(newData());
        instance.addChild(newData());
        
        instance.removeChildNode(child1);
        Assert.assertEquals(1, instance.getNumberOfChildren());
    }

    /**
     * Tests {@link Tree#getNumberOfChildren()} on a real world tree example.
     */
    @Test
    public void testGetNumberOfChildrenRealWorld() {
        fillTree(instance);
        
        final int expected = createRealWorldChildren().size();
        final int actual = instance.getNumberOfChildren();
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link Tree#getChildAt(int)} on a Tree with 3 children, with the value: 1.
     */
    @Test
    public void testGetChildAt1() {
        instance.addChild(newData());
        final TreeNode<T> child1 = instance.addChild(newData());
        instance.addChild(newData());
        
        final TreeNode<T> actual = instance.getChildAt(1);
        Assert.assertEquals(child1, actual);
    }

    /**
     * Tests {@link Tree#getChildAt(int)} on a Tree with 3 children, with a negative value.
     * Expects an {@link IndexOutOfBoundsException}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetChildAtNegative() {
        instance.addChild(newData());
        instance.addChild(newData());
        instance.addChild(newData());
        
        instance.getChildAt(-1);
    }

    /**
     * Tests {@link Tree#getChildAt(int)} on a Tree with 3 children, with the value: 3.
     * Expects an {@link IndexOutOfBoundsException}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetChildAtOutOfBounds() {
        instance.addChild(newData());
        instance.addChild(newData());
        instance.addChild(newData());
        
        instance.getChildAt(3);
    }
    
    /**
     * Tests {@link Tree#insertChildAt(int, TreeNode)} on a Tree with 2 children, with the values:
     * 2 and an empty TreeNode.
     */
    @Test
    public void testInsertChildAtEnd() {
        final TreeNode<T> node1 = instance.addChild(newData());
        final TreeNode<T> node2 = instance.addChild(newData());
        final TreeNode<T> insertedNode = emptyNode();
        
        instance.insertChildAt(2, insertedNode);
        
        final List<TreeNode<T>> expected = ImmutableList.of(node1, node2, insertedNode);
        final List<TreeNode<T>> actual = ImmutableList.copyOf(instance.getChildren());
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("inserted child has wrong parent", getRoot(), insertedNode.getParent());
    }
    
    /**
     * Tests {@link Tree#insertChildAt(int, TreeNode)} on a Tree with 2 children, with the values:
     * 1 and an empty TreeNode.
     */
    @Test
    public void testInsertChildAtMiddle() {
        final TreeNode<T> node1 = instance.addChild(newData());
        final TreeNode<T> node2 = instance.addChild(newData());
        final TreeNode<T> insertedNode = emptyNode();
        
        instance.insertChildAt(1, insertedNode);

        final List<TreeNode<T>> expected = ImmutableList.of(node1, insertedNode, node2);
        final List<TreeNode<T>> actual = ImmutableList.copyOf(instance.getChildren());
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("inserted child has wrong parent", getRoot(), insertedNode.getParent());
    }
    
    /**
     * Tests {@link Tree#insertChildAt(int, TreeNode)} on a Tree with 2 children, with the values:
     * 0 and an empty TreeNode.
     */
    @Test
    public void testInsertChildAtStart() {
        final TreeNode<T> node1 = instance.addChild(newData());
        final TreeNode<T> node2 = instance.addChild(newData());
        final TreeNode<T> insertedNode = emptyNode();
        
        instance.insertChildAt(0, insertedNode);

        final List<TreeNode<T>> expected = ImmutableList.of(insertedNode, node1, node2);
        final List<TreeNode<T>> actual = ImmutableList.copyOf(instance.getChildren());
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("inserted child has wrong parent", getRoot(), insertedNode.getParent());
    }
    
    /**
     * Tests {@link Tree#insertChildAt(int, TreeNode)} on a Tree with 2 children, with the values:
     * 3 and an empty TreeNode.
     * Expects {@link IndexOutOfBoundsException}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertChildAtOutOfBounds() {
        instance.addChild(newData());
        instance.addChild(newData());
        
        instance.insertChildAt(3, emptyNode());
    }
    
    /**
     * Tests {@link Tree#insertChildAt(int, TreeNode)} on a Tree with 2 children, with the values:
     * -1 and an empty TreeNode.
     * Expects {@link IndexOutOfBoundsException}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertChildAtNegative() {
        instance.addChild(newData());
        instance.addChild(newData());
        
        instance.insertChildAt(-1, emptyNode());
    }

    /**
     * Tests {@link Tree#removeChildAt(int)} on a Tree with 3 children, with the value: 0.
     */
    @Test
    public void testRemoveChildAtStart() {
        final TreeNode<T> node1 = instance.addChild(newData());
        final TreeNode<T> node2 = instance.addChild(newData());
        final TreeNode<T> node3 = instance.addChild(newData());

        instance.removeChildAt(0);

        final List<TreeNode<T>> expected = ImmutableList.of(node2, node3);
        final List<TreeNode<T>> actual = ImmutableList.copyOf(instance.getChildren());
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("removed child has wrong parent", null, node1.getParent());
    }

    /**
     * Tests {@link Tree#removeChildAt(int)} on a Tree with 3 children, with the value: 1.
     */
    @Test
    public void testRemoveChildAtMiddle() {
        final TreeNode<T> node1 = instance.addChild(newData());
        final TreeNode<T> node2 = instance.addChild(newData());
        final TreeNode<T> node3 = instance.addChild(newData());

        instance.removeChildAt(1);

        final List<TreeNode<T>> expected = ImmutableList.of(node1, node3);
        final List<TreeNode<T>> actual = ImmutableList.copyOf(instance.getChildren());
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("removed child has wrong parent", null, node2.getParent());
    }

    /**
     * Tests {@link Tree#removeChildAt(int)} on a Tree with 3 children, with the value: 2.
     */
    @Test
    public void testRemoveChildAtEnd() {
        final TreeNode<T> node1 = instance.addChild(newData());
        final TreeNode<T> node2 = instance.addChild(newData());
        final TreeNode<T> node3 = instance.addChild(newData());

        instance.removeChildAt(2);

        final List<TreeNode<T>> expected = ImmutableList.of(node1, node2);
        final List<TreeNode<T>> actual = ImmutableList.copyOf(instance.getChildren());
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("removed child has wrong parent", null, node3.getParent());
    }
    
    /**
     * Tests {@link Tree#contains(TreeNode)} with the parameter null.
     */
    @Test
    public void testContainsNull() {
        instance.addChild(newData());

        Assert.assertEquals(false, instance.contains(null));
    }
    
    /**
     * Tests {@link Tree#contains(TreeNode)} on a direct child.
     */
    @Test
    public void testContainsChild() {
        final TreeNode<T> node1 = instance.addChild(newData());

        Assert.assertEquals(true, instance.contains(node1));
    }
    
    /**
     * Tests {@link Tree#contains(TreeNode)} on a grand-grand-child
     * (child of a child of a child).
     */
    @Test
    public void testContainsDescendant() {
        final TreeNode<T> node1 = instance.addChild(newData());
        node1.addChild(newData());
        final TreeNode<T> node1x2 = node1.addChild(newData());
        final TreeNode<T> node1x2x1 = node1x2.addChild(newData());
        node1.addChild(newData());
        instance.addChild(newData());

        Assert.assertEquals(true, instance.contains(node1x2x1));
    }
    
    /**
     * Tests {@link Tree#contains(TreeNode)} on a real world Tree with an unrelated TreeNode.
     */
    @Test
    public void testContainsUnrelated() {
        fillTree(instance);
        
        final TreeNode<T> unrelated = newNode(newData());
        unrelated.addChild(newData());
        unrelated.addChild(newData());
        
        Assert.assertEquals(false, instance.contains(unrelated));
    }
    
    /**
     * Tests {@link Tree#contains(TreeNode)} on a real world Tree with an unrelated TreeNode
     * that is equal to the first child.
     */
    @Test
    public void testContainsUnrelatedButEqualToChild() {
        fillTree(instance);
        
        // this node is unrelated, but equal with a childNode
        final TreeNode<T> unrelated = createRealWorldChildren().iterator().next();
        
        Assert.assertEquals(false, instance.contains(unrelated));
    }
    
    /**
     * Tests {@link Tree#traverse(TraverseMode)} with {@link TraverseMode#PRE_ORDER}.
     */
    @Test
    public void testTraversePreOrder() {
        final T root = newData();
        final T child1 = newData();
        final T child1x1 = null;
        final T child1x2 = newData();
        final T child2 = newData();
        final T child2x1 = newData();
        final T child2x1x1 = newData();
        final T child3 = newData();
        final Tree<T> tree = newTree(newNode(root));
        final TreeNode<T> node1 = tree.addChild(child1);
        node1.addChild(child1x1);
        node1.addChild(child1x2);
        tree.addChild(child2).addChild(child2x1).addChild(child2x1x1);
        tree.addChild(child3);

        @SuppressWarnings("unchecked")
        final List<T> expected = 
            Lists.newArrayList(root, child1, child1x1, child1x2, child2, child2x1, child2x1x1, child3);
        final List<T> actual = Lists.newArrayList(tree.traverse(TraverseMode.PRE_ORDER));
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Tree#traverse(TraverseMode)} on an empty Tree with {@link TraverseMode#PRE_ORDER}.
     */
    @Test
    public void testTraversePreOrderEmpty() {
        @SuppressWarnings("unchecked")
        final List<T> expected = Lists.newArrayList((T) null);
        final List<T> actual = Lists.newArrayList(instance.traverse(TraverseMode.PRE_ORDER));
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Tree#traverse(TraverseMode)} on a more complex Tree
     * with {@link TraverseMode#POST_ORDER}, but without testing the result on correctness.
     */
    @Test
    public void testTraversePreOrderComplex() {
        final Tree<T> tree = createComplexTree();
        System.out.println("Pre Order Complex: " + Lists.newArrayList(tree.traverse(TraverseMode.PRE_ORDER)));
    }
    
    /**
     * Tests {@link Tree#traverse(TraverseMode)} with {@link TraverseMode#POST_ORDER}.
     */
    @Test
    public void testTraversePostOrder() {
        final T root = newData();
        final T child1 = newData();
        final T child1x1 = null;
        final T child1x2 = newData();
        final T child2 = newData();
        final T child2x1 = newData();
        final T child2x1x1 = newData();
        final T child3 = newData();
        final Tree<T> tree = newTree(newNode(root));
        final TreeNode<T> node1 = tree.addChild(child1);
        node1.addChild(child1x1);
        node1.addChild(child1x2);
        tree.addChild(child2).addChild(child2x1).addChild(child2x1x1);
        tree.addChild(child3);

        @SuppressWarnings("unchecked")
        final List<T> expected = 
            Lists.newArrayList(child1x1, child1x2, child1, child2x1x1, child2x1, child2, child3, root);
        final List<T> actual = Lists.newArrayList(tree.traverse(TraverseMode.POST_ORDER));
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Tree#traverse(TraverseMode)} on an empty Tree with {@link TraverseMode#POST_ORDER}.
     */
    @Test
    public void testTraversePostOrderEmpty() {
        @SuppressWarnings("unchecked")
        final List<T> expected = Lists.newArrayList((T) null);
        final List<T> actual = Lists.newArrayList(instance.traverse(TraverseMode.POST_ORDER));
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Tree#traverse(TraverseMode)} on a more complex Tree
     * with {@link TraverseMode#POST_ORDER}, but without testing the result on correctness.
     */
    @Test
    public void testTraversePostOrderComplex() {
        final Tree<T> tree = createComplexTree();
        System.out.println("Post Order Complex: " + Lists.newArrayList(tree.traverse(TraverseMode.POST_ORDER)));
    }
    
    /**
     * Tests {@link Tree#traverse(TraverseMode)} with {@link TraverseMode#LEVEL_ORDER}.
     */
    @Test
    public void testTraverseLevelOrder() {
        final T root = newData();
        final T child1 = newData();
        final T child1x1 = null;
        final T child1x2 = newData();
        final T child2 = newData();
        final T child2x1 = newData();
        final T child2x1x1 = newData();
        final T child3 = newData();
        final Tree<T> tree = newTree(newNode(root));
        final TreeNode<T> node1 = tree.addChild(child1);
        node1.addChild(child1x1);
        node1.addChild(child1x2);
        tree.addChild(child2).addChild(child2x1).addChild(child2x1x1);
        tree.addChild(child3);

        @SuppressWarnings("unchecked")
        final List<T> expected = 
            Lists.newArrayList(root, child1, child2, child3, child1x1, child1x2, child2x1, child2x1x1);
        final List<T> actual = Lists.newArrayList(tree.traverse(TraverseMode.LEVEL_ORDER));
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Tree#traverse(TraverseMode)} on an empty Tree with {@link TraverseMode#LEVEL_ORDER}.
     */
    @Test
    public void testTraverseLevelOrderEmpty() {
        @SuppressWarnings("unchecked")
        final List<T> expected = Lists.newArrayList((T) null);
        final List<T> actual = Lists.newArrayList(instance.traverse(TraverseMode.LEVEL_ORDER));
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link Tree#traverse(TraverseMode)} on a more complex Tree
     * with {@link TraverseMode#LEVEL_ORDER}, but without testing the result on correctness.
     */
    @Test
    public void testTraverseLevelOrderComplex() {
        final Tree<T> tree = createComplexTree();
        System.out.println("Level Order Complex: " + Lists.newArrayList(tree.traverse(TraverseMode.LEVEL_ORDER)));
    }

}
