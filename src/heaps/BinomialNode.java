package heaps;
import java.util.*;

public class BinomialNode implements Node{
	
	private int data;
	int degree, id;
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	List<BinomialNode> children = new ArrayList<BinomialNode>();
	BinomialNode parent;
	
	
	public BinomialNode getParent() {
		return parent;
	}

	public void setParent(BinomialNode parent) {
		this.parent = parent;
	}

	BinomialNode(int n){
		data = n;
		degree = 1;
		parent = null;
	}
	
	public int getData() {
		return data;
	}
	
	public int getDegree() {
		return degree;
	}
	
	public void addChild(BinomialNode bn) {
		children.add(bn);
	}
	
	public BinomialNode getChild(int index) {
		return children.get(index);
	}
	
	public void setData(int n) {
		data = n;
	}
	
	public int numberOfChildren() {
		return children.size();
	}
	
}
