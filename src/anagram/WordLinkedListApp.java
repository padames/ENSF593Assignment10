import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class WordLinkedListApp {

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
//		WordLinkedList arrayOfWordsLinkedList[] = null;
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
		
		WordLinkedList arrayOfWordsLinkedList[] = null;
		
	    String words[] = {"car",
	    		"dog",
	    		"bed",
	    		"stop",
	    		"god",
	    		"pots",
	    		"arc",
	    		"tops"};
	    
	    for(String word : words) {
	    	arrayOfWordsLinkedList = addWord(arrayOfWordsLinkedList, word);
	    }
	    
	    for(WordLinkedList list : arrayOfWordsLinkedList) {
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
    
    
    public static WordLinkedList[] addWord(WordLinkedList[] theList, String word) {
    	
    	Word theWord = new Word(word);
    	
    	if (theList == null) {
    		theList = new WordLinkedList[1];
    		theList[0] = new WordLinkedList(); 
    		theList[0].InsertInOrder(theWord); 
    		return theList;
    	}
    	
    	// check for anagrams
    	boolean wordAdded =false;
    	for(int i=0;  i < theList.length; i++) {
    		String str = theList[i].getHead().getWord();
    		if (isAnagram(str, word)) {
    			theList[i].InsertInOrder(theWord);
    			wordAdded = true;
    			break;
    		}
    	}
    	
    	if (wordAdded)
    		return theList;
    	
    	// resize array
    	WordLinkedList[] newList = new WordLinkedList[theList.length + 1];
    	for(int i=0;  i < theList.length; i++) {
    		newList[i] = theList[i];
    	}
    	
    	newList[newList.length-1] = new WordLinkedList();
    	newList[newList.length-1].InsertInOrder(theWord);
    	return newList;
    	
    	
    }
    
    
    /**
	 * Prints usage
	 */
	private static void printUsage() {

		System.out.println("Arguments missing!!");
		System.out.println("Usage: <inputfile> <outputfile>");

	}
    
    
    //***********************************************************
    /**
	 * 
	 * @param arr
	 */
	public static void quickSort(WordLinkedList[] arr) {
		if (arr.length < 2)
			return;
		
		int max = 0;
		// find the largest element and put it at the end of data
		for (int i = 0; i < arr.length; i++)
			if (arr[max].getHead().getWord().compareTo(arr[i].getHead().getWord()) < 0)
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
	private static void quickSort(WordLinkedList[] arr, int first, int last) {
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
	 * 
	 * @param arr
	 * @param i
	 * @param j
	 */
	private static void swap(WordLinkedList arr[], int i, int j) {
		WordLinkedList temp = arr[i]; // Create a temporary integer to store value 1
		arr[i] = arr[j]; // Swap value 1 to value 2
		arr[j] = temp; // Swap value 2 to value 1
	}
	
	
	
    

}
