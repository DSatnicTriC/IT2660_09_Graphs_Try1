import java.util.Scanner;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;

public class GraphRunner {

	static Scanner input = new Scanner(System.in);
	static Graph graph = new Graph();
	static Integer menuEntry = 1;

	public static void main(String[] args) {
		loadGraph();

		System.out.println("Graph has been loaded!");
		do {
			InteractWithUser();
		} while (menuEntry != 0);

		input.close();
	}

	private static void loadGraph() {	
		try {
			// breadth testing
//			graph.addEdge(1, 4, 1, false);
//			graph.addEdge(1, 3, 1, false);
//			graph.addEdge(4, 2, 1, false);
			
			// depth testing
//			graph.addEdge(1, 2, 1, false);
//			graph.addEdge(1, 3, 1, false);
//			graph.addEdge(3, 5, 1, false);
		} catch (Exception e) {
			// TODO: handle exception
		}
		int numberOfEdges = 1000;
		int increaseStorageCounter = numberOfEdges * 5;
		int maxValue = 100000;
		int maxWeight = 10;		
		int first, second, weight, graphSize,
		randomToConnectIndex1, randomToConnectIndex2,
		randomToConnectValue1 = 0, randomToConnectValue2 = 0;
		boolean bidirectional;

		Random rand = new Random();

		for (int i = 0; i < numberOfEdges; i++) {
			try {
				first = rand.nextInt(maxValue) + 1;
				second = rand.nextInt(maxValue) + 1;
				weight = rand.nextInt(maxWeight) + 1;
				bidirectional = rand.nextInt(2) == 0 ? false : true;
				graph.addEdge(first, second, weight, bidirectional);
			} catch (Exception e) {
				// try again
				i--;
			}
		}
		
		var storage = graph.getStorage();
		graphSize = storage.size();
		for (int i = 0; i < increaseStorageCounter; i++) {
			randomToConnectIndex1 = rand.nextInt(graphSize) + 1;
			randomToConnectIndex2 = rand.nextInt(graphSize) + 1;
			weight = rand.nextInt(maxWeight) + 1;
			int counter = 1;
			for (Map.Entry<Integer, List<GraphEdge>> w : storage.entrySet()) {
				if (counter == randomToConnectIndex1) {
					randomToConnectValue1 = w.getKey();
					break;
				}
				counter++;
			}
			counter = 1;
			for (Map.Entry<Integer, List<GraphEdge>> w : storage.entrySet()) {
				if (counter == randomToConnectIndex2) {
					randomToConnectValue2 = w.getKey();
					break;
				}
				counter++;
			}
			
			try {
				graph.addEdge(randomToConnectValue1, randomToConnectValue2, weight, false);
			} catch (Exception e) {
				// TODO: handle exception
			}
		}
	}

	private static void displayBasicGraphInfo() {
		System.out.println("Vertices: " + graph.getNumberOfVertices());
		System.out.println("Y edges: " + +graph.getNumberOfVertices());
	}

	private static void displayFullGraph() {
		System.out.println("Full Graph");
		System.out.println(graph.toString());
	}

	private static void displayMenu() {
		System.out.println("**********************");
		System.out.println("What would you like to do?");
		System.out.println("1) Display Basic Graph Info");
		System.out.println("2) Display Full Graph");
		System.out.println("3) Breadth-First Search");
		System.out.println("4) Depth-First Search");
		System.out.println("5) Dijkstra's Algorithm Search");
		System.out.println("0) Exit");
	}
	
	private static void InteractWithUser() {
		displayMenu();
		do {
			menuEntry = parseUserInput();
		} while (menuEntry == null);
		
		if (menuEntry == 1) {
			displayBasicGraphInfo();
		}
		else if (menuEntry == 2) {
			displayFullGraph();
		}
		else if (menuEntry == 3) {
			breadthFirstSearch();
		}
		else if (menuEntry == 4) {
			depthFirstSearch();
		}
		else if (menuEntry == 5) {
			dijkstraSearch();
		}
	}

	private static Integer parseUserInput() {

		System.out.print("Please select an option: ");
		System.out.printf("...\n");
		int menuEntry = input.nextInt();

		if (menuEntry < 0 || menuEntry > 5) {
			System.out.println("Invalid selection");
			return null;
		}
		return menuEntry;
	}
	
	private static void breadthFirstSearch() {
		int first = graph.getFirstVertex();
		int second = graph.getSecondVertex();
		System.out.println("Calculating Breadth-First between: " + first + " and " + second);
		graph.breadthFirstSearch(first, second);
	}
	
	private static void depthFirstSearch() {
		int first = graph.getFirstVertex();
		int second = graph.getSecondVertex();
		System.out.println("Calculating Depth-First between: " + first + " and " + second);
		graph.depthFirstSearch(first, second);
	}
	
	private static void dijkstraSearch() {
		int first = graph.getFirstVertex();
		int second = graph.getSecondVertex();
		System.out.println("Calculating Dijkstra's Algorithm between: " + first + " and " + second);
		graph.dijkstraSearchSearch(first, second);
	}
}
