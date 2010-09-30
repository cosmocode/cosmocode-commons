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

import java.beans.PropertyVetoException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import com.google.common.collect.ImmutableList;

/**
 * Tests TreeNode. This test is independent of the implementation. 
 * Special behaviour should be tested by the Tests for the actual implementation.
 * @param <T>
 * 
 * @author Oliver Lorenz
 */
public abstract class TreeNodeTest<T> {
    
    private TreeNode<T> root;
    
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
     * Used by {@link #fillTree(TreeNode)}
     * @return a list of test childnodes
     */
    protected abstract Collection<TreeNode<T>> createRealWorldChildren();
    
    /**
     * Compares 2 collections on equality.
     * @param <E> an element
     * @param expected the expected collection
     * @param actual the actual collection
     */
    public static <E> void assertEqualsCollections(Collection<E> expected, Collection<E> actual) {
        Assert.assertEquals("Collections don't have the same size", expected.size(), actual.size());
        
        int counter = 1;
        for (E elem : expected) {
            Assert.assertTrue("Element #" + counter + " (" + elem + ") was expected, but is not in actual", 
                    actual.contains(elem));
            counter++;
        }
        
        counter = 1;
        for (E elem : actual) {
            Assert.assertTrue("Element #" + counter + " (" + elem + ") in actual was not expected", 
                    expected.contains(elem));
            counter++;
        }
    }
    
    /**
     * <p>
     * Fills a given tree with test nodes.
     * It uses {@link #createRealWorldChildren()} and {@link Tree#addChildNode(TreeNode)}.
     * </p>
     * <ul>
     * <li>Preconditions: The given tree is empty</li>
     * <li>Effects: The tree is filled with nodes and is not empty anymore.</li>
     * </ul>
     * @param tree an empty {@link Tree}
     */
    protected void fillTree(TreeNode<T> tree) {
        for (final TreeNode<T> node : createRealWorldChildren()) {
            tree.addChildNode(node);
        }
    }
    
    /**
     * Returns the root node, that is initialized as an empty node.
     * @return the root node
     */
    protected TreeNode<T> getRoot() {
        return root;
    }

    
    /* (non Javadoc)
     * Methods to test on TreeNode<T>:
     *   addChild(T)                                     - tested in 2 methods
     *   addChild(TreeNode<T>)                           - tested in 4 methods
     *   addChildren(T...)                               - tested in 1 method
     *   addHierarchyListener(HierarchyListener)
     *   changeParent(TreeNode<T>)                       - tested in 5 methods
     *   contains(TreeNode<T>)                           - tested in 5 methods
     *   equals(TreeNode<T>)                             - tested in 4 methods
     *   getChildAt(int)                                 - tested in 3 methods
     *   getChildren()                                   - tested in 2 methods
     *   getNumberOfChildren()                           - tested in 3 methods
     *   getRoot()                                       - tested in 3 methods
     *   hasChild(T)                                     - tested in 6 methods
     *   insertChildAt(int, TreeNode<T>)                 - tested in 5 methods
     *   isLeaf()                                        - tested in 4 methods
     *   removeAllChildren()
     *   removeChild(TreeNode<T>)                        - tested in 2 methods
     *   removeChildAt(int)                              - tested in 3 methods
     *   removeHierarchyListener(HierarchyListener<T>)
     *   setChildren(Collection<TreeNode<T>>)            - tested in 5 methods
     *   setData(T)                                      - tested in 3 methods
     *   setParent(TreeNode<T>)                          - tested in 1 method
     *   
     * Atomic methods that are untestable:
     *   getData()
     *   getParent()
     */
    
    
    /**
     * Sets the attribute {@link #root} to a new instance of
     * a {@link TreeNode} using {@link #emptyNode()}.
     * This method is called before each test execution. 
     */
    @Before
    public void initializeRoot() {
        this.root = emptyNode();
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
     * Tests {@link TreeNode#getRoot()} on a descendant of the root node.
     */
    @Test
    public void testGetRoot() {
        final TreeNode<T> newNode = root.addChild(newData()).addChild(newData());
        
        final TreeNode<T> expected = root;
        final TreeNode<T> actual = newNode.getRoot();
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TreeNode#getRoot()} on the root node itself.
     */
    @Test
    public void testGetRootSelf() {
        final TreeNode<T> expected = root;
        final TreeNode<T> actual = root.getRoot();
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link TreeNode#getRoot()} on a real world example.
     */
    @Test
    public void testGetRootRealWorld() {
        fillTree(root);
        
        Assert.assertEquals("root failed", root, root.getRoot());
        for (final TreeNode<T> child : root.getChildren()) {
            if (child == null) continue;
            Assert.assertEquals("child of root failed", root, child.getRoot());
            for (final TreeNode<T> grandchild : child.getChildren()) {
                if (grandchild == null) continue;
                Assert.assertEquals("grand-child of root failed", root, grandchild.getRoot());
                for (final TreeNode<T> grandgrandchild : child.getChildren()) {
                    if (grandgrandchild == null) continue;
                    Assert.assertEquals("grand-grand-child of root failed", root, grandgrandchild.getRoot());
                }
            }
        }
    }
    
    /**
     * Tests {@link TreeNode#equals(Object)} on 2 TreeNodes with the same data.
     */
    @Test
    public void testEqualsWithData() {
        final T data = newData();
        final TreeNode<T> node1 = newNode(data);
        final TreeNode<T> node2 = newNode(data);
        
        Assert.assertEquals(node1, node2);
    }
    
    /**
     * Tests {@link TreeNode#equals(Object)} on 2 empty TreeNodes.
     */
    @Test
    public void testEqualsEmptyNode() {
        final TreeNode<T> node1 = emptyNode();
        final TreeNode<T> node2 = emptyNode();
        
        Assert.assertEquals(node1, node2);
    }
    
    /**
     * Tests {@link TreeNode#equals(Object)} on 2 TreeNodes which each have 2 children.
     */
    @Test
    public void testEqualsWithTwoChildren() {
        final TreeNode<T> node1 = emptyNode();
        final TreeNode<T> node2 = emptyNode();
        
        final T data1 = newData();
        final T data2 = newData();
        node1.addChild(data1);
        node1.addChild(data2);
        node2.addChild(data1);
        node2.addChild(data2);
        
        Assert.assertEquals(node1, node2);
    }
    
    /**
     * Tests {@link TreeNode#equals(Object)} with real world examples (complex trees).
     */
    @Test
    public void testEqualsRealWorld() {
        final TreeNode<T> node1 = emptyNode();
        final TreeNode<T> node2 = emptyNode();
        fillTree(node1);
        fillTree(node2);
        
        Assert.assertEquals(node1, node2);
    }
    
    /**
     * Test {@link TreeNode#getChildren()} on an empty TreeNode.
     */
    @Test
    public void testGetChildrenEmptyNode() {
        final Collection<TreeNode<T>> expected = Collections.emptySet();
        final Collection<TreeNode<T>> actual = root.getChildren();
        assertEqualsCollections(expected, actual);
    }

    /**
     * Tests {@link TreeNode#getChildren()} on a real world example.
     */
    @Test
    public void testGetChildrenRealWorld() {
        fillTree(root);
        
        final Collection<TreeNode<T>> expected = createRealWorldChildren();
        final Collection<TreeNode<T>> actual = root.getChildren();
        assertEqualsCollections(expected, actual);
    }

    /**
     * Tests {@link TreeNode#removeChildNode(TreeNode)}.
     */
    @Test
    public void testRemoveChild() {
        final T data = newData();
        
        final TreeNode<T> newChild = root.addChild(data);
        Assert.assertEquals(1, root.getNumberOfChildren());
        Assert.assertEquals("root is not parent of new child - addChild(TreeNode) failed", 
                root, newChild.getParent());
        
        root.removeChildNode(newChild);
        Assert.assertEquals(0, root.getNumberOfChildren());
        Assert.assertEquals("root is still parent of child after remove", null, newChild.getParent());
    }
    
    /**
     * Tests {@link TreeNode#removeChildNode(TreeNode)} with a null value.
     * Expects a {@link NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testRemoveChildNull() {
        root.removeChildNode(null);
    }

    /**
     * Tests {@link TreeNode#setChildren(java.util.Collection)}.
     */
    @Test
    public void testSetChildren() {
        root.setChildren(createRealWorldChildren());
        final Collection<TreeNode<T>> expected = createRealWorldChildren();
        final Collection<TreeNode<T>> actual = root.getChildren();
        assertEqualsCollections(expected, actual);
        
        for (TreeNode<T> child : root.getChildren()) {
            Assert.assertEquals("root is not parent of one of the children", root, child.getParent());
        }
    }
    
    /**
     * Tests {@link TreeNode#setChildren(Collection)} on the root node that previously had children
     * with real-world children.
     * The old children should be removed from the root node and the new nodes
     * should be children of the root node.
     */
    @Test
    public void testSetChildrenOverridePreviousChildren() {
        final TreeNode<T> child1 = root.addChild(newData());
        final TreeNode<T> child2 = root.addChild(newData());
        final TreeNode<T> child3 = root.addChild(newData());
        final TreeNode<T> child4 = root.addChild(newData());
        
        root.setChildren(createRealWorldChildren());
        final Collection<TreeNode<T>> expected = createRealWorldChildren();
        final Collection<TreeNode<T>> actual = root.getChildren();
        assertEqualsCollections(expected, actual);
        
        for (TreeNode<T> child : root.getChildren()) {
            Assert.assertEquals("root is not parent of one of the new children", root, child.getParent());
        }
        
        Assert.assertEquals("old child1 not removed from root", null, child1.getParent());
        Assert.assertEquals("old child2 not removed from root", null, child2.getParent());
        Assert.assertEquals("old child3 not removed from root", null, child3.getParent());
        Assert.assertEquals("old child4 not removed from root", null, child4.getParent());
    }
    
    /**
     * Tests {@link TreeNode#setChildren(java.util.Collection)} with a null value.
     * Expects a {@link NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testSetChildrenNull() {
        root.setChildren(null);
    }
    
    /**
     * Test {@link TreeNode#setChildren(Collection)} with a collection that has exactly one null value.
     * Expects a {@link NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testSetChildrenOneElementNull() {
        final Collection<TreeNode<T>> testList = ImmutableList.of(
                emptyNode(), newNode(newData()), null, emptyNode());
        
        root.setChildren(testList);
    }
    
    /**
     * Tests {@link TreeNode#setChildren(Collection)} with getChildren() as its argument.
     */
    @Test
    public void testSetChildrenOfGetChildren() {
        root.setChildren(createRealWorldChildren());
        root.setChildren(root.getChildren());

        final Collection<TreeNode<T>> expected = createRealWorldChildren();
        final Collection<TreeNode<T>> actual = root.getChildren();
        assertEqualsCollections(expected, actual);
        
        for (TreeNode<T> child : root.getChildren()) {
            Assert.assertEquals("root is not parent of one of the children", root, child.getParent());
        }
    }

    /**
     * Tests {@link TreeNode#addChildNode(TreeNode)}.
     */
    @Test
    public void testAddChildNode() {
        final TreeNode<T> newNode = emptyNode();
        root.addChildNode(newNode);

        Assert.assertEquals("wrong number of children after add", 1, root.getNumberOfChildren());
        Assert.assertEquals("instance not parent of new node", root, newNode.getParent());
    }
    
    /**
     * Tests {@link TreeNode#addChildNode(TreeNode)} with itself as argument.
     * Expects an IllegalArgumentException.
     * @throws IllegalArgumentException expected
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddChildNodeSelf() {
        root.addChildNode(root);
    }
    
    /**
     * Tests {@link TreeNode#addChild(Object)} with its parent as argument.
     * Expects an IllegalArgumentException.
     * @throws IllegalArgumentException expected
     */
    @Test(expected = IllegalArgumentException.class)
    public void testAddChildNodeParent() {
        final TreeNode<T> newNode = newNode(newData());
        root.addChildNode(newNode);
        
        // the following line should throw the error
        newNode.addChildNode(root);
    }

    /**
     * Tests {@link TreeNode#addChildNode(TreeNode)} with a null value.
     * Expects a {@link NullPointerException}.
     */
    @Test(expected = NullPointerException.class)
    public void testAddChildNodeNull() {
        root.addChildNode(null);
    }

    /**
     * Tests {@link TreeNode#addChild(Object)} with a non-empty string value.
     */
    @Test
    public void testAddChildT() {
        final T data = newData();
        final TreeNode<T> newNode = root.addChild(data);
        Assert.assertNotNull("error: new node is null", newNode);
        Assert.assertEquals("wrong number of children after add", 1, root.getNumberOfChildren());
        Assert.assertEquals("instance not parent of new node", root, newNode.getParent());
        
        final T expected = data;
        final T actual = newNode.getData();
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TreeNode#addChild(Object)} with null.
     */
    @Test
    public void testAddChildTNull() {
        final TreeNode<T> newNode = root.addChild(null);
        Assert.assertNotNull("error: new node is null", newNode);
        Assert.assertEquals("wrong number of children after add", 1, root.getNumberOfChildren());
        Assert.assertEquals("instance not parent of new node", root, newNode.getParent());
        
        final T expected = null;
        final T actual = newNode.getData();
        Assert.assertEquals("child has wrong data", expected, actual);
    }
    
    /**
     * Tests {@link TreeNode#addChildren(Object[])} with 3 values.
     */
    @SuppressWarnings("unchecked")
    @Test
    public void testAddChildren() {
        final T data1 = newData();
        final T data2 = null;
        final T data3 = newData();
        
        final List<TreeNode<T>> expected = ImmutableList.of(newNode(data1), newNode(data2), newNode(data3));
        final List<TreeNode<T>> actual = root.addChildren(data1, data2, data3);
        Assert.assertEquals("return value of addChildren not as expected", expected, actual);
        
        Assert.assertTrue(data1 + " was not added correctly", root.hasChild(data1));
        Assert.assertTrue(data2 + " was not added correctly", root.hasChild(data2));
        Assert.assertTrue(data3 + " was not added correctly", root.hasChild(data3));
    }
    
    /**
     * Tests {@link TreeNode#hasChild(Object)} on an empty TreeNode.
     */
    @Test
    public void testHasChildEmpty() {
        final boolean expected = false;
        final boolean actual = root.hasChild(newData());
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link TreeNode#hasChild(Object)} on an empty TreeNode with null.
     */
    @Test
    public void testHasChildEmptyWithNull() {
        final boolean expected = false;
        final boolean actual = root.hasChild(null);
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link TreeNode#hasChild(Object)} on a TreeNode that has a childData null with the value null.
     */
    @Test
    public void testHasChildWithNull() {
        final T childData = null;
        root.addChild(childData);
        
        final boolean expected = true;
        final boolean actual = root.hasChild(null);
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link TreeNode#hasChild(Object)} on a TreeNode that has one child with its only child.
     */
    @Test
    public void testHasChildWithChild() {
        final T data = newData();
        root.addChild(data);
        
        final boolean expected = true;
        final boolean actual = root.hasChild(data);
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link TreeNode#hasChild(Object)} on a non-empty TreeNode with another value.
     */
    @Test
    public void testHasChildWithUnrelated() {
        root.addChild(newData());
        
        final boolean expected = false;
        final boolean actual = root.hasChild(newData());
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link TreeNode#hasChild(Object)} on a real-world example with one of its children.
     */
    @Test
    public void testHasChildRealWorld() {
        fillTree(root);
        
        int counter = 1;
        for (TreeNode<T> child : root.getChildren()) {
            if (child == null) continue;
            final String message = "child #" + counter + " (data=" + child.getData() + ") did not pass hasChild";
            final boolean expected = true;
            final boolean actual = root.hasChild(child.getData());
            Assert.assertEquals(message, expected, actual);
            counter++;
        }
    }

    /**
     * Tests {@link TreeNode#getNumberOfChildren()} before and after addChild calls.
     */
    @Test
    public void testGetNumberOfChildrenAfterAddChildT() {
        root.addChild(newData());
        Assert.assertEquals("1. after first addChild(T)", 1, root.getNumberOfChildren());
        root.addChild(newData());
        Assert.assertEquals("2. after second addChild(T)", 2, root.getNumberOfChildren());
    }
    
    /**
     * Tests {@link TreeNode#getChildren()} after removeChild calls. 
     */
    public void testGetNumberOfChildrenAfterRemoveChild() {
        final TreeNode<T> child1 = root.addChild(newData());
        root.addChild(newData());
        
        root.removeChildNode(child1);
        Assert.assertEquals(1, root.getNumberOfChildren());
    }

    /**
     * Tests {@link TreeNode#getNumberOfChildren()} on a real world tree example.
     */
    @Test
    public void testGetNumberOfChildrenRealWorld() {
        fillTree(root);
        
        final int expected = createRealWorldChildren().size();
        final int actual = root.getNumberOfChildren();
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TreeNode#getChildAt(int)} on a TreeNode with 3 children, with the value: 1.
     */
    @Test
    public void testGetChildAt1() {
        root.addChild(newData());
        final TreeNode<T> child1 = root.addChild(newData());
        root.addChild(newData());
        
        final TreeNode<T> expected = child1;
        final TreeNode<T> actual = root.getChildAt(1);
        Assert.assertEquals(expected, actual);
    }

    /**
     * Tests {@link TreeNode#getChildAt(int)} on a TreeNode with 3 children, with a negative value.
     * Expects an {@link IndexOutOfBoundsException}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetChildAtNegative() {
        root.addChild(newData());
        root.addChild(newData());
        root.addChild(newData());
        
        root.getChildAt(-1);
    }

    /**
     * Tests {@link TreeNode#getChildAt(int)} on a TreeNode with 3 children, with the value: 3.
     * Expects an {@link IndexOutOfBoundsException}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testGetChildAtOutOfBounds() {
        root.addChild(newData());
        root.addChild(newData());
        root.addChild(newData());
        
        root.getChildAt(3);
    }
    
    /**
     * Tests {@link TreeNode#insertChildAt(int, TreeNode)} on an empty TreeNode.
     */
    @Test
    public void testEmptyInsertChildAt() {
        final TreeNode<T> node = newNode(newData());
        root.insertChildAt(0, node);
        
        final List<TreeNode<T>> expected = ImmutableList.of(node);
        final List<TreeNode<T>> actual = ImmutableList.copyOf(root.getChildren());
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link TreeNode#insertChildAt(int, TreeNode)} on a TreeNode with 2 children, with the values:
     * 2 and an empty TreeNode.
     */
    @Test
    public void testInsertChildAtEnd() {
        final TreeNode<T> node1 = root.addChild(newData());
        final TreeNode<T> node2 = root.addChild(newData());
        final TreeNode<T> insertedNode = emptyNode();
        
        root.insertChildAt(2, insertedNode);
        
        final List<TreeNode<T>> expected = ImmutableList.of(node1, node2, insertedNode);
        final List<TreeNode<T>> actual = ImmutableList.copyOf(root.getChildren());
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("inserted child has wrong parent", root, insertedNode.getParent());
    }
    
    /**
     * Tests {@link TreeNode#insertChildAt(int, TreeNode)} on a TreeNode with 2 children, with the values:
     * 1 and an empty TreeNode.
     */
    @Test
    public void testInsertChildAtMiddle() {
        final TreeNode<T> node1 = root.addChild(newData());
        final TreeNode<T> node2 = root.addChild(newData());
        final TreeNode<T> insertedNode = emptyNode();
        
        root.insertChildAt(1, insertedNode);

        final List<TreeNode<T>> expected = ImmutableList.of(node1, insertedNode, node2);
        final List<TreeNode<T>> actual = ImmutableList.copyOf(root.getChildren());
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("inserted child has wrong parent", root, insertedNode.getParent());
    }
    
    /**
     * Tests {@link TreeNode#insertChildAt(int, TreeNode)} on a TreeNode with 2 children, with the values:
     * 0 and an empty TreeNode.
     */
    @Test
    public void testInsertChildAtStart() {
        final TreeNode<T> node1 = root.addChild(newData());
        final TreeNode<T> node2 = root.addChild(newData());
        final TreeNode<T> insertedNode = emptyNode();
        
        root.insertChildAt(0, insertedNode);

        final List<TreeNode<T>> expected = ImmutableList.of(insertedNode, node1, node2);
        final List<TreeNode<T>> actual = ImmutableList.copyOf(root.getChildren());
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("inserted child has wrong parent", root, insertedNode.getParent());
    }
    
    /**
     * Tests {@link TreeNode#insertChildAt(int, TreeNode)} on a TreeNode with 2 children, with the values:
     * 3 and an empty TreeNode.
     * Expects {@link IndexOutOfBoundsException}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertChildAtOutOfBounds() {
        root.addChild(newData());
        root.addChild(newData());
        
        root.insertChildAt(3, emptyNode());
    }
    
    /**
     * Tests {@link TreeNode#insertChildAt(int, TreeNode)} on a TreeNode with 2 children, with the values:
     * -1 and an empty TreeNode.
     * Expects {@link IndexOutOfBoundsException}.
     */
    @Test(expected = IndexOutOfBoundsException.class)
    public void testInsertChildAtNegative() {
        root.addChild(newData());
        root.addChild(newData());
        
        root.insertChildAt(-1, emptyNode());
    }

    /**
     * Tests {@link TreeNode#removeChildAt(int)} on a TreeNode with 3 children, with the value: 0.
     */
    @Test
    public void testRemoveChildAtStart() {
        final TreeNode<T> node1 = root.addChild(newData());
        final TreeNode<T> node2 = root.addChild(newData());
        final TreeNode<T> node3 = root.addChild(newData());

        root.removeChildAt(0);

        final List<TreeNode<T>> expected = ImmutableList.of(node2, node3);
        final List<TreeNode<T>> actual = ImmutableList.copyOf(root.getChildren());
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("removed child has wrong parent", null, node1.getParent());
    }

    /**
     * Tests {@link TreeNode#removeChildAt(int)} on a TreeNode with 3 children, with the value: 1.
     */
    @Test
    public void testRemoveChildAtMiddle() {
        final TreeNode<T> node1 = root.addChild(newData());
        final TreeNode<T> node2 = root.addChild(newData());
        final TreeNode<T> node3 = root.addChild(newData());

        root.removeChildAt(1);

        final List<TreeNode<T>> expected = ImmutableList.of(node1, node3);
        final List<TreeNode<T>> actual = ImmutableList.copyOf(root.getChildren());
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("removed child has wrong parent", null, node2.getParent());
    }

    /**
     * Tests {@link TreeNode#removeChildAt(int)} on a TreeNode with 3 children, with the value: 2.
     */
    @Test
    public void testRemoveChildAtEnd() {
        final TreeNode<T> node1 = root.addChild(newData());
        final TreeNode<T> node2 = root.addChild(newData());
        final TreeNode<T> node3 = root.addChild(newData());

        root.removeChildAt(2);

        final List<TreeNode<T>> expected = ImmutableList.of(node1, node2);
        final List<TreeNode<T>> actual = ImmutableList.copyOf(root.getChildren());
        Assert.assertEquals(expected, actual);
        Assert.assertEquals("removed child has wrong parent", null, node3.getParent());
    }
    
    /**
     * Tests {@link TreeNode#setData(Object)} with a normal value.
     * @throws PropertyVetoException should not happen
     */
    @Test
    public void testSetData() throws PropertyVetoException {
        final T data = newData();
        root.setData(data);
        
        final T expected = data;
        final T actual = root.getData();
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link TreeNode#setData(Object)} with null.
     * @throws PropertyVetoException should not happen
     */
    @Test
    public void testSetDataNull() throws PropertyVetoException {
        root.setData(null);
        
        final T expected = null;
        final T actual = root.getData();
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link TreeNode#setData(Object)} on a child of the root node.
     * The altered child should still be a child of its parent via "root.hasChild(data)".
     * @throws PropertyVetoException should not happen
     */
    @Test
    public void testSetDataChild() throws PropertyVetoException {
        final T data = newData();
        final TreeNode<T> child = root.addChild(newData());
        child.setData(data);

        final boolean expected = true;
        final boolean actual = root.hasChild(data);
        Assert.assertEquals("root.hasChild(data)", expected, actual);
    }
    
    /**
     * Tests {@link TreeNode#setParent(TreeNode)}.
     * This is only a test for the setter-functionality.
     */
    @Test
    public void testSetParent() {
        final TreeNode<T> node = newNode(newData());
        final TreeNode<T> newParent = newNode(newData());
        node.setParent(newParent);
        
        final TreeNode<T> expected = newParent;
        final TreeNode<T> actual = node.getParent();
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link TreeNode#changeParent(TreeNode)} from null to a new Node.
     */
    @Test
    public void testChangeParentFromNullToNode() {
        final TreeNode<T> newParent = newNode(newData());
        final TreeNode<T> newNode = newNode(newData());
        
        final boolean expectedResult = true;
        final boolean actualResult = newNode.changeParent(newParent);
        Assert.assertEquals("changeParent returned wrong result", expectedResult, actualResult);
        
        final TreeNode<T> expected = newParent;
        final TreeNode<T> actual = newNode.getParent();
        Assert.assertSame("new parent is not set", expected, actual);
        
        Assert.assertTrue("new parent does not contain node", newParent.contains(newNode));
        Assert.assertEquals("newParent.getNumberOfChildren()", 1, newParent.getNumberOfChildren());
        Assert.assertTrue("new parent does not have node as child", newParent.hasChild(newNode.getData()));
    }
    
    /**
     * Tests {@link TreeNode#changeParent(TreeNode)} from one node to another node.
     */
    @Test
    public void testChangeParentFromNodeToOtherNode() {
        final TreeNode<T> newParent = newNode(newData());
        final TreeNode<T> newNode = root.addChild(newData());
        
        final boolean expectedResult = true;
        final boolean actualResult = newNode.changeParent(newParent);
        Assert.assertEquals("changeParent returned wrong result", expectedResult, actualResult);
        
        final TreeNode<T> expected = newParent;
        final TreeNode<T> actual = newNode.getParent();
        Assert.assertSame("new parent is not set", expected, actual);
        
        Assert.assertFalse("old parent still contains node", root.contains(newNode));
        Assert.assertEquals("old parent has wrong number of children", 0, root.getNumberOfChildren());
        Assert.assertFalse("old parent still has node as child", root.hasChild(newNode.getData()));
        
        Assert.assertTrue("new parent does not contain node", newParent.contains(newNode));
        Assert.assertEquals("newParent.getNumberOfChildren()", 1, newParent.getNumberOfChildren());
        Assert.assertTrue("new parent does not have node as child", newParent.hasChild(newNode.getData()));
    }
    
    /**
     * Tests {@link TreeNode#changeParent(TreeNode)} from a node to the same node again.
     * This should have no effect.
     */
    @Test
    public void testChangeParentFromNodeToSameNode() {
        final TreeNode<T> parent = newNode(newData());
        final TreeNode<T> newNode = parent.addChild(newData());
        
        final boolean expectedResult = false;
        final boolean actualResult = newNode.changeParent(parent);
        Assert.assertEquals("changeParent returned wrong result", expectedResult, actualResult);
        
        final TreeNode<T> expected = parent;
        final TreeNode<T> actual = newNode.getParent();
        Assert.assertSame("new parent is not set", expected, actual);
        
        Assert.assertTrue("parent does not contain node", parent.contains(newNode));
        Assert.assertEquals("newParent.getNumberOfChildren()", 1, parent.getNumberOfChildren());
        Assert.assertTrue("new parent does not have node as child", parent.hasChild(newNode.getData()));
    }
    
    /**
     * Tests {@link TreeNode#changeParent(TreeNode)} from null to null (so from no parent to no parent).
     * This should have no effect.
     */
    @Test
    public void testChangeParentFromNullToNull() {
        final TreeNode<T> newNode = newNode(newData());

        final boolean expectedResult = false;
        final boolean actualResult = newNode.changeParent(null);
        Assert.assertEquals("changeParent returned wrong result", expectedResult, actualResult);
        
        final TreeNode<T> expected = null;
        final TreeNode<T> actual = newNode.getParent();
        Assert.assertEquals("new parent is not null", expected, actual);
    }

    /**
     * Tests {@link TreeNode#changeParent(TreeNode)} from a node to null.
     * This means that a node is extracted from a tree and is then a new root node.
     */
    @Test
    public void testChangeParentFromNodeToNull() {
        final TreeNode<T> parent = newNode(newData());
        final TreeNode<T> newNode = parent.addChild(newData());
        
        final boolean expectedResult = true;
        final boolean actualResult = newNode.changeParent(null);
        Assert.assertEquals("changeParent returned wrong result", expectedResult, actualResult);

        final TreeNode<T> expected = null;
        final TreeNode<T> actual = newNode.getParent();
        Assert.assertEquals("new parent is not null", expected, actual);

        Assert.assertFalse("old parent still contains node", parent.contains(newNode));
        Assert.assertEquals("old parent has wrong number of children", 0, parent.getNumberOfChildren());
        Assert.assertFalse("old parent still has node as child", parent.hasChild(newNode.getData()));
    }
    
    /**
     * Tests {@link TreeNode#isLeaf()} on an empty root node.
     */
    @Test
    public void testIsLeafRootEmpty() {
        final boolean expected = false;
        final boolean actual = root.isLeaf();
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link TreeNode#isLeaf()} on an empty root node.
     */
    @Test
    public void testIsLeafRoot() {
        final TreeNode<T> child = root.addChild(newData());
        child.addChild(newData());
        
        final boolean expected = false;
        final boolean actual = root.isLeaf();
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link TreeNode#isLeaf()} on a branch (has parent and one child).
     */
    @Test
    public void testIsLeafBranch() {
        final TreeNode<T> child = root.addChild(newData());
        child.addChild(newData());
        
        final boolean expected = false;
        final boolean actual = child.isLeaf();
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link TreeNode#isLeaf()} on a leaf (has parent and no children).
     */
    @Test
    public void testIsLeafLeaf() {
        final TreeNode<T> child = root.addChild(newData());
        final TreeNode<T> leaf = child.addChild(newData());
        
        final boolean expected = true;
        final boolean actual = leaf.isLeaf();
        Assert.assertEquals(expected, actual);
    }
    
    /**
     * Tests {@link TreeNode#contains(TreeNode)} with the parameter null.
     */
    @Test
    public void testContainsNull() {
        root.addChild(newData());

        Assert.assertEquals(false, root.contains(null));
    }
    
    /**
     * Tests {@link TreeNode#contains(TreeNode)} on a direct child.
     */
    @Test
    public void testContainsChild() {
        final TreeNode<T> node1 = root.addChild(newData());

        Assert.assertEquals(true, root.contains(node1));
    }
    
    /**
     * Tests {@link TreeNode#contains(TreeNode)} on a grand-grand-child
     * (child of a child of a child).
     */
    @Test
    public void testContainsDescendant() {
        final TreeNode<T> node1 = root.addChild(newData());
        node1.addChild(newData());
        final TreeNode<T> node1x2 = node1.addChild(newData());
        final TreeNode<T> node1x2x1 = node1x2.addChild(newData());
        node1.addChild(newData());
        root.addChild(newData());

        Assert.assertEquals(true, root.contains(node1x2x1));
    }
    
    /**
     * Tests {@link TreeNode#contains(TreeNode)} on a real world TreeNode with an unrelated TreeNode.
     */
    @Test
    public void testContainsUnrelated() {
        fillTree(root);
        
        final TreeNode<T> unrelated = newNode(newData());
        unrelated.addChild(newData());
        unrelated.addChild(newData());
        
        Assert.assertEquals(false, root.contains(unrelated));
    }
    
    /**
     * Tests {@link TreeNode#contains(TreeNode)} on a real world TreeNode with an unrelated TreeNode
     * that is equal to the first child.
     */
    @Test
    public void testContainsUnrelatedButEqualToChild() {
        fillTree(root);
        
        // this node is unrelated, but equal with a childNode
        final TreeNode<T> unrelated = createRealWorldChildren().iterator().next();
        
        Assert.assertEquals(false, root.contains(unrelated));
    }

}
