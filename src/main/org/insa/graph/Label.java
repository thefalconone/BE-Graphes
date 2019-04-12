package org.insa.graph;

public class Label {

	private Node sommetCourant;
	private double cout;
	private boolean marque;
	private int idPere;
	private Arc arcPere;
	

	public Label (Node pSommet) {
		this.sommetCourant=pSommet;
		this.marque=false;
		this.cout=Double.POSITIVE_INFINITY;
		this.idPere=0;
		this.arcPere=null;
	}
	
	public Label (Node pSommet, double pCout, boolean pMarque, Arc pArcPere) {
		this.sommetCourant=pSommet;
		this.cout=pCout;
		this.marque=pMarque;
		this.arcPere=pArcPere;
		this.idPere=this.arcPere.getOrigin().getId();
	}
	
	public boolean getMarque() {
		return this.marque;
	}
	
	public double getCout() {
		return this.cout;
	}
	
	public void setMarque(boolean pMarque) {
		this.marque=pMarque;
	}
	
	
}
