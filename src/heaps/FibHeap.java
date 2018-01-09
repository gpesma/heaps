package heaps;

import java.util.*;

//duplicates
public class FibHeap implements Heap{

	private LinkedList<FibNode> heap = new LinkedList<FibNode>();

	private FibNode min;

	private HashMap<Integer, FibNode> treeSizes = new HashMap<Integer, FibNode>();

	FibHeap() {
		this.min = null;
	}

	public FibNode insert(int n) {
		
		FibNode fN = new FibNode(n);
		
		if (heap.size() == 0)
			this.min = fN;
		if (min.getData() > fN.getData())
			this.min = fN;
		
		this.heap.add(fN);
		//this.treeSizes.put(n, fN);
		return fN;
	}

	public void insertNode(Node f) {
		FibNode fN = (FibNode) f;
		if (heap.size() == 0)
			this.min = fN;
		if (min.getData() > fN.getData())
			this.min = fN;
		
		this.heap.add(fN);
	}

	public FibHeap union(FibHeap fh1, FibHeap fh2) {
		fh1.min = fh1.min.getData() < fh2.min.getData() ? fh1.min : fh2.min;
		fh1.heap.addAll(fh2.heap);
		return fh1;
	}

	public int getMin() {
		
		return this.min.getData();
	}

	private void fixSizes() {
		
		HashMap<Integer, FibNode> rootList = new HashMap<Integer, FibNode>();
		int i = 0;
		
		while (heap.size() > i) {
			if(rootList.containsValue(heap.get(i))) {
				i++;
				continue;
			}
			if (!rootList.containsKey(heap.get(i).getDegree())) {
				rootList.put(heap.get(i).getDegree(), heap.get(i));
				i++;
			} else {
				int tempDegree = heap.get(i).getDegree();
				FibNode sameDegreeTree = rootList.get(tempDegree);
				FibNode newTree = mergeEqualTrees(heap.get(i), sameDegreeTree);
				// order of next 2 lines
				this.heap.set(i, newTree);
				i = heap.indexOf(sameDegreeTree) > i ? i : i - 1;
				this.heap.remove(sameDegreeTree);
				rootList.remove(tempDegree);
			}
		}
	}

	public Node extractMin() {

		if(this.heap.size() == 0)
			return null;
		
		FibNode temp = min.getChild();
		FibNode rev = null;
		//System.out.println("prin");
		while (temp != null) {
			temp.setParent(null);
			insertNode(temp);
			rev = temp;
			temp = temp.getRight();
			if (temp != null && temp.getLeft() != null) {
				temp.getLeft().setRight(null);
			}
		}
		//System.out.println("after");
		
		Node n = min;
		int result = min.getData();
		this.heap.remove(min);
		int minValue = Integer.MAX_VALUE;
		int index = 0;
		
		for (int i = 0; i < this.heap.size(); ++i) {
			if (heap.get(i).getData() < minValue) {
				index = i;
				minValue = this.heap.get(i).getData();
			}
		}
		//System.out.println("after loop");
		this.min = this.heap.size() == 0 ? null : this.heap.get(index);
		this.fixSizes();

		return n;
	}

	private FibNode mergeEqualTrees(FibNode f1, FibNode f2) {
		
		if (f1.getData() < f2.getData()) {
			f2.setRight(f1.getChild());
			if (f1.getChild() != null)
				f1.getChild().setLeft(f2);
			f1.setChild(f2);
			f2.setLeft(null);
			f2.setParent(f1);
			f1.incrementDegree();
			return f1;
		} else {
			f1.setRight(f2.getChild());
			if (f2.getChild() != null)
				f2.getChild().setLeft(f1);
			f2.setChild(f1);
			f1.setLeft(null);
			f1.setParent(f2);
			f2.incrementDegree();
			return f2;
		}
	}

	public void decreaseKey(Node node, int n) {
		//System.out.println(":arx dec");
		FibNode fN = (FibNode) node;
		
		if(fN.getParent() == null) {
			fN.setData(n);
			if(this.min == null)
				this.min = fN;
			this.min = n < min.getData() ? fN : this.min;
			return;
		}

		FibNode temp = fN.getParent();
		
		if (fN.getParent().getChild() == fN)
			fN.getParent().setChild(fN.getRight());
		fN.setParent(null);
		//updateDegrees(temp);


		if (fN.getLeft() != null && fN.getRight() != null) {
			fN.getLeft().setRight(fN.getRight());
			fN.getRight().setLeft(fN.getLeft());
		} else if (fN.getLeft() != null) 
			fN.getLeft().setRight(null);
		else if (fN.getRight() != null) {
			fN.getRight().setLeft(null);
			//fN.getRight().setChild(fN.getChild());
		}
		fN.setLeft(null);
		fN.setRight(null);

		//fN.setChild(null);
		
		fN.setDegree(getDegreeOfMaxChild(fN) + 1);;
		
		this.insertNode(fN);
		fN.setData(n);
		fN.setMark(false);

		if (n < min.getData())
			min = fN;
		if (temp != null && !temp.isMark())
			temp.setMark(true);
		else if (temp != null && temp.isMark())
			this.decreaseKey(temp, temp.getData());

		System.out.println("telos dec");
		
	}

	static private int getDegreeOfMaxChild(FibNode n) {
		int max = 0;
		n = n.getChild();
		while(n !=null) {
			max= max < n.getDegree() ? n.getDegree() : max;
			n = n.getRight();
		}
		return max;
	}
	
	private void updateDegrees(FibNode fb) {
		if (fb.getChild() == null)
			fb.setDegree(1);
		else {
			fb.setDegree(fb.getChild().getDegree() + 1);
		}
	}

	public void delete(Node node) {
		FibNode fn = (FibNode) node;
		this.decreaseKey(fn, Integer.MIN_VALUE);
		this.extractMin();
	}

	public boolean isEmpty() {
		return this.heap.size() == 0;
	}
}
