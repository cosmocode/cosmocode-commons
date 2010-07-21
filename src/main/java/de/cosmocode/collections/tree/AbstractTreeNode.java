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
import java.beans.VetoableChangeListener;
import java.beans.VetoableChangeSupport;
import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import com.google.common.collect.Iterators;

/**
 * This is an abstract implementation of a TreeNode<T>.
 * Most methods are left abstract, but {@link #hashCode()}, {@link #equals(Object)}
 * and {@link #toString()} are implemented in a convenient way.
 * 
 * @author Oliver Lorenz
 *
 * @param <E> a generic type that indicates the data stored in this AbstractTreeNode
 */
public abstract class AbstractTreeNode<E> implements TreeNode<E> {

    public static final String ERR_ADD_ANCESTOR = "Cannot add ancestor or this Node as a child";
    
    private VetoableChangeSupport vetoableChangeSupport;

    /**
     * Fires a vetoable property change event to all registered listeners.
     * @param propertyName the name of the changed property
     * @param oldVal the old property value
     * @param newVal the new propety value
     * @throws PropertyVetoException if any listener rejects the change
     */
    protected void fireVetoableChange(final String propertyName, final Object oldVal, final Object newVal)
        throws PropertyVetoException {
        
        if (vetoableChangeSupport != null) {
            vetoableChangeSupport.fireVetoableChange(propertyName, oldVal, newVal);
        }
    }
    
    /**
     * Returns the vetoable change support for this TreeNode.
     * @return the vetoable change support for this TreeNode
     */
    protected VetoableChangeSupport getVetoableChangeSupport() {
        if (vetoableChangeSupport == null) {
            this.vetoableChangeSupport = new VetoableChangeSupport(this);
        }
        return vetoableChangeSupport;
    }
    
    /**
     * <p> Tests the given TreeNode on its ability to become a child of this TreeNode.
     * Throws RuntimeExceptions if the given TreeNode is not an acceptable child.
     * Returns true if the given TreeNode must be added.
     * Returns false if the given TreeNode must not be added, because it was already added.
     * </p>
     * 
     * @param child the TreeNode that should be added as a child
     * @return true if the child can be added, false otherwise
     * @throws NullPointerException if the given argument `child` is null
     * @throws IllegalArgumentException if the given argument `child` is an ancestor of this node
     */
    protected boolean checkChildNode(final TreeNode<E> child) {
        if (child == null) throw new NullPointerException("non-null parameter expected");

        // if the given argument is already our child then we have nothing to do
        if (child.getParent() == this) return false;
        
        if (child == this || child.contains(this)) {
            throw new IllegalArgumentException(ERR_ADD_ANCESTOR);
        }
        
        return true;
    }
    
    /**
     * <p> Sets this TreeNode as the new parent of the given node.
     * Any previously set parent on the given TreeNode `child` is unset,
     * and the child removed from the previous parent.
     * </p>
     * 
     * @param child the child to change the parent on
     */
    protected void setThisAsParentOf(final TreeNode<E> child) {
        // change parent
        if (child.getParent() != null) {
            child.getParent().removeChildNode(child);
        }
        child.setParent(this);
    }
    
    /**
     * <p> Adds the child node internally.
     * The default implementation throws an {@link UnsupportedOperationException}.
     * </p>
     * 
     * @param child the child to add internally
     * @see #addChildNode(TreeNode)
     */
    protected void addChildNodeInternal(final TreeNode<E> child) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void addChildNode(final TreeNode<E> child) {
        if (checkChildNode(child)) {
            setThisAsParentOf(child);
            addChildNodeInternal(child);
        }
    }
    
    @Override
    public List<TreeNode<E>> addChildren(E... newChildren) {
        if (newChildren == null) throw new NullPointerException("addChildren expects a non-null parameter");
        
        final List<TreeNode<E>> childNodes = new LinkedList<TreeNode<E>>();
        for (E child : newChildren) {
            childNodes.add(this.addChild(child));
        }
        return childNodes;
    };
    
    @Override
    public boolean contains(final TreeNode<E> descendant) {
        if (descendant == null) {
            return false;
        } else if (descendant.getParent() == this) {
            // this node is the parent of the descendant
            return true;
        } else {
            // recurse further
            return this.contains(descendant.getParent());
        }
    }
    
    @Override
    public TreeNode<E> getChildAt(int index) throws IndexOutOfBoundsException {
        if (index < 0) {
            throw new IndexOutOfBoundsException("index < 0");
        } else if (index >= getNumberOfChildren()) {
            throw new IndexOutOfBoundsException("index >= number of children (" + getNumberOfChildren() + ")");
        } else {
            // we don't have to check on children (with initChildren()),
            // because index >= getNumberOfChildren() would be index >= 0 if children is null,
            // and with this every possible value of index throws an IndexOutOfBoundsException
            final Iterator<TreeNode<E>> iter = getChildren().iterator();
            return Iterators.get(iter, index);
        }
    }

