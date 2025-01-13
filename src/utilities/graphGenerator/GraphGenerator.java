package utilities.graphGenerator;

import java.util.Arrays;
import java.util.Random;

import structures.graph.Graph;

public class GraphGenerator {
	
	private static final int MAX_V = 5000;
	
	private static int matrix[][] = new int[MAX_V][MAX_V];
	
	public GraphGenerator() {
		
		for(int i = 0; i < MAX_V; i++) {
			Arrays.fill(matrix[i], 0);
		}
		
	}
	
	public void generate(Graph g, double prob, int numVertices) {
		
		for(int i = 0; i < numVertices; i++) 
			g.addVertex(i+1);
		
		
		for (int i = 0; i < numVertices; i++) {
			for (int j = i; j < numVertices; j++) {
				
				Random rand = new Random();
				
				if(Math.random() < prob && i != j) {
					g.addEdge(i + 1, j + 1, rand.nextInt());
				}
				
			}
		}
		
	}
	
}
