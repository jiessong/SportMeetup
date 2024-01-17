package com.example.sportsdating.datastructure;
/**
 * @Author Ke Yan
 */
public class RBTree<T extends Comparable<T>>{
    /* for this RBTree, root node can be red */
    public Node<T> root;

    /**
     * Initialize empty RBTree
     */
    public RBTree() {
        this.root = null;
    }

    /**
     * simple left rotation
     * by changing pointer
     */
    private void leftRotate(Node<T> node) {
        // new parent node
        Node<T> newNode = node.right;
        node.right = newNode.left;
        newNode.left.parent = node;
        // node may be root node
        newNode.parent = node.parent;
        if (newNode.parent == null) this.root = newNode;
        else {
            if (node.parent.left == node) node.parent.left = newNode;
            else node.parent.right = newNode;
        }
        // node is new node's left child
        newNode.left = node;
        node.parent = newNode;
    }

    /**
     * simple right rotation
     */
    private void rightRotate(Node<T> node) {
        Node<T> newNode = node.left;
        node.left = newNode.right;
        newNode.right.parent = node;
        newNode.parent = node.parent;
        if (newNode.parent == null) this.root = newNode;
        else {
            if (node.parent.left == node) node.parent.left = newNode;
            else node.parent.right = newNode;
        }
        newNode.right = node;
        node.parent = newNode;

    }

    /**
     * @param node node to rotate
     * @param dir 0 do right rotation, 1 do left rotation
     */
    public void rotateDir(Node<T> node, int dir) {
        if (dir == 0) leftRotate(node);
        else if (dir == 1) rightRotate(node);
        else throw new IllegalArgumentException("dir has to be 0 or 1");
    }

    public Node<T> find(T value) {
        while (root != null) {
            if (root.value == null) break; // not found
            if (root.value.compareTo(value) == 0) return root;
            if (root.value.compareTo(value) > 0)
                root = root.left;
            else root = root.right;
        }
        return null;
    }
    /**
     * insert a generic value node to a generic RB tree.
     * so you have to implement or rewrite "compareTo" method
     */
    public void insert(T value) {
        // TODO node may contain object
        // node color is black by default, but when insert node set it red
        if (value != null) {
            Node<T> node = new Node<T>(value);
            node.colour = Colour.RED;
            insertNode(node);
        } else throw new IllegalArgumentException("value cannot be null");

    }

    // TODO add equal element
    private void insertNode(Node<T> node) {
        Node<T> root = this.root;
        Node<T> position = null;

        // go to leaf node
        while (root != null) {
            if (root.value == null) break;
            position = root;
            // TODO: rewrite compareTo for different entity
            if (root.value.compareTo(node.value) > 0)
                root = root.left;
            else root = root.right;

        }

        // insert at leaf node or the root is node
        node.parent = position;
        if (position != null) {
            if (node.parent.value.compareTo(node.value) > 0)
                position.left = node;
            else position.right = node;
        }
        else {
            this.root = node;
        }

        insertFix(node);
        // amend root color, which is not necessary
        // if (root != null && root.isRed()) root.setBlack();
    }

    private void insertFix(Node<T> node) {
        Node<T> P, G, U;

        while(node.parent != null) {
            // in first iteration P always exist
            // but not necessary the case
            P = node.parent;
            // only when parent is red we fix the tree
            // case 1 parent is black insertion complete
            if (P.isBlack()) return;

            // case 2
            G = P.parent;
            U = node.uncle();
            // P red and root case 4
            if (G == null) {
                P.setBlack();
                return;
            }

            // else P red and G != null
            // case 5, 6
            int dir = P.getDir(); // the side of parent G on which node P is located
            // case 5
            if (U.isLeaf() || U.isBlack()) {
                // perform double rotation if dir is not the same
                if (node.getDir() == 1 - dir) {
                    rotateDir(P, dir);
                    node = P;
                    P = G.getChild(dir);
                }
            }

            // case 6
            if (P.isRed() && U.isBlack()) {  //&& !U.isLeaf()
                rotateDir(G, 1-dir);
                P.setBlack();
                G.setRed();
                return;
            }

            // case 2 P+U red
            if (U.isRed()) {
                P.setBlack();
                U.setBlack();
                G.setRed();
                node = G; // change current node to G
                continue;
            }

            // case 3 node is root and red
            if (node.parent == null && node.isRed()) {
                node.setBlack();
                return;
            }
        }

    }

    // delete and replace by successor (minimum value in right subtree)
    public void delete(T value) {
        // we don't know its colour
        if (value != null) {
            Node<T> node = new Node<T>(value);
            deleteNode(node);
        } else throw new IllegalArgumentException("value cannot be null");

    }

