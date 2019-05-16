package org.insa.algo.shortestpath;

import java.util.*;
import java.util.List;

import org.insa.graph.*;
import org.insa.algo.*;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
        ShortestPathData data = getInputData();
        Graph g = data.getGraph();
        AbstractSolution.Status status = AbstractSolution.Status.FEASIBLE;
        
        Node n = data.getOrigin();
        
        if(n.equals(data.getDestination())) {//si origine = destination
        	Path p = new Path(g, n);
            status = AbstractSolution.Status.INFEASIBLE;
            ShortestPathSolution solution = new ShortestPathSolution(data, status, p);
            return solution;
        }
        
        List<Label> tab = new ArrayList<Label>();
        
        for(int i=0; i<g.size(); i++) {
        	tab.add(new Label(i,Double.POSITIVE_INFINITY));
        }
        
        tab.set(n.getId(),new Label(n.getId(),0));
        
        boolean fini = false;
        //on marque un sommet par cycle donc on fait tab.size() cycles
        while(!fini) {
        	double min=Double.POSITIVE_INFINITY;
        	int minId=-1;
        	for(int i=0;i<tab.size();i++) {
        		if(!tab.get(i).getMarque() && min>tab.get(i).getCout()) {
        			min=tab.get(i).getCout();
        			minId=i;
        		}
        		
        	}
        	if(minId==-1) {
        		fini=true;
                status = AbstractSolution.Status.INFEASIBLE;
        	}
        	else {
        		notifyNodeReached(n);
	        	n=g.get(minId);
	        	tab.get(n.getId()).setMarque(true);
	        	
	            List<Arc> succ = n.getSuccessors();
	        	for(int i=0; i<succ.size(); i++) {
	        		
	        		Arc a = succ.get(i);
	        		int destId = a.getDestination().getId();
	        		int origId = a.getOrigin().getId();
	        		
	        		//si la node n'est pas marquée
	        		if( !tab.get(destId).getMarque() ) {
	        			
	        			double nouv = tab.get(origId).getCout() + getCost(a);
	                	//si le cout du nouveau chemin est plus faible
	        			if( tab.get(destId).getCout() > nouv )
	        				tab.set(destId, new Label(destId, nouv, false, a));
	        		}
	        	}
	        	if(n.equals(data.getDestination()))
	        		fini = true;
        	}
        }
        
        List<Arc> chemin = new ArrayList<Arc>();
        
        while( !n.equals(data.getOrigin()) ){
        	Arc a = tab.get(n.getId()).getArc();
        	chemin.add(a);
        	n = a.getOrigin();
        }
        Collections.reverse(chemin);

        Path p = new Path(g, chemin);
        ShortestPathSolution solution = new ShortestPathSolution(data, status, p);
        return solution;
    }
    
    public double getCost(Arc a) {
    	if(data.getMode().equals(AbstractInputData.Mode.LENGTH))
    		return (double)a.getLength();
    	else
    		return a.getMinimumTravelTime();
    }

}
