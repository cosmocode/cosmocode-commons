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

import de.cosmocode.collections.tree.TraverseMode;
import de.cosmocode.collections.tree.TreeNode;

import java.util.NoSuchElementException;

/**
 * An iterator that returns the given root node's elements
 * in pre order ({@link TraverseMode#PRE_ORDER}).
 * 
 * @author Oliver Lorenz
 *
 * @param <E> the type of the elements returned
 */
public final class PreOrderIterator<E> extends AbstractTreeIterator<E> {
    
    private boolean finished;
    
    public PreOrderIterator(final TreeNode<E> root) {
        super(root);
        finished = false;
    }

    @Override
    public boolean hasNext() {
        return !finished;
    }

    @Override
    public E next() {
        if (finished) {
            throw new NoSuchElementException();
        }
        
        final E data = currentData();
        
        if (hasNextChild()) {
            // go down
            nextChild();
        } else if (hasNextSibling()) {
            // can't go down: go right
            nextSibling();
        } else {
            // can't go right: go up again, until we can go right
            while (!hasNextSibling() && !isRoot()) {
                parent();
            }
            if (isRoot()) {
                finished = true;
            } else {
                nextSibling();
            }
        }
        return data;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
}
