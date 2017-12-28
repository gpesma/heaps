package heaps;
import java.util.*;

public class Vertex{
	List<Edge> edges;
	Node node;
	int dist, data;
	
	public int getData() {
		return this.data;
	}
	public void setData(int data) {
		this.data = data;
	}
	public Node getNode() {
		return this.node;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	
	public void addEdge(Edge edge) {
		this.edges.add(edge);
	}
	
	public void setEdges(List<Edge> edges) {
		this.edges = edges;
	}
	public Node minDist() {
		int min = Integer.MAX_VALUE;
		Node node = null;
		for(Edge e: this.edges) {
			if(e.getWeight() < min) {
				min = e.getWeight();
				node = e.getNode();
			}
		}
		return node;
	}
	public int getDist() {
		return dist;
	}

	public void setDist(int dist) {
		this.dist = dist;
	}

	public Vertex(int n){
		this.data = n;
		edges = new ArrayList<Edge>();
		dist = Integer.MAX_VALUE;
	}


	public List<Edge> getEdges() {
		return edges;
	}

	public void setEdge(Edge e) {
		this.edges.add(e);
	}
	
	
}
