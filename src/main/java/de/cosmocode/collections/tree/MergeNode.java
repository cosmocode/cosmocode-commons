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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import java.beans.PropertyChangeEvent;
import java.beans.VetoableChangeListener;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.Map;

/**
 * <p>
 * Represents a node of the Tree<T> class. The TreeNode<T> is also a container, and
 * can be thought of as instrumentation to determine the location of the type T
 * in the Tree<T>.
 * </p>
 * <p>
 * The data of the children of a MergeNode are always distinct, duplicates are merged.
 * This means: If someone attempts to call {@link #addChildNode(TreeNode)} with 
 * a TreeNode that has the same data (via the getData()-method) as an already added child
 * then this MergeNode will (recursively) merge the already existing child with the given argument.
 * The same holds true for {@link #insertChildAt(int, TreeNode)} and {@link #addChildren(Object...)}.
 * </p>
 * 
 * @param <T> a generic type that indicates the data stored in this MergeNode
 * 
 * @author Oliver Lorenz
 * @author Willi Schoenborn
 */
public class MergeNode<T> extends AbstractTreeNode<T> implements Serializable, VetoableChangeListener {
    
    private static final long serialVersionUID = 1498524990314635637L;
    
    private T data;
    private Map<T, TreeNode<T>> children;
    private TreeNode<T> parent;
 
    /**
     * Default constructor.
     */
    public MergeNode() {
        super();
    }
 
    /**
     * Convenience constructor to create a Node<T> with an instance of T.
     * @param data an instance of T.
     */
    public MergeNode(final T data) {
        this();
        this.data = data;
    }
    
    
    /**
     * <p> Set parent explicitly.
     * </p>
     * <p> <strong>Attention:</strong> 
     * This constructor only stores a reference to the parent and does not alter it.
     * It should only be used in a context where the parent is altered outside of the constructor.
     * </p>
     * @param parent the parent of this node
     */
    protected MergeNode(final TreeNode<T> parent) {
        this();
        this.parent = parent;
    }
    
    /**
     * <p> Convenience constructor to create a MergeNode<T> as a child of parent with an instance of T.
     * </p>
     * <p> <strong>Attention:</strong> 
     * This constructor only stores a reference to the parent and does not alter it.
     * It should only be used in a context where the parent is altered outside of the constructor.
     * </p>
     * @param parent the parent for this node. Can be null.
     * @param data an instance of T
     */
    protected MergeNode(final TreeNode<T> parent, final T data) {
        this();
        this.parent = parent;
        this.data = data;
    }
    
    
    /**
     * Initializes the children if necessary.
     */
    protected void initChildren() {
        if (children == null) {
            children = Maps.newLinkedHashMap();
        }
    }
    
    @Override
    protected void setThisAsParentOf(TreeNode<T> child) {
        super.setThisAsParentOf(child);
        child.addVetoableChangeListener(this);
    }
    
    
    /**
     * Merges 2 nodes together and returns a new MergeDuplicatesNode.
     * The children of the new MergeDuplicatesNode are the union of the children of child1 and child2.
     * Either node1 or node2 can be null, but not both. If both parameters are null,
     * then a NullPointerException is thrown.
     * @param node1 the first node to merge
     * @param node2 the second node to merge
     * @param <T> a generic type that indicates the data stored in the parameters
     * @return a TreeNode&lt;T&gt; whose children are the union of the children of node1 and node2
     * @throws NullPointerException if node1 and node2 are null
     */
    public static <T> MergeNode<T> mergeNodes(final TreeNode<T> node1, final TreeNode<T> node2) {
        if (node1 == null && node2 == null) return null;
        
        final MergeNode<T> newChild;
        if (node1 == null) {
            newChild = new MergeNode<T>(node2.getParent(), node2.getData());
            newChild.setChildren(node2.getChildren());
        } else {
            newChild = new MergeNode<T>(node1.getParent(), node1.getData());
            newChild.setChildren(node1.getChildren());
            if (node2 != null) {
                // real merge
                final Iterable<TreeNode<T>> nodes = Lists.newArrayList(node2.getChildren());
                for (final TreeNode<T> subChild : nodes) {
                    newChild.addChildNode(subChild);
                }
            }
        }
        return newChild;
    }

    @Override
    public TreeNode<T> addChild(final T childData) {
        initChildren();
        
        TreeNode<T> child = children.get(childData);
        if (child != null) {
            return child;
        } else {
            child = new MergeNode<T>(this, childData);
            children.put(childData, child);
            child.addVetoableChangeListener(this);
            return child;
        }
    }
    
