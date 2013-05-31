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

import java.beans.PropertyChangeEvent;
import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.io.Serializable;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;


/**
 * <p>
 * Represents a node of the Tree<T> class. The TreeNode<T> is also a container, and
 * can be thought of as instrumentation to determine the location of the type T
 * in the Tree<T>.
 * </p>
 * <p>
 * The data of the children of a UniqueNode are always distinct, duplicates are rejected.
 * This means: If someone attempts to call {@link #addChildNode(TreeNode)} with 
 * a TreeNode that has the same data as an already added child
 * then this UniqueNode will remain unchanged and throw an IllegalArgumentException.
 * The same happens on calls to {@link #insertChildAt(int, TreeNode)}.
 * </p>
 * 
 * @param <E> a generic type that indicates the data stored in this UniqueNode
 * 
 * @author Oliver Lorenz
 * @author Willi Schoenborn
 */
public class UniqueNode<E> extends AbstractTreeNode<E> implements Serializable, VetoableChangeListener {

    public static final String ERR_ADD_DUPLICATE = "The given node already exists in this tree";
    
    private static final long serialVersionUID = 3205686938101651970L;
     
    private E data;
    private Map<E, TreeNode<E>> children;
    private TreeNode<E> parent;
 
    /**
     * Default constructor.
     * Sets parent and data to null.
     */
    public UniqueNode() {
        super();
    }
 
