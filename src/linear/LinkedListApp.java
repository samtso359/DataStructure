package linear;

import java.util.NoSuchElementException;

public class LinkedListApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LinkedList <Integer> intLL = new LinkedList<Integer>();
		intLL.addToFront(9);
		intLL.addToFront(8);
		intLL.addToFront(7);
		intLL.traverse();
		
		LinkedList<String> intLLS = new LinkedList<String> ();
		intLLS.addToFront("Wednesday");
		intLLS.addToFront("Tuesday");
		intLLS.addToFront("Monday");
		intLLS.traverse();
		System.out.println(intLLS.deleteFront());;
		intLLS.traverse();
		System.out.println(intLLS.deleteFront());;
		intLLS.traverse();
		System.out.println(intLLS.deleteFront());;
		intLLS.traverse();
	
		try{
			intLLS.deleteFront();
		} catch(NoSuchElementException e){
			System.out.println("means our LL is empty");
		}
	}

}
