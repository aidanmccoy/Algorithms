import java.io.*;
import java.lang.Math.*; 
import java.util.Arrays;
import java.util.Scanner;	

public class Bipartite {
	public static void main(String[] args) throws IOException {
		int[][] verticies;
		int currVertex;
		File file = new File(args[0]);
		int numVerticies = Math.toIntExact(file.length() / 4);

		System.out.println("Num verticies is " + numVerticies);
		verticies = new int[numVerticies][numVerticies];

		Scanner sc = new Scanner (file);

		for (int i = 0; i < numVerticies; i ++) {
			verticies[i][0] = 1;
		}

		while (sc.hasNextInt()) {
			currVertex = sc.nextInt();
			verticies[currVertex][verticies[currVertex][0]] = sc.nextInt();
			verticies[currVertex][0]++;
		} 
		PrintArray(verticies, numVerticies);

		CheckBipartite(verticies, numVerticies);
	}

	private static void PrintArray(int[][] arr, int size) {
		for (int i = 0; i < size; i ++) {
			for (int j = 0; j < size; j++) {
				System.out.print(arr[i][j] + "-");
			}
			System.out.println();
		}
	}

	private static void CheckBipartite(int[][] arr, int numVerticies) {

	}
}