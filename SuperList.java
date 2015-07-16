/* Viet Nguyen 
 * TCSS 342
 * Spring 2014
 * HomeWork 2
 */
import java.util.Iterator;

/**
 * This program representing a super List
 * 
 * @author Viet Nguyen
 * @version 1
 * @param <T> Generic Type
 */
public class SuperList<T> implements Iterable<T> {
	
	Node<T> front;
	int maxNum; // max number of elements in a node
	static int length; // is the actually number of element in the super list
	
	// constructing the super list
	public SuperList(int maxNum) {
		this.maxNum = maxNum;
		front = null;
		length = 0;
	}
	
	// construct and return the iterator
	public Iterator<T> iterator() {
        return new SuperListIterator<T>(front);
	}
	
	/**
	 * Add an item to different location
	 * 
	 * @param i is location to add item to the super list
	 * @param newItem is generic type of element to be added to the list
	 */
	public void add(int i, T newItem) {
		
		if (front == null) { // for empty list case
			Node<T> newNode = new Node<T>(maxNum);
			newNode.array[0] = newItem;
			newNode.size = 1;
			front = newNode;
			length = 1;
		} else if (i >= length)  { 
			appendCase(i, newItem); // append case to the end if i is > number of items in the list
		
		} else if (i < length) { // insert case
			insertCase(i, newItem);	
		}
	} // end add method
	
	
	/**
	 *  Help method.
	 *  Append case to the end if i is > number of items in the list
	 * @param i
	 * @param newItem
	 */
	private void appendCase(int i, T newItem) {
		Node<T> current = front;
		while (current.next != null) {
			current = current.next;
		}
		if (current.notFull) {
			current.array[current.size] = newItem;
			current.size ++;
			length ++;
			if (current.size == maxNum)
				current.notFull = false;
		} else {
			Node<T> newNode = new Node<T>(maxNum);
			newNode.array[0] = newItem;
			newNode.size = 1;
			length ++;
			current.next = newNode;
		}
	}
	
	/**
	 * Help method - insert Case
	 * @param i
	 * @param newItem
	 */
	private void insertCase(int i, T newItem) {
		Node<T> current = front;
		int currentI = 0;
		boolean notDone = true;
		while (i < length && current != null && notDone) {
			if (i <= currentI + current.size) { //
				int indexNode = i - currentI; // actual index of the current node
				// insert new item to current node
				if (current.notFull) {
					if(current.array[indexNode] == null) {
						current.array[indexNode] = newItem;
					} else { // shift elements to right before adding new item
						for (int g = current.size - 1; g >= indexNode; g--)
							current.array[g + 1] = current.array[g];
						current.array[indexNode] = newItem;
					}
					current.size += 1;
					if (current.size == maxNum)
						current.notFull = false;
					notDone = false;
					
				// if node is full, split into 2 nodes
				} else if (!current.notFull) { 
					Node<T> newNode = new Node<T>(maxNum);
					// Test Size System.out.println("\n " + newNode.maxNum);
					for (int g = 0; g < maxNum / 2; g++) {
						newNode.array[g] = current.array[maxNum / 2 + g];
						current.array[maxNum / 2 + g] = null;
					}
					
					// if i less than half size of previous node
					if (i - currentI <= maxNum / 2) {
						// shift element to right before insert newItem.
						for (int g = maxNum - 1; g > i; g--)
							current.array[g] = current.array[g - 1];
						current.array[i - currentI] = newItem;
					} else { // put newItem at new node
						int index = i - currentI - maxNum / 2 - 1;
						// shift element to right before insert newItem.
						for (int g = maxNum - 1; g > index - 1; g--)
							newNode.array[g] = newNode.array[g - 1];
						newNode.array[index] = newItem;
					}
					newNode.size = maxNum / 2;
					current.size = maxNum / 2;
					current.size += 1;
					current.notFull = true;
					newNode.next = current.next;						
					current.next = newNode;
					break;
				}
			} 
			currentI += current.size;
			current = current.next;
		}
		length ++;
	} // end of insert item to node
	
