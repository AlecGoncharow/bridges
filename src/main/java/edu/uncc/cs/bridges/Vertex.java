package edu.uncc.cs.bridges;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * An implementation of AbstractVertex with HashMap for adjacency.
 *  
 * @author Sean Gallagher
 */
public class Vertex extends AbstractVertex {

	//private GraphVisualizer graph;
	/**
	 * Creates and vertex and adds it to the graph.
	 * @param identifier Name of the vertex.
	 * @param graph The graph the vertex is added to.
	 */
	public Vertex(String identifier, GraphVisualizer graph) {
		super(identifier);

		outgoing = new ArrayList<AbstractEdge>();//creates empty list of connected edges

		
		//adds a vertex to the map	
		graph.vertices.put(identifier, this);		
	}
	
	/**
	 * Creates an edge between the calling vertex and a passed vertex.
	 * 
	 * @param v2 The second vertex that edge is between.
	 */
	public void createEdge(Vertex v2){
		//identifier is to be used internally to find the Edges later
		String ident = this.getIdentifier() +"To"+ v2.getIdentifier();
		new Edge(this, v2, ident);
	}
	/**
	 * Creates an edge between the calling vertex and a passed vertex.
	 * It attaches the weight attribute to an Edge
	 * @param v2 The second vertex that edge is between.
	 * @param weight Contains the weight value as a double
	 */
	public void createEdge(Vertex v2, double weight){
		//identifier is to be used internally to find the Edges later
		String ident = this.getIdentifier() +"To"+ v2.getIdentifier();
		new Edge(this, v2, ident, weight);
	}
	
	/**
	 * Creates an edge between the calling vertex and a passed vertex.
	 * It attaches the weight attribute to an Edge and assigns it a random value
	 * @param v2 The second vertex that edge is between.
	 * @param weight Contains the weight value as a string "randWeight", later 
	 * transformed in a random double between 0.0-9.0
	 */
	public void createEdge(Vertex v2, String randWeight){
		//identifier is to be used internally to find the Edges later
		String ident = this.getIdentifier() +"To"+ v2.getIdentifier();
		new Edge(this, v2, ident, randWeight);
	}
	
	/**
	 * Returns the Edge between two Vertices.
	 * 
	 * @param v2 The second vertex.
	 * @return The associated Edge.
	 */
	public AbstractEdge getEdge(AbstractVertex v2){
		//Can't think of any faster way to do this. -- Any suggestions?
		for(int i = 0; i < this.outgoing.size(); i++){ 
			for(int j = 0; j < v2.outgoing.size(); j++){
				if(this.outgoing.get(i).getIdentifier() == v2.outgoing.get(j).getIdentifier()){				
					return this.outgoing.get(i);
				}
			}
		}		
		return null;
	}
}