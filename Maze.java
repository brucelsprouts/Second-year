import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

// This class represents the maze
public class Maze {
    private Graph graph;                // The graph representing the maze
    private int entrance;               // The ID of the starting node
    private int exit;                   // The ID of the ending node
    private int coins;                  // The number of coins     
    private List<GraphNode> path;       // The path from the entrance to the exit

    // Constructor that reads the input file and builds the graph representing the maze
    public Maze(String inputFile) throws MazeException {
        try (BufferedReader br = new BufferedReader(new FileReader(inputFile))) {
            readInput(br);
        } catch (Exception e) {
            throw new MazeException("Invalid maze");
        }
    }

    // Returns a reference to the Graph object representing the maze
    public Graph getGraph() {
        return graph;
    }

    // Returns a java Iterator containing the nodes of the path from the entrance to the exit of the maze
    public Iterator<GraphNode> solve() {
        path = new ArrayList<>();
        try {
            return DFS(coins, graph.getNode(entrance));
        } catch (GraphException e) {
            return null;
        }
    }

    // Perform a DFS of the graph
    private Iterator<GraphNode> DFS(int k, GraphNode node) throws GraphException {
        path.add(node);
        node.mark(true);
    
        if (node.getName() == exit) {
            return path.iterator(); // Exit condition, path found
        }
    
        // Iterate over all incident edges
        Iterator<GraphEdge> edges = graph.incidentEdges(node);
        while (edges.hasNext()) {
            GraphEdge edge = edges.next();
            if (edge.getType() <= k && !edge.secondEndpoint().isMarked()) {
                Iterator<GraphNode> result = DFS(k - edge.getType(), edge.secondEndpoint());
                if (result != null) {
                    return result; // Path found in recursion
                }
            }
        }
    
        // Backtrack if no path is found
        path.remove(node);
        node.mark(false);
        return null;
    }
    

    // Read the values S, A, L, and k   
    private void readInput(BufferedReader inputReader) throws IOException, GraphException {
        inputReader.readLine(); // Skip first line

        int A = Integer.parseInt(inputReader.readLine());
        int L = Integer.parseInt(inputReader.readLine());
        graph = new Graph(A * L);
        coins = Integer.parseInt(inputReader.readLine());
        List<String> graphStrList = new ArrayList<>();
        String line;
    
        // Read all remaining lines into a list
        while ((line = inputReader.readLine()) != null) {
            graphStrList.add(line);
        }
    
        // Process each node and its edges
        for (int i = 0; i < L + L - 1; i += 2) {
            for (int j = 0; j < A + A - 1; j += 2) {
                int nodeName1 = (i / 2) * A + (j / 2);
                char v1 = graphStrList.get(i).charAt(j);
    
                if (v1 == 's') entrance = nodeName1;
                if (v1 == 'x') exit = nodeName1;
    
                // Check and add edge to the left
                if (j > 0) {
                    int nodeName2 = (i / 2) * A + (j / 2) - 1;
                    char c = graphStrList.get(i).charAt(j - 1);
                    if (c != 'w') {
                        insertEdge(nodeName1, nodeName2, c != 'c' ? Character.getNumericValue(c) : 0, c != 'c' ? "door" : "corridor");
                    }
                }
    
                // Check and add edge to the top
                if (i > 0) {
                    int nodeName3 = ((i / 2) - 1) * A + (j / 2); // Inline getNodeIndex for top neighbor
                    char c = graphStrList.get(i - 1).charAt(j);
                    if (c != 'w') {
                        insertEdge(nodeName1, nodeName3, c != 'c' ? Character.getNumericValue(c) : 0, c != 'c' ? "door" : "corridor");
                    }
                }
            }
        }
    }
    
    // Select the nodes and insert the appropriate edge
    private void insertEdge(int node1, int node2, int linkType, String label) throws GraphException {
        GraphNode u = graph.getNode(node1); // Retrieve the first node
        GraphNode v = graph.getNode(node2); // Retrieve the second node
        
        // Insert the edge with the given type and label
        graph.insertEdge(u, v, linkType, label);
     }
}