import java.io.*;
import java.util.LinkedList;
import java.util.Arrays;
import java.util.Scanner;

public class Bipartite {
	static int[] colors;
	static boolean bipartite = true;

	public static void main(String[] args) throws IOException {
		int[][] verticies;
		int currVertex, v1, v2, maxVertex = 0;
		File file = new File(args[0]);
		int numVerticies = Math.toIntExact(file.length() / 2);

		verticies = new int[numVerticies][numVerticies];

		Scanner sc = new Scanner(file);

		while (sc.hasNextInt()) {
			v1 = sc.nextInt();
			v2 = sc.nextInt();
			verticies[v1][v2] = 1;
			verticies[v2][v1] = 1;

			maxVertex = Math.max(v1, maxVertex);
			maxVertex = Math.max(v2, maxVertex);
		}
		
		colors = new int[maxVertex + 1];

		CheckBipartite(verticies, maxVertex);

	}

	private static void PrintArray(int[][] arr, int size) {
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				System.out.print(arr[i][j] + "-");
			}
			System.out.println();
		}
	}

	private static void CheckBipartite(int[][] arr, int numVerticies) {
		int numComponents = 0;

		while (!CheckIfDone()) {
			for (int i = 0; i < numVerticies; i++) {
				if (colors[i] == 0) {
					ColorComponent(arr, i, numVerticies);
					break;
				}
			}
			numComponents++;
		}

		//PrintArray(arr, numVerticies);
		//System.out.println("Colors is " + Arrays.toString(colors));

		if (bipartite == true) {
			System.out.println("Is two-colorable");
		} else {
			System.out.println("Is not two-colorable");
		}
		System.out.println(numComponents + " connected component(s)");
	}

	private static void ColorComponent(int[][] arr, int sourceVertex, int numVerticies) {
		LinkedList<Integer> nextVerticies;
		int currVertex;

		nextVerticies = new LinkedList<Integer>();
		nextVerticies.add(sourceVertex);

		colors[sourceVertex] = 1;

		while (nextVerticies.size() != 0) {
			currVertex = nextVerticies.poll();

			for (int i = 1; i <= numVerticies; i++) {
				if (arr[currVertex][i] == 1 && colors[i] == 0) {
					if (colors[currVertex] == 1) {
						colors[i] = 2;
					} else {
						colors[i] = 1;
					}
					nextVerticies.add(i);
				}

				else if (arr[currVertex][i] == 1 && colors[i] == colors[currVertex]) {
					bipartite = false;
					while (nextVerticies.size() != 0) {
						colors[nextVerticies.poll()] = 3;
					}
				}
			}
		}
	}

	private static boolean CheckIfDone() {
		for (int i = 0; i < colors.length; i++) {
			if (colors[i] == 0)
				return false;
		}
		return true;
	}
}