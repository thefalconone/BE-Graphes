package org.insa.algo.shortestpath;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.insa.algo.AbstractInputData;
import org.insa.algo.AbstractSolution;
import org.insa.algo.utils.BinaryHeap;
import org.insa.graph.Arc;
import org.insa.graph.Graph;
import org.insa.graph.LabelStar;
import org.insa.graph.Label;
import org.insa.graph.Node;
import org.insa.graph.Path;
import org.insa.graph.Point;

public class AStarAlgorithm extends DijkstraAlgorithm {

    public AStarAlgorithm(ShortestPathData data) {
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

        BinaryHeap<Label> tas = new BinaryHeap<Label>();
        ArrayList<LabelStar> tab = new ArrayList<LabelStar>();
        Point pointDest = data.getDestination().getPoint();
        double volOiseau = n.getPoint().distanceTo(pointDest);
        
        for(int i=0; i<g.size(); i++) {
        	tab.add(new LabelStar(i, Double.POSITIVE_INFINITY, volOiseau));
        }
    	tas.insert(new LabelStar(n.getId(), 0, volOiseau));
        
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
        		LabelStar orig = (LabelStar)tas.deleteMin();
        		notifyNodeReached(n);
	        	n=g.get(orig.getId());
	        	//on marque le sommet
	        	tab.get(orig.getId()).setMarque(true);
	        	
	            List<Arc> succ = n.getSuccessors();
	            
	        	for(int i=0; i<succ.size(); i++) {
	        		
	        		Arc a = succ.get(i);
	        		LabelStar dest = tab.get(a.getDestination().getId());

	        		//si la node n'est pas marquée
	        		if(!dest.getMarque()) {
		        		
	        			//cout depuis l'origine + cout arc + cout vers la dest
	        			double nouvCout = orig.getCost() + getCost(a) + a.getDestination().getPoint().distanceTo(pointDest);
	                	//si le cout du nouveau chemin est plus faible
	        			if( dest.getTotalCost() > nouvCout ) {
	        				LabelStar nouv = tab.get(dest.getId());
	        				nouv.setCost(nouvCout);
        					nouv.setPere(a);
        					tas.insert(nouv);
        					
	        			}
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
