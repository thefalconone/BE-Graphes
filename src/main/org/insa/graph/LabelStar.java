package org.insa.graph;

public class LabelStar extends Label{

	private double coutDest;
	
	public LabelStar (int pCourant, double pCout, double coutDest) {
		super(pCourant, pCout);
		this.coutDest=coutDest;
		
	}

	public double getTotalCost() {
		return this.getCost() + this.coutDest;
	}

	private double getCostDest() {
		return this.coutDest;
	}
	public void setCostDest(double coutDest) {
		this.coutDest = coutDest;
	}
	
	public int compareTo(LabelStar l) {
		int result=0;
		//this is less than object
		if(this.getTotalCost() < l.getTotalCost())
			result = -1;
		//this is equal to object
		if(this.getTotalCost() == l.getTotalCost()) {
			if(this.coutDest < l.getCostDest())
				result = -1;
			else if(this.coutDest > l.getCostDest())
				result = 1;
			else
				result = 0;
		}
		//this is greater than object
		if(this.getTotalCost() > l.getTotalCost())
			result = 1;
		return result;
	}
}
