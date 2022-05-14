import java.util.*;

public class Graph {
	private Map<Integer, List<GraphEdge>> storage = new HashMap<>();
	
	public Map<Integer, List<GraphEdge>> getStorage() {
		return storage;
	}

	public void addVertex(int value) {
		this.storage.put(value, new LinkedList<GraphEdge>());
	}

	// keeping bidirectional weights the same
	public void addEdge(int first, int second, int weight, boolean bidirectional) throws Exception {
		if (first == second) {
			throw new Exception("no duplicates");
		}
		
		var existingConnections = this.storage.get(first);
		if (existingConnections != null && existingConnections.size() > 0) {
			for (GraphEdge w : existingConnections) {
				if (w.getConnectedTo() == second) {
					throw new Exception("connection already exists");
				}
			}
			
			if (existingConnections.size() > 5) {
				throw new Exception("no more than 5 connections");
			}
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
			existingConnections = this.storage.get(second);
			if (existingConnections != null && existingConnections.size() > 0) {
				for (GraphEdge w : existingConnections) {
					if (w.getConnectedTo() == first) {
						throw new Exception("connection already exists");
					}
				}
				
				if (existingConnections.size() > 5) {
					throw new Exception("no more than 5 connections");
				}
			}
			
			graphEdge = new GraphEdge();
			graphEdge.setWeight(weight);
			graphEdge.setConnectedTo(first);
			this.storage.get(second).add(graphEdge);
		}
	}

	public int getNumberOfVertices() {
		return this.storage.keySet().size();
	}

	// counting a directional edge as two edges - might not always be
	// terminologically correct
	public int getNumberOfEdges() {
		int count = 0;
		for (int item : this.storage.keySet()) {
			count += this.storage.get(item).size();
		}
		return count;
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
	
	public int getFirstVertex() {
		int counter = 0;
		for (Integer item : this.storage.keySet()) {
			if (counter == 0) {
				return item;
			}
			counter++;
		}
		return 0;
	}
	
	public int getSecondVertex() {
		int counter = 0;
		for (Integer item : this.storage.keySet()) {
			if (counter == 1) {
				return item;
			}
			counter++;
		}
		return 0;
	}
	
	public void breadthFirstSearch(int start, int end) {
		var successMessage = "Success: ";
		var shortestLength = "The length of the shortest path to the found node: ";
		var numberOfNodes = "The total number of nodes examined during the search: ";
	}
	
	public void depthFirstSearch(int start, int end) {
		var toVisit = new Stack<Integer>();
		var visited = new LinkedList<Integer>();
		
		toVisit.push(start);
		int visitingFromStack, visitingFromChildren;
		
		while (!toVisit.isEmpty()) {
			visitingFromStack = toVisit.pop();
			
			visited.add(visitingFromStack);
			if (visitingFromStack == end) {
				break;
			}
			
			var descendents = this.storage.get(visitingFromStack);
			if (descendents != null) {
				for (GraphEdge w : descendents) {
					visitingFromChildren = w.getConnectedTo();
					visited.add(visitingFromChildren);
					if (visitingFromChildren == end) {
						break;
					}
				}
			}			
		}
		
		var path = "Path: " + visited.toString();
		System.out.println(path);
		
//		var successMessage = "Success: ";
//		var shortestLength = "The length of the shortest path to the found node: ";
//		var path = "Path: " + visited.toString();
//		var numberOfNodes = "The total number of nodes examined during the search: ";
	}
	
	private void depthFirstVisit() {
		
	}
	
	public void dijkstraSearchSearch(int start, int end) {
		var successMessage = "Success: ";
		var shortestLength = "The length of the shortest path to the found node: ";
		var numberOfNodes = "The total number of nodes examined during the search: ";
	}
}