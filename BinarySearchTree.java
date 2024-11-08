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

    public void insert(BSTNode r, Record d) throws DictionaryException {
        if (r == null) {
            // If the tree is empty, create a new root node
            this.root = new BSTNode(d);
        } else {
            Key nodeKey = r.getRecord().getKey();
            Key newKey = d.getKey();
            
            if (nodeKey.compareTo(newKey) == 0) {
                throw new DictionaryException("Key already exists in tree.");
            } else if (newKey.compareTo(nodeKey) < 0) {
                // Insert into the left subtree
                if (r.getLeftChild() == null) {
                    BSTNode newNode = new BSTNode(d);
                    r.setLeftChild(newNode);
                    newNode.setParent(r);
                } else {
                    insert(r.getLeftChild(), d);
                }
            } else {
                // Insert into the right subtree
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
        if r is n
    }

    // Returns the node storing the successor of the given key in the tree with root r; returns null if the successor does not exist.
    public BSTNode successor(BSTNode r, Key k) {
        
        return null;
    }

    // Returns the node storing the predecessor of the given key in the tree with root r; returns null if the predecessor does not exist.
    public BSTNode predecessor(BSTNode r, Key k) {
        
        return null;
    }

    // Returns the node with the smallest key in tree with root r.
    public BSTNode smallest(BSTNode r) {
        
        return null;
    }

    // Returns the node with the largest key in tree with root r.
    public BSTNode largest(BSTNode r) {
        
        return null;
    }
}