    /**
     * Convenience constructor to create a UniqueNode<T> with an instance of T.
     * Sets parent to null.
     * @param data an instance of T.
     */
    public UniqueNode(final E data) {
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
    protected UniqueNode(final TreeNode<E> parent) {
        this();
        this.parent = parent;
    }
    
    /**
     * <p> Convenience constructor to create a UniqueNode<T> as a child of parent with an instance of T.
     * </p>
     * <p> <strong>Attention:</strong> 
     * This constructor only stores a reference to the parent and does not alter it.
     * It should only be used in a context where the parent is altered outside of the constructor.
     * </p>
     * @param parent the parent for this node. Can be null.
     * @param data an instance of T
     */
    protected UniqueNode(final TreeNode<E> parent, final E data) {
        this();
        this.parent = parent;
        this.data = data;
    }
    
    
    /**
     * Initializes the children if necessary.
     */
    protected void initChildren() {
        if (children == null) {
            children = new LinkedHashMap<E, TreeNode<E>>();
        }
    }
    
    @Override
    protected void setThisAsParentOf(TreeNode<E> child) {
        super.setThisAsParentOf(child);
        child.addVetoableChangeListener(this);
    }
    
    
    @Override
    public TreeNode<E> getParent() {
        return parent;
    }
    
    @Override
    public void setParent(TreeNode<E> parent) {
        this.parent = parent;
    }
    
    @Override
    public Collection<TreeNode<E>> getChildren() {
        if (children == null) {
            return Collections.emptyList();
        } else {
            return Collections.unmodifiableCollection(children.values());
        }
    }

    @Override
    public void setChildren(Collection<TreeNode<E>> children) {
        if (children == null) throw new NullPointerException("setChildren expects a non-null parameter");
        
        final Collection<TreeNode<E>> newChildren = Lists.newArrayList(children);

        removeAllChildren();
        for (final TreeNode<E> child : newChildren) {
            this.addChildNode(child);
        }
    }
 
    @Override
    public int getNumberOfChildren() {
        return children == null ? 0 : children.size();
    }
    
    @Override
    protected void addChildNodeInternal(TreeNode<E> child) {
        children.put(child.getData(), child);
    }
    
    /**
     * {@inheritDoc}
     * 
     * @throws IllegalArgumentException if the `child` is this TreeNode or an ancestor of this TreeNode
     *                                  or if its data is already stored in another child
     * @throws NullPointerException if the parameter `child` is null
     */
    @Override
    public void addChildNode(final TreeNode<E> child) {
        if (!checkChildNode(child)) return;
        
        initChildren();
        if (children.containsKey(child.getData())) {
            throw new IllegalArgumentException(ERR_ADD_DUPLICATE);
        }
        
        setThisAsParentOf(child);
        addChildNodeInternal(child);
    }
    
    @Override
    public TreeNode<E> addChild(final E childData) {
        initChildren();
        
        if (children.containsKey(childData)) {
            throw new IllegalArgumentException(ERR_ADD_DUPLICATE);
        } else {
            final UniqueNode<E> newNode = new UniqueNode<E>(this, childData);
            children.put(childData, newNode);
            newNode.addVetoableChangeListener(this);
            return newNode;
        }
    }
    
    @Override
    public boolean hasChild(E childData) {
        return children != null && children.containsKey(childData);
    }
    
    @Override
    public void insertChildAt(int index, TreeNode<E> child) throws IndexOutOfBoundsException {
        if (child == null) throw new NullPointerException("insertChildAt expects a non-null value");
        
        initChildren();
        if (index == getNumberOfChildren()) {
            // this is really an append
            addChildNode(child);
        } else if (index < 0) {
            throw new IndexOutOfBoundsException("index < 0");
        } else if (index > getNumberOfChildren()) {
            throw new IndexOutOfBoundsException("index > number of children");
        } else if (children.containsKey(child.getData())) {
            throw new IllegalArgumentException(ERR_ADD_DUPLICATE);
        } else {
            checkChildNode(child);
            setThisAsParentOf(child);
            
            // go to index
            int counter = index;
            final Iterator<Map.Entry<E, TreeNode<E>>> iter = children.entrySet().iterator();
            while (counter > 0 && iter.hasNext()) {
                iter.next();
                --counter;
            }
            
            // create new map, put new element and all old elements into temporary map
            final int numElements = children.size() - index + 1;
            final Map<E, TreeNode<E>> nextElements = new LinkedHashMap<E, TreeNode<E>>(2 * numElements);
            nextElements.put(child.getData(), child);
            while (iter.hasNext()) {
                final Map.Entry<E, TreeNode<E>> elem = iter.next();
                nextElements.put(elem.getKey(), elem.getValue());
                iter.remove();
            }
            
            // put all elements of temporary map back into children map
            children.putAll(nextElements);
        }
    }
    
    @Override
    public void removeChildNode(final TreeNode<E> child) {
        if (child == null) throw new NullPointerException("removeChild expects a non-null parameter");

        initChildren();
        final TreeNode<E> myChild = children.remove(child.getData());
        if (myChild != null && myChild == child) {
            myChild.setParent(null);
            myChild.removeVetoableChangeListener(this);
        }
    }
     
    @Override
    public void removeChildAt(final int index) throws IndexOutOfBoundsException {
        final TreeNode<E> child = getChildAt(index);
        if (child != null) {
            children.remove(child.getData());
            child.setParent(null);
            child.removeVetoableChangeListener(this);
        }
    }
    
    @Override
    public void removeAllChildren() {
        if (children == null) return;
        
        final Iterator<Map.Entry<E, TreeNode<E>>> entries = this.children.entrySet().iterator();
        while (entries.hasNext()) {
            final Map.Entry<E, TreeNode<E>> entry = entries.next();
            if (entry.getValue() != null) {
                entry.getValue().setParent(null);
                entry.getValue().removeVetoableChangeListener(this);
            }
            entries.remove();
        }
    }

    @Override
    public E getData() {
        return this.data;
    }

    @Override
    public void setDataUnchecked(E newData) {
        this.data = newData;
    }
    

    @Override
    public void vetoableChange(PropertyChangeEvent evt) throws PropertyVetoException {
        if (DATA_PROPERTY.equals(evt.getPropertyName())
            && children.containsKey(evt.getOldValue())) {
            
            // this is one of our children
            @SuppressWarnings("unchecked")
            final TreeNode<E> child = (TreeNode<E>) evt.getSource();
            @SuppressWarnings("unchecked")
            final E newData = (E) evt.getNewValue();
            
            if (children.containsKey(newData)) {
                throw new PropertyVetoException(ERR_ADD_DUPLICATE, evt);
            } else {
                children.remove(evt.getOldValue());
                children.put(newData, child);
            }
        }
    }

}
