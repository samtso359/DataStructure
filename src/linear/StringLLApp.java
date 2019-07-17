package linear;

public class StringLLApp {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StringLL LinkedList = new StringLL();
		LinkedList.addToFront("Friday");
		LinkedList.traverse();
		LinkedList.addToFront("Wednesday");
		LinkedList.traverse();
		LinkedList.addToFront("Tuesday");
		LinkedList.addToFront("Monday");
		LinkedList.traverse();
		LinkedList.delete("Tuesday");
		LinkedList.traverse();
		LinkedList.delete("Monday");
		LinkedList.traverse();
	}

}
