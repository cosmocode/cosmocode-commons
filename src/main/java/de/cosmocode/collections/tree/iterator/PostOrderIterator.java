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

package de.cosmocode.collections.tree.iterator;

import java.util.NoSuchElementException;

import de.cosmocode.collections.tree.TraverseMode;
import de.cosmocode.collections.tree.TreeNode;

/**
 * An iterator that returns the given root node's elements
 * in post order ({@link TraverseMode#POST_ORDER}).
 * 
 * @author Oliver Lorenz
 *
 * @param <E> the type of the elements returned
 */
public final class PostOrderIterator<E> extends AbstractTreeIterator<E> {
    
    public PostOrderIterator(final TreeNode<E> root) {
        super(root);
    }

    @Override
    public boolean hasNext() {
        return rootNotVisited();
    }

    @Override
    public E next() {
        if (rootVisited()) {
            throw new NoSuchElementException();
        } else if (isRoot() && !hasChildren()) {
            return currentData();
        } else if (hasNextChild()) {
            // go down to deepest child
            while (hasNextChild()) {
                nextChild();
            }
            return currentData();
        } else if (hasNextSibling()) {
            // can't go further down: go right
            nextSibling();
            // go down to deepest child
            while (hasChildren()) {
                firstChild();
            }
            return currentData();
        } else {
            // can't go further right: go up again
            return parent();
        }
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
}
