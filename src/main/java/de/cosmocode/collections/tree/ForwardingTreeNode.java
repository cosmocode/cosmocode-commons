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
import java.util.Collection;
import java.util.List;

import com.google.common.collect.ForwardingObject;

/**
 * <p>
 * Implementation of {@link ForwardingObject} of the {@link TreeNode}.
 * It delegates every TreeNode-method to the forwarded TreeNode.
 * </p>
 * <p>
 * {@link #delegate()} is abstract, so the implementation must handle the wrapped element.
 * </p>
 * 
 * @author olorenz
 *
 * @param <E> a generic type that indicates the data stored in the forwarded TreeNode
 */
public abstract class ForwardingTreeNode<E> extends ForwardingObject implements TreeNode<E> {

    @Override
    public TreeNode<E> addChild(E childData) {
        return delegate().addChild(childData);
    }

    @Override
    public void addChildNode(TreeNode<E> child) {
        delegate().addChildNode(child);
    }
    
    @Override
    public boolean hasChild(E childData) {
        return delegate().hasChild(childData);
    };

    @Override
    public List<TreeNode<E>> addChildren(E... children) {
        return delegate().addChildren(children);
    }

    @Override
    public TreeNode<E> getChildAt(int index) throws IndexOutOfBoundsException {
        return delegate().getChildAt(index);
    }

    @Override
    public Collection<TreeNode<E>> getChildren() {
        return delegate().getChildren();
    }

    @Override
    public E getData() {
        return delegate().getData();
    }

    @Override
    public int getNumberOfChildren() {
        return delegate().getNumberOfChildren();
    }
    
    @Override
    public TreeNode<E> getRoot() {
        return delegate().getRoot();
    }

    @Override
    public TreeNode<E> getParent() {
        return delegate().getParent();
    }

    @Override
    public void insertChildAt(int index, TreeNode<E> child) throws IndexOutOfBoundsException {
        delegate().insertChildAt(index, child);
    }

    @Override
    public boolean contains(TreeNode<E> descendant) {
        return delegate().contains(descendant);
    }

    @Override
    public boolean isLeaf() {
        return delegate().isLeaf();
    }

    @Override
    public void removeChildNode(TreeNode<E> child) {
        delegate().removeChildNode(child);
    }

    @Override
    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        delegate().removeChildAt(index);
    }
    
    @Override
    public void removeAllChildren() {
        delegate().removeAllChildren();
    }

    @Override
    public void setChildren(Collection<TreeNode<E>> children) {
        delegate().setChildren(children);
    }

    @Override
    public void setData(E data) throws PropertyVetoException {
        delegate().setData(data);
    }

    @Override
    public void setParent(TreeNode<E> parent) {
        delegate().setParent(parent);
    }
    
    @Override
    public boolean changeParent(TreeNode<E> newParent) {
        return delegate().changeParent(newParent);
    }
    
    @Override
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        delegate().addVetoableChangeListener(listener);
    }
    
    @Override
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        delegate().removeVetoableChangeListener(listener);
    }
    
    @Override
    public int hashCode() {
        return delegate().hashCode();
    }
    
    @Override
    public boolean equals(Object obj) {
        return delegate().equals(obj);
    }
    
    @Override
    public String toString() {
        return delegate().toString();
    }

    @Override
    protected abstract TreeNode<E> delegate();

}
