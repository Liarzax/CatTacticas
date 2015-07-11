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
		Node n;
		
		n = nodes.getFirst();
		System.out.println(graph.countEdges(n));
		
		graph.addDirectedEdge(n, nodes.get(1));
		System.out.println(graph.countEdges(n));
		
		graph.removeNode(nodes.get(1));
		System.out.println("Root = " + graph.countEdges(graph.rootNode));
		System.out.println("First = " + graph.countEdges(n));
		
		graph.removeEdge(nodes.get(2), graph.rootNode);
		System.out.println("Third: " + graph.countEdges(nodes.get(2)));
		
		graph.redirectEdge(n, graph.getEdge(n, graph.rootNode), nodes.get(2));
		System.out.println("Root = " + graph.countEdges(graph.rootNode));
		System.out.println("First = " + graph.countEdges(n));
		System.out.println("Third: " + graph.countEdges(nodes.get(2)));
		
		graph.flipEdge(n, nodes.get(2));
		System.out.println("Root = " + graph.countEdges(graph.rootNode));
		System.out.println("First = " + graph.countEdges(n));
		System.out.println("Third: " + graph.countEdges(nodes.get(2)));
	}

}
