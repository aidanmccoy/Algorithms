import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Dynamic_Programming {
	public static void main(String[] args) throws FileNotFoundException {
		int numLockers, numKeys, numBalls;

		File file = new File(args[0]);
		Scanner sc = new Scanner(file);

		numLockers = sc.nextInt();
		numKeys = sc.nextInt();
		numBalls = sc.nextInt();

		int[] initialKeyNumbers = new int[numLockers];
		int[] ballLockerNumbers = new int[numLockers];

		for (int i = 0; i < numKeys; i++) {
			initialKeyNumbers[i] = sc.nextInt();
		}
		
		for (int i = 0; i < numBalls; i++) {
			ballLockerNumbers[i] = sc.nextInt();
		}
		System.out.println("Done");
	}
}
