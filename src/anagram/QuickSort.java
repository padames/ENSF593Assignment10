package anagram;

public class QuickSort {

	/**
	 * Implementation of quick sort for an array of anagram lists.
	 * <p>
	 * <b>Note: </b> this is the public method called by users
	 * @param arr a java <code>array</code> of anagram lists
	 */
	public static void sort(AnagramList[] arr) {
		if (arr.length < 2)
			return;

		int max = 0;
		// find the largest element and put it at the end of data
		for (int i = 0; i < arr.length; i++) {
			String s1 = arr[max].getHead().getWord();
			String s2 = arr[i].getHead().getWord();
			if (s1.compareTo(s2) < 0)
				max = i;
		}

		swap(arr, arr.length - 1, max); // largest element is now in its final position
		quickSort(arr, 0, arr.length - 2);
	}

	/**
	 * Private method that implements the quick sort algorithm over an array list
	 * of anagram lists.
	 * 
	 * @param arr the array of lists
	 * @param first the index where the array data starts
	 * @param last the index where the array data ends
	 */
	private static void quickSort(AnagramList[] arr, int first, int last) {
		int lower = first + 1, upper = last;
		swap(arr, first, (first + last) / 2);
		String bound = arr[first].getHead().getWord();
		while (lower <= upper) {
			while (bound.compareTo(arr[lower].getHead().getWord()) > 0) {
				lower++;
			}

			while (bound.compareTo(arr[upper].getHead().getWord()) < 0) {
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
	 * Interchanges i place the values stored in the array at arr indexes left and right
	 * @param arr the java array of anagram lists
	 * @param left index into the array
	 * @param right index into the array
	 */
	private static void swap(AnagramList arr[], int left, int right) {
		AnagramList temp = arr[left]; // Create a temporary integer to store value 1
		arr[left] = arr[right]; // Swap value 1 to value 2
		arr[right] = temp; // Swap value 2 to value 1
	}

}
