package kruskal;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

import structures.comparator.EdgeComparator;
import structures.graph.Edge;
import structures.graph.Graph;
import structures.graph.Vertex;

/**
 * Class of the kruskal algorithm
 * */
public class KruskalAlgorithm {
	
	private List<Vertex> compConexas;
	private EdgeComparator comparator;
	
	public KruskalAlgorithm() {
		
		compConexas = new ArrayList<Vertex>();	
		comparator = new EdgeComparator();
		
	}
	
	/**
	 * Kruskal algorithm with path compressor
	 * @param g A created graph
	 * @return The list with the least weight edges
	 * */
	public List<Edge> kruskal(Graph g) {
		
		compConexas.clear();
		
		for(Vertex v : g.getListVertices()) {
			compConexas.add(v);
		}
		
		PriorityQueue<Edge> q = new PriorityQueue<Edge>(g.getEdges(), comparator);
		
		q.addAll(g.getListEdges());
		
		UnionFind u = new UnionFind(g.getListVertices().size(), g.getListVertices());
		
		List<Edge> sol= new ArrayList<Edge>();
		
		while(sol.size() < g.getListVertices().size() - 1 &&  !q.isEmpty()) {
			
			Edge e = q.poll();
			
			int x = u.find(compConexas.indexOf(e.getSource()));
			int y = u.find(compConexas.indexOf(e.getDestination()));
			
			if(x != y) {
				
				sol.add(e);
				u.union(x, y);
				
			}
			
		}
		
		return sol;
	}
	
}
