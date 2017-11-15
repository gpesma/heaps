package heaps;

import java.util.ArrayList;
import java.util.List;

public class TTree {
	
	QuakeNode root;
	
	List<Integer> nodesPerLevel = new ArrayList<Integer>();
	
	public void setRoot(QuakeNode root) {
		this.root = root;
	}

	public QuakeNode getRoot() {
		return root;
	}

	public TTree link(TTree tree) {
		if(this.getHeight() > tree.getHeight()) 
			tree = fixHeight(tree, this.getHeight());
		 else if(this.getHeight() < tree.getHeight()) 
			fixHeight(this, tree.getHeight());
		
		mergeTrees(tree);
		
		return this;
	}
	
	private void incrementNodesInLevelLastOfRoot() {
		this.root.setNodesInLevel(this.root.getNodesInLevel() + 1);
	}
	
	private int getNodesAtLevel(int n) {
		return this.nodesPerLevel.get(n);
	}

	private void updateNodesPerLevelMerge(TTree tree) {
		for(int i = 0; i < this.getHeight(); ++i) {
			this.nodesPerLevel.set(i, this.getNodesAtLevel(i) + tree.getNodesAtLevel(i));
		}
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
			q.setClone(this.root);
			this.root.getHighestClone().setHighestClone(q);
			this.updateNodesPerLevelMerge(tree);
			this.incrementNodesInLevelLastOfRoot();
			q.setDegree(this.root.getDegree() + 1);
			this.root = q;
		} else {
			QuakeNode q = new QuakeNode(treeData);
			q.setLeft(tree.getRoot());
			q.setRight(this.root);
			this.root.setParent(q);
			tree.getRoot().setParent(q);
			q.setClone(tree.getRoot());
			tree.getRoot().getHighestClone().setHighestClone(q);
			this.updateNodesPerLevelMerge(tree);
			tree.incrementNodesInLevelLastOfRoot();
			q.setDegree(this.root.getDegree() + 1);
			this.root = q;
		}
	}

	public int getHeight() {
		return nodesPerLevel.size();
	}
	
	public TTree(QuakeNode root) {
		super();
		this.root = root;
		this.nodesPerLevel.add(1);
	}
	
	public TTree(int n) {
		QuakeNode node = new QuakeNode(n);
		this.root = node;
		nodesPerLevel.set(0, 1);
	}

	private TTree fixHeight(TTree tree, int n) {
		
		while(tree.getHeight() < n) {
			QuakeNode q = new QuakeNode(tree.getRoot().getData());
			QuakeNode treeRoot =  tree.getRoot();
			treeRoot.setParent(q);
			q.setLeft(root);
			q.setDegree(tree.getHeight() + 1);
			tree.nodesPerLevel.set(tree.getHeight() - 1, 1);
			q.setClone(treeRoot.clone);
			treeRoot.getHighestClone().setHighestClone(q);
			q.setNodesInLevel(1);
			q.setDegree(treeRoot.getDegree() + 1);
			tree.setRoot(q);
		}
		return tree;		
	}
	/*
	public TTree cut(QuakeNode q) {
		
		QuakeNode topClone = q.getHighestClone();
		QuakeNode parent = topClone.getParent();
		if(parent != null) {
			if(parent.getLeft().equals(topClone))
				parent.setLeft(null);
			else
				parent.setRight(null);
		}
		topClone.setParent(null);
		topClone.setNodesInLevel(1);
		
		
	}
	
	public void updateNodesPerLevelCut(TTree tree) {
		if(this.root == null)
			return;
		this.root.setNodesInLevel();
	}*/
	
}
