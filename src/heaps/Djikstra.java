package heaps;
import java.util.*;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.File;
import java.util.Scanner;


public class Djikstra {

	
	HashMap<Integer, Vertex> vertices;
	ArrayList<Vertex> vs;
	public Djikstra() {
		super();
		vs = new ArrayList<Vertex>();
		vertices = new HashMap<Integer, Vertex>();
	}
	
	public ArrayList read() throws FileNotFoundException{
		//String file = "graph.csv";
		String file = "out.opsahl-usairport";
		BufferedReader br = null;
	    String line = "";
	    String cvsSplitBy = ",";

	    File f = new File(file);
        try {
            br = new BufferedReader(new FileReader(f));
            while ((line = br.readLine()) != null) {
                String[] args = line.split(" ");

                if(nodeExists(Integer.valueOf(args[0]))){
                		//Vertex v = vertices.get(Integer.valueOf(args[0]));
                		//Vertex v = vs.get(vs.indexOf(new Vertex(Integer.valueOf(args[0]))));
                		//v.setNode(new FibNode(Integer.valueOf(args[1])));
                		
                		Vertex v = findVertex(Integer.valueOf(args[0]));
                		Edge e = new Edge(Integer.valueOf(args[1]), Integer.valueOf(args[2]) + 10);
                		v.addEdge(e);
                		
                } else {
                		Vertex v = new Vertex(Integer.valueOf(args[0]));
                		Edge e = new Edge(Integer.valueOf(args[1]), Integer.valueOf(args[2]) + 10);
                		v.addEdge(e);
                		//v.setEdge(new Edge(Integer.valueOf(args[1]),new FibNode(Integer.valueOf(args[2]))));
                		v.setNode(new FibNode(Integer.valueOf(args[0])));
                		//vertices.put(Integer.valueOf(args[0]), v);
                		vs.add(v);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        updateEdges();
        
        	return vs;
        		
	}
	
	private boolean nodeExists(int num) {
		for(Vertex v: vs) {
			if(v.getData() == num)
				return true;
		}
		return false;	
	}

	private void updateEdges() {
		for(Vertex v: vs) {
			List<Edge> edges = v.getEdges();
			for(Edge e: edges) {
				e.setNode(findNode(e.getData_end()));
			}
		}
	}
	
	private Node findNode(int id) {
		for(Vertex v: vs) {
			if(v.getData() == id)
				return v.getNode();
		}
		return null;
	}
	
	private Vertex findVertex(int num) {
		for(Vertex v: vs) {
		if(v.getData() == num)
			return v;
	
		}
		return null;
	}
	
	public void shortestPath(Vertex vert, Heap h) {
		
		System.out.println(vert.getData());
		
		vert.getNode().setId(vert.getData());
		vert.getNode().setData(0);
		h.insertNode(vert.getNode());
		
		
		for(Vertex v : vs) {
			if(v.getNode().getId() == vert.getData())
				continue;
			v.getNode().setId(v.getData());
			v.getNode().setData(Integer.MAX_VALUE);
			//v.setDist(Integer.MAX_VALUE);
			h.insertNode(v.getNode());
		}
		h.decreaseKey(vert.getNode(),0);

		
		while(!h.isEmpty()) {
			Node minDist = h.extractMin();
			List<Edge> neighbors = findNeighbors(minDist);
			System.out.println();
			//System.out.println(minDist + " " + neighbors.size());
			for(Edge e: neighbors) {
				if(e.getNode() == null)
					continue;
				int alt = minDist.getData() + e.weight;
				//System.out.println(alt + " " + minDist.getData() + " " + e.getWeight()+ " " + e.getNode().getData() + " " + e.getNode().getId());
				if(alt < e.getNode().getData()) {
					e.getNode().setData(alt);
					h.decreaseKey(e.getNode(), alt);
				}	
				
			}
			//System.out.println(minDist.getData());
			System.out.println(minDist.getId() + " " + minDist.getData());
		}
	    System.out.println("done");
	}
	
	private List<Edge> findNeighbors(Node n){
		int id = n.getId();
		int i = 0;
		while(i < vs.size() && vs.get(i).getData() != id)
			i++;
		
		List<Edge> edges = vs.get(i).getEdges();
		List<Node> nodeList = new ArrayList<Node>();
		for(Edge e: edges)
			nodeList.add(e.getNode());
		
		return edges;
	}
	
	
}
