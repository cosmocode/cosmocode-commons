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

/**
 * Determines the mode in which a tree is traversed.
 * 
 * @author Oliver Lorenz
 */
public enum TraverseMode {

    /**
     * Returns the {@code Tree<T>} as an {@code Iterator<T>}.
     * The elements are in pre order, meaning 
     * the node itself is visited first and its children afterwards
     * (http://en.wikipedia.org/wiki/Tree_traversal#Traversal).
     */
    PRE_ORDER,

    /**
     * Returns the {@code Tree<T>} as an {@code Iterator<T>}.
     * The elements are in order, meaning 
     * the left part is traversed, then the element itself, then the right part
     * (http://en.wikipedia.org/wiki/Tree_traversal#Traversal).
     */
    IN_ORDER,

    /**
     * Returns the {@code Tree<T>} as an {@code Iterator<T>}.
     * The elements are post order, meaning 
     * the children are visited first and the node itself afterwards
     * (http://en.wikipedia.org/wiki/Tree_traversal#Traversal).
     */
    POST_ORDER,
    
    /**
     * Returns the {@code Tree<T>} as an {@code Iterator<T>}.
     * The elements are in level-order, meaning 
     * every level is visited before going to the next level
     * (http://en.wikipedia.org/wiki/Tree_traversal#Traversal).
     */
    LEVEL_ORDER

}
