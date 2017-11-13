package heaps;
import java.util.*;

public class Main {

	public static void main(String[] args) {
		
		FibHeap f = new FibHeap();
		f.insert(1);
		f.insert(2);
		f.insert(3);
		f.insert(4);
		f.insert(5);
		f.insert(6);
		f.insert(7);
		f.insert(8);
		f.insert(9);
		f.extractMin();
		f.decreaseKey(f.map.get(8), -8);
		f.decreaseKey(f.map.get(7), -7);
		f.insert(10);
		System.out.println(f.extractMin());
		System.out.println(f.extractMin());
		System.out.println(f.extractMin());
		System.out.println(f.extractMin());
		/*f.insert(1);
		f.insert(2);
		f.insert(3);
		f.insert(4);
		f.insert(5);
		System.out.println(f.extractMin());
		f.insert(6);
		System.out.println(f.extractMin());
		f.insert(7);
		f.insert(8);
		f.insert(9);
		f.insert(10);
		f.insert(11);
		f.insert(12);
		f.insert(13);
		f.insert(14);
		f.insert(15);
		f.insert(16);
		System.out.println(f.extractMin());
		System.out.println(f.extractMin());
		System.out.println(f.extractMin());
		System.out.println(f.extractMin());
		System.out.println(f.extractMin());*/
		
	}


}



