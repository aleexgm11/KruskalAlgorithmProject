package kruskal;

import java.util.List;
import structures.graph.Vertex;

/**
 * Class with all the methods that it's need to find, compress and unify edges
 * */
public class UnionFind {
	
	
	// number of elements in the union find
	@SuppressWarnings("unused")
	private int size;
	
	// number of components in the union find
	@SuppressWarnings("unused")
	private int numComponents;
	
	// list with the size of each component
	private int[] sizeComponent;
	
	// list that points to the parent, if i is a root node then parent[i] = i
	private int[] parent;
	
	public UnionFind(int size, List<Vertex> v) {
		
		this.size = size;
		this.numComponents = size;
		this.sizeComponent = new int[size];
		this.parent = new int[size];
		
		
		for (int i = 0; i < size; i++) {
			
			// each node is root
			parent[i] = i;
			
			// for being root it has size 1, itself
			sizeComponent[i] = 1;
			
		}
		
	}
	
	/**
	 * Finds each parents of element 1 and 2 to see if they are already connected
	 * @param elem1 one vertex of the graph
	 * @param elem2 another vertex of the graph
	 * @return true if they are connected
	 * */
	private boolean connected(int elem1, int elem2) {
		return find(elem1) == find(elem2);
	}
	
	/**
	 * Finds the root node of the given element
	 * @param elem one vertex of the graph
	 * @return the root node of the element
	 * */
	public int find(int element) {
		
		int rootNode = element;
		
		// this while goes over the parent array to find the root node of the element
		while(rootNode != parent[rootNode])
			rootNode = parent[rootNode];
		
		// this other while is the Path Compressor
		while (element != rootNode) {
			
		      int next = parent[element];
		      parent[element] =  rootNode;
		      element = next;
		      
		    }
		
		return rootNode;
	}
	
	/**
	 * Unifies the biggest component with the lowest, in case both have the same size element 2 parent connects to element 1 parent
	 * @param elem1 one vertex of the graph
	 * @param elem2 another vertex of the graph
	 * */
	public void union(int elem1, int elem2) {
			
		// if the are already connected both components then we cannot continue
		if(connected(elem1, elem2))
			return;
		
		int rootElem1 = find(elem1);
		int rootElem2 = find(elem2);
		
		 // Merge the smaller component in the big one
		if (sizeComponent[rootElem1] < sizeComponent[rootElem2]) {
			
			sizeComponent[rootElem2] += sizeComponent[rootElem1];
			parent[rootElem1] = rootElem2;
			sizeComponent[rootElem1] = 0;
		      
		} 
		
		else {
			
			sizeComponent[rootElem1] += sizeComponent[rootElem2];
			parent[rootElem2] = rootElem1;
			sizeComponent[rootElem2] = 0;
			
		}
	    
	    // when the components are mixed the number them decreases
	    numComponents--;
	}
	
}
