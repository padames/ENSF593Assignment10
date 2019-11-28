//This is the "Node". i.e. class Word is used to create a
//linkedList, such that each Word object is a node in the linkedList.
public class Word {

	private String word;
	private Word next; //object reference that points to the next object
	
	/**
	 * Constructs a Word object with the specified value 
	 * @param word the word value
	 */
	public Word (String word) {
		setWord(word);
		setNext(null);
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
	public Word getNext() {
		return next;
	}

	/**
	 * Sets the reference to the next student object
	 * @param next
	 */
	public void setNext(Word next) {
		this.next = next;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString () {
		return getWord();
	}
}
