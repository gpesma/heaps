package heaps;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {

	public static void main(String[] args) throws FileNotFoundException {

		Heap h = new BinomialHeap();
		Djikstra d = new Djikstra();
		List<Vertex> l = d.read();
		d.shortestPath(l.get(0), h);
		
		/*		
		int[] first20kNums = new int[20000];
			
		Random rand = new Random();
		int operation;
		Heap h = new BinomialHeap();
		List<Node> nodes = new LinkedList<Node>();
		
		int limit = 50;
		for(int j = 1; j < 15; ++j) {
			limit *= 2;
			long startTime = System.currentTimeMillis();
			for(int i = 0; i < limit; ++i) {
					nodes.add(h.insert(i));
					/*operation = rand.nextInt(3);
					//System.out.println(i + " " + operation);
					switch(operation) {
						case 0:
							//h.insert(i);
							nodes.add(h.insert(i));
							break;
						case 1:
							//h.extractMin();
							//h.insert(i);
							break;
						case 2:
							if(!nodes.isEmpty())
								h.delete(nodes.remove(0));
							break;
						case 3:
							h.insert(i);
							break;	
						default:
							h.insert(i);
					}	
			}
			for(Node node: nodes) {
				h.decreaseKey(node, -node.getData());
			}
			
			//System.out.println();
			//for(int i = 0; i < limit; ++i) {
			//	Node num = h.extractMin();
				//if(num!=null)
				//	System.out.println(num.getData());
				//h.print();
			//}
			//System.out.println("new");
			System.out.println(System.currentTimeMillis() - startTime);
			h = new FibHeap();
			nodes.clear();*/
		//}
	}


	
}



