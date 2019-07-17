package linear;

import java.util.ArrayList;
import java.util.NoSuchElementException;

/*
 * Stack using ArrayList
 */
public class ALStack<T> {

	ArrayList<T> items;
	
	public ALStack () {
		items = new ArrayList<T>();
	}
	
	public ALStack (int initialCapacity) {
		items = new ArrayList<T> (initialCapacity);
	}
	
	public void push (T item) {
		items.add(item);
	}
	
	public T pop () {
		if (items.size() == 0) {
			throw new NoSuchElementException();
		}
		return items.remove(items.size()-1);
	}
	
	public T peek () { 	// take a look what's on top
		if (items.size() == 0) {
			throw new NoSuchElementException();
		}
		return items.get(items.size()-1);
	}
	
	public boolean isEmpty () {
		return items.isEmpty();
	}
	
	public int size () {
		return items.size();
	}
	
	public void clear() {
		items.clear();
	}
	
	public static void main (String[] args) {
		ALStack<String> books = new ALStack<String> (5);
		books.push("Organic Chemistry"); //O(1)
		books.push("Data Structures"); //O(1)
		books.push("Perfect Game"); //O(1)		
		books.push("Psychology Fourth Edition"); //O(1)
		books.push("The Time Machine"); // O(1)
		books.push("Calculus Early Trancendentals"); //O(n) in this case O(6), 6 element
	}
}