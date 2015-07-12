package com.fancypath.finding;

import java.util.LinkedList;

public class Map {
	
	Graph graph;
	
	public Map() {
		
		graph = new Graph(new Node());
		graph.addNonDirectedEdge(graph.rootNode, graph.createNode());
		graph.addNonDirectedEdge(graph.rootNode, graph.createNode());
		graph.addDirectedEdge(graph.rootNode, graph.createNode());
		
		System.out.println(graph.countEdges(graph.rootNode));
		
		LinkedList<Node> nodes = graph.getAdjacentNodes(graph.rootNode);
		Node n, n1, n2;
		
		n = nodes.getFirst();
		n1 = nodes.get(1);
		n2 = nodes.get(2);
		
		//System.out.println(graph.countEdges(n));
		
		graph.removeNode(n1);
		//System.out.println(graph.countEdges(graph.rootNode));
		
		Node n3 = graph.createNode();
		graph.addNonDirectedEdge(n, n3);
		graph.addDirectedEdge(n, n2);
		
		//System.out.println(graph.countEdges(n));
		
		graph.flipEdge(graph.rootNode, n2);
		//System.out.println(graph.countEdges(graph.rootNode));
		//System.out.println(graph.countEdges(n2));
		
		//System.out.println(graph.isNodeAdjacent(graph.rootNode, n2));
		graph.addNonDirectedEdge(n2, n);
		
		//System.out.println(graph.countEdges(n2));

		//System.out.println("n2 to root " + graph.isNodeAdjacent(n2, graph.rootNode));
		//System.out.println("n2 to n " + graph.isNodeAdjacent(n2, n));
		
		graph.removeEdge(n, n2);
		//System.out.println("root to n2: " + graph.isNodeAdjacent(graph.rootNode, n2));
		//System.out.println("n to n2: " + graph.isNodeAdjacent(n, n2));
		//System.out.println("n2 to n: " + graph.isNodeAdjacent(n2, n));
		//System.out.println(" ");
		
		graph.removeEdge(graph.rootNode, n2);
		System.out.println("edge from root to n2 removed");
		System.out.println("root to n2: " + graph.isNodeAdjacent(graph.rootNode, n2));
		System.out.println("root edges: "+graph.countEdges(graph.rootNode));
		System.out.println("n edges: "+graph.countEdges(n));
		System.out.println("n2 edges: "+graph.countEdges(n2));
		graph.removeEdge(n, n2);
		System.out.println("edge from n to n2 removed");
		System.out.println("n to n2: " + graph.isNodeAdjacent(n, n2));
		System.out.println(" ");
		graph.removeEdge(n, graph.rootNode);
		System.out.println("removed directed edge from n to root node");
		System.out.println("checking if directed edge from root node to n");
		System.out.println("root to n: " + graph.isNodeAdjacent(graph.rootNode, n));
		System.out.println(" ");
		System.out.println("checking if non directed edge from n node to n3");
		System.out.println("n to n3: " + graph.isNodeAdjacent(n, n3));
		
		
	}

}
