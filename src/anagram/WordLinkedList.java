
public class WordLinkedList {

	/**
	 * The head of the Word Object Linked List
	 */
	private Word head;

	/**
	 * Constructs a WordLinkedList Object
	 */
	public WordLinkedList() {
		head = null;
	}

	/**
	 * Inserts a Word object to the end of the Linked List
	 * 
	 * @param s the Word's object to be inserted
	 */
	public void insertToEndOfList(Word s) {

		if (head == null) {
			head = s;
		} else {
			getLastNode().setNext(s);
		}

	}

	/**
	 * Inserts a  Word object to the front of the Linked List
	 * 
	 * @param s the  Word's object to be inserted
	 */
	public void insertToFrontOfList(Word s) {
		if (head == null) {
			head = s;
		} else {
			s.setNext(head);
			head = s;
		}
	}

	// we create a wrapper method so that we don't have to pass the head from main.
	// The actual
	// recursive implementation is done in the overloaded recursivePrintList which
	// takes a Student
	// object as an argument.
	/**
	 * Prints Linked list (recursively)
	 */
	public void recursivePrintList() {
		recursivePrintList(head);
	}

	/**
	 * Prints the linked list recursively
	 * 
	 * @param cursor
	 */
	private void recursivePrintList(Word cursor) {

		if (cursor != null) {

			recursivePrintList(cursor.getNext());
			System.out.println(cursor);
		}
	}

	/**
	 * Prints Linked list (iteratively)
	 */
	public void printList() {
		Word cursor = head;
		while (cursor != null) {
			System.out.println(cursor);
			cursor = cursor.getNext();
		}
	}
	
	/**
	 * Prints Linked list (iteratively)
	 */
	public void printListInOneLine() {
		Word cursor = head;
		while (cursor != null) {
			System.out.print(cursor + " ");
			cursor = cursor.getNext();
		}
		System.out.println();
	}

	/**
	 * Gets last node (i.e.  Word object) in the Linked list
	 * 
	 * @return
	 */
	private Word getLastNode() {
		Word cursor = head;
		while (cursor.getNext() != null) {
			cursor = cursor.getNext();
		}
		return cursor;
	}

	/**
	 * Gets the head ( i.e.  Word)
	 * 
	 * @return the  Word object at the head
	 */
	public Word getHead() {
		return head;
	}

	/**
	 * Inserts a  Word object in order of character. 
	 * @param p the Word's object to be inserted
	 */
	void InsertInOrder(Word p) {

		Word current = head;
		Word previous = null;

		while (current != null && p.getWord().compareTo(current.getWord()) >= 0) {

			// TODOL do I need to exclude equality
//			if (p.getId() == current.getId())
//				return;
			previous = current;
			current = current.getNext();
		}

		// previous is the predecessor node
		// if previous null, then linked list is empty/
		// so set head to the new node, otherwise set
		// previous to point to the new node
		if (previous == null)
			head = p;
		else
			previous.setNext(p);

		// set the new node to point to current node
		p.setNext(current);

	}

	/**
	 * Returns a Word object if the id matches a  Word in the list
	 * 
	 * @param id the word to search for 
	 * @return the  Word object if id matches, null otherwise
	 */
	Word search(String word) {
		Word current = head;
		while (current != null) {
			if (current.getWord().compareTo(word) == 0 )
				return current;
			current = current.getNext();
		}
		return null;
	}

	/**
	 * Removes the element at the end of the list
	 * 
	 * @return the Word object removed
	 */
	Word removeEndElement() {

		if (isEmpty())
			return null;

		Word current = head;
		Word previous = null;

		// get last node and one before last
		while (current.getNext() != null) {
			previous = current;
			current = current.getNext();
		}

		if (previous == null)
			head = null;
		else
			previous.setNext(null);

		return current;

	}

	/**
	 * Removes the element at the beginning of the list
	 * 
	 * @return the Word object removed
	 */
	Word removeFirstElement() {

		if (isEmpty())
			return null;

		Word current = head;
		head = head.getNext();
		return current;
	}

	/**
	 * Removes an element with the matching  Word
	 * 
	 * @param word the word to be removed
	 * @return the  Word object removed
	 */
	Word removeElement(String word) {

		// check if linked list is empty
		if (isEmpty())
			return null;

		Word current = head;
		Word previous = null;

		while (current != null && current.getWord().compareTo(word) != 0) {

			if (current.getNext() == null)
				return null;

			previous = current;
			current = current.getNext();
		}

		if (current == head) {
			head = head.getNext();
		} else {
			previous.setNext(current.getNext());
		}
		return current;

	}

	/**
	 * Checks if the linked list is empty
	 * 
	 * @return true if empty, false otherwise
	 */
	public boolean isEmpty() {
		return (head == null);
	}

//	/**
//	 * Sorts the list using bubble sort algorithm
//	 */
//	public void sort() {
//
//		// check if linked list is empty
//		if (isEmpty())
//			return;
//
//		// another method (for reference only)
//		boolean swapped = false;
//
//		Word lastChecked = head;
//		do {
//			Word previous = null;
//			Word current = head;
//			Word next = current.getNext();
//			swapped = false;
//
//			while (next != null && next != lastChecked) {
//				if (current.getId() > next.getId()) {
//					swap(current, next, previous);
//					current = next;
//					swapped = true;
//				}
//				previous = current;
//				current = previous.getNext();
//				next = current.getNext();
//			}
//
//			lastChecked = current;
//		} while (swapped);
//	}

//	/**
//	 * Swaps the content (i.e. data) of two Student nodes (current and next)
//	 * 
//	 * @param current  the current Student node to be swapped
//	 * @param next     the next current Student to be swapped with
//	 * @param previous the previous Student node
//	 */
//	private void swap(Word current, Word next, Word previous) {
//
//		if (previous != null) {
//			previous.setNext(next);
//		} else {
//			head = next;
//		}
//
//		Word temp = next.getNext();
//		next.setNext(current);
//		current.setNext(temp);
//	}

	/**
	 * Returns the size or the number of elements present in the LinkedList
	 * 
	 * @return the total number of elements (nodes) present in the LinkedList
	 */
	public int size() {
		return getNumberOfNodes();
	}

	/**
	 * Counts number of nodes in the linked list
	 * 
	 * @return the total number of nodes
	 */
	private int getNumberOfNodes() {
		Word cursor = head;
		int count = 0;
		while (cursor != null) {
			cursor = cursor.getNext();
			count++;
		}
		return count;

	}

}