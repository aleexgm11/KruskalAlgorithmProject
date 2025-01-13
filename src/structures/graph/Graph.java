package structures.graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/**
 * Class which represent the data structure of a graph
 * */
public class Graph {
	
	private Map<Vertex, List<Vertex>> adjVertices;
	private List<Vertex> listVertices;
	private HashSet<Edge> edges;
	
	public Graph(){
		listVertices = new ArrayList<Vertex>();
		adjVertices = new HashMap<Vertex, List<Vertex>>();	
		edges = new HashSet<Edge>();
	}
	
	/**
	 * Puts a new vertex in the graph
	 * @param label The label of the new vertex
	 * @return 
	 * */
	public void addVertex(int label) {
		
		Vertex v = new Vertex(Integer.toString(label));
		
		adjVertices.putIfAbsent(v, new ArrayList<Vertex>());
		listVertices.add(label - 1,  v);
		
	}	
	
	/**
	 * Creates a new edge using two Vertex with a value
	 * @param v1 The label of one vertex
	 * @param v2 The label of another vertex
	 * @param weight The weight of the edge
	 * @return 
	 * */
	public void addEdge(int label1, int label2, int weight) {
		
		Vertex v1 = listVertices.get(label1 - 1);
		Vertex v2 = listVertices.get(label2 - 1);
		
		adjVertices.get(v1).add(v2);
		adjVertices.get(v2).add(v1);

		edges.add(new Edge(v1, v2, weight));
	}
	
	/**
	 * Getter of vertices' list
	 * @return A list with all the used vertices 
	 * */
	public List<Vertex> getListVertices(){
		return this.listVertices;
	}
	
	/**
	 * Getter of edges' list, it goes over the hash of edges
	 * @return A list with all the edges 
	 * */
	public List<Edge> getListEdges(){
		List<Edge> listE = new ArrayList<Edge>();
		
		for(Edge e : edges) 
			listE.add(e);
		
		
		return listE;
 	}
	
	
	/**
	 * Function that returns how many edges are in the graph
	 * @return The count of edges
	 * */
	public int getEdges(){
		return this.edges.size();
	}
	
	/**
	 * Function that cleans the graph
	 * @return 
	 * */
	public void clear() {
		
		this.adjVertices.clear();
		this.edges.clear();
		this.listVertices.clear();
		
	}
	
	@Override
	public String toString() {
		
		StringBuilder b = new StringBuilder();
		
		for (Vertex v : listVertices) {
			
			b.append(v.toString() + ": ");
			
			for(Vertex v2 : adjVertices.get(v)) {
				
				b.append(v2.toString() + " ");
			}
			
			b.append("\n");
		}
		
		return b.toString();
	}
	
	
}
