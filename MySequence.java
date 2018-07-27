import java.util.TreeSet;
//your implementation of Sequence interface
//header comments here

/**
 * 
 * @author Avinash Gupta
 * CS310 Spring 2018
 * Project 4
 * George Mason University
 *
 * @param <T> This is the type that we specify during the object deceleration
 * 
 * File Name: MySequence.java
 *
 * This is the class that implements the sequence that you are using to store the time. Here I am using a tree set.
 * The tree set class in java implements a Red and Black Tree.
 */
public class MySequence<T extends Comparable<T>> implements Sequence<T>{

	// you decide the internal design of your class:
	//  - it must implement the provided Sequence interface
	//  - it cannot have any other public members (attributes or method) other than
	//    the public constructor and the methods defined in Sequence interface
	
	private TreeSet<T> sequence; //This is the sequence
	private int size = 0;//keeps track of the size
	private Object[] sortingArray;//stores the elements in an array
	
	public MySequence(){

		sequence = new TreeSet<T>(); //Makes a new tree set object of type T
	}
	
	// make sure you implement all methods in Sequence interface


	//------------------------------------
	// example test code... edit this as much as you want!
	public static void main(String[] args){
		MySequence<Integer> seq = new MySequence<Integer>();
		
		if(seq.size()==0 && !seq.contains(11) && seq.countNoSmallerThan(10) == 0 
			&& seq.toStringAscendingOrder().equals("")) 	{
			System.out.println("Yay 1");
		}
		
		seq.insert(11);
		seq.insert(5);
		
		if(seq.insert(200) && seq.size()==3 && seq.contains(11) 
			&& seq.countNoSmallerThan(10) == 2 && !seq.remove(20)) 	{
			System.out.println("Yay 2");
		}
		
		seq.insert(112);
		seq.insert(50);
		seq.insert(20);
		
		if(seq.remove(20) && !seq.contains(20) && !seq.insert(200)
			&& seq.countNoSmallerThan(50) == 3 
			&& seq.toStringAscendingOrder().equals("5 11 50 112 200 ")) 	{
			System.out.println("Yay 3");
		}

	}

	@Override
	/**
	 * Inserts the element in the tree
	 */
	public boolean insert(T v) {
		
		return sequence.add(v);
	}

	@Override
	/**
	 * Removes the element in the tree
	 */
	public boolean remove(T v) {
		
		return sequence.remove(v);
	}

	@Override
	/**
	 * Loops through the tree and checks if the value exits or not
	 */
	public boolean contains(T v) {
		
		return sequence.contains(v);
	}

	@Override
	/**
	 * Checks how many elements are greater than or equal to the value v
	 */
	public int countNoSmallerThan(T v) {

		return sequence.tailSet(v).size(); //converts all elements that are greater than or equal to v in an Set and returns the size of the set.
	}

	@Override
	/**
	 * Returns the number of elements in the tree
	 */
	public int size() {
		
		size = sequence.size();
		return size;
	}


	@Override
	/**
	 * Returns all the elements in the tree as a string but in ascending order
	 */
	public String toStringAscendingOrder() {
		
		
		sortingArray = sequence.toArray(); //converts all the elements into an array representation
		String output = "";
		
		for(int i=0;i<sortingArray.length;i++) {
			
			output += sortingArray[i].toString() + " "; //Since the tree is a Red and Black tree, the elements will be in ascending order
		}
		return output;
	}
}

