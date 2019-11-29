

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;

public class AnagramApp {

	public static void main(String[] args) {

		// check number of arguments
		if (args.length != 2) {
			printUsage();
			System.exit(1);
		}

		// parse arguments
		String inputfile = args[0];
		String outputfile = args[1];
		
		AnagramList arrayOfWordsLinkedList[] = null;
		
		long statTime = System.nanoTime();		
		// read string from file
		Path pathToFile = Paths.get(inputfile);
		try (BufferedReader br = Files.newBufferedReader(pathToFile, StandardCharsets.US_ASCII)) {

			// read the first line from the text file
			String line = br.readLine();

			// loop until all lines are read
			while (line != null) {

				String word = line.trim().toLowerCase();

				arrayOfWordsLinkedList = insert(arrayOfWordsLinkedList, word);

				// read next line
				line = br.readLine();
			}

		} catch (Exception e) {
			System.err.println("Could not read file " + outputfile);
			System.exit(3);

		}
		
	    
		QuickSort.sort(arrayOfWordsLinkedList);
		long timeElapsed = System.nanoTime() - statTime;
		
		System.out.println("Time: "+ timeElapsed/1.0E9 + "sec");
		
        try {
			saveResultsToFile(arrayOfWordsLinkedList, outputfile);
		} catch (IOException e) {
			System.err.println(e.getMessage());
			System.exit(2);
		}
		
		
        
//		AnagramList arrayOfWordsLinkedList[] = null;
//		
//	    String words[] = {"car",
//	    		"dog",
//	    		"bed",
//	    		"stop",
//	    		"god",
//	    		"pots",
//	    		"arc",
//	    		"tops"};
//	    
//	    for(String word : words) {
//	    	arrayOfWordsLinkedList = insert(arrayOfWordsLinkedList, word);
//	    }
//	    
//	    for(AnagramList list : arrayOfWordsLinkedList) {
//	    	list.printListInOneLine();
//	    }
//	    
//	    //TODO: Get quicksort to work
//	    QuickSort.sort(arrayOfWordsLinkedList);
//	    
//	    System.out.println("\n");
//	    for(AnagramList list : arrayOfWordsLinkedList) {
//	    	list.printListInOneLine();
//	    }
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
	 * precondition: filename exists and list has been sorted
	 * saves sorted array of linked list to text file one value per line
	 */
	private static void saveResultsToFile(AnagramList[] arr, String outputfile) throws IOException {
		FileWriter writer = new FileWriter(outputfile);
		for (int i = 0; i < arr.length; i++) {
			writer.write(arr[i] + "\n");
		}
		writer.close();

	}

}
