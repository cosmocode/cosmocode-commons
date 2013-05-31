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
import com.google.common.collect.Iterables;
import com.google.common.collect.Lists;
import de.cosmocode.collections.tree.iterator.LevelOrderIterator;
import de.cosmocode.collections.tree.iterator.PostOrderIterator;
import de.cosmocode.collections.tree.iterator.PreOrderIterator;

import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * <p> This is an abstract implementation of {@link Tree}.
 * It delegates most methods to getRoot().
 * </p>
 * <h5> The following methods are left without an implementation: </h5>
 * <ul>
 *   <li>{@link #getRoot()}</li>
 *   <li>{@link #setRootElement(TreeNode)}</li>
 * </ul>
 * 
 * @author Oliver Lorenz
 *
 * @param <E>
 */
public abstract class AbstractTree<E> implements Tree<E> {

    /**
     * Indicates that the given root element is null.
     * Can be used in a NullPointerException.
     * 
     * @deprecated should be replaced with {@link Preconditions#checkNotNull(Object, Object)}
     */
    @Deprecated
    public static final String ERR_ROOT_NULL = "root must not be null";
    
    private final Iterable<E> preOrderIterable = new Iterable<E>() {

        @Override
        public Iterator<E> iterator() {
            return new PreOrderIterator<E>(getRoot());
        }
        
        @Override
        public String toString() {
            return Iterables.toString(this);
        }
        
    };
    
    private final Iterable<E> postOrderIterable = new Iterable<E>() {

        @Override
        public Iterator<E> iterator() {
            return new PostOrderIterator<E>(getRoot());
        }
        
        @Override
        public String toString() {
            return Iterables.toString(this);
        }
        
    };
    
    private final Iterable<E> levelOrderIterable = new Iterable<E>() {
        
        @Override
        public Iterator<E> iterator() {
            return new LevelOrderIterator<E>(getRoot());
        }
        
        @Override
        public String toString() {
            return Iterables.toString(this);
        }
        
    };
    
    /**
     * Returns an {@code Iterable<T>} that returns a new {@link PreOrderIterator}
     * on each call to iterator().
     * @return a pre-ordered Iterable of this tree
     * @see TraverseMode#PRE_ORDER
     */
    protected Iterable<E> getPreOrderIterable() {
        return preOrderIterable;
    }
    
    /**
     * Returns an {@code Iterable<T>} that returns a new {@link PostOrderIterator}
     * on each call to iterator().
     * @return a post-ordered Iterable of this tree
     * @see TraverseMode#POST_ORDER
     */
    protected Iterable<E> getPostOrderIterable() {
        return postOrderIterable;
    }
    
    /**
     * Returns an {@code Iterable<T>} that returns a new {@link LevelOrderIterator}
     * on each call to iterator().
     * @return a level-ordered Iterable of this tree
     * @see TraverseMode#LEVEL_ORDER
     */
    protected Iterable<E> getLevelOrderIterable() {
        return levelOrderIterable;
    }
    

    @Override
    public TreeNode<E> addChild(E childData) {
        return getRoot().addChild(childData);
    }

    @Override
    public void addChildNode(TreeNode<E> child) {
        getRoot().addChildNode(child);
    }

    @Override
    public List<TreeNode<E>> addChildren(E... children) {
        return getRoot().addChildren(children);
    }

    @Override
    public boolean contains(TreeNode<E> descendant) {
        return getRoot().contains(descendant);
    }

    @Override
    public TreeNode<E> getChildAt(int index) throws IndexOutOfBoundsException {
        return getRoot().getChildAt(index);
    }

    @Override
    public Collection<TreeNode<E>> getChildren() {
        return getRoot().getChildren();
    }

    @Override
    public int getNumberOfChildren() {
        return getRoot().getNumberOfChildren();
    }

    @Override
    public void insertChildAt(int index, TreeNode<E> child) throws IndexOutOfBoundsException {
        getRoot().insertChildAt(index, child);
    }

    @Override
    public void removeChildNode(TreeNode<E> child) {
        getRoot().removeChildNode(child);
    }

    @Override
    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        getRoot().removeChildAt(index);
    }

    @Override
    public void setChildren(Collection<TreeNode<E>> children) {
        getRoot().setChildren(children);
    }
    
    @Override
    public List<TreeNode<E>> toPreOrderedList() {
        final List<TreeNode<E>> list = Lists.newLinkedList();
        walkInPreOrder(getRoot(), list);
        return list;
    }
    
    @Override
    public Iterable<E> traverse(final TraverseMode mode) {
        switch (mode) {
            case PRE_ORDER: {
                return getPreOrderIterable();
            }
            case POST_ORDER: {
                return getPostOrderIterable();
            }
            case IN_ORDER: {
                throw new UnsupportedOperationException("not yet implemented");
            }
            case LEVEL_ORDER: {
                return getLevelOrderIterable();
            }
            default: {
                throw new IllegalArgumentException("Unknown TraverseMode " + mode);
            }
        }
    }

    
    @Override
    public abstract TreeNode<E> getRoot();

    @Override
    public abstract void setRootElement(TreeNode<E> rootElement);
    
    
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getRoot() == null) ? 0 : getRoot().hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        return this == obj || obj != null && obj instanceof Tree<?> && this.equals((Tree<?>) obj);
    }
    
    @Override
    public boolean equals(final Tree<?> t) {
        // the null check is for convenience, so that equals never breaks
        if (getRoot() == null) {
            return t.getRoot() == null;
        } else {
            return getRoot().equals(t.getRoot());
        }
    }
    
    
    /**
     * Walks the Tree in pre-order style. This is a recursive method, and is
     * called from the toList() method with the root element as the first
     * argument. It appends to the second argument, which is passed by reference
     * as it recurses down the tree.
     * @param element the starting element.
     * @param list the output of the walk.
     */
    private void walkInPreOrder(TreeNode<E> element, List<TreeNode<E>> list) {
        list.add(element);
        for (TreeNode<E> data : element.getChildren()) {
            walkInPreOrder(data, list);
        }
    }

}
