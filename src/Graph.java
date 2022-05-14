import java.util.*;

public class Graph {
	private Map<Integer, List<GraphEdge>> storage = new HashMap<>();

	public void addVertex(int value) {
		this.storage.put(value, new LinkedList<GraphEdge>());
	}

	// keeping bidirectional weights the same
	public void addEdge(int first, int second, int weight, boolean bidirectional) throws Exception {
		if (first == second) {
			throw new Exception("no duplicates");
		}

		if (!this.storage.containsKey(first))
			addVertex(first);

		if (!this.storage.containsKey(second))
			addVertex(second);

		var graphEdge = new GraphEdge();
		graphEdge.setWeight(weight);
		graphEdge.setConnectedTo(second);

		this.storage.get(first).add(graphEdge);
		if (bidirectional == true) {
			graphEdge = new GraphEdge();
			graphEdge.setWeight(weight);
			graphEdge.setConnectedTo(first);
			this.storage.get(second).add(graphEdge);
		}
	}

	public void getNumberOfVertices() {
		System.out.println("Number of vertices: " + this.storage.keySet().size());
	}

	// counting a directional edge as two edges - might not always be
	// terminologically correct
	public void getNumberOfEdges() {
		int count = 0;
		for (int item : this.storage.keySet()) {
			count += this.storage.get(item).size();
		}

		System.out.println("Number of edges: " + count);
	}

	public void hasVertex(int vertex) {
		if (this.storage.containsKey(vertex)) {
			System.out.println("Yes, this is a vertex: " + vertex);
		} else {
			System.out.println("No, this is not a vertex: " + vertex);
		}
	}

	// print the graph
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();

		for (Integer item : this.storage.keySet()) {
			builder.append(item.toString() + ": ");
			for (GraphEdge w : this.storage.get(item)) {
				builder.append(w.getConnectedTo() + "(" + w.getWeight() + ")" + " ");
			}
			builder.append("\n");
		}

		return (builder.toString());
	}
}