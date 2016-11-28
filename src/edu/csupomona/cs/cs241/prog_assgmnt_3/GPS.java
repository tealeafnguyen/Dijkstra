package edu.csupomona.cs.cs241.prog_assgmnt_3;

import java.util.List;
import java.util.Scanner;

public class GPS {
	
	Vertex[] vertices = setUpGraph();
	
	Vertex userLocation = vertices[4];
	
	String[] keywords = new String[]{"Library","Recreation","Landmark"
		,"Sports","Arts","Hospital","Health&Care","Dining","Fast-food"
		,"Restaurant","Fitness"};
	
	Scanner sc = new Scanner(System.in);
	
	DijkstraAlgorithm da = new DijkstraAlgorithm();
	
	public void mainMenu(){
		System.out.println("Current location: "+ userLocation);
		System.out.println("1) Point-to-point navigation 2) Find destination");
		int userChoice = sc.nextInt();
		
		switch(userChoice){
		case 1: point2point();
		case 2: finalDestination();
		}
	}

	public void point2point(){
		int a,b;
		int i = 1;
		for(Vertex v: vertices)
			System.out.println(i++ +": " +v);
		System.out.println();
		System.out.println("Enter corresponding location for point A");
		a = sc.nextInt();
		System.out.println();
		System.out.println("Enter corresponding location for point B");
		b = sc.nextInt();
		
		assert a != b;
		getDirections(vertices[b-1],vertices[a-1]);
	}
	
	public void finalDestination(){
		System.out.println("1) Search from list 2) Search by keyword");
		int userChoice = sc.nextInt();
		System.out.println();
		
		switch (userChoice){
		case 1: dropBox();
		case 2: keywordSearch();
		}		
	}
	
	public void dropBox(){
		System.out.println("Enter corresponding number");
		int i = 1;
		for(Vertex v: vertices)
			System.out.println(i++ +": " +v);
		int userChoice = sc.nextInt();
		
		System.out.println();
		System.out.println("1)Get directions 2)Navigate");
		int finalChoice = sc.nextInt();
		
		assert vertices[userChoice-1] != userLocation;
		
		switch(finalChoice){
		case 1: getDirections(vertices[userChoice-1]);
		case 2: navigate(vertices[userChoice-1]);
		}
	}
	
	public void keywordSearch(){
		System.out.println("Enter corresponding number");
		int i = 1;
		for(String s: keywords)
			System.out.println(i++ +": " + s);
		int userChoice = sc.nextInt();
		Vertex[] choices = findKeys(keywords[userChoice-1]);
		int j = 1;
		System.out.println("");
		System.out.println("Showing results for "+ keywords[userChoice-1]);
		for(Vertex v: choices){
			if(v != null)
				System.out.println(j++ +": "+v);
		}
		
		System.out.println("Enter corresponding destination");
		int nextUserChoice = sc.nextInt();
		System.out.println();
		System.out.println("1)Get directions 2)Navigate");
		int finalChoice = sc.nextInt();
		
		assert choices[nextUserChoice-1] != userLocation;
		
		switch(finalChoice){
		case 1: getDirections(choices[nextUserChoice-1]);
		case 2: navigate(choices[nextUserChoice-1]);
		}
	}
	
	public void reroute(Vertex destination){
		da.findSecondShortest(userLocation);
		List<Vertex> path = da.findSecondShortestPath(destination);
		Vertex[] npath = new Vertex[12];
		npath = path.toArray(npath);
		String userInput;
		sc.nextLine();
		for(int i = 0; i < npath.length-1; i++){
			if(npath[i+1] != null){
				System.out.print(i+1 +": ");
				System.out.print("At "+ npath[i].toString()+" go ");
				npath[i].getDirection(npath[i+1]);
			}
			if(npath[i+1] == null){
				if(npath[i] != null)
					System.out.println(i+1 +": Arrive at "+npath[i]+".");
			}
			if(npath[i] != null){
				System.out.println("Enter r to reroute, current location: " + npath[i].toString());
				userInput = sc.nextLine();
				userLocation = npath[i];
				if(userInput.equals("r"))
					reroute(destination);
			}
		}
		System.out.println();
		mainMenu();
		
	}
	
	public void navigate(Vertex destination){
		da.findPaths(userLocation);
		List<Vertex> path = da.findShortestPath(destination);
		Vertex[] npath = new Vertex[12];
		npath = path.toArray(npath);
		String userInput;
		sc.nextLine();
		for(int i = 0; i < npath.length-1; i++){
			if(npath[i+1] != null){
				System.out.print(i+1 +": ");
				System.out.print("At "+ npath[i].toString()+" go ");
				npath[i].getDirection(npath[i+1]);
			}
			if(npath[i+1] == null){
				if(npath[i] != null)
					System.out.println(i+1 +": Arrive at "+npath[i]+".");
			}
			if(npath[i] != null){
				System.out.println("Enter r to reroute, current location: " + npath[i].toString());
				userInput = sc.nextLine();
				userLocation = npath[i];
				if(userInput.equals("r"))
					reroute(destination);
			}
		}
		
		System.out.println();
		mainMenu();
	}

