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
package de.cosmocode.collections.tree.iterator;

import com.google.common.collect.Iterators;
import de.cosmocode.collections.tree.TreeNode;

import java.util.Arrays;
import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * <p> An abstract tree iterator that provides utility methods to
 * walk a tree.
 * </p>
 * <p> All iterator methods are left abstract.
 * </p>
 * 
 * @param <E> the generic type of the Tree to walk
 * 
 * @author Oliver Lorenz
 */
public abstract class AbstractTreeIterator<E> implements Iterator<E> {
    
    private TreeNode<E> currentNode;
    
    private int currentLevel;
    
    private int[] childIndexes;
    
    private Iterator<TreeNode<E>> siblingIterator;
    
    public AbstractTreeIterator(final TreeNode<E> root) {
        if (root == null) throw new NullPointerException("root must not be null");
        if (root.getParent() != null) throw new IllegalArgumentException("given TreeNode is not a root node");
        
        this.currentNode = root;
        this.childIndexes = new int[4];
        Arrays.fill(this.childIndexes, -1);
        this.currentLevel = 0;
        this.siblingIterator = Iterators.emptyIterator();
    }
    
    /**
     * <p> Returns true if the root has been visited, false otherwise.
     * The root is considered visited either when
     * going to root via {@link #parent()} or calling {@link #currentData()} at start.
     * </p>
     * <p> This method always returns false if it is called before every other method.
     * </p>
     * 
     * @return true if root has been visited (data fetched from root), false otherwise
     */
    protected boolean rootVisited() {
        return this.childIndexes[0] == 0;
    }
    
    /**
     * <p> Returns true if the root has not been visited, false otherwise.
     * This is the negation of {@link #rootVisited()}.
     * </p>
     * 
     * @return true if the root has not been visited, false otherwise.
     * 
     * @see #rootVisited()
     */
    protected boolean rootNotVisited() {
        return this.childIndexes[0] == -1;
    }

    /**
     * Returns true if the direct children of this node have been visited.
     * @return true if children have been visited, false otherwise
     */
    protected boolean childrenVisited() {
        if (currentNode.getNumberOfChildren() == 0) {
            return true;
        } else if (currentLevel + 1 >= this.childIndexes.length) {
            return false;
        } else {
            return this.childIndexes[currentLevel + 1] + 1 >= currentNode.getNumberOfChildren();
        }
    }
    
    /**
     * <p> Returns true if the current node is the root node, false otherwise.
     * This is equivalent to <code>getCurrentLevel() == 0</code>.
     * </p>
     * 
     * @return true if the current node is the root node, false otherwise 
     */
    protected boolean isRoot() {
        return currentLevel == 0;
    }
    
    /**
     * <p> Returns true if the current node has children, false otherwise.
     * This is equivalent to <code>numberOfChildren() == 0</code>.
     * </p>
     * 
     * @return true if the current node has children, false otherwise
     */
    protected boolean hasChildren() {
        return currentNode.getNumberOfChildren() > 0;
    }
    
    /**
     * <p> Returns the number of children the current node has.
     * This is always a non-negative number.
     * </p>
     * 
     * @return the number of children of the current node
     */
    protected int numberOfChildren() {
        return currentNode.getNumberOfChildren();
    }
    
    /**
     * <p> Returns the current level (or depth).
     * </p>
     * <p> The level increases on calls to {@link #firstChild()} or {@link #nextChild()}.
     * The level decreases on calls to {@link #parent()}.
     * On every other method call the level stays the same.
     * </p>
     * 
     * @return the current level in the tree
     */
    protected int getCurrentLevel() {
        return currentLevel;
    }
    
    /**
     * <p> Returns the index number of the current element at the current level.
     * This is -1 before a call to any other method.
     * </p>
     * <p> Example:
     * <pre>
     * firstChild();
     * System.out.println(currentIndex());  // 0
     * nextSibling();
     * System.out.println(currentIndex());  // 1
     * nextSibling();
     * System.out.println(currentIndex());  // 2
     * </pre>
     * </p>
     * 
     * @return the index of the current element at the current level
     */
    protected int currentIndex() {
        return this.childIndexes[this.currentLevel];
    }
    
    /**
     * <p> Returns the data of the current element.
     * </p>
     * 
     * @return the data of the current element
     */
    protected E currentData() {
        if (currentLevel == 0) {
            this.childIndexes[0] = 0;
        }
        return currentNode.getData();
    }
    
