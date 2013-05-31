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

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import de.cosmocode.patterns.Builder;

import javax.annotation.concurrent.Immutable;
import javax.annotation.concurrent.ThreadSafe;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.Collection;
import java.util.List;

/**
 * An immmutable view on a {@link TreeNode}. It throws an
 * UnsupportedOperationException on every add-, insert-, set- and remove-method.
 * 
 * @author olorenz
 * @param <E>
 */
@Immutable
@ThreadSafe
public final class ImmutableNode<E> implements TreeNode<E> {

    private final E data;

    private final ImmutableList<TreeNode<E>> children;

    private final TreeNode<E> parent;

    public ImmutableNode(final TreeNode<E> parent, final E data,
            final Iterable<TreeNode<E>> children) {
        final ImmutableList.Builder<TreeNode<E>> childBuilder = ImmutableList.builder();
        if (children != null) {
            for (TreeNode<E> child : children) {
                if (child == null) continue;
                childBuilder.add(child);
            }
        }
        this.children = childBuilder.build();
        this.parent = parent;
        this.data = data;
    }

    public ImmutableNode(final TreeNode<E> parent, final E data,
            final ImmutableList<TreeNode<E>> children) {
        this.parent = parent;
        this.data = data;
        this.children = Preconditions.checkNotNull(children, "Children");
    }

    @Override
    public boolean contains(TreeNode<E> descendant) {
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
        return children.get(index);
    }

    @Override
    public ImmutableList<TreeNode<E>> getChildren() {
        return children;
    }

    @Override
    public int getNumberOfChildren() {
        return children.size();
    }

    @Override
    public E getData() {
        return data;
    }

    @Override
    public TreeNode<E> getParent() {
        return parent;
    }

    @Override
    public TreeNode<E> getRoot() {
        if (parent == null) {
            return this;
        } else {
            return parent.getRoot();
        }
    }

    @Override
    public boolean hasChild(final E childData) {
        for (final TreeNode<E> child : children) {
            if (childData == null) {
                if (child.getData() == null) {
                    return true;
                }
            } else if (childData.equals(child.getData())) {
                return true;
            }
        }

        // we found nothing
        return false;
    }

