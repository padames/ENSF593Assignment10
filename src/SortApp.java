import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

import org.apache.commons.cli.*;


public class SortApp {

	private Options options = new Options();
	CommandLine cmd;
	String arrOrder;
	String algo;
	String outputFilename;
	int size;
	int [] arr;
	long runTime;
	
	/**
	 * Entry point for app. Parses the arguments and creates the necessary data to run.
	 * <p>
	 * There are four required command line arguments.
	 * After successfully parsing them it populates the input
	 * variables and leaves the app in a state suitable to start a run.
	 * @param args
	 */
	public SortApp(String[] args) {
		var arrOrderOpt = addRequiredOption("o", "order", "order for the input array: ascending, descending, random");
		options.addOption(arrOrderOpt);
		
		var arrSizeOpt = addRequiredOption("s", "size", "size of the array to sort");
		options.addOption(arrSizeOpt);
		
		var algoChoiceOpt = addRequiredOption("a", "algorithm", "sorting algorithm: bubble, insertion, merge, quick");
		options.addOption(algoChoiceOpt);
		
		var outFileNameOpt = addRequiredOption("f", "filename", "output file name for results");
		options.addOption(outFileNameOpt);
		
		parseArguments(args);
		
		populateValues();
	}
	
	/**
	 * having parsed the command line arguments, populate the input variables
	 * with the command line options
	 */
	private void populateValues() {
		
		arrOrder = cmd.getOptionValue("order");
		
		String sizeS = cmd.getOptionValue("size");
		sizeS = sizeS.strip();
		size = Integer.valueOf(sizeS);
		
		if (size < 0) {
			System.err.println("Size should be greater than zero, exiting");
			System.exit(1);
		}
		if (size > Integer.MAX_VALUE) {
			System.err.println(String.format("Size exceeded max allowed: %d, exiting", Integer.MAX_VALUE));
			System.exit(1);			
		}
		
		algo = cmd.getOptionValue("algorithm");
		
		outputFilename = cmd.getOptionValue("filename");
		
	}

	/**
	 * populate the command line member variable with the options from parsing the arguments.
	 * <p>
	 * If there is a problem in the command line arguments it will 
	 * print out the help and exit. 
	 * @param args the command line arguments given to the program
	 */
	private void parseArguments(String[] args) {
        CommandLineParser parser = new DefaultParser();
        HelpFormatter formatter = new HelpFormatter();

        try {
            cmd = parser.parse(options, args);
        } 
        catch (ParseException e) {
            System.out.println(e.getMessage());
            formatter.printHelp("Sort-App", options);

            System.exit(1);
        }
	}


	/**
	 * Use available infor from having parsed command line to 
	 * create an array for testing
	 */
	private void createArray() {
		arr =  new int[size];
		
		switch(arrOrder.toLowerCase()) {
		case ("ascending"):
			for (int i = 0; i < arr.length; i++) {
				arr[i] = i;
			}
			break;
		case ("descending"):
			for (int i = arr.length -1; i > 0; i--) {
				arr[i] = i;
			}
			break;
		case ("random"):
			Random random = new Random();
			for (int i = 0; i < arr.length; i++) {
				arr[i] = random.nextInt(); 
			}
			break;
		default:
			System.err.println("Unknown algorithm, exiting now");
			System.exit(1);
		}
	}

	
	private Option addRequiredOption(String shortArg, String longArg,
			String description) {
		var theOption = new Option(shortArg, longArg, true, description);
		theOption.setRequired(true);
		return theOption;
	}
	
	/**
	 * Having parsed the command line argument it executes the 
	 * options entered by the user.
	 * Finally it saves result to file.
	 */
	private void run() {
	
		if (cmd == null) {
			System.err.println("Something went wrong, no commandline arguments were parsed.");
			System.exit(1);
		}

		// create array
		createArray();
		
		// sort array
		long statTime = System.nanoTime();
		sort();
		runTime = System.nanoTime() - statTime;
		System.out.println(String.format("%1$s: %3$d miliseconds to sort %2$s array of %4$d integers.", algo, arrOrder, (int)(runTime / 1.0E6 ), size));

				
		// Write array to file
		try {
			saveResultsToFile(arr, outputFilename);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(6);
		}

	}
	
	/**
	 * executes sorting algorithm indicated by user
	 */
	private void sort() {
		
		long startTime = System.nanoTime();
		switch (algo) {
		case ("bubble"):
			SortApp.bubbleSort(arr);
			break;
		case ("insertion"):
			SortApp.insertionSort(arr);
			break;
		case ("merge"):
			SortApp.mergeSort(arr, 0, size-1);
			break;
		
		case("quick"):
			SortApp.quickSort(arr);
			break;
		}
		long endTime = System.nanoTime();
		
		runTime = endTime-startTime;
	}

	/**
	 * precondition: filename exists and array has been sorted
	 * saves sorted array to text file one value per line
	 */
	private void saveResultsToFile(int[] arr, String outputfile) throws IOException {
//		FileWriter writer = new FileWriter(outputfile);
//		writer.write("Run time: " +  (runTime / 1.0E9) + " seconds\n");
//		for (int i = 0; i < arr.length; i++) {
//			writer.write(arr[i] + "\n");
//		}
//		writer.close();

	}

	@Override
	public String toString() {
		String s="";
		s+= String.format("%s, size: %d, time (s): %10d", algo, size, runTime);
		return s;
	}
	
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
	public static void insertionSort(int[] arr) {
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
	 * <p>
	 * <b>Note: </b> Using the extension commons-cli to
	 * parse the command line options 
	 * (see: http://commons.apache.org/proper/commons-cli/introduction.html)
	 * It is installed locally in the folder /lib/ext as a Jar file and 
	 * has been added to the class path pf the Eclipse project
	 * Idea from https://stackoverflow.com/a/367714/1585486
	 * @param args the command line arguments
	 */
	public static void main(String[] args) {
		SortApp app = null;
		args = new String[4];
		String sizes[] = {"100", "100000", "1000000"};
		String algorithms[] = { "bubble", "quick", "insertion", "merge" };
		String orders[] = { "descending", "random", "ascending" };
		for (String algorithm : algorithms) {
			for (String order : orders) {
				for (var size: sizes) {
					args[0] = String.format("-s%s ", size);	
					args[1] = String.format("-o%s", order);
					args[2] = String.format("-a%s", algorithm);
					args[3] = String.format("-f%s ", String.format("%s-%s-%s.txt", algorithm, order, size));
					app = new SortApp(args);
					app.run();					
				}
			}
		}
	}

}
