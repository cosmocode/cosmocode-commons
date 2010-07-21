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

import java.util.Collection;
import java.util.UUID;

import com.google.common.collect.Lists;

/**
 * Tests the {@link DefaultTree}.
 * 
 * @author Oliver Lorenz
 */
public class DefaultTreeStringTest extends AbstractTreeTest<String> {
    
    @Override
    protected String newData() {
        return UUID.randomUUID().toString();
    }
    
    @Override
    protected Collection<TreeNode<String>> createRealWorldChildren() {
        final Collection<TreeNode<String>> nodes = Lists.newArrayList();
        final TreeNode<String> node1 = newNode("root-1");
        final TreeNode<String> node2 = newNode("root-2");
        node2.addChild("2-1");
        final TreeNode<String> node3 = newNode("root-3");
        node3.addChild("3-1");
        node3.addChild("3-2");
        final TreeNode<String> node4 = newNode("root-4");
        
        nodes.add(node1);
        nodes.add(node2);
        nodes.add(node3);
        nodes.add(node4);
        
        return nodes;
    }
    
    @Override
    protected Tree<String> createComplexTree() {
        final Tree<String> newTree = newTree(newNode(newData()));
        final TreeNode<String> child1 = newTree.addChild(newData());
        for (final TreeNode<String> grandchild : child1.addChildren(newData(), newData(), null, newData())) {
            grandchild.addChild(newData()).addChildren(newData(), null);
        }
        
        final TreeNode<String> grandchild2 = newTree.addChild(newData()).addChild(null);
        grandchild2.addChildren(newData(), newData(), null, newData());
        
        final TreeNode<String> grandchild3 = newTree.addChild(newData()).addChild(newData());
        for (final TreeNode<String> grandgrandchild : grandchild3.addChildren("test", "blubbs", "foo", "bar")) {
            grandgrandchild.
                addChild("4th-level (" + grandgrandchild.getData() + ")").
                addChild("5th-level (" + grandgrandchild.getData() + ")").
                addChild("6th-level (" + grandgrandchild.getData() + ")").
                addChild("7th-level (" + grandgrandchild.getData() + ")").
                addChild("8th-level (" + grandgrandchild.getData() + ")");
        }
        
        return newTree;
    }

    @Override
    protected Tree<String> newTree(TreeNode<String> rootNode) {
        return new DefaultTree<String>(rootNode);
    }
    
    @Override
    protected TreeNode<String> emptyNode() {
        return new DuplicatesNode<String>();
    }
    

    @Override
    protected TreeNode<String> newNode(final String data) {
        return new DuplicatesNode<String>(data);
    }

}
