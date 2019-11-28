package anagram;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class AnagramApp {

	public static void main(String[] args) {

//		// check number of arguments
//		if (args.length != 2) {
//			printUsage();
//			System.exit(1);
//		}
//
//		// parse arguments
//		String inputfile = args[0];
//		String outputfile = args[1];
//		
//		AnagramList arrayOfWordsLinkedList[] = null;
//		
//		long statTime = System.nanoTime();		
//		// read string from file
//		Path pathToFile = Paths.get(inputfile);
//		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {
//
//			// read the first line from the text file
//			String line = br.readLine();
//
//			// loop until all lines are read
//			while (line != null) {
//
		        // TODO; Check with TAs 
//				String word = line.trim().toLowerCase();
//
//				arrayOfWordsLinkedList = addWord(arrayOfWordsLinkedList, word);
//
//				// read next line
//				line = br.readLine();
//			}
//
//		} catch (Exception e) {
//			System.err.println("Could not read file " + outputfile);
//			System.exit(3);
//
//		}
//		
//	    // TODO: Fix quick sort 
//		quickSort(arrayOfWordsLinkedList);
//		long timeElapsed = System.nanoTime() - statTime;


		// TODO: Write linked list to a file
		
		// TODO: print time to screen
		
		AnagramList arrayOfWordsLinkedList[] = null;
		
	    String words[] = {"car",
	    		"dog",
	    		"bed",
	    		"stop",
	    		"god",
	    		"pots",
	    		"arc",
	    		"tops"};
	    
	    for(String word : words) {
	    	arrayOfWordsLinkedList = insert(arrayOfWordsLinkedList, word);
	    }
	    
	    for(AnagramList list : arrayOfWordsLinkedList) {
	    	list.printListInOneLine();
	    }
	    
	    //TODO: Get quicksort to work
	    quickSort(arrayOfWordsLinkedList);
	}
	
	/**
	 * Checks is the two strings are anagrams
	 * @param s1 the first string
	 * @param s2 the second string
	 * @return true if words are anagram, false otherwise
	 */
	public static boolean isAnagram(String s1, String s2) {
		String firstSorted = sort(s1);
		String secondSorted = sort(s2);
		return (firstSorted.compareTo(secondSorted) == 0); 
	}
	
	
	/**
	 * Sort string alphabetically 
	 * @param str the string to be sorted
	 * @return the sorted string
	 */
    public static String sort(String str) 
    { 
        char temp[] = str.toCharArray();          
        Arrays.sort(temp);           
        return new String(temp); 
    } 
    
    
    
    /**
     * inserts a new node to the anagram list in alphabetical order
     * @param aList
     * @param word
     * @return
     */
    public static AnagramList[] insert(AnagramList[] aList, String word) {
    	
    	Node newNode = new Node(word);
    	
    	if (aList == null) {
    		aList = new AnagramList[1];
    		aList[0] = new AnagramList(); 
    		aList[0].InsertInOrder(newNode); 
    		return aList;
    	}
    	
    	// check for anagrams
    	boolean wordAdded =false;
    	for(int i=0;  i < aList.length; i++) {
    		String str = aList[i].getHead().getWord();
    		if (isAnagram(str, word)) {
    			aList[i].InsertInOrder(newNode);
    			wordAdded = true;
    			break;
    		}
    	}
    	
    	if (wordAdded)
    		return aList;
    	
    	// resize array
    	AnagramList[] newList = new AnagramList[aList.length + 1];
    	for(int i=0;  i < aList.length; i++) {
    		newList[i] = aList[i];
    	}
    	
    	newList[newList.length-1] = new AnagramList();
    	newList[newList.length-1].InsertInOrder(newNode);
    	return newList;
    }
    
    
    /**
	 * Prints informative message about how to use the program 
	 * from command line
	 */
	private static void printUsage() {

		System.out.println("Arguments missing!!");
		System.out.println("Usage: <inputfile> <outputfile>");

	}
    
    

	/**
	 * Implementation of quick sort for an array of anagram lists.
	 * <p>
	 * <b>Note: </b> this is the public method called by users
	 * @param arr a java <code>array</code> of anagram lists
	 */
	public static void quickSort(AnagramList[] arr) {
		if (arr.length < 2)
			return;
		
		int max = 0;
		// find the largest element and put it at the end of the array
		for (int i = 0; i < arr.length; i++)
			if (arr[max].getHead().getWord().compareTo(arr[i].getHead().getWord()) < 0)
				max = i;

		swap(arr, arr.length - 1, max); // largest element is now in its final position
		quickSort(arr, 0, arr.length - 2);
	}

	/**
	 * Private method that implements the quick sort algoritm over an array list
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
			while (bound.compareTo(arr[lower].getHead().getWord()) < 0) {
				lower++;
			}

			while (bound.compareTo(arr[upper].getHead().getWord()) > 0) {
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
	 * Interchanges i place the values stored in the array at arra indexes left and right
	 * @param arr the java array of anagram lists
	 * @param left index into the array
	 * @param right index into the array
	 */
	private static void swap(AnagramList arr[], int left, int right) {
		AnagramList temp = arr[left]; // original left value is set aside 
		arr[left] = arr[right]; // new left value becomes old right
		arr[right] = temp; // new right value becomes old left
	}

}
