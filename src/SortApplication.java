import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

/**
 * Class to run the command line program through main
 *
 */
public class SortApplication {

	/**
	 * Main entry point to run program from command line.
	 * Expects command line arguments as explained by print usage function.
	 * @param args
	 */
	public static void main(String[] args) {

		// check number of arguments
		if (args.length != 4) {
			printUsage();
			System.exit(1);
		}

		// parse arguments
		String order = parseOrder(args[0]);
		int size = parseSize(args[1]);
		String algorithm = parseAlgorithm(args[2]);
		String outputfile = parseOutputfile(args[3]);


		// Create array
		int arr[] = CreateArray(size, order);

		// Sort array
		long statTime = System.nanoTime();
		SortArray(arr, algorithm);
		long timeElapsed = System.nanoTime() - statTime;

		System.out.println(algorithm + " " + order + " " + size + ": " + (timeElapsed / 1.0E9) + " sec");

		// Write array to file
		try {
			saveResultsToFile(arr, outputfile);
		} 
		catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(6);
		}

	}

	/**
	 * save all results to a file for permanent record of results.
	 * @param arr
	 * @param outputfile
	 * @throws IOException
	 */
	private static void saveResultsToFile(int[] arr, String outputfile) throws IOException {
		FileWriter writer = new FileWriter(outputfile);
		for (int i = 0; i < arr.length; i++) {
			writer.write(arr[i] + "\n");
		}
		writer.close();

	}

	/**
	 * Sorts the array using one of these algorithms: bubble, insertion, merge, or
	 * quick,
	 * 
	 * @param arr       the array to be sorted
	 * @param algorithm the sorting algorithm
	 */
	private static void SortArray(int[] arr, String algorithm) {
		switch (algorithm) {
		case ("bubble"):
			Sort.bubbleSort(arr);
			break;
		case ("insertion"):
			Sort.insertionSort(arr);
			break;
		case ("merge"):
			Sort.mergeSort(arr);
			break;

		case ("quick"):
			Sort.quickSort(arr);
			break;
		}

	}

	/**
	 * Creates an array given size and sorting order
	 * 
	 * @param size  the size of the array to be created
	 * @param order the sorting order of the created array (ascending, descending,
	 *              or random)
	 * @return the created array
	 */
	private static int[] CreateArray(int size, String order) {
		int arr[] = new int[size];
		switch (order) {
		case ("ascending"):
			for (int i = 0; i < arr.length; i++)
				arr[i] = i; // storing random integers in an array
			break;
		case ("descending"):
			for (int i = 0; i < arr.length; i++)
				arr[i] = arr.length - i - 1;
			break;
		case ("random"):
			Random random = new Random();
			for (int i = 0; i < arr.length; i++)
				arr[i] = random.nextInt(); // storing random integers in an array
			break;
		}

		return arr;
	}

	/**
	 * Parses output file name from string
	 * 
	 * @param argument the argument
	 * @return the outputfile name
	 */
	private static String parseOutputfile(String argument) {
		return argument;
	}

	/**
	 * Parses algorithm from string
	 * 
	 * @param argument the argument
	 * @return the algorithm name
	 */
	private static String parseAlgorithm(String argument) {
		String algorithm = argument;
		if (algorithm.compareTo("bubble") != 0 && algorithm.compareTo("insertion") != 0
				&& algorithm.compareTo("merge") != 0 && algorithm.compareTo("quick") != 0) {
			System.err.println("Invalid algorithm. program aborting");
			System.exit(3);
		}
		return algorithm;
	}

	/**
	 * Parses array size from argument
	 * 
	 * @param argument the argument
	 * @return the array size
	 */
	private static int parseSize(String argument) {
		int arraySize = 0;
		try {
			arraySize = Integer.parseInt(argument);
			if (arraySize <= 0)
				throw new IllegalArgumentException("test");
		} catch (NumberFormatException e) {
			System.err.println(e.getMessage());
			System.exit(4);
		} catch (IllegalArgumentException e) {
			System.err.println(e.getMessage());
			System.exit(5);
		}
		return arraySize;
	}

	/**
	 * Parses sorting order from argument
	 * 
	 * @param argument the argument
	 * @return the sorting order
	 */
	private static String parseOrder(String argument) {
		String order = argument;
		if (order.compareTo("ascending") != 0 && order.compareTo("descending") != 0 && order.compareTo("random") != 0) {
			System.err.println("Invalid order. program aborting");
			System.exit(2);
		}
		return order;
	}

	/**
	 * Prints usage
	 */
	private static void printUsage() {

		System.out.println("Arguments missing!!");
		System.out.println("Usage: <order> <size> <algorithm> <outputfile>");

	}
}
