package org.insa.algo.shortestpath;

import java.util.*;
import java.util.List;

import org.insa.graph.*;
import org.insa.algo.*;
import org.insa.algo.utils.BinaryHeap;

public class DijkstraAlgorithm extends ShortestPathAlgorithm {

    public DijkstraAlgorithm(ShortestPathData data) {
        super(data);
    }

    @Override
    protected ShortestPathSolution doRun() {
    	//chronométrage
    	long startTime = System.currentTimeMillis();
    	//nb iterations
    	int nb=0;
    	
        ShortestPathData data = getInputData();
        Graph g = data.getGraph();
        AbstractSolution.Status status = AbstractSolution.Status.FEASIBLE;
        
        Node orig = data.getOrigin();
        
        if(orig.equals(data.getDestination())) {//si origine = destination
        	Path p = new Path(g, orig);
            status = AbstractSolution.Status.INFEASIBLE;
            ShortestPathSolution solution = new ShortestPathSolution(data, status, p);
            return solution;
        }

        BinaryHeap<Label> tas = new BinaryHeap<Label>();
        ArrayList<Label> tab = new ArrayList<Label>();
        
        
        for(int i=0; i<g.size(); i++) {
        	tab.add(creerLabel(g.get(i), data, Double.POSITIVE_INFINITY));
        }
    	tas.insert(creerLabel(orig, data, 0));
        
        boolean fini = false;
        //on marque un sommet par cycle donc on fait tab.size() cycles max
        while(!fini) {
        	//si on a tout exploré sans rien trouver
        	if(tas.isEmpty()) {
        		fini=true;
                status = AbstractSolution.Status.INFEASIBLE;
        	}
        	else {
        		//label avec le cout min
            	Label l = tas.deleteMin();
            	orig = l.getNode();
        		notifyNodeReached(orig);
	        	//on marque le sommet
	        	tab.get(orig.getId()).setMarque(true);
	        	
	            List<Arc> succ = orig.getSuccessors();
	            
	        	for(int i=0; i<succ.size(); i++) {
	        		
	        		Arc a = succ.get(i);
	        		Label dest = tab.get(a.getDestination().getId());

	        		//si la node n'est pas marquée
	        		if(!dest.getMarque()) {
		        		
	        			double nouvCout = l.getCost() + data.getCost(a);
	                	//si le cout du nouveau chemin est plus faible
	        			if( dest.getCost() > nouvCout ) {
	        				Label nouv = creerLabel(dest.getNode(), data, nouvCout);
        					nouv.setPere(a);
        					tas.insert(nouv);
        					tab.set(dest.getNode().getId(), nouv);
        					//System.out.println("node " + nouv.getNode().getId() + "  cout " + nouv.getCost() + "  cout total " + nouv.getTotalCost());
        					
	        			}
	        		}
	        		nb++;
	        	}
	        	if(orig.equals(data.getDestination()))
	        		fini = true;
        	}
        }
        
        List<Arc> chemin = new ArrayList<Arc>();
        
        while( !orig.equals(data.getOrigin()) ){
        	Arc a = tab.get(orig.getId()).getPere();
        	chemin.add(a);
        	orig = a.getOrigin();
        }
        Collections.reverse(chemin);

        Path p = new Path(g, chemin);
        ShortestPathSolution solution = new ShortestPathSolution(data, status, p);
        
        //fin chronométrage
    	long endTime = System.currentTimeMillis();

    	System.out.println("graph : 	nb arcs=" + g.getGraphInformation().getArcCount() + "	nb routes=" + (g.getGraphInformation().getTwoWaysRoadCount() + g.getGraphInformation().getOneWayRoadCount()));
    	
    	System.out.println(this.getClass() + "	nb iter=" + nb + "	temps=" + (endTime - startTime) + " millisecondes");
        
        return solution;
    }
    
    public Label creerLabel(Node n, ShortestPathData data, double cout) {
    	return new Label(n, data, cout);
    }
}