	/**
	 * Remove an item at location i.
	 * 
	 * @param i location of element in the super list
	 */
	public void remove(int i) {
		Node<T> current = front;
		Node<T> prev = front;
		int currentI = 0;
		boolean notDone = true;
		// checking each node
		while (i < length && current != null && notDone) {
			// System.out.println("current " + currentI + " current size: " + current.size);
			if (i < currentI + current.size) { 
				int indexNode = i - currentI; // actual index of the current node
				current.array[indexNode] = null;
				current.notFull = true;
				current.size -= 1;
				notDone = false;
				indexNode += 1;
				// shift elements to left after remove
				while (indexNode < maxNum && current.array[indexNode] != null) {
					current.array[indexNode - 1] = current.array[indexNode];
					indexNode ++;
				}
				current.array[indexNode - 1] = null; // set last index to null after move
				
				// if node less than half size after remove
				if (current.size < maxNum / 2) {
					// remove in front case
					// combine with next node 1st case after removing front
					if (prev == current && maxNum - current.next.size >= current.size) {
						combineNextNode(prev, current);
						
				    // borrow next node an element if there is one available
					} else if (prev == current && current.next.size > maxNum / 2) {
						borrowNextNode(prev, current);					

					// combine previous node if there are rooms	
					} else if (prev != current && maxNum - prev.size >= current.size) { // remove in the middle
						for (int g = 0; g < current.size; g++) {
							prev.array[prev.size] = current.array[g]; // add element base on g
							prev.size ++; // update node size
						}
						current = current.next;
						prev.next = current;
						
					// combine with next node
					} else if (prev != current && current.next != null && maxNum - current.next.size >= current.size) {
						combineNextNode(prev, current);
						
					// borrow element from previous node
					} else if (prev != current && prev.size > current.size) {
						for (int g = current.size - 1; g >= 0; g--)  // shift elements to right in current node
							current.array[g + 1] = current.array[g];
						// borrow 1 element from previous node
						current.array[0] = prev.array[prev.size - 1];
						current.size += 1;
						prev.array[prev.size - 1] = null;
						prev.size -= 1;
						prev.notFull = true;
					}
				}
			}
			if (notDone) {
			currentI += current.size;
				if (current != prev)
					prev = prev.next;
				current = current.next;
			}
		}
		length --; // decrement length after remove
	}
	
	/**
	 * Help method - combine current node with the next one
	 * @param prev
	 * @param current
	 */
	private void combineNextNode(Node<T> prev, Node<T> current) {
		// creating spaces for the next node 
		for (int j = current.next.size - 1; j >= 0; j--) 
			current.next.array[j + current.size] = current.next.array[j];	
		// move current elements to next node				
		for (int g = 0; g < current.size; g++) 
			current.next.array[g] = current.array[g];
		// update next node size;
		current.next.size += current.size;
		if (current.next.size == maxNum)
			current.next.notFull = false;
		
		// prev == current case
		if (prev == current) {
			current.size = 0;
			prev = prev.next;
			current = current.next;
			front = front.next;
		} else if ( prev != current) { // prev != current
			
			// remove current node after combine
			current = current.next;
			prev.next = current;
		}
	}
	
	/**
	 * Help method - Borrow an element from the next node;
	 * @param prev
	 * @param current
	 */
	private void borrowNextNode(Node<T> prev, Node<T> current) {
		
		// copy 1st value of next node to the end of current node
		current.array[current.size] = current.next.array[0];
		current.size += 1;
		// shift left all elements of next node by 1 after its 1st element is borrowed
		for (int g = 1; g < current.next.size; g++) 
			current.next.array[g - 1] = current.next.array[g];
		current.next.array[current.next.size - 1] = null; // set last element to null after move left
		current.next.size -= 1; // reduce size by 1 after shift
	}    
	
	/**
	 * display all the element in super list.
	 */
	public void displayAll() {
		Node<T> current = front;
		while (current != null) {
			int i = 0;
			System.out.print( "(");
			while (i < current.array.length && current.array[i] != null) {
				System.out.print(current.array[i] + " ");
				i++;
			}
			System.out.print( ") ");
			current = current.next;
		}
		
	}
	
}

/**
 *  This representing a super node.
 * @author V
 *
 * @param <T>
 */
class Node<T> {
	T[] array;
	Node<T> next;
	int maxNum;
	int size;
	boolean notFull; // keep track whether node is full or not
	@SuppressWarnings("unchecked")
	Node(int maxNum) {
		this.maxNum = maxNum;
		notFull = true;
		array = (T[]) new Object[maxNum];
	}
}


/**
 * Iterator
 * 
 * @author V
 *
 * @param <T>
 */
class SuperListIterator<T> implements Iterator<T> {
	private int index;
	private Node<T> current;
    public SuperListIterator(Node<T> current) {
        this.current = current;
        index = 0;
    }
    
    public T next() {
        if (!hasNext()) {
            return null;
        }
        T data = current.array[index];
        index++;
    	if (index + 1 > current.size  || current.array[index] == null) {
    		index = 0;
    		current = current.next;
    	}
        
        return (T) data;
    }
    
    public boolean hasNext() {
    	boolean result = false;
    	if (current != null && current.array[index] != null) {
    		result = true;
    	}
        return result;
    }

	@Override
	public void remove() {
		// 
	}  
}