package com.fancypath.finding;

public class Edge {
	
	private Node destNode;
	
	public Edge(Node destNode) {
		this.destNode = destNode;
	}
	
	public Node getDestNode() {
		return this.destNode;
	}
	
	public void setDestNode(Node node) {
		destNode = node;
	}

	
}
