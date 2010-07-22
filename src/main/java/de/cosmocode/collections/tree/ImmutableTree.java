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

import com.google.common.base.Preconditions;


/**
 * An immutable view on a {@link Tree}.
 * It throws an {@link UnsupportedOperationException} on every add-, insert-, set- and remove-method.
 * 
 * @author Oliver Lorenz
 * @param <E> a generic type that indicates the data stored in the {@link ImmutableNode}s
 */
public class ImmutableTree<E> extends AbstractTree<E> implements Tree<E> {
    
    private final ImmutableNode<E> rootNode;
    
    /**
     * Creates a new ImmutableTree of a TreeNode.
     * All add-, insert-, set- and remove-methods
     * throw an {@link UnsupportedOperationException}.
     * @param rootNode
     */
    public ImmutableTree(final TreeNode<E> rootNode) {
        super();
        
        Preconditions.checkNotNull(rootNode, "RootNode");

        final ImmutableNode.ImmutableBuilder<E> builder = ImmutableNode.builder();
        this.rootNode = builder.deepCopy(rootNode).build();
    }
    
    /**
     * Creates a new ImmutableTree of a Tree<T>.
     * All add-, insert-, set- and remove-methods
     * throw an {@link UnsupportedOperationException}.
     * @param tree
     */
    public ImmutableTree(final Tree<E> tree) {
        this(tree.getRoot());
    }
    
    
    @Override
    public ImmutableNode<E> getRoot() {
        return rootNode;
    }
    
    /**
     * {@inheritDoc}
     * <p> This method is not allowed by ImmutableTree.</p>
     */
    @Override
    public void setRootElement(TreeNode<E> rootElement) {
        throw new UnsupportedOperationException("setRootElement not allowed by ImmutableTree");
    }

}
