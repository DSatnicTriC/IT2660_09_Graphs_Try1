import java.util.Scanner;

public class GraphRunner {

	public static void main(String[] args) {
		System.out.println("Graph has been loaded!");

		displayMenu();

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
				
		Scanner scan = new Scanner(System.in);
        System.out.print("Please select an option: ");
	}
}
