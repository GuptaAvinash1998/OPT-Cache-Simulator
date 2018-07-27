//The generic interface that used to store a collection of values as a sorted sequence
//No duplicate values allowed

//T can be any type that implements Comparable<T> interface
//T must implement a .compareTo(T another) method

/**
 * @author Yutao Zhong and Jitin Krishnan
 * CS310 Spring 2018
 * Project 4
 * George Mason University
 * 
 * File Name: Sequence.java
 *
 * This is where we define what goes into the sequence
 */
public interface Sequence<T extends Comparable<T>> {

	// add a new value v into the collection
	// return true if added successfully
	// return false if value is already present (no duplicate added)
	//
	// O(logN) given N as the number of values stored in sequence
	public boolean insert (T v); 
	
	// delete value v from the collection
	// return true if removed successfully
	// return false if value is not present
	//
	// O(logN) given N as the number of values stored in sequence
	public boolean remove (T v);


	// find whether value v is present in collection
	// return true if value is present
	// return false otherwise
	//
	// O(logN) given N as the number of values stored in sequence
	public boolean contains (T v);


	// count how many values in collection are greater than or equal to v
	// return the integer count
	//
	//  Note: T must implement .compareTo(T another) to define how to determine 
	//        which one is greater than the other one between any two values of type T
	//
	// O(logN) given N as the number of values stored in sequence
	public int countNoSmallerThan (T v);
	
	// count how many values present in collection 
	// return the integer count
	//
	// O(1)
	public int size();

	// return a string representation of all values in collection, in ascending order
	// - exactly one space should be appended after every value (including the last value)
	//
	// O(N) given N as the number of values stored in sequence
	public String toStringAscendingOrder();
	
}


