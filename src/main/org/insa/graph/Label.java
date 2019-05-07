package org.insa.graph;

public class Label {

	private int idCourant;
	private double cout;
	private boolean marque;
	private int idPere;
	private Arc arcPere;
	

	public Label (int pCourant, double pCout) {
		this.idCourant=pCourant;
		this.marque=false;
		this.cout=pCout;
		this.idPere=0;
		this.arcPere=null;
	}
	
	public Label (int pCourant, double pCout, boolean pMarque, Arc pArcPere) {
		this.idCourant=pCourant;
		this.cout=pCout;
		this.marque=pMarque;
		this.arcPere=pArcPere;
		this.idPere=this.arcPere.getOrigin().getId();
	}
	
	public Arc getArc() {
		return this.arcPere;
	}
	
	public boolean getMarque() {
		return this.marque;
	}
	
	public int getId() {
		return this.idCourant;
	}
	
	public double getCout() {
		return this.cout;
	}
	
	public double getIdPere() {
		return this.idPere;
	}
	
	public void setMarque(boolean pMarque) {
		this.marque=pMarque;
	}
	
	
}
