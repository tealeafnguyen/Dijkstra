package edu.csupomona.cs.cs241.prog_assgmnt_3;

public class Edge {
	
	public final Vertex target;
	
	public final double weight;
	
	public final String direction;
	
	public Edge(Vertex newTarget, double newWeight, String newDirection){
		target = newTarget;
		weight = newWeight;
		direction = newDirection;
	}
}
