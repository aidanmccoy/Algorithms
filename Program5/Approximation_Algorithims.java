import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Approximation_Algorithims {

	public static boolean CheckForVerticies(int[][] graph, int numVerticies) {
		for (int i = 0; i < numVerticies; i++) {
			for (int j = 0; j < numVerticies; j++) {
				if (graph[i][j] == 1) {
					return true;
				}
			}
		}

		return false;
	}

	public static int GetVertexWithMaxDegree(int[][] graph, int numVerticies) {
		int maxDegree = 0, maxDegreeCount = 0, degreeCount;

		for (int i = 0; i < numVerticies; i++) {
			degreeCount = 0;
			for (int j = 0; j < numVerticies; j++) {
				if ((graph[i][j] == 1) || (graph[j][i] == 1)) {
					degreeCount++;
				}
			}
			if (degreeCount > maxDegreeCount) {
				maxDegree = i;
				maxDegreeCount = degreeCount;
			}
		}
		return maxDegree;
	}

	public static int[][] CopyGraph(int[][] graph, int numVerticies) {
		int[][] copy = new int[numVerticies][numVerticies];
		for (int i = 0; i < numVerticies; i++) {
			copy[i] = graph[i].clone();
		}

		return copy;
	}

	public static int GetNextBasicVertex(int[][] graph, int numVerticies) {
		for (int i = 0; i < numVerticies; i++) {
			for (int j = 0; j < numVerticies; j++) {
				if ((graph[i][j] == 1) || (graph[j][i] == 1)) {
					return i;
				}
			}
		}
		return -1;
	}

	public static void BasicGreedyVertexCover(int[][] origGraph, int numVerticies) {
		int[][] graph = CopyGraph(origGraph, numVerticies);
		ArrayList<Integer> cover = new ArrayList<Integer>();
		int basicDegreeVertex;

		while (CheckForVerticies(graph, numVerticies)) {
			basicDegreeVertex = GetNextBasicVertex(graph, numVerticies);
			cover.add(basicDegreeVertex);
			for (int i = 0; i < numVerticies; i++) {
				graph[basicDegreeVertex][i] = 0;
				graph[i][basicDegreeVertex] = 0;
			}
		}
		System.out.print("2-Approximation: ");
		for (int value : cover) {
			System.out.print(value + " ");
		}
		System.out.println();
	}

	public static void SmartGreedyVertexCover(int[][] origGraph, int numVerticies) {
		ArrayList<Integer> cover = new ArrayList<Integer>();
		int[][] graph = CopyGraph(origGraph, numVerticies);
		int maxDegreeVertex;

		while (CheckForVerticies(graph, numVerticies)) {
			maxDegreeVertex = GetVertexWithMaxDegree(graph, numVerticies);
			cover.add(maxDegreeVertex);
			for (int i = 0; i < numVerticies; i++) {
				graph[maxDegreeVertex][i] = 0;
				graph[i][maxDegreeVertex] = 0;
			}
		}
		System.out.print("log-Approximation: ");
		for (int value : cover) {
			System.out.print(value + " ");
		}
		System.out.println();
	}

	public static int[] CreateVertexList(int numVerticies) {
		int[] list = new int[numVerticies];

		for (int i = 0; i < numVerticies; i++) {
			list[i] = i;
		}
		return list;
	}

	public static boolean CheckIfVertexCover(ArrayList<Integer> cover, int[][] origGraph, int numVerticies) {
		for (int i = 0; i < numVerticies; i++) {
			for (int j = 0; j < numVerticies; j++) {
				if (origGraph[i][j] == 1) {
					if (!(cover.contains(i) || cover.contains(j))) {
						return false;
					}
				}
			}
		}
		return true;
	}

	public static void BruteForceVertexCover(int[][] origGraph, int numVerticies) {
		ArrayList<Integer> cover = new ArrayList<Integer>();
		ArrayList<Integer> finalCover = new ArrayList<Integer>();
		int[][] graph = CopyGraph(origGraph, numVerticies);
		int[] vertexList = CreateVertexList(numVerticies);

		for (int i = 0; i < (1 << numVerticies); i++) {

			for (int j = 0; j < numVerticies; j++) {
				if ((i & (1 << j)) > 0) {
					cover.add(vertexList[j]);
				}
			}
			if (CheckIfVertexCover(cover, origGraph, numVerticies)) {
				if (cover.size() < finalCover.size() || finalCover.isEmpty()) {
					finalCover = new ArrayList<>(cover);
				}
			}
			cover.clear();
		}

		System.out.print("Exact Solution: ");
		for (int value : finalCover) {
			System.out.print(value + " ");
		}
		System.out.println();
	}

	public static void printGraph(int[][] graph, int numVerticies) {
		for (int i = 0; i < numVerticies; i++) {
			for (int j = 0; j < numVerticies; j++) {
				System.out.print(graph[i][j]);
			}
			System.out.println();
		}
		System.out.println("-------------------");
	}

	public static void main(String[] args) throws FileNotFoundException {

		int numVerticies = 0, in1 = 0, in2;
		int[][] graph = new int[100][100];

		File file = new File(args[0]);
		Scanner sc = new Scanner(file);

		while (sc.hasNextInt()) {
			in1 = sc.nextInt();
			in2 = sc.nextInt();

			if (Math.max(in1, in2) > numVerticies - 1) {
				numVerticies = (Math.max(in1, in2) + 1);
			}
			graph[in1][in2] = 1;
		}

		SmartGreedyVertexCover(graph, numVerticies);

		BasicGreedyVertexCover(graph, numVerticies);

		BruteForceVertexCover(graph, numVerticies);

		// printGraph(graph, numVerticies);

		sc.close();
	}

}
