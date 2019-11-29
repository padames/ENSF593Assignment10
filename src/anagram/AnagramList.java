package anagram;

public class AnagramList {

	/**
	 * The head of the Node Object Linked List
	 */
	private Node head;

	/**
	 * Constructs a AnagramList Object
	 */
	public AnagramList() {
		head = null;
	}

	/**
	 * Inserts a Node object to the end of the Linked List
	 * 
	 * @param s the Node's object to be inserted
	 */
	public void insertToEndOfList(Node s) {

		if (head == null) {
			head = s;
		} else {
			getLastNode().setNext(s);
		}

	}

	/**
	 * Inserts a  Node object to the front of the Linked List
	 * 
	 * @param s the  Node's object to be inserted
	 */
	public void insertToFrontOfList(Node s) {
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
	private void recursivePrintList(Node cursor) {

		if (cursor != null) {

			recursivePrintList(cursor.getNext());
			System.out.println(cursor);
		}
	}

	/**
	 * Prints Linked list (iteratively)
	 */
	public void printList() {
		Node cursor = head;
		while (cursor != null) {
			System.out.println(cursor);
			cursor = cursor.getNext();
		}
	}
	
	/**
	 * Prints Linked list (iteratively)
	 */
	public void printListInOneLine() {
		Node cursor = head;
		while (cursor != null) {
			System.out.print(cursor + " ");
			cursor = cursor.getNext();
		}
		System.out.println();
	}

	/**
	 * Gets last node (i.e.  Node object) in the Linked list
	 * 
	 * @return
	 */
	private Node getLastNode() {
		Node cursor = head;
		while (cursor.getNext() != null) {
			cursor = cursor.getNext();
		}
		return cursor;
	}

	/**
	 * Gets the head ( i.e.  Node)
	 * 
	 * @return the  Node object at the head
	 */
	public Node getHead() {
		return head;
	}

	/**
	 * Inserts a new given Node object in alphabetical order.
	 * It inserts duplicates. This happens between 
	 * inner nodes (nodes between the head and the tail)
	 * because it inquires if the current node
	 * is less than the new node and if it is equal to or greater than 
	 * the next node, before inserting.
	 * 
	 * @param newNode the Node's object to be inserted
	 */
	void InsertInOrder(Node newNode) {

		Node current = head;
		Node previous = null;

		while (current != null && newNode.isEqualOrGreatherThan(current)) {

			// TODO: do I need to exclude duplicate nodes
//			if (newNode.isEqual(current))
//				return;
			previous = current;
			current = current.getNext();
		}

		// previous is the predecessor node
		// if previous null, then linked list is empty/
		// so set head to the new node, otherwise set
		// previous to point to the new node
		if (previous == null)
			head = newNode;
		else
			previous.setNext(newNode);

		// set the new node to point to current node
		newNode.setNext(current);

	}

	
	/**
	 * Returns a Node object if the id matches a  Node in the list
	 * 
	 * @param id the word to search for 
	 * @return the  Node object if id matches, null otherwise
	 */
	Node search(String word) {
		Node current = head;
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
	 * @return the Node object removed
	 */
	Node removeEndElement() {

		if (isEmpty())
			return null;

		Node current = head;
		Node previous = null;

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
	 * @return the Node object removed
	 */
	Node removeFirstElement() {

		if (isEmpty())
			return null;

		Node current = head;
		head = head.getNext();
		return current;
	}

	/**
	 * Removes an element with the matching  Node
	 * 
	 * @param word the word to be removed
	 * @return the  Node object removed
	 */
	Node removeElement(String word) {

		// check if linked list is empty
		if (isEmpty())
			return null;

		Node current = head;
		Node previous = null;

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
		Node cursor = head;
		int count = 0;
		while (cursor != null) {
			cursor = cursor.getNext();
			count++;
		}
		return count;

	}
    
    /**
	 * Returns a string suitable for printing the contents of the list
	 */
	@Override
	public String toString() {
		String str = "";
		Node cursor = head;
		while (cursor != null) {
			str += cursor + " ";
			cursor = cursor.getNext();
		}		
		return str;
	}

}