    /**
     * This method increases the private childIndex array.
     * It is called only if the childIndex array becomes too small
     * and must be resized.
     */
    private void increaseIndexes() {
//        System.out.println("Need to increase index");
        final int[] newIndexes = new int[2 * childIndexes.length];
        System.arraycopy(childIndexes, 0, newIndexes, 0, childIndexes.length);
        Arrays.fill(newIndexes, childIndexes.length, newIndexes.length, -1);
        this.childIndexes = newIndexes;
//        System.out.println("Increased indexes: " + Arrays.toString(this.childIndexes));
    }
    
    /**
     * <p> This method goes to the parent node of the current node.
     * The level is reduced by one after a call to this method.
     * The data of the parent element is returned as by {@link #currentData()}.
     * </p>
     * 
     * @return the parent element's data
     * @throws NoSuchElementException if this method is called on the root node
     */
    protected E parent() {
        if (isRoot()) {
            throw new NoSuchElementException("can not go to parent of root");
        }
        
        this.currentNode = this.currentNode.getParent();
        --this.currentLevel;
        
        if (isRoot()) {
            this.siblingIterator = Iterators.emptyIterator();
        } else {
            this.siblingIterator = this.currentNode.getParent().getChildren().iterator();
            if (currentIndex() >= 0) {
                Iterators.get(this.siblingIterator, currentIndex());
            }
        }
        
        return currentData();
    }

    /**
     * <p> This method goes to the first child node of the current node.
     * The level is increased by one after a call to this method.
     * The data of the child element is returned as by {@link #currentData()}.
     * </p>
     * 
     * @return the data of the first child
     * @throws NoSuchElementException if the current node has no children
     * @see #hasChildren()
     */
    protected E firstChild() {
        if (currentLevel + 1 >= childIndexes.length) increaseIndexes();
        
        this.siblingIterator = this.currentNode.getChildren().iterator();
        this.currentNode = this.siblingIterator.next();
        ++currentLevel;
        this.childIndexes[currentLevel] = 0;
        Arrays.fill(childIndexes, currentLevel + 1, childIndexes.length, -1);
        
        return this.currentNode.getData();
    }

    /**
     * <p> Returns true if the current node has a next child, false otherwise.
     * </p>
     *  
     * @return true if the current node has a next child, false otherwise.
     * @see #nextChild()
     */
    protected boolean hasNextChild() {
        if (currentNode.getNumberOfChildren() == 0) {
            return false;
        } else if (currentLevel + 1 >= this.childIndexes.length) {
            return true;
        } else {
            return this.childIndexes[currentLevel + 1] + 1 < currentNode.getNumberOfChildren();
        }
    }

    /**
     * <p> This method goes to the next child node of the current node.
     * This method is only useful in conjunction with {@link #parent()},
     * because the last visited child node is saved when going to the parent.
     * This means that <code>parent(); nextChild();</code> is equivalent to <code>nextSibling();</code>.
     * </p>
     * <p> The level is increased by one after a call to this method.
     * The data of the child element is returned as by {@link #currentData()}.
     * </p>
     * 
     * @return the data of the next child
     * @throws NoSuchElementException if the current node has no more children
     * @see #hasNextChild()
     */
    protected E nextChild() {
        if (currentLevel + 1 >= childIndexes.length) increaseIndexes();
        
        if (this.childIndexes[currentLevel + 1] + 1 >= this.currentNode.getNumberOfChildren()) {
            throw new NoSuchElementException();
        }
        
        ++this.currentLevel;
        this.childIndexes[currentLevel] += 1;
        this.siblingIterator = this.currentNode.getChildren().iterator();
        this.currentNode = Iterators.get(this.siblingIterator, currentIndex());
        
        return this.currentNode.getData();
    }
    
    /**
     * Returns true if the current node has a next sibling, false otherwise.
     * The root node always returns false.
     * @return true if next sibling exists, false otherwise
     */
    protected boolean hasNextSibling() {
        return siblingIterator.hasNext();
    }
    
    /**
     * Advances the current node to its next sibling
     * and returns the (new) current node.
     * May throw a {@link NoSuchElementException} if no next sibling exists.
     * The root node always throws this exception.
     * @return the next sibling
     * @throws NoSuchElementException if no next sibling exists
     */
    protected E nextSibling() {
        this.currentNode = siblingIterator.next();
        this.childIndexes[currentLevel] += 1;
        Arrays.fill(childIndexes, currentLevel + 1, childIndexes.length, -1);
        
        return this.currentNode.getData();
    }
    
}
