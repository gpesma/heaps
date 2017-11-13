package heaps;

public interface Heap {
	void add(int n);
	boolean delete(int n);
	int find(int n);
	void merge(Heap h1, Heap h2);
	
	
}
