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

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import com.google.common.collect.ImmutableList;
import com.google.common.collect.Lists;


/**
 * <p>
 * Represents a node of the Tree<T> class. The TreeNode<T> is also a container, and
 * can be thought of as instrumentation to determine the location of the type T
 * in the Tree<T>.
 * </p>
 * <p>
 * A DuplicatesNode always allows a child to be added, regardless of the data stored in it.
 * It is backed by a list so that the order in which children are added matters is preserved.
 * </p>
 * 
 * <br> Prototype taken from: http://sujitpal.blogspot.com/2006/05/java-data-structure-generic-tree.html
 * 
 * @param <E> a generic type that indicates the data stored in this ListNode
 * 
 * @author Oliver Lorenz
 * @author Willi Schoenborn
 */
public class DuplicatesNode<E> extends AbstractTreeNode<E> implements Serializable {
    
    private static final long serialVersionUID = 3205686938101651970L;
     
    private E data;
    private List<TreeNode<E>> children;
    private TreeNode<E> parent;
 
    /**
     * Default constructor.
     */
    public DuplicatesNode() {
        super();
        this.children = Lists.newArrayList();
    }
 
    /**
     * Convenience constructor to create a Node<T> with an instance of T.
     * @param data an instance of T.
     */
    public DuplicatesNode(final E data) {
        this();
        this.data = data;
    }
    
    
    /**
     * <p> Convenience constructor to directly set the parent of this TreeNode.
     * </p>
     * <p> <strong>Attention:</strong> 
     * This constructor only stores a reference to the parent and does not alter it.
     * It should only be used in a context where the parent is altered outside of the constructor.
     * </p>
     * 
     * @param parent the parent of this node
     */
    protected DuplicatesNode(final TreeNode<E> parent) {
        this();
        this.parent = parent;
    }
    
    /**
     * <p> Convenience constructor to create a ListNode<T> as a child of parent with an instance of T.
     * </p>
     * <p> <strong>Attention:</strong> 
     * This constructor only stores a reference to the parent and does not alter it.
     * It should only be used in a context where the parent is altered outside of the constructor.
     * </p>
     * 
     * @param parent the parent for this ListNode. Can be null.
     * @param data an instance of T
     */
    protected DuplicatesNode(final TreeNode<E> parent, final E data) {
        this();
        this.parent = parent;
        this.data = data;
    }
    
    
    @Override
    public TreeNode<E> getParent() {
        return parent;
    }
    
    @Override
    public void setParent(final TreeNode<E> parent) {
        this.parent = parent;
    }
    
    @Override
    public List<TreeNode<E>> getChildren() {
        return ImmutableList.copyOf(this.children);
    }

    @Override
    public void setChildren(final Collection<TreeNode<E>> children) {
        if (children == null) throw new NullPointerException("setChildren expects a non-null parameter");
        
        for (TreeNode<E> child : this.children) {
            if (child != null) child.setParent(null);
        }
        this.children.clear();
        for (TreeNode<E> child : children) {
            this.addChildNode(child);
        }
    }
 
    @Override
    public int getNumberOfChildren() {
        return children.size();
    }
    
    @Override
    protected void addChildNodeInternal(final TreeNode<E> child) {
        children.add(child);
    }
    
    @Override
    public DuplicatesNode<E> addChild(final E childData) {
        final DuplicatesNode<E> newNode = new DuplicatesNode<E>(this, childData);
        children.add(newNode);
        return newNode;
    }
    
    @Override
    public void insertChildAt(int index, TreeNode<E> child) throws IndexOutOfBoundsException {
        if (!checkChildNode(child)) return;
        
        if (index == getNumberOfChildren()) {
            // this is really an append
            addChildNode(child);
        } else {
            setThisAsParentOf(child);
            
            // add child internal
            children.add(index, child);
        }
    }
    
    @Override
    public TreeNode<E> getChildAt(int index) throws IndexOutOfBoundsException {
        return children.get(index);
    }
    
    @Override
    public void removeChildNode(TreeNode<E> child) {
        if (child == null) throw new NullPointerException("removeChild expects a non-null parameter");
        
        final Iterator<TreeNode<E>> childIter = children.iterator();
        while (childIter.hasNext()) {
            if (childIter.next() == child) {
                childIter.remove();
                child.setParent(null);
                break;
            }
        }
    }
     
    @Override
    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        final TreeNode<E> child = children.remove(index);
        if (child != null) child.setParent(null);
    }
    
    @Override
    public void removeAllChildren() {
        final Iterator<TreeNode<E>> childIter = this.children.iterator();
        while (childIter.hasNext()) {
            final TreeNode<E> child = childIter.next();
            if (child != null) {
                child.setParent(null);
            }
            childIter.remove();
        }
    }

    @Override
    public E getData() {
        return this.data;
    }

    @Override
    public void setDataUnchecked(E newData) {
        this.data = newData;
    }
    
    @Override
    protected boolean equalsChildren(TreeNode<?> other) {
        if (other == null) return false;
        
        if (getNumberOfChildren() != other.getNumberOfChildren()) {
            return false;
        } else {
            final Collection<?> otherChildren = other.getChildren();
            if (otherChildren instanceof List<?>) {
                return this.getChildren().equals(other.getChildren());
            } else {
                return super.equalsChildren(other);
            }
        }
    }

}
