package structures.comparator;

import java.util.Comparator;

import structures.graph.Edge;

/**
 * This class is used to sort from smallest to largest the priority queue 
 * */
public class EdgeComparator implements Comparator<Edge>{

	@Override
	// to compare to edges is necessary the weight
	public int compare(Edge e1, Edge e2) {

		if (e1.getWeight() < e2.getWeight()) return -1;
		else if (e1.getWeight() == e2.getWeight()) return 0;
		return 1;
	}
}
