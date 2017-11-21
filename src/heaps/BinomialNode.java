package heaps;
import java.util.*;

public class BinomialNode {
	
	private int data;
	int degree;
	List<BinomialNode> children = new ArrayList<BinomialNode>();
	BinomialNode parent;
	
	BinomialNode(int n){
		data = n;
		degree = 1;
	}
	
	int getData() {
		return data;
	}
	
	int getDegree() {
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
