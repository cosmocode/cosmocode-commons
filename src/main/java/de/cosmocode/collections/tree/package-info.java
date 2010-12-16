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