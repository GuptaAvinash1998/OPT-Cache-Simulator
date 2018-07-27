
//your implementation of PriorityQueue interface
//header comments here


//One additional requirement is the value type T must be Comparable.
//When two items are compared to determine the order in priority queue, follow the rules below:
//1. use the priority of them to determine the order 
//2. if they are of the same priority, use their values (of type T) to determine the order
//3. if they are of the same priority and same value, any order is fine

/**
 * 
 * @author Avinash Gupta
 * CS310 Spring 2018
 * Project 4
 * George Mason University 
 *
 * @param <T> This is the type that we are defining in the object deceleration
 * 
 * File Name: MyPriorityQueue.java
 *
 * This is a class where we are defining the priority queue. In this class, I implemented a priority queue 
 * using a doubly linked list that works like a queue. The highest priority is in the front and the lowest 
 * is in the back. The highest priority is deleted from the front.
 */
public class MyPriorityQueue<T extends Comparable<T>> implements PriorityQueue<T>
{
	
	// you decide the internal design of your class:
	//  - it must implement the provided PriorityQueue interface
	//  - it cannot have any other public members (attributes or method) other than
	//    the public constructor and the methods defined in PriorityQueue interface


	static final int INF = Integer.MAX_VALUE; // the max priority to use: infinity
	private int size = 0;//This keeps track of the size of the priority queue
	private PriorityLinkedList head;//reference to the head
	private PriorityLinkedList tail;//reference to the tail
	private PriorityLinkedList temp;//temporary reference
//-----------------------------------------------------------------------------------------------------------	
	
	/**
	 * 
	 * @author Avinash Gupta
	 *
	 *This is a class where we define each node in the priority queue. Since it's a doubly linked list, it has 2 references
	 */
	private class PriorityLinkedList{
		
		T element;
		int priority;
		PriorityLinkedList next;
		PriorityLinkedList prev;
		
		/**
		 * Constructor
		 * @param element The element data
		 * @param priority The priority data
		 */
		PriorityLinkedList(T element, int priority) {
			
			this.element = element;
			this.priority = priority;
			next = null;
			prev = null;
		}
		
		/**
		 * Sets the next to e
		 * @param e This is the element that next is being set to
		 */
		private void setNext(PriorityLinkedList e) {
			
			next = e;
		}
	}
//-----------------------------------------------------------------------------------------------------------
	/**
  * Construct an empty PriorityQueue.
  */
 public MyPriorityQueue()
 {

 }
 
	// make sure you implement all methods in Sequence interface

	//------------------------------------
	// example test code... edit this as much as you want!
	// note: you might want to add a method like printPQ() for debugging purpose
	
	public static void main(String[] args){
		MyPriorityQueue<String> pq = new MyPriorityQueue<String>();
		
		if(pq.size()==0 && pq.remove()==null && !pq.contains("a", 4)) 	{
			System.out.println("Yay 1");
		}
		
		pq.insert("a",4);
		pq.insert("b",10);
		pq.insert("h",2);
		
		if(pq.size()==3 && pq.peek().equals("b") && pq.contains("a", 4) && pq.contains("h", 2) 
			&& pq.contains("b",10)) {
			System.out.println("Yay 2");
		}

		if((pq.remove()).equals("b") && !pq.contains("b",10) & pq.size()==2 
			&& (pq.peek().equals("a")) ) {
			System.out.println("Yay 3");
		}

		pq.insert("d",4);
		if ((pq.peek()).equals("d")) {System.out.println("Yay 4");}
				
		pq.insert("b",10);
		pq.insert("f",3);
		pq.updatePriority("a",3);
		if (pq.size() == 5 && pq.contains("a",3) && pq.contains("b",10) && pq.contains("d",4) 
			&& pq.contains("f",2) && pq.contains("h",1)) {
			System.out.println("Yay 5");
		}


	}

	@Override
	/**
	 * returns the size of the list
	 */
	public int size() {
		
		return size;
	}

	@Override
	/**
	 * returns the element with the highest priority
	 */
	public T peek() {
		
		return head.element;
	}

	@Override
	/**
	 * removes the element with the highest priority
	 */
	public T remove() {
		
		if(size == 0) {//if there is no list, there is nothing to remove
			return null;
		}
		
		if(size == 1) { //if there is one element, remove that element
			head = null;
			size--;
			return null;
		}
		T tempHolder = head.element;//gets the head element
		
		temp = head.next;//stroes the next element and makes it the head and removes the previous head
		head.next = null;
		temp.prev = null;
		head = temp;
		size--;
		return tempHolder;
	}

