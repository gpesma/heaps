package heaps;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {
	
		FibHeap f = new FibHeap();
		f.insert(0);
		f.insert(1);
		System.out.println(f.extractMin().getData());
		System.out.println(f.extractMin().getData());
		
		
		QuakeHeap q = new QuakeHeap();
		q.insert(0);
		q.insert(1);
		System.out.println(q.extractMin().getData());
		System.out.println(q.extractMin().getData());
		
		
		Heap b = new BinomialHeap();
		b.insert(0);
		b.insert(1);
		
		System.out.println(b.extractMin().getData());
		System.out.println(b.extractMin().getData());
		
		/*Djikstra d = new Djikstra();
		ArrayList<Vertex> v = d.read();
		
		FibHeap f= new FibHeap();
		d.shortestPath(v.get(0), f);*/
		
	}


}



