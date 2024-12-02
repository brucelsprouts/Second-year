// Represents a node of the graph
public class GraphNode {
    private int name;			// Name of the node
    private boolean marked;		// Boolean to check if the node is marked or not

	// Constructor for the class
    public GraphNode(int name) {
        this.name = name;
        this.marked = false;
    }

	// Marks the node with the specified value
    public void mark(boolean mark) {
        this.marked = mark;
    }

	// Returns the value with which the node has been marked
    public boolean isMarked() {
        return marked;
    }

	// Returns the name of the node
    public int getName() {
        return name;
    }
}
