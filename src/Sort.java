
public class Sort {

	/**
	 * 
	 * @param arr
	 */
	public static void bubbleSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			boolean swapped = false;

			for (int j = arr.length - 1; j > i; j--)
				if (arr[j] < arr[j - 1]) { // Compare items
					swap(arr, j, j - 1);
					swapped = true;
				}
			if (!swapped) {
				return;
			}
		}
	}

	/**
	 * 
	 * @param arr
	 */
	public static void quickSort(int[] arr) {
		if (arr.length < 2)
			return;

		int max = 0;
		// find the largest element and put it at the end of data
		for (int i = 0; i < arr.length; i++)
			if (arr[max] < arr[i])
				max = i;

		swap(arr, arr.length - 1, max); // largest element is now in its final position
		quickSort(arr, 0, arr.length - 2);
	}

	/**
	 * 
	 * @param arr
	 * @param first
	 * @param last
	 */
	private static void quickSort(int[] arr, int first, int last) {
		int lower = first + 1, upper = last;
		swap(arr, first, (first + last) / 2);
		int bound = arr[first];
		while (lower <= upper) {
			while (bound > arr[lower]) {
				lower++;
			}

			while (bound < arr[upper]) {
				upper--;
			}

			if (lower < upper)
				swap(arr, lower++, upper--);
			else
				lower++;
		}

		swap(arr, upper, first);

		if (first < upper - 1)
			quickSort(arr, first, upper - 1);
		if (upper + 1 < last)
			quickSort(arr, upper + 1, last);
	}

	/**
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 */
	private static void swap(int arr[], int i, int j) {
		int temp = arr[i]; // Create a temporary integer to store value 1
		arr[i] = arr[j]; // Swap value 1 to value 2
		arr[j] = temp; // Swap value 2 to value 1
	}

	/**
	 * 
	 * @param arr an array of integers
	 */
	public static void mergeSort(int[] arr) {
		mergeSort(arr, 0, arr.length - 1);
	}

	/**
	 * Based on the course implementation worked during class. It recursively
	 * subdivides the arrays if the initial member of the left array is smaller than
	 * the initial member of the right array
	 * <p>
	 * Uses a helper method <code>merge</code> to merge the arrays generated at the
	 * bottom of the recursive calls when the first two calls to
	 * <code>mergeSort</code> return.
	 * 
	 * @param arr an array of integers
	 */
	private static void mergeSort(int[] arr, int first, int last) {
		if (first < last) {
			int mid = (last + first) / 2;
			mergeSort(arr, first, mid);
			mergeSort(arr, mid + 1, last);
			merge(arr, first, mid, last);
		}
	}

	/**
	 * A helper class to the <code>mergeSort</code> method
	 * 
	 * @param arr   an array of integers
	 * @param left
	 * @param mid
	 * @param right
	 */
	private static void merge(int[] arr, int left, int mid, int right) {
		// step 1
		int sizeLeft = mid - left + 1;
		int sizeRight = right - mid;
		// step 2
		int[] leftTemp = new int[sizeLeft];
		int[] rightTemp = new int[sizeRight];
		// step 3
		for (int i = 0; i < sizeLeft; i++) {
			leftTemp[i] = arr[left + i];
		}
		for (int i = 0; i < sizeRight; i++) {
			rightTemp[i] = arr[mid + 1 + i];
		}
		// step 4
		int iL = 0, iR = 0, j = left; // we are overwriting the original array starting at left
		while (iL < sizeLeft && iR < sizeRight) {
			if (leftTemp[iL] <= rightTemp[iR]) {
				arr[j] = leftTemp[iL];
				iL++;
			} else {
				arr[j] = rightTemp[iR];
				iR++;
			}
			j++;
		}
		// in case any of the arrays is larger than the other copy the remaining items
		while (iL < sizeLeft) {
			arr[j] = leftTemp[iL];
			iL++;
			j++;
		}
		while (iR < sizeRight) {
			arr[j] = rightTemp[iR];
			iR++;
			j++;
		}

	}

	/**
	 * Implementation from course slides. Average and worst cases is O(n^2). It
	 * approaches O(n) if array is nearly ordered.
	 * 
	 * @param arr an array of integers
	 */
	public static void insertionSort(int[] arr) {
		for (int i = 1, j; i < arr.length; i++) {
			int temp = arr[i];
			for (j = i; j > 0 && temp < arr[j - 1]; j--) {
				arr[j] = arr[j - 1];
			}
			arr[j] = temp;
		}
	}

}
