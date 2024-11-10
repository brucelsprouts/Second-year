// Represents a node of the binary search tree
public class BSTNode {
    Record item;                        // Record object stored in the node.
    private BSTNode leftChild;          // Left child of the node.
    private BSTNode rightChild;         // Right child of the node.
    private BSTNode parent;             // Parent of the node.

    // Constructor for the class.
    public BSTNode(Record item) {
        this.item = item;
        this.leftChild = null;
        this.rightChild = null;
        this.parent = null;
    }

    // Returns the Record object stored in this node.
    public Record getRecord() {
        return this.item;
    }

    // Stores the given record in this node.
    public void setRecord(Record d) {
        this.item = d;
    }

    // Returns the left child.
    public BSTNode getLeftChild() {
        return this.leftChild;
    }

    // Returns the right child.
    public BSTNode getRightChild() {
        return this.rightChild;
    }

    // Returns the parent.
    public BSTNode getParent() {
        return this.parent;
    }

    // Sets the left child to the specified value.
    public void setLeftChild(BSTNode u) {
        this.leftChild = u;
    }

    // Sets the right child to the specified value.
    public void setRightChild(BSTNode u) {
        this.rightChild = u;
    }

    // Sets the parent to the specified value.
    public void setParent(BSTNode u) {
        this.parent = u;
    }

    // Returns true if this node is a leaf; false otherwise.
    public boolean isLeaf() {
        if (this.leftChild == null && this.rightChild == null) {
            return true;
        } else {
            return false;
        }
    }
    
}