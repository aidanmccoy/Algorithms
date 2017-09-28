import java.util.Arrays;
import java.lang.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.Random;

import static java.nio.file.StandardOpenOption.*;

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

	/*public static void MergeSort(int[] input) {
		int mid, n;
		int[] lArray, rArray;
	
		n = input.length;

		if (n <= 1) {
			return;
		} else {
			mid = n/2;

			lArray = new int[mid];
			rArray = new int[n - mid];

			System.arraycopy(input, 0, lArray, 0, mid);
			System.arraycopy(input, mid + 1, rArray, 0, n - mid);

			MergeSort(lArray);
			MergeSort(rArray);
			
			int i = 0;
            int j = 0;
            int k = 0;
 
            // Merge lArray and rArray arrays
            while(i < lArray.length && j < rArray.length)
            {
                if(lArray[i] < rArray[j])
                {
                    input[k] = lArray[i];
                    i++;
                }
                else
                {
                    input[k] = rArray[j];
                    j++;
                }
                k++;
            }
            // Collect remaining elements
            while(i < lArray.length)
            {
                input[k] = lArray[i];
                i++;
                k++;
            }
            while(j < rArray.length)
            {
                input[k] = rArray[j];
                j++;
                k++;
            }
		}
	}*/
	
	public static void mergeSort(int[] array)
    {
        if(array == null)
        {
            return;
        }
 
        if(array.length > 1)
        {
            int mid = array.length / 2;
 
            // Split left part
            int[] left = new int[mid];
            for(int i = 0; i < mid; i++)
            {
                left[i] = array[i];
            }
             
            // Split right part
            int[] right = new int[array.length - mid];
            for(int i = mid; i < array.length; i++)
            {
                right[i - mid] = array[i];
            }
            mergeSort(left);
            mergeSort(right);
 
            int i = 0;
            int j = 0;
            int k = 0;
 
            // Merge left and right arrays
            while(i < left.length && j < right.length)
            {
                if(left[i] < right[j])
                {
                    array[k] = left[i];
                    i++;
                }
                else
                {
                    array[k] = right[j];
                    j++;
                }
                k++;
            }
            // Collect remaining elements
            while(i < left.length)
            {
                array[k] = left[i];
                i++;
                k++;
            }
            while(j < right.length)
            {
                array[k] = right[j];
                j++;
                k++;
            }
        }
    }

	public static void main(String[] args) {
		/*int[] testData = new int[] {5,2,7,4,2,6,9,0,4,6,7,3,1,3,8,9,7,5,6,3,4};
		System.out.println("Unsorted Data: " + Arrays.toString(testData)); 	
		System.out.println("Sorted Data: " + Arrays.toString(SelectionSort(testData)));	
		
		testData = new int[] {5,2,7,4,2,6,9,0,4,6,7,3,1,3,8,9,7,5,6,3,4};
		System.out.println("Unsorted Data: " + Arrays.toString(testData)); 	
		mergeSort(testData);
		System.out.println("Sorted Data: " + Arrays.toString(testData));*/
		
		Random rn = new Random();
		
		int listSize;
		int[] randomList;
		long timeStart;
		long timeEnd;
		
		for (listSize = 10000; listSize <= 20000; listSize = listSize + 20) {
			System.out.println("Listsize is now " + listSize);
			randomList = new int[listSize];
			for (int i = 0; i < listSize; i++) {
				randomList [i] = -10000 + rn.nextInt(20000);
			}
			timeStart = System.nanoTime();
			SelectionSort(randomList);
			timeEnd = System.nanoTime();
			
			try {
			    Files.write(Paths.get("selectionTimes.txt"), (Long.toString(timeEnd - timeStart) + "\n").getBytes(), StandardOpenOption.APPEND);
			}catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
			
			for (int i = 0; i < listSize; i++) {
				randomList [i] = -10000 + rn.nextInt(20000);
			}
			timeStart = System.nanoTime();
			mergeSort(randomList);
			timeEnd = System.nanoTime();
			
			try {
			    Files.write(Paths.get("mergeTimes.txt"), (Long.toString(timeEnd - timeStart) + "\n").getBytes(), StandardOpenOption.APPEND);
			}catch (IOException e) {
			    //exception handling left as an exercise for the reader
			}
			
			
			
			
		}
		
		try {
		    Files.write(Paths.get("mergeTimes.txt"), (Integer.toString(30) + "\n").getBytes(), StandardOpenOption.APPEND);
		}catch (IOException e) {
		    //exception handling left as an exercise for the reader
		}

	}
}