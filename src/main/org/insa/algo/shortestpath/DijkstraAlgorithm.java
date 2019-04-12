package org.insa.algo.shortestpath;

import java.util.ArrayList;
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
        
        AbstractSolution.Status status = AbstractSolution.Status.INFEASIBLE;
        Node n = data.getOrigin();
        List<Label> tab = new ArrayList<Label>();
        
        for(int i=0; i<g.size(); i++) {
        	tab.add(new Label(i));
        }
        boolean fini = false;
        //on marque un sommet par cycle donc on fait tab.size() cycles
        while(!fini) {
        	
            List<Arc> succ = n.getSuccessors();
        	for(int i=0; i<succ.size(); i++) {
        		
        		Arc a = succ.get(i);
        		int destId = a.getDestination().getId();
        		int origId = a.getOrigin().getId();
        		
        		//si la node n'est pas marquée
        		if( !tab.get(destId).getMarque() ) {
        			
        			double nouv = tab.get(origId).getCout() + a.getMinimumTravelTime();
                	//si le cout du nouveau chemin est plus faible
        			if( tab.get(destId).getCout() > nouv )
        				tab.set(destId, new Label(destId, nouv, false, a));
        		}
        	}
        	tab.get(n.getId()).setMarque(true);
        	if(n.equals(data.getDestination()))
        		fini = true;
        }
        
        List<Arc> chemin = new ArrayList<Arc>();
        
        while(n.getId() == tab.get(n.getId()).getId() ){
        	Arc a = tab.get(n.getId()).getArc();
        	chemin.add(a);
        	n = a.getOrigin();
        }
        
        int size = chemin.size();
        for(int i=0; i<size/2; i++) {
        	chemin.set(i, chemin.get(size-i));
        }

        Path p = new Path(g, n);
        ShortestPathSolution solution = new ShortestPathSolution(data, status, p);
        return solution;
    }

}
