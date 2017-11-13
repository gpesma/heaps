package heaps;

import java.util.*;

public class QuakeHeap {
	LinkedList<QuakeNode> heap = new LinkedList<QuakeNode>();
	QuakeNode min;
	
	public void insert(int n) {
		QuakeNode qN  = new QuakeNode(n);
		if( heap.size() == 0)
			min = qN;
		if(min.getData() > qN.getData())
			min= qN;
		heap.add(qN);
	}
	
	public int getMin() {
		return min.getData();
	}
	
	private QuakeNode link(QuakeNode q1, QuakeNode q2) {
		QuakeNode temp1, temp2;
		temp2 = q2;
		temp1 = q1;
		if(q1.getDegree() > q2.getDegree()) {
			q2 = fixHeight(q2, q1.getDegree());
		} else if(q1.getDegree() < q2.getDegree()) {
			q1 = fixHeight(q1, q2.getDegree());
		}
		int newData = q1.getData() < q2.getDegree() ? q1.getData() : q2.getData();
		QuakeNode newNode = new QuakeNode(newData);
		newNode.setClone(q1.getData() < q1.getData() ? q1 : q2);
		newNode.setDegree(q1.getDegree() + 1);
		newNode.setLeft(q1);
		newNode.setRight(q2);
		if(q1.getData() == newNode.getData())
			q1.setHighestClone(newNode);
		else
			q2.setHighestClone(newNode);
		q1.setParent(newNode);
		q2.setParent(newNode);
		heap.remove(temp1);
		heap.remove(temp2);
		heap.add(newNode);
		return newNode;
	}
	
	private QuakeNode fixHeight(QuakeNode q, int n) {
		int i = n + 1;
		QuakeNode child = q;
		while(q.getDegree() < n) {
			QuakeNode temp = new QuakeNode(q.getData());
			temp.setDegree(i);
			i++;
			temp.setClone(q);
			temp.setLeft(child);
			child.setParent(temp);
			child = temp;
		}
		q.setHighestClone(child);
		return child;		
	}
	
	public void decreaseKey(QuakeNode q, int n) {
		q.setData(n);
		cut(q.getHighestClone());
	}
	
	public void delete(QuakeNode q) {
		QuakeNode temp = q;
		QuakeNode tempChild = q.getParent();
		while(temp.getParent() != null && temp.equals(q)) {
			cut(temp);
			temp = tempChild;
			tempChild = temp.equals(temp.getLeft()) ? temp.getLeft() : temp.getRight();
			
		}
	}
	
	
	public void cut(QuakeNode q) {

		QuakeNode temp;
		if(q.getLeft() != null && q.getLeft().getData() == q.getData()) {
			temp = q.getRight();
			q.setRight(null);
		} else {
			temp = q.getRight();
			q.setLeft(null);
		}
		temp.setParent(null);
		if(temp!=null) heap.add(temp);
	}
		
}
