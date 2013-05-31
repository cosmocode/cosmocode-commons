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
 * in level order ({@link TraverseMode#LEVEL_ORDER}).
 * 
 * @author Oliver Lorenz
 *
 * @param <E> the type of the elements returned
 */
public final class LevelOrderIterator<E> extends AbstractTreeIterator<E> {
    
    private int iterLevel;
    
    private boolean finished;
    
    public LevelOrderIterator(final TreeNode<E> root) {
        super(root);
        iterLevel = 0;
        finished = false;
    }
    
    @Override
    public boolean hasNext() {
        return !finished;
    }
    
    /**
     * Advances the iterator on the same level.
     * If this fails then this method returns false.
     * @return true if a next element on the same level could be found, false otherwise
     */
    protected boolean sameLevel() {
        while (true) {
            if (getCurrentLevel() == iterLevel && hasNextSibling()) {
                // go right on current level
                nextSibling();
                return true;
            }
            
            // can't go right: go up again, until we can go right again
            while (!hasNextSibling() && !isRoot()) {
                parent();
            }
            if (isRoot()) {
                return false;
            } else {
                nextSibling();
            }
            
            while (hasNextChild() && getCurrentLevel() < iterLevel) {
                nextChild();
            }
            if (getCurrentLevel() == iterLevel) {
                return true;
            }
        }
    }
    
    @Override
    public E next() {
        if (finished) {
            throw new NoSuchElementException();
        }
        
        final E data = currentData();

        if (sameLevel()) {
            return data;
        } else {
            // finished this depth: go to next depth
            ++iterLevel;
            
            while (hasChildren() && getCurrentLevel() < iterLevel) {
                firstChild();
            }
            
            if (getCurrentLevel() == iterLevel || sameLevel()) {
                // reached a new level
                return data;
            } else {
                finished = true;
                return data;
            }
        }
    }
    
    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }
    
}
