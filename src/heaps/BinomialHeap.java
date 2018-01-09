package heaps;
import java.util.*;

class BinomialHeap implements Heap{
	
	private LinkedList<BinomialNode> heap = new LinkedList<BinomialNode>();
	
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
		if(index == -1)
			return;
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
			this.heap = heap2.heap;
			this.first = heap2.first;
			this.min = heap2.min;
			return heap2;
		}else if(heap2.heapSize() == 0) {
			this.heap = heap1.heap;
			this.first = heap1.first;
			this.min = heap1.min;
			return heap1;
		}
		//System.out.println(heap2.heap.get(0));
		BinomialNode heap1Tree = heap1.first;
		BinomialNode heap2Tree = heap2.first;
		
		List<BinomialNode> rootList1 = heap1.heap;
		List<BinomialNode> rootList2 = heap2.heap;
		HashMap<Integer, BinomialNode> sizes = new HashMap<Integer, BinomialNode>();
		int index1 = 0;
		int index2 = 0;
		//System.out.println(rootList1.size() + rootList2.size());
		for(int i = 0; i < rootList1.size() + rootList2.size(); ++i) {
			
			
			if(rootList1.size() <= index1 && rootList2.size() <= index2) {
				break;
			} else if(rootList1.size() <= index1 ) {
				BinomialNode node2 =(BinomialNode) rootList2.get(index2);
				if(sizes.containsKey(node2.getDegree())) {
					BinomialNode node1 = sizes.remove(node2.getDegree());
					node2 = mergeTreesOfEqualDegree(node1,node2);
					rootList2.add(node2);
				} else
					sizes.put(node2.getDegree(), node2);
				
				index2++;	
			}else if(rootList2.size() <= index2) {
				BinomialNode node1 =(BinomialNode) rootList1.get(index1);
				if(sizes.containsKey(node1.getDegree())) {
					BinomialNode node2 = sizes.remove(node1.getDegree());
					node1 = mergeTreesOfEqualDegree(node1,node2);
					rootList2.add(node1);
				} else
					sizes.put(node1.getDegree(), node1);
				
				index1++;	
			}else{
				BinomialNode node1 =(BinomialNode) rootList1.get(index1);
				if(sizes.containsKey(node1.getDegree())) {
					BinomialNode node2 = sizes.remove(node1.getDegree());
					node1 = mergeTreesOfEqualDegree(node1,node2);
					rootList2.add(node1);
				} else
					sizes.put(node1.getDegree(), node1);
				
				index1++;
			}
		}

		this.heap.clear();
		
	    Iterator it = sizes.entrySet().iterator();
	    while (it.hasNext()) {
	        Map.Entry pair = (Map.Entry)it.next();
	        this.heap.addLast((BinomialNode) pair.getValue());
	    }
		
		
		this.min = this.findMin();
		if(min != null)
			this.first = this.heap.get(0);
		return this;
		
	}
	
	private int heapSize(){
		return this.heap.size();
	}
		
	public BinomialNode insert(int n) {
		BinomialNode node = new BinomialNode(n);
		BinomialHeap bh = new BinomialHeap(node);
		//bh.AddTreeEnd(node);
		bh = union(bh, this);
		this.heap = bh.heap;
		this.first = bh.first;
		this.min = bh.min;
		return node;
	}
	
	public void insertNode(Node n) {
		BinomialNode node = (BinomialNode) n;
		BinomialHeap bh = new BinomialHeap(node);
		bh = union(bh, this);
		this.first = bh.first;
		this.min = bh.min;
		this.heap = bh.heap;
	}
	
	public int getMin() {
		return this.min.getData();
	}

	public Node extractMin() {
		
		if(this.heapSize() == 0)
			return null;
		
	//	BinomialHeap newBh = new BinomialHeap();
		//BinomialNode newMin = this.findMin() == null ? new BinomialNode(Integer.MAX_VALUE) : this.findMin();
		
		BinomialNode temp ;
		
		for(int i = 0; i < min.numberOfChildren(); ++i) {
			temp = min.getChild(i);
			temp.setParent(null);
			this.insertNode(temp);
			//if(temp.getData() < newMin.getData())
			//	newMin = temp;
		}
				
		Node resultMin = this.min;
		//this.first = this.first.equals(this.min) ? nextTree(first) : first;
		this.heap.remove(min);
		
	
		this.min = findMin();
		
		return resultMin;
	}
	
	private BinomialNode findMin() {
		
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
			t2.setParent(t1);
			return t1;
		} else {
			t2.addChild(t1);
			t2.degree++;
			t1.setParent(t2);
			return t2;
		}	
	}
	
	public void delete(Node node) {
		BinomialNode bN = (BinomialNode) node;
		this.decreaseKey(bN, Integer.MIN_VALUE);
		this.extractMin();
	}
	
	public void decreaseKey(Node node, int val) {
		BinomialNode bN = (BinomialNode) node;
		bN.setData(val);
		while(bN.getParent() != null && bN.getParent().getData() < bN.getData()) {
			int temp = bN.getData();
			bN.setData(bN.getParent().getData());
			bN.getParent().setData(temp);
			bN = bN.getParent();
		}
	}
	
	public boolean isEmpty() {
		return this.heap.size() == 0;
	}
}
