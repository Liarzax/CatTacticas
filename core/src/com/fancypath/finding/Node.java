package com.fancypath.finding;

import java.util.LinkedList;

public class Node {
	
	private LinkedList<Edge> edges = new LinkedList<Edge>();
	boolean isChecked = false;
	
	public void addEdge(Edge edge) {
		edges.add(edge);
	}
	
	public void removeEdge(Edge edge) {
		edges.remove(edge);
	}
	
	public Edge getEdge(Node node) {
		for(Edge e : edges){
			if(e.getDestNode() == node) {
				return e;
			}
		}
		return null;
	}
	
	public LinkedList<Edge> getAllEdges() {
		return edges;
	}
	
	
}
