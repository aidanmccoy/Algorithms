import java.util.ArrayList;
import java.io.*;

public class Algorithm {

	public static void findSingle(int[] input, int left, int right) {
		int mid;
		
		if (left == right) {
			System.out.println(input[left]);
			return;
		}

		mid = (left + right) / 2;

		if (mid % 2 == 1) { //Mid is odd
			if (input[mid -1] != input[mid]) {
				findSingle(input, left, mid -1);
			} else {
				findSingle(input, mid + 1, right);
			}
		} else {	//Mid is even
			if (input[mid] != input[mid +1]) {
				findSingle(input, left, mid);
			} else {
				findSingle(input, mid + 2, right);
			}
		}
	}

	public static void main(String[] args) throws IOException {
		BufferedReader infile = new BufferedReader(new FileReader(args[0]));
		int[] numberArray;

		ArrayList<Integer> numberList = new ArrayList<Integer>();

		String line = infile.readLine();

		if (line.contains(", ")) {
			String[] numbers = line.split(", ");
			for (int i = 0; i < numbers.length; i++)
				numberList.add(Integer.parseInt(numbers[i]));
		} else {
			numberList.add(Integer.parseInt(line));
		}

		numberArray = new int[numberList.size()];
		for (int i = 0; i < numberList.size(); i++) {
			numberArray[i] = numberList.get(i).intValue();
		}
		findSingle(numberArray, 0, numberArray.length - 1);
	}

}