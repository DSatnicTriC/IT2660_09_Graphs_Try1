import java.util.*;

public class Graph {
	private Map<Integer, List<GraphEdge>> storage = new HashMap<>();
	
	public void addVertex(int value)
    {
		this.storage.put(value, new LinkedList<GraphEdge>());
    }

	// keeping bidirectional weights the same
	public void addEdge(int first, int second, int weight, boolean bidirectional) {

		if (!this.storage.containsKey(first))
			addVertex(first);

		if (!this.storage.containsKey(second))
			addVertex(second);

		var graphEdge = new GraphEdge();
		graphEdge.setWeight(weight);
		graphEdge.setConnectedTo(second);

		this.storage.get(first).add(graphEdge);
		if (bidirectional == true) {
			graphEdge.setConnectedTo(first);
			this.storage.get(second).add(graphEdge);
		}
	}
}