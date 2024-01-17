package com.example.sportsdating;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.sportsdating.datastructure.RBTree;
/**
 * @Author Ke Yan
 */
public class RBTreeTest {
    // FIXME need better and more test for user class..
    RBTree<Integer> tree;

    @Before
    public void init() {
        this.tree = new RBTree<Integer>();

        this.tree.insert(1);
        this.tree.insert(2);
        this.tree.insert(3);
        this.tree.insert(4);
        this.tree.insert(5);
        this.tree.insert(6);
        this.tree.insert(8);
        this.tree.insert(7);

        this.tree.insert(9);
        this.tree.insert(0);
        this.tree.insert(10);
    }

    @Test
    public void testProp2() {
        assertTrue(this.tree.testProp2());
    }

    @Test
    public void testProp3() {
        assertTrue(this.tree.testProp3());
    }

    @Test
    public void insertTest() {
        RBTree<Integer> tree  = new RBTree<Integer>();
        tree.insert(7);
        tree.insert(3);
        tree.insert(1);
        tree.insert(0);
        tree.insert(2);
        tree.insert(5);
        tree.insert(6);
        tree.insert(11);
        tree.insert(9);
        tree.insert(8);
        tree.insert(15);
        tree.insert(13);
        tree.insert(12);
        tree.insert(14);
        tree.insert(17);
        tree.insert(16);
        tree.insert(18);
        tree.insert(19);
        tree.insert(-1);
        tree.insert(4);
        tree.insert(10);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());
    }

    @Test
    public void insertrootTest() {
        RBTree<Integer> tree  = new RBTree<Integer>();
        tree.insert(1);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());
    }

    @Test
    public void deleteNonLeafBlackNodeTest() { // case 3 and case 5
        RBTree<Integer> tree  = new RBTree<Integer>();
        tree.insert(7);
        tree.insert(3);
        tree.insert(1);
        tree.insert(0);
        tree.insert(2);
        tree.insert(5);
        tree.insert(6);
        tree.insert(11);
        tree.insert(9);
        tree.insert(8);
        tree.insert(15);
        tree.insert(13);
        tree.insert(12);
        tree.insert(14);
        tree.insert(17);
        tree.insert(16);
        tree.insert(18);
        tree.insert(19);
        tree.insert(-1);
        tree.insert(4);
        tree.insert(10);
        tree.delete(13);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());

        tree.delete(10);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());

        tree.delete(4);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());

        tree.delete(6);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());

        tree.delete(1);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());

        tree.delete(11);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());

        tree.delete(8);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());

        tree.delete(17);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());
        // test case 5
        tree.delete(19);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());

        tree.delete(18);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());

        tree.delete(0);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());
    }

    @Test
    public void deleteCase4Test() {
        RBTree<Integer> tree  = new RBTree<Integer>();
        tree.insert(7);
        tree.insert(3);
        tree.insert(1);
        tree.insert(0);
        tree.insert(2);
        tree.insert(5);
        tree.insert(6);
        tree.insert(11);
        tree.insert(9);
        tree.insert(8);
        tree.insert(15);
        tree.insert(13);
        tree.insert(12);
        tree.insert(14);
        tree.insert(17);
        tree.insert(16);
        tree.insert(18);
        tree.insert(19);
        tree.insert(4);
        tree.insert(10);
        tree.delete(13);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());
    }

    @Test
    public void simpleDeleteTest() {
        RBTree<Integer> tree  = new RBTree<Integer>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.delete(1);
        assertEquals((Integer)2, tree.root.getValue());
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());
    }

    @Test
    public void deleteRootTest() {
        RBTree<Integer> tree  = new RBTree<Integer>();
        tree.insert(1);
        tree.insert(2);
        tree.insert(3);
        tree.delete(2);
        assertEquals((Integer)3, tree.root.getValue());
        assertEquals((Integer)1, tree.root.left.getValue());
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());
    }

    @Test
    public void deleteSimpleBlackNonLeafNodeTest() {
        RBTree<Integer> tree  = new RBTree<Integer>();

        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(5);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        tree.delete(2);
        assertEquals((Integer)5, tree.root.getValue());
        assertEquals((Integer)3, tree.root.left.getValue());
        assertEquals((Integer)7, tree.root.right.getValue());
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());
    }

    // test for right and left rotation
    @Test
    public void rotateTest() {
        RBTree.Node<Integer> node3 = new RBTree.Node<>(3);
        RBTree.Node<Integer> node2 = new RBTree.Node<>(2, null, node3);
        RBTree.Node<Integer> node1 = new RBTree.Node<>(1, null, node2);
        tree.rotateDir(node1, 1 - node2.getDir());
        assertNull(node2.getParent());
        assertEquals(node1.getParent(), node2);
        assertEquals(node3.getParent(), node2);

    }

    @Test
    public void findTest() {
        RBTree<Integer> tree  = new RBTree<Integer>();

        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        tree.insert(5);

        assertEquals((Integer) 4, tree.find(4).getValue());
        assertEquals((Integer) 5, tree.find(5).getValue());
    }

    @Test
    public void deleteOneOrTwoChildrenTest() {
        RBTree<Integer> tree  = new RBTree<Integer>();

        tree.insert(2);
        tree.insert(3);
        tree.insert(4);
        tree.insert(6);
        tree.insert(7);
        tree.insert(8);
        tree.insert(9);
        tree.insert(5);

        tree.delete(2);
        tree.delete(3);
        tree.delete(4);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());
        tree.delete(6);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());
        tree.delete(7);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());
        tree.delete(8);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());
        tree.delete(9);
        assertTrue(tree.testProp2());
        assertTrue(tree.testProp3());
        tree.delete(5);
        assertNull(tree.root);
    }

    @Test
    public void deleteOnlyRootTest() {
        RBTree<Integer> tree  = new RBTree<Integer>();
        tree.insert(1);
        tree.insert(2);
        tree.delete(1);
        assertTrue(tree.root.getValue() == 2);
    }

    @Test
    public void insertRootTest() {
        RBTree<Integer> tree  = new RBTree<Integer>();
        tree.insert(1);
        assertEquals(1, (int) tree.root.getValue());
    }

    @Test(timeout = 1000, expected = IllegalArgumentException.class)
    public void testDeleteException() throws IllegalArgumentException {
        RBTree<Integer> tree  = new RBTree<Integer>();
        tree.insert(1);
        tree.insert(2);
        tree.delete(3);
    }


}