    @Override
    protected void addChildNodeInternal(TreeNode<T> child) {
        initChildren();
        
        // create the merged child
        final T childData = child.getData();
        final MergeNode<T> newChild;
        if (children.containsKey(childData)) {
            newChild = mergeNodes(children.get(childData), child);
        } else if (child instanceof MergeNode<?>) {
            newChild = (MergeNode<T>) child;
        } else {
            newChild = new MergeNode<T>(child.getParent(), childData);
            newChild.setChildren(child.getChildren());
        }
        
        // add child internal
        children.put(childData, newChild);
    }
    
    @Override
    public boolean hasChild(final T childData) {
        return children != null && children.containsKey(childData);
    }

    @Override
    public Collection<TreeNode<T>> getChildren() {
        if (children == null) {
            return Collections.emptySet();
        } else {
            return Collections.unmodifiableCollection(children.values());
        }
    }

    @Override
    public T getData() {
        return data;
    }

    @Override
    public int getNumberOfChildren() {
        return children == null ? 0 : children.size();
    }

    @Override
    public TreeNode<T> getParent() {
        return parent;
    }

    @Override
    public void insertChildAt(final int index, final TreeNode<T> child) throws IndexOutOfBoundsException {
        if (child == null) throw new NullPointerException("insertChildAt expects a non-null value");
        
        initChildren();
        if (index == getNumberOfChildren() || children.containsKey(child.getData())) {
            // this is either an append or a duplicate
            addChildNode(child);
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("index < 0");
        } else if (index > getNumberOfChildren()) {
            throw new IndexOutOfBoundsException("index > number of children");
        } else {
            checkChildNode(child);
            setThisAsParentOf(child);
            
            // go to index
            int counter = index;
            final Iterator<Map.Entry<T, TreeNode<T>>> iter = children.entrySet().iterator();
            while (counter > 0 && iter.hasNext()) {
                iter.next();
                --counter;
            }
            
            // create new map, put new element and all old elements into temporary map
            final Map<T, TreeNode<T>> nextElements = Maps.newLinkedHashMap();
            nextElements.put(child.getData(), child);
            
            while (iter.hasNext()) {
                final Map.Entry<T, TreeNode<T>> elem = iter.next();
                nextElements.put(elem.getKey(), elem.getValue());
                iter.remove();
            }
            
            // put all elements of temporary map back into children map
            children.putAll(nextElements);
        }
    }

    @Override
    public void removeChildNode(final TreeNode<T> child) {
        if (child == null) throw new NullPointerException("removeChild expects a non-null parameter");
        
        initChildren();
        final TreeNode<T> myChild = children.remove(child.getData());
        if (myChild != null) {
            myChild.setParent(null);
            myChild.removeVetoableChangeListener(this);
        }
    }
    
    @Override
    public void removeAllChildren() {
        if (children == null) return;
        
        final Iterator<Map.Entry<T, TreeNode<T>>> entries = this.children.entrySet().iterator();
        while (entries.hasNext()) {
            final Map.Entry<T, TreeNode<T>> entry = entries.next();
            if (entry.getValue() != null) {
                entry.getValue().setParent(null);
                entry.getValue().removeVetoableChangeListener(this);
            }
            entries.remove();
        }
    }

    @Override
    public void removeChildAt(final int index) throws IndexOutOfBoundsException {
        final TreeNode<T> child = getChildAt(index);
        if (child != null) {
            children.remove(child.getData());
            child.setParent(null);
            child.removeVetoableChangeListener(this);
        }
    }

    @Override
    public void setChildren(final Collection<TreeNode<T>> children) {
        if (children == null) throw new NullPointerException("setChildren expects a non-null parameter");
        
        final Collection<TreeNode<T>> newChildren = Lists.newArrayList(children);

        initChildren();
        removeAllChildren();
        for (final TreeNode<T> child : newChildren) {
            this.addChildNode(child);
        }
    }
    

    @Override
    public void setDataUnchecked(final T newData) {
        this.data = newData;
    }

    @Override
    public void setParent(final TreeNode<T> parent) {
        this.parent = parent;
    }
    

    @Override
    public void vetoableChange(PropertyChangeEvent evt) {
        if (DATA_PROPERTY.equals(evt.getPropertyName())
            && children.containsKey(evt.getOldValue())) {
            
            // this is one of our children
            @SuppressWarnings("unchecked")
            final TreeNode<T> child = (TreeNode<T>) evt.getSource();
            @SuppressWarnings("unchecked")
            final T newData = (T) evt.getNewValue();
            
            if (children.containsKey(newData)) {
                children.remove(evt.getOldValue());
                final TreeNode<T> otherChild = children.get(newData);
                children.put(newData, mergeNodes(child, otherChild));
            } else {
                children.remove(evt.getOldValue());
                children.put(newData, child);
            }
        }
    }

}
