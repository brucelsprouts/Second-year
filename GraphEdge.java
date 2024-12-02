// Represents an edge of the graph
public class GraphEdge {
    private GraphNode u;		// First endpoint of the edge
    private GraphNode v;		// Second endpoint of the edge
    private int type;			// Type of the edge
    private String label;		// Label of the edge

	// Constructor for the class
    public GraphEdge(GraphNode u, GraphNode v, int type, String label) {
        this.u = u;
        this.v = v;
        this.type = type;
        this.label = label;
    }

	// Returns the first endpoint of the edge
    public GraphNode firstEndpoint() {
        return u;
    }

	// Returns the second endpoint of the edge
    public GraphNode secondEndpoint() {
        return v;
    }

	// Returns the type of the edge
    public int getType() {
        return type;
    }

	// Sets the type of the edge to the specified value
    public void setType(int newType) {
        this.type = newType;
    }

	// Returns the label of the edge
    public String getLabel() {
        return label;
    }

	// Sets the label of the edge to the specified value
    public void setLabel(String newLabel) {
        this.label = newLabel;
    }
}
