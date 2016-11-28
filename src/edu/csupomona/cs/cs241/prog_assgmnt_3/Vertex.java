package edu.csupomona.cs.cs241.prog_assgmnt_3;

public class Vertex implements Comparable<Vertex> {
	
	public final String name;
	
	public Edge[] adjacencies;
	
	public double minimumDistance = Double.POSITIVE_INFINITY;
	
	public double secondMinDistance = Double.POSITIVE_INFINITY;
	
	public Vertex prev;
	
	public Vertex otherPrev;
	
	public String[] keywords;
	
	public Vertex(String newName){
		name = newName;
	}
	
	public String toString(){
		return name;
	}
	
	public void getDirection(Vertex next){
		for(int i = 0; i <= adjacencies.length-1; i++){
			if(adjacencies[i].target == next)
				System.out.println(adjacencies[i].direction + " " + "for "+ (int)adjacencies[i].weight + " miles.");
		}
	}
	
	public void setKeywords(String[] tag){
		keywords = tag;
	}
	
	public boolean keyMatch(String match){
		for(int i = 0; i < keywords.length; i++){
			if(keywords[i].compareTo(match) == 0)
				return true;
		}
		
		return false;
	}

	@Override
	public int compareTo(Vertex other) {
		return Double.compare(minimumDistance, other.minimumDistance);
	}

}
