import java.util.Arrays;

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
		
	}

	public static void main(String[] args) {
		int[] testData = new int[] {5,2,7,4,2,6,9,0,4,6,7,3,1,3,8,9,7,5,6,3,4};
		System.out.println("Unsorted Data: " + Arrays.toString(testData)); 	
		System.out.println("Sorted Data: " + Arrays.toString(SelectionSort(testData)));	
	}
}