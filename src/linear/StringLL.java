package linear;
//Static does not have access to the object
public class StringLL {
	/* testing our stringLL object */
	//fields of the LL object
	StringNode front; //beginning of the LL
	int size;
	//Constructor initializes the LL
	StringLL(){
		front = null;
		size =0;
		}
	
	//methods of the LL object
	
	public boolean isEmpty(){
		return front == null;
	}
	public void addToFront(String newData){ //addToFront = bigO(1), addToEnd = bigO(n)
		front = new StringNode(newData, front);
		size++;
		
	}
	
	public void delete(String target){
		if(isEmpty()){
			System.out.println("List is empty");
		}
		StringNode ptr = front;
		StringNode prev = null;
		while(ptr != null && !ptr.data.equals(target)){
			prev = ptr;
			ptr = ptr.next;
		}
		
		if(ptr ==null){
			System.out.println(target +" not found");
		}
		else if(ptr == front){
			front = front.next;
			size --;
		}
		else{
			prev.next = ptr.next;
			size --;
		}
	
	}
	
	public void traverse(){
		for (StringNode ptr = front; ptr!=null; ptr = ptr.next){
			System.out.print(ptr.data +" -> ");
		}
		System.out.println("\\");
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub


	}

}
