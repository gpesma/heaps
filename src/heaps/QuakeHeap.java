package heaps;

import java.util.*;

public class QuakeHeap {

	private static final float a = (float) 0.75;
	LinkedList<QuakeNode> heap = new LinkedList<QuakeNode>();
	QuakeNode min;
	List<Integer> rootList = new ArrayList<Integer>();

	public void print() {
		for (int i : rootList)
			System.out.println(i);
		System.out.println('\n');
	}

	public QuakeHeap(QuakeNode q) {
		this.min = q;
		this.heap.add(q);
		this.rootList.add(1);
	}

	public void decreaseKey(QuakeNode q1, int newVal) {
		q1.getClone().setData(newVal);
		if (q1.getParent() != null)
			cut(q1.getClone().getHighestClone());
	}

	public void delete(QuakeNode q) {
		
		QuakeNode temp = q.getHighestClone();
		QuakeNode nextTemp = temp;
		while (nextTemp != null) {
			if (nextTemp.getRight() != null) {
				nextTemp.getRight().setParent(null);
				heap.add(nextTemp.getRight());
			}
			nextTemp = nextTemp.getLeft();
		}

		if (this.heap.contains(temp))
			this.heap.remove(temp);

		while (temp != null) {
			this.rootList.set(temp.getDegree() - 1, this.rootList.get(temp.getDegree() - 1) - 1);
			if (temp.getParent() != null) {
				if (temp.getParent().getLeft().equals(temp))
					temp.getParent().setLeft(null);
				else
					temp.getParent().setRight(null);
			}
			temp = temp.getLeft();
		}
		checkAlpha();
		fixSizes();
	}

	private void fixSizes() {
		
		HashMap<Integer, QuakeNode> treeSizes = new HashMap<Integer, QuakeNode>();
		int i = 0;
		
		while (this.heap.size() > i) {
			if (!treeSizes.containsKey(this.heap.get(i).getDegree())) {
				treeSizes.put(this.heap.get(i).getDegree(), this.heap.get(i));
				i++;
			} else {
				int tempDegree = this.heap.get(i).getDegree();
				QuakeNode sameDegreeTree = treeSizes.get(tempDegree);
				if (this.heap.size() > i + 1) {
					i = this.heap.indexOf(this.heap.get(i + 1)) != -1 ? this.heap.indexOf(this.heap.get(i + 1)) - 1 : i;
				}

				link(heap.get(i), sameDegreeTree);
				treeSizes.remove(tempDegree);
			}
		}
	}

	private void checkAlpha() {
		
		for (int i = 1; i < this.rootList.size() && rootList.get(i) != 0; ++i) {
			if (rootList.get(i) / rootList.get(i - 1) > a) {
				int j = 0;
				while (j < this.heap.size()) {
					quake(i, heap.get(j));
					if (heap.get(j).getDegree() > j)
						this.heap.remove(heap.get(j));
					j++;
				}
				break;
			}
		}
	}

	private void quake(int level, QuakeNode node) {
		
		if (node == null || node.getDegree() < level + 1)
			return;
		if (node.getDegree() == level + 1) {
			QuakeNode temp;
			if (node.getLeft() != null) {
				temp = node.getLeft();
				temp.setParent(null);
				this.heap.addLast(temp);
			}
			if (node.getRight() != null) {
				temp = node.getRight();
				temp.setParent(null);
				this.heap.addLast(temp);
			}
			this.rootList.set(node.getDegree() - 1, this.rootList.get(node.getDegree() - 1) - 1);
			return;
		}
		if (!node.isMark()) {
			this.rootList.set(node.getDegree() - 1, this.rootList.get(node.getDegree() - 1) - 1);
			node.setMark(true);
		}
		quake(level, node.getLeft());
		quake(level, node.getRight());
	}

	public void insert(QuakeNode q) {
		if (q != null) {
			heap.add(q);
			if (q.getData() < min.getData())
				min = q;
			this.rootList.set(0, this.rootList.get(0) + 1);
		}

	}

	public QuakeNode link(QuakeNode q1, QuakeNode q2) {
		if (q1.getDegree() > q2.getDegree()) {
			q2 = fixHeight(q2, q1.getDegree());
		} else if (q1.getDegree() < q2.getDegree()) {
			q1 = fixHeight(q1, q2.getDegree());
		}
		QuakeNode newNode = mergeTrees(q1, q2);
		this.heap.addLast(newNode);
		this.heap.remove(q1);
		this.heap.remove(q2);
		return newNode;
	}

	private QuakeNode mergeTrees(QuakeNode q1, QuakeNode q2) {
		
		QuakeNode newRoot;
		
		if (q1.getClone().getData() < q2.getClone().getData()) {
			newRoot = new QuakeNode(q1.getClone().getData());
			newRoot.setLeft(q1);
			newRoot.setRight(q2);
			q1.setParent(newRoot);
			q2.setParent(newRoot);
			newRoot.setClone(q1.getClone());
			q1.getClone().setHighestClone(newRoot);
			newRoot.setDegree(q1.getDegree() + 1);
		} else {
			newRoot = new QuakeNode(q2.getClone().getData());
			newRoot.setLeft(q2);
			newRoot.setRight(q1);
			q1.setParent(newRoot);
			q2.setParent(newRoot);
			newRoot.setClone(q2.getClone());
			q2.getClone().setHighestClone(newRoot);
			newRoot.setDegree(q2.getDegree() + 1);
		}

		if (this.rootList.size() > newRoot.getDegree() - 1)
			this.rootList.set(newRoot.getDegree() - 1, this.rootList.get(newRoot.getDegree() - 1) + 1);
		else
			this.rootList.add(1);

		return newRoot;
	}

	private QuakeNode fixHeight(QuakeNode q, int newHeight) {
		
		QuakeNode temp;
		
		while (q.getDegree() < newHeight) {
			temp = new QuakeNode(q.getData());
			temp.setLeft(q);
			temp.setDegree(q.getDegree() + 1);
			temp.setClone(q.getClone());
			temp.getClone().setHighestClone(temp);
			q.setParent(temp);
			q = temp;
			if (this.rootList.size() > temp.getDegree() - 1)
				this.rootList.set(temp.getDegree() - 1, this.rootList.get(temp.getDegree() - 1) + 1);
			else
				this.rootList.add(1);
		}
		return q;
	}

	public QuakeNode cut(QuakeNode q) {
		
		if (q == null)
			return null;
		QuakeNode topClone = q.getHighestClone();
		QuakeNode parent = topClone.getParent();
		
		if (parent != null) {
			if (parent.getLeft().equals(topClone))
				parent.setLeft(null);
			else
				parent.setRight(null);
			topClone.setParent(null);
		}
		
		this.heap.add(topClone);
		return topClone;
	}

	public int extraxtMin() {
		int minVal = min.getData();
		delete(min);
		this.min = this.findMin();
		return minVal;
	}

	private QuakeNode findMin() {
		
		int minValue = Integer.MAX_VALUE;
		QuakeNode newMin = heap.get(0);
		
		for (QuakeNode q : heap) {
			if (q.getData() < minValue) {
				newMin = q;
				minValue = q.getData();
			}
		}
		return newMin.getClone();
	}

	public QuakeHeap union(QuakeHeap qHeap) {
		this.heap.addAll(qHeap.heap);
		return this;
	}
}
