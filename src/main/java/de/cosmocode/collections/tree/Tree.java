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

import java.util.Collection;
import java.util.List;


/**
 * Represents a Tree of Objects of generic type E. The Tree is represented as
 * a single rootElement which itself has several children as {@code Collection<TreeNode<E>>}.
 * There is no restriction on the number of children that a particular node may have.
 * This Tree provides a method to serialize the Tree into a List.
 * It has several methods to allow easy updates of Nodes in the Tree.
 * 
 * <br> Taken from: http://sujitpal.blogspot.com/2006/05/java-data-structure-generic-tree.html
 * 
 * @param <E> a generic type that indicates the data stored in the {@link TreeNode}s
 */
public interface Tree<E> {
    
    /**
     * Return the root Node of the tree.
     * It must never be null.
     * @return the root element.
     */
    TreeNode<E> getRoot();
    
    /**
     * Set the root Element for the tree.
     * @param rootElement the root element to set.
     * @throws NullPointerException if rootElement is null
     */
    void setRootElement(TreeNode<E> rootElement);
    
    /**
     * Return the children of TreeNode<T>. The Tree<T> is represented by a single
     * root TreeNode<T> whose children are represented by a List<TreeNode<T>>. Each of
     * these TreeNode<T> elements in the List can have children. The getChildren()
     * method will return the children of a TreeNode<T>.
     * @return the children of Node<T>
     */
    Collection<TreeNode<E>> getChildren();
    
    /**
     * Sets the children of a Node<T> object. See docs for {@link #getChildren()} for
     * more information.
     * @param children the List<Node<T>> to set.
     */
    void setChildren(Collection<TreeNode<E>> children);
    
    /**
     * Add multiple children to this TreeNode. 
     * @param children the children to add
     * @return a list with the newly added children
     */
    List<TreeNode<E>> addChildren(E... children);
    
    /**
     * Returns the number of immediate children of this Node<T>.
     * @return the number of immediate children.
     */
    int getNumberOfChildren();
    
    /**
     * Adds a child to the list of children for this TreeNode<T>.
     * It creates a new Instance of a TreeNode<T> that has its data set to childData.
     * The created TreeNode<T> is then returned.
     * @param childData the data for the new child-node
     * @return the created child node
     */
    TreeNode<E> addChild(E childData);
    
    /**
     * Adds a child to the list of children for this Node<T>. The addition of
     * the first child will create a new List<Node<T>>.
     * @param child a Node<T> object to set.
     */
    void addChildNode(TreeNode<E> child);
    
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
     * @param child the Node<T> object to insert.
     * @throws IndexOutOfBoundsException if thrown.
     */
    void insertChildAt(int index, TreeNode<E> child) throws IndexOutOfBoundsException;
    
    /**
     * Remove the given TreeNode<T> element from the List<Node<T>>.
     * If the given TreeNode<T> is not a child of this TreeNode<T>, then nothing happens.
     * @param child the child to remove.
     */
    void removeChildNode(TreeNode<E> child);
    
    /**
     * Remove the Node<T> element at index index of the List<Node<T>>.
     * @param index the index of the element to delete.
     * @throws IndexOutOfBoundsException if thrown.
     */
    void removeChildAt(int index) throws IndexOutOfBoundsException;
    
    /**
     * Returns true if the given TreeNode<T> is a descendant of this Tree<T>,
     * or in other words: Returns true if this TreeNode<T> is a part of this Tree<T>.
     * Returns false otherwise.
     * @param descendant the possible descedant of this Tree<T>
     * @return true if this Tree<T> contains the parameter, false otherwise
     */
    boolean contains(TreeNode<E> descendant);
    
    /**
     * Returns the Tree<E> as a List of TreeNode<E> objects.
     * The elements are in pre-order.
     * @return a {@code List<TreeNode<E>>}.
     * @deprecated use {@code traverse(TraverseMode.PRE_ORDER)} instead
     */
    @Deprecated
    List<TreeNode<E>> toPreOrderedList();
    
    /**
     * Returns an Iterable that traverses the {@code Tree<T>} in the given {@link TraverseMode}.
     * Look at the JavaDoc of TraverseMode for further informations.
     * @param mode the mode to traverse in.
     * @return an Iterable that returns an iterator that traverses this Tree in the given TraverseMode
     * 
     * @see TraverseMode
     */
    Iterable<E> traverse(TraverseMode mode);
    
    @Override
    int hashCode();
    
    @Override
    boolean equals(Object obj);
    
    /**
     * Returns true if this Tree is equal to the other,
     * false otherwise.
     * Two trees are commonly considered equal, if their root elements are equal,
     * if not stated otherwise by the implementing class.
     * @param t the other Tree
     * @return true if this tree is equal to the other, false otherwise
     */
    boolean equals(Tree<?> t);

}
