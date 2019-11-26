
public class SortApp {

	/**
	 * 
	 * @param arr
	 */
	public static void bubbleSort(int[] arr) {
		
	}

	/**
	 * 
	 * @param arr
	 */
	public static void quickSort(int[] arr) {
		
	}

	
	/**
	 * Based on the course implementation worked during class.
	 * It recursively subdivides the arrays if the initial
	 * member of the left array is smaller than the initial member 
	 * of the right array  
	 * <p>
	 * Uses a helper method <code>merge</code> to merge the arrays
	 * generated at the bottom of the recursive calls when the first
	 * two calls to <code>mergeSort</code> return. 
	 * @param arr an array of integers
	 */
	public static void mergeSort(int [] arr, int first, int last) {
		if (first < last) {
			int mid = (last + first) /2;
			mergeSort(arr, first, mid);
			mergeSort(arr, mid+1, last);
			merge(arr, first, mid, last);
		}
	}
	
	/**
	 * A helper class to the <code>mergeSort</code> method
	 * @param arr an array of integers
	 * @param left
	 * @param mid
	 * @param right
	 */
	private static void merge(int[] arr, int left, int mid, int right) {
		// step 1
		int sizeLeft = mid - left + 1;
		int sizeRight = right - mid;
		// step 2
		int [] leftTemp = new int[sizeLeft];
		int [] rightTemp = new int[sizeRight];
		// step 3
		for (int i = 0; i < sizeLeft; i++) {
			leftTemp[i] = arr[left+i];				
		}
		for (int i =0; i < sizeRight; i++) {
			rightTemp[i] = arr[mid+1+i];
		}
		// step 4
		int iL=0, iR=0, j=left; // we are overwriting the original array starting at left
		while(iL<sizeLeft && iR < sizeRight) {
			if (leftTemp[iL]<=rightTemp[iR]) {
				arr[j] = leftTemp[iL];
				iL++;
			}else {
				arr[j] = rightTemp[iR];
				iR++;
			}
			j++;
		}
		// in case any of the arrays is larger than the other copy the remaining items
		while(iL < sizeLeft) {
			arr[j]=leftTemp[iL];
			iL++;
			j++;
		}
		while(iR < sizeRight) {
			arr[j]=rightTemp[iR];
			iR++;
			j++;
		}
		
	}
	
	/**
	 * Implementation from course slides. 
	 * Average and worst cases is O(n^2). It approaches
	 * O(n) if array is nearly ordered. 
	 * @param arr an array of integers
	 */
	public void insertionSort(int[] arr) {
		for (int i = 1, j; i < arr.length; i++){
			int temp = arr[i];
			for (j = i; j > 0 && temp < arr[j-1]; j--) {
				arr[j] = arr[j-1];
			}
			arr[j] = temp;
		}
	}

	
	/**
	 * Expected execution from command line:
	 * java SortApp order size algorithm outputfile
	 * @param args
	 */
	public static void main(String[] args) {
		

	}

}
