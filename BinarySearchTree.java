// Represents a binary search tree
public class BinarySearchTree {
    private BSTNode root;               // Root of the binary search tree.

    // Constructor that creates a leaf node as root of tree.
    public BinarySearchTree() {
        this.root = null;
    }

    // Returns the root node of this binary search tree.
    public BSTNode getRoot() {
        return this.root;
    }

    // Returns node storing the key; returns null if the key is not stored in tree with root r.
    public BSTNode get(BSTNode r, Key k) {
        if (r == null || r.isLeaf()) {
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
                throw new DictionaryException();
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
        // Find the node to be removed
        BSTNode nodeToRemove = findNode(r, k);
        if (nodeToRemove == null) {
            throw new DictionaryException();
        }

        // Node to be removed has no children.
        if (nodeToRemove.isLeaf()) {
            if (nodeToRemove == r) {        // Tree had only one node.
                root = null;                
            } else {                        // Remove the node and update the parent's reference.
                if (nodeToRemove.getParent().getLeftChild() == nodeToRemove) {
                    nodeToRemove.getParent().setLeftChild(null);
                } else {
                    nodeToRemove.getParent().setRightChild(null);
                }
            }
            return;
        }

        // Node to be removed has one child
        if (nodeToRemove.getLeftChild() == null || nodeToRemove.getRightChild() == null) {
            BSTNode child;
            if (nodeToRemove.getLeftChild() != null) {
                child = nodeToRemove.getLeftChild();
            } else {
                child = nodeToRemove.getRightChild();
            }
            if (nodeToRemove == r) {        // Root node is replaced by its child
                root = child; 
            } else {
                if (nodeToRemove.getParent().getLeftChild() == nodeToRemove) {
                    nodeToRemove.getParent().setLeftChild(child);
                } else {
                    nodeToRemove.getParent().setRightChild(child);
                }
                child.setParent(nodeToRemove.getParent());
            }
            return;
        }

        // Node to be removed has two children
        BSTNode successor = successor(r,nodeToRemove.getRecord().getKey());
        nodeToRemove.setRecord(successor.getRecord());
        remove(successor, successor.getRecord().getKey());
    }

    // Returns the node storing the successor of the given key in the tree with root r; returns null if the successor does not exist.
    public BSTNode successor(BSTNode r, Key k) {
        BSTNode current = findNode(r, k);       // Find the node with the given key.
        if (current == null) {
            return null;
        }
    
        if (current.getRightChild() != null) {  // Check for right subtree.
            return smallest(current.getRightChild());
        }
    
        // Find the lowest ancestor.
        BSTNode successor = null;
        BSTNode ancestor = r;
        while (ancestor != current) {
            if (k.compareTo(ancestor.getRecord().getKey()) < 0) {
                successor = ancestor;
                ancestor = ancestor.getLeftChild();
            } else {
                ancestor = ancestor.getRightChild();
            }
        }
        return successor;
    }

    // Returns the node storing the predecessor of the given key in the tree with root r; returns null if the predecessor does not exist.
    public BSTNode predecessor(BSTNode r, Key k) {
        BSTNode current = findNode(r, k);       // Find the node with the given key.
        if (current == null) {
            return null;
        }
    
        if (current.getLeftChild() != null) {  // Check for left subtree.
            return largest(current.getLeftChild());
        }
    
        // Find the highest ancestor.
        BSTNode predecessor = null;
        BSTNode ancestor = r;
        while (ancestor != current) {
            if (k.compareTo(ancestor.getRecord().getKey()) > 0) {
                predecessor = ancestor;
                ancestor = ancestor.getRightChild();
            } else {
                ancestor = ancestor.getLeftChild();
            }
        }
        return predecessor;
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
    private BSTNode smallest(BSTNode r) {
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