import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Maze {
	private Graph graph;
	private int start;
	private int end;
	private int coins;
	private List<GraphNode> path;

	public Maze(String inputFile) throws MazeException {
		BufferedReader br;
		try {
			br = new BufferedReader(new FileReader(inputFile));
			this.readInput(br);
		} catch (Exception e) {
			throw new MazeException("Invalid maze");
		}
	}

	public Graph getGraph() {
		return graph;
	}

	public Iterator<GraphNode> solve() {
		path = new ArrayList<>();
		try {
			Iterator<GraphNode> solvePath = DFS(coins, graph.getNode(start));
			return solvePath;
		} catch (GraphException e) {
			return null;
		}
	}

	private Iterator<GraphNode> DFS(int k, GraphNode go) throws GraphException {
		boolean res = DFSHelper(k, go);
		if (res) {
			return path.iterator();
		} else {
			return null;
		}
	}

	private boolean DFSHelper(int k, GraphNode go) throws GraphException{
		path.add(go);
		go.mark(true);
		if (go.getName() == this.end) {
			return true;
		}
		Iterator<GraphEdge> it = graph.incidentEdges(go);
		while (it.hasNext()) {
			GraphEdge e = it.next();
			if (e.getType() <= k && !e.secondEndpoint().isMarked()) {
				//DFS the node
				boolean res =  DFSHelper(k - e.getType(), e.secondEndpoint());
				if (res) {
					return res;
				} 
			}
		}
		path.remove(go);
		go.mark(false);
		return false;
	}

	private void readInput(BufferedReader inputReader) throws IOException, GraphException {
		inputReader.readLine();
		int A = Integer.parseInt(inputReader.readLine());
		int L = Integer.parseInt(inputReader.readLine());
		graph = new Graph(A * L);
		coins = Integer.parseInt(inputReader.readLine());
		List<String> graphStrList = new ArrayList<>();
		String line;
		while( (line = inputReader.readLine()) != null) {
			graphStrList.add(line);
		}
		for (int i = 0; i < L + L - 1; i = i + 2) {
			for (int j = 0; j < A + A - 1; j = j + 2) {
				int nodeName1 = getNodeIndex(i, j, A);
				char v1  = graphStrList.get(i).charAt(j);
				if (v1 == 's') {
					start = nodeName1;
				}
				if (v1 == 'x') {
					end = nodeName1;
				}
				if(j > 0) {
					int nodeName2 = getNodeIndex(i, j - 2, A);
					char c =  graphStrList.get(i).charAt(j - 1);
					if (c != 'w') {
						String label = "corridor";
						int conis = 0;
						if (c != 'c') {
							label = "door";
							conis = Character.getNumericValue(c);
						}
						insertEdge(nodeName1, nodeName2, conis, label);
					}
				}

				if(i > 0) {
					int nodeName3 = getNodeIndex(i - 2, j, A);
					char c =  graphStrList.get(i - 1).charAt(j);
					if (c != 'w') {
						String label = "corridor";
						int conis = 0;
						if (c != 'c') {
							label = "door";
							conis = Character.getNumericValue(c);
						}
						insertEdge(nodeName1, nodeName3, conis, label);
					}
				}

			}
		}
	}

	private int getNodeIndex(int i, int j, int A) {
		int rowsNodeCnt = i / 2 * A;
		int columnNumOffset = j / 2;
		return rowsNodeCnt + columnNumOffset;
	}

	private void insertEdge(int node1, int node2, int linkType, String label) throws GraphException {
		// select the nodes and insert the appropriate edge.
		this.graph.insertEdge(graph.getNode(node1), graph.getNode(node2), linkType, label);
	}
}
