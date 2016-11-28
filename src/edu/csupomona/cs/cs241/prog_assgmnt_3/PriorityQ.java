package edu.csupomona.cs.cs241.prog_assgmnt_3;

public class PriorityQ {
	
	private int size;
	
	private MinHeap heap;
	
	public PriorityQ(){
		heap = new MinHeap(30);
		size = 0;
	}
	
	public void add(Vertex v){
		heap.insert(v);
		size++;
	}
	
	public Vertex deQ(){
		if(size != 0){
			Vertex next;
			next = (Vertex)heap.remove();
			size --;
			return next;
		}
		else
			throw new IndexOutOfBoundsException();
	}
	
	public boolean isEmpty(){
		return size == 0;
	}

}
