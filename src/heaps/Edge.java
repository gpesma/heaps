package heaps;

public class Edge {
	Node node;
	int weight;
	int data_end;
	
	public int getData_end() {
		return data_end;
	}
	public void setData_end(int data_end) {
		this.data_end = data_end;
	}
	
	/*public Edge(Node node, int weight) {
		super();
		this.node = node;
		this.weight = weight;
	}*/
	
	public Edge(int end, int weight){
		super();
		this.weight = weight;
		this.data_end = end;
	}
	
	
	public Node getNode() {
		return node;
	}
	public void setNode(Node node) {
		this.node = node;
	}
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
}
