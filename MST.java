package apps;

import structures.*;
import structures.Vertex.Neighbor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import apps.PartialTree.Arc;

public class MST {
	
	/**
	 * Initializes the algorithm by building single-vertex partial trees
	 * 
	 * @param graph Graph for which the MST is to be found
	 * @return The initial partial tree list
	 */
public static PartialTreeList initialize(Graph graph) {
	PartialTreeList L = new PartialTreeList(); 
	
	for (int i = 0; i<graph.vertices.length; i++){ 
		Vertex V = graph.vertices[i];
		PartialTree T = new PartialTree(V); 
		
	
	Vertex.Neighbor N = graph.vertices[i].neighbors;
	
	MinHeap<PartialTree.Arc> P = T.getArcs();
	
	while (N != null){
	
	PartialTree.Arc D = new PartialTree.Arc(graph.vertices[i], N.vertex , N.weight);
		
	P.insert(D);

	N = N.next;
	

	}
		
		
		L.append(T); 
	}
	


	
		return L;
			
}



/**
 * Executes the algorithm on a graph, starting with the initial partial tree list
 * 
 * @param graph Graph for which MST is to be found
 * @param ptlist Initial partial tree list
 * @return Array list of all arcs that are in the MST - sequence of arcs is irrelevant
 */
public static ArrayList<PartialTree.Arc> execute(PartialTreeList ptlist) {
	
	/* COMPLETE THIS METHOD */
	ArrayList<PartialTree.Arc> result = new ArrayList<PartialTree.Arc>();
	
	int counter = ptlist.size();
	
	
	while(counter>1)	{
		


		PartialTree PTX = ptlist.remove();
//		
		MinHeap<PartialTree.Arc> PQX = PTX.getArcs();
		
		PartialTree.Arc highestP= PQX.deleteMin();

		
		
	System.out.println("The v2.getRoot() is :"+highestP.v2.getRoot());
		//while( highestP.v1.equals(highestP.v2) || highestP.v1.equals(highestP.v2.parent)){
		while(highestP.v2.getRoot().equals(PTX.getRoot())){	
			System.out.println("PTX.getRoot(): "+PTX.getRoot()+" (v1: "+highestP.v1+", v1parent "+highestP.v1.parent+") (v2: "+highestP.v2+" v2.parent: "+highestP.v2.parent+")");
			System.out.println("bad highestP, choosing a new one");
			highestP = PQX.deleteMin();
			System.out.println(highestP);
		
			System.out.println("highestp.v2.parent is: "+highestP.v2);

			
			System.out.println("New PTX.getRoot(): "+PTX.getRoot()+" (v1: "+highestP.v1+", v1parent "+highestP.v1.parent+") (v2: "+highestP.v2+" v2.parent: "+highestP.v2.parent+")");
			System.out.println("");
			
			
		}
		System.out.println("");
	
		System.out.println("final target is: "+highestP);
		System.out.println("PTX.getRoot(): "+PTX.getRoot()+" (v1: "+highestP.v1+", v1parent "+highestP.v1.parent+") (v2: "+highestP.v2+" v2.parent: "+highestP.v2.parent+")");
		result.add(highestP);
		
		PartialTree PTY = ptlist.removeTreeContaining(highestP.v2);
		
		System.out.println("PTY is: "+PTY);
		if(PTY==null){
			System.out.println("PTY is null");
			continue;
		}
		
		
		PTX.merge(PTY);
		ptlist.append(PTX);
	

		
		System.out.println("_____________________________________________________________________");
		counter--;
}

	return result;
}
}