    @Override
    public boolean isLeaf() {
        return children.size() > 0;
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong> This method is not allowed by ImmutableNode. </strong>
     * </p>
     * 
     * @throws UnsupportedOperationException always
     */
    @Override
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        throw new UnsupportedOperationException("not allowed by ImmutableNode");
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong> This method is not allowed by ImmutableNode. </strong>
     * </p>
     * 
     * @throws UnsupportedOperationException always
     */
    @Override
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        throw new UnsupportedOperationException("not allowed by ImmutableNode");
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong> This method is not allowed by ImmutableNode. </strong>
     * </p>
     * 
     * @throws UnsupportedOperationException always
     */
    @Override
    public void removeAllChildren() {
        throw new UnsupportedOperationException("not allowed by ImmutableNode");
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong> This method is not allowed by ImmutableNode. </strong>
     * </p>
     * 
     * @throws UnsupportedOperationException always
     */
    @Override
    public void setParent(TreeNode<E> parent) {
        throw new UnsupportedOperationException("not allowed by ImmutableNode");
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong> This method is not allowed by ImmutableNode. </strong>
     * </p>
     * 
     * @return never returns
     * @throws UnsupportedOperationException always
     */
    @Override
    public boolean changeParent(TreeNode<E> newParent) {
        throw new UnsupportedOperationException("not allowed by ImmutableNode");
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong> This method is not allowed by ImmutableNode. </strong>
     * </p>
     * 
     * @throws UnsupportedOperationException always
     */
    @Override
    public void addChildNode(TreeNode<E> child) {
        throw new UnsupportedOperationException("not allowed by ImmutableNode");
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong> This method is not allowed by ImmutableNode. </strong>
     * </p>
     * 
     * @return never returns
     * @throws UnsupportedOperationException always
     */
    @Override
    public TreeNode<E> addChild(E childData) {
        throw new UnsupportedOperationException("not allowed by ImmutableNode");
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong> This method is not allowed by ImmutableNode. </strong>
     * </p>
     * 
     * @throws UnsupportedOperationException always
     */
    @Override
    public void insertChildAt(int index, TreeNode<E> child) throws IndexOutOfBoundsException {
        throw new UnsupportedOperationException("not allowed by ImmutableNode");
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong> This method is not allowed by ImmutableNode. </strong>
     * </p>
     * 
     * @throws UnsupportedOperationException always
     */
    @Override
    public void removeChildNode(TreeNode<E> child) {
        throw new UnsupportedOperationException("not allowed by ImmutableNode");
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong> This method is not allowed by ImmutableNode. </strong>
     * </p>
     * 
     * @throws UnsupportedOperationException always
     */
    @Override
    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        throw new UnsupportedOperationException("not allowed by ImmutableNode");
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong> This method is not allowed by ImmutableNode. </strong>
     * </p>
     * 
     * @throws UnsupportedOperationException always
     */
    @Override
    public void setChildren(Collection<TreeNode<E>> children) {
        throw new UnsupportedOperationException("not allowed by ImmutableNode");
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong> This method is not allowed by ImmutableNode. </strong>
     * </p>
     * 
     * @return never returns
     * @throws UnsupportedOperationException always
     */
    @Override
    public List<TreeNode<E>> addChildren(E... newChildren) {
        throw new UnsupportedOperationException("not allowed by ImmutableNode");
    }

    /**
     * {@inheritDoc}
     * <p>
     * <strong> This method is not allowed by ImmutableNode. </strong>
     * </p>
     * 
     * @throws UnsupportedOperationException always
     */
    @Override
    public void setData(E data) {
        throw new UnsupportedOperationException("not allowed by ImmutableNode");
    }

    /**
     * <p>
     * Returns a new Builder that can be used to create a new ImmutableNode.
     * </p>
     * 
     * @param <E> the generic type of the new Builder
     * @return a new {@link ImmutableBuilder}
     */
    public static <E> ImmutableBuilder<E> builder() {
        return new ImmutableBuilder<E>();
    }

    /**
     * <p> This is a Builder for {@link ImmutableNode}s.
     * It can be reused, so build() can be called multiple times.
     * </p>
     * <p> This builder is not threadsafe, but the built ImmutableNode is threadsafe.
     * </p>
     *  
     * @author Oliver Lorenz
     *
     * @param <E> the generic type of the ImmutableNode to create
     */
    public static final class ImmutableBuilder<E> extends ForwardingTreeNode<E>
            implements Builder<ImmutableNode<E>> {

        private final TreeNode<E> delegated;

        public ImmutableBuilder() {
            this.delegated = new DuplicatesNode<E>();
        }

        /**
         * Copies the data, parent and children from a given TreeNode. The
         * children are not altered and their ordering is preserved.
         * 
         * @param toCopy
         *            the TreeNode to copy
         * @return this
         */
        public ImmutableBuilder<E> copy(final TreeNode<E> toCopy) {
            setData(toCopy.getData());
            setParent(toCopy.getParent());
            setChildren(toCopy.getChildren());
            return this;
        }

        /**
         * Copies the data, parent and children from a given TreeNode. The
         * children are copied deep, that means that every child of the given
         * TreeNode is converted to an ImmutableNode. This means that when this
         * ImmutableBuilder gets {@link #build()} the whole sub-tree is
         * immutable.
         * 
         * @param toCopy
         *            the TreeNode to copy
         * @return this
         */
        public ImmutableBuilder<E> deepCopy(final TreeNode<E> toCopy) {
            setData(toCopy.getData());
            setParent(toCopy.getParent());

            for (TreeNode<E> child : toCopy.getChildren()) {
                if (child == null)
                    continue;

                final ImmutableBuilder<E> childBuilder = new ImmutableBuilder<E>();
                childBuilder.deepCopy(child);
                this.addChildNode(childBuilder);
            }
            return this;
        }

        @Override
        public ImmutableBuilder<E> addChild(E childData) {
            final ImmutableBuilder<E> childBuilder = new ImmutableBuilder<E>();
            childBuilder.setData(childData);
            this.addChildNode(childBuilder);
            return childBuilder;
        }

        @Override
        public void addChildNode(TreeNode<E> child) {
            if (child instanceof ImmutableNode<?>) {
                final ImmutableBuilder<E> childBuilder = new ImmutableBuilder<E>();
                childBuilder.copy(child);
                this.addChildNode(childBuilder);
            } else {
                super.addChildNode(child);
            }
        }

        @Override
        public void setData(E data) {
            try {
                delegated.setData(data);
            } catch (PropertyVetoException e) {
                throw new IllegalStateException("Got an illegal PropertyVetoException", e);
            }
        }

        @Override
        public void addVetoableChangeListener(VetoableChangeListener listener) {
            throw new UnsupportedOperationException();
        }

        @Override
        public void removeVetoableChangeListener(VetoableChangeListener listener) {
            throw new UnsupportedOperationException();
        }

        @Override
        protected TreeNode<E> delegate() {
            return delegated;
        }

        @Override
        public ImmutableNode<E> build() {
            final OneTimeDelegate<E> forwardedResult = new OneTimeDelegate<E>();
            final ImmutableList.Builder<TreeNode<E>> children = ImmutableList.builder();
            for (final TreeNode<E> child : getChildren()) {
                if (child instanceof ImmutableBuilder<?>) {
                    final ImmutableBuilder<E> childBuilder = (ImmutableBuilder<E>) child;
                    childBuilder.setParent(forwardedResult);
                    children.add(childBuilder.build());
                } else {
                    child.setParent(forwardedResult);
                    children.add(child);
                }
            }
            final ImmutableNode<E> built = new ImmutableNode<E>(getParent(), getData(), children.build());
            forwardedResult.setDelegate(built);
            return built;
        }

    }

    /**
     * <p>
     * A OneTimeDelegate is a ForwardingTreeNode that is lazy initialized.
     * </p>
     * <p>
     * On creation every method except
     * {@link OneTimeDelegate#setDelegate(TreeNode)} throws an
     * IllegalStateException.
     * </p>
     * <p>
     * Once setDelegate(TreeNode) is called with a non-null value all methods
     * work, but setDelegate(TreeNode) throws an IllegalStateException. This
     * means it can be initialized once, but only once.
     * </p>
     * 
     * @author Oliver Lorenz
     * 
     * @param <E> 
     */
    public static final class OneTimeDelegate<E> extends ForwardingTreeNode<E> {

        private TreeNode<E> delegated;

        /**
         * <p>
         * Sets the delegated TreeNode. This method exits normally on the first
         * invocation and throws IllegalStateExceptions on every following
         * invocation.
         * 
         * @param newDelegate the delegate to forward every call to
         * @throws NullPointerException if newDelegate is null
         * @throws IllegalStateException if this method has already been called before
         */
        public void setDelegate(final TreeNode<E> newDelegate) {
            if (newDelegate == null) {
                throw new NullPointerException();
            } else if (this.delegated == null) {
                this.delegated = newDelegate;
            } else {
                throw new IllegalStateException("Delegate is already set");
            }
        }

        @Override
        protected TreeNode<E> delegate() {
            if (this.delegated == null) {
                throw new IllegalStateException("Delegate not yet set");
            } else {
                return delegated;
            }
        }

    }

}
