package heaps;
import java.util.*;

class BinomialHeap {
	
	//nodes
	LinkedList<BinomialNode> heap = new LinkedList<BinomialNode>();
	
	public BinomialNode first;
	
	public BinomialNode min;
	
	public BinomialHeap() {
		first = null;
		min = null;
	}
	
	public BinomialHeap(BinomialNode f) {
		first = f;
		min = f;
		AddTreeEnd(f);
	}
	
	public void replaceEqualTrees(BinomialNode leftTree, BinomialNode newTree) {
		int index = heap.indexOf(leftTree);
		heap.set(index, newTree);
		heap.remove(index+1);
	}
	
	private void AddTreeEnd(BinomialNode bn) {
		heap.addLast(bn);
		if(heap.size() == 1)
			first = bn;
		else if(bn.getData() < min.getData());
			min = bn;
	}
		
	private BinomialNode nextTree(BinomialNode bn) {
		int index = heap.indexOf(bn);
		if(heap.size() > index + 1)
			return heap.get(index + 1);
		return null;
	}
	
	private BinomialNode nextNextTree(BinomialNode bn) {
		int index = heap.indexOf(bn);
		if(heap.size() > index + 2)
			return heap.get(index + 2);
		return null;
	}
	
	public BinomialHeap union(BinomialHeap heap1, BinomialHeap heap2) {
		if(heap1.heapSize() == 0) {
			return heap2;
		}else if(heap2.heapSize() == 0) {
			return heap1;
		}
		
		BinomialNode heap1Tree = heap1.first;
		BinomialNode heap2Tree = heap2.first;
		BinomialHeap newBh = new BinomialHeap();

		if(heap1Tree.getDegree() < heap2Tree.getDegree()) {
			newBh.AddTreeEnd(heap1Tree);
			heap1Tree = heap1.nextTree(heap1Tree);
		} else {
			newBh.AddTreeEnd(heap2Tree);
			heap2Tree = heap2.nextTree(heap2Tree);
		}
		
		for(int i = 0 ; i < heap1.heapSize() + heap2.heapSize() - 1; ++i) {
			if(heap1Tree == null){
				newBh.AddTreeEnd(heap2Tree);
				heap2Tree = heap2.nextTree(heap2Tree);
			}else if(heap2Tree == null) {
				newBh.AddTreeEnd(heap1Tree);
				heap1Tree = heap1.nextTree(heap1Tree);
			}else if(heap1Tree.getDegree() < heap2Tree.getDegree()) {
				newBh.AddTreeEnd(heap1Tree);
				heap1Tree = heap1.nextTree(heap1Tree);
			}else {
				newBh.AddTreeEnd(heap2Tree);
				heap2Tree = heap2.nextTree(heap2Tree);
			}
		}
		
		BinomialNode temp = newBh.first;
		while(temp  != null && newBh.nextTree(temp) != null) {
			if(temp.getDegree() == newBh.nextTree(temp).getDegree()) {
					if(!(newBh.nextNextTree(temp) != null && temp.getDegree() == newBh.nextNextTree(temp).getDegree())) {
						BinomialNode mergedTrees = mergeTreesOfEqualDegree(temp, newBh.nextTree(temp));
						newBh.replaceEqualTrees(temp,mergedTrees);
					}
					else
						temp = newBh.nextTree(temp);
			} else
				temp = newBh.nextTree(temp);
		}
		newBh.min = newBh.findMin();
		newBh.first =newBh.heap.get(0);
		return newBh;
	}
	
	private int heapSize(){
		return heap.size();
	}
		
	public void insert(int n) {
		BinomialNode node = new BinomialNode(n);
		BinomialHeap bh = new BinomialHeap(node);
		bh = union(bh, this);
		this.heap = bh.heap;
		this.first = bh.first;
		this.min = bh.min;
	}
	
	public void insertNode(BinomialNode node) {
		BinomialHeap bh = new BinomialHeap(node);
		bh = union(bh, this);
		this.first = bh.first;
		this.min = bh.min;
		this.heap = bh.heap;
	}
	
	public BinomialNode getMin() {
		return min;
	}

	public Integer removeMin() {
		BinomialHeap newBh = new BinomialHeap();
		BinomialNode newMin = new BinomialNode(Integer.MAX_VALUE);
		BinomialNode temp ;
		for(int i = 0; i < min.numberOfChildren(); ++i) {
			temp = min.getChild(i);
			newBh.insertNode(temp);
			if(temp.getData() < newMin.getData())
				newMin.setData(temp.getData()); 
		}
		int resultMin = min.getData();
		this.first = this.first.equals(this.min) ? nextTree(first) : first;
		heap.remove(min);
		if(newBh.heapSize() != 0) {
			newBh.min = newMin;
			newBh = union(newBh,this);
			this.first = newBh.first;
			this.min = newBh.min;
			this.heap = newBh.heap;
		} else
			this.min = findMin();
		
		return resultMin;
	}
	
	public BinomialNode findMin() {
		if(heapSize() == 0)
			return null;
		
		int min = Integer.MAX_VALUE;
		int index = 0;
		for(int i = 0; i < heap.size();++i) {
			if(heap.get(i).getData() < min) {
				index = i;
				min = heap.get(i).getData();
			}
		}
		return heap.get(index);
	}

	private BinomialNode mergeTreesOfEqualDegree(BinomialNode t1, BinomialNode t2) {
		if(t1.getData() < t2.getData()) {
			t1.addChild(t2);
			t1.degree++;
			return t1;
		} else {
			t2.addChild(t1);
			t2.degree++;
			return t2;
		}
			
	}
}