    private void deleteNode(Node<T> node) {
        // when there is nothing to delete, could have other situation
        if (root == null) throw new IllegalArgumentException("nothing to delete");

        // find node to be deleted
        Node<T> root = this.root;
        while (root != null) {
            if (root.value == null) throw new IllegalArgumentException("nothing to delete");
            if (root.value.compareTo(node.value) == 0) {
                node = root; // node is now the node to delete
                break;
            }
            // TODO: rewrite compareTo for different entity
            if (root.value.compareTo(node.value) > 0)
                root = root.left;
            else root = root.right;
        }
        // node to delete is the root and only the root
        if (this.root.right.isLeaf() && this.root.left.isLeaf() && this.root == root) {
            this.root = null;
            return;
        }

        // delete node with two children, find successor, and exchange the two nodes' positions and color
        // FIXME remove the node
        // after exchange node has at most one non-leaf child
        assert root != null; // I think it is useless FIXME
        if (!root.left.isLeaf() && !root.right.isLeaf()) {
            Node<T> successor = root.getSuccessor();
            // exchange
            this.root.exchange(node, successor);
            node = successor;
        }

        // delete node with one non-leaf children, it must be red node, and it cannot have any children
        // can remove the node
        if (node.isRed()) {
            if (node.getDir() == 0) node.parent.left = new Node<>();
            else node.parent.right = new Node<>();
            return;
        }

        // delete node with exactly one non-leaf children, it must be red child
        if ((!node.left.isLeaf() && node.right.isLeaf()) || (node.left.isLeaf() && !node.right.isLeaf())){
            // delete black node with red child, replace black node with its child and repaint
            if (node.right != null && node.right.isRed()) {
                if (node.parent == null) {
                    node.right.parent = null;
                    this.root = node.right;
                } else {
                    if (node.getDir() == 0) node.parent.left = node.right;
                    else node.parent.right = node.right;
                    node.right.parent = node.parent;
                    node.right.setBlack();
                }
                return;
            }

            if (node.left != null && node.left.isRed()) {
                if (node.parent == null) {
                    node.left.parent = null;
                    this.root = node.left;
                } else {
                    if (node.getDir() == 0) node.parent.left = node.left;
                    else node.parent.right = node.left;
                    node.left.parent = node.parent;
                    node.left.setBlack();
                }
                return;
            }
        }

        // node to delete is a black non-root leaf
        int dir = node.getDir();
        if (dir == 0) {
            node.parent.left = new Node<>();
            node.parent.left.parent = node.parent;
        }
        else {
            node.parent.right = new Node<>();
            node.parent.right.parent = node.parent;
        }

        // delete iteration
        while(node.parent != null) {
            Node<T> P = node.parent;
            Node<T> S = node.parent.getChild(1- dir); // sibling
            Node<T> D = S.getChild(1 - dir); // distant nephew
            Node<T> C = S.getChild(dir); // close nephew
            // case 3: S red, D, C, P black
            if (S.isRed()) {
                rotateDir(P, dir);
                P.setRed();
                S.setBlack();
                S = C;
                D = S.getChild(1 - dir);
                if (D.isLeaf() || !D.isRed())
                C = S.getChild(dir); // FIXME
            }

            // case 6: S black, D red
            if (!D.isLeaf() && D.isRed()) {
                rotateDir(P, dir);
                S.colour = P.colour;
                P.setBlack();
                D.setBlack();
                return;
            }

            // case 5: S+D black, C red, PASSED test
            if (!C.isLeaf() && C.isRed()) {
                rotateDir(S,  1 - dir);
                S.setRed();
                C.setBlack();
                D = S;
                S = C;
            }

            // case 4: C+D is leaf(first iteration) or black
            if (P.isRed() && D.isBlack()) {
                S.setRed();
                P.setBlack();
                return;
            }

            // case 1: P+C+D+S all black
            // this is not likely to happen
            if (P.isBlack() && C.isBlack() && D.isBlack() && S.isBlack()) {
                S.setRed();
                node = P; // new node maybe root
            }

            // case 2: P is null deletion complete
            if (node.parent == null) return;

            dir = node.getDir(); // FIXME
        }

    }

    private enum Colour {
        RED, BLACK
    }

    public static class Node<E extends Comparable<E>> {
        private Colour colour;
        private E value;
        private Node<E> parent; // null if root
        public Node<E> left, right;

        // Leaf node
        public Node() {
            this.value = null;
            this.colour = Colour.BLACK;
        }

        public Node(E value) {
            this.value = value;

            // new created node is black by default
            this.colour = Colour.BLACK;
            this.parent = null;

            // create leaf nodes
            this.left = new Node<E>();
            this.right = new Node<E>();
            this.left.parent = this;
            this.right.parent = this;
        }

