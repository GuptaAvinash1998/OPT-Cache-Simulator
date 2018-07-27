import java.util.HashMap;
//your implementation of SymbolTable interface
//header comments here

/**
 * 
 * @author Avinash Gupta
 * CS310 Spring 2018
 * Project 4
 * George Mason University
 * 
 * @param <SymbolType> This is the key
 * @param <RecordType> This is the value
 * 
 * File Name: MySymbolTable.java
 *
 * This is a class that implements the methods of the interface  SymbolTable. Here we define the basic functions
 * of the HashMap.
 */
public class MySymbolTable<SymbolType, RecordType> implements SymbolTable<SymbolType, RecordType>{

	// you decide the internal design of your class:
	//  - it must implement the provided SymbolTable interface
	//  - it cannot have any other public members (attributes or method) other than
	//    the public constructor and the methods defined in SymbolTable interface
	
	private HashMap<SymbolType, RecordType> symbolTable; //This is the Hash Map
	private int size = 0;//keeps track of the number of elements in the table
	
	public MySymbolTable(){

		symbolTable = new HashMap<SymbolType, RecordType>(); //makes a new Hash Map object
	}
	
	
	// make sure you implement all methods in SymbolTable interface
	
	
	
	
	//------------------------------------
	// example test code... edit this as much as you want!
	public static void main(String[] args){
		MySymbolTable<String,Integer> table = new MySymbolTable<String,Integer>();
		
		if(table.size()==0 && !table.hasSymbol("a")) 	{
			System.out.println("Yay 1");
		}

		table.putRecord("a",new Integer(136));
		table.putRecord("b",new Integer(112));
		
		if(table.size()==2 && table.hasSymbol("a") && table.getRecord("b").equals(new Integer(112))) 	{
			System.out.println("Yay 2");
		}

		table.putRecord("b",new Integer(211));
		Integer v = table.removeSymbol("a");
		if(table.size()==1 && v.equals(new Integer(136)) && !table.hasSymbol("a") 
			&& table.getRecord("b").equals(new Integer(211))) 	{
			System.out.println("Yay 3");
		}
	
	}

	
	@Override
	/**
	 * Returns the size of the table
	 */
	public int size() {
		
		size = symbolTable.size();
		return size; //calculates and returns the size
	}


	/**
	 * Checks if the key is present in the table or not, if it is, returns true else returns false
	 */
	@Override
	public boolean hasSymbol(SymbolType s) {
		
		if(symbolTable.containsKey(s)) { 
			
			return true;
		}else {
			return false;
		}
	}

	/**
	 * Returns the value what the key is mapped to
	 */
	@Override
	public RecordType getRecord(SymbolType s) {
		// TODO Auto-generated method stub
		
		return symbolTable.get(s);
	}

	/**
	 * Inserts the key value pair in the table. If it already exists the it replaces the old pair with the new
	 */
	@Override
	public void putRecord(SymbolType s, RecordType r) {
		
		if(hasSymbol(s)) {
		
			symbolTable.replace(s, r);
		}else {
			symbolTable.put(s, r);
		}
	}

	/**
	 * Removes the specified key value pair from the table. 
	 */
	@Override
	public RecordType removeSymbol(SymbolType s) {
		
		RecordType record;
		
		if(hasSymbol(s)) {
			
			record = symbolTable.get(s);
			symbolTable.remove(s);
		}else {
			return null;
		}
		return record;
	}


}