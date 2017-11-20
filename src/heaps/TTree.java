package heaps;

import java.util.ArrayList;
import java.util.List;

public class TTree {
	
	QuakeNode root;
	
	int height;
	
	public void setRoot(QuakeNode root) {
		this.root = root;
		this.height = root.getDegree();
	}

	public QuakeNode getRoot() {
		return root;
	}

	public TTree link(TTree tree) {
		if(this.getHeight() > tree.getHeight()) 
			tree = tree.fixHeight(this.getHeight());
		 else if(this.getHeight() < tree.getHeight()) 
			this.fixHeight(tree.getHeight());
		
		mergeTrees(tree);
		
		return this;
	}
	
	private void mergeTrees(TTree tree) {
		int thisData = this.root.getData();
		int treeData = tree.getRoot().getData();
		if(thisData < treeData) {
			QuakeNode q = new QuakeNode(thisData);
			q.setLeft(this.root);
			q.setRight(tree.getRoot());
			this.root.setParent(q);
			tree.getRoot().setParent(q);
			q.setClone(this.root.getClone());
			this.root.getClone().setHighestClone(q);
			q.setDegree(this.root.getDegree() + 1);
			this.root = q;
			this.height = q.getDegree();
		} else {
			QuakeNode q = new QuakeNode(treeData);
			q.setLeft(tree.getRoot());
			q.setRight(this.root);
			this.root.setParent(q);
			tree.getRoot().setParent(q);
			q.setClone(tree.getRoot().getClone());
			tree.getRoot().getClone().setHighestClone(q);
			q.setDegree(this.root.getDegree() + 1);
			this.root = q;
			this.height = q.getDegree();
		}
	}

	public int getHeight() {
		return this.height;
	}
	
	public TTree(QuakeNode root) {
		super();
		this.root = root;
		this.height = root.getDegree();
	}
	
	public TTree(int n) {
		QuakeNode node = new QuakeNode(n);
		this.root = node;
		this.height = 1;
	}

	private TTree fixHeight(int n) {
		
		while(this.getHeight() < n) {
			QuakeNode q = new QuakeNode(this.getRoot().getData());
			QuakeNode treeRoot =  this.getRoot();
			treeRoot.setParent(q);
			q.setLeft(this.root);
			q.setDegree(this.getHeight() + 1);
			q.setClone(treeRoot.clone);
			treeRoot.getClone().setHighestClone(q);
			q.setDegree(treeRoot.getDegree() + 1);
			this.setRoot(q);
		}
		return this;		
	}
	
	public TTree cut(QuakeNode q) {
		
		QuakeNode topClone = q.getHighestClone();
		QuakeNode parent = topClone.getParent();
		if(parent != null) {
			if(parent.getLeft().equals(topClone))
				parent.setLeft(null);
			else
				parent.setRight(null);
			topClone.setParent(null);
			return new TTree(topClone);
		} else 
			return this;
		
	}
	

}
