public class BinarySearchTree {
    private BSTNode root;

    // Constructor for BinarySearchTree
    public BinarySearchTree() {
        this.root = null;
    }

    // Returns the root of the tree
    public BSTNode getRoot() {
        return root;
    }

    // Searches for a node with key k in the subtree rooted at r
    public BSTNode get(BSTNode r, Key k) {
        if (r == null || r.getRecord() == null) {
            return null;
        }
        int comparison = k.compareTo(r.getRecord().getKey());
        if (comparison == 0) {
            return r;
        } else if (comparison < 0) {
            return get(r.getLeftChild(), k);
        } else {
            return get(r.getRightChild(), k);
        }
    }

    // Inserts a record in the tree rooted at r
    public void insert(BSTNode r, Record d) throws DictionaryException {
        if (root == null) {
            root = new BSTNode(d);
            return;
        }
        int comparison = d.getKey().compareTo(r.getRecord().getKey());
        if (comparison == 0) {
            throw new DictionaryException("Duplicate key!");
        } else if (comparison < 0) {
            if (r.getLeftChild() == null) {
                BSTNode newNode = new BSTNode(d);
                r.setLeftChild(newNode);
                newNode.setParent(r);
            } else {
                insert(r.getLeftChild(), d);
            }
        } else {
            if (r.getRightChild() == null) {
                BSTNode newNode = new BSTNode(d);
                r.setRightChild(newNode);
                newNode.setParent(r);
            } else {
                insert(r.getRightChild(), d);
            }
        }
    }

    // Removes a node with the given key
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

    // Finds the successor node
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

    // Finds the predecessor node
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

    // Finds the smallest node in the subtree
    public BSTNode smallest(BSTNode r) {
        if (r == null) return null;
        while (r.getLeftChild() != null) {
            r = r.getLeftChild();
        }
        return r;
    }

    // Finds the largest node in the subtree
    public BSTNode largest(BSTNode r) {
        if (r == null) return null;
        while (r.getRightChild() != null) {
            r = r.getRightChild();
        }
        return r;
    }
}
