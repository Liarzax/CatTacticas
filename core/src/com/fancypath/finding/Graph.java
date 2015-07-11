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
	
	public void addNonDirectedEdge(Node node, Node node2) {
		addDirectedEdge(node, node2);
		addDirectedEdge(node2, node);
	}
	
	public Node createNode() {
		Node node = new Node();
		return node;
	}
	
	public void addDirectedEdge(Node sourceNode, Node destNode) {
		if(sourceNode.getEdge(destNode) == null){
			Edge edge = new Edge(destNode);
			sourceNode.addEdge(edge);
		}
	}
	
	public void flipEdge(Node sourceNode, Node destNode) {
		if(destNode.getEdge(sourceNode) == null){
			Edge tempEdge;
			tempEdge = sourceNode.getEdge(destNode);
			sourceNode.removeEdge(tempEdge);
			tempEdge.setDestNode(sourceNode);
			destNode.addEdge(tempEdge);
		}
	}
	
	public void redirectEdge(Node sourceNode, Edge edge, Node destNode) {
		if(sourceNode.getEdge(destNode) == null){
			edge.setDestNode(destNode);
			return;
		}
		
		sourceNode.removeEdge(edge);
	}
	
	public void removeEdge(Node sourceNode, Node destNode) {
		sourceNode.removeEdge(sourceNode.getEdge(destNode));
	}

	public Edge getEdge(Node sourceNode, Node destNode) {
		Edge edge = sourceNode.getEdge(destNode);
		return edge;
	}
	
	public void removeNode(Node targetNode) {
		Stack<Node> uncheckedNodes = new Stack<Node>();
		uncheckedNodes.add(rootNode);
		Node currentNode;
		LinkedList<Edge> edges;
		
		while(uncheckedNodes.size() > 0) {
			currentNode = uncheckedNodes.pop();
			edges = currentNode.getAllEdges();
			
			for(Edge e: edges) {
				Node destNode = e.getDestNode();
				
				if(destNode == targetNode){
					currentNode.removeEdge(e);
					continue;
				}else if(destNode.isChecked){
					break;
				}
				
				destNode.isChecked = true;
				uncheckedNodes.push(destNode);
			}
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
		
		for(Edge e : node.getAllEdges()) {
			adjacentNodes.add(e.getDestNode());
		}
		
		return adjacentNodes;
	}
	
	public int countEdges(Node node) {
		return node.getAllEdges().size();
	}
	
	
}
