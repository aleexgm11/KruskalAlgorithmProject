package structures.graph;

public class Edge{
	
    private Vertex source;
    private Vertex destination;
    private int weight;
    
    // all edges are bidirectional so source and destination are there simply to know which vertices connect
    Edge(Vertex source, Vertex destination, int value){
    	
    	this.source = source;
    	this.destination = destination;
    	this.weight = value;
    	
    }
    
    /**
     * Getter of the value
     * @return The value
     * */
    public int getWeight() {
    	return this.weight;
    }
    
    /**
     * Getter of the source
     * @return The source
     * */
    public Vertex getSource() {
		return source;
	}
    
    /**
     * Getter of the destination
     * @return The destination
     * */
    public Vertex getDestination() {
		return destination;
	}
    
    @Override
    public boolean equals(Object obj) {
    	// TODO Auto-generated method stub
    	return this.source == ((Edge)obj).source && this.destination == ((Edge)obj).destination 
    			|| this.source == ((Edge)obj).destination && this.destination == ((Edge)obj).source;
    }
    
    @Override
    public int hashCode() {
    	
    	int hashSource = source.hashCode();
    	int hashDest = destination.hashCode();
    	
        // before i create it i check if it's the same edge v1 -> v2 that v2 -> v1
    	// in case that in the first try v1 was greater than v2 when it came the opposite case it will enter in the if and swap it to has the same hash
    	if(hashSource < hashDest) {
    		int aux = hashSource;
    		hashSource = hashDest;
    		hashDest = aux;
    		
    	}
    	
    	// to create the hash i use Cantor's function
    	return (hashSource + hashDest) * (hashSource + hashDest + 1) / 2 + hashSource;
    }
    
    @Override
    public String toString() {
    	// TODO Auto-generated method stub
    	return source.toString() + " ---- " + destination.toString();
    }
    
}