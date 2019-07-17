package linear;

public class StringCLL {
	StringNode rear;
	int size;
	StringCLL() {
		rear = null;
		size = 0;
	}
	void addTofront(String data){
		StringNode node = new StringNode(data, null);
			
		if (rear == null){
			node.next = node;
			rear = node;
		} else{
			node.next = rear.next;    // 8->9,  9->8      9 is the rear, 8 is the rear.next. node = new element. 
			rear.next = node;		//node.next = rear.next connects 7 -> 8, rear.next = node conencts 9 ->7
		}
		size++;
	}
	void deleteFront() {
		if(rear == null){	
			System.out.println("List is empty");
			
		}
		else if(rear == rear.next){				//if last element == first element
			size = 0;
			rear = null;						// delete the element by pointing it to null
		}
		else{
			rear.next = rear.next.next;     //first element = second element (first element no long accessible)
			size--;
		}
	}
	void search(String target){
		if(rear == null){
			System.out.println("LL is empty");
			}
		else{
			StringNode ptr = rear.next;  //rear.next is the front of the list
			do{
				if(ptr.data.equals(target)){
					System.out.println("Found it");
					return;
				}
				ptr = ptr.next;
			}while(ptr != rear.next);
			
			System.out.println(target + " not found");
		}
	}
}

