import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// This class represents an undirected graph
public class Graph implements GraphADT {
    private Map<Integer, List<GraphEdge>> adjacencyList; // Maps node IDs to lists of edges
    private Map<Integer, GraphNode> nodes; // Maps node IDs to GraphNode instances

    // Creates an empty graph with n nodes and no edge
    public Graph(int n) {
        nodes = new HashMap<>();
        adjacencyList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            GraphNode node = new GraphNode(i);
            nodes.put(i, node);
            adjacencyList.put(i, new ArrayList<>());
        }
    }

    // Adds to the graph an edge connecting nodes u and v
    @Override
    public void insertEdge(GraphNode u, GraphNode v, int type, String label) throws GraphException {
        if (!nodes.containsKey(u.getName()) || !nodes.containsKey(v.getName())) {
            throw new GraphException("One or both nodes do not exist.");
        }

        // Check if the edge already exists
        for (GraphEdge edge : adjacencyList.get(u.getName())) {
            if (edge.secondEndpoint().equals(v)) {
                throw new GraphException("Edge already exists between these nodes.");
            }
        }

        GraphEdge edge = new GraphEdge(u, v, type, label);
        adjacencyList.get(u.getName()).add(edge);
        adjacencyList.get(v.getName()).add(new GraphEdge(v, u, type, label)); // Undirected graph, mirror edge
    }

    // Returns the node with the specified name
    @Override
    public GraphNode getNode(int name) throws GraphException {
        if (!nodes.containsKey(name)) {
            throw new GraphException("Node does not exist.");
        }
        return nodes.get(name);
    }

    // Returns a Java Iterator storing all the edges incident on node u
    @Override
    public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException {
        if (!adjacencyList.containsKey(u.getName())) {
            throw new GraphException("Node does not exist.");
        }
        List<GraphEdge> edges = adjacencyList.get(u.getName());
        return edges.isEmpty() ? null : edges.iterator();
    }

    // Returns the edge connecting nodes u and v
    @Override
    public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
        if (!adjacencyList.containsKey(u.getName()) || !adjacencyList.containsKey(v.getName())) {
            throw new GraphException("One or both nodes do not exist.");
        }

        for (GraphEdge edge : adjacencyList.get(u.getName())) {
            if (edge.secondEndpoint().equals(v)) {
                return edge;
            }
        }
        throw new GraphException("Edge does not exist between the specified nodes.");
    }

    // Returns true if nodes u and v are adjacent; returns false otherwise
    @Override
    public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
        if (!adjacencyList.containsKey(u.getName()) || !adjacencyList.containsKey(v.getName())) {
            throw new GraphException("One or both nodes do not exist.");
        }

        for (GraphEdge edge : adjacencyList.get(u.getName())) {
            if (edge.secondEndpoint().equals(v)) {
                return true;
            }
        }
        return false;
    }
}
