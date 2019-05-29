package org.insa.graph;

import org.insa.algo.AbstractInputData;
import org.insa.algo.shortestpath.ShortestPathData;

public class LabelStar extends Label implements Comparable<Label>{

	private double volOiseau;
	
	public LabelStar (Node n, ShortestPathData data, double cout) {
		super(n, data, cout);
		
		int Vmax=data.getGraph().getGraphInformation().getMaximumSpeed();
		
		//distance en mètres
    	double distance = data.getDestination().getPoint().distanceTo(n.getPoint());
    	
    	//si on veut la distance
    	if(data.getMode().equals(AbstractInputData.Mode.LENGTH))
    		this.volOiseau=distance;
    	
    	//si on veut la vitesse
    	else {
	    	//Vmax en kmh
	    	//if( Vmax == GraphStatistics.NO_MAXIMUM_SPEED)
	    	//	Vmax = 130;
	    	//vitesse en m/s
	    	double vitesse = Vmax/3.6;
	    	this.volOiseau=distance/vitesse;
    	}
	}

	public double getTotalCost() {
		return this.getCost() + this.volOiseau;
	}
}
