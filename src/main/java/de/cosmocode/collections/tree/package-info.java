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
/**
 * <p>
 * A Tree API for Java. At the moment there are only nodes with multiple children, no binary nodes.
 * </p>
 * <p>
 * At the heart of this API is the TreeNode interface which provides several methods
 * to list or change children and parent nodes.
 * The Tree Interface then wraps a TreeNode as its root node.
 * From there the whole tree can be navigated or traversed (listed).
 * </p>
 * <p>
 * The following implementations exist:
 * </p>
 * <ul>
 *   <li> DefaultTree implements Tree </li>
 *   <li> DuplicatesNode implements TreeNode </li>
 *   <li> MergeNode implements TreeNode </li>
 *   <li> UniqueNode implements TreeNode </li>
 * </ul>
 * <p>
 * There are also immutable implementations:
 * <ul>
 *   <li> ImmutableTree implements Tree </li>
 *   <li> ImmutableNode implements TreeNode </li>
 * </ul>
 */
package de.cosmocode.collections.tree;
