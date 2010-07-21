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
 * NOT YET USED. Real Class JavaDoc comes when this class becomes useful.
 * 
 * @author Oliver Lorenz
 */
public enum TreeNodeCapability {
    
    /**
     * Setting and changing parent is allowed.
     * {@link TreeNode#setParent(TreeNode)} and
     * {@link TreeNode#changeParent(TreeNode)} are tested.
     */
    CHANGE_PARENT,
    
    /**
     * Setting, adding and removing children is allowed.
     * {@link TreeNode#addChild(Object)},
     * {@link TreeNode#addChild(TreeNode)},
     * {@link TreeNode#addChildren(Object...)},
     * {@link TreeNode#setChildren(java.util.Collection)},
     * {@link TreeNode#removeChild(TreeNode)},
     * {@link TreeNode#removeChildAt(int)},
     * {@link TreeNode#removeAllChildren()}
     * and {@link TreeNode#insertChildAt(int, TreeNode)}
     * are tested.
     */
    CHANGE_CHILDREN,
    
    /**
     * Setting and changing data is allowed.
     * {@link TreeNode#setData(Object)} is tested.
     */
    CHANGE_DATA

}