    @Override
    public int getNumberOfChildren() {
        return getChildren().size();
    }
    
    @Override
    public TreeNode<E> getRoot() {
        final TreeNode<E> parent = getParent();
        if (parent == null) {
            return this;
        } else {
            return parent.getRoot();
        }
    }
    
    @Override
    public boolean hasChild(final E childData) {
        for (final TreeNode<E> child : getChildren()) {
            if (childData == null) {
                if (child.getData() == null) return true;
            } else if (childData.equals(child.getData())) {
                return true;
            }
        }
        
        // we found nothing
        return false;
    };
    
    @Override
    public boolean isLeaf() {
        return getParent() != null && getNumberOfChildren() == 0;
    }
    
    @Override
    public boolean changeParent(TreeNode<E> newParent) {
        final TreeNode<E> oldParent = getParent();
        
        // check if we have anything to do
        if (newParent == oldParent) return false;
        
        // remove from old parent
        if (oldParent != null) {
            oldParent.removeChildNode(this);
        }
        
        // add to new parent
        if (newParent != null) {
            newParent.addChildNode(this);
        }
        
        return true;
    }
    
    @Override
    public void setData(E data) throws PropertyVetoException {
        // check all listeners on this node if they approve the new data
        final E oldData = getData();
        fireVetoableChange(DATA_PROPERTY, oldData, data);
        
        // new data approved: do the update
        setDataUnchecked(data);
    };
    
    /**
     * <p> This method sets the data unchecked. It is called by {@link #setData(Object)}.
     * No exceptions should be thrown on this method, but by setData(T) instead.
     * </p>
     * <p> <i>Implementation advice</i>: If a subclass of this AbstractTreeNode does not support setData(T),
     * then it should directly override setData() and throw {@link UnsupportedOperationException}s
     * on both methods (this method and setData(T).
     * </p>
     * <p> The default implementation throws an UnsupportedOperationException.
     * </p>
     * 
     * @param newData the data to set
     * @throws UnsupportedOperationException if setting the data is disallowed
     */
    protected void setDataUnchecked(E newData) {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void addVetoableChangeListener(final VetoableChangeListener listener) {
        getVetoableChangeSupport().addVetoableChangeListener(listener);
    }
    
    @Override
    public void removeVetoableChangeListener(final VetoableChangeListener listener) {
        if (vetoableChangeSupport == null) return;
        
        vetoableChangeSupport.removeVetoableChangeListener(listener);
    }
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        final E data = getData();
        result = prime * result + ((data == null) ? 0 : data.hashCode());
        result = prime * result + hashCodeChildren();
        return result;
    }
    
    /**
     * Returns the hashCode for the children.
     * @return the hashCode for the children of this TreeNode.
     */
    protected int hashCodeChildren() {
        if (getNumberOfChildren() == 0) return 1;
        
        final Collection<TreeNode<E>> children = getChildren();
        final Iterator<TreeNode<E>> i = children.iterator();
        final int prime = 31;
        int result = 1;
        while (i.hasNext()) {
            final TreeNode<E> obj = i.next();
            result = prime * result + (obj == null ? 0 : obj.hashCode());
        }
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (!(obj instanceof TreeNode<?>))
            return false;
        final TreeNode<?> other = (TreeNode<?>) obj;
        final E data = getData();
        if (data == null) {
            if (other.getData() != null)
                return false;
        } else if (!data.equals(other.getData()))
            return false;
        if (!this.equalsChildren(other))
            return false;
        return true;
    }
    
    /**
     * <p> Determines whether the children of this TreeNode are equal to the children of another TreeNode.
     * Returns true if the children are equal, false otherwise. This method is called by equals.
     * </p>
     * 
     * <p> If sub-classes rely on the equals method provided by the AbstractNode
     * then they are strongly advised to override this method, because this method runs in O(n).
     * </p>
     * 
     * @param other the other TreeNode to compare with
     * @return true if the children of this TreeNode are equal to the children of the other TreeNode, false otherwise
     */
    protected boolean equalsChildren(final TreeNode<?> other) {
        if (other == null) return false;
        
        if (other.getNumberOfChildren() != this.getNumberOfChildren()) {
            return false;
        } else if (this.getChildren() == null) {
            return other.getChildren() == null;
        } else {
            final Iterator<?> otherChildIter = other.getChildren().iterator();
            for (TreeNode<E> child : this.getChildren()) {
                final Object otherChild = otherChildIter.next();
                if (child == null) {
                    if (otherChild != null) {
                        return false;
                    }
                } else if (!child.equals(otherChild)) {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("{");
        if (getData() != null) {
            sb.append(getData().toString()).append(",");
        }
        sb.append("[");
        int i = 0;
        for (TreeNode<E> e : getChildren()) {
            if (e == null) continue;
            
            if (i > 0) {
                sb.append(",");
            }
            sb.append(e.toString());
            i++;
        }
        sb.append("]").append("}");
        return sb.toString();
    }

}
