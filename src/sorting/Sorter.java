package sorting;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class Sorter {
	
	private List<Integer> arr;

	public long bubbleSort(List<Integer> nums) {
		long start = System.nanoTime();
		
		boolean swapped = true;
		while (swapped == true) {
			swapped = false;
			for (int i = 0; i < nums.size() -1; ++i) {
				if (nums.get(i) > nums.get(i+1)) {		// If Right is less than Left, swap the elements
					int tmp = nums.get(i);
					nums.set(i, nums.get(i+1));
					nums.set(i+1, tmp);	
					swapped = true;
				}
			}
		}
		
		long end = System.nanoTime();
		return (end-start);
	}
	
	public long insertionSort(List<Integer> nums) {
		long start = System.nanoTime();
		
		int x;
		for (int i = 0; i < nums.size(); ++i) {
			x = nums.get(i);
			while (i > 0 && nums.get(i-1) > x) {
				nums.set(i, nums.get(i-1));
				--i;
			}
			nums.set(i, x);
		}
		
		long end = System.nanoTime();
		return (end-start);
	}
	
	public long selectionSort(List<Integer> nums) {
		long start = System.nanoTime();
		
		List<Integer> sorted =  new ArrayList<>();
		int low;
		int count = 0, pos = 0;
		while (count < nums.size()) {
			low = 0;
			for (int i = 0; i < nums.size(); ++i){
				if (nums.get(i) < nums.get(low)) {
					low = i;
				}
			}
			sorted.add(nums.get(low));
			nums.remove(low);
		}
		
		for (int i = 0; i < sorted.size(); ++i) {
			nums.add(i, sorted.get(i));
		}
		
		long end = System.nanoTime();
		return (end-start);
	}
	
	public long bucketSort(List<Integer> nums) {
		long start = System.nanoTime();
		
		// Work out how many buckets are required
		int lowest = 0;
		int highest = 0;
		for (int i = 0; i < nums.size(); ++i) {
			if (nums.get(i) > highest)
				highest = nums.get(i);
			if (nums.get(i) < lowest)
				lowest = nums.get(i);
		}
		int numBuckets = (int) Math.round((highest - lowest)/10 +0.5);	// Decide number of buckets based on array range /10, and round up
		
		// Create buckets
		List<List<Integer>> buckets = new ArrayList<>();
		for (int i = 0; i < numBuckets+1; ++i) {
			List<Integer> b = new ArrayList<>();
			b.add(9*i+i-1);							// Store the max value allowable in that bucket
			buckets.add(b);
		}
		
		// Populate the bucket
		for (int i = 0; i < numBuckets+1; ++i) {
			for (int n = 0; n < nums.size(); ++n) {
				int element = nums.get(n);
				if (element <= buckets.get(i).get(0) && element > buckets.get(i-1).get(0))
					buckets.get(i).add(element);
			}
		}
		
		// Remove max values from first element of each bucket
		for (int i = 0; i < buckets.size(); ++i) {
			buckets.get(i).remove(0);
		}
		
		// Sort buckets using Insertion sort
		for (int i = 0; i < buckets.size(); ++i) {
			this.insertionSort(buckets.get(i));
		}
		
		// Read buckets back into main array
		int count = 0;
		for (int i = 0; i < buckets.size(); ++i) {
			for (int n = 0; n < buckets.get(i).size(); ++n) {
				nums.set(count, buckets.get(i).get(n));
				++count;
			}
		}
		long end = System.nanoTime();
		return (end-start);
	}
	
	public long quickSort(List<Integer> nums) {
		// Wrapper function for quickSortRecursive, timing execution.
		long start = System.nanoTime();

		this.arr = nums;
		quickSortRecursive(0, this.arr.size()-1);
		
		long end = System.nanoTime();
		return (end-start);
	}

	private void quickSortRecursive(int left, int right) {
		int i = left, j = right;
		int tmp;
		int pivot = this.arr.get((left + right) / 2);		// Choose pivot - mid value
		while (i <= j) {
			while (this.arr.get(i) < pivot)
					i++;
			while (this.arr.get(j) > pivot)
					j--;
			if (i <= j) {									// Swap elements
					tmp = this.arr.get(i);
					this.arr.set(i, this.arr.get(j));
					this.arr.set(j,tmp);
					i++;
					j--;
			}
		}
		if (left < i - 1)									// Implement on divided arrays
			quickSortRecursive(left, i-1);
		if (i < right)
			quickSortRecursive(i, right);
	}
}
