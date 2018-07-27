//The generic interface that used to maintain a max priority queue
//
//All max priority queue properties must be maintained:
//- at any time, with the max priority is kept at the top
//- at any time, the priority of a parent node is higher than the priority of its children
//

/**************************************************************************
 * @author Yutao Zhong and Jitin Krishnan
 * CS310 Spring 2018
 * Project 4
 * George Mason University
 * 
 * File Name: ExpressionBinaryTree.java
 * 
 * This is where we declare the methods that the priority queue needs
 ***************************************************************************/
public interface PriorityQueue<T>{

	// count how many items present in priority queue
	// return the integer count
	//
	// O(1)
	public int size();

	// return the top item (of the max priority) in the priority queue
	// return null if empty
	//
	// O(1)
	public T peek();

		
	// remove the top item (of the max priority) from the priority queue
	// return that item to be deleted
	// return null if empty
	//
	// O(logN): N is the number of items in priority queue
	public T remove();
	
	// add an item v with priority p into the priority queue
	// no checking whether a duplicate value is already in queue
	// return: nothing
	// 
	// O(logN): N is the number of items in priority queue
	public void insert(T v, int p);
	
	// perform a priority update for items in the priority queue based on the following rules
	//  - If the item is the same as v: set the priority as p
	//  - If the item is different from v, compare their priorities
	//      - For any item x with a priority <= v's priority, decrement x's priority by 1
	//      - Otherwise, do not change x's priority
	// 
	// Hint: perform necessary adjustment to ensure a valid priority queue after updating
	//
	// O(N): N is the number of items in priority queue
	public void updatePriority(T v, int p);
	
	// check whether there is a value v associated with priority p in the priority queue
	// return true if present
	// return false otherwise
	//
	// O(N): N is the number of items in priority queue
	public boolean contains(T v, int p);
	
	
	
}


