package sorting;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class SortingShowdown {
	
	public static void main(String args[])
	{
		Scanner scan = new Scanner(System.in);	// Used for user input on console
		
		// Create array to be sorted
		List<Integer> nums = new ArrayList<>();
		for (int i = 0; i < 100000; ++i) {
			nums.add(i);
		}
		Collections.shuffle(nums);
		Sorter s = new Sorter();
		
		System.out.println("Welcome to sorting showdown");
		int inp = -1;
		long time = -1;
		while (inp != 0) {
			System.out.println("Select an algorithm to sort the array: 0: Exit, 1: Insertion, 2: Bubble, 3: Quick, 4: Bucket, 5: Selection");
			if (scan.hasNextInt()) {
				inp = scan.nextInt();
				switch (inp) {
				case 0: System.out.println("Program Exiting");
						break;
				case 1: System.out.println("Insertion Sort Selected");
						time = s.insertionSort(nums);
						break;
				case 2: System.out.println("Bubble Sort Selected");
						time = s.bubbleSort(nums);
						break;
				case 3: System.out.println("QuickSort Selected");
						time = s.quickSort(nums);
						break;
				case 4: System.out.println("Bucket Sort Selected");
						time = s.bucketSort(nums);
						break;
				case 5: System.out.println("Selection Sort Selected");
						time = s.selectionSort(nums);
						break;
				default: System.out.println("Error: Number out of range. Select a valid option.");
						break;
				}

			} else {
				System.out.println("Error: Please enter a number");
				scan.nextLine();
			}
			if (time != -1) {							// Sort ran, print out stats and reset
				double t = time / 1000000000.0;
				System.out.printf("The sort completed succesfully in: %f seconds.", t );
				time = -1;
				//Collections.shuffle(nums);
			}
		}
		for (int i = 0; i < nums.size(); ++i) {
			System.out.println(nums.get(i));
		}
		scan.close();
	}
}
