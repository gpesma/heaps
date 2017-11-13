package heaps;

import java.util.*;

public class BinaryHeap {

	private List<Integer> heap;
	
	BinaryHeap(){
		heap = new ArrayList<Integer>();
	}
	
	public int getMin() {
		return heap.get(0);
	}
	
	private void heapify(int i) {
		int left = getLeft(i);
		int right = getRight(i);
		int small = i;
		if(left < heap.size() && heap.get(left) < heap.get(i))
			small = left;
		if(right < heap.size() && heap.get(right) < heap.get(i))
			small = right;
		
		if(small != i) {
			swap(i,small);
			heapify(small);
		}
	
	}
	
	public Integer removeMin() {
		if(heap.size() <=0)
			return Integer.MAX_VALUE;
		if(heap.size() == 1)
			return heap.remove(0);
		
		swap(0,heap.size()-1);
		int min = heap.remove(heap.size()-1);
		heapify(0);
		return min;
	}

	public void decreaseVal(int i, int n) {
		heap.set(i, n);
		while(i >= 0 && heap.get(getParent(i)) > heap.get(i)) {
			swap(i, getParent(i));
			i = getParent(i);
		}
	}
	
	public void delete(int n) {
		decreaseVal(heap.indexOf(n),Integer.MIN_VALUE);
		removeMin();
		//heapify(0);
	}

	public void insert(int n) {
		heap.add(n);
		int i = heap.size() - 1;
		while(i >= 0 && heap.get(getParent(i)) > heap.get(i)) {
			swap(i, getParent(i));
			i = getParent(i);
		}
	}
	
	private void swap(int i1, int i2) {
		int temp = heap.get(i1);
		heap.set(i1,heap.get(i2));
		heap.set(i2, temp);
	}
	
	private int getParent(int n) {
		return (n-1)/2;
		
	}
	
	private int getLeft(int n) {
		return 2*n+1;
	}
	
	private int getRight(int n) {
		return 2*n+2;
	}
	
}
