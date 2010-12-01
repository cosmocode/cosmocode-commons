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

package de.cosmocode.commons.reflect;

import java.beans.PropertyVetoException;
import java.beans.VetoableChangeListener;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.Collections2;
import com.google.common.collect.Lists;

import de.cosmocode.collections.tree.TreeNode;

/**
 * {@link TreeNode} implementation which maps directly to {@link Class}es in the inheritance
 * tree.
 *
 * @since 1.12 
 * @author Willi Schoenborn
 */
final class HierarchyTreeNode implements TreeNode<Class<?>> {

    private final TreeNode<Class<?>> root;
    private final TreeNode<Class<?>> parent;
    private final Class<?> type;
    
    public HierarchyTreeNode(Class<?> type) {
        this.type = Preconditions.checkNotNull(type, "Type");
        this.parent = null;
        this.root = this;
    }
    
    public HierarchyTreeNode(Class<?> type, TreeNode<Class<?>> parent, TreeNode<Class<?>> root) {
        this.type = Preconditions.checkNotNull(type, "Type");
        this.parent =  Preconditions.checkNotNull(parent, "Parent");
        this.root =  Preconditions.checkNotNull(root, "Root");
    }

    @Override
    public TreeNode<Class<?>> getRoot() {
        return root;
    }

    @Override
    public TreeNode<Class<?>> getParent() {
        return parent;
    }

    @Override
    public Collection<TreeNode<Class<?>>> getChildren() {
        final List<Class<?>> types;
        
        if (type.getSuperclass() == null) {
            types = Arrays.asList(type.getInterfaces());
        } else {
            types = Lists.asList(type.getSuperclass(), type.getInterfaces());
        }
        
        return Collections2.transform(types, new Function<Class<?>, TreeNode<Class<?>>>() {
            
            @Override
            public TreeNode<Class<?>> apply(Class<?> from) {
                return new HierarchyTreeNode(from, HierarchyTreeNode.this, root);
            }
            
        });
    }

    @Override
    public int getNumberOfChildren() {
        return (type.getSuperclass() == null ? 0 : 1) + type.getInterfaces().length;
    }

    @Override
    public boolean hasChild(Class<?> childData) {
        if (childData == null) {
            return false;
        } else if (childData.equals(type.getSuperclass())) {
            return true;
        } else {
            for (Class<?> iface : type.getInterfaces()) {
                if (iface.equals(childData)) {
                    return true;
                }
            }
            return false;
        }
    }

    @Override
    public TreeNode<Class<?>> getChildAt(int index) throws IndexOutOfBoundsException {
        final Class<?> child;
        final Class<?> superClass = type.getSuperclass();
        if (index == 0 && superClass != null) {
            child = superClass;
        } else {
            final Class<?>[] interfaces = type.getInterfaces();
            child = interfaces[index];
        }
        return new HierarchyTreeNode(child, this, root);
    }

    @Override
    public Class<?> getData() {
        return type;
    }

    @Override
    public boolean isLeaf() {
        return type.getSuperclass() == null && type.getInterfaces().length == 0;
    }

    @Override
    public boolean contains(TreeNode<Class<?>> descendant) {
        return equals(descendant) || contains(descendant.getParent());
    }

    /*
     * Everything below is unsupported.
     */

    @Override
    public void setData(Class<?> data) throws PropertyVetoException {
        throw new UnsupportedOperationException();
    }
    
    @Override
    public void setParent(TreeNode<Class<?>> parent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public boolean changeParent(TreeNode<Class<?>> newParent) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void setChildren(Collection<TreeNode<Class<?>>> children) {
        throw new UnsupportedOperationException();
    }

    @Override
    public List<TreeNode<Class<?>>> addChildren(Class<?>... children) {
        throw new UnsupportedOperationException();
    }

    @Override
    public TreeNode<Class<?>> addChild(Class<?> childData) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addChildNode(TreeNode<Class<?>> child) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void insertChildAt(int index, TreeNode<Class<?>> child) throws IndexOutOfBoundsException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeChildNode(TreeNode<Class<?>> child) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeChildAt(int index) throws IndexOutOfBoundsException {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeAllChildren() {
        throw new UnsupportedOperationException();
    }

    @Override
    public void addVetoableChangeListener(VetoableChangeListener listener) {
        throw new UnsupportedOperationException();
    }

    @Override
    public void removeVetoableChangeListener(VetoableChangeListener listener) {
        throw new UnsupportedOperationException();
    }

}
