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

import com.google.common.base.Preconditions;

/**
 * Represents a Tree of Objects of generic type E. The Tree is represented as
 * a single rootElement of type {@code TreeNode<E>}.
 * It forwards every call to the extended {@link AbstractTree}.
 * 
 * <br> Taken from: http://sujitpal.blogspot.com/2006/05/java-data-structure-generic-tree.html
 * 
 * @param <E> a generic type that indicates the data stored in the {@link TreeNode}s
 * 
 * @author Oliver Lorenz
 */
public final class DefaultTree<E> extends AbstractTree<E> implements Serializable {
    
    public static final String ERR_DELEGATED_NULL = "delegated must not be null";
    
    public static final String ERR_NOT_ROOT = "given TreeNode is not a root node";
    
    private static final long serialVersionUID = -5207310430838111045L;
    
    private TreeRoot<E> rootElement;
    
    /**
     * Default constructor.
     */
    public DefaultTree() {
        super();
        this.rootElement = new TreeRoot<E>();
    }
    
    public DefaultTree(TreeNode<E> rootElement) {
        Preconditions.checkNotNull(rootElement, "RootElement");
        this.rootElement = new TreeRoot<E>(rootElement);
    }

    
    @Override
    public TreeNode<E> getRoot() {
        return this.rootElement;
    }
    
    @Override
    public void setRootElement(TreeNode<E> rootElement) {
        Preconditions.checkNotNull(rootElement, "RootElement");
        this.rootElement = new TreeRoot<E>(rootElement);
    }

    /**
     * Returns a String representation of the Tree. The elements are generated
     * from a pre-order traversal of the Tree.
     * @return the String representation of the Tree.
     */
    @Override
    public String toString() {
        return getRoot().toString();
    }
    
    
    /**
     * The root element of the DefaultTree. It is a wrapper around the core TreeNode<T> functions
     * and delegates them to the given TreeNode<T>.
     * <br> The TreeRoot<T> disallows the usage of: setParent and changeParent
     * 
     * @author Oliver Lorenz
     *
     * @param <E>
     */
    private static final class TreeRoot<E> extends ForwardingTreeNode<E> implements Serializable {
        
        private static final long serialVersionUID = 8553069950829726723L;
        
        private final TreeNode<E> delegate;
        
        public TreeRoot() {
            this.delegate = new DuplicatesNode<E>();
        }
        
        public TreeRoot(final TreeNode<E> delegated) {
            if (delegated == null) throw new NullPointerException(ERR_DELEGATED_NULL);
            if (delegated.getParent() != null) throw new IllegalArgumentException(ERR_NOT_ROOT);
            
            this.delegate = delegated;
        }
        
        @Override
        protected TreeNode<E> delegate() {
            return delegate;
        }
        
        @Override
        public void setParent(TreeNode<E> parent) {
            throw new UnsupportedOperationException("setParent not supported by ForwardingTreeRoot");
        }
        
        @Override
        public boolean changeParent(TreeNode<E> newParent) {
            throw new UnsupportedOperationException("changeParent not supported by ForwardingTreeRoot");
        }
        
        @Override
        public TreeNode<E> getRoot() {
            return this;
        }
        
        @Override
        public TreeNode<E> getParent() {
            return null;
        }
        
        @Override
        public TreeNode<E> addChild(E childData) {
            final TreeNode<E> newChild = delegate.addChild(childData);
            newChild.setParent(this);
            return newChild;
        }
        
        @Override
        public void addChildNode(final TreeNode<E> child) {
            if (child == null) throw new NullPointerException("addChild expects a non-null value");
            
            // test if we have anything to do
            if (child.getParent() == this) return;
            
            delegate.addChildNode(child);
            child.setParent(this);
        }
        
        @Override
        public void insertChildAt(int index, TreeNode<E> child) throws IndexOutOfBoundsException {
            delegate.insertChildAt(index, child);
            child.setParent(this);
        }
        
        @Override
        public boolean contains(TreeNode<E> descendant) {
            return descendant != null && (descendant.getParent() == this || this.contains(descendant.getParent()));
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

    }

}
