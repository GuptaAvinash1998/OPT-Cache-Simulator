import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.Stack;
import java.io.PrintWriter;
import java.io.FileWriter;

// class to calculate (LRU) stack distance for any given sequence of accesses
/**
 * 
 * @author Avinash Gupta
 * CS310 Spring 2018
 * Project 4
 * George Mason University
 *
 * File Name: StackDistCollector.java
 *
 * This is a class that calculates the stack distance. It is done in the access method.
 */
public class StackDistCollector{
	
	private char[] elementsArray;//This is a character array that holds the elements in the string
	
	/**
	 * This is where we calculate the stack distance.
	 * @param address This is the address that we are calculating the stack distance for
	 * @return Returns the stack distance
	 */
	public Integer access(int address){
		
		int stack_dist = 0; //This stores the stack distance
		boolean decision = false; //This is a condition that we check in the calculation
		
		if(!symbolTable.hasSymbol(address)) { //If the address is not present in the table
		
			symbolTable.putRecord(address, new SymbolRecord(address,time));//Then we add the address into the table
			pastAccesses.insert(time);//we insert the current time into the tree set
			time++;//increment the time
			return null;//since the address was just inserted, the stack distance would be infinity, so the value would be null
			
		}else { //if it is present
			int cntr = 0;//this is what we use to calculate the stack distance
			pastAccesses.insert(time);//inserts the current time into the tree set
			String elements = pastAccesses.toStringAscendingOrder();//gets the to string representation of the elements in the tree set
			int past = symbolTable.getRecord(address).getLastAccessTime(); //stores the last time this address was accessed
			
			elementsArray = elements.toCharArray();//converts the toString into an array
			
			for(int i=0;i<elementsArray.length;i++) {//loops through the array
				
				if(decision) {
					
					if(Character.isWhitespace(elementsArray[i])) {//if the element is a white space, we ignore it
						continue;
					}else if(Character.toString(elementsArray[i]).equals(Integer.toString(time))) { //if the element is the current time, we return break from the loop and that is the stack distance
						break;
					}else { //if it is not the current time, we increment the counter
						cntr++;
						continue;
					}
				}
				//The stack distance is basically the distance from the past access time to the current time
				if(elements.charAt(i) == Integer.toString(past).charAt(0)) {//if the element is equal to the last access time of the address
					cntr++;//increment the counter
					decision = true;//change the decision
				}
			}
			stack_dist = cntr; //stack_dist = size-#iterations to the element
			pastAccesses.remove(symbolTable.getRecord(address).getLastAccessTime());//once calculated, then we remove the old accesstime from the tree
			symbolTable.getRecord(address).setLastAccessTime(time);//we update the access time of the address
			time++;//increment the time
			return stack_dist;
		}

		// process one access and calculate the LRU stack distance for that access
		// - if the address has never been accessed before, the distance is infinity  
		// - otherwise the distance is how many distinct addresses have been accessed 
		//    since the address was accessed last time (including itself)
		//
		// return the calculated stack distance
		// if the distance is infinity, return null
		// 
		// Hint: get the help from symbolTable and pastAccesses
		// Note: also need to perform maintenance tasks before return, including:
		// 	1. update symbolTable 
		//  2. update pastAccesses 
		//  3. advance time to the next cycle
		//
		// symbolTable:
		//   - must have one entry for every address that have been accessed
		//   - must store the latest access time for that address in the table
		//
		// pastAccesses:
		//   - also store the latest access time for every distinct address 
		//   - store them as an ordered collection and support efficient operation
		//
		// time:
		//   - indicate which cycle the address is accessed
		//   - move to the next cycle for a new reference
		
		
	}


	// DO NOT MODIFY BELOW THIS LINE
    //-----------------------------------------------
    //-----------------------------------------------

	private int time; 
	// integer value to simulate clock cycle time for accesses
	// initialize as zero for the start of each sequence
	// advance by one for each access in the sequence


	MySymbolTable<Integer, SymbolRecord> symbolTable;  
	// symbol table to remember the distinct addresses we have accessed
	
	MySequence<Integer> pastAccesses; 
	// sequence to remember the time of distinct accesses in the past (before this.time)
	
	Stack<Integer> distSequence;  
	// storage of the calculated distances for each access in the sequence up to this.time
	// storage organized as a stack (FILO)
	
	// constructor
	public StackDistCollector(){
		time = 0;
		symbolTable = new MySymbolTable<Integer, SymbolRecord>();
		pastAccesses = new MySequence<Integer>();
		distSequence = new Stack<Integer>();
		
	}
	
	// accessor
	public int getTime(){
		return time;
	}
	
	// collect the stack distances for a new sequence
	// input: a plain text file with a sequence of integer numbers separated by space
	// output: another plain text file with the calculated sequence of stack distances
	//         in REVERSE order (again a sequence of integer numbers separated by space)
	// 
	public void processSequence(String inFileName, String outFileName) throws IOException{
		// reset everything to start over
		time = 0;
		pastAccesses = new MySequence<Integer>();
		symbolTable = new MySymbolTable<Integer, SymbolRecord>();
		distSequence = new Stack<Integer>();

		// open the input file
		Scanner input = new Scanner(new File(inFileName));
		
		// process accesses one by one and push the distance calculated onto a stack
		while(input.hasNext()) {
			int address = Integer.parseInt(input.next());
			// add it to distance stack
			distSequence.push(access(address));
		}
	
		// output distances into the outFile
		PrintWriter out = new PrintWriter(new FileWriter(outFileName));
		while (!distSequence.isEmpty()){
			Integer dist = distSequence.pop();
			if (dist!=null)
				out.print(dist.toString()+" ");
			else
				out.print("inf ");
		}
		out.close();
	}
	
	
	
	public static void main(String[] args) throws IOException{
	
		StackDistCollector collector = new StackDistCollector();
		
		if(collector.access(20)==null && collector.symbolTable.size() == 1
			&& collector.pastAccesses.contains(0)){
			System.out.println("Yay 1");
		}

		if(collector.access(20)==1 && collector.symbolTable.getRecord(20).getLastAccessTime() == 1
			&& collector.pastAccesses.contains(1) && !collector.pastAccesses.contains(0)){
			System.out.println("Yay 2");
		}

		if(collector.access(32)==null && collector.access(20)==2 && collector.access(20)==1 
			&& collector.access(20)==1 && collector.access(32)==2) {
			System.out.println("Yay 3");
		}
		
		if(collector.getTime() == 7 && collector.pastAccesses.toStringAscendingOrder().equals("5 6 ") ){
			System.out.println("Yay 4");
		}
		
		collector.processSequence("inputs/input1_reverse.txt","inputs/input1_my_distance.txt");
		// you can manually inspect the output file: inputs/input1_my_distance.txt
		// expected output should be identical to inputs/input1_distance.txt
		
		
	}
	
	
}

/**
*  
*  Class for record we want to keep for every symbol (address) from the access sequence
*
*  used in symbol table construction
*/
class SymbolRecord{
	private int address;  		//address of the symbol (as integer)
	private int lastAccessTime; //clock cycle of the latest access to the symbol
	
	// constructor
	public SymbolRecord(int address, int time){
		this.address = address;
		this.lastAccessTime = time;
	}
	
	// accessors
	public int getAddress(){
		return this.address;
	}
	
	public int getLastAccessTime(){
		return this.lastAccessTime;
	}
	
	// mutator for lastAccessTime
	public void setLastAccessTime(int newTime){
		this.lastAccessTime = newTime;
	}
	
}