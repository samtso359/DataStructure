package linear;

public class LinkedListAddBeforeLast {

       public int data;
       public LinkedListAddBeforeLast next;

       public LinkedListAddBeforeLast(int data, LinkedListAddBeforeLast next) {
           this.data = data; this.next = next;
       }
       public String toString() {
           return data + "";
    }
       
       public static LinkedListAddBeforeLast addBeforeLast(LinkedListAddBeforeLast front, int item) {
    	   if(front == null){
    		   return null;
    	   }
    	   LinkedListAddBeforeLast prev = null, ptr = front;
    	   while(ptr.next != null){
    		   prev = ptr;
    		   ptr = ptr.next;
    		 
    	   }
    	   
    	   LinkedListAddBeforeLast temp = new LinkedListAddBeforeLast(item, ptr);
    	   if (prev == null){
    		   return temp;
    	   }
    	 
    	   prev.next = temp;
    	   return front;
       }
       
       public static void main(String[] args){
    	   LinkedListAddBeforeLast x = new LinkedListAddBeforeLast(5, null);
    	//   x = addBeforeLast(x, 1);
    	   System.out.println(x);
       }
}
