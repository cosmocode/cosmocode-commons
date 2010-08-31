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

import java.beans.PropertyChangeListener;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.Collection;
import java.util.List;

/**
 * <p> Represents a node of the {@code Tree<E>} class. The TreeNode<T> is also a container, and
 * can be thought of as instrumentation to determine the location of the type T
 * in the {@code Tree<E>}.
 * </p>
 * <p> Prototype from: http://sujitpal.blogspot.com/2006/05/java-data-structure-generic-tree.html
 * </p>
 * 
 * @param <E> a generic type that indicates the data stored in this TreeNode
 */
public interface TreeNode<E> {

    /* possible improvements:
     *
     * let TreeNode<T> extend Iterable<T>
     *
     * TreeNode<T> search(T data) -> searches in tree hierarchy for data T,
     *                                     returns corresponding TreeNode<T> or null
     * TreeNode<T> getChild(T data)  -> returns the child node for data T or null if no such child exists
     * boolean isBranch() -> returns true if this node neither root nor leaf, i.e. has parent and children
     * boolean isRoot()  -> returns true if this is the root node, false otherwise
     * TreeNode<T> removeChild(T childData) -> removes first child with data T
     * int height()   -> the height of the tree, that is: the number of parents until root is reached
     * int depth()    -> depth is the minimum number of steps to get to the deepest possible child
     *
     * TreeNode<T> firstChild()      -> returns first child or null if none exists
     * TreeNode<T> lastChild()       -> returns last child      --- "" ---
     * TreeNode<T> previousSibling() -> returns previous sibling   --- "" ---
     *                                      previous sibling = the child of the parent that is before this node
     * TreeNode<T> nextSibling()     -> next sibling ... see above
     *
     * addChildNode(TreeNode<T>) should return a boolean, indicating success (or not)
     * removeChildNode(TreeNode<T>) should return a boolean, indicating success
     * removeChildAt(int) should return the TreeNode<T> that was removed
     * insertChildAt(int, TreeNode<T>) should return a boolean, indicating success
     */
    
    
    String DATA_PROPERTY = "TreeNode data";
    
    /**
     * Returns the root Node of the tree.
     * The root-node returns itself on this method-call.
     * @return the root element.
     */
    TreeNode<E> getRoot();
    
    /**
     * Returns the parent of this TreeNode. Each TreeNode<T> except the root
     * TreeNode<T> must have a non-null parent.
     * This means: If this method returns null then this TreeNode<T> is the root node.
     * @return the parent of this TreeNode or null if this TreeNode is the root.
     */
    TreeNode<E> getParent();
    
    /**
     * <p>
     * Sets the parent of this TreeNode. Each TreeNode<T> except the root
     * TreeNode<T> must have a non-null parent.
     * </p>
     * <p>
     * This means: If this method is called with null, then this TreeNode is a new root node.
     * The root node may throw an {@link UnsupportedOperationException} on this method.
     * </p>
     * <p><strong>Attention:</strong> This method alters neither the old nor the new parent.
     * If this method is used without further changes to this node's old and new parent
     * then they won't recognize the changes.
     * This could lead to inconsistent behavior and we strongly advise against
     * calling this method directly.
     * Use {@link #changeParent(TreeNode)} instead.
     * </p>
     * @param parent the (new) parent of this TreeNode
     * @throws UnsupportedOperationException if setting the parent is disallowed by the implementation
     * 
     * @see #changeParent(TreeNode)
     */
    void setParent(TreeNode<E> parent); 
    
    /**
     * <p>
     * Changes the parent of this TreeNode<T> so that this TreeNode<T> is no longer
     * a child of the old parent, but a child of the new parent.
     * </p>
     * <p>
     * This TreeNode<T> is removed from its current parent and added to the new parent.
     * {@link #getParent()} will return the newParent after this method call.
     * </p>
     * <p>
     * This method does nothing if the current parent and the new parent are the same.
     * </p>
     * @param newParent the new parent for this TreeNode<T>
     * @return true if this method changed the parent of this TreeNode<T>, false otherwise
     * @throws UnsupportedOperationException if altering the parent is disallowed by the implementation
     */
    boolean changeParent(TreeNode<E> newParent);
    
    /**
     * Return the children of TreeNode<T>. The Tree<T> is represented by a single
     * root TreeNode<T> whose children are represented by a List<TreeNode<T>>. Each of
     * these TreeNode<T> elements in the List can have children. The getChildren()
     * method will return the children of a TreeNode<T>.
     * @return the children of this TreeNode&lt;T&gt;
     */
    Collection<TreeNode<E>> getChildren();
    
    /**
     * Sets the children of a {@link TreeNode}<T> object. See docs for {@link #getChildren()} for
     * more information.
     * @param children the List<Node<T>> to set.
     * @throws NullPointerException if the parameter `children` or one of its elements is null
     * @throws UnsupportedOperationException if altering the children is disallowed by the implementation
     */
    void setChildren(Collection<TreeNode<E>> children);
    
    /**
     * Add multiple children to this TreeNode. 
     * @param children the children to add
     * @return a list with the newly added children
     * @throws UnsupportedOperationException if adding children is disallowed by the implementation
     */
    List<TreeNode<E>> addChildren(E... children);
    