        public Node(E value, Node<E> left, Node<E> right) {
            this.value = value;
            this.colour = Colour.BLACK;
            this.parent = null;

            // Initialise children or create leaf node
            if (left == null) this.left = new Node<E>();
            else this.left = left;
            if (right == null) this.right = new Node<>();
            else this.right = right;
            this.left.parent = this;
            this.right.parent = this;
        }

//        public void repaint() {
//            if (this.colour == Colour.RED)
//                this.colour = Colour.BLACK;
//            else
//                this.colour = Colour.RED;
//        }

        private void setBlack() {
            this.colour = Colour.BLACK;
        }

        private void setRed() {
            this.colour = Colour.RED;
        }

        public Node<E> getParent() {
            return parent;
        }

        public E getValue() { return value; }

        private boolean isRed() {
            return colour == Colour.RED;
        }

        private boolean isBlack() {
            return colour == Colour.BLACK;
        }

        private boolean isLeaf() {
            return this.left == null && this.right == null;
        }

        /**
         * @return 0 for node is parent's left child position, 1 for right child position
         */
        public int getDir() {
            if (parent.left == this) return 0;
            else return 1;
        }

        private Node<E> getChild(int dir) {
            if (dir == 0) return this.left;
            else if (dir == 1) return this.right;
            else throw new IllegalArgumentException("dir has to be 0 or 1");
        }

        private Node<E> uncle() {
            Node<E> G = parent.parent;
            if (G != null) {
                if (G.left == parent) return G.right;
                else return G.left;
            } else return null;
        }

        /**
         * @return successor new node if node has right child, not considering other condition as in the lecture
         * because it is useless
         */
        public Node<E> getSuccessor() {
            if (!this.right.isLeaf()) return minimum(this.right);
            return null;
        }

        private Node<E> minimum(Node<E> node) {
            while(!node.left.isLeaf()) {
                node = node.left;
            }
            return node;
        }


        /**
         * exchange two nodes colour, children and parent index not changing value and parent pointer, N1 could be root
         */
        public void exchange(Node<E> N1, Node<E> N2) {
            Node<E> N3 = new Node<>(N1.value) ;
//            N3.parent = N1.parent;

            N1.value = N2.value;
//            N1.colour = N2.colour;
//            N1.left = N2.left;
//            N1.right = N2.right;
//            N1.parent = N2.parent;
//            // N2.parent could be N1
//            if (N2.parent != null) {
//                if (N2.getDir() == 0) N2.parent.left = N1;
//                else N2.parent.right = N1;
//            }

            N2.value = N3.value;
//            N2.colour = N3.colour;
//            N2.left = N3.left;
//            N2.right = N3.right;
//            N2.parent = N3.parent;
//            if (N3.parent != null) {
//                if (N3.getDir() == 0) N1.parent.left = N2;
//                else N2.parent.right = N1;
//            }
//            if (root == N1) root = N2;
//            return root;
        }

    }

    /* test method, warning: never use it in a large tree */
    /**
     * prop 1, but I think the root node can be red
     */

    /**
     * prop 2
     */
    public boolean testProp2() {
        return recursive_colour_checker(root);
    }

    /** helper method for prop 2 */
    public boolean recursive_colour_checker(Node<T> node) {
        boolean checker = true;
        if (node.colour == Colour.RED) {
            if (node.left != null) {
                if (node.left.colour == Colour.RED)
                    return false;
            }
            if (node.right != null) {
                if (node.right.colour == Colour.RED)
                    return false;
            }
        }

        if (node.left != null) {
            checker = recursive_colour_checker(node.left);
            if (!checker) return false;
        }
        if (node.right != null) {
            checker = recursive_colour_checker(node.right);
        }
        return checker;
    }

    /**
     * prop 3
     */
    public boolean testProp3() {
        // START YOUR CODE
        int depth = recursive_left_path_length(root, 0);
        // System.out.println(depth);
        return recursive_path_checker(root, depth, 0);
    }

    /** helper method for prop 3 */
    public int recursive_left_path_length(Node<T> node, int depth) {
        // depth should be 0 at first
        if (node.colour == Colour.BLACK)
            depth += 1;
        if (node.value == null) {
            return depth;
        } else  {
            if (node.left != null) {
                depth = recursive_left_path_length(node.left, depth);
            }
        }
        return depth;
    }

    /** helper method for prop 3 */
    public boolean recursive_path_checker(Node<T> node, int defaultDepth, int depth) {
        // depth should be one of the path length
        if (node.colour == Colour.BLACK) {
            depth += 1;
        }
        // stop at leaf node
        if (node.value == null) {
            return defaultDepth == depth;
        }
        if (node.left != null) {
            if (!recursive_path_checker(node.left, defaultDepth, depth))
                return false;
        }
        if (node.right != null) {
            if (!recursive_path_checker(node.right, defaultDepth, depth))
                return false;
        }
        return true;
    }
}
