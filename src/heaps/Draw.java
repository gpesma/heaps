package heaps;
import java.awt.*;
import java.util.LinkedList;

import javax.swing.JFrame; 

public class Draw extends Canvas{

	
	
	  public void paint(Graphics g) {  
			BinomialNode node = new BinomialNode(1);
			BinomialHeap h = new BinomialHeap(node);
			h.insertNode(node);
			for(int i = 2; i < 20; ++i) {
				h.insert(i);
			} 
			
	  }  
	    
	  
	  public void printBinomial(BinomialHeap h) {
			LinkedList<BinomialNode> list = h.heap;
			BinomialNode b = h.first;
			int[][] nums = new int[b.getDegree()][b.getDegree()];
	  }
	  
	  public static void main(String[] args) {  
	        Draw m=new Draw();  
	        JFrame f=new JFrame();  
	        f.add(m);  
	        f.setSize(400,400);  
	        f.setLayout(null);  
	        f.setVisible(true);  
	    }  

}
