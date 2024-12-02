import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Stack;

public class Maze {
    private Graph graph;
    private GraphNode entrance;
    private GraphNode exit;
    private int coins;
    private List<GraphNode> path;

    public Maze(String inputFile) throws MazeException {
        try (BufferedReader inputReader = new BufferedReader(new FileReader(inputFile))) {
            readInput(inputReader);
        } catch (IOException | GraphException e) {
            throw new MazeException("Error reading input file", e);
        }
    }

    public Graph getGraph() {
        if (graph == null) {
            throw new MazeException("Graph is null");
        }
        return graph;
    }

    public Iterator<GraphNode> solve() {
        path = new ArrayList<>();
        try {
            if (DFS(entrance, coins)) {
                return path.iterator();
            }
        } catch (GraphException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean DFS(GraphNode node, int remainingCoins) throws GraphException {
        if (node.equals(exit)) {
            path.add(node);
            return true;
        }

        node.mark(true);
        path.add(node);

        Iterator<GraphEdge> edges = graph.incidentEdges(node);
        while (edges != null && edges.hasNext()) {
            GraphEdge edge = edges.next();
            GraphNode neighbor = edge.secondEndpoint();

            if (!neighbor.isMarked()) {
                int coinsNeeded = edge.getType();
                if (remainingCoins >= coinsNeeded) {
                    if (DFS(neighbor, remainingCoins - coinsNeeded)) {
                        return true;
                    }
                }
            }
        }

        path.remove(path.size() - 1);
        node.mark(false);
        return false;
    }

    private void readInput(BufferedReader inputReader) throws IOException, GraphException {
        int S = Integer.parseInt(inputReader.readLine().trim());
        int A = Integer.parseInt(inputReader.readLine().trim());
        int L = Integer.parseInt(inputReader.readLine().trim());
        coins = Integer.parseInt(inputReader.readLine().trim());

        int totalRooms = A * L;
        graph = new Graph(totalRooms);

        for (int i = 0; i < L; i++) {
            String roomLine = inputReader.readLine().trim();
            String corridorLine = inputReader.readLine().trim();

            for (int j = 0; j < A; j++) {
                char roomChar = roomLine.charAt(j * 2);
                if (roomChar == 's') {
                    entrance = graph.getNode(i * A + j);
                } else if (roomChar == 'x') {
                    exit = graph.getNode(i * A + j);
                }

                if (j < A - 1) {
                    char horizontalChar = roomLine.charAt(j * 2 + 1);
                    if (horizontalChar != 'w') {
                        int type = Character.isDigit(horizontalChar) ? Character.getNumericValue(horizontalChar) : 0;
                        graph.insertEdge(graph.getNode(i * A + j), graph.getNode(i * A + j + 1), type, horizontalChar == 'c' ? "corridor" : "door");
                    }
                }

                if (i < L - 1) {
                    char verticalChar = corridorLine.charAt(j * 2);
                    if (verticalChar != 'w') {
                        int type = Character.isDigit(verticalChar) ? Character.getNumericValue(verticalChar) : 0;
                        graph.insertEdge(graph.getNode(i * A + j), graph.getNode((i + 1) * A + j), type, verticalChar == 'c' ? "corridor" : "door");
                    }
                }
            }
        }
    }
}