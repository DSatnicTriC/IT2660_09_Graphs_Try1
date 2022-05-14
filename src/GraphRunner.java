import java.util.Scanner;
import java.util.Random;

public class GraphRunner {

	static Scanner input = new Scanner(System.in);
	static Graph graph = new Graph();

	public static void main(String[] args) {
		Integer menuEntry;

		loadGraph();

		System.out.println("Graph has been loaded!");

		System.out.println(graph.toString());

//		displayMenu();
//		do {
//			menuEntry = parseUserInput();
//		} while (menuEntry == null);

		input.close();
	}

	private static void loadGraph() {
		int graphSize = 5;
		int minValue = 1;
		int maxValue = 100001;
		int minWeight = 1;
		int maxWeight = 50;
		int first, second, weight;

		Random rand = new Random();

		for (int i = 0; i < graphSize; i++) {
			try {
				first = rand.nextInt(minValue, maxValue);
				second = rand.nextInt(minValue, maxValue);
				weight = rand.nextInt(minWeight, maxWeight);
				graph.addEdge(first, second, weight, false);
			} catch (Exception e) {
				// try again
				i--;
			}
		}
	}

	private static void displayBasicGraphInfo() {
		System.out.println("**********************");
		System.out.println("X vertices");
		System.out.println("Y edges");
		System.out.println("**********************");
	}

	private static void displayFullGraph() {
		System.out.println("Full Graph");
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
