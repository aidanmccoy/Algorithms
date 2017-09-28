import java.util.Arrays;
import java.lang.*;

public class Runtime {

	public static int[] SelectionSort(int[] input) {
		int minNdx, tempInt, j, i;
		for (i = 0; i < (input.length -1); i++) {
			minNdx = i;
			for (j = i + 1; j < input.length; j ++) {
				if (input [j] < input[minNdx]) {
					minNdx = j;
				}
			}
			tempInt = input[i];
			input [i] = input[minNdx];
			input [minNdx] = tempInt;
		}
		return input;
	}

	public static int[] MergeSort(int[] input) {
		int mid, n;
		int[] lArray, rArray;
	
		n = input.length;

		if (n <= 1) {
			return input;
		} else {
			mid = n/2;

			lArray = new int[mid];
			rArray = new int[n - mid];

			System.arraycopy(input, 0, lArray, 0, mid);
			System.arraycopy(input, mid + 1, rArray, 0, n - mid);

			return Merge(MergeSort(lArray), MergeSort(rArray));
		}
	}

	public static int[] Merge (int[] input1, int[] input2) {
		if (input1.length == 0) {
			return input2;
		} else if (input2.length == 0) {
			return input1;
		} 
	}

	public static void main(String[] args) {
		int[] testData = new int[] {5,2,7,4,2,6,9,0,4,6,7,3,1,3,8,9,7,5,6,3,4};
		System.out.println("Unsorted Data: " + Arrays.toString(testData)); 	
		System.out.println("Sorted Data: " + Arrays.toString(SelectionSort(testData)));	
	}
}