    /**
     * Returns the number of direct children of this {@code TreeNode<T>}.
     * @return the number of direct children.
     */
    int getNumberOfChildren();
    
    /**
     * Adds a child to the list of children for this TreeNode.
     * It creates a new Instance of a TreeNode<T> that has its data set to childData.
     * The created TreeNode<T> is then returned.
     * @param childData the data for the new child-node
     * @return the created child node
     * @throws UnsupportedOperationException if adding children is disallowed by the implementation
     */
    TreeNode<E> addChild(E childData);
    
    /**
     * Adds a child to the list of children for this TreeNode&lt;T&gt;. The addition of
     * the first child will create a new List<Node<T>>.
     * @param child a TreeNode<T> object to set.
     * @throws IllegalArgumentException if the child is this node or an ancestor of this node
     * @throws NullPointerException if the parameter `child` is null
     * @throws UnsupportedOperationException if adding children is disallowed by the implementation
     */
    void addChildNode(TreeNode<E> child);
    
    /**
     * Determines whether one of the child nodes has the given data.
     * Returns true if at least one of the direct children of this node has the given data,
     * false otherwise.
     * @param childData the data to search for
     * @return true if at least one of this node's direct children has `childData` as data, false otherwise
     */
    boolean hasChild(E childData);
    
    /**
     * Returns the child at index index.
     * @param index the index of the child
     * @return the child at index index
     * @throws IndexOutOfBoundsException if (index < 0 || index >= {@link #getNumberOfChildren()})
     */
    TreeNode<E> getChildAt(int index) throws IndexOutOfBoundsException;
    
    /**
     * Inserts a Node<T> at the specified position in the child list. Will
     * throw an ArrayIndexOutOfBoundsException if the index does not exist.
     * @param index the position to insert at.
     * @param child the TreeNode<T> object to insert.
     * @throws IndexOutOfBoundsException if thrown.
     * @throws NullPointerException if the second parameter `child` is null
     * @throws UnsupportedOperationException if inserting children is disallowed by the implementation
     */
    void insertChildAt(int index, TreeNode<E> child) throws IndexOutOfBoundsException;
    
    /**
     * <p> Removes the given TreeNode<T> child element from this TreeNode.
     * If the given TreeNode<T> is not a child of this TreeNode<T>, then nothing happens.
     * </p>
     * <p> <b>Note:</b> a TreeNode<T> `child` is considered a child of this TreeNode<T>,
     * if <code>child.getParent() == this</code> holds true.
     * </p>
     * @param child the child to remove.
     * @throws NullPointerException if the parameter `child` is null
     * @throws UnsupportedOperationException if removing children is disallowed by the implementation
     */
    void removeChildNode(TreeNode<E> child);
    
    /**
     * Remove the TreeNode&lt;T&gt; child element at index `index`.
     * @param index the index of the element to delete.
     * @throws IndexOutOfBoundsException if thrown.
     * @throws UnsupportedOperationException if removing children is disallowed by the implementation
     */
    void removeChildAt(int index) throws IndexOutOfBoundsException;
    
    /**
     * Removes all children from this TreeNode.
     */
    void removeAllChildren();
    
    /**
     * Returns the stored data.
     * @return the stored data
     */
    E getData();
    
    /**
     * Sets the data to store for this Node.
     * @param data the data to store
     * @throws PropertyVetoException if the given data is not allowed 
     *      either by {@link PropertyChangeListener}s registered on this node or this node itself
     * @throws UnsupportedOperationException if altering the data is disallowed by the implementation
     */
    void setData(E data) throws PropertyVetoException;
    
    /**
     * <p> Returns true if this TreeNode is a leaf, false otherwise.
     * This TreeNode is a leaf if and only if {@link #getNumberOfChildren()} returns 0
     * and {@link #getParent()} returns a non-null value.
     * </p>
     * <p> Or in other words: This TreeNode is a leaf if it has a parent and no children.
     * This means that the root node is never a leaf.
     * </p>
     * @return true if this TreeNode is a leaf, false otherwise
     */
    boolean isLeaf();
    
    /**
     * Returns true if the given TreeNode<T> is a descendant of this TreeNode<T>,
     * or in other words: Returns true if this TreeNode<T> is an ancestor of
     * the given TreeNode<T>.
     * Returns false otherwise.
     * @param descendant the possible descendant of this TreeNode<T>
     * @return true if this TreeNode is an ancestor of the param, false otherwise
     */
    boolean contains(TreeNode<E> descendant);
    
    /**
     * <p> Adds a VetoableChangeListener to this TreeNode (optional operation).
     * It gets notified on every {@link #setData(Object)} call.
     * </p>
     * 
     * @param listener the listener to add
     * @throws UnsupportedOperationException if the implementation does not support listening
     */
    void addVetoableChangeListener(VetoableChangeListener listener);
    
    /**
     * Removes a previously added VetoableChangeListener from this TreeNode (optional operation).
     * 
     * @param listener the listener to remove
     * @throws UnsupportedOperationException if the implementation does not support listening
     */
    void removeVetoableChangeListener(VetoableChangeListener listener);

}
