package org.insa.graph;

import org.insa.algo.shortestpath.ShortestPathData;

public class Label implements Comparable<Label>{

	private Node n;
	private double cout;
	private boolean marque;
	private Arc pere;
	

	public Label (Node n, ShortestPathData data, double cout) {
		this.n=n;
		this.marque=false;
		this.cout=cout;
		this.pere=null;
	}
	
	public Arc getPere() {
		return this.pere;
	}
	
	public void setPere(Arc a) {
		this.pere=a;
	}
	
	public boolean getMarque() {
		return this.marque;
	}
	
	public void setMarque(boolean pMarque) {
		this.marque=pMarque;
	}
	
	public Node getNode() {
		return this.n;
	}

	public double getCost() {
		return this.cout;
	}
	
	public void setCost(double cout) {
		this.cout=cout;
	}

	public double getTotalCost() {
		return this.cout;
	}
	
	public int compareTo(Label l) {
		int result=0;
		//this is less than object
		if(this.cout < l.getCost())
			result = -1;
		//this is equal to object
		if(this.cout == l.getCost())
			result = 0;
		//this is greater than object
		if(this.cout > l.getCost())
			result = 1;
		return result;
	}
}