	@Override
	/**
	 * inserts the value into the list, but here, we are inserting in ascending order so that we know where the element with the highest priority is
	 */
	public void insert(T v, int p) {
		
		PriorityLinkedList temp2;
		
		if(size == 0) { //if there are no elements, make a new one
			head = new PriorityLinkedList(v,p);
			tail = head;
			size++;
			return;
		}
		
		if(size == 1) {//if there is one element, only the head will exist
			
			if(p > head.priority) { //if the priority to insert is greater than the head's priority, insert before head
				
				temp = new PriorityLinkedList(v,p);
				head = temp;
				head.setNext(tail);
				tail.prev = head;
				size++;
				return;
			}
			
			if(p == head.priority) {//if its equal, we compare the T element
				
				if(v.compareTo(head.element) < 0) { //if its less than the head's, then we add after the head
					
					temp = new PriorityLinkedList(v,p);
					temp.prev = head;
					head.setNext(temp);
					tail = temp;
					size++;
					return;
					
				}else if(v.compareTo(head.element) > 0) { //else before the head
					
					temp = new PriorityLinkedList(v,p);
					head = temp;
					temp.setNext(tail);
					tail.prev = temp;
					size++;
					return;
					
				}else { //if its equal, then it does not matter where we insert it, but I inserted it before the head
					
					temp = new PriorityLinkedList(v,p);
					temp.setNext(head);
					head.prev = temp;
					head = temp;
					size++;
					return;
				}
				
			}else {//if less than, then we are inserting after head
				temp = new PriorityLinkedList(v,p);
				temp.prev = head;
				head.setNext(temp);
				tail = temp;
				size++;
				return;
			}
		}else {//if there is more than one element
			
			//first compares with the head, it's checking if it's less than or equal to the head
			//if it is equal, then it'll compare the string element
			
			//HEAD CONDITIONS / HEAD CHECK
			if(p > head.priority) { //if the new priority is greater than the head's priority, we add before the head
				
				temp = new PriorityLinkedList(v,p);
				temp.setNext(head);
				head.prev = temp;
				head = temp;
				size++;
				return;
			}
			
			if(p == head.priority) { //if the priority is the same, then we compare the string element
				
				if(v.compareTo(head.element) < 0) {//if the new string element is greater than the head's, we add before the head
					
					temp = new PriorityLinkedList(v,p);
					temp.setNext(head);
					head.prev = temp;
					head = temp;
					size++;
					return;
					
				}else if(v.compareTo(head.element) < 0) {//if the new string element is less than the head's, we add right after the head
					
					temp = head.next;
					temp2 = new PriorityLinkedList(v,p);
					head.setNext(temp2);
					temp2.prev = head;
					temp2.setNext(temp);
					temp.prev = temp2;
					size++;
					return;
					
				}else { //if it is equal to the head, then it does not matter if we add it before or after the head, but in this case I chose to add it before the head
					
					temp = new PriorityLinkedList(v,p);
					temp.setNext(head);
					head.prev = temp;
					head = temp;
					size++;
					return;
				}
			}
			 //if it does not satisfy any of the head conditions, we check the tail conditions
			//we are checking if the priority is greater than or equal to the tail's priority
			
			//TAIL CONDITIONS / TAIL CHECK
			if(p < tail.priority) { //if the new priority is greater than the tail's priority, we add after the tail
				
				temp = new PriorityLinkedList(v,p);
				tail.setNext(temp);
				temp.prev = tail;
				tail = temp;
				size++;
				return;
			}
			
			if(p == tail.priority) {//if the new priority is equal to the tail's priority, we then compare the string elements
				
				if(v.compareTo(tail.element) > 0) { //if the element is less than the tail's element, we add right before the tail
					
					temp = tail.prev;
					temp2 = new PriorityLinkedList(v,p);
					temp.setNext(temp2);
					temp2.prev = temp;
					temp2.setNext(tail);
					tail.prev = temp2;
					size++;
					return;
					
				}else if(v.compareTo(tail.element) < 0) { //if the new element data is greater than the tail data, we add after the tail
					
					temp = new PriorityLinkedList(v,p);
					tail.setNext(temp);
					temp.prev = tail;
					tail = temp;
					size++;
					return;
					
				}else { //if the new element data is equal to the tail's data, it does not matter where we add it, we can add it either before or after the tail, I chose to add it after the tail
					
					temp = new PriorityLinkedList(v,p);
					tail.setNext(temp);
					temp.prev = tail;
					tail = temp;
					size++;
					return;
				}
				
			}else { //if the priority is not in greater than or equal to the head and less than or equal to the the tail, it has to be inserted in the middle somewhere
				
				temp = head;//stores the head
				
				
				while(temp.next != null) {
					
					temp2 = temp.next;//stores the element after temp
					
					if(p < temp.priority && p >= temp2.priority) {//if the value of the element is in between the 2 nodes, it gets inserted between them
						
						PriorityLinkedList temp3 = new PriorityLinkedList(v,p);
						temp.setNext(temp3);
						temp3.prev = temp;
						temp3.setNext(temp2);
						temp2.prev = temp3;
						size++;
						return;
						
					}else{//else moves to the next
						
						temp = temp.next;
					}
				}
				
			}
			
		}
		
	}

	@Override
	/**
	 * This updates the priority of all the elements, if it is less than or equal to p, it reduces all the element's priorities by 1
	 */
	public void updatePriority(T v, int p) {
		
		temp = head;//starts with the head
		
		while(temp != null) {
			
			if(temp.element.compareTo(v) == 0) {//if it's the same, then it updates the priority
				
				temp.priority = p;
				temp = temp.next;
			}else {
				
				if(temp.priority <= p) {//if it's less than, then it updates the priority
					
					temp.priority--;
					temp = temp.next;
				}else {
					
					temp = temp.next;
				}
			}
		}
		
	}

	@Override
	/**
	 * This loops through the list and finds if the pair exists in the list or not
	 */
	public boolean contains(T v, int p) {
		
		temp = head;
		
		while(temp != null) {
			
			if(temp.element.equals(v) && temp.priority == p) {
				return true;
			}else {
				temp = temp.next;
			}
		}
		return false;
	}


}