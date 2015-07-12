package com.fancypath.finding;

import java.util.LinkedList;
import java.util.Stack;

public class Graph {

	Node rootNode;
	
	public Graph(Node rootNode){
		this.rootNode = rootNode;
	}
	
	public Graph(){
		rootNode = new Node();
	}
	
	/*public Graph(properties of rootNode)
	 * {
	 * 	   rootNode = new Node();
	 *     //set properties of rootNode
	 * }
	 */
	
	public Node createNode() {
		Node node = new Node();
		return node;
	}
	
	public void addDirectedEdge(Node sourceNode, Node destNode) {
		addEdge(sourceNode, destNode, true);
	}
	
	public void addNonDirectedEdge(Node n, Node n2) {
		addEdge(n, n2, false);
	}
	
	private void addEdge(Node n, Node n2, boolean isDirected){
		if(n.getEdge(n2) == null){
			Edge edge = new Edge(n, n2, isDirected);
			n.addEdge(edge);
			n2.addEdge(edge);
			return;
		}
		
		if(n.getEdge(n2).isDirected() == false) {
			return;
		}
		
		if(n.getEdge(n2).getSourceNode() == n) {
			return;
		}
		
		n.getEdge(n2).setDirected(false);
	}
	
	public void flipEdge(Node sourceNode, Node destNode) {
		Edge e = sourceNode.getEdge(destNode);
		flipEdge(e);
	}
	
	public void flipEdge(Edge edge){
		Node n = edge.getDestNode();
		edge.setDestNode(edge.getSourceNode());
		edge.setSourceNode(n);
	}
	
	public void redirectEdge(Node sourceNode, Edge edge, Node destNode) {
	/*	if(sourceNode.getEdge(destNode) == null){
			
			Node n = edge.getDestNode();
			
			if(edge.isDirected()){
				n.removeEdge(edge);
				edge.setDestNode(destNode);
				if(destNode.getEdge(sourceNode) == null){
					destNode.addEdge(edge);
				}
			}else{
				removeEdge()
				
			}
			
			return;
		}else if (destNode.getEdge(sourceNode) != null){
			Edge e = destNode.getEdge(sourceNode);
			setDirectedEdge(e, true);
		}
		
		edge.getDestNode().removeEdge(edge);*/
	}
	
	public void setDirectedEdge(Edge edge, boolean isDirected){
		edge.setDirected(isDirected);
	}
	
	public void removeEdge(Node sourceNode, Node destNode) {
		if(sourceNode == null || destNode == null){
			return;
		}
		
		Edge edge = sourceNode.getEdge(destNode);
		
		if(edge == null){
			return;
		}
		
		if(edge.isDirected()){
			sourceNode.removeEdge(edge);
			destNode.removeEdge(edge);
			return;
		}
		
		if(sourceNode == edge.getSourceNode()){
			flipEdge(edge);
		}
		
		edge.setDirected(true);
	}

	public Edge getEdge(Node sourceNode, Node destNode) {
		Edge edge = sourceNode.getEdge(destNode);
		return edge;
	}
	
	public void removeNode(Node targetNode) {
		LinkedList<Edge> edges = targetNode.getAllEdges();
		
		for(Edge edge : edges){
			edge.getSourceNode().removeEdge(edge);
			edge.getDestNode().removeEdge(edge);
		}
	}
	
	public void uncheckAllNodes() {
		Stack<Node> checkedNodes = new Stack<Node>();
		checkedNodes.add(rootNode);
		Node currentNode;
		LinkedList<Edge> edges;
		
		while(checkedNodes.size() > 0) {
			currentNode = checkedNodes.pop();
			edges = currentNode.getAllEdges();
			
			for(Edge e: edges) {
				Node destNode = e.getDestNode();
				
				if(!destNode.isChecked){
					break;
				}
				
				destNode.isChecked = false;
				checkedNodes.push(destNode);
			}
		}
	}
	
	public LinkedList<Node> getAdjacentNodes(Node node) {
		LinkedList<Node> adjacentNodes = new LinkedList<Node>();
		LinkedList<Edge> edges = node.getAllEdges();
		
		for(Edge edge : edges) {
			if(edge.isDirected() && edge.getDestNode() == node){
				continue;
			}
			adjacentNodes.add(edge.getDestNode());
		}
		return adjacentNodes;
	}
	
	public boolean isNodeAdjacent(Node sourceNode, Node destNode) {
		LinkedList<Node> nodes = getAdjacentNodes(sourceNode);
		Edge edge;
		for(Node n : nodes) {
			edge = n.getEdge(sourceNode);
			
			if(edge == null){
				continue;
			}
			
			if(edge.isDirected() == false && edge.getDestNode() != destNode){
				continue;
			}
			
			return true;
		}
		System.out.println("dafuq?");
		return false;
	}
	
	public int countEdges(Node node) {
		LinkedList<Edge> edges = node.getAllEdges();
		int count = 0;
		
		for(Edge edge : edges){
			if(edge.isDirected() && edge.getDestNode() == node){
				continue;
			}
			++count;
		}
		return count;
	}
}
