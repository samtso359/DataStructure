package linear;

public class DLL <T> {
	
	NodeDLL <T> front;
	int size;
	DLL(){					//initialization
		front = null;
		size = 0;
	}
	
	void addToFront (T data){
		NodeDLL<T> node = new NodeDLL <T>(data, null, front);     // previous = null; front becomes new data;
		if(front == node){
			front.previous = node;
		}
		front = node;
		size++;
		
	}
	
	void addAfter (T target, T data){
			for (NodeDLL <T> ptr = front; ptr != null; ptr = ptr.next){
				if(ptr.data.equals(target)){
					NodeDLL<T> node = new NodeDLL<T>(data, ptr, ptr.next);
					ptr.next.previous = node;
					ptr.next=node;
					size++;
				}}
			}
	
	void traverse(){
		for(NodeDLL<T> ptr = front; ptr!=null; ptr=ptr.next){
			System.out.print(ptr.data +" -> ");
		}
		System.out.println();
		if(front == null){
			System.out.println("List is empty");
		}
		}
	
	
	void delete(T target){
		NodeDLL<T> ptr = front;
		while(ptr != null && !ptr.data.equals(target)) {
			ptr = ptr.next;
		}
		if (ptr == null) {
			return;
		} else {
			if (ptr.previous != null) {
				ptr.previous.next = ptr.next;
			} else {
				front = ptr.next;
			}
			if (ptr.next != null) {
				ptr.next.previous = ptr.previous;
			}
		}
	}
	
	/*public static NodeDLL moveToFront(NodeDLL front, NodeDLL target) {
        if (target == null || front == null || target == front) {
           return null;
        }
        NodeDLL ptr = front;
        // delink the target from the list
        while(target.next!= null||target.previous!=null||target.previous.next!=null){
        target.previous.next = target.next;
        }
        // make sure there is something after target before setting its prev
        if (target.next != null) {
           target.next.previous = target.previous;
        }
        target.next = front;
        target.previous = null;
        front.previous = target;
        return target;
    }*/ //not working
	/*public static NodeDLL reverse(NodeDLL front) {
        if (front == null) {
           return null;
        }
        NodeDLL rear=front, prev=null;
        while (rear != null) {
           NodeDLL temp = rear.next;
           rear.next = rear.previous;
           rear.previous = temp;
           prev = rear;
           rear = temp;
        }
        return prev;
    }*/
	
   /* public static Node deleteAll(Node front, String target) {
        if (front == null) { return null; }
        if (front.data.equals(target)) {
           return deleteAll(front.next, target);
        }
        front.next = deleteAll(front.next, target);
        return front;
    } */
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		DLL<String> dll = new DLL<String>();
		dll.addToFront("Real Madrid");
		dll.addToFront("Barcelona");
		dll.addToFront("Chelsea");
		dll.addAfter("Barcelona", "Liverpool");
		dll.traverse();
		

		/*dll.delete("Chelsea");
		dll.traverse();
		dll.delete("Liverpool");
		dll.traverse();
		dll.delete("Real Madrid");*/
		dll.traverse();
		/*dll.addToFront(3);
		dll.addToFront(5);
		dll.addToFront(7);
		dll.addToFront(1);
		dll.traverse();
		dll.reverse(dll.front);
		dll.traverse();*/
	}

}
