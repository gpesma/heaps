package heaps;

public class FibNode {
	int data, degree;
	boolean mark;
	FibNode left,right, child, parent;
	
	FibNode(int n){
		data = n;
		degree = 1;
		left = null;
		right = null;
		parent = null;
		child = null;
	}
	
	FibNode(int n, FibNode l, FibNode r, FibNode p, FibNode c){
		data = n;
		degree = 1;
		left = l;
		right = r;
		parent = p;
		child = c;
	}
	
	public void incrementDegree() {
		degree++;
	}
	
	public int getDegree() {
		return degree;
	}

	public void setDegree(int degree) {
		this.degree = degree;
	}

	public boolean isMark() {
		return mark;
	}

	public void setMark(boolean mark) {
		this.mark = mark;
	}

	public FibNode getLeft() {
		return left;
	}

	public void setLeft(FibNode left) {
		this.left = left;
	}

	public FibNode getRight() {
		return right;
	}

	public void setRight(FibNode right) {
		this.right = right;
	}

	public FibNode getChild() {
		return child;
	}

	public void setChild(FibNode child) {
		this.child = child;
	}

	public FibNode getParent() {
		return parent;
	}

	public void setParent(FibNode parent) {
		this.parent = parent;
	}

	public void setData(int data) {
		this.data = data;
	}

	public int getData() { return data;}
	
}
