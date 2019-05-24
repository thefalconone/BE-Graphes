package org.insa.graph;

public class Label implements Comparable<Label>{

	private int idCourant;
	private double cout;
	private boolean marque;
	private Arc arcPere;
	

	public Label (int pCourant, double pCout) {
		this.idCourant=pCourant;
		this.marque=false;
		this.cout=pCout;
		this.arcPere=null;
	}
	
	public Arc getArc() {
		return this.arcPere;
	}
	
	public void setPere(Arc a) {
		this.arcPere=a;
	}
	
	public boolean getMarque() {
		return this.marque;
	}
	
	public int getId() {
		return this.idCourant;
	}

	public double getCost() {
		return this.cout;
	}
	
	public double getTotalCost() {
		return this.cout;
	}
	
	public void setCost(double cout) {
		this.cout=cout;
	}
	
	public void setMarque(boolean pMarque) {
		this.marque=pMarque;
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
