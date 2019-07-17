package apps;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.NoSuchElementException;

import apps.PartialTree.Arc;
import apps.PartialTreeList.Node;
import structures.Graph;
import structures.MinHeap;
import structures.Vertex;
import structures.Vertex.Neighbor;

//public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
//	ArrayList<PartialTree.Arc> result = new ArrayList<PartialTree.Arc>();
//	PartialTree show = null;
//	while(true){
//		
//		Iterator<PartialTree> iterator0 = ptlist.iterator();
//		while(iterator0.hasNext()){
//			PartialTree y = iterator0.next();
//			
//			System.out.println(y);
//		}
//		PartialTree temp = ptlist.remove();
//		Arc a = temp.getArcs().deleteMin();
//		while(a.v2.equals(temp.getRoot())){
//			a = temp.getArcs().deleteMin();
//		}
//		System.out.println("");
//		result.add(a);
//		System.out.println("Now we are working at vertex: "+temp.getRoot());
//		System.out.println("The highest priority arc is: "+a+" and "+a.v2+"'s parent is: "+a.v2.parent);
//		PartialTree temp2 = ptlist.removeTreeContaining(a.v2.parent);
//		System.out.println("");
//		System.out.println("After the vertexes have been removed for merge, the ptlist is: ");
//		Iterator<PartialTree> iterator = ptlist.iterator();
//		while(iterator.hasNext()){
//			PartialTree y = iterator.next();
//			
//			System.out.println(y);
//		}
//		System.out.println("ptlist size is: "+ptlist.size());
//		System.out.println("");
//		System.out.println("Will perform merge on this vertex: "+temp);
//		System.out.println("The target vertex that will get merged with is: "+temp2);
//		temp.merge(temp2);
//		System.out.println("Merging result is: "+temp);
//		ptlist.append(temp);
//		System.out.println("");
//		System.out.println("After appending temp back to ptlist, the result ptlist is: ");
//		Iterator<PartialTree> iterator1 = ptlist.iterator();
//		while(iterator1.hasNext()){
//			PartialTree y = iterator1.next();
//			
//			System.out.println(y);
//		}
//		
//		System.out.println("ptlist size after appending is: "+ptlist.size());
//		if(ptlist.size()==1){
//			break;
//		}
//		System.out.println("________________________________________________________________________________________________");
//		System.out.println("________________________________________________________________________________________________");
//	}
//
//	System.out.println("show is: "+show);
//
//	return result;
//}
//}
public class MSTapp {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		Graph a = new Graph("graph5.txt");
//		for(int i = 0; i<a.vertices.length; i++){
//			System.out.print(a.vertices[i]);
//			System.out.print(a.vertices[i].neighbors.vertex);
//			System.out.println(" "+a.vertices[i].neighbors.weight);
//			Neighbor temp = a.vertices[i].neighbors;
//			
//			//System.out.println(a.vertices[i].neighbors.vertex);
//			while(temp!=null){
//				System.out.print(a.vertices[i]);
//				System.out.print(temp.vertex);
//				System.out.println(" "+temp.weight);
//				temp =temp.next;
//			}
//		
//	
//		}
		
//		public PartialTree removeTreeContaining(Vertex vertex) 
//			    throws NoSuchElementException {
//			    	Node ptr = rear.next;
//			    	Node prev = rear;
//			    	Boolean t = false;
//			    	do{
//			    		
//			    		if(ptr.tree.getRoot().equals(vertex)){
//			    			//System.out.println(ptr.tree.getRoot()+" is equal to "+vertex);
//			    			t= true;
//			    			break;
//			    		}
//			    		
//			    		prev = ptr;
//			    		ptr = ptr.next;
//			    	}while(ptr!=rear.next);
//			    	if(t == true){
//			    		
//			    	Node x = new Node(prev.next.tree);
//			    	prev.next = ptr.next;
//			    	size--;
//			    		return x.tree;
//			     
//			    	}
//			    	else{
//			    		System.out.println("cannot find the right one");
//			    		throw new NoSuchElementException();
//			    	}
//			    }
//			    
		PartialTreeList x = MST.initialize(a);
		ArrayList<PartialTree.Arc> result = MST.execute(x);
		for(int i = 0; i<result.size();i++){
			System.out.println(result.get(i));
		}
	}
	
}
