package anagram;

/** 
 * This is the node used to create linked list
 * of anagrams.
 */

public class Node {

	/**
	 * the node's pay load, a single word
	 */
	private String word;
	/**
	 * reference to next cell
	 */
	private Node next;
	
	/**
	 * Constructs a Node object with the specified value 
	 * @param word the word value
	 */
	public Node (String word) {
		setWord(word);
		setNext(null);
	}

	/**
	 * Bases the comparison between this node and another on the ascii values of 
	 * the characters in the pay load of type <code>String</code>
	 * of the nodes.
	 * It falls back on a method of the class type <code>String</code> to do the 
	 * comparison between pay loads. 
	 * @param otherNode the node to compare to
	 * @return true if the pay loads are syntactically equal (case sensitive), false otherwise
	 */
	public boolean isEqual(Node otherNode) {
		if (otherNode == null)
			return false;
		return getWord().compareTo(otherNode.getWord()) == 0;
	}

	/**
	 * Compares if the pay load of this node is greater than that of the
	 * other node. It bases the comparison on a case-sensitive java <code>String</code> method.
	 * 
	 * @param otherNode the node to compare to
	 * @return true if this node's pay load is syntactically greater than the other's, false otherwise
	 */
	public boolean isGreatherThan(Node otherNode) {
		if (otherNode == null)
			return false;
		return getWord().compareTo(otherNode.getWord()) > 0;
	}	
	
	/**
	 * Does the comparison of less than between the pay loads of this and the
	 * other nodes. It bases such comparison on a case-sensitive java <code>String</code> method.
	 * 
	 * @param otherNode the node to compare to
	 * @return true if this node's pay load is syntactically less than the other's, false otherwise
	 */
	public boolean isLessThan(Node otherNode) {
		if (otherNode == null)
			return false;
		return getWord().compareTo(otherNode.getWord()) < 0;
	}	

	
	/**
	 * Get the word value
	 * @return the word value
	 */
	public String getWord() {
		return word;
	}

	/**
	 * Sets the word value
	 * @param name the word value
	 */
	public void setWord(String word) {
		this.word = word;
	}

	/**
	 * Gets the reference to the next student object
	 * @return the reference to the next student object
	 */
	public Node getNext() {
		return next;
	}

	/**
	 * Sets the reference to the next student object
	 * @param next
	 */
	public void setNext(Node next) {
		this.next = next;
	}
	
	/**
	 * returns a string suitable for printing the contents of the node 
	 */
	@Override
	public String toString () {
		return getWord();
	}
}
