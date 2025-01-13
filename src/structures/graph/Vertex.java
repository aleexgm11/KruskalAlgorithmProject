package structures.graph;

public class Vertex {

	protected String label;
	
	public Vertex(String label){
		
		this.label = label;
		
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return ""+label;
	}
	
}
