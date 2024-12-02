import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

// Represents an undirected graph
public class Graph implements GraphADT {
    private Map<GraphNode, List<GraphEdge>> adjacencyList;	// Adjacency list for the graph

	// Creates an empty graph with n nodes and no edge
    public Graph(int n) {
        adjacencyList = new HashMap<>();
        for (int i = 0; i < n; i++) {
            GraphNode node = new GraphNode(i);
            adjacencyList.put(node, new ArrayList<>());
        }
    }

	// Adds to the graph an edge connecting nodes u and v
    @Override
    public void insertEdge(GraphNode u, GraphNode v, int type, String label) throws GraphException {
        if (!adjacencyList.containsKey(u) || !adjacencyList.containsKey(v)) {
            throw new GraphException("One or both nodes do not exist.");
        }

        Iterator<GraphEdge> iterator = adjacencyList.get(u).iterator();
        while (iterator.hasNext()) {
            GraphEdge edge = iterator.next();
            if (edge.secondEndpoint().equals(v)) {
                throw new GraphException("Edge already exists between these nodes.");
            }
        }

        GraphEdge edge = new GraphEdge(u, v, type, label);

        adjacencyList.get(u).add(edge);
        adjacencyList.get(v).add(edge);
    }

	// Returns the node with the specified name
    @Override
    public GraphNode getNode(int name) throws GraphException {
        Iterator<GraphNode> iterator = adjacencyList.keySet().iterator();
        while (iterator.hasNext()) {
            GraphNode node = iterator.next();
            if (node.getName() == name) {
                return node;
            }
        }
        throw new GraphException("Node does not exist.");
    }

	// Returns a Java Iterator storing all the edges incident on node u and v
    @Override
    public Iterator<GraphEdge> incidentEdges(GraphNode u) throws GraphException {
        if (!adjacencyList.containsKey(u)) {
            throw new GraphException("Node does not exist.");
        }
        List<GraphEdge> edges = adjacencyList.get(u);
        return edges.isEmpty() ? null : edges.iterator();
    }

	// Returns the edge connecting nodes u and v
    @Override
    public GraphEdge getEdge(GraphNode u, GraphNode v) throws GraphException {
        if (!adjacencyList.containsKey(u) || !adjacencyList.containsKey(v)) {
            throw new GraphException("One or both nodes do not exist.");
        }

        Iterator<GraphEdge> iterator = adjacencyList.get(u).iterator();
        while (iterator.hasNext()) {
            GraphEdge edge = iterator.next();
            if (edge.secondEndpoint().equals(v)) {
                return edge;
            }
        }
        throw new GraphException("Edge does not exist between the specified nodes.");
    }

	// Returns true if nodes u and v are adjacent; returns false otherwise
    @Override
    public boolean areAdjacent(GraphNode u, GraphNode v) throws GraphException {
        if (!adjacencyList.containsKey(u) || !adjacencyList.containsKey(v)) {
            throw new GraphException("One or both nodes do not exist.");
        }

        Iterator<GraphEdge> iterator = adjacencyList.get(u).iterator();
        while (iterator.hasNext()) {
            GraphEdge edge = iterator.next();
            if (edge.secondEndpoint().equals(v)) {
                return true;
            }
        }
        return false;
    }
}
