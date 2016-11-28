package edu.csupomona.cs.cs241.prog_assgmnt_3;

public class MinHeap<T extends Comparable<T>> {
	
	private T[] Heap;
	
	private int maxSize, size;

	public MinHeap(int initialSize, T initData) {
		maxSize = initialSize;
		Heap = (T[]) new Object[maxSize];
		size = 1;
		Heap[size-1] = initData;
	}
	
	public MinHeap(T initData){
		maxSize = 30;
		Heap = (T[]) new Object[maxSize];
		size = 1;
		Heap[size-1] = initData;
	}
	
	public MinHeap(int initialSize){
		maxSize = initialSize;
		Heap = (T[]) new Object[maxSize];
		size = 0;
	}
	
	private int leftChild(int position){
		return 2*position;
	}
	
	private int rightChild(int position){
		return 2*position+1;
	}
	
	private int parent(int position){
		return position / 2;
	}
	
	private boolean ifLeaf(int position){
		return ((position > size/2)&&(position <= size));
	}
	
	private void swap(int pos1, int pos2){
		T temp;
		temp = (T)Heap[pos1];
		Heap[pos1] = Heap[pos2];
		Heap[pos2] = temp;
	}

	public void insert(T element){
		size++;
		Heap[size-1] = element;
		int current = size;

		while(Heap[current].compareTo(Heap[parent(current)]) < 0){
			swap(current, parent(current));
			current = parent(current);
		}
	}

	public void print(){
		for(int i = 0; i <= size-1; i++)
			System.out.print(Heap[i] + " ");
		System.out.println();
	}
	
	public T remove(){
		swap(0, size);
		size--;
		if(size != 0)
			pushDown(0);
		return Heap[size];
	}
	
	private void pushDown(int pos){
		int smallestChild;
		while(!ifLeaf(pos)){
			smallestChild = leftChild(pos);
			if((smallestChild<size)&&(Heap[smallestChild].compareTo(Heap[smallestChild+1])>0))
					smallestChild = smallestChild+1;
			if(Heap[pos].compareTo(Heap[smallestChild]) <= 0)
				return;
			swap(pos, smallestChild);
			pos = smallestChild;
		}
	}
}
