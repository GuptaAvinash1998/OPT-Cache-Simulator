//The generic interface that used to maintain a table for unique symbols (addresses) from 
//a sequence of references

//SymbolType is the type you use to represent different symbols
//RecordType is the type you use to define the record of each symbol (address), which should 
//include information we would like to remember about that symbol.  For example, the 
//last access time of that symbol (used in StackDistCollector.java).


/**
 * @author Yutao Zhong and Jitin Krishnan
 * CS310 Spring 2018
 * Project 4
 * George Mason University
 * 
 * File Name: SymbolTable.java
 *
 * This is where we define what goes into the symbol Table
 */
public interface SymbolTable<SymbolType, RecordType>{

	// count how many symbols present in table 
	// return the integer count
	//
	// Average O(1)
	public int size();
	
	// check whether the symbol s is present in table
	// return true if present
	// return false otherwise
	//
	// Average O(1)
	public boolean hasSymbol(SymbolType s);

	// find and return the record we store in table for symbol s
	// if symbol not present, return null
	//
	// Average O(1)
	public RecordType getRecord(SymbolType s);
	
	// for symbol s, set the record of that symbol s as r
	// if s not present before, add s into table;
	// if s already in table, replace the record for s to be r
	// no return
	//
	// Average O(1)
	public void putRecord(SymbolType s, RecordType r);
	
	// find and remove the entry of symbol s from the table
	// return the record we store in table for symbol s
	// if symbol not present, return null
	//
	// Average O(1)
	public RecordType removeSymbol(SymbolType s);

}




