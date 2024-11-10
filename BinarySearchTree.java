// Represents a binary search tree
public class BinarySearchTree {
    private BSTNode root;               // Root of the binary search tree.

    // Constructor that creates a leaf node as root of tree.
    public BinarySearchTree() {
        this.root = null;
    }

    // Returns the root node of this binary search tree.
    public BSTNode getRoot() {
        return root;
    }

    // Returns node storing the key; returns null if the key is not stored in tree with root r.
    public BSTNode get(BSTNode r, Key k) {
        if (r == null || r.getRecord() == null) {
            return null;
        }
        Key nodeKey = r.getRecord().getKey();
        if (nodeKey.compareTo(k) == 0) {
            return r;
        }
        if (k.compareTo(nodeKey) < 0) {
            return get(r.getLeftChild(), k);
        } else {
            return get(r.getRightChild(), k);
        }
    }

    // Adds the record to the binary search tree with root r.
    public void insert(BSTNode r, Record d) throws DictionaryException {
        if (r == null) {                                // Create a new root node if tree is empty
            this.root = new BSTNode(d);
        } else {
            Key nodeKey = r.getRecord().getKey();
            Key newKey = d.getKey();

            if (nodeKey.compareTo(newKey) == 0) {       // If the key is already in the tree
                throw new DictionaryException("Record with the same key already exists.");
            } else if (newKey.compareTo(nodeKey) < 0) { // Insert into the left subtree
                if (r.getLeftChild() == null) {
                    BSTNode newNode = new BSTNode(d);
                    r.setLeftChild(newNode);
                    newNode.setParent(r);
                } else {
                    insert(r.getLeftChild(), d);
                }
            } else {                                    // Insert into the right subtree
                if (r.getRightChild() == null) {
                    BSTNode newNode = new BSTNode(d);
                    r.setRightChild(newNode);
                    newNode.setParent(r);
                } else {
                    insert(r.getRightChild(), d);
                }
            }
        }
    }
    
    // Deletes the node with the given key from the tree with root r. Throws a DictionaryException if the tree does not store a record with the given key.
    public void remove(BSTNode r, Key k) throws DictionaryException {
        BSTNode target = get(r, k);
        if (target == null) {
            throw new DictionaryException("Key not found!");
        }
        if (target.isLeaf()) {
            replaceNode(target, null);
        } else if (target.getLeftChild() == null) {
            replaceNode(target, target.getRightChild());
        } else if (target.getRightChild() == null) {
            replaceNode(target, target.getLeftChild());
        } else {
            BSTNode successor = smallest(target.getRightChild());
            target.setRecord(successor.getRecord());
            remove(successor, successor.getRecord().getKey());
        }
    }

    // Helper function to replace nodes
    private void replaceNode(BSTNode oldNode, BSTNode newNode) {
        if (oldNode.getParent() == null) {
            root = newNode;
        } else if (oldNode == oldNode.getParent().getLeftChild()) {
            oldNode.getParent().setLeftChild(newNode);
        } else {
            oldNode.getParent().setRightChild(newNode);
        }
        if (newNode != null) {
            newNode.setParent(oldNode.getParent());
        }
    }

    // Returns the node storing the successor of the given key in the tree with root r; returns null if the successor does not exist.
    public BSTNode successor(BSTNode r, Key k) {
        BSTNode node = get(r, k);
        if (node == null) {
            return null;
        }
        if (node.getRightChild() != null) {
            return smallest(node.getRightChild());
        }
        BSTNode parent = node.getParent();
        while (parent != null && node == parent.getRightChild()) {
            node = parent;
            parent = parent.getParent();
        }
        return parent;
    }

    // Returns the node storing the predecessor of the given key in the tree with root r; returns null if the predecessor does not exist.
    public BSTNode predecessor(BSTNode r, Key k) {
        BSTNode node = get(r, k);
        if (node == null) {
            return null;
        }
        if (node.getLeftChild() != null) {
            return largest(node.getLeftChild());
        }
        BSTNode parent = node.getParent();
        while (parent != null && node == parent.getLeftChild()) {
            node = parent;
            parent = parent.getParent();
        }
        return parent;
    }

    // Returns the node a node in tree.
    private BSTNode findNode(BSTNode r, Key k) {
        if (r == null || r.isLeaf()) {          // Root is null or a leaf.
            return null;
        }
        Key nodeKey = r.getRecord().getKey();
        if (nodeKey.compareTo(k) == 0) {        // Found the node.
            return r;
        } else if (k.compareTo(nodeKey) < 0) {
            return findNode(r.getLeftChild(), k);
        } else {
            return findNode(r.getRightChild(), k);
        }
    }

    // Returns the node with the smallest key in tree with root r.
    public BSTNode smallest(BSTNode r) {
        while (r.getLeftChild() != null) {
            r = r.getLeftChild();
        }
        return r;
    }

    // Returns the node with the largest key in tree with root r.
    public BSTNode largest(BSTNode r) {
        while (r.getRightChild() != null) {
            r = r.getRightChild();
        }
        return r;
    }
}