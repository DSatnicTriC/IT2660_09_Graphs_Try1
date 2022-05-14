import java.util.Scanner;
import java.util.Random;

public class GraphRunner {

	static Scanner input = new Scanner(System.in);
	static Graph graph = new Graph();

	public static void main(String[] args) {
		Integer menuEntry;

		loadGraph();

		System.out.println("Graph has been loaded!");

		displayMenu();
		do {
			menuEntry = parseUserInput();
			
			if (menuEntry == 1) {
				displayBasicGraphInfo();
			}
			else if (menuEntry == 2) {
				displayFullGraph();
			}
		} while (menuEntry == null);

		input.close();
	}

	private static void loadGraph() {
		int numberOfEdges = 2;
		int maxValue = 5;
		int maxWeight = 50;
		int first, second, weight;
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
	}

	private static void displayBasicGraphInfo() {
		System.out.println("**********************");
		System.out.println("Vertices: " + graph.getNumberOfVertices());
		System.out.println("Y edges: " + +graph.getNumberOfVertices());
		System.out.println("**********************");
	}

	private static void displayFullGraph() {
		System.out.println("Full Graph");
		System.out.println(graph.toString());
	}

	private static void displayMenu() {
		System.out.println("What would you like to do?");
		System.out.println("1) Display Basic Graph Info");
		System.out.println("2) Display Full Graph");
		System.out.println("3) Breadth-First Search");
		System.out.println("4) Depth-First Search");
		System.out.println("5) Dijkstra's Algorithm Search");
	}

	private static Integer parseUserInput() {

		System.out.print("Please select an option: ");
		int menuEntry = input.nextInt();

		if (menuEntry < 1 || menuEntry > 5) {
			System.out.println("Invalid selection");
			return null;
		}
		return menuEntry;
	}

}