	public void getDirections(Vertex destination){
		da.findPaths(userLocation);
		List<Vertex> path = da.findShortestPath(destination);
		Vertex[] npath = new Vertex[12];
		npath = path.toArray(npath);

		for(int i = 0; i < npath.length-1; i++){
			if(npath[i+1] != null){
				System.out.print(i+1 +": ");
				System.out.print("At "+ npath[i].toString()+" go ");
				npath[i].getDirection(npath[i+1]);
			}
			if(npath[i+1] == null){
				if(npath[i] != null)
					System.out.println(i+1 +": Arrive at "+npath[i]+".");
			}
		}
		System.out.println();
		mainMenu();
	}
	
	public void getDirections(Vertex pointB, Vertex pointA){
		da.findPaths(pointA);
		List<Vertex> path = da.findShortestPath(pointB);
		Vertex[] npath = new Vertex[12];
		npath = path.toArray(npath);

		for(int i = 0; i < npath.length-1; i++){
			if(npath[i+1] != null){
				System.out.print(i+1 +": ");
				System.out.print("At "+ npath[i].toString()+" go ");
				npath[i].getDirection(npath[i+1]);
			}
			if(npath[i+1] == null){
				if(npath[i] != null)
					System.out.println(i+1 +": Arrive at "+npath[i]+".");
			}
		}
		System.out.println();
		mainMenu();
	}

	public Vertex[] findKeys(String key){
		Vertex[] selection = new Vertex[12];
		int j = 0;
		for(int i = 0; i < vertices.length; i++){
			if(vertices[i].keyMatch(key)){
				selection[j] = vertices[i];
				j++;
			}				
		}	
		return selection;
	}

	private static Vertex[] setUpGraph(){

		Vertex v1 = new Vertex("Los Santos Public Library");
		Vertex v2 = new Vertex("Los Santos Saints' Stadium");
		Vertex v3 = new Vertex("Vinewood Boulevard");
		Vertex v4 = new Vertex("Los Santos Forum");
		Vertex v5 = new Vertex("Los Santos City Hall");
		Vertex v6 = new Vertex("Centennial Theater");
		Vertex v7 = new Vertex("All Saints General Hospital");
		Vertex v8 = new Vertex("Richman Country Club");
		Vertex v9 = new Vertex("BurgerShot");
		Vertex v10 = new Vertex("Los Santos Gym");
		Vertex v11 = new Vertex("Cluckin'Bell");
		Vertex v12 = new Vertex("Pimiento's");
		
		v1.setKeywords(new String[]{"Library","Recreation","Landmark"});
		v2.setKeywords(new String[]{"Recreation","Landmark","Sports"});
		v3.setKeywords(new String[]{"Recreation","Landmark"});
		v4.setKeywords(new String[]{"Recreation","Landmark","Sports"});
		v5.setKeywords(new String[]{"Landmark"});
		v6.setKeywords(new String[]{"Recreation","Arts","Landmark"});
		v7.setKeywords(new String[]{"Hospital","Health&Care"});
		v8.setKeywords(new String[]{"Recreation"});
		v9.setKeywords(new String[]{"Dining","Fast-food","Restaurant"});
		v10.setKeywords(new String[]{"Fitness","Health&Care"});
		v11.setKeywords(new String[]{"Dining","Fast-food","Restaurant"});
		v12.setKeywords(new String[]{"Dining","Restaurant"});
		
		
		v1.adjacencies = new Edge[]{new Edge(v2, 3, "east on Main Ave."),
				new Edge(v5, 5, "sount on 1st St.")};
		v2.adjacencies = new Edge[]{new Edge(v1, 3, "west on Main Ave."),
				new Edge(v3, 2, "east on Main Ave.")};
		v3.adjacencies = new Edge[]{new Edge(v2, 2, "west on Main Ave."),
				new Edge(v7, 6, "south on 3rd St."),
				new Edge(v4, 5, "east on Main Ave.")};
		v4.adjacencies = new Edge[]{new Edge(v3, 5, "west on Main Ave.")};
		v5.adjacencies = new Edge[]{new Edge(v6, 4, "east on Main Ave.")};
		v6.adjacencies = new Edge[]{new Edge(v2, 1, "north on 2nd St."),
				new Edge(v5, 4, "west on Centennial Ave."),
				new Edge(v7, 7, "east on Centennial Ave.")};
		v7.adjacencies = new Edge[]{new Edge(v10, 1, "south on 3rd St."),
				new Edge(v8, 3, "east on Centennial Ave."),
				new Edge(v6, 7, "west on Centennial Ave.")};
		v8.adjacencies = new Edge[]{new Edge(v4, 1, "north on 4th St."),
				new Edge(v7, 3, "west on Centennial Ave.")};
		v9.adjacencies = new Edge[]{new Edge(v6, 2, "north on 2nd street"),
				new Edge(v10, 1, "east on General Ave.")};
		v10.adjacencies = new Edge[]{new Edge(v9, 1, "west on General Ave."),
				new Edge(v11, 1, "east on General Ave."),
				new Edge(v12, 2, "south east on food alley")};
		v11.adjacencies = new Edge[]{new Edge(v8, 3, "north on 4th St."),
				new Edge(v10, 1, "west on General Ave.")};
		v12.adjacencies = new Edge[]{new Edge(v11, 3, "north on 4th St.")};

		Vertex[] vertices ={v1,v2,v3,v4,v5,v6,v7,v8,v9,v10,v11,v12};
		return vertices;
	}

}



