package edu.csupomona.cs.cs241.prog_assgmnt_3;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class DijkstraAlgorithm {

	public void findPaths(Vertex start){
		start.minimumDistance = 0;
		PriorityQueue<Vertex> vertexQ = new PriorityQueue<Vertex>();
		vertexQ.add(start);

		while(!vertexQ.isEmpty()){
			Vertex curr = vertexQ.poll();

			for(Edge e: curr.adjacencies){
				Vertex vert = e.target;
				double weight = e.weight;
				double distanceThroughU = curr.minimumDistance + weight;

				if(distanceThroughU < vert.minimumDistance){
					System.out.println(distanceThroughU + " " + vert.minimumDistance);
					vertexQ.remove(vert);
					vert.minimumDistance = distanceThroughU;
					vert.prev = curr;
					vertexQ.add(vert);
				}
			}
		}
	}
	
	public void findSecondShortest(Vertex current){
		current.secondMinDistance = 0;
		PriorityQueue<Vertex> vert = new PriorityQueue<Vertex>();
		vert.add(current);
		
		while(!vert.isEmpty()){
			Vertex curr = vert.poll();
			
			for(Edge e: curr.adjacencies){
				Vertex nVert = e.target;
				double weight = e.weight;
				double distanceThroughU = curr.secondMinDistance + weight;
				
				if(distanceThroughU < nVert.secondMinDistance ){
					vert.remove(nVert);
					nVert.secondMinDistance = distanceThroughU;
					nVert.otherPrev = curr;
					vert.add(nVert);
				}
			}
		}
	}
	
	public static List<Vertex> findSecondShortestPath(Vertex target){
		List<Vertex> path = new ArrayList<Vertex>();
		for(Vertex vert = target; vert != null; vert = vert.otherPrev){
			path.add(vert);
		}
		Collections.reverse(path);
		return path;
	}
	
	public static List<Vertex> findShortestPath(Vertex target){
		List<Vertex> path = new ArrayList<Vertex>();
		for(Vertex vert = target; vert != null; vert = vert.prev){
			path.add(vert);
		}
		Collections.reverse(path);
		return path;
	}

}
