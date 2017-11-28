import java.io.File;
import java.io.FileNotFoundException;
import java.lang.*;
import java.util.ArrayList;
import java.util.Arrays;
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
		System.out.println("BaiscGreedyVertexCover:" + Arrays.toString(cover.toArray()));
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
		System.out.println("SmartGreedyVertexCover:" + Arrays.toString(cover.toArray()));
	}

	public static int[] CreateVertexList(int numVerticies) {
		int[] list = new int[numVerticies];
		
		for (int i = 0; i < numVerticies; i++) {
			list[i] = i;
		}
		return list;
	}

	public static void BruteForceVertexCover(int[][] origGraph, int numVerticies) {
		ArrayList<Integer> cover = new ArrayList<Integer>();
		int[][] graph = CopyGraph(origGraph, numVerticies);
		int[] vertexList = CreateVertexList(numVerticies);
		
		
		for (int i = 0; i < (1<<numVerticies); i++) {
			System.out.print("{ ");
			
			for (int j = 0; j < numVerticies; j++) {
				if ((i & (1 << j)) > 0) {
					System.out.print(vertexList[j] + " ");
				}	
			}	
			System.out.println("}");
		}
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

		//printGraph(graph, numVerticies);

		sc.close();
	}

}

