package org.insa.graph;

import org.insa.algo.AbstractInputData;
import org.insa.algo.shortestpath.ShortestPathData;

public class LabelStar extends Label implements Comparable<Label>{

	private double coutDest;
	
	public LabelStar (Node n, ShortestPathData data, double cout) {
		super(n, data, cout);
		
		int Vmax=data.getGraph().getGraphInformation().getMaximumSpeed();
		
		//distance en mètres
    	double distance = data.getOrigin().getPoint().distanceTo(n.getPoint());
    	
    	//si on vaut la distance
    	if(data.getMode().equals(AbstractInputData.Mode.LENGTH))
    		this.coutDest=distance;
    	
    	//si on veut la vitesse
    	else {
	    	//Vmax en kmh
	    	if( Vmax == GraphStatistics.NO_MAXIMUM_SPEED)
	    		Vmax = 130;
	    	//vitesse en m/s
	    	double vitesse = Vmax/3.6;
	    	this.coutDest=distance*vitesse;
    	}
	}

	public double getTotalCost() {
		return this.getCost() + this.coutDest;
	}

	private double getCostDest() {
		return this.coutDest;
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
