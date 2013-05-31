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

import com.google.common.collect.ImmutableList;
import junit.framework.Assert;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.Collection;
import java.util.UUID;

/**
 * Tests {@code DuplicatesNode<String>}.
 * 
 * @author Oliver Lorenz
 * 
 * @see DuplicatesNode
 */
public class DuplicatesNodeStringTest extends TreeNodeTest<String> {
    
    @Override
    protected String newData() {
        return UUID.randomUUID().toString();
    }

    @Override
    protected Collection<TreeNode<String>> createRealWorldChildren() {
        final ImmutableList.Builder<TreeNode<String>> builder = ImmutableList.builder();

        final TreeNode<String> node1 = new DuplicatesNode<String>("root-1");
        final TreeNode<String> node1x1 = node1.addChild("1-1");
        node1x1.addChild("1-1-1");
        node1x1.addChild("1-1-2");
        final TreeNode<String> node2 = new DuplicatesNode<String>("root-2");
        final TreeNode<String> node2x1 = node2.addChild("2-1");
        node2x1.addChild("2-1-1");
        node2x1.addChild("2-1-duplicate");
        node2x1.addChild("2-1-duplicate");
        final TreeNode<String> node3 = new MergeNode<String>("root-3");
        node3.addChild("3-1");
        node3.addChild("3-2");
        final TreeNode<String> node4 = new DuplicatesNode<String>("root-duplicate");
        node4.addChild("duplicate-1");
        final TreeNode<String> node5 = new DuplicatesNode<String>("root-duplicate");
        node5.addChild("duplicate-1");
        
        builder.add(node1);
        builder.add(node2);
        builder.add(node3);
        builder.add(node4);
        builder.add(node5);
        
        return builder.build();
    }

    @Override
    protected TreeNode<String> emptyNode() {
        return new DuplicatesNode<String>();
    }

    @Override
    protected TreeNode<String> newNode(String data) {
        return new DuplicatesNode<String>(data);
    }
    
    /**
     * Tests serialization on the DuplicatesNode.
     * @throws IOException if some low-level exception occurred
     * @throws ClassNotFoundException should not happen
     */
    @Test
    public void testSerialization() throws IOException, ClassNotFoundException {
        final TreeNode<String> root = new DuplicatesNode<String>("root");
        root.addChildren("child1", "child2", "child3");
        
        final File outFile = new File("test.ser");
        final OutputStream outStream = new BufferedOutputStream(new FileOutputStream(outFile));
        final ObjectOutputStream objectStream = new ObjectOutputStream(outStream);
        objectStream.writeObject(root);
        objectStream.close();

        System.out.println("File length: " + outFile.length());

        final InputStream inStream = new BufferedInputStream(new FileInputStream(outFile));
        final ObjectInputStream objIn = new ObjectInputStream(inStream);
        @SuppressWarnings("unchecked")
        final TreeNode<String> fromSerialized = (TreeNode<String>) objIn.readObject();
        objIn.close();
        
        outFile.delete();
        
        Assert.assertEquals(root, fromSerialized);
    }

}
