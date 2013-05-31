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

import com.google.common.collect.ForwardingObject;

import java.util.Collection;
import java.util.List;


/**
 * <p>
 * Implementation of {@link ForwardingObject} of {@link Tree}.
 * It delegates every Tree-method to the forwarded Tree.
 * </p>
 * <p>
 * {@link #delegate()} is abstract, so the implementation must handle the wrapped element.
 * </p>
 * 
 * @author Oliver Lorenz
 *
 * @param <E> a generic type that indicates the data stored in the {@link TreeNode}s
 */
public abstract class ForwardingTree<E> extends ForwardingObject implements Tree<E> {

    @Override
    public TreeNode<E> addChild(E childData) {
        return delegate().addChild(childData);
    }

    @Override
    public void addChildNode(TreeNode<E> child) {
        delegate().addChildNode(child);
    }

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
    public int getNumberOfChildren() {
        return delegate().getNumberOfChildren();
    }

    @Override
    public TreeNode<E> getRoot() {
        return delegate().getRoot();
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
    public void removeChildNode(TreeNode<E> child) {
        delegate().removeChildNode(child);
    }

    @Override
    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        delegate().removeChildAt(index);
    }

    @Override
    public void setChildren(Collection<TreeNode<E>> children) {
        delegate().setChildren(children);
    }

    @Override
    public void setRootElement(TreeNode<E> rootElement) {
        delegate().setRootElement(rootElement);
    }

    /**
     * {@inheritDoc}
     * 
     * @deprecated use {@link #traverse(TraverseMode)}
     */
    @Deprecated
    @Override
    public List<TreeNode<E>> toPreOrderedList() {
        return delegate().toPreOrderedList();
    }
    
    @Override
    public Iterable<E> traverse(TraverseMode mode) {
        return delegate().traverse(mode);
    }
    

    @Override
    protected abstract Tree<E> delegate();

}
