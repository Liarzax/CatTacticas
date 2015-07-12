package com.fancypath.finding;

public class Edge {
	
	private Node sourceNode;
	private Node destNode;
	private boolean isDirected;
	
	public Edge(Node sourceNode, Node destNode, boolean isDirected) {
		this.sourceNode = sourceNode;
		this.destNode = destNode;
		this.isDirected = isDirected;
	}
	
	public Node getSourceNode(){
		return sourceNode;
	}
	
	public void setSourceNode(Node sourceNode){
		this.sourceNode = sourceNode;
	}
	
	public Node getDestNode() {
		return destNode;
	}
	
	public void setDestNode(Node destNode) {
		this.destNode = destNode;
	}
	
	public boolean isDirected(){
		return isDirected;
	}
	
	public void setDirected(boolean isDirected){
		this.isDirected = isDirected;
	}
}
