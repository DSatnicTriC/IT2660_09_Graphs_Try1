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
		var toVisit = new LinkedList<Integer>();
		var visited = new LinkedList<Integer>();
		int counterOfTotalNodes = 0;
		boolean success = false;
		
		toVisit.add(start);
		int visitingFromQueue, visitingFromChildren;
		
		while (!toVisit.isEmpty() && success == false) {
			visitingFromQueue = toVisit.remove();
			
			if (visited.contains(visitingFromQueue)) {
				continue;
			}
			visited.add(visitingFromQueue);
			counterOfTotalNodes++;
			if (visitingFromQueue == end) {
				success = true;
				break;
			}
			
			var descendents = this.storage.get(visitingFromQueue);
			if (descendents != null) {
				int childCounter = 0;
				for (GraphEdge w : descendents) {
					if (success) {
						// from inner loop below
						break;
					}
					childCounter++;
					visitingFromChildren = w.getConnectedTo();
					
					counterOfTotalNodes++;
					if (visited.contains(visitingFromChildren)) {
						continue;
					}
					
					visited.add(visitingFromChildren);							
					
					if (visitingFromChildren == end) {
						success = true;
						break;
					}
					
					if (childCounter == 1) {
						var childDescendents = this.storage.get(visitingFromChildren);
						for (GraphEdge y : childDescendents) {
							counterOfTotalNodes++;
							if (visited.contains(y.getConnectedTo())) {
								continue;
							}
							
							visited.add(y.getConnectedTo());							
							
							if (y.getConnectedTo() == end) {
								success = true;
								break;
							}
						}
					}
					else {
						counterOfTotalNodes++;
						toVisit.add(visitingFromChildren);
					}
				}
			}			
		}
		
		var shortestLength = "The length of the shortest path to the found node: " + visited.size();
		var path = "Path: " + visited.toString();
		var numberOfNodes = "The total number of nodes examined during the search: " + counterOfTotalNodes;
		
		System.out.println(numberOfNodes);
		if (success) {
			System.out.println(shortestLength);
			System.out.println(path);
		}
		else {
			System.out.println("Node not found or not linked");
		}
	}
	
	public void depthFirstSearch(int start, int end) {
		var toVisit = new Stack<Integer>();
		var visited = new LinkedList<Integer>();
		int counterOfTotalNodes = 0;
		boolean success = false;
		
		toVisit.push(start);
		int visitingFromStack, visitingFromChildren;
		
		while (!toVisit.isEmpty() && success == false) {
			visitingFromStack = toVisit.pop();
			
			if (visited.contains(visitingFromStack)) {
				continue;
			}
			visited.add(visitingFromStack);
			counterOfTotalNodes++;
			if (visitingFromStack == end) {
				success = true;
				break;
			}
			
			var descendents = this.storage.get(visitingFromStack);
			if (descendents != null) {
				for (GraphEdge w : descendents) {
					visitingFromChildren = w.getConnectedTo();
					if (visited.contains(visitingFromChildren)) {
						continue;
					}
					
					visited.add(visitingFromChildren);
					counterOfTotalNodes++;
					
					if (visitingFromChildren == end) {
						success = true;
						break;
					}
					
					var childDescendents = this.storage.get(visitingFromChildren);
					for (GraphEdge y : childDescendents) {
						toVisit.push(y.getConnectedTo());
						counterOfTotalNodes++;
					}
				}
			}			
		}
		
		var shortestLength = "The length of the shortest path to the found node: " + visited.size();
		var path = "Path: " + visited.toString();
		var numberOfNodes = "The total number of nodes examined during the search: " + counterOfTotalNodes;
		
		System.out.println(numberOfNodes);
		if (success) {
			System.out.println(shortestLength);
			System.out.println(path);
		}
		else {
			System.out.println("Node not found or not linked");
		}
	}
		
	public void dijkstraSearchSearch(int start, int end) {
		var successMessage = "Success: ";
		var shortestLength = "The length of the shortest path to the found node: ";
		var numberOfNodes = "The total number of nodes examined during the search: ";
	}
}