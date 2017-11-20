package heaps;

import java.util.*;


//duplicates
public class FibHeap {

	LinkedList<FibNode> heap = new LinkedList<FibNode>();
	
	private FibNode min;
	
	HashMap<Integer, FibNode> treeSizes = new HashMap<Integer, FibNode>();
	
	FibHeap(){
		min = null;
	}
	
	public void insert(int n) {
		FibNode fN = new FibNode(n);
		if( heap.size() == 0)
			min = fN;
		if(min.getData() > fN.getData())
			min= fN;
		heap.add(fN);
		treeSizes.put(n,fN);
	}
	
	public void insertNode(FibNode fN) {
		if( heap.size() == 0)
			min = fN;
		if(min.getData() > fN.getData())
			min= fN;
		heap.add(fN);
	}
	
	public FibHeap union(FibHeap fh1, FibHeap fh2) {
		fh1.min = fh1.min.getData() < fh2.min.getData() ? fh1.min : fh2.min;
		fh1.heap.addAll(fh2.heap);
		return fh1;
	}
	
	public int getMin() {
		return min.getData();
	}
	
	private void fixSizes() {
		HashMap<Integer,FibNode> rootList = new HashMap<Integer,FibNode>(); 
		int i = 0;
		while(heap.size() > i) {
			if(!rootList.containsKey(heap.get(i).getDegree())) {
				rootList.put(heap.get(i).getDegree(),heap.get(i));
				i++;
			}else {
				int tempDegree = heap.get(i).getDegree();
				FibNode sameDegreeTree = rootList.get(tempDegree);
				FibNode newTree = mergeEqualTrees(heap.get(i), sameDegreeTree);
				//order of next 2 lines
				heap.set(i,newTree);
				i = heap.indexOf(sameDegreeTree) > i ? i : i-1;
				heap.remove(sameDegreeTree);
				rootList.remove(tempDegree);
			}
		}
	}
	
	
	public Integer extractMin() {
		FibNode temp = min.getChild();
		
		while(temp != null) {
			temp.setParent(null);
			insertNode(temp);
			temp = temp.getRight();
			if(temp != null && temp.getLeft() != null) temp.getLeft().setRight(null);
		}
		int result = min.getData();
		heap.remove(min);
		int minValue = Integer.MAX_VALUE;
		int index = 0;
		for(int i = 0; i < heap.size();++i) {
			if(heap.get(i).getData() < minValue) {
				index = i;
				minValue = heap.get(i).getData();
			}
		}
		min = heap.get(index);
		fixSizes();

		return result;
	}
	
	private FibNode mergeEqualTrees(FibNode f1, FibNode f2) {
		if(f1.getData() < f2.getData()) {
			f2.setRight(f1.getChild());
			if(f1.getChild() != null) {f1.getChild().setLeft(f2);}
			f1.setChild(f2);
			f2.setLeft(null);
			f2.setParent(f1);
			f1.incrementDegree();
			return f1;
		}else {
			f1.setRight(f2.getChild());
			if(f2.getChild() != null) f2.getChild().setLeft(f1);
			f2.setChild(f1);
			f1.setLeft(null);
			f1.setParent(f2);
			f2.incrementDegree();
			return f2;
		}
	}
	
	public void decreaseKey(FibNode fN, int n) {
		if(heap.contains(fN))
			return;
		FibNode temp = fN.getParent();
		if(fN.getParent() != null) {
			if(fN.getParent().getChild() == fN)
				fN.getParent().setChild(fN.getRight());
			fN.setParent(null);
			updateDegrees(temp);
		}	
		
		if(fN.getLeft()!=null && fN.getRight() != null) {
			temp = fN.getLeft();
			fN.getLeft().setRight(fN.getRight());
			fN.getRight().setLeft(temp);
		}else if(fN.getLeft() != null)
			fN.getLeft().setRight(null);
		else  if(fN.getRight() != null)
			fN.getRight().setLeft(null);
		
		insertNode(fN);
		fN.setData(n);
		fN.setMark(false);

		if(n < min.getData())
			min = fN;
		if(temp != null && !temp.isMark())
			temp.setMark(true);
		else if(temp !=null && temp.isMark())
			decreaseKey(temp,temp.getData());
			
	}
	
	private void updateDegrees(FibNode fb) {
		if(fb.getChild() == null)
			fb.setDegree(1);
		else {
			fb.setDegree(fb.getChild().getDegree() + 1);
		}
		
	}
	
	
	private void delete(FibNode fn) {
		decreaseKey(fn,Integer.MIN_VALUE);
		extractMin();
	}

}
